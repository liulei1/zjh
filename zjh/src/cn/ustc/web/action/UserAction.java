package cn.ustc.web.action;

import java.sql.SQLException;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import cn.ustc.domain.User;
import cn.ustc.web.service.UserService;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class UserAction extends ActionSupport implements ModelDriven<User> {
	private UserService userService = new UserService();
	private User user = new User();
	@Override
	public User getModel() {
		return user;
	}

	/**
	 * 登录--要判断登录用户的类型
	 * @return
	 * @throws SQLException 
	 */
	public String login(){
		if (user.getName() == null || "".equals(user.getName().trim())) {
			return "login";
		}
		if (user.getPassword() == null || "".equals(user.getPassword().trim())) {
			return "login";
		}
		
		// TODO 判断用户类型 进行相应用户表查找
		
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
	public String register() {
		if (user.getName() == null || "".equals(user.getName().trim())) {
			return "login";
		}
		if (user.getEmail() == null || "".equals(user.getEmail().trim())) {
			return "login";
		}
		if (user.getPassword() == null || "".equals(user.getPassword().trim())) {
			return "login";
		}
		boolean res = userService.insertUser(user);
		System.out.println(res);
		return "insertSUCCESS";
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
}
