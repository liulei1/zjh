package cn.ustc.web.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import cn.ustc.domain.Evaluate;
import cn.ustc.domain.Project;
import cn.ustc.web.dao.impl.EvaluateDAO;

@Transactional
public class EvaluateService {
	private EvaluateDAO evaluateDAO;
	public void setEvaluateDAO(EvaluateDAO evaluateDAO) {
		this.evaluateDAO = evaluateDAO;
	}
	
	public Evaluate findById(String id){
		return evaluateDAO.findById(id);
	}
	
	public Evaluate findByProjId(int id){
		DetachedCriteria criteria = DetachedCriteria.forClass(Evaluate.class);
		criteria.add(Restrictions.eq("proj_id", id));
		List<Evaluate> list = this.evaluateDAO.findByDetachedCriteria(criteria);
		return list!=null?list.get(0):null;
	}
	
	public List<Evaluate> findByDetachedCriteria(DetachedCriteria criteria){
		return evaluateDAO.findByDetachedCriteria(criteria);
	}

	public void update(Evaluate evaluate) {
		evaluateDAO.update(evaluate);		
	}
}
