package dao;

import java.util.List;

import model.Publisher;

public interface IPublisherDAO {
	List<Publisher> getAllPublisher();
	List<Publisher> searchBy(String column, String keyw);
}
