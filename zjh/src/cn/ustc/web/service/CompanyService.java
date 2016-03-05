package cn.ustc.web.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import cn.ustc.domain.Company;
import cn.ustc.web.dao.impl.CompanyDAOImpl;

public class CompanyService {

	private CompanyDAOImpl CompanyDAO;
	public void setCompanyDAO(CompanyDAOImpl CompanyDAO) {
		this.CompanyDAO = CompanyDAO;
	}

	public boolean insertCompany(Company Company){
		int res = 0;
		res = CompanyDAO.insertCompany(Company);
		if (res > 0) {
			return true;
		}
		return false;
	}

	public Company login(Company company) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Company.class);
		criteria.add(Restrictions.eq("name", company.getName()));
		criteria.add(Restrictions.eq("password", company.getPassword()));
		Company loginCompany = CompanyDAO.findByCriteria(criteria);
		return loginCompany;
	}

	public List<Company> findAllCompany() {
		List<Company> Companys = CompanyDAO.findAll();
		return Companys;
	}

	public Company findCompanyById(String id) {
		Company Company = CompanyDAO.findByCompanyID(id);
		return Company;
	}

	public int update(Company Company) {
		return CompanyDAO.update(Company);
	}

	public void deleteCompanyById(String id) {
		CompanyDAO.deleteByCompanyID(id);
	}

	public List<Company> findCompanyByName(String name) {
		return CompanyDAO.findByCompanyName(name);
	}



}
