package model;

public class Publisher {
	private int idPublisher;
	private String namePublisher;
	private String address;
	private String email;
	
	public Publisher(int idPublisher, String namePublisher, String address, String email) {
		super();
		this.idPublisher = idPublisher;
		this.namePublisher = namePublisher;
		this.address = address;
		this.email = email;
	}

	public int getIdPublisher() {
		return idPublisher;
	}

	public void setIdPublisher(int idPublisher) {
		this.idPublisher = idPublisher;
	}

	public String getNamePublisher() {
		return namePublisher;
	}

	public void setNamePublisher(String namePublisher) {
		this.namePublisher = namePublisher;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
