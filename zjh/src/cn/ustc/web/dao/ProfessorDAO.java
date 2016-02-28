package cn.ustc.web.dao;

import java.util.List;

import cn.ustc.domain.Professor;

/**
 * 普通用户模块信息
 * @author liu
 *
 */
public interface ProfessorDAO {
	/**
	 * 根据用户名 密码 查询普通用户
	 * 
	 * @param Professorname
	 * @param pwd
	 * @return 查询到的用户
	 */
	public abstract Professor findProfessorByProfessorNameAndPwd(String Professorname,
			String password);

	/**
	 * 插入普通用户
	 * 
	 * @param Professor
	 * @return
	 */
	public abstract int insertProfessor(Professor Professor);

	/**
	 * 根据id查询普通用户
	 * 
	 * @param id
	 * @return
	 */
	public abstract Professor findByProfessorID(String id);

	/**
	 * 查询所有普通用户
	 * 
	 * @return List<Professor>普通用户集合
	 */
	public abstract List<Professor> findAll();

	/**
	 * 根据id 更新普通用户信息
	 * 
	 * @param Professor
	 * @return 1(要么抛异常，要么返回1)
	 */

	public abstract int update(Professor Professor);

	/**
	 * 根据id 删除普通用户
	 * 
	 * @param id
	 */
	public abstract void deleteById(String id);

	/**
	 * 根据用户名查找用户
	 * @param name
	 * @return
	 */
	public abstract List<Professor> findProfessorByName(String name);
}
