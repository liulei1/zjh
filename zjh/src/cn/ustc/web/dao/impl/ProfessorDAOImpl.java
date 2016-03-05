package cn.ustc.web.dao.impl;

import java.util.List;
import java.util.UUID;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.ustc.domain.Professor;
import cn.ustc.utils.HibernateUtils;
import cn.ustc.web.dao.ProfessorDAO;

@SuppressWarnings("all")
public class ProfessorDAOImpl  extends HibernateDaoSupport implements ProfessorDAO {
	
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
//			transaction.rollback();
			throw e;
		} finally {

			session.close();
		}

	}

	public int insertProfessor(Professor professor) {
		Session session = HibernateUtils.openSession();
		Transaction transaction = session.beginTransaction();
//		Professor.setId(UUID.randomUUID().toString());
		System.out.println(professor);

		try {
			session.save(professor);
			transaction.commit();
		} catch (RuntimeException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		// 为了和QueryRunner的插入条数返回值匹配
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

	@Override
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
