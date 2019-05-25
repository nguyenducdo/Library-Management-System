package dao;

import java.util.List;
import java.util.Map;

import model.ClassDTO.FavoriteBook;
import model.ClassDTO.StatisticsBorrow;

public interface IStatisticsDAO {
	Map<String, Integer> getTotalBookByCategory();
	List<FavoriteBook> getFavoriteBook();
	List<Integer> getYear();
	List<StatisticsBorrow> getBorrowInfoInYear(int year);
}
