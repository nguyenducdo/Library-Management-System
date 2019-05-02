package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

import dao.BookDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import model.Book;
import model.BookDTO;
import model.Category;

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
	
	private ObservableList<BookDTO> listBook;
	private final BookDAO bookDAO = new BookDAO();
	
	public BookDAO getBookDAO() {
		return bookDAO;
	}
	
	@FXML
	private Tab tabBooks,tabCategories;
	@FXML
	private TabPane tabPane;
	
	@FXML
	private Button btnBook;
	@FXML
	private Button btnCategories, btnButton;
	
	@FXML
	private TableView<Category> tbvCategories;
	
	@FXML
	private TableColumn<Category, Integer> idCategoryCol;
	@FXML
	private TableColumn<Category, String> nameCategoryCol2;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		idBookCol.setCellValueFactory(new PropertyValueFactory<BookDTO,String>("idBook"));
		idIsbnCol.setCellValueFactory(new PropertyValueFactory<BookDTO,String>("idIsbn"));
		nameCol.setCellValueFactory(new PropertyValueFactory<BookDTO,String>("name"));

		authorCol.setCellValueFactory(new PropertyValueFactory<BookDTO,String>("author"));

		namePublisherCol.setCellValueFactory(new PropertyValueFactory<BookDTO,String>("namePublisher"));

		nameCategoryCol.setCellValueFactory(new PropertyValueFactory<BookDTO, String>("nameCategory"));

		publishingYearCol.setCellValueFactory(new PropertyValueFactory<BookDTO,Date>("publishingYear"));

		quantityCol.setCellValueFactory(new PropertyValueFactory<BookDTO,Integer>("quantity"));

		
		ObservableList<BookDTO> listBook = FXCollections.observableArrayList(bookDAO.getAllBookDTO());
		tbvBookInfo.setItems(listBook);
		
		idCategoryCol.setCellValueFactory(new PropertyValueFactory<Category, Integer>("idCategory"));
		nameCategoryCol2.setCellValueFactory(new PropertyValueFactory<Category, String>("nameCategory"));
		
		ObservableList<Category> listCategories = FXCollections.observableArrayList(bookDAO.getAllCategory());
		tbvCategories.setItems(listCategories);
		
		}
	
	public void showTab(ActionEvent e) {
		SelectionModel model = tabPane.getSelectionModel();
		if(e.getSource() == btnBook) {
			model.select(tabBooks);
		}
		else if(e.getSource() == btnCategories) model.select(tabCategories);
		
	}
	
}
