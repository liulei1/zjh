package cn.ustc.android.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletResponseAware;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import cn.ustc.domain.Consult;
import cn.ustc.domain.Professor;
import cn.ustc.domain.Project;
import cn.ustc.web.service.ConsultService;
import cn.ustc.web.service.ProfessorService;
import cn.ustc.web.service.ProjectService;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 安卓端对Professor操作方法类
 * @author liu
 *
 */
public class ProfessorOperateAction extends ActionSupport implements ModelDriven<Professor>, ServletResponseAware{
	private Professor model = new Professor();
	@Autowired
	private ConsultService consultService;
	@Autowired
	private ProjectService projectService;
	@Autowired
	private ProfessorService professorService;
	
	private List<Project> projects;
	private List<Consult> consults;
	private HttpServletResponse response;
	
	/**
	 * 获取专家正在进行的项目
	 * 调用条件：传入专家的id
	 * @return
	 */
	public String getProfessorDoingProject(){
		String professorId = model.getId();
//		String professorId = "12312";
		DetachedCriteria criteria = DetachedCriteria.forClass(Project.class);
		criteria.add(Restrictions.eq("prof_id", professorId));
		criteria.add(Restrictions.eq("current_state", Project.ONGOING));
		projects = projectService.findProjectsByDetachedCriteria(criteria);
		return SUCCESS;
	}
	
	/**
	 * 获取专家已经完成的项目
	 * 调用条件：传入专家的id
	 * @return
	 */
	public String getProfessorCompletedProject(){
		String professorId = model.getId();
//		String professorId = "12312";
		DetachedCriteria criteria = DetachedCriteria.forClass(Project.class);
		criteria.add(Restrictions.eq("prof_id", professorId));
		criteria.add(Restrictions.eq("current_state", Project.COMPELETED));
		projects = projectService.findProjectsByDetachedCriteria(criteria);
		return SUCCESS;
	}
	
	/**
	 * 获取专家提交方案尚未立项的咨询
	 * 调用条件：传入专家的id
	 * @return
	 */
	public String getProfessorApplyConslut(){
		String professorId = model.getId();
		consults = consultService.findProfessorApplyConslut(professorId);
		return SUCCESS;
	}
	
	/**
	 * 获取专家信息
	 * 调用条件：传入专家的id
	 */
	public String getCurrentProfessor(){
		String id = model.getId();
//		String id="12312";
		model = professorService.findProfessorById(id);
		return SUCCESS;
	}
	
	/**
	 * 修改专家密码
	 * 调用条件：传入专家的id
	 */
	public void changePassword(){
		String id = model.getId();
		String password = model.getPassword();
		model = professorService.findProfessorById(id);
		model.setPassword(password);
		professorService.update(model);
		
		response.setCharacterEncoding("utf-8");
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
			pw.write("success");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		pw.flush();
		pw.close();
	}
	
	/**
	 * 更新专家信息
	 * 调用条件：传入专家的id
	 */
	public void updateProfessor(){
		String id = model.getId();
		Professor p = professorService.findProfessorById(id);
		p = this.copyModelNotNullProperty(model, p);
		professorService.update(p);
		
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
			pw.write("success");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		pw.flush();
		pw.close();
	}
	
	/**
	 * 拷贝model 中非空的属性到p中
	 * @param model 待拷贝的对象
	 * @param p 待覆盖的对象
	 * @return p
	 */
	private Professor copyModelNotNullProperty(Professor model, Professor p){
		if(model.getAddress() != null){
			p.setAddress(model.getAddress());
		}
		if(model.getEducation() != null){
			p.setEducation(model.getEducation());
		}
		if(model.getIntroduction() != null){
			p.setIntroduction(model.getIntroduction());
		}
		if(model.getName() != null){
			p.setName(model.getName());
		}
		if(model.getTelephone() != null){
			p.setTelephone(model.getTelephone());
		}
		if(model.getWebsite() != null){
			p.setWebsite(model.getWebsite());
		}
		return p;
	}
	
	/**
	 * 获取专家待评价的项目
	 * 调用条件：传入专家id
	 * @return
	 */
	public String getProfessorEvaluateProject(){
		String id = model.getId();
		projects = projectService.findProfessorEvaluateProject(id);
		return SUCCESS;
	}
	
	/****************************成员变量set，get方法*********************************/
	public List<Consult> getConsults() {
		return consults;
	}
	public List<Project> getProjects() {
		return projects;
	}
	@Override
	public Professor getModel() {
		return model;
	}
	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
}
