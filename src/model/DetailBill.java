package model;

import java.sql.Date;

public class DetailBill {
	private String id_book;
	private String book_name;
	private String id_bill;
	private Date borrowing_date;
	private Date return_date;
	private String name_state;
	public DetailBill(String id_book, String book_name, String id_bill, Date borrowing_date, Date return_date,
			String name_state) {
		super();
		this.id_book = id_book;
		this.book_name = book_name;
		this.id_bill = id_bill;
		this.borrowing_date = borrowing_date;
		this.return_date = return_date;
		this.name_state = name_state;
	}
	
	public String getId_book() {
		return id_book;
	}
	
	public void setId_book(String id_book) {
		this.id_book = id_book;
	}
	
	public String getBook_name() {
		return book_name;
	}
	
	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}
	
	public String getId_bill() {
		return id_bill;
	}
	
	public void setId_bill(String id_bill) {
		this.id_bill = id_bill;
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
	
}
