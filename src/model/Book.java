package model;

import java.sql.Date;

public class Book {
	private String idBook;
	private String idIsbn;
	private String name;
	private String author;
	private int idCategory;
	private int idPublisher;
	private Date publishingYear;
	private int quantity;
	private int remain;

	public Book() {

	}
	
	public Book(Book book) {
		this.idBook = book.getIdBook();
		this.idIsbn = book.getIdIsbn();
		this.name = book.getName();
		this.author = book.getAuthor();
		this.idCategory = book.getIdCategory();
		this.idPublisher = book.getIdPublisher();
		this.publishingYear = book.getPublishingYear();
		this.quantity = book.getQuantity();
		this.remain = book.getRemain();
	}

	public Book(String idBook, String idIsbn, String name, String author, int idCategory, int idPublisher,
			Date publishingYear, int quantity, int remain) {
		this.idBook = idBook;
		this.idIsbn = idIsbn;
		this.name = name;
		this.author = author;
		this.idCategory = idCategory;
		this.idPublisher = idPublisher;
		this.publishingYear = publishingYear;
		this.quantity = quantity;
		this.remain = remain;
	}

	public String getIdBook() {
		return idBook;
	}

	public void setIdBook(String idBook) {
		this.idBook = idBook;
	}

	public String getIdIsbn() {
		return idIsbn;
	}

	public void setIdIsbn(String idIsbn) {
		this.idIsbn = idIsbn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getIdCategory() {
		return idCategory;
	}

	public void setIdCategory(int idCategory) {
		this.idCategory = idCategory;
	}

	public int getIdPublisher() {
		return idPublisher;
	}

	public void setIdPublisher(int idPublisher) {
		this.idPublisher = idPublisher;
	}

	public Date getPublishingYear() {
		return publishingYear;
	}

	public void setPublishingYear(Date publishingYear) {
		this.publishingYear = publishingYear;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	

	@Override
	public String toString() {
		return "Book [idBook=" + idBook + ", idIsbn=" + idIsbn + ", name=" + name + ", author=" + author
				+ ", idCategory=" + idCategory + ", idPublisher=" + idPublisher + ", publishingYear=" + publishingYear
				+ ", quantity=" + quantity + "]";
	}

	public int getRemain() {
		return remain;
	}


	public void setRemain(int remain) {
		this.remain = remain;
	}
	
	public Book getBook() {
		return this;
	}
	
}
