package dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import model.Book;
import model.ClassDTO.BookDTO;

public class BookDAO implements IBookDAO{
	
	@Override
	public List<Book> getAllBook(){
		Connection cnn = DBConnection.open();
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Book> listBook = null;
		try {
			ps = (PreparedStatement) cnn.prepareStatement("SELECT * FROM book");
			rs = ps.executeQuery();
			listBook = new ArrayList<>();
			String id_book,id_isbn,name,author;
			int id_category,id_publisher,quantity, remain;
			Date publishing_year;
			while(rs.next()) {
				id_book = rs.getString("id_book");
				id_isbn = rs.getString("id_isbn");
				name = rs.getString("name");
				author = rs.getString("author");
				id_category = rs.getInt("id_category");
				id_publisher = rs.getInt("id_publisher");
				quantity = rs.getInt("quantity");
				remain = rs.getInt("remain");
				publishing_year = rs.getDate("publishing_year");
				
				listBook.add(new Book(id_book,id_isbn,name,author,id_category,id_publisher,publishing_year,quantity,remain));
			}
		} catch (SQLException e) {
			// 
			System.out.println("ISBN Duplicate");
		}finally {
			DBConnection.close(rs, ps, cnn);
		}
		return listBook;
	}
	
	@Override
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
			int id_category,id_publisher,quantity,remain;
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
				remain = rs.getInt("remain");
				listBookDTO.add(new BookDTO(new Book(id_book,id_isbn,name,author,id_category,id_publisher,publishing_year,quantity,remain), namePublisher,nameCategory));
			}
		} catch (SQLException e) {
			// 
			e.printStackTrace();
		}finally {
			DBConnection.close(rs, ps, cnn);
		}
		
		return listBookDTO;
	}
	
	@Override
	public <T> List<BookDTO> searchBy(String column, T keyw){
		if(column == null || keyw == null) return getAllBookDTO();
		String condition;
		if(keyw instanceof String) {
			condition = column +" LIKE '%"+keyw+"%'";
		}else {
			condition = column + "= '"+keyw+"'";
		}
		Connection cnn = DBConnection.open();
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<BookDTO> listBookDTO = null;
		try {
			String query = "SELECT * FROM book,category,publisher WHERE book.id_category = category.id_category AND book.id_publisher = publisher.id_publisher AND " + condition;
			System.out.println(query);
			ps = (PreparedStatement) cnn.prepareStatement(query);
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
				int remain = rs.getInt("remain");
				listBookDTO.add(new BookDTO(new Book(id_book,id_isbn,name,author,id_category,id_publisher,publishing_year,quantity,remain), namePublisher,nameCategory));
			}
		} catch (SQLException e) {
			// 
			e.printStackTrace();
		}finally {
			DBConnection.close(rs, ps, cnn);
		}
		
		return listBookDTO;
	}
	
	@Override
	public void addBook(Book newBook) {
		Connection cnn = DBConnection.open();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			
			String id_isbn,name,author;
			int id_category,id_publisher,quantity;
			Date publishing_year;
			id_isbn = newBook.getIdIsbn();
			name = newBook.getName();
			author = newBook.getAuthor();
			id_category = newBook.getIdCategory();
			id_publisher = newBook.getIdPublisher();
			quantity = newBook.getQuantity();
			publishing_year = newBook.getPublishingYear();
			String query = "INSERT INTO book VALUES (null,'"+id_isbn+"','"+name+"','"+author+"','"+id_category+"','"+id_publisher+"','"+publishing_year+"','"+quantity+"','"+quantity+"')";
			System.out.println(query);
			ps = (PreparedStatement) cnn.prepareStatement(query);
			ps.executeUpdate();
		} catch (SQLException e) {
			// 
			e.printStackTrace();
		}finally {
			DBConnection.close(rs, ps, cnn);
		}
	}
	
	@Override
	public boolean deleteBook(Book book) {
		Connection cnn = DBConnection.open();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String query = "DELETE FROM book WHERE id_book = '"+book.getIdBook()+"'";
//			System.out.println(query);
			ps = (PreparedStatement) cnn.prepareStatement(query);
			ps.executeUpdate();
		} catch (SQLException e) {
			// 
			
			return false;
		}finally {
			DBConnection.close(rs, ps, cnn);
		}
		return true;
	}

	@Override
	public void modifyBook(Book book) {
		Connection cnn = DBConnection.open();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String query = "UPDATE book SET id_isbn=?, name=?, author=?, id_category=?, id_publisher=?, publishing_year=? WHERE id_book =?";
			System.out.println(query);
			ps = (PreparedStatement) cnn.prepareStatement(query);
			ps.setString(1, book.getIdIsbn());
			ps.setString(2, book.getName());
			ps.setString(3, book.getAuthor());
			ps.setInt(4, book.getIdCategory());
			ps.setInt(5, book.getIdPublisher());
			ps.setDate(6, book.getPublishingYear());
			ps.setString(7, book.getIdBook());
			ps.executeUpdate();
		} catch (SQLException e) {
			// 
			Alert alert = new Alert(AlertType.ERROR,"Error... Cannot modify this book",ButtonType.OK);
			alert.setHeaderText(null);
			alert.showAndWait();
		}finally {
			DBConnection.close(rs, ps, cnn);
		}
	}

	@Override
	public boolean addMore(Book book,int quantity) {
		Connection cnn = DBConnection.open();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = (PreparedStatement) cnn.prepareStatement("UPDATE book SET quantity=quantity+?,remain=remain+? WHERE id_book=?");
			ps.setInt(1, quantity);
			ps.setInt(2, quantity);
			ps.setString(3, book.getIdBook());
			ps.executeUpdate();
		} catch (SQLException e) {
			// 
			e.printStackTrace();
			return false;
		}finally {
			DBConnection.close(rs, ps, cnn);
		}
		return true;
	}

	@Override
	public boolean reduceBook(Book book,int quantity) {
		if(book.getRemain() < quantity) {
			return false;
		}
		Connection cnn = DBConnection.open();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = (PreparedStatement) cnn.prepareStatement("UPDATE book SET quantity=quantity-?,remain=remain-? WHERE id_book=?");
			ps.setInt(1, quantity);
			ps.setInt(2, quantity);
			ps.setString(3, book.getIdBook());
			ps.executeUpdate();
		} catch (SQLException e) {
			// 
			e.printStackTrace();
			return false;
		}finally {
			DBConnection.close(rs, ps, cnn);
		}
		return true;
	}
	
}
