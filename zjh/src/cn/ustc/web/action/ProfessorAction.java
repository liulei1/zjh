package cn.ustc.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;

import cn.ustc.domain.Message;
import cn.ustc.domain.Professor;
import cn.ustc.domain.User;
import cn.ustc.domain.Vocation;
import cn.ustc.utils.DateUtils;
import cn.ustc.utils.GetPropertiesUtil;
import cn.ustc.utils.UploadAndDownloadUtils;
import cn.ustc.web.dao.VocationDAO;
import cn.ustc.web.service.MessageService;
import cn.ustc.web.service.ProfessorService;
import cn.ustc.web.service.UserService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

public class ProfessorAction extends ActionSupport implements ModelDriven<Professor>{
	private final int PAGESIZE = 10;
	private List<Professor> professors;
	private Professor professor=new Professor();
	
	@Autowired
	private ProfessorService professorService;
	@Autowired
	private VocationDAO vocationDAO;
	@Autowired
	private UserService userService;
	@Autowired
	private MessageService messageService;
	
	private File file;

	public void setFile(File file) {
		this.file = file;
	}
	private String imgPath;	// 上传头像的本地存储路径，相对tomcat静态资源路径
	private String imgErrorResult;	// 上传错误结果
	
	public String getImgErrorResult() {
		return imgErrorResult;
	}
	public String getImgPath() {
		return imgPath;
	}
	
	// 用户注册
	@InputConfig(resultName = "professorRegister")
	public String register() {
		User user=(User) ServletActionContext.getServletContext().getAttribute("user");
		if(user==null){
			professor.setState("1");
		}else{
			professor.setState("0");
		}
		
		if(professor.getName()==null){
			return "professorRegister";
		}
		//设置用户类型为专家
		professor.setUsertype(Professor.PROFESSOR);
		professorService.insertProfessor(professor);
		ActionContext context = ActionContext.getContext();
		context.put("result", "operate success!");
		return "professorRegisterSUCCESS";
	}
	
	public String management(){
		professors=professorService.findAllProfessor();	
		return "professorlist";
	}
	
	// 检查用户名是否存在
	public String checkProfessorName(){
		List<Professor> professors=null;
		if(!("".equals(professor.getName()))){
			professors=professorService.findProfessorByName(professor.getName());
			if(professors.size()!=0){
				professor.setNameExsit(true);
			}else{
				professor.setNameExsit(false);
			}
		}
		return SUCCESS;
	}
	
	// 查找专家
	public String professorSearch(){
		String cat=(String) ServletActionContext.getRequest().getParameter("category");
		professors=professorService.findProfessorByVocation(cat);
		return "findSuccess";
	}
	
	// 通过名字查找专家
	public String professorSearchByName(){
		String name=(String) ServletActionContext.getRequest().getParameter("findByName");
		professors=professorService.findProfessorByName(name);
		return "findSuccess";
	}

	// 查看当前登录的专家用户信息
	public String viewProfessorInfo(){
		Professor user = (Professor) ServletActionContext.getServletContext().getAttribute("user");
		professor = professorService.findProfessorById(user.getId());
		return "viewProfessorInfoSUCCESS";
	}
	
	// 更新信息
	public String updateProfessorInfo(){
		Professor user = (Professor) ServletActionContext.getServletContext().getAttribute("user");
		professor.setId(user.getId());
		professorService.updateInfo(professor);
		ActionContext context = ActionContext.getContext();
		context.put("result", "operate success");
		return "updateProfessorInfoSUCCESS";
	}
	
	// 修改密码视图
	public String viewChangePassword(){
		return "viewChangePasswordSUCCESS";
	}
	
	// 更新密码
	public String changePassword(){
		Professor user = (Professor) ServletActionContext.getServletContext().getAttribute("user");
		Professor p = professorService.findProfessorById(user.getId());
		ActionContext context = ActionContext.getContext();
		if(p.getPassword().equals(professor.getPassword())){
			p.setPassword(professor.getNewPassword());
			professorService.update(p);
			context.put("result", "operate success");
		}else{
			context.put("result", "passwords entered did not match");
		}
		return "changePasswordSUCCESS";
	}
	
	public String viewRegister(){
		return "viewRegisterSUCCESS";
	}
	
	/**
	 * 通过传来的专家的id 获取专家信息
	 * @return
	 */
	public String viewProfessorInfoById(){
		String id = professor.getId();
		professor = professorService.findProfessorById(id);
		Vocation vocation = vocationDAO.findVocationById(professor.getField());
		professor.setField(vocation.getName());
		return "viewProfessorInfoByIdSUCCESS";
	}
	
	// 返回状态为0的professor列表
	public String unauditlist(){
		professors=professorService.findAllUnaudit();
		return "uneditProfessors";
	}
	
	//通过一个porfessor申请
	public String pass(){
		professorService.pass(professor.getId());
		//为user放一条消息,先查找到professor的名字，再拿着名字查找user,给这个id的user放一条信息
		professor = professorService.findProfessorById(professor.getId());
		String name=professor.getName();
		User user=userService.findUserByName(name).get(0);
		String userID=user.getId();
		//生成一条完整的message
		Message message=new Message();
		message.setRecipientId(userID);
		message.setType(Message.TOUSER);
		String time=DateUtils.dateToString(new Date());
		message.setSendTime(time);
		message.setState(Message.UNREAD);
		message.setTitle("系统通知");
		message.setContent("您注册成为专家的申请已经通过，现在可以以专家身份登录");
		messageService.sendMessage(message);
		return "passSuccess";
	}
	
	//拒绝一个professor申请
	public String refuse(){
		professor=professorService.findProfessorById(professor.getId());
		String name=professor.getName();
		User user=userService.findUserByName(name).get(0);
		String userID=user.getId();
		//生成一条完整的message
		Message message=new Message();
		message.setRecipientId(userID);
		message.setType(Message.TOUSER);
		String time=DateUtils.dateToString(new Date());
		message.setSendTime(time);
		message.setState(Message.UNREAD);
		message.setTitle("系统通知");
		message.setContent("您注册成为专家的申请未通过，可再次申请");
		messageService.sendMessage(message);
		
		professorService.refuse(professor.getId());
		return "refused";
		
	}
	
	// 首页推荐的专家
	public String recommendProfessor(){
		professors = professorService.getRecommendProfessor(5);
		for (Professor p : professors) {
			try{
				Vocation vocation = vocationDAO.findVocationById(p.getField());
				p.setField(vocation.getName());
			}catch (Exception e) {
			}
		}
		return SUCCESS;
	}
	
	// 专家头像上传
	public String uploadProfessorImage(){
		
		// 判断文件不为空，且是图片
		if (file != null) {
			HttpServletRequest request = ServletActionContext.getRequest();
			// 图片大小默认最大10M
			String fileSize = request.getParameter("fileSize")!=null?request.getParameter("fileSize"):"10";
			try {
				FileInputStream f = new FileInputStream(file);
				int imgSize = f.available()>>20; // 单位变为M
				if(imgSize<Integer.valueOf(fileSize)){
					String imgRootPath = GetPropertiesUtil.getPropertiesValueByKey("imgRootPath");
					
					// 更新信息
					professor = (Professor)ServletActionContext.getServletContext().getAttribute("user");
					imgPath = UploadAndDownloadUtils.restoreFile(file,imgRootPath,professor.getId());
					Professor professorById = professorService.findProfessorById(professor.getId());
					professorById.setImage(imgPath);
					professorService.update(professorById);
				}else {
					imgErrorResult = "上传大小不得高于10M";
					return ERROR;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return SUCCESS;
	}
	
	/**
	 * 分页查询所有专家
	 * @return
	 */
	public String getProfessorWithPage(){
		int count = professorService.getProfessorCount();
		professor.setTotal(count);
		professor.setPageCount((count-1)/PAGESIZE+1);
		DetachedCriteria criteria = DetachedCriteria.forClass(Professor.class);
		int pageIndex = professor.getPageIndex();
		if(pageIndex == 0){
			professor.setPageIndex(1);
			professors = professorService.findByDetachedCriteria(criteria, 0, PAGESIZE);
		}else{
			professors = professorService.findByDetachedCriteria(criteria, (pageIndex-1)*PAGESIZE, PAGESIZE);
		}
		
		for (Professor p : professors) {
			try {
				Vocation vocation = vocationDAO.findVocationById(p.getField());
				p.setField(vocation.getName());
			} catch (Exception e) {
			}
		}
		return "getProfessorWithPageSUCCESS";
	}
	
	/**
	 * 查询所有专家
	 * @return
	 */
	public String getAllProfessor(){
		DetachedCriteria criteria = DetachedCriteria.forClass(Professor.class);
		professors = professorService.findByDetachedCriteria(criteria, 0, PAGESIZE);
		return "getAllProfessorSUCCESS";
	}
	
	/**
	 * 跳转到专家信息修改页面 
	 * 调用者：管理员用户
	 * @return
	 */
	public String editProfessorView(){
		String id = professor.getId();
		professor = professorService.findProfessorById(id);
		return "editProfessorViewSUCCESS";
	}
	
	/********************************************************************/
	public List<Professor> getProfessors() {
		return professors;
	}
	@Override
	public Professor getModel() {
		return professor;
	}
}
