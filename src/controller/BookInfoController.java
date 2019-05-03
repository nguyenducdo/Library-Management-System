package controller;

import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;

import dao.BookDAO;
import dao.CategoryDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import model.Book;
import model.BookDTO;
import model.Category;

public class BookInfoController implements Initializable{
	
	//Declare DAO
	private final BookDAO bookDAO = new BookDAO();
	private final CategoryDAO categoryDAO = new CategoryDAO();
	
	
	// get Tab Pane & tabs in pane
	@FXML
	private Tab tabBooks,tabCategories;
	@FXML
	private TabPane tabPane;
	
	// get table, columns, data list in tabBooks
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
	
	
	// get table, columns, data list in tabCategories
	@FXML
	private TableView<Category> tbvCategories;
	@FXML
	private TableColumn<Category, Integer> idCategoryCol;
	@FXML
	private TableColumn<Category, String> nameCategoryColumnInCategoriesTab;
	
	private ObservableList<Category> listCategories;
	
	// get comboBox
	@FXML
	private ComboBox<String> cbSearch;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initTbvBookInfo();
		initTbvCategories();
		initCbSearch();
	}

	private void initTbvBookInfo() {
		idBookCol.setCellValueFactory(new PropertyValueFactory<BookDTO,String>("idBook"));
		idIsbnCol.setCellValueFactory(new PropertyValueFactory<BookDTO,String>("idIsbn"));
		nameCol.setCellValueFactory(new PropertyValueFactory<BookDTO,String>("name"));

		authorCol.setCellValueFactory(new PropertyValueFactory<BookDTO,String>("author"));

		namePublisherCol.setCellValueFactory(new PropertyValueFactory<BookDTO,String>("namePublisher"));

		nameCategoryCol.setCellValueFactory(new PropertyValueFactory<BookDTO, String>("nameCategory"));

		publishingYearCol.setCellValueFactory(new PropertyValueFactory<BookDTO,Date>("publishingYear"));

		quantityCol.setCellValueFactory(new PropertyValueFactory<BookDTO,Integer>("quantity"));

		
		listBook = FXCollections.observableArrayList(bookDAO.getAllBookDTO());
		tbvBookInfo.setItems(listBook);
	}
	
	private void initTbvCategories() {
		idCategoryCol.setCellValueFactory(new PropertyValueFactory<Category, Integer>("idCategory"));
		nameCategoryColumnInCategoriesTab.setCellValueFactory(new PropertyValueFactory<Category, String>("nameCategory"));
		
		listCategories = FXCollections.observableArrayList(categoryDAO.getAllCategory());
		tbvCategories.setItems(listCategories);
	}
	
	private void initCbSearch() {
		ObservableList<String> list = FXCollections.observableArrayList(new String[]{"ID","ID-ISBN","Name","Author","Publisher","Category"});
		cbSearch.setItems(list);
	}
	
	
	private void refresh(Tab tab) {
		if(tab == tabBooks) {
			listBook.clear();
			listBook.addAll(bookDAO.getAllBookDTO());
			SelectionModel<String> model = cbSearch.getSelectionModel();
			model.select(null);
		}else if(tab == tabCategories) {
			listCategories.clear();
			listCategories.addAll(categoryDAO.getAllCategory());
		}
		
	}
	
	
	@FXML
	private Button btnCategories;
	@FXML
	private Button btnBook;
	
	public void showTab(ActionEvent evt) {
		SelectionModel<Tab> model = tabPane.getSelectionModel();
		if(evt.getSource() == btnBook) {
			model.select(tabBooks);
		}
		else if(evt.getSource() == btnCategories) model.select(tabCategories);
		
	}
	
	
	@FXML
	private Button btnSearchBooks;
	@FXML
	private TextField tfSearchBooks;
	
	public void searchBookInfo(ActionEvent evt) {
		String key = tfSearchBooks.getText();
		if(key.equals("")) {
			refresh(tabBooks);
			return;
		}
		SelectionModel<String> model = cbSearch.getSelectionModel();
		String selected = model.getSelectedItem();
		if(selected == null) return;
		String column=null;
		if(selected.equalsIgnoreCase("ID")) {
			column = "id_book";
		}else if(selected.equalsIgnoreCase("ID-ISBN")) {
			column = "id_isbn";
		}else if(selected.equalsIgnoreCase("Name")) {
			column = "book.name";
		}else if(selected.equalsIgnoreCase("Author")){
			column = "author";
		}else if(selected.equalsIgnoreCase("Publisher")) {
			column = "publisher.name";
		}else if(selected.equalsIgnoreCase("Category")) {
			column = "category.name";
		}else {
			System.out.println("ComboBox ERROR!!!");
		}
		
		listBook.clear();
		listBook.addAll(bookDAO.searchBy(column,key));
	}
	
	public void keyEvtHandle(KeyEvent evt) {
		
		if(evt.getCode() == KeyCode.BACK_SPACE) {
			if(evt.getSource() == tfSearchBooks) {
				if(tfSearchBooks.getText().equals("")) refresh(tabBooks);
			}else if(evt.getSource() == tfSearchCategories){
				if(tfSearchCategories.getText().equals("")) refresh(tabCategories);
			}
			
		}
	}
	
	@FXML
	private RadioButton radioSearchByID, radioSearchByName;
	@FXML
	private TextField tfSearchCategories;
	public void searchCategories(ActionEvent evt) {
		List<Category> listData = null;
		String key = tfSearchCategories.getText();
		if(key.equals("")) {
			refresh(tabCategories);
			return;
		}
		if(radioSearchByID.isSelected()) {
			try{
				Integer.parseInt(key);
			}catch (NumberFormatException e) {
				// TODO: handle exception
				System.out.println("ID khong hop le");
				return;
			}
			listData = categoryDAO.searchBy("id_category",key);
		}else if(radioSearchByName.isSelected()){
			listData = categoryDAO.searchBy("name",key);
		}
		listCategories.clear();
		listCategories.addAll(listData);
	}
	
}
