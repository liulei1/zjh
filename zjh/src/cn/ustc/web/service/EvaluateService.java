package cn.ustc.web.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import cn.ustc.domain.Evaluate;
import cn.ustc.domain.Project;
import cn.ustc.web.dao.EvaluateDAO;

/**
 * 评价service
 * @author liu
 *
 */
@Transactional
public class EvaluateService {
	@Autowired
	private EvaluateDAO evaluateDAO;
	
	public Evaluate findById(String id){
		return evaluateDAO.findById(id);
	}
	/**
	 * 根据项目id查找评价
	 * @param id
	 * @return
	 */
	public Evaluate findByProjId(String id){
		DetachedCriteria criteria = DetachedCriteria.forClass(Evaluate.class);
		criteria.add(Restrictions.eq("proj_id", id));
		List<Evaluate> list = this.evaluateDAO.findByDetachedCriteria(criteria);
		return list!=null?list.get(0):null;
	}
	/**
	 * 条件查找评价
	 * @param criteria
	 * @return
	 */
	public List<Evaluate> findByDetachedCriteria(DetachedCriteria criteria){
		return evaluateDAO.findByDetachedCriteria(criteria);
	}

	/**
	 * 更新评价信息
	 * @param evaluate
	 */
	public void update(Evaluate evaluate) {
		evaluateDAO.update(evaluate);		
	}

	/**
	 * 根据id和professorId查找Evaluate
	 * @param id
	 * @param professorId
	 * @return
	 */
	public Evaluate findEvaluateByIdAndProfessorId(String id, String professorId) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Evaluate.class);
		criteria.add(Restrictions.eq("id", id));
		criteria.add(Restrictions.eq("prof_id", professorId));
		criteria.add(Restrictions.eq("prof_state", Evaluate.UNCOMPLETED));
		List<Evaluate> list = evaluateDAO.findByDetachedCriteria(criteria);
		return list.size()>0?list.get(0):null;
	}

	/**
	 * 根据项目id查找未完成的评价
	 * @param proj_id
	 * @return
	 */
	public Evaluate findEvaluateByProjectId(String proj_id) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Evaluate.class);
		criteria.add(Restrictions.eq("proj_id", proj_id));
		criteria.add(Restrictions.eq("prof_state", Evaluate.UNCOMPLETED));
		List<Evaluate> list = evaluateDAO.findByDetachedCriteria(criteria);
		return list.size()>0?list.get(0):null;
	}
}
