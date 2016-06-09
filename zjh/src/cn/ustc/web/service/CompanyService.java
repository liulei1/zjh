package cn.ustc.web.service;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import cn.ustc.domain.Company;
import cn.ustc.web.dao.CompanyDAO;
import cn.ustc.web.exception.NoUserException;

@Transactional
public class CompanyService {
	@Autowired
	private CompanyDAO companyDAO;
	
	private List<Company> companys=null;
	
	public boolean insertCompany(Company Company){
		int res = 0;
		res = companyDAO.insertCompany(Company);
		if (res > 0) {
			return true;
		}
		return false;
	}

	public Company login(Company company) {
		/*DetachedCriteria criteria = DetachedCriteria.forClass(Company.class);
		criteria.add(Restrictions.eq("name", company.getName()));
		criteria.add(Restrictions.eq("password", company.getPassword()));
		Company loginCompany = companyDAO.findByCriteria(criteria);*/
		Company loginCompany=companyDAO.findByCompInfo(company.getName(),company.getPassword());
		return loginCompany;
	}

	public List<Company> findAllCompany() {
		List<Company> Companys = companyDAO.findAll();
		return Companys;
	}

	public Company findCompanyById(String id) {
		Company company = companyDAO.findByCompanyID(id);
		Hibernate.initialize(company.getConsults());
		return company;
	}

	/**
	 * 更新持久对象
	 * @param company 持久对象
	 */
	public void update(Company company) {
		companyDAO.update(company);
	}

	/**
	 * 通过id删除企业
	 * @param id
	 */
	public void deleteCompanyById(String id) {
		companyDAO.deleteByCompanyID(id);
	}

	public List<Company> findCompanyByName(String name) {
		return companyDAO.findByCompanyName(name);
	}

	public void addBalance(String id, double balance) {
		Company company = companyDAO.findByCompanyID(id);
		if(company == null){
			throw new NoUserException();
		}else {
			if(company.getBalance() != null && !"".equals(company.getBalance())){
				balance = Double.parseDouble(company.getBalance()) + balance;
			}
			company.setBalance(balance+"");
			companyDAO.update(company);
		}
	}

	public List<Company> findCompanyByVocation(String cat) {
		return companyDAO.findCompanyByVocation(cat);
	}

	public void updateInfo(Company company) {
		Company update = this.copyUpdateCompany(company);
		companyDAO.update(update);
	}
	
	private Company copyUpdateCompany(Company company){
		Company instance = companyDAO.findByCompanyID(company.getId());
		instance.setName(company.getName());
		instance.setEmail(company.getEmail());
		instance.setTelephone(company.getTelephone());
		instance.setAddress(company.getAddress());
		instance.setWebsite(company.getWebsite());
		instance.setSex(company.getSex());
		instance.setField(company.getField());
		instance.setAnnotation(company.getAnnotation());
		return instance;
	}

	public List<Company> findAllUnaudit() {
		companys=companyDAO.findAllUnaudit();
		return companys;
	}

	public void pass(String id) {
		companyDAO.pass(id);
	}

	public void refuse(String id) {
		companyDAO.refuse(id);
	}
}
