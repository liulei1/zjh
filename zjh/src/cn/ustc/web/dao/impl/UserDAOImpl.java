package cn.ustc.web.dao.impl;

import java.util.List;
import java.util.UUID;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import cn.ustc.domain.User;
import cn.ustc.utils.HibernateUtils;
import cn.ustc.web.dao.UserDAO;

@SuppressWarnings("all")
public class UserDAOImpl implements UserDAO {
	
	public User findUserByuserNameAndPwd(String username, String password) {
		Session session = HibernateUtils.openSession();
		Transaction transaction = session.beginTransaction();

		try {
			String hql = "from User as user where user.name=? and user.password=?";
			Query qr = session.createQuery(hql);
			qr.setString(0, username);
			qr.setString(1, password);

			User user = (User) qr.uniqueResult();
			transaction.commit();
			return user;

		} catch (RuntimeException e) {
//			transaction.rollback();
			throw e;
		} finally {

			session.close();
		}

	}

	public int insertUser(User user) {
		Session session = HibernateUtils.openSession();
		Transaction transaction = session.beginTransaction();
//		user.setId(UUID.randomUUID().toString());
		System.out.println(user);

		try {
			session.save(user);
			transaction.commit();
		} catch (RuntimeException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		// 为了和QueryRunner的插入条数返回值匹配
		return 1;
	}

	public User findByUserID(String id) {
		Session session = HibernateUtils.openSession();
		Transaction tx = session.beginTransaction();

		User user = null;
		try {
			user = (User) session.get(User.class, id);
			session.getTransaction().commit();

		} catch (RuntimeException e) {
			tx.rollback();
			throw e;
		} finally {
			session.close();
		}
		return user;
	}

	public List<User> findAll() {

		Session session = HibernateUtils.openSession();
		Transaction tx = session.beginTransaction();

		List<User> userList = null;

		try {
			userList = (List<User>) session.createQuery("FROM User").list();
			session.getTransaction().commit();

		} catch (RuntimeException e) {
			tx.rollback();
			throw e;
		} finally {
			session.close();
		}

		return userList;

	}

	public int update(User user) {
		Session session = HibernateUtils.openSession();
		Transaction tx = session.beginTransaction();

		try {
			session.update(user);
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
		User user = null;

		try {
			user = findByUserID(id);
			session.delete(user);
			tx.commit();
		} catch (RuntimeException e) {
			tx.rollback();
			throw e;
		} finally {
			session.close();
		}
	}
}
