package cn.ustc.web.action;

import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import cn.ustc.domain.Company;
import cn.ustc.domain.Message;
import cn.ustc.domain.User;
import cn.ustc.utils.DateUtils;
import cn.ustc.web.service.CompanyService;
import cn.ustc.web.service.MessageService;
import cn.ustc.web.service.UserService;

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
	@Autowired
	private UserService userService;
	@Autowired
	private MessageService messageService;
	
	@InputConfig(resultName = "companyRegister")
	public String register() {
		User user=(User) ServletActionContext.getServletContext().getAttribute("user");
		if(user!=null){
			company.setState("0");
		}else{
			company.setState("1");
		}
		company.setUsertype("1");
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
	
	//查询所有状态为0的company,放在companys中
	public String unauditlist(){
		companys=companyService.findAllUnaudit();
		return "uneditCompanys";
	}
	
	//通过一个company申请
	public String pass(){
		//传进来的company只有id一个属性
		companyService.pass(company.getId());
		//为user放一条消息，先查到company的名字，再拿着名字查找user,给这个id的user放一条消息
		company=companyService.findCompanyById(company.getId());
		String name=company.getName();
		User user=companyService.findCompanyByName(name).get(0);
		String userID=user.getId();
		//生成一条完整的message
		Message message=new Message();
		message.setRecipientId(userID);
		message.setType(Message.TOUSER);
		String time=DateUtils.dateToString(new Date());
		message.setSendTime(time);
		message.setState(Message.UNREAD);
		message.setTitle("系统通知");
		message.setContent("您注册成为企业用户的申请已经通过，现在可以以企业用户身份登录");
		messageService.sendMessage(message);
		return "passSuccess";
	}
	
	//拒绝一个company申请
	public String refuse(){
		company=companyService.findCompanyById(company.getId());
		String name=company.getName();
		User user=userService.findUserByName(name).get(0);
		String userID=user.getId();
		//生成一条完整的message
		Message message=new Message();
		message.setRecipientId(userID);
		message.setType(Message.TOUSER);
		String time=DateUtils.dateToString(new Date());
		message.setSendTime(time);
		message.setState(Message.UNREAD);
		message.setTitle("系统通知");
		message.setContent("您注册成为专家的申请未通过，可再次申请");
		messageService.sendMessage(message);
		
		companyService.refuse(company.getId());
		return "refused";
		
	}
	
}
