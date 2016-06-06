package cn.ustc.web.dao;

import java.util.List;
import java.util.UUID;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.ustc.domain.User;
import cn.ustc.utils.HibernateUtils;

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

	public List<User> findAll() {
		List<User> userList = this.getHibernateTemplate().find("FROM User");
		return userList;
	}

	/**
	 * 根据id 更新普通用户信息
	 * @param user
	 * @return 1(要么抛异常，要么返回1)
	 */
	public int update(User user) {
		Session session = this.getSession();
		session.update(user);
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

	public User findByUserInfo(String name, String password) {
		String hql="from User where name=? and password=?";
		List<User> userList=this.getHibernateTemplate().find(hql, name,password);
		return userList.get(0)==null?null:userList.get(0);
	}
	
	
}
