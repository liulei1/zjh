package cn.ustc.domain;

public class Professor extends User{
	private String real_name;
	private String image;
	private String telephone;
	private String address;
	private String authority;
	private String website;
	private String remark;
	
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
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Override
	public String toString() {
		return "Professor [real_name=" + real_name + ", image=" + image
				+ ", telephone=" + telephone + ", address=" + address
				+ ", authority=" + authority + ", website=" + website
				+ ", remark=" + remark + "]";
	}
}
