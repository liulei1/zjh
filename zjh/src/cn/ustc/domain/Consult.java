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
	// 定义状态常量
	public static String UNCHECKED = "0";
	public static String ALLOW = "1";
	public static String REJECT = "2";
	public static String COMPLETED = "3";
	
	private String id;
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
	private String com_id;		//公司ID
	
	private String scm_id; // 作为model 在项目成立时接受页面传来的 方案 id
	
	// 分页
	private int pageSize; // 每页的数据条数
	private int total; // 数据总条数
	private int pageIndex; // 当前页面号
	private int pageCount; // 页面总数
	
	private Project project;	// 与项目表一对一
	
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	
	public String getScm_id() {
		return scm_id;
	}
	public void setScm_id(String scm_id) {
		this.scm_id = scm_id;
	}
	public String getCom_id() {
		return com_id;
	}
	public void setCom_id(String com_id) {
		this.com_id = com_id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

}
