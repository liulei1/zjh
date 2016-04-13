package cn.ustc.web.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.ustc.domain.Vocation;
import cn.ustc.utils.HibernateUtils;

public class VocationDAO extends HibernateDaoSupport {

	@SuppressWarnings("unchecked")
	public List<Vocation> listVocation() {
		String hql = "from Vocation";
		List<Vocation> list = this.getHibernateTemplate().find(hql);
		return list;
	}

}
