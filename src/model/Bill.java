package model;

import java.sql.Date;

public class Bill {
	
//	private String id_book;
//	private String book_name;
	private String id_bill;
	private String id_member;
	private String name_member;
	private int id_staff;
	private Date borrowing_date;
//	private Date return_date;
//	private String name_state;
	
	public Bill(String id_bill, String id_member, String name_member,
			int id_staff, Date borrowing_date) {
		super();
		this.id_bill = id_bill;
		this.id_member = id_member;
		this.name_member = name_member;
		this.id_staff = id_staff;
		this.borrowing_date = borrowing_date;
		
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
	 * @return the id_member
	 */
	public String getId_member() {
		return id_member;
	}
	/**
	 * @param id_member the id_member to set
	 */
	public void setId_member(String id_member) {
		this.id_member = id_member;
	}
	/**
	 * @return the name_member
	 */
	public String getName_member() {
		return name_member;
	}
	/**
	 * @param name_member the name_member to set
	 */
	public void setName_member(String name_member) {
		this.name_member = name_member;
	}
	/**
	 * @return the id_staff
	 */
	public int getId_staff() {
		return id_staff;
	}
	/**
	 * @param id_staff the id_staff to set
	 */
	public void setId_staff(int id_staff) {
		this.id_staff = id_staff;
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

}
