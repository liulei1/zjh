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
import cn.ustc.utils.UploadAndDownloadUtils;
import cn.ustc.web.service.ConsultService;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class ConsultAction extends ActionSupport implements ModelDriven<Consult> {
	private Consult model = new Consult();
	@Override
	public Consult getModel() {
		return model;
	}

	private File file;
	private String fileFileName;
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
	
	ConsultService service = new ConsultService();
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
	
	private List<Consult> consults;
	public List<Consult> getConsults() {
		return consults;
	}
	public String list(){
		List<Consult> list = service.conlistList();
		consults = list;
		System.out.println(list);
		return "listSUCCESS";
	}
	
	public String view(){
		Consult consult = service.findById(model.getId());
		model = consult;
		return "viewSUCCESS";
	}
	
	// <param name="contentType">${contentType}</param>
	public String getContentType(){
		if(model == null || model.getFilePath() == null){
			return null;
		}else{
			String fileName = model.getFileName();
			return ServletActionContext.getServletContext().getMimeType(fileName);
		}
	}
	
	// <param name="contentDisposition">attachment;filename=${downloadFileName}</param>
	public String getDownloadFileName() throws IOException{
		if(model == null || model.getFilePath() == null){
			return null;
		}else{
			String fileName = model.getFileName();
			return UploadAndDownloadUtils.encodeDownloadFilename(fileName, ServletActionContext.getRequest().getHeader("user-agent"));
		}
	}
	
	public InputStream getInputStream() throws FileNotFoundException{
		if(model == null || model.getFilePath() == null) {
			return null;
		}
		File file = new File(model.getFilePath());
		FileInputStream fis = new FileInputStream(file);
		return fis;
	}
	
	public String download(){
		model = service.findById(model.getId());
		return "downSUCCESS";
	}
	
	public String allow(){
		boolean res = service.consultAllow(model.getId());
		if(res){
			return "checkSUCCESS";
		}else {
			this.addActionError("审核失败！");
			return "checkFAIL";
		}
	}
	public String reject(){
		boolean res = service.consultReject(model.getId());
		if(res){
			return "checkSUCCESS";
		}else {
			this.addActionError("审核失败！");
			return "checkFAIL";
		}
	}
	
}
