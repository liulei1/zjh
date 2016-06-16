package cn.ustc.android.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.factory.annotation.Autowired;

import cn.ustc.domain.Company;
import cn.ustc.domain.Professor;
import cn.ustc.domain.User;
import cn.ustc.web.service.CompanyService;
import cn.ustc.web.service.ProfessorService;
import cn.ustc.web.service.UserService;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 安卓端用户登录注册处理类
 * @author liu
 *
 */
public class UserLogin extends ActionSupport implements ModelDriven<User>,ServletResponseAware {
	@Autowired
	private UserService userService;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private ProfessorService professorService;

	private HttpServletResponse response;
	private User user = new User();
	

	/**
	 * 登录--要判断登录用户的类型
	 * @return
	 * @throws SQLException
	 */
	public String login() {
		String id = null;
		if(User.PROFESSOR.equals(user.getUsertype())){
			// 专家用户登录
			Professor professor = new Professor();
			professor.setName(user.getName());
			professor.setPassword(user.getPassword());
			professor = professorService.login(professor);
			id = professor.getId();
		}else if(User.COMPANY.equals(user.getUsertype())){
			// 企业登录
			Company company = new Company();
			company.setName(user.getName());
			company.setPassword(user.getPassword());
			company = companyService.login(company);
			id = company.getId();
		}
		
		response.setCharacterEncoding("utf-8");
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
			if(id == null){
				pw.write("用户名密码错误！");
			}else {
				pw.write(id);
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		pw.flush();
		pw.close();
		return NONE;
	}
	
	/**
	 * 用户注册
	 * @return
	 */
	public String register() {
		boolean res = userService.insertUser(user);
		
		response.setCharacterEncoding("utf-8");
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
			if(res == true){
				pw.write("regist success");
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		pw.flush();
		pw.close();
		return NONE;
	}
	
	
	/****************************成员变量set，get方法*********************************/
	@Override
	public User getModel() {
		return user;
	}
	public void setServletResponse(HttpServletResponse arg0) {
		this.response = arg0;

	}
}
