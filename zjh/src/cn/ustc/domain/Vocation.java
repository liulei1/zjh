package cn.ustc.domain;

/**
 * 行业
 * @author liu
 *
 */
public class Vocation {
	private String id;
	private String name;
	
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
	@Override
	public String toString() {
		return "Vocation [id=" + id + ", name=" + name + "]";
	}
	
}
