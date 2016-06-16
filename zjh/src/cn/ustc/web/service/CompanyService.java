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
/**
 * 企业service
 * @author liu
 *
 */
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

	/**
	 * 企业登录
	 * @param company
	 * @return
	 */
	public Company login(Company company) {
		/*DetachedCriteria criteria = DetachedCriteria.forClass(Company.class);
		criteria.add(Restrictions.eq("name", company.getName()));
		criteria.add(Restrictions.eq("password", company.getPassword()));
		Company loginCompany = companyDAO.findByCriteria(criteria);*/
		Company loginCompany=companyDAO.findByCompInfo(company.getName(),company.getPassword());
		return loginCompany;
	}

	/**
	 * 查找所有企业
	 * @return
	 */
	public List<Company> findAllCompany() {
		List<Company> Companys = companyDAO.findAll();
		return Companys;
	}

	/**
	 * 根据id查找企业
	 * @param id
	 * @return
	 */
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

	/**
	 * 根据名称查找企业
	 * @param name
	 * @return
	 */
	public List<Company> findCompanyByName(String name) {
		return companyDAO.findByCompanyName(name);
	}

	/**
	 * 充值余额
	 * @param id
	 * @param balance
	 */
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

	/**
	 * 根据领域查找企业
	 * @param cat
	 * @return
	 */
	public List<Company> findCompanyByVocation(String cat) {
		return companyDAO.findCompanyByVocation(cat);
	}

	/**
	 * 更新企业信息
	 * @param company
	 */
	public void updateInfo(Company company) {
		Company update = this.copyUpdateCompany(company);
		companyDAO.update(update);
	}

	/**
	 * 拷贝model非空的待更新信息
	 * @param company
	 * @return
	 */
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

	/**
	 * 查看所有企业认证
	 * @return
	 */
	public List<Company> findAllUnaudit() {
		companys=companyDAO.findAllUnaudit();
		return companys;
	}

	/**
	 * 通过认证
	 * @param id
	 */
	public void pass(String id) {
		companyDAO.pass(id);
	}

	/**
	 * 拒绝认证
	 * @param id
	 */
	public void refuse(String id) {
		companyDAO.refuse(id);
	}
}
