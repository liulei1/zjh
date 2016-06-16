package cn.ustc.web.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.ustc.domain.Scheme;

/**
 * 方案DAO
 * @author liu
 *
 */
@SuppressWarnings("unchecked")
public class SchemeDAO extends HibernateDaoSupport {
	/**
	 * 发布解决方案
	 * @param scheme
	 */
	public void add(Scheme scheme){
		/*
		 * nested exception is org.hibernate.NonUniqueObjectException: 
		 * a different object with the same identifier value 
		 * was already associated with the session
		 */
		scheme = (Scheme) this.getSession().merge(scheme);
		this.getHibernateTemplate().save(scheme);
	}
	
	/**
	 * 删除
	 * @param scheme
	 */
	public void delete(Scheme scheme){
		this.getHibernateTemplate().delete(scheme);
	}
	
	/**
	 * 所有解决方案
	 * @return
	 */
	public List<Scheme> findAll(){
		return this.getHibernateTemplate().find("from Scheme");
	}
	
	/**
	 * 条件查询
	 * @param criteria
	 * @return
	 */
	public List<Scheme> findByDetachedCriteria(DetachedCriteria criteria){
		return this.getHibernateTemplate().findByCriteria(criteria);
	}
	/**
	 * 根据id 获取方案
	 * @param id
	 * @return
	 */
	public Scheme findById(String id) {
		return this.getHibernateTemplate().get(Scheme.class, id);
	}

	/**
	 * 分页条件查询
	 * @param criteria
	 * @param page 首记录下标
	 * @param pageSize 最大记录数
	 * @return
	 */
	public List<Scheme> findByDetachedCriteriaPage(DetachedCriteria criteria,
			int page, int pageSize) {
		return this.getHibernateTemplate().findByCriteria(criteria,page,pageSize);
	
	}
	
	/**
	 * 通过主键id获取发布的方案数
	 * @param id
	 * @return
	 */
	public int getCountByPorfessorID(String id) {
//		String hql = "select count(*) from Scheme as scheme";
		DetachedCriteria criteria = DetachedCriteria.forClass(Scheme.class);
		criteria.add(Restrictions.eq("professor.id", id));
		List findByCriteria = this.getHibernateTemplate().findByCriteria(criteria);
		//Long count = (Long) this.getHibernateTemplate().find(hql, id).listIterator().next();
		Long count=(long) findByCriteria.size();
		return count.intValue();
	}
}
