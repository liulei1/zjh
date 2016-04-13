package cn.ustc.web.dao.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.ustc.domain.ConsultCheck;
import cn.ustc.utils.HibernateUtils;
import cn.ustc.web.dao.ConsultCheckDAO;
/**
 * 咨询审核
 * @author liu
 *
 */
public class ConsultCheckDAOImpl extends HibernateDaoSupport implements ConsultCheckDAO {

	@Override
	public void insert(ConsultCheck consultCheck) {
		Session session = HibernateUtils.openSession();
		Transaction transaction = session.beginTransaction();
		
		session.save(consultCheck);
		
		transaction.commit();
		session.close();
	}

}
