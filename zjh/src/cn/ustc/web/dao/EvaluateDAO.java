package cn.ustc.web.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.ustc.domain.Evaluate;

/**
 * 评价操作DAO
 * @author liu
 *
 */
@SuppressWarnings("unchecked")
public class EvaluateDAO extends HibernateDaoSupport{
	/**
	 * 插入
	 * @param project
	 */
	public void insert(Evaluate evaluate) {
		this.getHibernateTemplate().save(evaluate);
	}
	
	/**
	 * 条件查询
	 * @param criteria
	 * @return
	 */
	public List<Evaluate> findByDetachedCriteria(DetachedCriteria criteria){
		return this.getHibernateTemplate().findByCriteria(criteria);
	}

	/**
	 * 根据id查找评价
	 * @param id
	 * @return
	 */
	public Evaluate findById(String id) {
		return this.getHibernateTemplate().get(Evaluate.class, id);
	}

	/**
	 * 更新评价信息
	 * @param evaluate
	 */
	public void update(Evaluate evaluate) {
		this.getHibernateTemplate().update(evaluate);
	}

}
