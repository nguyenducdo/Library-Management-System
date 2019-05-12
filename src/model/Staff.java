package model;

public class Staff extends Person{
	private int id;
	private String username;
	private String password;
	
	public Staff(int id, String name, int gender, String address, String tel, String email, String username, String password) {
		super(name,gender,address,tel,email);
		this.id = id;
		this.username = username;
		this.password = password;
	}
	
	
	public final int getId() {
		return id;
	}


	public final void setId(int id) {
		this.id = id;
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

}
