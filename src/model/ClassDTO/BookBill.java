package model.ClassDTO;

import java.sql.Date;

public class BookBill {
	String id_bill;
	String id_book;
	String id_isbn;
	String name_book;
	String id_member;
	String name_member;
	int id_staff;
	Date borrowing_date;
	Date return_date;
	String name_state;
	public BookBill(String id_bill, String id_book, String id_isbn, String name_book, String id_member,
			String name_member, int id_staff, Date borrowing_date, Date return_date, String name_state) {
		super();
		this.id_bill = id_bill;
		this.id_book = id_book;
		this.id_isbn = id_isbn;
		this.name_book = name_book;
		this.id_member = id_member;
		this.name_member = name_member;
		this.id_staff = id_staff;
		this.borrowing_date = borrowing_date;
		this.return_date = return_date;
		this.name_state = name_state;
	}
	
	public String getId_bill() {
		return id_bill;
	}
	
	public void setId_bill(String id_bill) {
		this.id_bill = id_bill;
	}
	
	public String getId_book() {
		return id_book;
	}
	
	public void setId_book(String id_book) {
		this.id_book = id_book;
	}
	
	public String getId_isbn() {
		return id_isbn;
	}
	
	public void setId_isbn(String id_isbn) {
		this.id_isbn = id_isbn;
	}
	
	public String getName_book() {
		return name_book;
	}
	
	public void setName_book(String name_book) {
		this.name_book = name_book;
	}
	
	public String getId_member() {
		return id_member;
	}
	
	public void setId_member(String id_member) {
		this.id_member = id_member;
	}
	
	public String getName_member() {
		return name_member;
	}
	
	public void setName_member(String name_member) {
		this.name_member = name_member;
	}

	
	public Date getBorrowing_date() {
		return borrowing_date;
	}
	
	public void setBorrowing_date(Date borrowing_date) {
		this.borrowing_date = borrowing_date;
	}
	
	public Date getReturn_date() {
		return return_date;
	}
	
	public void setReturn_date(Date return_date) {
		this.return_date = return_date;
	}
	
	public String getName_state() {
		return name_state;
	}
	
	public void setName_state(String name_state) {
		this.name_state = name_state;
	}

	public int getId_staff() {
		return id_staff;
	}

	public void setId_staff(int id_staff) {
		this.id_staff = id_staff;
	}
	
	
	
}
