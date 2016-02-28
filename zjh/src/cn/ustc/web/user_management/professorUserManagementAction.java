package cn.ustc.web.user_management;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;

import cn.ustc.domain.Professor;
import cn.ustc.web.service.ProfessorService;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

public class professorUserManagementAction extends ActionSupport implements ModelDriven<Professor> {
	private Professor Professor = new Professor();
	
	@Override
	public Professor getModel() {
		return Professor;
	}

	private ProfessorService ProfessorService;
	public void setProfessorService(ProfessorService ProfessorService) {
		this.ProfessorService = ProfessorService;
	}

	/**
	 * 登录--要判断登录用户的类型
	 * @return
	 * @throws SQLException
	 */
	@InputConfig(resultName="loginINPUT")
	public String login(){
		
		if (Professor.getName() == null || "".equals(Professor.getName().trim())) {
			return "loginINPUT";
		}
		if (Professor.getPassword() == null || "".equals(Professor.getPassword().trim())) {
			return "loginINPUT";
		}
		
	
		
		// TODO 判断用户类型 进行相应用户表查找
		
		Professor loginProfessor = ProfessorService.login(Professor);
		if(loginProfessor != null){
			ServletActionContext.getServletContext().setAttribute("Professor", loginProfessor);
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
		boolean res = ProfessorService.insertProfessor(Professor);
		System.out.println(res);
		return "registerSUCCESS";
	}
	
	// 放入struts 值栈，用于所有普通用户显示
	private List<Professor> Professors;
	public List<Professor> getProfessors() {
		return Professors;
	}
	/**
	 * 普通用户信息列表
	 * @return
	 */
	public String list(){
		Professors = ProfessorService.findAllProfessor();
		return "listSUCCESS";
	}
	
	/**
	 * 普通用户信息显示
	 * @return
	 */
	public String view(){
		Professor = ProfessorService.findProfessorById(Professor.getId());
		return "viewSUCCESS";
	}
	
	/**
	 * 修改普通用户信息前，先显示该用户信息
	 * @return
	 */
	public String editview(){
		Professor = ProfessorService.findProfessorById(Professor.getId());
		return "editviewSUCCESS";
	}
	
	/**
	 * 用户信息修改
	 * @return
	 */
	public String edit(){
		ProfessorService.update(Professor);
		return "editSUCCESS";
	}
	
	/**
	 * 用户信息删除
	 * @return
	 */
	public String delete(){
		ProfessorService.deleteProfessorById(Professor.getId());
		return "deleteSUCCESS";
	}
	
	/**
	 * 离焦判断用户名是否存在
	 * @return
	 */
	public String checkProfessorName(){
		if(!"".equals(Professor.getName())){
			List<Professor> list = ProfessorService.findProfessorByName(Professor.getName());
			if(list.size() > 0){
				Professor.setNameExsit(true);
			}else{
				Professor.setNameExsit(false);
			}
		}
		return SUCCESS;
	}
	
}
