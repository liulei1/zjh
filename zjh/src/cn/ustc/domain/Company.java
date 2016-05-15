package cn.ustc.domain;

import java.util.HashSet;
import java.util.Set;

public class Company extends User{
	private String real_name;
	private String image;
	private String telephone;
	private String address;
	private String field;//领域
	private String authority;//权限
	private String website;//网址
	private String annotation;//备注
	private String state;
	private String balance; //账户余额
	
	private Set<Consult> consults = new HashSet<Consult>();//
	
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
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
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
