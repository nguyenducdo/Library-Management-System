package model;

import java.sql.Date;

public class DetailBill {
	private String idBill;
	private String idBook;
	private Date returnDate;
	private int state;
	public DetailBill(String idBill, String idBook, Date returnDate, int state) {
		super();
		this.idBill = idBill;
		this.idBook = idBook;
		this.returnDate = returnDate;
		this.state = state;
	}
	/**
	 * @return the idBill
	 */
	public String getIdBill() {
		return idBill;
	}
	/**
	 * @param idBill the idBill to set
	 */
	public void setIdBill(String idBill) {
		this.idBill = idBill;
	}
	/**
	 * @return the idBook
	 */
	public String getIdBook() {
		return idBook;
	}
	/**
	 * @param idBook the idBook to set
	 */
	public void setIdBook(String idBook) {
		this.idBook = idBook;
	}
	/**
	 * @return the returnDate
	 */
	public Date getReturnDate() {
		return returnDate;
	}
	/**
	 * @param returnDate the returnDate to set
	 */
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
	/**
	 * @return the state
	 */
	public int getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(int state) {
		this.state = state;
	}
	
	
}
