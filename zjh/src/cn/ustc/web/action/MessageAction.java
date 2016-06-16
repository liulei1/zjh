package cn.ustc.web.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import cn.ustc.domain.Message;
import cn.ustc.domain.User;
import cn.ustc.web.service.MessageService;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 消息操作action
 * @author liu
 *
 */
public class MessageAction extends ActionSupport implements ModelDriven<Message>{
	@Autowired
	private MessageService messageService;
	
	private List<Message> messages;
	public List<Message> getMessages() {
		return messages;
	}

	/******************************* 消息操作  ***************************************/
	/**
	 * 获取未读信息的条数
	 * @return
	 */
	public String getMyUnreadMessageCount(){
		User loginUser = (User) ServletActionContext.getServletContext().getAttribute("user");
		if(loginUser.getId() != null || !"".equals(loginUser.getId())){
			int count = messageService.getUserUnreadMessageCount(loginUser);
			messageModel.setCount(count);
		}
		return SUCCESS;
	}
	
	/**
	 * 查找登录用户的未读信息
	 * @return
	 */
	public String queryMyUnread(){
		User loginUser = (User) ServletActionContext.getServletContext().getAttribute("user");
		messages = messageService.getUserUnreadMessages(loginUser);
		return "queryMyUnreadSUCCESS";
	}
	
	/**
	 * 查找登录用户的历史信息
	 * @return
	 */
	public String queryMyReaded(){
		User loginUser = (User) ServletActionContext.getServletContext().getAttribute("user");
		messages = messageService.getUserReadedMessages(loginUser);
		return "queryMyReadedSUCCESS";
	}
	
	/**
	 * 标记为已读
	 * @return
	 */
	public String haveReaded(){
		String id = messageModel.getId();
		messageService.readMessageById(id);
		return "haveReadedSUCCESS";
	}
	
	/**
	 * 标记为已读
	 * @return
	 */
	public String toUnreadMessageById(){
		messageModel = messageService.findMessageById(messageModel.getId());
		messageModel.setState(Message.UNREAD);
		messageService.update(messageModel);
		return "toUnreadMessageByIdSUCCESS";
	}
	
	/**
	 * 根据id删除
	 * @return
	 */
	public String deleteMessageById(){
		String id = messageModel.getId();
		messageService.deleteMessageById(id);
		return "deleteMessageByIdSUCCESS";
	}

	/*************************************************************/
	private Message messageModel = new Message();
	@Override
	public Message getModel() {
		return messageModel;
	}
}