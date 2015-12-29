package cn.ustc.domain;

import java.util.Date;

/**
 * 咨询审核类
 * @author liu
 *
 */
public class ConsultCheck {
	private Integer id;
	private Integer cons_id; 		// 咨询id
	private Integer admin_id; 		// 审核人id
	private Date check_datetime; 	// 审核日期
	private String state;			// 审核结果 0：未通过，1：通过
	private String rejectReason;	// 审核失败原因
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCons_id() {
		return cons_id;
	}
	public void setCons_id(Integer cons_id) {
		this.cons_id = cons_id;
	}
	public Integer getAdmin_id() {
		return admin_id;
	}
	public void setAdmin_id(Integer admin_id) {
		this.admin_id = admin_id;
	}
	public Date getCheck_datetime() {
		return check_datetime;
	}
	public void setCheck_datetime(Date check_datetime) {
		this.check_datetime = check_datetime;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getRejectReason() {
		return rejectReason;
	}
	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}
}
