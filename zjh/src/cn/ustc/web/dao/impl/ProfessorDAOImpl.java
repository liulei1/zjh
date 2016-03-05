package cn.ustc.web.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.ustc.domain.Professor;
import cn.ustc.utils.HibernateUtils;

public class ProfessorDAOImpl extends HibernateDaoSupport{
	
	public Professor findProfessorByProfessorNameAndPwd(String Professorname, String password) {
		Session session = HibernateUtils.openSession();
		Transaction transaction = session.beginTransaction();

		try {
			String hql = "from Professor as Professor where Professor.name=? and Professor.password=?";
			Query qr = session.createQuery(hql);
			qr.setString(0, Professorname);
			qr.setString(1, password);

			Professor professor = (Professor) qr.uniqueResult();
			transaction.commit();
			return professor;

		} catch (RuntimeException e) {
			throw e;
		} finally {

			session.close();
		}
	}

	public int insertProfessor(Professor professor) {
		this.getHibernateTemplate().save(professor);
		return 1;
	}

	public Professor findByProfessorID(String id) {
		Session session = HibernateUtils.openSession();
		Transaction tx = session.beginTransaction();

		Professor Professor = null;
		try {
			Professor = (Professor) session.get(Professor.class, id);
			session.getTransaction().commit();

		} catch (RuntimeException e) {
			tx.rollback();
			throw e;
		} finally {
			session.close();
		}
		return Professor;
	}

	public List<Professor> findAll() {

		Session session = HibernateUtils.openSession();
		Transaction tx = session.beginTransaction();

		List<Professor> ProfessorList = null;

		try {
			ProfessorList = (List<Professor>) session.createQuery("FROM Professor").list();
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			tx.rollback();
			throw e;
		} finally {
			session.close();
		}
		return ProfessorList;
	}

	public int update(Professor Professor) {
		Session session = HibernateUtils.openSession();
		Transaction tx = session.beginTransaction();

		try {
			session.update(Professor);
			session.getTransaction().commit();

		} catch (RuntimeException e) {
			tx.rollback();
			throw e;
		} finally {
			session.close();
		}
		return 1;
	}

	public void deleteById(String id) {
		Session session = HibernateUtils.openSession();
		Transaction tx = session.beginTransaction();
		Professor Professor = null;

		try {
			Professor = findByProfessorID(id);
			session.delete(Professor);
			tx.commit();
		} catch (RuntimeException e) {
			tx.rollback();
			throw e;
		} finally {
			session.close();
		}
	}

	public List<Professor> findProfessorByName(String name) {
		Session session = HibernateUtils.openSession();
		Transaction tx = session.beginTransaction();
		
		String hql = "from Professor where name=?";
		List<Professor> ProfessorList = session.createQuery(hql).setParameter(0, name).list();

		tx.commit();
		session.close();
		return ProfessorList;
	}
}
