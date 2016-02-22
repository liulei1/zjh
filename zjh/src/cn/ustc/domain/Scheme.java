package cn.ustc.domain;

import java.util.Date;

/**
 * 方案类 -- 专家提供的解决方案
 * @author liu
 *
 */
public class Scheme {
	private String id;
	private int cons_id;
	private int prof_id; // 专家id
	private int proj_id; // 项目id
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
	public int getCons_id() {
		return cons_id;
	}
	public void setCons_id(int cons_id) {
		this.cons_id = cons_id;
	}
	public int getProf_id() {
		return prof_id;
	}
	public void setProf_id(int prof_id) {
		this.prof_id = prof_id;
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
	public int getProj_id() {
		return proj_id;
	}
	public void setProj_id(int proj_id) {
		this.proj_id = proj_id;
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
	@Override
	public String toString() {
		return "Scheme [id=" + id + ", cons_id=" + cons_id + ", prof_id="
				+ prof_id + ", proj_id=" + proj_id + ", details=" + details
				+ ", upload_date=" + upload_date + ", fileName=" + fileName
				+ ", filePath=" + filePath + "]";
	}
}
