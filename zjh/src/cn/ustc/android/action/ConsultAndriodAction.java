package cn.ustc.android.action;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
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
	public String consultDetails(){
		String id = model.getId();
		if(id != null){
			model = consultService.findById(id);
		}
		return SUCCESS;
	}
	
	/**
	 * 获取推荐的咨询,领域已做显示修正
	 * @return
	 */
	public String recommendConsult(){
		consults = consultService.getRecommendConsult(5);
		for (Consult c : consults) {
			System.out.println(c.getCategory());
			Vocation vocation = vocationDAO.findVocationById(c.getCategory());
			c.setCategory(vocation.getName());
		}
		return SUCCESS;
	}
	
	/**
	 * 条件查询待接收的咨询
	 * 调用条件：传入查询条件title标题和category分类
	 * @return
	 */
	public String findConsultByCriteria(){
		DetachedCriteria criteria = DetachedCriteria.forClass(Consult.class);
		if(model.getCategory() != null && !"".equals(model.getCategory())){
			criteria.add(Restrictions.eq("category", model.getCategory()));
		}
		if(model.getTitle()!=null && !"".equals(model.getTitle())){
			criteria.add(Restrictions.eq("title", model.getTitle()));
		}
		criteria.add(Restrictions.eq("state", Consult.ALLOW));
		consults = consultService.findConsultsByDetachedCriteria(criteria);
		return SUCCESS;
	}
	
	/****************************成员变量set，get方法*********************************/
	@Override
	public Consult getModel() {
		return model;
	}
	public List<Consult> getConsults() {
		return consults;
	}
	
}
