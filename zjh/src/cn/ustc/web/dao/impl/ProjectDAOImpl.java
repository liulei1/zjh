package cn.ustc.web.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.ustc.domain.Project;

public class ProjectDAOImpl extends HibernateDaoSupport{

	/**
	 * 插入一个项目
	 * @param project
	 */
	public void insert(Project project) {
		this.getHibernateTemplate().save(project);
	}
	
	/**
	 * 条件查询
	 * @param criteria
	 * @return
	 */
	public List<Project> findByDetachedCriteria(DetachedCriteria criteria){
		return this.getHibernateTemplate().findByCriteria(criteria);
	}

	/**
	 * 更新
	 * @param project
	 */
	public void update(Project project){
		this.getHibernateTemplate().update(project);
	}
	
	public Project findById(int id){
		return this.getHibernateTemplate().get(Project.class, id);
	}
}
