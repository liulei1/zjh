package cn.ustc.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import cn.ustc.domain.Professor;
import cn.ustc.web.service.ProfessorService;

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

}
