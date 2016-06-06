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
	private String title; // 方案标题
	private String cons_id;	// 方案对应的需求id
	private String details;
	private String upload_date;
	private String fileName;	// 文档名
	private String filePath;	// 文档的路径
	
	private int pageSize; // 每页的数据条数
	private int total; // 数据总条数
	private int pageIndex; // 当前页面号
	private int pageCount; // 页面总数
	
	private String consultTitle;// 需求的标题
	
	public String getConsultTitle() {
		return consultTitle;
	}
	public void setConsultTitle(String consultTitle) {
		this.consultTitle = consultTitle;
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
	public String getDetails() {
		return details;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public String getUpload_date() {
		return upload_date;
	}
	public void setUpload_date(String upload_date) {
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
