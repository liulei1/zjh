package cn.ustc.web.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.ustc.domain.Scheme;

/**
 * 方案DAO
 * @author liu
 *
 */
public class SchemeDAO extends HibernateDaoSupport {
	/**
	 * 发布解决方案
	 * @param scheme
	 */
	public void add(Scheme scheme){
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
	@SuppressWarnings("unchecked")
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
}
