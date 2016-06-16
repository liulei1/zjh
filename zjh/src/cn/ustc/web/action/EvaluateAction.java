package cn.ustc.web.action;

import java.util.Date;


import org.apache.struts2.ServletActionContext;
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

/**
 * 评价操作action
 * @author liu
 *
 */
public class EvaluateAction extends ActionSupport implements ModelDriven<Evaluate> {
	private Evaluate evaluate = new Evaluate();
	
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
	 * 跳转到发布评价页面
	 * @return
	 */
	public String publishView(){
		Project project = projectService.findById(evaluate.getProj_id());
		evaluate = evaluateService.findByProjId(project.getId());
		evaluate.setTitle(project.getConsult().getTitle());
		return "publishView";
	}
	
	/**
	 * 企业评价
	 * @return
	 */
	public String compEvaluate() {
		Company company = (Company) ServletActionContext.getServletContext().getAttribute("user");
		Evaluate eva = evaluateService.findById(evaluate.getId());
		
		if(Evaluate.COMPLETED.equals(eva.getCom_state())){
			// 已评价
			this.addActionError(GetPropertiesUtil.getPropertiesValueByKey("haveEvaluated"));
			return ERROR;
		}else{
			
			eva.setBegin_date(DateUtils.dateToString(new Date()));
			eva.setCom_id(company.getId());
			eva.setCom_grade(evaluate.getCom_grade());
			eva.setCom_text(evaluate.getCom_text());
			eva.setCom_state(Evaluate.COMPLETED);
			evaluateService.update(eva);
			
			// 如果企业已经完成评价，更新项目的状态
			if(Evaluate.COMPLETED.equals(eva.getProf_state())){
				this.addPointAndUpdateProject(eva);
			}
			
			return "compEvaluateSUCCESS";
		}
	}

	/**
	 * 专家评价
	 * @return
	 */
	public String profEvaluate() {
		
		Professor professor = (Professor) ServletActionContext.getServletContext().getAttribute("user");
		Evaluate eva=evaluateService.findById(evaluate.getId());
		
		if(Evaluate.COMPLETED.equals(eva.getProf_state())){
			// 已评价
			this.addActionError(GetPropertiesUtil.getPropertiesValueByKey("haveEvaluated"));
			return ERROR;
		}else {
			
			eva.setProf_id(professor.getId());
			eva.setProf_grade(evaluate.getProf_grade());
			eva.setProf_text(evaluate.getProf_text());
			eva.setProf_state(Evaluate.COMPLETED);
			
			// 如果专家已经完成评价，更新项目的状态
			if(Evaluate.COMPLETED.equals(eva.getCom_state())){
				this.addPointAndUpdateProject(eva);
			}
			
			evaluateService.update(eva);
			return  "profEvaluateSUCCESS";
		}
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
	
	/**********************************************************/
	@Override
	public Evaluate getModel() {
		return evaluate;
	}
}
