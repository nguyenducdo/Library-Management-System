package dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import model.Book;
import model.Staff;

public class StaffDAO {
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
				staff = new Staff(id, name, address, tel, email, username, password, gender);
			}else {
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBConnection.close(rs, ps, cnn);
		}
		return staff;
	}
}
