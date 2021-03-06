package cn.ustc.domain;

/**
 * 用户bean
 * @author liu
 *
 */
public class User {
	/*********************用户类型*******************************/
	 
	public static final String ADMIN = "0";
	public static final String COMPANY = "1";
	public static final String PROFESSOR = "2";
	public static final String USER = "3";
	
	private String id;
	private String name;
	private String password;
	private String repassword;
	private String email;
	private String sex;
	private String usertype; //用户的类型，专家，普通还是企业
	private boolean nameExsit; //用于注册ajax验证是否用户名存在
	private String state;
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public boolean isNameExsit() {
		return nameExsit;
	}
	public void setNameExsit(boolean nameExsit) {
		this.nameExsit = nameExsit;
	}
	public String getRepassword() {
		return repassword;
	}
	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}
	public String getUsertype() {
		return usertype;
	}
	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
//	@Override
//	public String toString() {
//		return "User [id=" + id + ", name=" + name + ", password=" + password
//				+ ", repassword=" + repassword + ", email=" + email + ", sex="
//				+ sex + ", usertype=" + usertype + "]";
//	}
}
