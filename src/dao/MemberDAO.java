package dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import model.Book;
import model.BorrowingInfo;
import model.Member;
import model.ClassDTO.BookDTO;

public class MemberDAO {
	public List<Member> getAllMember() {
		Connection cnn = DBConnection.open();
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Member> listMember = null;
		try {
			ps = (PreparedStatement) cnn.prepareStatement("SELECT * FROM member");
			rs = ps.executeQuery();
			listMember = new ArrayList<>();
			String id_member,name,address,email,tel;
			int gender;
			while(rs.next()) {
				id_member = rs.getString("id_member");
				name = rs.getString("name");
				address = rs.getString("address");
				email = rs.getString("email");
				tel = rs.getString("tel");
				gender = rs.getInt("gender");
				listMember.add(new Member(id_member, name, gender, address, tel, email));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}finally {
			DBConnection.close(rs, ps, cnn);
		}
		return listMember;
	}
	
	public List<Member> searchBy(String column, String key) {
		if(column.isEmpty() || key.isEmpty()) return null;
		Connection cnn = DBConnection.open();
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Member> listMember = null;
		
		try {
			String query = "SELECT * FROM member WHERE "+ column + " LIKE '%"+key+"%'";
			ps = (PreparedStatement) cnn.prepareStatement(query);
			rs = ps.executeQuery();
			listMember = new ArrayList<>();
			String id_member,name,address,email,tel;
			int gender;
			while(rs.next()) {
				id_member = rs.getString("id_member");
				name = rs.getString("name");
				address = rs.getString("address");
				email = rs.getString("email");
				tel = rs.getString("tel");
				gender = rs.getInt("gender");
				listMember.add(new Member(id_member, name, gender, address, tel, email));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}finally {
			DBConnection.close(rs, ps, cnn);
		}
		return listMember;
	}
	
	public void update(Member member) {
		Connection cnn = DBConnection.open();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String query = "UPDATE member SET name=?, gender=?, address=?,email=?,tel=? WHERE id_member=?";
			ps = (PreparedStatement) cnn.prepareStatement(query);
			ps.setString(1, member.getName());
			ps.setInt(2,member.getGender());
			ps.setString(3,member.getAddress());
			ps.setString(4,member.getEmail());
			ps.setString(5,member.getTel());
			ps.setString(6,member.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}finally {
			DBConnection.close(rs, ps, cnn);
		}
	}
	
	public void add(Member member) {
		Connection cnn = DBConnection.open();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String query = "INSERT INTO member VALUES(null,?,?,?,?,?)";
			ps = (PreparedStatement) cnn.prepareStatement(query);
			ps.setString(1, member.getName());
			ps.setInt(2,member.getGender());
			ps.setString(3,member.getAddress());
			ps.setString(4,member.getEmail());
			ps.setString(5,member.getTel());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}finally {
			DBConnection.close(rs, ps, cnn);
		}
	}
	public void delete(Member member) {
		Connection cnn = DBConnection.open();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String query = "DELETE FROM member WHERE id_member = ?";
			ps = (PreparedStatement) cnn.prepareStatement(query);
			ps.setString(1, member.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}finally {
			DBConnection.close(rs, ps, cnn);
		}
	}
	
}


//String condition = "";
//if(column!=null && key != null) condition = "and m."+column+" LIKE '%"+key+"%'";
//
//List<BorrowingInfo> listBorrow = null;
//try {
//	String query = "select b.id_bill, d.id_book, bk.name, b.id_member, m.name, id_staff, borrowing_date, return_date, name_state " + 
//			"from borrow_book b, detail_bill d, member m, book bk, state s " + 
//			"where b.id_member = m.id_member and b.id_bill = d.id_bill " + 
//			"and d.id_book = bk.id_book and d.state = s.id_state " + condition;