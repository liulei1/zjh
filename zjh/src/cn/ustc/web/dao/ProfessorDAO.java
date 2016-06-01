package cn.ustc.web.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.ustc.domain.Professor;
import cn.ustc.utils.HibernateUtils;

/**
 * 专家操作
 * @author liu
 *
 */
public class ProfessorDAO extends HibernateDaoSupport{
	private Professor professor;
	
	@SuppressWarnings("unchecked")
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

	@SuppressWarnings("unchecked")
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

	@SuppressWarnings("unchecked")
	public List<Professor> findProfessorByName(String name) {
		String hql = "from Professor where name=?";
		List<Professor> professorList = this.getHibernateTemplate().find(hql, name);
		return professorList;
	}

	public List<Professor> findProfessorVocation(String cat) {
		String hql = "from Professor where field=?";
//		List<Professor> professorList = this.getHibernateTemplate().findByNamedQuery(hql, cat);
		List<Professor> professorList = this.getHibernateTemplate().find(hql, cat);
		return professorList;
	}

	public List<Professor> findAllUnaudit() {
		String hql="from Professor where state=?";
		List<Professor> professorList=this.getHibernateTemplate().find(hql,"0");
		return professorList;
	}

	public void pass(String id) {
		professor=findByProfessorID(id);
		professor.setState("1");
		this.getHibernateTemplate().update(professor);
	}

	public void refuse(String id) {
		professor=findByProfessorID(id);
		this.getHibernateTemplate().delete(professor);
	}
	
}
