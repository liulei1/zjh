package cn.ustc.web.dao;

import java.util.List;
import java.util.UUID;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.ustc.domain.User;
import cn.ustc.utils.HibernateUtils;

/**
 * 普通用户DAO
 * @author liu
 *
 */
@SuppressWarnings("all")
public class UserDAO extends HibernateDaoSupport {
	
	/**
	 * 插入普通用户
	 * @param user
	 * @return
	 */
	public int insertUser(User user) {
		this.getHibernateTemplate().save(user);
		return 1;
	}

	/**
	 * 根据id查询普通用户
	 * @param id
	 * @return
	 */
	public User findByUserID(String id) {
		User user = this.getHibernateTemplate().get(User.class, id);
		return user;
	}

	/**
	 * 所有普通用户
	 * @return
	 */
	public List<User> findAll() {
//		List<User> userList = this.getHibernateTemplate().find("FROM User");
		String sql = "Select * From user";
		Session session = this.getSessionFactory().getCurrentSession();
		SQLQuery query = session.createSQLQuery(sql).addEntity(User.class);
		return query.list();
	}

	/**
	 * 根据id 更新普通用户信息
	 * @param user
	 * @return 1(要么抛异常，要么返回1)
	 */
	public int update(User user) {
		this.getHibernateTemplate().update(user);
		return 1;
	}

	/**
	 * 根据id 删除普通用户
	 * @param id
	 */
	public void deleteById(String id) {
		User user = findByUserID(id);
		this.getHibernateTemplate().delete(user);
	}

	/**
	 * 根据用户名查找用户
	 * @param name
	 * @return
	 */
	public List<User> findUserByName(String name) {
		String hql = "from User where name=:name";
		List<User> userList = this.getHibernateTemplate().findByNamedParam(hql, "name", name);
		return userList;
	}

	/**
	 * 条件查询
	 * @param criteria
	 * @return
	 */
	public User findByCriteria(DetachedCriteria criteria) {
		List<User> list = this.getHibernateTemplate().findByCriteria(criteria);
		int max=-1;
		for(int i=0;i<list.size();i++){
			Object obj=list.get(i);
			if(obj!=null){
				max++;
			}
		}
		if(max==-1){
			max=0;
		}
		return list.isEmpty() ? null : list.get(max);
	}

	/**
	 * 根据用户名密码查找用户
	 * @param name
	 * @param password
	 * @return
	 */
	public User findByUserInfo(String name, String password) {
		String hql="from User where name=? and password=?";
		List<User> userList=this.getHibernateTemplate().find(hql, name,password);
		return userList.get(0)==null?null:userList.get(0);
	}
	
}
