package model.ClassDTO;

import java.sql.Date;

import model.Book;

public class SelectedBook extends Book{
		private Date returnDate;
		
		public SelectedBook(Book book, Date returnDate) {
			super(book);
			this.returnDate = returnDate;
		}

		public Date getReturnDate() {
			return returnDate;
		}

	
		public void setReturnDate(Date returnDate) {
			this.returnDate = returnDate;
		}

		@Override
		public String toString() {
			return "SelectedBook [returnDate=" + returnDate + "]  " + super.toString();
		}
		
}
