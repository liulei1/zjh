package cn.ustc.web.action;

import java.util.List;

import cn.ustc.domain.Company;
import cn.ustc.web.dao.impl.CompanyDAOImpl;
import cn.ustc.web.service.CompanyService;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

public class CompanyAction extends ActionSupport implements ModelDriven<Company>{
	private CompanyService companyService;

	public void setCompanyService(CompanyService companyService) {
		this.companyService = companyService;
	}

	private Company company=new Company();

	@Override
	public Company getModel() {
		return company;
	}
	
	@InputConfig(resultName = "companyRegister")
	public String register() {
		companyService.insertCompany(company);
		return "companyRegisterSuccess";
	}
		
	public String checkCompanyName(){
		List<Company> companys=null;
		if(!("".equals(company.getName()))){
			companys=companyService.findCompanyByName(company.getName());
			if(companys.size()!=0){
				company.setNameExsit(true);
			}else{
				company.setNameExsit(false);
			}
		}
		return SUCCESS;
	}

	
}
