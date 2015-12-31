package cn.ustc.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import cn.ustc.domain.Consult;
import cn.ustc.domain.ConsultCheck;
import cn.ustc.domain.Professor;
import cn.ustc.utils.UploadAndDownloadUtils;
import cn.ustc.web.service.ConsultService;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

public class ConsultAction extends ActionSupport implements ModelDriven<Consult> {
	private Consult model = new Consult();
	private File file;
	private String fileFileName;
	private String fileContentType;
	@Override
	public Consult getModel() {
		return model;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}
	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}
	ConsultService service = new ConsultService();
	
	/*************************************发布上传下载****************************************/
	// 咨询发布
	@InputConfig(resultName="input")
	public String publish(){
		String fileRootPath = ServletActionContext.getServletContext().getRealPath("/document");
		String filePath = service.restoreFile(file, fileRootPath);
		model.setFilePath(filePath);
		model.setState("0");
		model.setRelease_date(new Date());
		model.setFileName(fileFileName);
		boolean res = service.Publish(model);
		if(res){
			return "publishSUCCESS";
		}
		return NONE;
	}
	
	// 获取下载文件 类型
		// <param name="contentType">${contentType}</param>
		public String getContentType(){
			if(model == null || model.getFilePath() == null){
				return null;
			}else{
				String fileName = model.getFileName();
				return ServletActionContext.getServletContext().getMimeType(fileName);
			}
		}
		
		// 获取下载文件名
		// <param name="contentDisposition">attachment;filename=${downloadFileName}</param>
		public String getDownloadFileName() throws IOException{
			if(model == null || model.getFilePath() == null){
				return null;
			}else{
				String fileName = model.getFileName();
				return UploadAndDownloadUtils.encodeDownloadFilename(fileName, ServletActionContext.getRequest().getHeader("user-agent"));
			}
		}
		
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
			model = service.findById(model.getId());
			return "downSUCCESS";
		}
	
	/*************************************显示*****************************************/
		
	private List<Consult> consults;
	public List<Consult> getConsults() {
		return consults;
	}
	
	// 显示全部
	public String list(){
		List<Consult> list = service.consultList();
		consults = list;
		System.out.println(consults);
		return "listSUCCESS";
	}
	
	// 显示未审核
	public String unCheckList(){
		List<Consult> list = service.unCheckConsultList();
		consults = list;
		System.out.println(consults);
		return "listSUCCESS";
	}
	
	// 显示通过
	public String allowList(){
		List<Consult> list = service.allowConsultList();
		consults = list;
		System.out.println(consults);
		return "allowListSUCCESS";
	}
	
	// 根据查看咨询内容
	public String view(){
		Consult consult = service.findById(model.getId());
		model = consult;
		return "viewSUCCESS";
	}
	
	/****************************************审核*******************************************/
	// 允许
	public String allow(){
		ConsultCheck consultCheck = new ConsultCheck();
		consultCheck.setAdmin_id(9527);
		consultCheck.setCheck_datetime(new Date());
		consultCheck.setCons_id(model.getId());
		
		boolean res = service.consultAllow(model.getId(),consultCheck);
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
		
		boolean res = service.consultReject(model.getId(),consultCheck);
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
		
		return NONE;
	}
	
}
