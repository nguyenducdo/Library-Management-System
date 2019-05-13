package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import model.Category;

public class CategoryDAO {
	public List<Category> getAllCategory(){
		Connection cnn = DBConnection.open();
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Category> listCategories = null;
		try {
			ps = (PreparedStatement) cnn.prepareStatement("SELECT * FROM category");
			rs = ps.executeQuery();
			listCategories = new ArrayList<>();
			String nameCategory;
			int id_category;
			while(rs.next()) {
				id_category = rs.getInt("id_category");
				nameCategory = rs.getString("name");
				listCategories.add(new Category(id_category, nameCategory));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBConnection.close(rs, ps, cnn);
		}
		
		return listCategories;
	}
	
	public List<Category> searchBy(String column, String keyw){
		if(column == null || keyw == null) return getAllCategory();
		String condition;
		if(column.equals("name")) {
			condition = column + " LIKE '%"+keyw+"%'";
		}else {
			condition = column + " = '"+keyw+"'";
		}
		Connection cnn = DBConnection.open();
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Category> listCategories = null;
		try {
			ps = (PreparedStatement) cnn.prepareStatement("SELECT * FROM category WHERE " + condition);
			rs = ps.executeQuery();
			listCategories = new ArrayList<>();
			String nameCategory;
			int id_category;
			while(rs.next()) {
				id_category = rs.getInt("id_category");
				nameCategory = rs.getString("name");
				listCategories.add(new Category(id_category, nameCategory));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBConnection.close(rs, ps, cnn);
		}
		
		return listCategories;
		
	}
}
