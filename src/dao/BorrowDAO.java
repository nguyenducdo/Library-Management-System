package dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import model.BorrowingInfo;
import model.DetailBill;

public class BorrowDAO {
	public List<BorrowingInfo> searchBorrowInfo(String column, String key){
		Connection cnn = DBConnection.open();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String condition = "";
		if(column!=null && key != null) condition = "and member."+column+" LIKE '%"+key+"%'";
		
		List<BorrowingInfo> listBorrow = null;
		try {
			String query = "select * from member,borrow_book where member.id_member = borrow_book.id_member " + condition;
//			System.out.println(query);
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
}
