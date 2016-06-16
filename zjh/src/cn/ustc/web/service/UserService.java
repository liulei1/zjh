package cn.ustc.web.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import cn.ustc.domain.User;
import cn.ustc.web.dao.UserDAO;

/**
 * 用户service
 * @author liu
 *
 */
@Transactional
public class UserService {
	@Autowired
	private UserDAO userDAO;

	/**
	 * 插入普通用户
	 * @param user
	 * @return
	 */
	public boolean insertUser(User user){
		int res = 0;
		res = userDAO.insertUser(user);
		if (res > 0) {
			return true;
		}
		return false;
	}

	/**
	 * 普通用户登录
	 * @param user
	 * @return
	 */
	public User login(User user) {
		/*DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
		criteria.add(Restrictions.eq("name", user.getName()));
		criteria.add(Restrictions.eq("password", user.getPassword()));
		User loginUser = userDAO.findByCriteria(criteria);*/
		User loginUser=userDAO.findByUserInfo(user.getName(),user.getPassword());
		return loginUser;
	}

	/**
	 * 查找所有普通用户
	 * @return
	 */
	public List<User> findAllUser() {
		List<User> users = userDAO.findAll();
		return users;
	}

	/**
	 * 根据id查找普通用户
	 * @param id
	 * @return
	 */
	public User findUserById(String id) {
		User user = userDAO.findByUserID(id);
		return user;
	}

	/**
	 * 更新用户信息
	 * @param user
	 * @return
	 */
	public int update(User user) {
		return userDAO.update(user);
	}

	/**
	 * 根据id删除普通用户
	 * @param id
	 */
	public void deleteUserById(String id) {
		userDAO.deleteById(id);
	}

	/**
	 * 根据用户名查找用户
	 * @param name
	 * @return
	 */
	public List<User> findUserByName(String name) {
		return userDAO.findUserByName(name);
	}

}
