package cn.ustc.android.action;

import org.springframework.beans.factory.annotation.Autowired;

import cn.ustc.domain.Company;
import cn.ustc.domain.Professor;
import cn.ustc.domain.Project;
import cn.ustc.domain.Scheme;
import cn.ustc.web.service.CompanyService;
import cn.ustc.web.service.ProfessorService;
import cn.ustc.web.service.ProjectService;
import cn.ustc.web.service.SchemeService;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 项目处理类
 * @author liu
 *
 */
public class ProjectAndriodAction extends ActionSupport implements ModelDriven<Project> {
	private Project model = new Project();
	@Autowired
	private ProjectService projectService;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private SchemeService schemeService;
	@Autowired
	private ProfessorService professorService;
	
	/**
	 * 获取项目的信息，项目的部分信息已做显示调整
	 * 调用条件：传入项目的id
	 * @return
	 */
	public String getProjectDetails(){
		String id = "5";
		model = projectService.findById(id);
		
		Company company = companyService.findCompanyById(model.getCom_id());
		model.setCom_id(company.getName());
		
		Scheme scheme = schemeService.findById(model.getScm_id());
		model.setScm_id(scheme.getTitle());
		
		Professor professor = professorService.findProfessorById(model.getProf_id());
		model.setProf_id(professor.getName());
		
		if(Project.ONGOING.equals(model.getCurrent_state())){
			model.setCurrent_state("进行中");
		}else if(Project.COMPELETED.equals(model.getCurrent_state())){
			model.setCurrent_state("已完成");
		}else if(Project.EVALUATE.equals(model.getCurrent_state())){
			model.setCurrent_state("评价中");
		}
		return SUCCESS;
	}

	
	/****************************成员变量set，get方法*********************************/
	@Override
	public Project getModel() {
		return model;
	}
}
