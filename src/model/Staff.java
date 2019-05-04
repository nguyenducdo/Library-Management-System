package model;

public class Staff {
	private int id;
	private String name;
	private String address;
	private String tel;
	private String email;
	private String username;
	private String password;
	private int gender;
	
	public Staff(int id, String name, String address, String tel, String email, String username, String password,
			int gender) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.tel = tel;
		this.email = email;
		this.username = username;
		this.password = password;
		this.gender = gender;
	}
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	
	
}
