package dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import controller.LoginController;
import javafx.collections.ObservableList;
import model.BorrowingInfo;
import model.DetailBill;
import model.ClassDTO.BookBill;
import model.ClassDTO.SelectedBook;

public class BorrowDAO {
	public List<BorrowingInfo> searchBorrowInfo(String column, String key){
		Connection cnn = DBConnection.open();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String condition = "";
		if(column!=null && key != null) {
			if(column.equals("id_bill")) {
				condition = "and id_bill = '"+key+"'";
			}else {
				condition = "and member."+column+" LIKE '%"+key+"%'";
			}
		}
		
		List<BorrowingInfo> listBorrow = null;
		try {
			String query = "select * from member,borrow_book where member.id_member = borrow_book.id_member " + condition;
			System.out.println(query);
			ps = (PreparedStatement) cnn.prepareStatement(query);
			rs = ps.executeQuery();
			listBorrow = new ArrayList<BorrowingInfo>();
			while(rs.next()) {
				 String id_bill = rs.getString("id_bill");
				 String id_member = rs.getString("id_member");
				 String name_member = rs.getString("name");
				 int id_staff = rs.getInt("id_staff");
				 Date borrowing_date = rs.getDate("borrowing_date");
				 listBorrow.add(new BorrowingInfo(id_bill, id_member, name_member, id_staff, borrowing_date));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}finally {
			DBConnection.close(rs, ps, cnn);
		}
		return listBorrow;
	}
	
	public List<DetailBill> getDetailBill(String idBill, String idBook){
		Connection cnn = DBConnection.open();
		PreparedStatement ps = null;
		ResultSet rs = null;
		StringBuilder condition = new StringBuilder("");
		if(idBill!=null) {
			condition.append(" and borrow_book.id_bill='"+ idBill+"'");
		}
		if(idBook!=null) {
			condition.append(" and book.id_book='"+idBook+"'");
		}
		List<DetailBill> listDetail = null;
		try {
			String query = "select book.id_book,book.name,borrow_book.id_bill,borrowing_date,return_date,name_state " + 
					"from book,borrow_book,detail_bill,state " + 
					"where book.id_book=detail_bill.id_book " + 
					"and borrow_book.id_bill=detail_bill.id_bill " + 
					"and detail_bill.state=state.id_state" + condition;
//			System.out.println(query);
			ps = (PreparedStatement) cnn.prepareStatement(query);
			rs = ps.executeQuery();
			listDetail = new ArrayList<DetailBill>();
			while(rs.next()) {
				String id_book = rs.getString("book.id_book");
				String book_name = rs.getString("book.name");
				String id_bill = rs.getString("borrow_book.id_bill");
				Date borrowing_date = rs.getDate("borrowing_date");
				Date return_date = rs.getDate("return_date");
				String name_state = rs.getString("name_state");
				listDetail.add(new DetailBill(id_book, book_name, id_bill, borrowing_date, return_date, name_state));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}finally {
			DBConnection.close(rs, ps, cnn);
		}
		return listDetail;
	}
	
	public List<DetailBill> getDetailBill2(String idBill, String idBook){
		Connection cnn = DBConnection.open();
		PreparedStatement ps = null;
		ResultSet rs = null;
		StringBuilder condition = new StringBuilder("");
		if(idBill!=null) {
			condition.append(" and borrow_book.id_bill='"+ idBill+"'");
		}
		if(idBook!=null) {
			condition.append(" and book.id_book='"+idBook+"'");
		}
		List<DetailBill> listDetail = null;
		try {
			String query = "select book.id_book,book.name,borrow_book.id_bill,borrowing_date,return_date,name_state " + 
					"from book,borrow_book,detail_bill,state " + 
					"where book.id_book=detail_bill.id_book " + 
					"and borrow_book.id_bill=detail_bill.id_bill "+
					"and (state.id_state=0 or state.id_state=4) " + 
					"and detail_bill.state=state.id_state" + condition;
//			System.out.println(query);
			ps = (PreparedStatement) cnn.prepareStatement(query);
			rs = ps.executeQuery();
			listDetail = new ArrayList<DetailBill>();
			while(rs.next()) {
				String id_book = rs.getString("book.id_book");
				String book_name = rs.getString("book.name");
				String id_bill = rs.getString("borrow_book.id_bill");
				Date borrowing_date = rs.getDate("borrowing_date");
				Date return_date = rs.getDate("return_date");
				String name_state = rs.getString("name_state");
				listDetail.add(new DetailBill(id_book, book_name, id_bill, borrowing_date, return_date, name_state));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}finally {
			DBConnection.close(rs, ps, cnn);
		}
		return listDetail;
	}
	
	public void createBill(BorrowingInfo borrowInfo, ObservableList<SelectedBook> list) {
		Connection cnn = DBConnection.open();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			String queryInsertBorrowBook = "INSERT INTO borrow_book values (null,?,?,?)";
//			System.out.println(query);
			ps = (PreparedStatement) cnn.prepareStatement(queryInsertBorrowBook);
			ps.setString(1, borrowInfo.getId_member());
			ps.setInt(2, borrowInfo.getId_staff());
			ps.setDate(3, borrowInfo.getBorrowing_date());
			ps.executeUpdate();
			
			ps = (PreparedStatement) cnn.prepareStatement("select id_bill from borrow_book order by id_bill DESC limit 1");
			rs = ps.executeQuery();
			if(!rs.next()) {
				System.out.println("Error get last value");
				return;
			}
			String id_bill = rs.getString("id_bill");
			
			String queryInsertDetailBill = "INSERT INTO detail_bill VALUES (?,?,?,default)";
			ps = (PreparedStatement) cnn.prepareStatement(queryInsertDetailBill);
			
			for (SelectedBook selectedBook : list) {
				System.out.println("fore: " +selectedBook);
				ps.setString(1, id_bill);
				ps.setString(2, selectedBook.getIdBook());
				ps.setDate(3, selectedBook.getReturnDate());
				ps.executeUpdate();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}finally {
			DBConnection.close(rs, ps, cnn);
		}
	}
	
	public List<BookBill> getBookBillInfo(String idBill){
		Connection cnn = DBConnection.open();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<BookBill> listBookBills = null;
		String condition = "";
		if(idBill!=null) condition = "and borrow_book.id_bill = '"+idBill+"'";
		try {
			String query ="select * from book,borrow_book,detail_bill,member,state " + 
					"where book.id_book = detail_bill.id_book " + 
					"and borrow_book.id_bill = detail_bill.id_bill " + 
					"and borrow_book.id_member = member.id_member " +
					"and detail_bill.state = state.id_state "+
					"and (state.id_state=0 or state.id_state=4) " +condition;
			System.out.println(query);
			ps = (PreparedStatement) cnn.prepareStatement(query);
			rs = ps.executeQuery();
			listBookBills = new ArrayList<>();
			while(rs.next()) {
				String id_bill = rs.getString("borrow_book.id_bill");
				String id_book = rs.getString("book.id_book");
				String id_isbn = rs.getString("book.id_isbn");
				String name_book = rs.getString("book.name");
				String id_member = rs.getString("member.id_member");
				String name_member = rs.getString("member.name");
				int id_staff = rs.getInt("borrow_book.id_staff");
				Date borrowing_date = rs.getDate("borrowing_date");
				Date return_date = rs.getDate("return_date");
				String name_state = rs.getString("state.name_state");
				listBookBills.add(new BookBill(id_bill, id_book, id_isbn, name_book, id_member, name_member, id_staff, borrowing_date, return_date, name_state));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}finally {
			DBConnection.close(rs, ps, cnn);
		}
		return listBookBills;
	}
	
	public void returnBook(List<DetailBill> listSelectedDBill) {
		Connection cnn = DBConnection.open();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String query = "INSERT INTO return_book VALUES(?,?,?,?)";
			ps = (PreparedStatement) cnn.prepareStatement(query);
			for (DetailBill detailBill : listSelectedDBill) {
				ps.setString(1, detailBill.getId_bill());
				ps.setString(2, detailBill.getId_book());
				ps.setInt(3, LoginController.ID_STAFF);
				ps.setDate(4, new Date(System.currentTimeMillis()));
				ps.executeUpdate();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}finally {
			DBConnection.close(rs, ps, cnn);
		}
	}
}
