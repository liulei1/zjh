package cn.ustc.domain;

import java.math.BigDecimal;
import java.util.Date;

//	    	 id id		integer(10)	是	否	否
// 	    cons_id	咨询表id	integer(10)	否	是	否
//		 scm_id	方案Id	integer(10)	否	是	否
//   	  title	合约名称	varchar(50)	否	否	否
//	 start_date	成立日期	datetime	否	否	否
//	   end_date	结束日期	datetime	否	否	否
//current_state	进度		varchar(20)	否	否	否
//		   cost	应付金额	double	否	否	否
/**
 * 需求被专家接受的项目
 * @author liu
 *
 */
public class Project {
	private int id;
	private int cons_id; // 咨询表id
	private int scm_id;	 // 方案id
	private String title;
	private Date start_date;
	private Date end_date;
	private String current_state; // 项目的状态，3是进行中，4是企业评价，5是专家评价
	private BigDecimal cost;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCons_id() {
		return cons_id;
	}
	public void setCons_id(int cons_id) {
		this.cons_id = cons_id;
	}
	public int getScm_id() {
		return scm_id;
	}
	public void setScm_id(int scm_id) {
		this.scm_id = scm_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getStart_date() {
		return start_date;
	}
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}
	public Date getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	public String getCurrent_state() {
		return current_state;
	}
	public void setCurrent_state(String current_state) {
		this.current_state = current_state;
	}
	
	public BigDecimal getCost() {
		return cost;
	}
	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}
	@Override
	public String toString() {
		return "Project [id=" + id + ", cons_id=" + cons_id + ", scm_id="
				+ scm_id + ", title=" + title + ", start_date=" + start_date
				+ ", end_date=" + end_date + ", current_state=" + current_state
				+ ", cost=" + cost + "]";
	}
}
