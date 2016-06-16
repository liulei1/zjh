package cn.ustc.domain;

import java.util.Date;

/**
 * 咨询接受方案形成的项目bean
 * @author liu
 *
 */
public class Project {
	// 定义状态常量
	public static String ONGOING = "4";
	public static String EVALUATE = "5";
	public static String COMPELETED = "6";
	
	private String id;
	private String prof_id;	// 专家id
	private String scm_id;	 // 方案id
	private String start_date;	// 项目的开始时间。选择好方案开始
	private String end_date;		// 企业评价后结束
	private String current_state; // 项目进度，4是进行中，5是企业评价，6是专家评价
	private String com_id;	// 企业id 冗余数据
	private Consult consult;	// 与咨询表一对一
	
	private int pageSize; // 每页的数据条数
	private int total; // 数据总条数
	private int pageIndex; // 当前页面号
	private int pageCount; // 页面总数
	
	
	
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
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProf_id() {
		return prof_id;
	}
	public void setProf_id(String prof_id) {
		this.prof_id = prof_id;
	}
	public String getScm_id() {
		return scm_id;
	}
	public void setScm_id(String scm_id) {
		this.scm_id = scm_id;
	}
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	public String getCurrent_state() {
		return current_state;
	}
	public void setCurrent_state(String current_state) {
		this.current_state = current_state;
	}
	public Consult getConsult() {
		return consult;
	}
	public void setConsult(Consult consult) {
		this.consult = consult;
	}
	public String getCom_id() {
		return com_id;
	}
	public void setCom_id(String com_id) {
		this.com_id = com_id;
	}
}
