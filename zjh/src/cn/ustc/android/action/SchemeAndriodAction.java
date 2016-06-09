package cn.ustc.android.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.factory.annotation.Autowired;

import cn.ustc.domain.Scheme;
import cn.ustc.web.service.SchemeService;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 方案处理类
 * @author liu
 *
 */
public class SchemeAndriodAction extends ActionSupport implements ModelDriven<Scheme>, ServletResponseAware {
	private Scheme model = new Scheme();
	private HttpServletResponse response;
	@Autowired
	private SchemeService schemeService;
	
	/**
	 * 方案详情
	 * 调用条件：传入方案Id
	 * @return
	 */
	public String getSchemeDetails(){
		model = schemeService.findById(model.getId());
		return SUCCESS;
	}
	
	/**
	 * 发布方案
	 * 调用条件：传入方案信息
	 * @return
	 */
	public void publishScheme(){
		schemeService.publish(model);
		
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
	
	/****************************成员变量set，get方法*********************************/
	@Override
	public Scheme getModel() {
		return model;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
}
