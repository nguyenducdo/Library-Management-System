package dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import model.Book;
import model.BookDTO;
import model.Member;

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
			System.out.println(query);
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
	
}
