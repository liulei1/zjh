package cn.ustc.web.action;

import java.util.List;

import cn.ustc.domain.Vocation;
import cn.ustc.web.dao.VocationDAO;
import cn.ustc.web.dao.impl.VocationDAOImpl;
import cn.ustc.web.service.VocationService;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class ListVocation extends ActionSupport{
	private List<Vocation> vocationList;
	public List<Vocation> getVocationList() {
		return vocationList;
	}
	
	public String ListVocation(){
		VocationDAO vocationDAO = new VocationDAOImpl();
		vocationList = vocationDAO.listVocation();
		System.out.println(vocationList);
		return SUCCESS;
	}
}
