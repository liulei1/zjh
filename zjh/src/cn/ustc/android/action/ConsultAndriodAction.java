package cn.ustc.android.action;

import cn.ustc.domain.Project;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 咨询处理类
 * @author liu
 *
 */
public class ConsultAndriodAction extends ActionSupport implements ModelDriven<Project> {
	private Project model = new Project();
	
	public String getConsultDetails(){
		model.getId();
		return SUCCESS;
	}

	
	/****************************成员变量set，get方法*********************************/
	@Override
	public Project getModel() {
		return model;
	}
}
