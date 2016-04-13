package cn.ustc.web.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.ustc.domain.Evaluate;
import cn.ustc.utils.HibernateUtils;

/**
 * 评价操作
 * @author liu
 *
 */
@SuppressWarnings("unchecked")
public class EvaluateDAO extends HibernateDaoSupport{
	/**
	 * 插入
	 * @param project
	 */
	public void insert(Evaluate evaluate) {
		this.getHibernateTemplate().save(evaluate);
	}
	
	/**
	 * 条件查询
	 * @param criteria
	 * @return
	 */
	public List<Evaluate> findByDetachedCriteria(DetachedCriteria criteria){
		return this.getHibernateTemplate().findByCriteria(criteria);
	}

	public Evaluate findById(String id) {
		return this.getHibernateTemplate().get(Evaluate.class, id);
	}

	public void update(Evaluate evaluate) {
		Session session = this.getSession();
		try{
			session.update(evaluate);
		}catch(Exception e){
			System.out.println("出错了，么么哒");
			throw new RuntimeException();
		}finally{
			session.close();
		}
	}

}
