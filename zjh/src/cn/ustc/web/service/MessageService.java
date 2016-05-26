package cn.ustc.web.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import cn.ustc.domain.Message;
import cn.ustc.domain.User;
import cn.ustc.web.dao.MessageDAO;

@Transactional
public class MessageService {
	@Autowired
	private MessageDAO messageDAO;
	public void setMessageDAO(MessageDAO messageDAO) {
		this.messageDAO = messageDAO;
	}
	
	public int getUserUnreadMessageCount(User user) {
		List<Message> list = this.getUserUnreadMessages(user);
		return list.size();
	}
	
	public List<Message> getUserUnreadMessages(User user){
		DetachedCriteria criteria = DetachedCriteria.forClass(Message.class);
		if(user.getId().equals("")){
			//如果 user的id为空（用户注销）则直接返回一个空List
			return new ArrayList<Message>();
		}else{
			criteria.add(Restrictions.eq("recipientId", user.getId()));
			criteria.add(Restrictions.eq("state", Message.UNREAD));
			return messageDAO.findMessageByCriteria(criteria);
		}
	}
	
	public void readMessageById(String id) {
		Message message = messageDAO.findById(id);
		message.setState(Message.READED);
		messageDAO.update(message);
	}

	public List<Message> getUserReadedMessages(User user) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Message.class);
		if(user.getId().equals("")){
			//如果 user的id为空（用户注销）则直接返回一个空List
			return new ArrayList<Message>();
		}else{
			criteria.add(Restrictions.eq("recipientId", user.getId()));
			criteria.add(Restrictions.eq("state", Message.READED));
			return messageDAO.findMessageByCriteria(criteria);
		}
	}
}
