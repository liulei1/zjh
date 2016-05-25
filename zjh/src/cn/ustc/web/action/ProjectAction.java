package cn.ustc.web.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import cn.ustc.domain.Company;
import cn.ustc.domain.Consult;
import cn.ustc.domain.Professor;
import cn.ustc.domain.Project;
import cn.ustc.web.service.ProfessorService;
import cn.ustc.web.service.ProjectService;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class ProjectAction extends ActionSupport implements ModelDriven<Project> {
	private Project project = new Project();
	private static final int PAGESIZE=4;
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
		int count=0;
		DetachedCriteria criteria = DetachedCriteria.forClass(Project.class);
		Object o = ServletActionContext.getServletContext().getAttribute("user");
		if(o instanceof Company){
			Company company =(Company) o;
			String prof_id=ServletActionContext.getRequest().getParameter("professor_id");
			//前台传入专家的id,拿着id查找项目
			if(prof_id!=null){
				count = projectService.getCountByPorfessorID(prof_id);
				criteria.add(Restrictions.eq("prof_id", prof_id));
			}else{
				count = projectService.getCountByCompanyID(company.getId());
				criteria.add(Restrictions.eq("com_id", company.getId()));
			}
			
		}else if(o instanceof Professor){
			Professor professor =(Professor) o;
			//前台传入company的id，拿着id找项目
			String com_id=ServletActionContext.getRequest().getParameter("company_id");
			if(com_id!=null){
				count = projectService.getCountByCompanyID(com_id);
				criteria.add(Restrictions.eq("com_id", com_id));
			}else{
				count = projectService.getCountByPorfessorID(professor.getId());
				criteria.add(Restrictions.eq("prof_id", professor.getId()));
			}
		}
		
		project.setTotal(count);
		project.setPageCount((count-1)/PAGESIZE+1);
		
		int pageIndex = project.getPageIndex();
		if(pageIndex == 0){
			project.setPageIndex(1);
			projects = projectService.findByDetachedCriteria(criteria, 0, PAGESIZE);
		}else{
			projects = projectService.findByDetachedCriteria(criteria, (pageIndex-1)*PAGESIZE, PAGESIZE);
		}
		return "queryMyProjectSUCCESS";
	}
	
	public String complete(){
		projectService.projectComplete(project);
		return "completeSUCCESS";
	}
}
