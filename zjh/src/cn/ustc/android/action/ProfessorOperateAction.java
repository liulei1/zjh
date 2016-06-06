package cn.ustc.android.action;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import cn.ustc.domain.Professor;
import cn.ustc.domain.Project;
import cn.ustc.web.service.ConsultService;
import cn.ustc.web.service.ProjectService;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class ProfessorOperateAction extends ActionSupport implements ModelDriven<Professor>{
	private Professor model = new Professor();
	@Override
	public Professor getModel() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Autowired
	private ConsultService consultService;
	@Autowired
	private ProjectService projectService;
	
	private List<Project> projects = null;
	public List<Project> getProjects() {
		return projects;
	}


	public String getProfessorDoingProject(){
//		String professorId = model.getId();
		String professorId = "12312";
		DetachedCriteria criteria = DetachedCriteria.forClass(Project.class);
		criteria.add(Restrictions.eq("prof_id", professorId));
		criteria.add(Restrictions.eq("current_state", Project.ONGOING));
		projects = projectService.findByDetachedCriteria(criteria);
		return SUCCESS;
	}
}
