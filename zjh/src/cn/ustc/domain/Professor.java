package cn.ustc.domain;

import java.util.HashSet;
import java.util.Set;

public class Professor extends User{
	private String real_name;//真实姓名
	private String image;//头像
	private String telephone;//电话
	private String identity;//身份证号
	private String introduction;//个人简介
	private String field;//擅长领域
	private String authority;//权限
	private String achieve;//成就
	private String address;
	private String website;
	private String balance; // 余额
	private String education; // 学历
	private Integer points;	// 积点，完成项目获得

	private Set<Scheme> schemes = new HashSet<Scheme>();
	
	private String newPassword;//新密码
	
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public Integer getPoints() {
		return points;
	}
	public void setPoints(Integer points) {
		this.points = points;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getReal_name() {
		return real_name;
	}
	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getIdentity() {
		return identity;
	}
	public void setIdentity(String identity) {
		this.identity = identity;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	public String getAchieve() {
		return achieve;
	}
	public void setAchieve(String achieve) {
		this.achieve = achieve;
	}
	public Set<Scheme> getSchemes() {
		return schemes;
	}
	public void setSchemes(Set<Scheme> schemes) {
		this.schemes = schemes;
	}
}
