package cn.ustc.android.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletResponseAware;

import cn.ustc.domain.User;
import cn.ustc.web.service.UserService;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class UserLogin extends ActionSupport implements ModelDriven<User>,
		ServletResponseAware {
	private UserService userService = new UserService();
	private HttpServletResponse response;
	private User user = new User();
	@Override
	public User getModel() {
		return user;
	}
	public void setServletResponse(HttpServletResponse arg0) {
		this.response = arg0;

	}

	/**
	 * 登录--要判断登录用户的类型
	 * 
	 * @return
	 * @throws SQLException
	 */
	public String login() {
		response.setCharacterEncoding("utf-8");
		PrintWriter pw = null;
		System.out.println("name="+ user.getName() + " password=" + user.getPassword());
		User loginer = userService.login(user);
		try {
			pw = response.getWriter();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		if(loginer == null){
			pw.write("用户名密码错误！");
		}else {
			pw.write("visit success!!!");
		}
		pw.flush();
		pw.close();
		return NONE;
	}
}
