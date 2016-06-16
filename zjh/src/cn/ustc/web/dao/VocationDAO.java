package cn.ustc.web.dao;

import java.util.List;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.ustc.domain.Vocation;

/**
 * 领域DAO
 * @author liu
 *
 */
public class VocationDAO extends HibernateDaoSupport {

	/**
	 * 查询所有领域
	 * @return
	 */
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
