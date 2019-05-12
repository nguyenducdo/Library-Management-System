package model;

public class Person {
	private String name;
	private String address;
	private int gender;
	private String tel;
	private String email;
	
	
	public Person(String name, int gender, String address, String tel, String email) {
		super();
		this.name = name;
		this.address = address;
		this.tel = tel;
		this.email = email;
		this.gender = gender;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
