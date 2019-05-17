package model.ClassDTO;

import java.sql.Date;

public class LostedBook extends BookBill{
	private Date report_date;

	public LostedBook(String id_bill, String id_book, String id_isbn, String name_book, String id_member,
			String name_member, int id_staff, Date borrowing_date, Date return_date, String name_state,
			Date report_date) {
		super(id_bill, id_book, id_isbn, name_book, id_member, name_member, id_staff, borrowing_date, return_date,
				name_state);
		this.report_date = report_date;
	}

	public Date getReport_date() {
		return report_date;
	}

	public void setReport_date(Date report_date) {
		this.report_date = report_date;
	}
	
}
