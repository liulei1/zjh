package cn.ustc.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import cn.ustc.domain.Company;
import cn.ustc.domain.User;
import cn.ustc.utils.GetPropertiesUtil;
import cn.ustc.utils.UploadAndDownloadUtils;
import cn.ustc.web.service.CompanyService;

import com.opensymphony.xwork2.ActionContext;
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

	// 跳转到充值界面
	public String skipBalancePage(){
		User user = (User)ServletActionContext.getServletContext().getAttribute("user");
		String id = user.getId();
		company = companyService.findCompanyById(id);
		return "skipBalancePageSUCCESS";
	}
	
	// 企业用户充值
	public String addBalance(){
		String id = company.getId();
		double balance = Double.parseDouble(company.getBalance());
		if(balance < 0){
			balance = 0;
		}
		companyService.addBalance(id, balance);
		return "addBalanceSUCCESS";
	}
	
	// 查看企业用户信息
	public String viewCompanyInfo(){
		Company user = (Company) ServletActionContext.getServletContext().getAttribute("user");
		company = companyService.findCompanyById(user.getId());
		return "viewCompanyInfoSUCCESS";
	}
	
	// 更新信息
	public String updateProfessorInfo(){
		Company user = (Company) ServletActionContext.getServletContext().getAttribute("user");
		company.setId(user.getId());
		companyService.updateInfo(company);
		ActionContext context = ActionContext.getContext();
		context.put("result", "operate success");
		return "updateProfessorInfoSUCCESS";
	}
	
	// 修改密码视图
	public String viewChangePassword(){
		return "viewChangePasswordSUCCESS";
	}
	
	// 更新密码
	public String changePassword(){
		Company user = (Company) ServletActionContext.getServletContext().getAttribute("user");
		Company c = companyService.findCompanyById(user.getId());
		ActionContext context = ActionContext.getContext();
		if(c.getPassword().equals(company.getPassword())){
			c.setPassword(company.getNewPassword());
			companyService.update(c);
			context.put("result", "operate success");
		}else{
			context.put("result", "passwords entered did not match");
		}
		return "changePasswordSUCCESS";
	}
	
	private File file;
	private String fileContentType;

	public void setFile(File file) {
		this.file = file;
	}
	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}
	private String imgPath;
	public String getImgPath() {
		return imgPath;
	}

	//头像上传
	public String uploadCompanyImage(){
		
		// 判断文件不为空，且是图片
		if (file != null && "image/png".equals(fileContentType)) {
			HttpServletRequest request = ServletActionContext.getRequest();
			// 图片大小默认最大10M
			String fileSize = request.getParameter("fileSize")!=null?request.getParameter("fileSize"):"10";
			try {
				FileInputStream f = new FileInputStream(file);
				int imgSize = f.available()>>20; // 单位变为M
				if(imgSize<Integer.valueOf(fileSize)){
					String uploadPath = GetPropertiesUtil.getPropertiesValueByKey("imgUploadPath");
					String imgRootPath = ServletActionContext.getServletContext().getRealPath(uploadPath);
					
					// 更新信息
//					company = (Company)ServletActionContext.getServletContext().getAttribute("user");
					imgPath = UploadAndDownloadUtils.restoreFile(file,imgRootPath,"1111");
//					Company companyById = companyService.findCompanyById(company.getId());
//					companyById.setImage(imgPath);
//					companyService.update(companyById);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return SUCCESS;
	}
}
