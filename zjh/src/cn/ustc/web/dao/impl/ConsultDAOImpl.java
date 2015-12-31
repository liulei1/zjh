package cn.ustc.web.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import cn.ustc.domain.Consult;
import cn.ustc.utils.HibernateUtils;
import cn.ustc.web.dao.ConsultDAO;
import cn.ustc.web.service.ConsultService;

public class ConsultDAOImpl implements ConsultDAO {

	@Override
	public int insert(Consult consult) {
//		Session session = HibernateUtils.getCurrentSession();
		Session session = HibernateUtils.openSession();
		Transaction transaction = session.beginTransaction();
		session.save(consult);
		transaction.commit();
		session.close();
		return 1;
	}

	@Override
	public List<Consult> findAll() {
		Session session = HibernateUtils.openSession();
		Transaction transaction = session.beginTransaction();
		
		String hql = "from Consult";
		@SuppressWarnings("unchecked")
		List<Consult> list = session.createQuery(hql).list();
		
		transaction.commit();
		session.close();
		
		return list;
	}

	@Override
	public List<Consult> findUncheckConsult() {
		Session session = HibernateUtils.openSession();
		Transaction transaction = session.beginTransaction();
		
		@SuppressWarnings("unchecked")
		List<Consult> list = session.createCriteria(Consult.class).add(Restrictions.eq("state", ConsultService.UNCHECK)).list();
		
		transaction.commit();
		session.close();
		
		return list;
	}
	
	@Override
	public List<Consult> findAllowConsult() {
		Session session = HibernateUtils.openSession();
		Transaction transaction = session.beginTransaction();
		
		String hql = "from Consult where state = ?";
		@SuppressWarnings("unchecked")
		List<Consult> list = session.createQuery(hql).setParameter(0, ConsultService.ALLOW).list();
		
		transaction.commit();
		session.close();
		
		return list;
	}
	
	@Override
	public Consult findById(Integer id) {
		Session session = HibernateUtils.openSession();
		Transaction transaction = session.beginTransaction();
		
		Consult uniqueResult = (Consult) session.createCriteria(Consult.class).add(Restrictions.idEq(id)).uniqueResult();
		
		transaction.commit();
		session.close();
		
		return uniqueResult;
	}

	@Override
	public int check(Integer id, String state) {
		Session session = HibernateUtils.openSession();
		Transaction transaction = session.beginTransaction();
		
		String hql = "update Consult set state = ? where id = ?";
		Query query = session.createQuery(hql).setParameter(0, state).setParameter(1, id);
		int res = query.executeUpdate();
		
		transaction.commit();
		session.close();
		
		return res;
	}


}
