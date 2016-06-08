package cn.ustc.web.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.LocalSessionFactoryBean;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.ustc.domain.Company;
import cn.ustc.domain.Professor;
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
		Company company=this.getHibernateTemplate().get(Company.class, id);
		this.getHibernateTemplate().delete(company);
	}

	public List<Company> findCompanyByVocation(String cat) {
		String hql = "from Company where field=?";
		List<Company> companyList = this.getHibernateTemplate().find(hql, cat);
		return companyList;
	}

	public List<Company> findAllUnaudit() {
		String hql="from Company where state=?";
		List<Company> companys=this.getHibernateTemplate().find(hql,"0");
		return companys;
	}

	public void pass(String id) {
		Company company=findByCompanyID(id);
		company.setState("1");
		this.getHibernateTemplate().update(company);
	}

	public void refuse(String id) {
		Company company=findByCompanyID(id);
		this.getHibernateTemplate().delete(company);
	}

	public Company findByCompInfo(String name, String password) {
		
		String hql="from Company where name=:name and password=:password";
		List<Company> complist=this.getHibernateTemplate().findByNamedParam(hql, new String []{"name","password"},new Object[]{name,password});
		return complist.size()==0?null:complist.get(0);
	}
	
}
