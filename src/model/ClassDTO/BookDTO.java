package model.ClassDTO;

import model.Book;

public class BookDTO extends Book{
	private String namePublisher;
	private String nameCategory;
	public BookDTO(Book book, String namePublisher, String nameCategory) {
		super(book);
		this.namePublisher = namePublisher;
		this.nameCategory = nameCategory;
	}
	
	public String getNamePublisher() {
		return namePublisher;
	}
	
	public void setNamePublisher(String name) {
		this.namePublisher = name;
	}
	
	public String getNameCategory() {
		return nameCategory;
	}
	
	public void setNameCategory(String name) {
		this.nameCategory = name;
	}
	
	
}
