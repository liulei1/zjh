package cn.ustc.web.dao;

import java.util.List;

import cn.ustc.domain.User;

/**
 * 普通用户模块信息
 * @author liu
 *
 */
public interface UserDAO {
	/**
	 * 根据用户名 密码 查询普通用户
	 * 
	 * @param username
	 * @param pwd
	 * @return 查询到的用户
	 */
	public abstract User findUserByuserNameAndPwd(String username,
			String password);

	/**
	 * 插入普通用户
	 * 
	 * @param user
	 * @return
	 */
	public abstract int insertUser(User user);

	/**
	 * 根据id查询普通用户
	 * 
	 * @param id
	 * @return
	 */
	public abstract User findByUserID(int id);

	/**
	 * 查询所有普通用户
	 * 
	 * @return List<User>普通用户集合
	 */
	public abstract List<User> findAll();

	/**
	 * 根据id 更新普通用户信息
	 * 
	 * @param user
	 * @return 1(要么抛异常，要么返回1)
	 */

	public abstract int update(User user);

	/**
	 * 根据id 删除普通用户
	 * 
	 * @param id
	 */
	public abstract void deleteById(Integer id);
}
