package cn.ustc.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import sun.misc.BASE64Encoder;
import cn.ustc.domain.Consult;
import cn.ustc.web.service.ConsultService;

import com.opensymphony.xwork2.ActionContext;
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
		String filePath = ServletActionContext.getServletContext().getRealPath("/document") + "/" + fileFileName;
//		System.out.println(model);
		service.restoreFile(file, filePath);
		model.setFilePath(filePath);
		model.setState("0");
		model.setRelease_date(new Date());
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
			String fileName = checkFileName(model.getFilePath());
			return ServletActionContext.getServletContext().getMimeType(fileName);
		}
	}
	
	// <param name="contentDisposition">attachment;filename=${downloadFileName}</param>
	public String getDownloadFileName() throws IOException{
		if(model == null || model.getFilePath() == null){
			return null;
		}else{
			String fileName = checkFileName(model.getFilePath());
			return encodeDownloadFilename(fileName, ServletActionContext.getRequest().getHeader("user-agent"));
		}
	}
	
	private String checkFileName(String filePath) {
		int begin = filePath.indexOf("/");
//		int end = filePath.lastIndexOf(".");
//		return filePath.substring(begin, end);
		return filePath.substring(begin+1);
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
	
	/**
	 * 下载文件时，针对不同浏览器，进行附件名的编码
	 * @param filename 下载文件名
	 * @param agent 客户端浏览器
	 * @return 编码后的下载附件名
	 * @throws IOException
	 */
	public String encodeDownloadFilename(String filename, String agent)
			throws IOException {
		if (agent.contains("Firefox")) { // 火狐浏览器
			filename = "=?UTF-8?B?"
					+ new BASE64Encoder().encode(filename.getBytes("utf-8"))
					+ "?=";
		} else { // IE及其他浏览器
			filename = URLEncoder.encode(filename, "utf-8");
		}
		return filename;
	}
}
