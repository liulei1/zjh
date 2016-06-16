package cn.ustc.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import cn.ustc.domain.Administer;
import cn.ustc.domain.Company;
import cn.ustc.domain.Consult;
import cn.ustc.domain.ConsultCheck;
import cn.ustc.domain.Professor;
import cn.ustc.domain.Project;
import cn.ustc.domain.Scheme;
import cn.ustc.domain.Vocation;
import cn.ustc.utils.DateUtils;
import cn.ustc.utils.GetPropertiesUtil;
import cn.ustc.utils.UploadAndDownloadUtils;
import cn.ustc.web.dao.VocationDAO;
import cn.ustc.web.service.CompanyService;
import cn.ustc.web.service.ConsultService;
import cn.ustc.web.service.SchemeService;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

/**
 * 咨询操作action
 * @author liu
 *
 */
@SuppressWarnings("serial")
public class ConsultAction extends ActionSupport implements ModelDriven<Consult> {
	private static final int PAGESIZE = 4;
	private Consult model = new Consult();
	private List<Consult> consults;
	
	/****************************文件上传****************************/
	private File file;
	private String fileFileName;
	private String fileContentType;

	/****************************** 注入 **********************************/
	@Autowired
	private ConsultService consultService;
	@Autowired
	private SchemeService schemeService;
	@Autowired
	private VocationDAO vocationDAO;
	@Autowired
	private CompanyService companyService;

	/************************************* 发布上传下载 ****************************************/

	/**
	 * 获取下载输出流
	 * @return
	 * @throws FileNotFoundException
	 */
	public InputStream getInputStream() throws FileNotFoundException {
		if (model == null || model.getFilePath() == null) {
			return null;
		}
		File file = new File(model.getFilePath());
		try {
			FileInputStream fis = new FileInputStream(file);
			return fis;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 下载文档
	 * @return
	 */
	public String download() {
		model = consultService.findById(model.getId());
		return "downSUCCESS";
	}
	
	/************************************* 需求操作 *****************************************/
		
	/**
	 * 咨询发布
	 * @return
	 */
	@InputConfig(resultName = "input")
	public String publishConsult() {
		Object o = ServletActionContext.getServletContext().getAttribute("user");
		// 判断发布用户是否合法
		if(!(o instanceof Company)){
			ServletActionContext.getRequest().setAttribute("error", "Illegal User");
			return ERROR;
		}
		Company c =(Company)o;
		c = companyService.findCompanyById(c.getId());
		
		// 判断发布权限
		int onGoingConsultCount = consultService.findCompanyOnGoingConsultCount(c.getId());
		if(Company.AUTHORITY_COMMON.equals(c.getAuthority())&&onGoingConsultCount>Company.AUTHORITY_COMMON_COUNT){
			ServletActionContext.getRequest().setAttribute("message", "Insufficient Privilege.You can just publish "+Company.AUTHORITY_COMMON_COUNT +" consults.");
			return "message";
		}else if(Company.AUTHORITY_VIP.equals(c.getAuthority())&&onGoingConsultCount>Company.AUTHORITY_VIP_COUNT){
			ServletActionContext.getRequest().setAttribute("message", "Insufficient Privilege.You can just publish "+Company.AUTHORITY_VIP_COUNT +" consults.");
			return "message";
		}
		
		if (file != null) {
			Properties properties = GetPropertiesUtil.getProperties();
			String fileRootPath = properties.getProperty("consultFileRootPath");
			String filePath = UploadAndDownloadUtils.restoreFile(file,fileRootPath);
			model.setFileName(fileFileName);
			model.setFilePath(filePath);
		}
		model.setState(Consult.UNCHECKED);
		model.setRelease_date(DateUtils.dateToString(new Date()));
		Company company = (Company) ServletActionContext.getServletContext()
				.getAttribute("user");
		model.setCom_id(company.getId()); // 获取企业的id
		boolean res = consultService.publish(model);
		if (res) {
			return "publishConsultSUCCESS";
		}
		return NONE;
	}

	// 
	/**
	 * 获取下载文件 类型
	 * @return
	 */
	public String getContentType() {
		// <param name="contentType">${contentType}</param>
		if (model == null || model.getFilePath() == null) {
			return null;
		} else {
			String fileName = model.getFileName();
			return ServletActionContext.getServletContext().getMimeType(
					fileName);
		}
	}

	/**
	 * 获取下载文件名
	 * @return
	 * @throws IOException
	 */
	public String getDownloadFileName() throws IOException {
		// <param name="contentDisposition">attachment;filename=${downloadFileName}</param>
		if (model == null || model.getFilePath() == null) {
			return null;
		} else {
			String fileName = model.getFileName();
			return UploadAndDownloadUtils.encodeDownloadFilename(fileName,
					ServletActionContext.getRequest().getHeader("user-agent"));
		}
	}

	/**
	 * 显示全部需求
	 * @return
	 */
	public String list(){
		List<Consult> list = consultService.consultList();
		consults = list;
		System.out.println(consults);
		return "listSUCCESS";
	}
	
	/**
	 * 查询登录企业所发布所有未完成的需求
	 * @return
	 */
	public String queryMyConsult(){
		//Company company = (Company) ServletActionContext.getServletContext().getAttribute("user");
		Object obj=ServletActionContext.getServletContext().getAttribute("user");
		if(obj instanceof Company){
			Company company=(Company)obj;
		
			// 记录的总条数
			int count = consultService.getCount(company.getId());
			model.setTotal(count);
			model.setPageCount((count-1)/PAGESIZE+1);
			
			DetachedCriteria criteria = DetachedCriteria.forClass(Consult.class);
			criteria.add(Restrictions.eq("com_id", company.getId()));
			//criteria.add(Restrictions.in("state", new String[]{Consult.ALLOW,Consult.UNCHECKED,Consult.REJECT}));
			int pageIndex = model.getPageIndex();
			if(pageIndex == 0){
				model.setPageIndex(1);
				consults = consultService.findByDetachedCriteria(criteria, 0, PAGESIZE);
			}else{
				consults = consultService.findByDetachedCriteria(criteria, (pageIndex-1)*PAGESIZE, PAGESIZE);
			}
		}else if(obj instanceof Professor){
			String id=ServletActionContext.getRequest().getParameter("company_id");
			
			//记录的条数
			int count=consultService.getCount(id);
			model.setTotal(count);//第多少条记录
			model.setPageCount((count-1)/PAGESIZE+1);//设置页数
			
			DetachedCriteria criteria = DetachedCriteria.forClass(Consult.class);
			criteria.addOrder(Order.desc("release_date"));
			int pageIndex=model.getPageIndex();
			if(pageIndex==0){
				model.setPageIndex(1);
				consults = consultService.findByDetachedCriteria(criteria, 0, PAGESIZE);
			}else{
				consults = consultService.findByDetachedCriteria(criteria, (pageIndex-1)*PAGESIZE, PAGESIZE);
			}
		}
		return "queryMyConsultSUCCESS";
	}
	
	/**
	 * 查询登录企业所发布所有未完成的需求
	 * @return
	 */
	public String queryMyConsultReturnJson(){
		Company company = (Company) ServletActionContext.getServletContext().getAttribute("user");
		
		// 记录的总条数
		int count = consultService.getCount(company.getId());
		int pageSize = 2;
		model.setTotal(count);
		model.setPageSize(pageSize);
		
		DetachedCriteria criteria = DetachedCriteria.forClass(Consult.class);
		criteria.add(Restrictions.eq("com_id", company.getId()));
		criteria.add(Restrictions.in("state", new String[]{Consult.ALLOW,Consult.UNCHECKED,Consult.REJECT}));
		int pageIndex = model.getPageIndex();
		if(pageIndex == 0){
			consults = consultService.findByDetachedCriteria(criteria, 0, pageSize);
		}else{
			consults = consultService.findByDetachedCriteria(criteria, (pageIndex-1)*pageSize, pageSize);
		}
		return SUCCESS;
	}
	
	/**
	 * 显示未审核
	 * @return
	 */
	public String unCheckList(){
		List<Consult> list = consultService.unCheckConsultList();
		consults = list;
		System.out.println(consults);
		return "unCheckListSUCCESS";
	}
	
	/**
	 * 显示通过
	 * @return
	 */
	public String allowList(){
		int count=consultService.allowCount();
		//总条数
		model.setTotal(count);
		//当前页
		model.setPageCount((count-1)/PAGESIZE+1);
		DetachedCriteria criteria = DetachedCriteria.forClass(Consult.class);
		criteria.add(Restrictions.eq("state", Consult.ALLOW));
		int pageIndex = model.getPageIndex();
		if(pageIndex == 0){
			model.setPageIndex(1);
			consults = consultService.findByDetachedCriteria(criteria, 0, PAGESIZE);
		}else{
			consults = consultService.findByDetachedCriteria(criteria, (pageIndex-1)*PAGESIZE, PAGESIZE);
		}
		
		return "allowListSUCCESS";
	}
	
	/**
	 * 根据查看咨询内容
	 * @return
	 */
	public String view(){
		Consult consult = consultService.findById(model.getId());
		model = consult;
		return "viewSUCCESS";
	}
	
	/****************************************审核*******************************************/
	/**
	 * 咨询审核通过
	 * @return
	 */
	public String allow(){
		ConsultCheck consultCheck = new ConsultCheck();
		Administer admin = (Administer) ServletActionContext.getServletContext().getAttribute("user");
		consultCheck.setAdmin_id(admin.getId());
		consultCheck.setCheck_datetime(new Date());
		consultCheck.setCons_id(model.getId());
		
		// 获取咨询
		Consult consult = consultService.findById(model.getId());
		// 获取发布咨询的企业
		Company company = companyService.findCompanyById(consult.getCom_id());
		
		String res = consultService.consultAllow(consult, company, consultCheck);
		if("success".equals(res)){
			return "checkSUCCESS";
		}else if("balanceNotEnough".equals(res)){
			return "checkSUCCESS";
		}else {
			this.addActionError("审核失败！");
			return "checkFAIL";
		}
	}
	
	/**
	 * 审核拒绝
	 * @return
	 */
	public String reject(){
		Administer admin = (Administer) ServletActionContext.getServletContext().getAttribute("user");
		ConsultCheck consultCheck = new ConsultCheck();
		consultCheck.setAdmin_id(admin.getId());
		consultCheck.setCheck_datetime(new Date());
		consultCheck.setCons_id(model.getId());
		// TODO 审批拒绝原因 
		boolean res = consultService.consultReject(model.getId(),consultCheck);
		if(res){
			return "checkSUCCESS";
		}else {
			this.addActionError("审核失败！");
			return "checkFAIL";
		}
	}
	
	/**
	 * 咨询接受
	 * @return
	 */
	public String recieve(){
		Consult consult = consultService.findById(model.getId());
		Project project = new Project();
		
		project.setScm_id(model.getScm_id());
		project.setStart_date(DateUtils.dateToString(new Date()));
		project.setCurrent_state(Project.ONGOING);
		project.setConsult(consult);
		
		Scheme scheme = schemeService.findById(model.getScm_id());
		project.setProf_id(scheme.getProfessor().getId());
		project.setCom_id(consult.getCom_id());
		// 决定方案后,会插入一条 project记录并修改consult的状态
		consultService.consultRecieve(project,consult);
		return NONE;
	}
	
	/**
	 * 首页获取推荐的咨询
	 * @return
	 */
	public String recommendConsult(){
		consults = consultService.getRecommendConsult(5);
		for (Consult c : consults) {
			Vocation vocation = vocationDAO.findVocationById(c.getCategory());
			c.setCategory(vocation.getName());
		}
		return SUCCESS;
	}
	
	/******************************************************************************/
	public void setFile(File file) {
		this.file = file;
	}
	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}
	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}
	@Override
	public Consult getModel() {
		return model;
	}
	public List<Consult> getConsults() {
		return consults;
	}
	public void setConsults(List<Consult> consults) {
		this.consults = consults;
	}
}
