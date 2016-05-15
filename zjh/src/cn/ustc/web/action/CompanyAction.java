package cn.ustc.web.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import cn.ustc.domain.Company;
import cn.ustc.domain.Professor;
import cn.ustc.web.service.CompanyService;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

public class CompanyAction extends ActionSupport implements ModelDriven<Company>{
	private Company company=new Company();
	@Override
	public Company getModel() {
		return company;
	}
	
	private List<Company> companys;
	public List<Company> getCompanys() {
		return companys;
	}
	
	@Autowired
	private CompanyService companyService;
	
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
	
	public String companySearch(){
		String cat=ServletActionContext.getRequest().getParameter("category");
		companys=companyService.findCompanyByVocation(cat);
		return "findSuccess";
	}
	
	public String companySearchByName(){
		String name=ServletActionContext.getRequest().getParameter("findByName");
		System.out.println(name);
		companys=companyService.findCompanyByName(name);
		return "findSuccess";
	}

	
}
