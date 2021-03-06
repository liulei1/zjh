package cn.ustc.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import cn.ustc.domain.Company;
import cn.ustc.domain.Message;
import cn.ustc.domain.User;
import cn.ustc.domain.Vocation;
import cn.ustc.utils.DateUtils;
import cn.ustc.utils.GetPropertiesUtil;
import cn.ustc.utils.UploadAndDownloadUtils;
import cn.ustc.web.dao.VocationDAO;
import cn.ustc.web.service.CompanyService;
import cn.ustc.web.service.MessageService;
import cn.ustc.web.service.UserService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

/**
 * 企业用户操作action
 * @author liu
 *
 */
public class CompanyAction extends ActionSupport implements ModelDriven<Company>{
	private Company company=new Company();
	private List<Company> companys;
	
	@Autowired
	private CompanyService companyService;
	@Autowired
	private UserService userService;
	@Autowired
	private MessageService messageService;
	@Autowired
	private VocationDAO vocationDAO;
	
	/**
	 * 注册
	 * @return
	 */
	@InputConfig(resultName = "companyRegister")
	public String register() {
		User user=(User) ServletActionContext.getServletContext().getAttribute("user");
		if(user!=null){
			company.setState("0");
		}else{
			company.setState("1");
		}
		if(company.getName()==null){
			return "companyRegister";
		}
		//设置用户类型
		company.setUsertype(Company.COMPANY);
		company.setPoints(0);// 积点初始为0
		//新注册的用户的积分权限设置为普通的
		company.setAuthority(Company.AUTHORITY_COMMON);
		companyService.insertCompany(company);
		return "companyRegisterSuccess";
	}
	
	/**
	 * 检查企业用户名是否存在
	 * @return
	 */
	public String checkCompanyName(){
		List<Company> companys=null;
		if(!("".equals(company.getName()))){
			companys=companyService.findCompanyByName(company.getName());
			if(companys.size()!=0){
				company.setNameExsit(true);
			}else{
				company.setNameExsit(false);
			}
		}
		return SUCCESS;
	}
	
	/**
	 * 领域查找企业
	 * @return
	 */
	public String companySearch(){
		String cat=ServletActionContext.getRequest().getParameter("category");
		companys=companyService.findCompanyByVocation(cat);
		return "findSuccess";
	}
	
	/**
	 * 通过姓名查找企业
	 * @return
	 */
	public String companySearchByName(){
		String name=ServletActionContext.getRequest().getParameter("findByName");
		System.out.println(name);
		companys=companyService.findCompanyByName(name);
		return "findSuccess";
	}

	/**
	 * 跳转到充值界面
	 * @return
	 */
	public String skipBalancePage(){
		User user = (User)ServletActionContext.getServletContext().getAttribute("user");
		String id = user.getId();
		company = companyService.findCompanyById(id);
		return "skipBalancePageSUCCESS";
	}
	
	/**
	 * 企业用户充值
	 * @return
	 */
	public String addBalance(){
		String id = company.getId();
		double balance = Double.parseDouble(company.getBalance());
		if(balance < 0){
			balance = 0;
		}
		companyService.addBalance(id, balance);
		return "addBalanceSUCCESS";
	}
	
	/**
	 * 查看企业用户信息
	 * @return
	 */
	public String viewCompanyInfo(){
		Company user = (Company) ServletActionContext.getServletContext().getAttribute("user");
		company = companyService.findCompanyById(user.getId());
		return "viewCompanyInfoSUCCESS";
	}
	
	/**
	 * 更新信息
	 * @return
	 */
	public String updateProfessorInfo(){
		Company user = (Company) ServletActionContext.getServletContext().getAttribute("user");
		company.setId(user.getId());
		companyService.updateInfo(company);
		ActionContext context = ActionContext.getContext();
		context.put("result", "operate success");
		return "updateProfessorInfoSUCCESS";
	}
	
	/**
	 * 修改密码视图
	 * @return
	 */
	public String viewChangePassword(){
		return "viewChangePasswordSUCCESS";
	}
	
	/**
	 * 更新密码
	 * @return
	 */
	public String changePassword(){
		Company user = (Company) ServletActionContext.getServletContext().getAttribute("user");
		Company c = companyService.findCompanyById(user.getId());
		ActionContext context = ActionContext.getContext();
		if(c.getPassword().equals(company.getPassword())){
			c.setPassword(company.getNewPassword());
			companyService.update(c);
			context.put("result", "operate success");
		}else{
			context.put("result", "passwords entered did not match");
		}
		return "changePasswordSUCCESS";
	}
	
	private File file;
	private String imgPath;

	/**
	 * 查询所有状态为0的company,放在companys中
	 * @return
	 */
	public String unauditlist(){
		companys=companyService.findAllUnaudit();
		return "uneditCompanys";
	}
	
	/**
	 * 通过一个company申请
	 * @return
	 */
	public String pass(){
		//传进来的company只有id一个属性
		companyService.pass(company.getId());
		//为user放一条消息，先查到company的名字，再拿着名字查找user,给这个id的user放一条消息
		company=companyService.findCompanyById(company.getId());
		String name=company.getName();
		User user=companyService.findCompanyByName(name).get(0);
		String userID=user.getId();
		//生成一条完整的message
		Message message=new Message();
		message.setRecipientId(userID);
		message.setType(Message.TOUSER);
		String time=DateUtils.dateToString(new Date());
		message.setSendTime(time);
		message.setState(Message.UNREAD);
		message.setTitle("系统通知");
		message.setContent("您注册成为企业用户的申请已经通过，现在可以以企业用户身份登录");
		messageService.sendMessage(message);
		return "passSuccess";
	}
	
	/**
	 * 拒绝一个company申请
	 * @return
	 */
	public String refuse(){
		company=companyService.findCompanyById(company.getId());
		String name=company.getName();
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
		
		companyService.refuse(company.getId());
		return "refused";
		
	}
	
	/**
	 * 头像上传
	 * @return
	 */
	public String uploadCompanyImage(){
		
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
					//String imgRootPath = ServletActionContext.getServletContext().getRealPath(uploadPath);
					
					// 更新信息
					company = (Company)ServletActionContext.getServletContext().getAttribute("user");
					imgPath = UploadAndDownloadUtils.restoreFile(file,imgRootPath,company.getId());
					Company companyById = companyService.findCompanyById(company.getId());
					companyById.setImage(imgPath);
					companyService.update(companyById);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return SUCCESS;
	}
	
	/**
	 * 跳转到充值vip页面
	 * @return
	 */
	public String toVipPage(){
		Object o = ServletActionContext.getServletContext().getAttribute("user");
		// 判断发布用户是否合法
		if(!(o instanceof Company)){
			ServletActionContext.getRequest().setAttribute("error", "Illegal User");
			return ERROR;
		}
		Company c =(Company)o;
		company = companyService.findCompanyById(c.getId());
		if(Company.AUTHORITY_COMMON.equals(company.getAuthority())){
			company.setAuthority("Common User");
		}else {
			company.setAuthority("VIP User");
		}
		if("".equals(company.getVipEndTime())||company.getVipEndTime() == null){
			company.setVipEndTime("-");
		}
//		String vipBalance = GetPropertiesUtil.getPropertiesValueByKey("VIPBalance");
		company.setVipBalance(GetPropertiesUtil.getPropertiesValueByKey("VIPBalance"));
		return "toVipPageSUCCESS";
	}
	
	/**
	 * 充值vip
	 * @return
	 */
	public String toVip(){
		Object o = ServletActionContext.getServletContext().getAttribute("user");
		// 判断发布用户是否合法
		if(!(o instanceof Company)){
			ServletActionContext.getRequest().setAttribute("error", "Illegal User");
			return ERROR;
		}
		Company c =(Company)o;
		c = companyService.findCompanyById(c.getId());
		// 判断充值余额
		String effectiveMonth = company.getEffectiveMonth();
		// 获取充值月数
		Integer month = Integer.valueOf(effectiveMonth);
		
		float price = Float.valueOf(GetPropertiesUtil.getPropertiesValueByKey("VIPBalance"));
		price = price*month;
		float balance = Float.valueOf(c.getBalance());
		if(price>balance){
			ServletActionContext.getRequest().setAttribute("error", "Sorry, Your balance is not enough!");
			return "message";
		}else {
			c.setBalance(String.valueOf(balance-price));
			c.setAuthority(Company.AUTHORITY_VIP);
			// 有效期一个月
//			String vipEndTime = c.getVipEndTime();
//			if(vipEndTime == null){
				c.setVipEndTime(DateUtils.dateAddMonthToString(new Date(), month));
//			}
			companyService.update(c);
			return SUCCESS;
		}
	}
	
	/**
	 * 查询所有企业
	 * @return
	 */
	public String getAllCompany(){
		companys = companyService.findAllCompany();
		return "getAllCompanySUCCESS";
	}
	
	/**
	 * 跳转到企业信息修改页面 
	 * 调用者：管理员用户
	 * @return
	 */
	public String editCompanView(){
		String id = company.getId();
		company = companyService.findCompanyById(id);
		return "editCompanViewSUCCESS";
	}
	
	/**
	 * 通过传来的企业的id 获取企业信息
	 * @return
	 */
	public String viewCompanyInfoById(){
		String id = company.getId();
		company = companyService.findCompanyById(id);
		Vocation vocation = vocationDAO.findVocationById(company.getField());
		company.setField(vocation.getName());
		return "viewCompanyInfoByIdSUCCESS";
	}
	
	/**
	 * 删除企业用户
	 */
	public String delete(){
		String id=company.getId();
		companyService.deleteCompanyById(id);
		return "deleteCompanySUCCESS";
	}
	
	/**************************************************************/
	public List<Company> getCompanys() {
		return companys;
	}
	@Override
	public Company getModel() {
		return company;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public String getImgPath() {
		return imgPath;
	}
}
