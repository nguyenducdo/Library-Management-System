package dao;

import java.util.List;

import javafx.collections.ObservableList;
import model.Bill;
import model.DetailBill;
import model.ClassDTO.BookBill;
import model.ClassDTO.SelectedBook;

public interface IBorrowDAO {
	List<Bill> searchBorrowInfo(String column, String key);
	List<DetailBill> getDetailBill(String idBill, String idBook);
	List<DetailBill> getDetailBill2(String idBill, String idBook);
	void createBill(Bill borrowInfo, ObservableList<SelectedBook> list);
	List<BookBill> getBookBillInfo(String idBill, int state);
	void returnBook(List<DetailBill> listSelectedDBill);
	void reportLostedBooks(List<DetailBill> listSelectedDBill);
	List<String> getAllState();
}
