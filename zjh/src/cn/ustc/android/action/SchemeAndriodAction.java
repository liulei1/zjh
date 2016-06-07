package cn.ustc.android.action;

import cn.ustc.domain.Project;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 方案处理类
 * @author liu
 *
 */
public class SchemeAndriodAction extends ActionSupport implements ModelDriven<Project> {
	private Project model = new Project();
	
	public String getSchemeDetails(){
		model.getId();
		return SUCCESS;
	}
	
	/****************************成员变量set，get方法*********************************/
	@Override
	public Project getModel() {
		return model;
	}
}
