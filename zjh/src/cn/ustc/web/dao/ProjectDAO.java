package cn.ustc.web.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.ustc.domain.Project;
/**
 * 项目DAO
 * @author liu
 *
 */
@SuppressWarnings("unchecked")
public class ProjectDAO extends HibernateDaoSupport{

	/**
	 * 插入一个项目
	 * @param project
	 */
	public void insert(Project project) {
		this.getHibernateTemplate().save(project);
	}
	
	/**
	 * 条件查询
	 * @param criteria
	 * @return
	 */
	public List<Project> findByDetachedCriteria(DetachedCriteria criteria){
		return this.getHibernateTemplate().findByCriteria(criteria);
	}
	
	/**
	 * 分页查询
	 * @param criteria
	 * @param firstResult 首记录下标
	 * @param maxResults 最大记录数
	 * @return
	 */
	public List<Project> findByDetachedCriteria(DetachedCriteria criteria, int firstResult, int maxResults){
		return this.getHibernateTemplate().findByCriteria(criteria,firstResult,maxResults);
	}

	/**
	 * 更新
	 * @param project
	 */
	public void update(Project project){
		this.getHibernateTemplate().update(project);
	}
	
	/**
	 * 根据id查找项目
	 * @param id
	 * @return
	 */
	public Project findById(String id){
		return this.getHibernateTemplate().get(Project.class, id);
	}

	/**
	 * 根据专家id获取项目数
	 * @param pro_id
	 * @return
	 */
	public int getCountByPorfessorID(String pro_id) {
		String hql = "select count(*) from Project as project where project.prof_id =?";
		Long count = (Long) this.getHibernateTemplate().find(hql, pro_id).listIterator().next();
		return count.intValue();
	}
	
	/**
	 * 根据企业id获取项目数
	 * @param com_id
	 * @return
	 */
	public int getCountByCompanyID(String com_id) {
		String hql = "select count(*) from Project as project where project.com_id =?";
		Long count = (Long) this.getHibernateTemplate().find(hql, com_id).listIterator().next();
		return count.intValue();
	}
}
