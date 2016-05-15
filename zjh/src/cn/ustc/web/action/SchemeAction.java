package cn.ustc.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import cn.ustc.domain.Company;
import cn.ustc.domain.Professor;
import cn.ustc.domain.Scheme;
import cn.ustc.utils.DateUtils;
import cn.ustc.utils.GetPropertiesUtil;
import cn.ustc.utils.UploadAndDownloadUtils;
import cn.ustc.web.dao.ProfessorDAO;
import cn.ustc.web.dao.SchemeDAO;
import cn.ustc.web.service.ConsultService;
import cn.ustc.web.service.ProfessorService;
import cn.ustc.web.service.SchemeService;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

public class SchemeAction extends ActionSupport implements ModelDriven<Scheme> {
	
	@Autowired
	private ProfessorService professorService;
	
	private static final long serialVersionUID = 1L;
	private Scheme model = new Scheme();
	@Override
	public Scheme getModel() {
		return model;
	}

	/**************************** 文件上传 ****************************/
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

	/******************************* 注入 *********************************/
	@Autowired
	private SchemeService schemeService;

	/************************************* 发布上传下载 ****************************************/
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
	
	// 下载文档
	public String download() {
		// model 需要最获取 下载输出流时使用
		model = schemeService.findById(model.getId());
		return "downSUCCESS";
	}

	/************************************* 操作  *****************************************/
	private List<Scheme> schemes;
	public List<Scheme> getSchemes() {
		return schemes;
	}
	
	// 跳转到方案发布页面
	public String submitView() {
		return "submitViewSUCCESS";
	}

	// 方案发布
	@InputConfig(resultName = "input")
	public String publish() {
		if (file != null) {
			Properties properties = GetPropertiesUtil.getProperties();
			String fileRootPath = properties.getProperty("schemeFileRootPath");
			String filePath = UploadAndDownloadUtils.restoreFile(file, fileRootPath);
			model.setFilePath(filePath);
			model.setFileName(fileFileName);
		}
		
		model.setUpload_date(DateUtils.dateToString(new Date()));
		// 在servlet域中获取专家信息并放入model中
		Professor professor = (Professor)ServletActionContext.getServletContext().getAttribute("user");
		model.setProfessor(professor);
		
		DetachedCriteria criteria = DetachedCriteria.forClass(Scheme.class);
		criteria.add(Restrictions.eq("cons_id", model.getCons_id()));
		criteria.add(Restrictions.eq("professor", model.getProfessor()));
		
		// 不允许对一个项目发布多个方案，只保存最新的方案
		List<Scheme> list = schemeService.findByDetachedCriteria(criteria);
		if(list.size() > 0){
			for(int i=0; i<list.size(); i++){
				Scheme scheme = list.get(i);
				// 删除之前上传的文件
				if(scheme.getFilePath() != null){
					File f = new File(scheme.getFilePath());
					if(f != null){
						f.delete();
					}
				}
				schemeService.delete(scheme);
			}
		}
		
		schemeService.publish(model);
		return "publishSUCCESS";
	}
	
	// 查找登录专家发布的所有方案
	public String queryMyScheme(){
		Object obj=ServletActionContext.getServletContext().getAttribute("user");
		if(obj instanceof Professor){
			Professor professor = (Professor)obj;
			schemes = schemeService.findMyScheme(professor);
		}
		if(obj instanceof Company){
			String id = model.getProfessor().getId();
			System.out.println("-------------------------"+id);
			if(professorService==null){
				System.out.println("++++++++++"+"空");
			}
			Professor professor=professorService.findProfessorById(id);
			System.out.println(professor);
			schemes = schemeService.findMyScheme(professor);
		}
		return "queryMySchemeSUCCESS";
	} 
	
	public String findConsultSchemes(){
		DetachedCriteria criteria = DetachedCriteria.forClass(Scheme.class);
		criteria.add(Restrictions.eq("cons_id", model.getCons_id()));
		schemes = schemeService.findByDetachedCriteria(criteria);
		return "findConsultSchemesSUCCESS";
	}
	
	public String findProjectScheme(){
		model = schemeService.findById(model.getId());
		return "findProjectSchemeSUCCESS";
	}
	
	// 显示全部
	public String list() {
		schemes = schemeService.findAll();
		return "listSUCCESS";
	}
}
