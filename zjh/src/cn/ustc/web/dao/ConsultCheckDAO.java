package cn.ustc.web.dao;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.ustc.domain.ConsultCheck;
/**
 * 咨询审核DAO
 * @author liu
 *
 */
public class ConsultCheckDAO extends HibernateDaoSupport {

	/**
	 * 插入审核记录
	 * @param consultCheck
	 */
	public void insert(ConsultCheck consultCheck) {
		this.getHibernateTemplate().save(consultCheck);
	}

}
