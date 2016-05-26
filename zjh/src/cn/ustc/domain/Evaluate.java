package cn.ustc.domain;

/**
 * 项目评价
 * @author liu
 *
 */
public class Evaluate {
	public static final String UNCOMPLETED = "0";
	public static final String COMPLETED = "1";
	
	private String id;
	private String proj_id;
	private String com_id;
	private String prof_id;
	private String begin_date;
	private int prof_grade;	// 专家得分
	private String prof_text;
	private int com_grade;	// 企业得分
	private String com_text;
	private String com_state; // 企业评价状态
	private String prof_state; // 专家评价状态
	
	private String title; // 项目标题
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public String getProj_id() {
		return proj_id;
	}
	public void setProj_id(String proj_id) {
		this.proj_id = proj_id;
	}
	public String getCom_id() {
		return com_id;
	}
	public void setCom_id(String com_id) {
		this.com_id = com_id;
	}
	public String getBegin_date() {
		return begin_date;
	}
	public void setBegin_date(String begin_date) {
		this.begin_date = begin_date;
	}
	public int getProf_grade() {
		return prof_grade;
	}
	public void setProf_grade(int prof_grade) {
		this.prof_grade = prof_grade;
	}
	public String getProf_text() {
		return prof_text;
	}
	public void setProf_text(String prof_text) {
		this.prof_text = prof_text;
	}
	public int getCom_grade() {
		return com_grade;
	}
	public void setCom_grade(int com_grade) {
		this.com_grade = com_grade;
	}
	public String getCom_text() {
		return com_text;
	}
	public void setCom_text(String com_text) {
		this.com_text = com_text;
	}
	public String getCom_state() {
		return com_state;
	}
	public void setCom_state(String com_state) {
		this.com_state = com_state;
	}
	public String getProf_state() {
		return prof_state;
	}
	public void setProf_state(String prof_state) {
		this.prof_state = prof_state;
	}
	
}
