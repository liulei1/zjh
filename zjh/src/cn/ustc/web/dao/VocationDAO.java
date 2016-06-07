package cn.ustc.web.dao;

import java.util.List;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.ustc.domain.Vocation;

public class VocationDAO extends HibernateDaoSupport {

	@SuppressWarnings("unchecked")
	public List<Vocation> listVocation() {
		String hql = "from Vocation";
		List<Vocation> list = this.getHibernateTemplate().find(hql);
		return list;
	}
	
	/**
	 * 根据id 查找领域
	 * @param id
	 * @return
	 */
	public Vocation findVocationById(String id){
		return this.getHibernateTemplate().get(Vocation.class, id);
	}

}
