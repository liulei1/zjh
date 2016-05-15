package cn.ustc.web.action;

import java.util.List;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import cn.ustc.domain.Company;
import cn.ustc.domain.Professor;
import cn.ustc.domain.Project;
import cn.ustc.web.service.ProfessorService;
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
	@Autowired
	private ProjectService projectService;

	@Autowired
	private ProfessorService professorService;
	/********************************* 项目操作 ************************************/
	public String queryMyProject(){
		Object o = ServletActionContext.getServletContext().getAttribute("user");
		if(o instanceof Company){
			Company company =(Company) o;
			DetachedCriteria criteria = DetachedCriteria.forClass(Project.class);
			String pro_id=ServletActionContext.getRequest().getParameter("professor_id");
			//前台传入专家的id,拿着id查找项目
			if(pro_id!=null){
			criteria.add(Restrictions.eq("prof_id", pro_id));
			projects = projectService.findByDetachedCriteria(criteria);
			}else{
				criteria.add(Restrictions.eq("com_id", company.getId()));
				projects = projectService.findByDetachedCriteria(criteria);
			}
			
		}else if(o instanceof Professor){
			Professor professor =(Professor) o;
			DetachedCriteria criteria = DetachedCriteria.forClass(Project.class);
			//前台传入company的id，拿着id找项目
			String com_id=ServletActionContext.getRequest().getParameter("company_id");
			if(com_id!=null){
				criteria.add(Restrictions.eq("com_id", com_id));
				projects=projectService.findByDetachedCriteria(criteria);
			}else{
				criteria.add(Restrictions.eq("prof_id", professor.getId()));
				projects = projectService.findByDetachedCriteria(criteria);
			}
		}
		return "queryMyProjectSUCCESS";
	}
	
	public String complete(){
		projectService.projectComplete(project);
		return "completeSUCCESS";
	}
}
