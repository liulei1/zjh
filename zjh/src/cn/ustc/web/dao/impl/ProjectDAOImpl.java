package cn.ustc.web.dao.impl;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.ustc.domain.Project;
import cn.ustc.web.dao.ProjectDAO;

public class ProjectDAOImpl extends HibernateDaoSupport implements ProjectDAO {

	@Override
	public void insert(Project project) {
		this.getHibernateTemplate().save(project);
	}

}
