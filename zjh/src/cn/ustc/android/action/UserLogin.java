package cn.ustc.android.action;

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
//		if (user.getName() == null || "".equals(user.getName().trim())) {
//			return "loginINPUT";
//		}
//		if (user.getPassword() == null || "".equals(user.getPassword().trim())) {
//			return "loginINPUT";
//		}
		System.out.println("name="+ user.getName() + " password=" + user.getPassword());
		response.setCharacterEncoding("utf-8");
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
			pw.write("visit success!!!");
		} catch (Exception e) {
			// log.error(e.getMessage(),e.fillInStackTrace());
		}
		pw.flush();
		pw.close();
		return NONE;
	}
}
