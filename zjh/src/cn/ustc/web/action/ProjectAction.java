package cn.ustc.web.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import cn.ustc.domain.Company;
import cn.ustc.domain.Professor;
import cn.ustc.domain.Project;
import cn.ustc.web.service.ProjectService;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class ProjectAction extends ActionSupport implements ModelDriven<Project> {
	private Project project = new Project();
	@Override
	public Project getModel() {
		return project;
	}
	
	private List<Project> projects;
	public List<Project> getProjects() {
		return projects;
	}
	
	/******************************* 注入 ********************************/
	private ProjectService projectService;
	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}

	/********************************* 项目操作 ************************************/
	public String queryMyProject(){
		Object o = ServletActionContext.getServletContext().getAttribute("user");
		if(o instanceof Company){
			Company company =(Company) o;
			DetachedCriteria criteria = DetachedCriteria.forClass(Project.class);
			criteria.add(Restrictions.eq("com_id", company.getId()));
			projects = projectService.findByDetachedCriteria(criteria);
		}else if(o instanceof Professor){
			Professor professor =(Professor) o;
			DetachedCriteria criteria = DetachedCriteria.forClass(Project.class);
			criteria.add(Restrictions.eq("prof_id", professor.getId()));
			projects = projectService.findByDetachedCriteria(criteria);
		}
		return "queryMyProjectSUCCESS";
	}
	
	public String complete(){
		projectService.projectComplete(project);
		return "completeSUCCESS";
	}
}
