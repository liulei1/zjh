package cn.ustc.web.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cn.ustc.domain.Vocation;
import cn.ustc.web.dao.VocationDAO;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 发布需求显示 可选所在领域的列表 --ajax
 * @author liu
 *
 */
public class ListVocation extends ActionSupport{
	private List<Vocation> vocationList;
	public List<Vocation> getVocationList() {
		return vocationList;
	}
	
	@Autowired
	private VocationDAO vocationDAO;

	public String list(){
		vocationList = vocationDAO.listVocation();
		return SUCCESS;
	}
}
