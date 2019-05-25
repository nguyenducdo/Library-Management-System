package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import model.Staff;

public class StaffDAO implements IStaffDAO{
	public Staff getUser(String user, String pass) {
		Connection cnn = DBConnection.open();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Staff staff = null;
		try {
			System.out.println("SELECT * FROM staff WHERE username = '"+user+"' AND password = '"+pass+"'");
			ps = (PreparedStatement) cnn.prepareStatement("SELECT * FROM staff WHERE username = '"+user+"' AND password = '"+pass+"'");
			rs = ps.executeQuery();
			if(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String address = rs.getString("address");
				String tel = rs.getString("tel");
				String email = rs.getString("email");
				String username = rs.getString("username");
				String password = rs.getString("password");
				int gender = rs.getInt("gender");
				staff = new Staff(id, name,gender, address, tel, email, username, password);
			}else {
			}
		} catch (SQLException e) {
			// 
			e.printStackTrace();
		}finally {
			DBConnection.close(rs, ps, cnn);
		}
		return staff;
	}
	public Staff getUser(int ID) {
		Connection cnn = DBConnection.open();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Staff staff = null;
		try {
			System.out.println("SELECT * FROM staff WHERE id = "+ID);
			ps = (PreparedStatement) cnn.prepareStatement("SELECT * FROM staff WHERE id = "+ID);
			rs = ps.executeQuery();
			if(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String address = rs.getString("address");
				String tel = rs.getString("tel");
				String email = rs.getString("email");
				String username = rs.getString("username");
				String password = rs.getString("password");
				int gender = rs.getInt("gender");
				staff = new Staff(id, name, gender, address, tel, email, username, password);
			}else {
			}
		} catch (SQLException e) {
			// 
			e.printStackTrace();
		}finally {
			DBConnection.close(rs, ps, cnn);
		}
		return staff;
	}
	
	public void modifyStaffInfo(Staff newStaff) {
		Connection cnn = DBConnection.open();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = (PreparedStatement) cnn.prepareStatement("UPDATE staff SET name=?,address=?,tel=?,email=?,username=?,password=?,gender=? WHERE id = ?");
			ps.setString(1, newStaff.getName());
			ps.setString(2, newStaff.getAddress());
			ps.setString(3, newStaff.getTel());
			ps.setString(4, newStaff.getEmail());
			ps.setString(5, newStaff.getUsername());
			ps.setString(6, newStaff.getPassword());
			ps.setInt(7, newStaff.getGender());
			ps.setInt(8, newStaff.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			// 
			e.printStackTrace();
		}finally {
			DBConnection.close(rs, ps, cnn);
		}
	}
}
