package dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import model.BorrowingInfo;

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
}
