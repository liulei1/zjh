package cn.ustc.android.action;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.factory.annotation.Autowired;

import cn.ustc.domain.Company;
import cn.ustc.web.service.CompanyService;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 安卓端对Professor操作方法类
 * @author liu
 *
 */
public class CompanyOperateAction extends ActionSupport implements ModelDriven<Company>, ServletResponseAware{
	private Company model = new Company();
	private HttpServletResponse response;
	@Autowired
	private CompanyService companyService;
	
	/**
	 * 获取企业
	 * 调用条件：传入企业的id
	 * @return
	 */
	public String companyDetails(){
		String id = model.getId();
		model = companyService.findCompanyById(id);
		return SUCCESS;
	}
	
	/****************************成员变量set，get方法*********************************/
	@Override
	public Company getModel() {
		return model;
	}
	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
}
