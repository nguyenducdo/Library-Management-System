package controller;

import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import dao.BookDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Book;
import model.BookDTO;

public class BookInfoController implements Initializable{

	@FXML
	private TableView<BookDTO> tbvBookInfo;
	@FXML
	private TableColumn<BookDTO, String> idBookCol;
	@FXML
	private TableColumn<BookDTO, String> idIsbnCol;
	@FXML
	private TableColumn<BookDTO, String> nameCol;
	@FXML
	private TableColumn<BookDTO, String> authorCol;
	@FXML
	private TableColumn<BookDTO, String> namePublisherCol;
	@FXML
	private TableColumn<BookDTO, String> nameCategoryCol;
	@FXML
	private TableColumn<BookDTO, Date> publishingYearCol;
	@FXML
	private TableColumn<BookDTO, Integer> quantityCol;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
//		if(idBookCol == null) {
//			System.out.println("null cmnr");
////			System.exit(0);
//		}
		idBookCol.setCellValueFactory(new PropertyValueFactory<BookDTO,String>("idBook"));
		idBookCol.setMaxWidth(100);
		idIsbnCol.setCellValueFactory(new PropertyValueFactory<BookDTO,String>("idIsbn"));
		idIsbnCol.setMaxWidth(100);
		nameCol.setCellValueFactory(new PropertyValueFactory<BookDTO,String>("name"));
		nameCol.setMaxWidth(200);
		authorCol.setCellValueFactory(new PropertyValueFactory<BookDTO,String>("author"));
		authorCol.setMaxWidth(200);
		namePublisherCol.setCellValueFactory(new PropertyValueFactory<BookDTO,String>("namePublisher"));
		namePublisherCol.setMaxWidth(100);
		nameCategoryCol.setCellValueFactory(new PropertyValueFactory<BookDTO, String>("nameCategory"));
		nameCategoryCol.setMaxWidth(100);
		publishingYearCol.setCellValueFactory(new PropertyValueFactory<BookDTO,Date>("publishingYear"));
		publishingYearCol.setMaxWidth(100);
		quantityCol.setCellValueFactory(new PropertyValueFactory<BookDTO,Integer>("quantity"));
		quantityCol.setMaxWidth(100);
		
//		ObservableList<BookDTO> listBook = FXCollections.observableArrayList(new BookDAO().getAllBookDTO());
		ObservableList<BookDTO> listBook = FXCollections.observableArrayList(new BookDTO(new Book("efwfwe", "dadewf", "fehfe", "fruiehgr", 1, 2, new Date(22222222), 30), "namepublisher", "name category"));
		for(BookDTO x : listBook) {
			System.out.println(x.getNameCateGory());
		}
		tbvBookInfo.setItems(listBook);
//		tbvBookInfo.getColumns().addAll(idBookCol,idIsbnCol,nameCol,authorCol,namePublisherCol,nameCategoryCol,publishingYearCol,quantityCol);
		
		System.out.println(nameCategoryCol.getCellData(1));
		System.out.println(namePublisherCol.getCellData(1));
	}

}
