package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import model.Member;
import model.ClassDTO.FavoriteBook;

public class StatisticsDAO {
	public Map<String, Integer> getTotalBookByCategory(){
		Connection cnn = DBConnection.open();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Map<String, Integer> map = null;
		try {
			ps = (PreparedStatement) cnn.prepareStatement("SELECT * FROM statistics_category ORDER BY total DESC");
			rs = ps.executeQuery();
			map = new LinkedHashMap<String, Integer>();
			while(rs.next()) {
				map.put(rs.getString("name"), rs.getInt("total"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}finally {
			DBConnection.close(rs, ps, cnn);
		}
		return map;
	}
	
	public List<FavoriteBook> getFavoriteBook(){
			Connection cnn = DBConnection.open();
			PreparedStatement ps = null;
			ResultSet rs = null;
			List<FavoriteBook> list = null;
			try {
				ps = (PreparedStatement) cnn.prepareStatement("SELECT * FROM favorite_book");
				rs = ps.executeQuery();
				list = new ArrayList<FavoriteBook>();
				while(rs.next()) {
					String name = rs.getString("name");
					String author = rs.getString("author");
					int count = rs.getInt("total");
					list.add(new FavoriteBook(name, author, count));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}finally {
				DBConnection.close(rs, ps, cnn);
			}
			return list;
	}
}
