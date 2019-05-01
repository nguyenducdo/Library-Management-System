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

public class BookDAO{

	public List<Book> getAllBook() {
		Connection cnn = DBConnection.open();
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Book> listBook = null;
		try {
			ps = (PreparedStatement) cnn.prepareStatement("SELECT * FROM book");
			rs = ps.executeQuery();
			listBook = new ArrayList<>();
			String id_book,id_isbn,name,author;
			int id_category,id_publisher,quantity;
			Date publishing_year;
			while(rs.next()) {
				id_book = rs.getString("id_book");
				id_isbn = rs.getString("id_isbn");
				name = rs.getString("name");
				author = rs.getString("author");
				id_category = rs.getInt("id_category");
				id_publisher = rs.getInt("id_publisher");
				quantity = rs.getInt("quantity");
				publishing_year = rs.getDate("publishing_year");
				listBook.add(new Book(id_book,id_isbn,name,author,id_category,id_publisher,publishing_year,quantity));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBConnection.close(rs, ps, cnn);
		}
		return listBook;
	}
	
	public List<BookDTO> getAllBookDTO() {
		Connection cnn = DBConnection.open();
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<BookDTO> listBookDTO = null;
		try {
			ps = (PreparedStatement) cnn.prepareStatement("SELECT * FROM book,category,publisher WHERE book.id_category = category.id_category AND book.id_publisher = publisher.id_publisher");
			rs = ps.executeQuery();
			listBookDTO = new ArrayList<>();
			String id_book,id_isbn,name,author,namePublisher,nameCategory;
			int id_category,id_publisher,quantity;
			Date publishing_year;
			while(rs.next()) {
				id_book = rs.getString("id_book");
				id_isbn = rs.getString("id_isbn");
				name = rs.getString("book.name");
				author = rs.getString("author");
				id_category = rs.getInt("book.id_category");
				id_publisher = rs.getInt("book.id_publisher");
				namePublisher = rs.getString("publisher.name");
				nameCategory = rs.getString("category.name");
				quantity = rs.getInt("quantity");
				publishing_year = rs.getDate("publishing_year");
				listBookDTO.add(new BookDTO(new Book(id_book,id_isbn,name,author,id_category,id_publisher,publishing_year,quantity), namePublisher,nameCategory));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBConnection.close(rs, ps, cnn);
		}
		
		return listBookDTO;
	}
}
