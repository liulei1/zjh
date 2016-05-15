package cn.ustc.web.service;

import java.util.List;

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

	public boolean insertCompany(Company Company){
		int res = 0;
		res = companyDAO.insertCompany(Company);
		if (res > 0) {
			return true;
		}
		return false;
	}

	public Company login(Company company) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Company.class);
		criteria.add(Restrictions.eq("name", company.getName()));
		criteria.add(Restrictions.eq("password", company.getPassword()));
		Company loginCompany = companyDAO.findByCriteria(criteria);
		return loginCompany;
	}

	public List<Company> findAllCompany() {
		List<Company> Companys = companyDAO.findAll();
		return Companys;
	}

	public Company findCompanyById(String id) {
		Company Company = companyDAO.findByCompanyID(id);
		return Company;
	}

	public void update(Company Company) {
		companyDAO.update(Company);
	}

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
	
}
