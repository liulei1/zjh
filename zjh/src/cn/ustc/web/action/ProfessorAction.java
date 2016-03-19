package cn.ustc.web.action;

import java.util.List;

import cn.ustc.domain.Professor;
import cn.ustc.web.dao.impl.ProfessorDAOImpl;
import cn.ustc.web.service.ProfessorService;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

public class ProfessorAction extends ActionSupport implements ModelDriven<Professor>{
	private List<Professor> professors=null;
	
	public List<Professor> getProfessors() {
		return professors;
	}
	
	private Professor professor=new Professor();
	@Override
	public Professor getModel() {
		return professor;
	}
	
	private ProfessorService ProfessorService;
	public void setProfessorService(ProfessorService ProfessorService) {
		this.ProfessorService = ProfessorService;
	}

	@InputConfig(resultName = "professorRegister")
	public String register() {
		ProfessorService.insertProfessor(professor);
		return "professorRegisterSuccess";
	}
	
	
	public String management(){
		professors=ProfessorService.findAllProfessor();	
		return "professorlist";
	}
	public String checkProfessorName(){
		List<Professor> professors=null;
		if(!("".equals(professor.getName()))){
			professors=ProfessorService.findProfessorByName(professor.getName());
			if(professors.size()!=0){
				professor.setNameExsit(true);
			}else{
				professor.setNameExsit(false);
			}
		}
		return SUCCESS;
	}

}
