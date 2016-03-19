package cn.ustc.web.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import cn.ustc.domain.Message;
import cn.ustc.domain.User;
import cn.ustc.web.service.MessageService;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class MessageAction extends ActionSupport implements ModelDriven<Message>{
	@Autowired
	private MessageService messageService;
	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}
	
	private List<Message> messages;
	public List<Message> getMessages() {
		return messages;
	}

	/******************************* 消息操作  ***************************************/
	
	public String getMyUnreadMessageCount(){
		User loginUser = (User) ServletActionContext.getServletContext().getAttribute("user");
		int count = messageService.getUserUnreadMessageCount(loginUser);
		messageModel.setCount(count);
		return SUCCESS;
	}
	
	public String queryMyUnread(){
		User loginUser = (User) ServletActionContext.getServletContext().getAttribute("user");
		messages = messageService.getUserUnreadMessages(loginUser);
		return "queryMyUnreadSUCCESS";
	}
	
	public String haveReaded(){
		String id = messageModel.getId();
		messageService.readMessageById(id);
		return "haveReadedSUCCESS";
	}

	private Message messageModel = new Message();
	@Override
	public Message getModel() {
		return messageModel;
	}
}