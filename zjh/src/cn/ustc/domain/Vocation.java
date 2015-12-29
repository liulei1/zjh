package cn.ustc.domain;

/**
 * 行业
 * @author liu
 *
 */
public class Vocation {
	private int id;
	private String name;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
