package cn.ustc.web.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.ustc.domain.Message;

/**
 * 消息操作
 * @author liu
 *
 */
@SuppressWarnings("unchecked")
public class MessageDAO extends HibernateDaoSupport {
	/**
	 * 条件查询
	 * @param criteria
	 * @return
	 */
	public List<Message> findMessageByCriteria(DetachedCriteria criteria){
		return this.getHibernateTemplate().findByCriteria(criteria);
	}
	
	/**
	 * 更新操作
	 * @param message
	 */
	public void update(Message message){
		this.getHibernateTemplate().update(message);
	};
	
	/**
	 * 根据id 查找
	 * @param id
	 * @return
	 */
	public Message findById(String id){
		return this.getHibernateTemplate().get(Message.class, id);
	}
}
