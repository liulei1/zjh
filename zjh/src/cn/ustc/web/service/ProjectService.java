package cn.ustc.web.service;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import cn.ustc.domain.Consult;
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
	
	public Project findById(String id){
		return projectDAO.findById(id);
	}

	/**
	 * 完成项目
	 * @param project
	 */
	public void projectComplete(Project project) {
		Project p = projectDAO.findById(project.getId());
		p.setCurrent_state(Project.EVALUATE);
		p.setEnd_date(DateUtils.dateToString(new Date()));
		projectDAO.update(p);
		
		Evaluate evaluate = new Evaluate();
		evaluate.setBegin_date(DateUtils.dateToString(new Date()));
		evaluate.setCom_id(p.getConsult().getCom_id());
		evaluate.setProj_id(p.getId());
		evaluate.setProf_id(p.getProf_id());
		evaluate.setProf_state(Evaluate.UNCOMPLETED);
		evaluate.setCom_state(Evaluate.UNCOMPLETED);
		evaluateDAO.insert(evaluate);
	}
	
	public void update(Project project){
		this.projectDAO.update(project);
	}

	public int getCountByPorfessorID(String pro_id) {
		// TODO Auto-generated method stub
		return projectDAO.getCountByPorfessorID(pro_id);
	}
	
	public int getCountByCompanyID(String com_id) {
		// TODO Auto-generated method stub
		return projectDAO.getCountByCompanyID(com_id);
	}
	
	public List<Project> findConsultsByDetachedCriteria(DetachedCriteria criteria){
		return projectDAO.findByDetachedCriteria(criteria);
	}
	
	public List<Project> findByDetachedCriteria(DetachedCriteria criteria, int firstResult, int maxResults){
		return projectDAO.findByDetachedCriteria(criteria, firstResult, maxResults);
	}
}
