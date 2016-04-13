package cn.ustc.web.service;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import cn.ustc.domain.Evaluate;
import cn.ustc.domain.Project;
import cn.ustc.utils.DateUtils;
import cn.ustc.web.dao.EvaluateDAO;
import cn.ustc.web.dao.ProjectDAO;

@Transactional
public class ProjectService {
	@Autowired
	private ProjectDAO projectDAO;
	@Autowired
	private EvaluateDAO evaluateDAO;

	public List<Project> findByDetachedCriteria(DetachedCriteria criteria){
		return this.projectDAO.findByDetachedCriteria(criteria);
	}
	
	public Project findById(int id){
		return projectDAO.findById(id);
	}

	/**
	 * 完成项目
	 * @param project
	 */
	public void projectComplete(Project project) {
		Project p = projectDAO.findById(project.getId());
		p.setCurrent_state(Project.COMPANYEVALUATE);
		p.setEnd_date(new Date());
		projectDAO.update(p);
		
		Evaluate evaluate = new Evaluate();
		evaluate.setBegin_date(DateUtils.dateToString(new Date()));
		evaluate.setCom_id(p.getConsult().getCom_id());
		evaluate.setProj_id(p.getId());
		evaluate.setProf_id(p.getProf_id());
		evaluateDAO.insert(evaluate);
	}
	
	public void update(Project project){
		this.projectDAO.update(project);
	}
}
