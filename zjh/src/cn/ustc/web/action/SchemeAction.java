package cn.ustc.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import cn.ustc.domain.Company;
import cn.ustc.domain.Consult;
import cn.ustc.domain.Professor;
import cn.ustc.domain.Project;
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
	private final int PAGESIZE=4;
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
	@Autowired
	private ConsultService consultService;

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
		String cons_id = model.getCons_id();
		Consult consult = consultService.findById(cons_id);
		model.setConsultTitle(consult.getTitle());
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
		int count=0;
		//条件1查询的是scheme类
		DetachedCriteria criteria = DetachedCriteria.forClass(Scheme.class);
		ServletContext servletContext=null;
		if(obj instanceof Professor){
			Professor professor = (Professor)obj;
			count = schemeService.getCountByPorfessorID(professor.getId());
			criteria.add(Restrictions.eq("professor.id", professor.getId()));
		}
		if(obj instanceof Company){
			String prof_id=ServletActionContext.getRequest().getParameter("professor_id");;
			servletContext=ServletActionContext.getServletContext();
			servletContext.setAttribute("prof_id", prof_id);
			//条件2查询的条件是professor
			
			if(prof_id==null){
			prof_id=(String) servletContext.getAttribute("prof_id");
			}
			count = schemeService.getCountByPorfessorID(prof_id);
			criteria.add(Restrictions.eq("professor.id", prof_id));
			//schemes = schemeService.findMyScheme(professor);
		}
		
		model.setTotal(count);
		model.setPageCount((count-1)/PAGESIZE+1);
		
		int pageIndex = model.getPageIndex();
		if(pageIndex == 0){
			model.setPageIndex(1);
			//条件3，查询多少条scheme
			schemes = schemeService.findByDetachedCriteria(criteria, 0, PAGESIZE);
		}else{
			schemes = schemeService.findByDetachedCriteria(criteria, (pageIndex-1)*PAGESIZE, PAGESIZE);
		}
		return "queryMySchemeSUCCESS";
	} 
	
	/**
	 * 查看专家对咨询发布的方案
	 * @return
	 */
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
