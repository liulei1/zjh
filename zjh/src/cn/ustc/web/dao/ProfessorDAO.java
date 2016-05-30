package cn.ustc.web.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.ustc.domain.Professor;
import cn.ustc.utils.HibernateUtils;

/**
 * 专家操作
 * @author liu
 *
 */
@SuppressWarnings("unchecked")
public class ProfessorDAO extends HibernateDaoSupport{
	
	public Professor findProfessorByProfessorNameAndPwd(String professorname, String password) {
		String hql = "from Professor where name=:name and password=:password";
		List<Professor> list = this.getHibernateTemplate().findByNamedParam(hql, new String []{"name","password"}, new Object[]{professorname,password});
		return list.size()==0?null:list.get(0);
	}

	public int insertProfessor(Professor professor) {
		this.getHibernateTemplate().save(professor);
		return 1;
	}

	public Professor findByProfessorID(String id) {
		Professor professor = this.getHibernateTemplate().get(Professor.class, id);
		return professor;
	}

	public List<Professor> findAll() {
		List<Professor> list = this.getHibernateTemplate().find("FROM Professor");
		return list;
	}

	public void update(Professor professor) {
		this.getHibernateTemplate().update(professor);
	}

	public void deleteById(String id) {
		Professor professor = this.getHibernateTemplate().get(Professor.class, id);
		this.getHibernateTemplate().delete(professor);
	}

	public List<Professor> findProfessorByName(String name) {
		String hql = "from Professor where name=?";
		List<Professor> professorList = this.getHibernateTemplate().find(hql, name);
		return professorList;
	}

	public List<Professor> findProfessorVocation(String cat) {
		String hql = "from Professor where field=?";
		List<Professor> professorList = this.getHibernateTemplate().find(hql, cat);
		return professorList;
	}
	
	public List<Professor> findProfessorByCriteria(DetachedCriteria criteria) {
		return this.getHibernateTemplate().findByCriteria(criteria);
	}
	
	public List<Professor> findProfessorByCriteria(DetachedCriteria criteria, int maxSize) {
		return this.getHibernateTemplate().findByCriteria(criteria, 0, maxSize);
	}
}
