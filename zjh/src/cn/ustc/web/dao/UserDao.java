package cn.ustc.web.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.ustc.domain.User;
import cn.ustc.utils.JDBCUtils;
import cn.ustc.web.exception.MySQLException;

public class UserDao {
	QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());

	/**
	 * 根据用户名 密码 查询普通用户
	 * @param username
	 * @param pwd
	 * @return 查询到的用户
	 */
	public User findUserByuserNameAndPwd(String username, String password){
		String sql = "select * from user where name = ? and password = ?";
		User logonUser;
		try {
			logonUser = qr.query(sql, new BeanHandler<User>(User.class),username, password);
			return logonUser;
		} catch (SQLException e) {
			throw new MySQLException("findUserByuserNameAndPwd ERROR");
		}
	}

	/**
	 * 插入普通用户
	 * @param user
	 * @return
	 */
	public int insertUser(User user){
		String sql = "insert into user values(null,?,?,?,?)";
		Object[] o = { user.getName(), user.getPassword(),
				user.getEmail(), user.getSex()};
		try {
			int res = qr.update(sql, o);
			return res;
		} catch (SQLException e) {
			throw new MySQLException();
		}
		
	}

	/**
	 * 根据id查询普通用户
	 * @param id
	 * @return
	 */
	public User findByUserID(int id){
		String sql = "select * from user where id = ? ";
		User user;
		try {
			user = qr.query(sql, new BeanHandler<User>(User.class), id);
			return user;
		} catch (SQLException e) {
			 throw new MySQLException(); 
		}
	}

	/**
	 * 查询所有普通用户
	 * @return List<User>普通用户集合
	 */
	public List<User> findAll() {
		String sql = "select * from user";
		List<User> users = null;
		try {
			users =  qr.query(sql, new BeanListHandler<User>(User.class));
		} catch (SQLException e) {
			throw new MySQLException();
		}
		return users;
	}

	/**
	 * 根据id 更新普通用户信息
	 * @param user
	 * @return
	 */
	public int update(User user) {
		String sql = "update user set name=?, password =?, email=?, sex =? where id=?";
		Object[] args = { user.getName(), user.getPassword(), user.getEmail(), user.getSex(), user.getId()};
		int res = 0;
		try {
			System.out.println(sql);
			System.out.println(args);
			res = qr.update(sql, args);
		} catch (SQLException e) {
			throw new MySQLException("更新异常");
		}
		return res;
	}

	/**
	 * 根据id 删除普通用户
	 * @param id
	 */
	public void deleteById(Integer id) {
		String sql = "delete from user where id=?";
		try {
			qr.update(sql, id);
		} catch (SQLException e) {
			throw new MySQLException("删除异常");
		}
	}

}
