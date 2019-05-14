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
	/**
	 * @return the id_book
	 */
	public String getId_book() {
		return id_book;
	}
	/**
	 * @param id_book the id_book to set
	 */
	public void setId_book(String id_book) {
		this.id_book = id_book;
	}
	/**
	 * @return the book_name
	 */
	public String getBook_name() {
		return book_name;
	}
	/**
	 * @param book_name the book_name to set
	 */
	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}
	/**
	 * @return the id_bill
	 */
	public String getId_bill() {
		return id_bill;
	}
	/**
	 * @param id_bill the id_bill to set
	 */
	public void setId_bill(String id_bill) {
		this.id_bill = id_bill;
	}
	/**
	 * @return the borrowing_date
	 */
	public Date getBorrowing_date() {
		return borrowing_date;
	}
	/**
	 * @param borrowing_date the borrowing_date to set
	 */
	public void setBorrowing_date(Date borrowing_date) {
		this.borrowing_date = borrowing_date;
	}
	/**
	 * @return the return_date
	 */
	public Date getReturn_date() {
		return return_date;
	}
	/**
	 * @param return_date the return_date to set
	 */
	public void setReturn_date(Date return_date) {
		this.return_date = return_date;
	}
	/**
	 * @return the name_state
	 */
	public String getName_state() {
		return name_state;
	}
	/**
	 * @param name_state the name_state to set
	 */
	public void setName_state(String name_state) {
		this.name_state = name_state;
	}
	
}
