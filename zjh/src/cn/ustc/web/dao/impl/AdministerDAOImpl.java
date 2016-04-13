package cn.ustc.web.dao.impl;

import java.util.List;
import java.util.UUID;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.ustc.domain.Administer;
import cn.ustc.utils.HibernateUtils;

/**
 * 管理员用户
 * @author liu
 *
 */
@SuppressWarnings("all")
public class AdministerDAOImpl  extends HibernateDaoSupport {
	
	/**
	 * 插入管理员
	 * @param administer
	 * @return
	 */
	public int insertAdminister(Administer administer) {
		Session session = HibernateUtils.openSession();
		Transaction transaction = session.beginTransaction();
		System.out.println(administer);

		try {
			session.save(administer);
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
	public Administer findByAdministerID(String id) {
		Session session = HibernateUtils.openSession();
		Transaction tx = session.beginTransaction();

		Administer Administer = null;
		try {
			Administer = (Administer) session.get(Administer.class, id);
			session.getTransaction().commit();

		} catch (RuntimeException e) {
			tx.rollback();
			throw e;
		} finally {
			session.close();
		}
		return Administer;
	}

	public List<Administer> findAll() {

		Session session = HibernateUtils.openSession();
		Transaction tx = session.beginTransaction();

		List<Administer> AdministerList = null;

		try {
			AdministerList = (List<Administer>) session.createQuery("FROM Administer").list();
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			tx.rollback();
			throw e;
		} finally {
			session.close();
		}
		return AdministerList;
	}

	/**
	 * 根据id 更新普通用户信息
	 * @param administer
	 * @return 1(要么抛异常，要么返回1)
	 */
	public int update(Administer administer) {
		Session session = HibernateUtils.openSession();
		Transaction tx = session.beginTransaction();

		try {
			session.update(administer);
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
		Administer Administer = null;

		try {
			Administer = findByAdministerID(id);
			session.delete(Administer);
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
	public List<Administer> findAdministerByName(String name) {
		Session session = HibernateUtils.openSession();
		Transaction tx = session.beginTransaction();
		
		String hql = "from Administer where name=?";
		List<Administer> AdministerList = session.createQuery(hql).setParameter(0, name).list();

		tx.commit();
		session.close();
		return AdministerList;
	}

	public Administer findByCriteria(DetachedCriteria criteria) {
		List<Administer> list = this.getHibernateTemplate().findByCriteria(criteria);
		return list.isEmpty() ? null : list.get(0);
	}
}
