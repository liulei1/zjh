package cn.ustc.web.dao;

import java.util.List;

import javax.ejb.TransactionManagement;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import cn.ustc.domain.Consult;

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
		String hql = "from Consult where state =:state";
		List<Consult> list = this.getHibernateTemplate().findByNamedParam(hql, "state", Consult.ALLOW);
		return list;
	}
	
	/**
	 * 根据id查找
	 * @param id
	 * @return
	 */
	public Consult findById(String id) {
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
	
	/**
	 * 条件查询
	 * @param criteria 条件
	 * @param firstResult 起始记录下标
	 * @param maxResults 最大记录数
	 * @return
	 */
	public List<Consult> findByDetachedCriteria(DetachedCriteria criteria, int firstResult, int maxResults){
		List<Consult> list = this.getHibernateTemplate().findByCriteria(criteria,firstResult,maxResults);
		return list;
	}
	
	/**
	 * 获取记录的总条数
	 * @return
	 */
	public int getCount(String id){
		String hql = "select count(*) from Consult as consult where consult.com_id =?";
		Long count = (Long) this.getHibernateTemplate().find(hql, id).listIterator().next();
		return count.intValue();
	}
	
	/**
	 * 查询所有审核通过的咨询
	 * @return
	 */
	public int getAllowCount(){
		String hql="select count(*) from Consult as consult where consult.state=?";
		Long count=(Long)this.getHibernateTemplate().find(hql,Consult.ALLOW).listIterator().next();
		return count.intValue();
	}

	/**
	 * 条件查询，按发布时间降序
	 * @param criteria
	 * @param maxSize 最多的返回条数
	 * @return
	 */
	public List<Consult> findConsultByCriteria(DetachedCriteria criteria, int maxSize) {
		return findByDetachedCriteria(criteria, 0, maxSize);
	}
	
	/**
	 * 查询企业正在进行的项目个数
	 * @param companyId 企业的Id
	 * @return
	 */
	public int findCompanyOnGoingConsultCount(String companyId){
		String hql="select count(*) from Consult as consult where consult.com_id=? and consult.state=?";
		Long count = (Long)this.getHibernateTemplate().find(hql, new Object[]{companyId,Consult.ALLOW}).listIterator().next();
		return count.intValue();
	}

	/**
	 * 根据专家id查找专家参与的咨询
	 * @param professorId
	 * @return
	 */
	public List<Consult> findProfessorApplyConslut(String professorId) {
		String sql = "select c.* from consult c inner join (select * from project_scheme where prof_id=:profId) sub on c.id=sub.cons_id where c.state=:state";
		Session session = this.getSessionFactory().getCurrentSession();
		professorId="12312";
		SQLQuery query = session.createSQLQuery(sql).addEntity(Consult.class);
		query.setString("profId", professorId);
		query.setString("state", Consult.ALLOW);
		List<Consult> list = query.list();
		return list;
	}
	
}
