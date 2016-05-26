package cn.ustc.web.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import cn.ustc.domain.Company;
import cn.ustc.domain.Professor;
import cn.ustc.domain.Vocation;
import cn.ustc.web.dao.VocationDAO;
import cn.ustc.web.service.ProfessorService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

public class ProfessorAction extends ActionSupport implements ModelDriven<Professor>{
	private List<Professor> professors;
	public List<Professor> getProfessors() {
		return professors;
	}
	
	private Professor professor=new Professor();
	@Override
	public Professor getModel() {
		return professor;
	}
	
	@Autowired
	private ProfessorService professorService;
	@Autowired
	private VocationDAO vocationDAO;

	@InputConfig(resultName = "professorRegister")
	public String register() {
		professorService.insertProfessor(professor);
		return "professorRegisterSuccess";
	}
	
	public String management(){
		professors=professorService.findAllProfessor();	
		return "professorlist";
	}
	public String checkProfessorName(){
		List<Professor> professors=null;
		if(!("".equals(professor.getName()))){
			professors=professorService.findProfessorByName(professor.getName());
			if(professors.size()!=0){
				professor.setNameExsit(true);
			}else{
				professor.setNameExsit(false);
			}
		}
		return SUCCESS;
	}
	
	//查找专家
	public String professorSearch(){
		String cat=(String) ServletActionContext.getRequest().getParameter("category");
		professors=professorService.findProfessorByVocation(cat);
		return "findSuccess";
	}
	
	//通过名字查找专家
	public String professorSearchByName(){
		String name=(String) ServletActionContext.getRequest().getParameter("findByName");
		professors=professorService.findProfessorByName(name);
		return "findSuccess";
	}

	// 查看专家用户信息
	public String viewProfessorInfo(){
		Professor user = (Professor) ServletActionContext.getServletContext().getAttribute("user");
		professor = professorService.findProfessorById(user.getId());
		return "viewProfessorInfoSUCCESS";
	}
	
	// 更新信息
	public String updateProfessorInfo(){
		Professor user = (Professor) ServletActionContext.getServletContext().getAttribute("user");
		professor.setId(user.getId());
		professorService.updateInfo(professor);
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
		Professor user = (Professor) ServletActionContext.getServletContext().getAttribute("user");
		Professor p = professorService.findProfessorById(user.getId());
		ActionContext context = ActionContext.getContext();
		if(p.getPassword().equals(professor.getPassword())){
			p.setPassword(professor.getNewPassword());
			professorService.update(p);
			context.put("result", "operate success");
		}else{
			context.put("result", "passwords entered did not match");
		}
		return "changePasswordSUCCESS";
	}
	
	// 通过传来的专家的id 获取专家信息
	public String viewProfessorInfoById(){
		String id = professor.getId();
		professor = professorService.findProfessorById(id);
		Vocation vocation = vocationDAO.findVocationById(professor.getField());
		professor.setField(vocation.getName());
		return "viewProfessorInfoByIdSUCCESS";
	}
}
