package cn.ustc.android.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.factory.annotation.Autowired;

import cn.ustc.domain.Professor;
import cn.ustc.domain.User;
import cn.ustc.web.service.CompanyService;
import cn.ustc.web.service.ProfessorService;
import cn.ustc.web.service.UserService;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class UserLogin extends ActionSupport implements ModelDriven<User>,ServletResponseAware {
	@Autowired
	private UserService userService;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private ProfessorService professorService;

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
		String id = null;
		if("2".equals(user.getUsertype())){
			// 专家用户登录
			Professor professor = new Professor();
			professor.setName(user.getName());
			professor.setPassword(user.getPassword());
			professor = professorService.login(professor);
			id = professor.getId();
		}
		try {
			pw = response.getWriter();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		if(id == null){
			pw.write("用户名密码错误！");
		}else {
			pw.write(id);
		}
		pw.flush();
		pw.close();
		return NONE;
	}
	
	public String register() {
		response.setCharacterEncoding("utf-8");
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		boolean res = userService.insertUser(user);
		if(res == true){
			pw.write("regist success");
		}
		pw.flush();
		pw.close();
		return NONE;
	}
}
