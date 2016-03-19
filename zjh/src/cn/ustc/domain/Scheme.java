package cn.ustc.domain;

import java.util.Date;

/**
 * 方案类 -- 专家提供的解决方案
 * @author liu
 *
 */
public class Scheme {
	private String id;
	private Professor professor;
	private String cons_id;	// 方案对应的需求id
	private String details;
	private Date upload_date;
	private String fileName;	// 文档名
	private String filePath;	// 文档的路径
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public Date getUpload_date() {
		return upload_date;
	}
	public void setUpload_date(Date upload_date) {
		this.upload_date = upload_date;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public Professor getProfessor() {
		return professor;
	}
	public void setProfessor(Professor professor) {
		this.professor = professor;
	}
	public String getCons_id() {
		return cons_id;
	}
	public void setCons_id(String cons_id) {
		this.cons_id = cons_id;
	}
	@Override
	public String toString() {
		return "Scheme [id=" + id + ", professor=" + professor + ", cons_id="
				+ cons_id + ", details=" + details + ", upload_date="
				+ upload_date + ", fileName=" + fileName + ", filePath="
				+ filePath + "]";
	}
}
