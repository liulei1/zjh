package cn.ustc.web.action;

import java.util.Date;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import cn.ustc.domain.Company;
import cn.ustc.domain.Evaluate;
import cn.ustc.domain.Professor;
import cn.ustc.domain.Project;
import cn.ustc.utils.DateUtils;
import cn.ustc.web.service.EvaluateService;
import cn.ustc.web.service.ProjectService;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class EvaluateAction extends ActionSupport implements ModelDriven<Evaluate> {
	private Evaluate evaluate = new Evaluate();
	@Override
	public Evaluate getModel() {
		return evaluate;
	}
	
	/******************************* 注入 ********************************/
	@Autowired
	private EvaluateService evaluateService;
	@Autowired
	private ProjectService projectService;

	/********************************* 项目操作 ************************************/
	
	public String publishView(){
		Project project = projectService.findById(evaluate.getProj_id());
		evaluate = evaluateService.findByProjId(project.getId());
		evaluate.setTitle(project.getConsult().getTitle());
		return "publishView";
	}
	
	// 企业评价
	public String compEvaluate() {
		Company company = (Company) ServletActionContext.getServletContext().getAttribute("user");
		Evaluate eva = evaluateService.findById(evaluate.getId());
		eva.setBegin_date(DateUtils.dateToString(new Date()));
		eva.setCom_id(company.getId());
		eva.setCom_grade(evaluate.getCom_grade());
		eva.setCom_text(evaluate.getCom_text());
		eva.setState(Evaluate.EVALUATE);
		
		Project project = projectService.findById(eva.getProj_id());
		project.setCurrent_state(Project.PROFESSOREVALUATE);
		
		evaluateService.update(evaluate);
		projectService.update(project);
		return SUCCESS;
		// return "compEvaluateSUCCESS";
	}

	// 专家评价
	public String profEvaluate() {
		
		Professor professor = (Professor) ServletActionContext.getServletContext().getAttribute("user");
		Evaluate eva=evaluateService.findById(evaluate.getId());
		Project project=projectService.findById(eva.getProj_id());
		
		eva.setProf_id(professor.getId());
		eva.setProf_grade(evaluate.getProf_grade());
		eva.setProf_text(evaluate.getProf_text());
		eva.setState(Evaluate.COMPLETED);
		project.setCurrent_state(Project.COMPELETED);
		
		evaluateService.update(eva);
		projectService.update(project);
		return SUCCESS;
	}
}
