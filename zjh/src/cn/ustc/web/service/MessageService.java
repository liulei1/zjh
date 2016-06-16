package cn.ustc.web.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import cn.ustc.domain.Message;
import cn.ustc.domain.User;
import cn.ustc.utils.DateUtils;
import cn.ustc.web.dao.MessageDAO;
/**
 * 消息service
 * @author liu
 *
 */
@Transactional
public class MessageService {
	@Autowired
	private MessageDAO messageDAO;
	public void setMessageDAO(MessageDAO messageDAO) {
		this.messageDAO = messageDAO;
	}
	
	/**
	 * 获取用户未读的信息条数
	 * @param user
	 * @return
	 */
	public int getUserUnreadMessageCount(User user) {
		List<Message> list = this.getUserUnreadMessages(user);
		return list.size();
	}
	
	/**
	 * 获取用户未读的信息
	 * @param user
	 * @return
	 */
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
	
	/**
	 * 标记信息为已读
	 * @param id 消息的id
	 */
	public void readMessageById(String id) {
		Message message = messageDAO.findById(id);
		message.setState(Message.READED);
		messageDAO.update(message);
	}

	/**
	 * 获取已读信息
	 * @param user
	 * @return
	 */
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
	
	/**
	 * 发消息
	 * @param message
	 */
	public void sendMessage(Message message){
		messageDAO.addMessage(message);
	}
	
	/**
	 * 根据id删除消息
	 * @param id
	 */
	public void deleteMessageById(String id){
		Message message = messageDAO.findById(id);
		messageDAO.deleteMessage(message);
	}

	/**
	 * 根据id查找
	 * @param id
	 * @return
	 */
	public Message findMessageById(String id) {
		return messageDAO.findById(id);
	}

	/**
	 * 更新消息
	 * @param messageModel
	 */
	public void update(Message message) {
		messageDAO.update(message);
	}
}
