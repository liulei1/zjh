package cn.ustc.domain;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 需求被专家接受的项目
 * @author liu
 *
 */
public class Project {
	// 定义状态常量
	public static String ONGOING = "4";
	public static String COMPANYEVALUATE = "5";
	public static String PROFESSOREVALUATE = "6";
	public static String COMPELETED = "7";
	
	private int id;
	private String com_id;	// 企业id
	private String prof_id;	// 专家id
	private String cons_id;	// 咨询表id
	private String scm_id;	 // 方案id
	private String title;
	private String fileName;	// 需求文档名
	private String filePath;	// 需求的文档
	private Date start_date;	// 项目的开始时间。选择好方案开始
	private Date end_date;		// 企业评价后结束
	private String current_state; // 项目进度，4是进行中，5是企业评价，6是专家评价
	private BigDecimal cost;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCom_id() {
		return com_id;
	}
	public void setCom_id(String com_id) {
		this.com_id = com_id;
	}
	public String getProf_id() {
		return prof_id;
	}
	public void setProf_id(String prof_id) {
		this.prof_id = prof_id;
	}
	public String getCons_id() {
		return cons_id;
	}
	public void setCons_id(String cons_id) {
		this.cons_id = cons_id;
	}
	public String getScm_id() {
		return scm_id;
	}
	public void setScm_id(String scm_id) {
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
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	@Override
	public String toString() {
		return "Project [id=" + id + ", com_id=" + com_id + ", prof_id="
				+ prof_id + ", cons_id=" + cons_id + ", scm_id=" + scm_id
				+ ", title=" + title + ", fileName=" + fileName + ", filePath="
				+ filePath + ", start_date=" + start_date + ", end_date="
				+ end_date + ", current_state=" + current_state + ", cost="
				+ cost + "]";
	}
}
