package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import model.ClassDTO.FavoriteBook;
import model.ClassDTO.StatisticsBorrow;

public class StatisticsDAO implements IStatisticsDAO{
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
			// 
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
				// 
				System.out.println(e.getMessage());
			}finally {
				DBConnection.close(rs, ps, cnn);
			}
			return list;
	}
	
	public List<Integer> getYear(){
		Connection cnn = DBConnection.open();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Integer> list = null;
		try {
			ps = (PreparedStatement) cnn.prepareStatement("select year(borrowing_date) as year " + 
					"from borrow_book " + 
					"group by year(borrowing_date) " + 
					"order by year DESC");
			rs = ps.executeQuery();
			list = new ArrayList<Integer>();
			while(rs.next()) {
				int year = rs.getInt("year");
				list.add(year);
			}
		} catch (SQLException e) {
			// 
			System.out.println(e.getMessage());
		}finally {
			DBConnection.close(rs, ps, cnn);
		}
		return list;
	}
	
	public List<StatisticsBorrow> getBorrowInfoInYear(int year){
		Connection cnn = DBConnection.open();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<StatisticsBorrow> list = null;
		try {
			ps = (PreparedStatement) cnn.prepareStatement("select month(borrowing_date) as month, count(month(borrowing_date)) as total " + 
					"from borrow_book b, detail_bill d " + 
					"where b.id_bill = d.id_bill and year(borrowing_date) = ? " + 
					"group by month(borrowing_date) "
					+ "ORDER BY month ASC");
			ps.setInt(1, year);
			rs = ps.executeQuery();
			list = new ArrayList<StatisticsBorrow>();
			int index = 1;
			while(rs.next()) {
				int month = rs.getInt("month");
				int count = rs.getInt("total");
				if(index != month) {
					while(index != month) {
						list.add(new StatisticsBorrow(0, getNameMonth(index++)));
					}
				}
				list.add(new StatisticsBorrow(count, getNameMonth(month)));
				index++;
			}
			if(index!=13) {
				while(index != 13) {
					list.add(new StatisticsBorrow(0, getNameMonth(index++)));
				}
			}
		} catch (SQLException e) {
			// 
			System.out.println(e.getMessage());
		}finally {
			DBConnection.close(rs, ps, cnn);
		}
		return list;
	}
	
	private String getNameMonth(int month) {
		String nameMonth = "";
		switch (month) {
		case 1:
			nameMonth = "Jan";
			break;
		case 2:
			nameMonth = "Feb";				
			break;
		case 3:
			nameMonth = "Mar";
			break;
		case 4:
			nameMonth = "Apr";
			break;
		case 5:
			nameMonth = "May";
			break;
		case 6:
			nameMonth = "June";
			break;
		case 7:
			nameMonth = "July";
			break;
		case 8:
			nameMonth = "Aug";
			break;
		case 9:
			nameMonth = "Sept";
			break;
		case 10:
			nameMonth = "Oct";
			break;
		case 11:
			nameMonth = "Nov";
			break;
		case 12:
			nameMonth = "Dec";
			break;
		default:
			System.out.println("Error month???");
			break;
		}
		return nameMonth;
	}
}
