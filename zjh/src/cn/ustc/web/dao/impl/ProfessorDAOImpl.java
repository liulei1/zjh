package cn.ustc.web.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.ustc.domain.Professor;
import cn.ustc.utils.HibernateUtils;

/**
 * 专家操作
 * @author liu
 *
 */
public class ProfessorDAOImpl extends HibernateDaoSupport{
	
	@SuppressWarnings("unchecked")
	public Professor findProfessorByProfessorNameAndPwd(String Professorname, String password) {
//		Session session = HibernateUtils.openSession();
//		Transaction transaction = session.beginTransaction();
//		try {
//			String hql = "from Professor as Professor where Professor.name=? and Professor.password=?";
//			Query qr = session.createQuery(hql);
//			qr.setString(0, Professorname);
//			qr.setString(1, password);
//
//			Professor professor = (Professor) qr.uniqueResult();
//			transaction.commit();
//			return professor;
//
//		} catch (RuntimeException e) {
//			throw e;
//		} finally {
//
//			session.close();
//		}
		String hql = "from Professor as Professor where Professor.name=? and Professor.password=?";
		List<Professor> list = this.getHibernateTemplate().findByNamedQuery(hql, Professorname,password);
		return list.get(0);
	}

	public int insertProfessor(Professor professor) {
		this.getHibernateTemplate().save(professor);
		return 1;
	}

	public Professor findByProfessorID(String id) {
//		Session session = HibernateUtils.openSession();
//		Transaction tx = session.beginTransaction();
//
//		Professor Professor = null;
//		try {
//			Professor = (Professor) session.get(Professor.class, id);
//			session.getTransaction().commit();
//
//		} catch (RuntimeException e) {
//			tx.rollback();
//			throw e;
//		} finally {
//			session.close();
//		}
//		return Professor;
		Professor professor = this.getHibernateTemplate().get(Professor.class, id);
		return professor;
	}

	@SuppressWarnings("unchecked")
	public List<Professor> findAll() {

//		Session session = HibernateUtils.openSession();
//		Transaction tx = session.beginTransaction();
//
//		List<Professor> ProfessorList = null;
//
//		try {
//			ProfessorList = (List<Professor>) session.createQuery("FROM Professor").list();
//			session.getTransaction().commit();
//		} catch (RuntimeException e) {
//			tx.rollback();
//			throw e;
//		} finally {
//			session.close();
//		}
//		return ProfessorList;
		List<Professor> list = this.getHibernateTemplate().findByNamedQuery("FROM Professor");
		return list;
	}

	public int update(Professor professor) {
//		Session session = HibernateUtils.openSession();
//		Transaction tx = session.beginTransaction();
//
//		try {
//			session.update(professor);
//			session.getTransaction().commit();
//
//		} catch (RuntimeException e) {
//			tx.rollback();
//			throw e;
//		} finally {
//			session.close();
//		}
		this.getHibernateTemplate().update(professor);
		return 1;
	}

	public void deleteById(String id) {
//		Session session = HibernateUtils.openSession();
//		Session session = HibernateUtils.getCurrentSession();
//		Transaction tx = session.beginTransaction();
//		Professor professor = null;
//
//		try {
//			professor = findByProfessorID(id);
//			session.delete(professor);
//			tx.commit();
//		} catch (RuntimeException e) {
//			tx.rollback();
//			throw e;
//		} finally {
//			session.close();
//		}
		Professor professor = this.getHibernateTemplate().get(Professor.class, id);
		this.getHibernateTemplate().delete(professor);
	}

	@SuppressWarnings("unchecked")
	public List<Professor> findProfessorByName(String name) {
//		Session session = HibernateUtils.openSession();
//		Transaction tx = session.beginTransaction();
//		
//		String hql = "from Professor where name=?";
//		List<Professor> professorList = session.createQuery(hql).setParameter(0, name).list();
//
//		tx.commit();
//		session.close();
		String hql = "from Professor where name=?";
		List<Professor> professorList = this.getHibernateTemplate().findByNamedQuery(hql, name);
		return professorList;
	}
}
