package dao;

import java.util.List;

import model.Category;

public interface ICategoryDAO {
	List<Category> getAllCategory();
	List<Category> searchBy(String column, String keyw);
}
