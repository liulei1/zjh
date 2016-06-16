package cn.ustc.web.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.ustc.domain.Professor;

/**
 * 专家操作
 * @author liu
 *
 */
@SuppressWarnings("unchecked")
public class ProfessorDAO extends HibernateDaoSupport{
	private Professor professor;
	
	/**
	 * 根据密码和账户名查专家
	 * @param professorname
	 * @param password
	 * @return
	 */
	public Professor findProfessorByProfessorNameAndPwd(String professorname, String password) {
		String hql = "from Professor where name=:name and password=:password";
		List<Professor> list = this.getHibernateTemplate().findByNamedParam(hql, new String []{"name","password"}, new Object[]{professorname,password});
		return list.size()==0?null:list.get(0);
	}

	/**
	 * 插入专家
	 * @param professor
	 * @return
	 */
	public int insertProfessor(Professor professor) {
		this.getHibernateTemplate().save(professor);
		return 1;
	}

	/**
	 * 根据id查找专家
	 * @param id
	 * @return
	 */
	public Professor findByProfessorID(String id) {
		Professor professor = this.getHibernateTemplate().get(Professor.class, id);
		return professor;
	}

	/**
	 * 查询所有专家
	 * @return
	 */
	public List<Professor> findAll() {
		List<Professor> list = this.getHibernateTemplate().find("FROM Professor");
		return list;
	}

	/**
	 * 更新专家信息
	 * @param professor
	 */
	public void update(Professor professor) {
		this.getHibernateTemplate().update(professor);
	}

	/**
	 * 根据id删除专家
	 * @param id
	 */
	public void deleteById(String id) {
		Professor professor = this.getHibernateTemplate().get(Professor.class, id);
		this.getHibernateTemplate().delete(professor);
	}

	/**
	 * 根据用户名查找专家
	 * @param name
	 * @return
	 */
	public List<Professor> findProfessorByName(String name) {
		String hql = "from Professor where name=?";
		List<Professor> professorList = this.getHibernateTemplate().find(hql, name);
		return professorList;
	}

	/**
	 * 根据领域查找专家
	 * @param cat
	 * @return
	 */
	public List<Professor> findProfessorVocation(String cat) {
		String hql = "from Professor where field=?";
		List<Professor> professorList = this.getHibernateTemplate().find(hql, cat);
		return professorList;
	}

	/**
	 * 查询所有认证专家信息
	 * @return
	 */
	public List<Professor> findAllUnaudit() {
		String hql="from Professor where state=?";
		List<Professor> professorList=this.getHibernateTemplate().find(hql,"0");
		return professorList;
	}

	/**
	 * 通过认证
	 * @param id
	 */
	public void pass(String id) {
		professor=findByProfessorID(id);
		professor.setState("1");
		this.getHibernateTemplate().update(professor);
	}

	/**
	 * 拒绝认证
	 * @param id
	 */
	public void refuse(String id) {
		professor=findByProfessorID(id);
		this.getHibernateTemplate().delete(professor);
	}
	
	/**
	 * 条件查询专家
	 * @param criteria
	 * @return
	 */
	public List<Professor> findProfessorByCriteria(DetachedCriteria criteria) {
		return this.getHibernateTemplate().findByCriteria(criteria);
	}
	
	/**
	 * 分页条件查询
	 * @param criteria
	 * @param maxSize
	 * @return
	 */
	public List<Professor> findProfessorByCriteria(DetachedCriteria criteria, int maxSize) {
		return this.getHibernateTemplate().findByCriteria(criteria, 0, maxSize);
	}
	
	/**
	 * 查询专家总数
	 * @return
	 */
	public int getProfessorCount(){
		String hql = "select count(*) from Professor";
		Long count = (Long)this.getHibernateTemplate().find(hql).listIterator().next();
		return count.intValue();
	}
	
	/**
	 * 条件查询
	 * @param criteria 条件
	 * @param firstResult 起始记录下标
	 * @param maxResults 最大记录数
	 * @return
	 */
	public List<Professor> findByDetachedCriteria(DetachedCriteria criteria, int firstResult, int maxResults){
		List<Professor> list = this.getHibernateTemplate().findByCriteria(criteria,firstResult,maxResults);
		return list;
	}
}
