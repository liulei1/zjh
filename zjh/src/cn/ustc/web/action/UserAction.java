package cn.ustc.web.action;

import java.sql.SQLException;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import cn.ustc.domain.Company;
import cn.ustc.domain.Professor;
import cn.ustc.domain.User;
import cn.ustc.web.service.CompanyService;
import cn.ustc.web.service.ProfessorService;
import cn.ustc.web.service.UserService;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

public class UserAction extends ActionSupport implements ModelDriven<User> {
	private User user = new User();
	private String usertype=null;//登录者类型
	
	
	
	
	public String getUsertype() {
		return usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}
	

	@Override
	public User getModel() {
		return user;
	}

	private UserService userService;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	//写get方法用于注入方式获取对象
	private ProfessorService professorService;
	public void setProfessorService(ProfessorService professorService) {
		this.professorService = professorService;
	}
	
	private CompanyService companyService;
	public void setCompanyService(CompanyService companyService) {
		this.companyService = companyService;
	}

	/**
	 * 登录--要判断登录用户的类型
	 * @return
	 * @throws SQLException
	 */
	@InputConfig(resultName="loginINPUT")
	public String login(){
		
		if (user.getName() == null || "".equals(user.getName().trim())) {
			return "loginINPUT";
		}
		if (user.getPassword() == null || "".equals(user.getPassword().trim())) {
			return "loginINPUT";
		}
		
		// TODO 判断用户类型 进行相应用户表查找
		if(user.getUsertype().equals("professor")){
			Professor professor=new Professor();
			professor.setName(user.getName());
			professor.setPassword(user.getPassword());
			professor=professorService.login(professor);
		
			if(professor!=null){
				ServletActionContext.getServletContext().setAttribute("professor", professor);
				return "professorloginSUCCESS";
			}else{
				return "loginINPUT";
			}
			
		}else if(user.getUsertype().equals("company")){
			Company company=new Company();
			company.setName(user.getName());
			company.setPassword(user.getPassword());
			company=companyService.login(company);
			if(company!=null){
				ServletActionContext.getServletContext().setAttribute("company", company);
				return "companyloginSUCCESS";
			}else{
				return "loginINPUT";
			}
			
			
		}
		
		User loginUser = userService.login(user);
		if(loginUser != null){
			ServletActionContext.getServletContext().setAttribute("user", loginUser);
			return "loginSUCCESS";
		}else {
			return "loginINPUT";
		}
	}
	
	/**
	 * 增加普通用户
	 * @return
	 * @throws SQLException 
	 */
	@InputConfig(resultName="registerINPUT")
	public String register() {
		boolean res = userService.insertUser(user);
		System.out.println(res);
		return "registerSUCCESS";
	}
	
	// 放入struts 值栈，用于所有普通用户显示
	private List<User> users;
	public List<User> getUsers() {
		return users;
	}
	/**
	 * 普通用户信息列表
	 * @return
	 */
	public String list(){
		users = userService.findAllUser();
		return "listSUCCESS";
	}
	
	/**
	 * 普通用户信息显示
	 * @return
	 */
	public String view(){
		user = userService.findUserById(user.getId());
		return "viewSUCCESS";
	}
	
	/**
	 * 修改普通用户信息前，先显示该用户信息
	 * @return
	 */
	public String editview(){
		user = userService.findUserById(user.getId());
		return "editviewSUCCESS";
	}
	
	/**
	 * 用户信息修改
	 * @return
	 */
	public String edit(){
		userService.update(user);
		return "editSUCCESS";
	}
	
	/**
	 * 用户信息删除
	 * @return
	 */
	public String delete(){
		userService.deleteUserById(user.getId());
		return "deleteSUCCESS";
	}
	
	/**
	 * 离焦判断用户名是否存在
	 * @return
	 */
	public String checkUserName(){
		if(!"".equals(user.getName())){
			List<User> list = userService.findUserByName(user.getName());
			if(list.size() > 0){
				user.setNameExsit(true);
			}else{
				user.setNameExsit(false);
			}
		}
		return SUCCESS;
	}
	
}
