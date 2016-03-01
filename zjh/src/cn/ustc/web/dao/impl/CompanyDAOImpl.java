package cn.ustc.web.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.LocalSessionFactoryBean;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.ustc.domain.Company;
import cn.ustc.utils.HibernateUtils;

@SuppressWarnings("all")
public class CompanyDAOImpl extends HibernateDaoSupport{
	
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
		Session session=HibernateUtils.openSession();
		Transaction tx=session.beginTransaction();
		try{
			session.save(company);
			tx.commit();
		}catch(RuntimeException e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		
		return 1;
		
	}
	/**
	 * 查找
	 * @param criteria
	 * @return
	 */
	public Company findByCompanyID(String id){
		Session session=HibernateUtils.openSession();
		Transaction tx=session.beginTransaction();
		Company comp=null;
		try{
			comp=(Company) session.get(Company.class, id);
			tx.commit();
		}catch(RuntimeException e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return comp;
	}
	public Company findByCompanyName(String name){
		
		Session session=HibernateUtils.openSession();
	
		
		Transaction tx=session.beginTransaction();
		Company comp=null;
		try{
			comp=(Company) session.get(Company.class, name);
			tx.commit();
		}catch(RuntimeException e){
			e.printStackTrace();
			throw e;
		}finally{
			session.close();
		}
		return comp;
	}
	
	public List<Company> findAll(){
		Session session=HibernateUtils.openSession();
		Transaction tx=session.beginTransaction();
		List<Company> comps=null;
		try{
			comps=session.createQuery("FROM company").list();
			tx.commit();
		}catch(RuntimeException e){
			e.printStackTrace();
			throw e;
		}finally{
			session.close();
		}
		return comps;
	
	
	}
	public Company findByCriteria(DetachedCriteria criteria){
		List<Company> companys= this.getHibernateTemplate().findByCriteria(criteria);
		return companys.isEmpty()?null:companys.get(0);
	}
	/**
	 * 修改
	 * @param criteria
	 * @return
	 */
	public int update(Company company){
		Session session=HibernateUtils.openSession();
		Transaction tx=session.beginTransaction();
		Company comp=null;
		try{
			session.update(company);
			tx.commit();
		}catch(RuntimeException e){
			e.printStackTrace();
			throw e;
		}finally{
			session.close();
		}
		return 1;
	
	}
	/**
	 * 删除
	 * @param criteria
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
