package cn.ustc.android.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cn.ustc.domain.Consult;
import cn.ustc.domain.Vocation;
import cn.ustc.web.dao.VocationDAO;
import cn.ustc.web.service.ConsultService;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 咨询处理类
 * @author liu
 *
 */
public class ConsultAndriodAction extends ActionSupport implements ModelDriven<Consult> {
	private Consult model = new Consult();
	private List<Consult> consults;
	@Autowired
	private ConsultService consultService;
	@Autowired
	private VocationDAO vocationDAO;
	
	/**
	 * 获取咨询的详细信息
	 * 调用条件：传入consult的Id
	 * @return
	 */
	public String getConsultDetails(){
		String id = model.getId();
		model = consultService.findById(id);
		return SUCCESS;
	}
	
	/**
	 * 获取推荐的咨询,领域已做显示修正
	 * @return
	 */
	public String getRecommendConsult(){
		consults = consultService.getRecommendConsult(5);
		for (Consult c : consults) {
			Vocation vocation = vocationDAO.findVocationById(c.getCategory());
			c.setCategory(vocation.getName());
		}
		return SUCCESS;
	}
	
	/****************************成员变量set，get方法*********************************/
	@Override
	public Consult getModel() {
		return model;
	}
}
