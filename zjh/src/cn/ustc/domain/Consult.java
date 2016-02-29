package cn.ustc.domain;

import java.math.BigDecimal;
import java.util.Date;
/*
	id	企业id	integer(10)
	state	当前状态	varchar(20)
	title	咨询标题	varchar(20)
	details	咨询详情	txt
	budget	预算金额	double
	release_date	发布日期	datetime
	deadline	截止日期	datetime
	checked_id	平台审核记录	varchar(20)
	category	分类	int
	document	文档路径	varchar(50)
	annotation	备注	varchar(100)
 */
/**
 * 咨询bean， 审核后可以变成项目project
 * @author liu
 *
 */
public class Consult {
	private Integer id;
	private String state; 		// 状态，初始为0，审核通过后为1，失败为2
	private String title; 		// 标题
	private String details; 	// 咨询描述
	private BigDecimal budget;	// 酬金
	private Date release_date; 	// 发布日期
	private Date deadline;		// 咨询失效日期
	private String checked_id; 	// 关联审核表
	private String category; 	// 领域,分类
	private String fileName;	// 文档名
	private String filePath;	// 文档的路径
	private String remark;		// 备注
	private String cons_id;		//公司ID
	
	public String getCons_id() {
		return cons_id;
	}
	public void setCons_id(String cons_id) {
		this.cons_id = cons_id;
	}

	private String rejectReason;// 接受审核的拒绝信息
	public String getRejectReason() {
		return rejectReason;
	}
	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}
	
	public Consult() {
		super();
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public BigDecimal getBudget() {
		return budget;
	}

	public void setBudget(BigDecimal budget) {
		this.budget = budget;
	}

	public Date getRelease_date() {
		return release_date;
	}

	public void setRelease_date(Date release_date) {
		this.release_date = release_date;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public String getChecked_id() {
		return checked_id;
	}

	public void setChecked_id(String checked_id) {
		this.checked_id = checked_id;
	}

	public String getCategory() {
		return category;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}
	
	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "Consult [id=" + id + ", state=" + state + ", title=" + title
				+ ", details=" + details + ", budget=" + budget
				+ ", release_date=" + release_date + ", deadline=" + deadline
				+ ", checked_id=" + checked_id + ", category=" + category
				+ ", fileName=" + fileName + ", filePath=" + filePath
				+ ", remark=" + remark + "]";
	}

}
