package cn.ustc.domain;

import java.util.HashSet;
import java.util.Set;

public class Company extends User{
	/****************权限****************/
	public static final String AUTHORITY_COMMON = "0"; // 普通权限
	public static final String AUTHORITY_VIP = "1";	// VIP权限
	/****************权限对应可发布的最大条数****************/
	public static final int AUTHORITY_COMMON_COUNT = 2; // 普通权限
	public static final int AUTHORITY_VIP_COUNT = 5;	// VIP权限
	
	
	private String real_name;
	private String image;
	private String telephone;
	private String address;
	private String field;//领域
	private String authority;//权限
	private String website;//网址
	private String annotation;//备注
	private String balance; //账户余额
	private Integer points; //积点，通过评价获取
	private String vipEndTime; // 会员结束时间
	
	private Set<Consult> consults = new HashSet<Consult>();//
	private String newPassword;// 新密码
	
	private String vipBalance; //充值会员的价格
	private String effectiveMonth; // 有效月份
	
	public String getEffectiveMonth() {
		return effectiveMonth;
	}
	public void setEffectiveMonth(String effectiveMonth) {
		this.effectiveMonth = effectiveMonth;
	}
	public String getVipBalance() {
		return vipBalance;
	}
	public void setVipBalance(String vipBalance) {
		this.vipBalance = vipBalance;
	}
	public String getVipEndTime() {
		return vipEndTime;
	}
	public void setVipEndTime(String vipEndTime) {
		this.vipEndTime = vipEndTime;
	}
	public Integer getPoints() {
		return points;
	}
	public void setPoints(Integer points) {
		this.points = points;
	}
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
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public Set<Consult> getConsults() {
		return consults;
	}
	public void setConsults(Set<Consult> consults) {
		this.consults = consults;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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

	public String getAnnotation() {
		return annotation;
	}
	public void setAnnotation(String annotation) {
		this.annotation = annotation;
	}
	
}
