package cn.ustc.android.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.factory.annotation.Autowired;

import cn.ustc.domain.Company;
import cn.ustc.domain.Evaluate;
import cn.ustc.domain.Professor;
import cn.ustc.domain.Project;
import cn.ustc.utils.DateUtils;
import cn.ustc.utils.GetPropertiesUtil;
import cn.ustc.web.service.CompanyService;
import cn.ustc.web.service.EvaluateService;
import cn.ustc.web.service.ProfessorService;
import cn.ustc.web.service.ProjectService;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class EvaluateAndriodAction extends ActionSupport implements ModelDriven<Evaluate>, ServletResponseAware {
	private Evaluate model = new Evaluate();
	private HttpServletResponse response;
	
	/******************************* 注入 ********************************/
	@Autowired
	private EvaluateService evaluateService;
	@Autowired
	private ProjectService projectService;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private ProfessorService professorService;

	/********************************* 项目操作 ************************************/
	/**
	 * 获取评价的信息
	 * 调用条件：项目的Id：proj_id
	 * @return
	 */
	public String getEvaluate(){
		Project project = projectService.findById(model.getProj_id());
		model = evaluateService.findByProjId(project.getId());
		model.setTitle(project.getConsult().getTitle());
		return SUCCESS;
	}
	
	/**
	 * 专家评价
	 * 调用条件：项目的id：proj_id，专家的id：prof_id
	 * @return
	 */
	public void professorEvaluate() {
		response.setCharacterEncoding("utf-8");
		PrintWriter pw = null;
		
		Professor professor = professorService.findProfessorById(model.getProf_id());
		Evaluate eva=evaluateService.findEvaluateByIdAndProfessorId(model.getId(), professor.getId());
		
		if(eva == null||Evaluate.COMPLETED.equals(eva.getProf_state())){
			try {
				pw = response.getWriter();
				pw.write("fail");
			} catch (IOException e1) {
				
			}
			
		}else {
			eva.setProf_id(professor.getId());
			eva.setProf_grade(model.getProf_grade());
			eva.setProf_text(model.getProf_text());
			eva.setProf_state(Evaluate.COMPLETED);
			
			// 如果专家已经完成评价，更新项目的状态
			if(Evaluate.COMPLETED.equals(eva.getCom_state())){
				this.addPointAndUpdateProject(eva);
			}
			evaluateService.update(eva);
			try {
				pw = response.getWriter();
				pw.write("success");
			} catch (IOException e1) {
				
			}
		}
		pw.flush();
		pw.close();
	}
	
	/**
	 * 都完成了评价，增加积点
	 * @param eva
	 */
	private void addPointAndUpdateProject(Evaluate eva){
		// 完成了用户的评价，更新项目状态
		Project project = projectService.findById(eva.getProj_id());
		project.setCurrent_state(Project.COMPELETED);
		projectService.update(project);
		
		// 增加积点
		String prof_id = eva.getProf_id();
		int prof_grade = eva.getProf_grade();
		Professor professor = professorService.findProfessorById(prof_id);
		// 积分不能超过5，超过表示是 恶意操作
		professor.setPoints(professor.getPoints()+Math.min(prof_grade, 5));
		professorService.update(professor);
		
		String com_id = eva.getCom_id();
		int com_grade = eva.getCom_grade();
		Company company = companyService.findCompanyById(com_id);
		// 积分不能超过5，超过表示是 恶意操作
		company.setPoints(company.getPoints()+Math.min(com_grade, 5));
		companyService.update(company);
	}
	
	/************************************************************************/
	@Override
	public Evaluate getModel() {
		return model;
	}
	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

}
