package cn.ustc.web.dao;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.ustc.domain.ConsultCheck;
/**
 * 咨询审核
 * @author liu
 *
 */
public class ConsultCheckDAO extends HibernateDaoSupport {

	public void insert(ConsultCheck consultCheck) {
		this.getHibernateTemplate().save(consultCheck);
	}

}
