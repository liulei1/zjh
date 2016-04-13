package cn.ustc.web.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.ustc.domain.Consult;
import cn.ustc.utils.HibernateUtils;

/**
 * 咨询 操作
 * @author liu
 *
 */
@SuppressWarnings("unchecked")
public class ConsultDAO extends HibernateDaoSupport{

	/**
	 * 增加咨询
	 * @param consult
	 * @return
	 */
	public int insert(Consult consult) {
		this.getHibernateTemplate().save(consult);
		return 1;
	}
	
	/**
	 * 查找全部咨询
	 * @return
	 */
	public List<Consult> findAll() {
		String hql = "from Consult";
		List<Consult> list = this.getHibernateTemplate().find(hql);
		return list;
	}

	/**
	 * 查找未审核的咨询
	 * @return
	 */
	public List<Consult> findUncheckConsult() {
		DetachedCriteria criteria = DetachedCriteria.forClass(Consult.class).add(Restrictions.eq("state", Consult.UNCHECKED));
		List<Consult> list = this.getHibernateTemplate().findByCriteria(criteria);
		return list;
	}
	
	/**
	 * 查找审核通过的咨询
	 * @return
	 */
	public List<Consult> findAllowConsult() {
//		Session session = HibernateUtils.openSession();
//		Transaction transaction = session.beginTransaction();
//		
//		String hql = "from Consult where state =: state";
//		@SuppressWarnings("unchecked")
//		List<Consult> list = session.createQuery(hql).setParameter(0, Consult.ALLOW).list();
//		
//		transaction.commit();
//		session.close();
		String hql = "from Consult where state =: state";
		List<Consult> list = this.getHibernateTemplate().findByNamedParam(hql, "state", Consult.ALLOW);
		return list;
	}
	
	/**
	 * 根据id查找
	 * @param id
	 * @return
	 */
	public Consult findById(String id) {
//		Session session = HibernateUtils.openSession();
//		Transaction transaction = session.beginTransaction();
//		
//		Consult uniqueResult = (Consult) session.createCriteria(Consult.class).add(Restrictions.idEq(id)).uniqueResult();
//		
//		transaction.commit();
//		session.close();
		
		Consult uniqueResult = this.getHibernateTemplate().get(Consult.class, id);
		return uniqueResult;
	}

	/**
	 * 更新状态
	 * @param id consult的id
	 * @param state 改为此状态
	 * @return
	 */
	public int check(String id, String state) {
		Session session = this.getSession();
		
		String hql = "update Consult set state = ? where id = ?";
		Query query = session.createQuery(hql).setParameter(0, state).setParameter(1, id);
		int res = query.executeUpdate();
		
		return res;
	}
	
	/**
	 * 更新操作
	 * @param consult 持久化对象，更新的结果
	 */
	public void update(Consult consult){
		this.getHibernateTemplate().update(consult);
	}

	/**
	 * 条件查询
	 * @param criteria
	 * @return
	 */
	public List<Consult> findByDetachedCriteria(DetachedCriteria criteria){
		List<Consult> list = this.getHibernateTemplate().findByCriteria(criteria);
		return list;
	}
}
