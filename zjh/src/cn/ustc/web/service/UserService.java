package cn.ustc.web.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.ustc.domain.User;
import cn.ustc.web.dao.impl.UserDAOImpl;

@Transactional
public class UserService {
	private UserDAOImpl userDAO;
	public void setUserDAO(UserDAOImpl userDAO) {
		this.userDAO = userDAO;
	}

	public boolean insertUser(User user){
		int res = 0;
		res = userDAO.insertUser(user);
		if (res > 0) {
			return true;
		}
		return false;
	}

	public User login(User user) {
		User loginUser = userDAO.findUserByuserNameAndPwd(user.getName(), user.getPassword());
		return loginUser;
	}

	public List<User> findAllUser() {
		List<User> users = userDAO.findAll();
		return users;
	}

	public User findUserById(String id) {
		User user = userDAO.findByUserID(id);
		return user;
	}

	public int update(User user) {
		return userDAO.update(user);
	}

	public void deleteUserById(String id) {
		userDAO.deleteById(id);
	}

	public List<User> findUserByName(String name) {
		return userDAO.findUserByName(name);
	}

}
