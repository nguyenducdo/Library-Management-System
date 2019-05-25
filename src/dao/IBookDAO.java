package dao;

import java.util.List;

import model.Book;
import model.ClassDTO.BookDTO;

public interface IBookDAO {
	List<Book> getAllBook();
	List<BookDTO> getAllBookDTO();
	<T> List<BookDTO> searchBy(String column, T keyw);
	void addBook(Book book);
	boolean deleteBook(Book book);
	void modifyBook(Book book);
	boolean addMore(Book book, int quantity);
	boolean reduceBook(Book book, int quantity);
}
