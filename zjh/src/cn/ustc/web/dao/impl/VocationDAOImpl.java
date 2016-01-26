package cn.ustc.web.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.ustc.domain.Vocation;
import cn.ustc.utils.HibernateUtils;
import cn.ustc.web.dao.VocationDAO;

public class VocationDAOImpl extends HibernateDaoSupport implements VocationDAO {

	@Override
	public List<Vocation> listVocation() {
		Session session = HibernateUtils.openSession();
//		Session session = HibernateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		
		String hql = "from Vocation";
		List<Vocation> list = session.createQuery(hql).list();
		
		transaction.commit();
		session.close();
		return list;
	}

}
