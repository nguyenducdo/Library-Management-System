package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import model.Publisher;

public class PublisherDAO implements IPublisherDAO{
	public List<Publisher> getAllPublisher(){
		Connection cnn = DBConnection.open();
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Publisher> listPublishers = null;
		try {
			ps = (PreparedStatement) cnn.prepareStatement("SELECT * FROM publisher");
			rs = ps.executeQuery();
			listPublishers = new ArrayList<>();
			String namePublisher,address,email;
			int id_publisher;
			while(rs.next()) {
				id_publisher = rs.getInt("id_publisher");
				namePublisher = rs.getString("name");
				address = rs.getString("address");
				email = rs.getString("email");
				listPublishers.add(new Publisher(id_publisher, namePublisher,address,email));
			}
		} catch (SQLException e) {
			// 
			e.printStackTrace();
		}finally {
			DBConnection.close(rs, ps, cnn);
		}
		
		return listPublishers;
	}
	
	public List<Publisher> searchBy(String column, String keyw){
		if(column == null || keyw == null) return getAllPublisher();
		String condition;
		if(column.equals("name")) {
			condition = column + " LIKE '%"+keyw+"%'";
		}else {
			condition = column + " = '"+keyw+"'";
		}
		Connection cnn = DBConnection.open();
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Publisher> listPublishers = null;
		try {
			ps = (PreparedStatement) cnn.prepareStatement("SELECT * FROM publisher WHERE " + condition);
			rs = ps.executeQuery();
			listPublishers = new ArrayList<>();
			String namePublisher,address,email;
			int id_publisher;
			while(rs.next()) {
				id_publisher = rs.getInt("id_publisher");
				namePublisher = rs.getString("name");
				address = rs.getString("address");
				email = rs.getString("email");
				listPublishers.add(new Publisher(id_publisher, namePublisher,address,email));
			}
		} catch (SQLException e) {
			// 
			e.printStackTrace();
		}finally {
			DBConnection.close(rs, ps, cnn);
		}
		
		return listPublishers;
		
	}
}
