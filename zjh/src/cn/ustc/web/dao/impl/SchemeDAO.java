package cn.ustc.web.dao.impl;

import java.util.List;

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
	public void publish(Scheme scheme){
		this.getHibernateTemplate().save(scheme);
	}
	
	public List<Scheme> findAll(){
		return this.getHibernateTemplate().find("from Scheme");
	}
}
