package cn.ustc.web.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.LocalSessionFactoryBean;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.ustc.domain.Company;
import cn.ustc.domain.User;
import cn.ustc.utils.HibernateUtils;

/**
 * 企业用户
 * @author liu
 *
 */
@SuppressWarnings("all")
public class CompanyDAO extends HibernateDaoSupport{
	
	/**
	 * 插入一个企业
	 * @param company
	 * @return
	 */
	private LocalSessionFactoryBean sessionFactory;
	public void setSessionFactory(LocalSessionFactoryBean sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public int insertCompany(Company company){
		this.getHibernateTemplate().save(company);
		return 1;
	}
	/**
	 * 查找
	 * @param criteria
	 * @return
	 */
	public Company findByCompanyID(String id){
		Company comp= this.getHibernateTemplate().get(Company.class, id);
		return comp;
	}
	
	public List<Company> findByCompanyName(String name){
//		Session session=HibernateUtils.openSession();
//		Transaction tx=session.beginTransaction();
//		List<Company> companys;
//		try{
//			String hql="from Company where name=?";
//			companys=session.createQuery(hql).setParameter(0, name).list();
//			tx.commit();
//		}catch(RuntimeException e){
//			e.printStackTrace();
//			throw e;
//		}finally{
//			session.close();
//		}
		String hql="from Company where name=:name";
		List<Company> companys = this.getHibernateTemplate().findByNamedParam(hql, "name", name);
		return companys;
	}
	
	public List<Company> findAll(){
		List<Company> comps= this.getHibernateTemplate().find("FROM Company");
		return comps;
	
	
	}
	public Company findByCriteria(DetachedCriteria criteria){
		List<Company> companys= this.getHibernateTemplate().findByCriteria(criteria);
		return companys.isEmpty()?null:companys.get(0);
	}
	/**
	 * 修改
	 * @param company
	 * @return
	 */
	public void update(Company company){
		this.getHibernateTemplate().update(company);
	}
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	public void deleteByCompanyID(String id){

		Session session=HibernateUtils.openSession();
		Transaction tx=session.beginTransaction();
		Company comp=null;
		try{
			session.delete(comp);
			tx.commit();
		}catch(RuntimeException e){
			e.printStackTrace();
			throw e;
		}finally{
			session.close();
		}
	
	}
	
}
