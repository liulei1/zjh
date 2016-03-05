package cn.ustc.domain;

import java.util.HashSet;
import java.util.Set;

public class Professor extends User{
	private String real_name;//真实姓名
	private String image;//头像
	private String telephone;//电话
	private String identity;//身份证号
	private String id_image;//身份证照片
	private String introduction;//个人简介
	private String field;//擅长领域
	private String authority;//权限
	private String achieve;//成就
	private String annotation;//注解
	private String address;
	private String website;

	private Set<Scheme> schemes = new HashSet<Scheme>();
	
	
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
	public String getId_image() {
		return id_image;
	}
	public void setId_image(String id_image) {
		this.id_image = id_image;
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
	public String getAnnotation() {
		return annotation;
	}
	public void setAnnotation(String annotation) {
		this.annotation = annotation;
	}
	public Set<Scheme> getSchemes() {
		return schemes;
	}
	public void setSchemes(Set<Scheme> schemes) {
		this.schemes = schemes;
	}
	@Override
	public String toString() {
		return "Professor [real_name=" + real_name + ", image=" + image
				+ ", telephone=" + telephone + ", identity=" + identity
				+ ", id_image=" + id_image + ", introduction=" + introduction
				+ ", field=" + field + ", authority=" + authority
				+ ", achieve=" + achieve + ", annotation=" + annotation
				+ ", address=" + address + ", website=" + website
				+ ", schemes=" + schemes + "]";
	}
}
