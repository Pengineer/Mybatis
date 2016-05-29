package hust.bean;

public class Emp {

	private Integer id;
	private String name;
	private String birth;
	private String phone;
	
	public Emp(){}
	
	public Emp(Integer id, String name, String birth, String phone) {
		this.id = id;
		this.name = name;
		this.birth = birth;
		this.phone = phone;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}
