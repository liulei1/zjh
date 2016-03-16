package cn.ustc.web.dao.impl;

import java.util.List;
import java.util.UUID;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.ustc.domain.User;
import cn.ustc.utils.HibernateUtils;

@SuppressWarnings("all")
public class UserDAOImpl  extends HibernateDaoSupport {
	
	/**
	 * 插入普通用户
	 * @param user
	 * @return
	 */
	public int insertUser(User user) {
		Session session = HibernateUtils.openSession();
		Transaction transaction = session.beginTransaction();

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

	/**
	 * 根据id查询普通用户
	 * @param id
	 * @return
	 */
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

	/**
	 * 根据id 更新普通用户信息
	 * @param user
	 * @return 1(要么抛异常，要么返回1)
	 */
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

	/**
	 * 根据id 删除普通用户
	 * @param id
	 */
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

	/**
	 * 根据用户名查找用户
	 * @param name
	 * @return
	 */
	public List<User> findUserByName(String name) {
		Session session = HibernateUtils.openSession();
		Transaction tx = session.beginTransaction();
		
		String hql = "from User where name=?";
		List<User> userList = session.createQuery(hql).setParameter(0, name).list();

		tx.commit();
		session.close();
		return userList;
	}

	public User findByCriteria(DetachedCriteria criteria) {
		List<User> list = this.getHibernateTemplate().findByCriteria(criteria);
		return list.isEmpty() ? null : list.get(0);
	}
}
