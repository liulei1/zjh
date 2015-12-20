package cn.ustc.web.service;

import java.util.List;

import cn.ustc.domain.User;
import cn.ustc.web.dao.UserDao;

public class UserService {
	private UserDao userDao = new UserDao();

	public boolean insertUser(User user){
		int res = 0;
		res = userDao.insertUser(user);
		if (res > 0) {
			return true;
		}
		return false;
	}

	public User login(User user) {
		User loginUser = userDao.findUserByuserNameAndPwd(user.getName(), user.getPassword());
		return loginUser;
	}

	public List<User> findAllUser() {
		List<User> users = userDao.findAll();
		return users;
	}

	public User findUserById(Integer id) {
		User user = userDao.findByUserID(id);
		return user;
	}

	public int update(User user) {
		return userDao.update(user);
	}

	public void deleteUserById(Integer id) {
		userDao.deleteById(id);
	}

//	public boolean delByUserID(int userID){
//		User user = new User();
//		user.setUserID(userID);
//		int res = 0;
//		res = userDao.delUserByCondition(user);
//
//		if (res > 0) {
//			return true;
//		} else {
//			return false;
//		}
//	}

//	public User findUserByUserID(int userID){
//		User user = null;
//		user = userDao.findByUserID(userID);
//		return user;
//	}
//
//	public String updateUser(User user, boolean isEditUpload) {
//		userDao.update(user, isEditUpload);
//		return null;
//	}

}
