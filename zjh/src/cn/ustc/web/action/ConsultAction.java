package cn.ustc.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import cn.ustc.domain.Company;
import cn.ustc.domain.Consult;
import cn.ustc.domain.ConsultCheck;
import cn.ustc.domain.Professor;
import cn.ustc.domain.Project;
import cn.ustc.utils.UploadAndDownloadUtils;
import cn.ustc.web.service.ConsultService;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

public class ConsultAction extends ActionSupport implements ModelDriven<Consult> {
	private Consult model = new Consult();
	@Override
	public Consult getModel() {
		return model;
	}
	
	/****************************文件上传****************************/
	private File file;
	private String fileFileName;
	@SuppressWarnings("unused")
	private String fileContentType;
	public void setFile(File file) {
		this.file = file;
	}
	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}
	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}
	/****************************************************************/
	private ConsultService consultService;
	public void setConsultService(ConsultService consultService) {
		this.consultService = consultService;
	}
	/*************************************发布上传下载****************************************/
	
		
		// 获取下载输出流
		public InputStream getInputStream() throws FileNotFoundException{
			if(model == null || model.getFilePath() == null) {
				return null;
			}
			File file = new File(model.getFilePath());
//			FileInputStream fis = new FileInputStream(file);
			try {
				FileInputStream fis = new FileInputStream(file);
				return fis;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				return null;
			}
		}
		
		// 下载文档
		public String download(){
			model = consultService.findById(model.getId());
			return "downSUCCESS";
		}
	
	/************************************* 需求操作 *****************************************/
		
	private List<Consult> consults;
	public List<Consult> getConsults() {
		return consults;
	}

	// 咨询发布
	@InputConfig(resultName = "input")
	public String publish() {
		if (file != null) {
			String fileRootPath = ServletActionContext.getServletContext()
					.getRealPath("/document");
			String filePath = UploadAndDownloadUtils.restoreFile(file,
					fileRootPath);
			model.setFileName(fileFileName);
			model.setFilePath(filePath);
		}
		model.setState("0");
		model.setRelease_date(new Date());
		Company company = (Company) ServletActionContext.getServletContext()
				.getAttribute("user");
		model.setCom_id(company.getId()); // 获取企业的id
		boolean res = consultService.publish(model);
		if (res) {
			return "publishSUCCESS";
		}
		return NONE;
	}

	// 获取下载文件 类型
	// <param name="contentType">${contentType}</param>
	public String getContentType() {
		if (model == null || model.getFilePath() == null) {
			return null;
		} else {
			String fileName = model.getFileName();
			return ServletActionContext.getServletContext().getMimeType(
					fileName);
		}
	}

	// 获取下载文件名
	// <param
	// name="contentDisposition">attachment;filename=${downloadFileName}</param>
	public String getDownloadFileName() throws IOException {
		if (model == null || model.getFilePath() == null) {
			return null;
		} else {
			String fileName = model.getFileName();
			return UploadAndDownloadUtils.encodeDownloadFilename(fileName,
					ServletActionContext.getRequest().getHeader("user-agent"));
		}
	}

	// 显示全部
	public String list(){
		List<Consult> list = consultService.consultList();
		consults = list;
		System.out.println(consults);
		return "listSUCCESS";
	}
	
	// 查询登录企业所发布的所有需求
	public String queryMyConsult(){
		Company company = (Company) ServletActionContext.getServletContext().getAttribute("user");
		DetachedCriteria criteria = DetachedCriteria.forClass(Consult.class);
		criteria.add(Restrictions.eq("com_id", company.getId()));
		consults = consultService.findConsultsByDetachedCriteria(criteria);
		return "queryMyConsultSUCCESS";
	}
	
	// 显示未审核
	public String unCheckList(){
		List<Consult> list = consultService.unCheckConsultList();
		consults = list;
		System.out.println(consults);
		return "unCheckListSUCCESS";
	}
	
	// 显示通过
	public String allowList(){
		List<Consult> list = consultService.allowConsultList();
		consults = list;
		System.out.println(consults);
		return "allowListSUCCESS";
	}
	
	// 根据查看咨询内容
	public String view(){
		Consult consult = consultService.findById(model.getId());
		model = consult;
		return "viewSUCCESS";
	}
	
	/****************************************审核*******************************************/
	// 允许
	public String allow(){
		ConsultCheck consultCheck = new ConsultCheck();
		// TODO 获取管理员信息
		consultCheck.setAdmin_id(9527);
		consultCheck.setCheck_datetime(new Date());
		consultCheck.setCons_id(model.getId());
		
		boolean res = consultService.consultAllow(model.getId(),consultCheck);
		if(res){
			return "checkSUCCESS";
		}else {
			this.addActionError("审核失败！");
			return "checkFAIL";
		}
	}
	
	// 拒绝
	public String reject(){
		ConsultCheck consultCheck = new ConsultCheck();
		consultCheck.setAdmin_id(9527);
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
	
	// 咨询接受
	public String recieve(){
		Professor professor = (Professor) ServletActionContext.getServletContext().getAttribute("user");
		// TODO
		Consult consult = consultService.findById(model.getId());
		Project project = new Project();
		
		project.setCom_id(consult.getId());
		project.setScm_id(consult.getId());
		project.setTitle(consult.getTitle());
		project.setStart_date(new Date());
		project.setCurrent_state("3");
		project.setCost(consult.getBudget());
		// 决定方案后会插入一条 project 记录
		consultService.consultRecieve(project);
		System.out.println(professor + "接受项目。。。");
		return NONE;
	}
}
