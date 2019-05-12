package model;

public class Member extends Person{
	private String id;
	public Member(String id, String name, int gender, String address, String tel, String email) {
		super(name, gender, address, tel, email);
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
}
