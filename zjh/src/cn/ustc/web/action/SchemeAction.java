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
import cn.ustc.domain.Project;
import cn.ustc.domain.Scheme;
import cn.ustc.utils.UploadAndDownloadUtils;
import cn.ustc.web.dao.impl.SchemeDAO;
import cn.ustc.web.service.ConsultService;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

public class SchemeAction extends ActionSupport implements ModelDriven<Scheme> {
	private Scheme model = new Scheme();
	@Override
	public Scheme getModel() {
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
	private SchemeDAO schemeDAO;
	public void setConsultService(ConsultService consultService) {
		this.consultService = consultService;
	}
	public void setSchemeDAO(SchemeDAO schemeDAO) {
		this.schemeDAO = schemeDAO;
	}
	/*************************************发布上传下载****************************************/
	
	public String submit(){
		return "submitView";
	}
	
	// 方案发布
	@InputConfig(resultName="input")
	public String publish(){
		if(file != null){
			String fileRootPath = ServletActionContext.getServletContext().getRealPath("/document");
			String filePath = consultService.restoreFile(file, fileRootPath);
			model.setFilePath(filePath);
			model.setFileName(fileFileName);
		}
		model.setUpload_date(new Date());
//		model.setProf_id(9527);
		
		schemeDAO.publish(model);
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

	// 获取下载输出流
	public InputStream getInputStream() throws FileNotFoundException {
		if (model == null || model.getFilePath() == null) {
			return null;
		}
		File file = new File(model.getFilePath());
		// FileInputStream fis = new FileInputStream(file);
		try {
			FileInputStream fis = new FileInputStream(file);
			return fis;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	// 下载文档
	public String download() {
		// model = consultService.findById(model.getId());
		return "downSUCCESS";
	}
	
	/*************************************显示*****************************************/
		
	private List<Scheme> schemes;
	public List<Scheme> getSchemes() {
		return schemes;
	}
	
	// 显示全部
	public String list(){
		// TODO
		schemes = schemeDAO.findAll();
		return "listSUCCESS";
	}
	
	
}
