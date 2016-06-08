package cn.ustc.web.service;

import java.awt.image.RescaleOp;
import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
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

	/**
	 * 通过id查询项目
	 * @param id
	 * @return
	 */
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
		return projectDAO.getCountByPorfessorID(pro_id);
	}
	
	public int getCountByCompanyID(String com_id) {
		return projectDAO.getCountByCompanyID(com_id);
	}
	
	/**
	 * 条件查询项目
	 * @param criteria
	 * @return
	 */
	public List<Project> findProjectsByDetachedCriteria(DetachedCriteria criteria){
		return projectDAO.findByDetachedCriteria(criteria);
	}
	
	/**
	 * 条件查询项目
	 * @param criteria 条件
	 * @param firstResult 首记录下标
	 * @param maxResults 最大记录数
	 * @return
	 */
	public List<Project> findByDetachedCriteria(DetachedCriteria criteria, int firstResult, int maxResults){
		return projectDAO.findByDetachedCriteria(criteria, firstResult, maxResults);
	}

	/**
	 * 查询专家在评价的项目
	 * @param professorId 专家Id
	 * @return
	 */
	public List<Project> findProfessorEvaluateProject(String professorId) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Project.class);
		criteria.add(Restrictions.eq("prof_id", professorId));
		criteria.add(Restrictions.eq("current_state", Project.EVALUATE));
		return projectDAO.findByDetachedCriteria(criteria);
	}
}
