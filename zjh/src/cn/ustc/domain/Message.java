package cn.ustc.domain;

/**
 * 站内信息
 * @author liu
 *
 */
public class Message {
	public static final int UNREAD=0; // 未读
	public static final int READED=1; // 已读
	public static final int TOCOMPANY = 0; // 发送到企业
	public static final int TOPROFESSOR = 1; // 发送到专家
	
	private String id;
	private String recipientId;	// 收信人
	private int type;	// 类型
	private String sendTime;
	private int state;	// 状态，是否已读
	private String title;
	private String content;
	
	private int count;	// 扩展的属性，用于显示未读条数
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRecipientId() {
		return recipientId;
	}
	public void setRecipientId(String recipientId) {
		this.recipientId = recipientId;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getSendTime() {
		return sendTime;
	}
	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
}
