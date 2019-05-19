package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import controller.UpdateBookController.ModifyBookController;
import dao.BookDAO;
import dao.CategoryDAO;
import dao.PublisherDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Category;
import model.Publisher;
import model.ClassDTO.BookDTO;

public class BookInfoController implements Initializable, IBookInfoController{
	
	//Declare DAO
	private final BookDAO bookDAO = new BookDAO();
	private final CategoryDAO categoryDAO = new CategoryDAO();
	private final PublisherDAO publisherDAO = new PublisherDAO();
	
	// get Tab Pane & tabs in pane
	@FXML
	private Tab tabBooks,tabCategories,tabPublisher;
	@FXML
	private TabPane tabPane;
	
	@FXML
	private Button btnCategories,btnBook,btnPublisher;
	
	//getter
	public Tab getTabBooks() {
		return tabBooks;
	}
	
	// get table, columns, data listSelectedBook in tabBooks
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
	@FXML
	private TableColumn<BookDTO, Integer> remainingCol;
	@FXML
	private ContextMenu contextBook;
	@FXML
	private MenuItem itemModify,itemDelete,itemAddMore,itemReduceBook;
	private ObservableList<BookDTO> listBook;
	
	
	// get table, columns, data listSelectedBook in tabCategories
	@FXML
	private TableView<Category> tbvCategories;
	@FXML
	private TableColumn<Category, Integer> idCategoryCol;
	@FXML
	private TableColumn<Category, String> nameCategoryColumnInCategoriesTab;
	
	private ObservableList<Category> listCategories;
	
	
	// get table, columns, data listSelectedBook in tabPublisher
	@FXML
	private TableView<Publisher> tbvPublisher;
	@FXML
	private TableColumn<Publisher, Integer> idPublisherCol;
	@FXML
	private TableColumn<Publisher, String> namePublisherColumnInPublisherTab;
	
	private ObservableList<Publisher> listPublishers;

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initTbvBookInfo();
		initTbvCategories();
		initTbvPublisher();
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
		
		remainingCol.setCellValueFactory(new PropertyValueFactory<BookDTO, Integer>("remain"));

		
		listBook = FXCollections.observableArrayList(bookDAO.getAllBookDTO());
		tbvBookInfo.setItems(listBook);
		
		tbvBookInfo.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {

			@Override
			public void handle(ContextMenuEvent event) {
				
				contextBook.show(tbvBookInfo,event.getScreenX(),event.getScreenY());
			}
		});
	}
	
	private void initTbvCategories() {
		idCategoryCol.setCellValueFactory(new PropertyValueFactory<Category, Integer>("idCategory"));
		nameCategoryColumnInCategoriesTab.setCellValueFactory(new PropertyValueFactory<Category, String>("nameCategory"));
		
		listCategories = FXCollections.observableArrayList(categoryDAO.getAllCategory());
		tbvCategories.setItems(listCategories);
	}
	
	private void initTbvPublisher() {
		idPublisherCol.setCellValueFactory(new PropertyValueFactory<Publisher, Integer>("idPublisher"));
		namePublisherColumnInPublisherTab.setCellValueFactory(new PropertyValueFactory<Publisher, String>("namePublisher"));
		listPublishers = FXCollections.observableArrayList(publisherDAO.getAllPublisher());
		tbvPublisher.setItems(listPublishers);
	}
	
	// get comboBox
	@FXML
	private ComboBox<String> cbSearch;	
	private void initCbSearch() {
		ObservableList<String> list = FXCollections.observableArrayList(new String[]{"ID","ID-ISBN","Name","Author","Publisher","Category"});
		cbSearch.setItems(list);
	}
	
	public void refresh(Tab tab) {
		if(tab == tabBooks) {
			listBook.clear();
			listBook.addAll(bookDAO.getAllBookDTO());
			SelectionModel<String> model = cbSearch.getSelectionModel();
			model.select(null);
		}else if(tab == tabCategories) {
			listCategories.clear();
			listCategories.addAll(categoryDAO.getAllCategory());
		}else if(tab == tabPublisher) {
			listPublishers.clear();
			listPublishers.addAll(publisherDAO.getAllPublisher());
			lbNamePublisher.setText("");
			lbAddressPublisher.setText("");
			lbEmailPublisher.setText("");
		}
		
	}
	
	@FXML
	private Button btnBack;
	public void turnBack(ActionEvent evt) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/view/Home.fxml"));
			Stage stage = (Stage)((Node)evt.getSource()).getScene().getWindow();
			stage.setScene(new Scene(root));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	@Override
	public void showTab(ActionEvent evt) {
		SelectionModel<Tab> model = tabPane.getSelectionModel();
		if(evt.getSource() == btnBook) {
			model.select(tabBooks);
		}
		else if(evt.getSource() == btnCategories) {
			model.select(tabCategories);
		}else if(evt.getSource() == btnPublisher) {
			model.select(tabPublisher);
		}
		
	}

	public void keyEvtHandle(KeyEvent evt) {
		
		if(evt.getCode() == KeyCode.BACK_SPACE) {
			if(evt.getSource() == tfSearchBooks) {
				if(tfSearchBooks.getText().equals("")) refresh(tabBooks);
			}else if(evt.getSource() == tfSearchCategories){
				if(tfSearchCategories.getText().equals("")) refresh(tabCategories);
			}else if(evt.getSource() == tfSearchPublisher) {
				if(tfSearchPublisher.getText().equals("")) refresh(tabPublisher);
			}
			
		}
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
	
	
	@FXML
	private RadioButton radioSearchByID, radioSearchByName;
	@FXML
	private TextField tfSearchCategories;
	@Override
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
	
	@FXML
	private TextField tfSearchPublisher;
	public void searchPublisher() {
		String key = tfSearchPublisher.getText();
		if(key.equals("")) {
			refresh(tabPublisher);
			return;
		}
		listPublishers.clear();
		listPublishers.addAll(publisherDAO.searchBy("name", key));
	}
	@FXML
	private Label lbNamePublisher, lbAddressPublisher, lbEmailPublisher;
	public void clickEvtHandle(MouseEvent evt) {
		Publisher selected = tbvPublisher.getSelectionModel().getSelectedItem();
		if(selected == null) {
			System.out.println("Not selected");
			return;
		}
		lbNamePublisher.setText(selected.getNamePublisher());
		lbAddressPublisher.setText(selected.getAddress());
		lbEmailPublisher.setText(selected.getEmail());
	}
	
	public void showAddBookStage(ActionEvent evt) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/view/StageUpdateBook/StageAddBook.fxml"));
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.showAndWait();
			refresh(tabBooks);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void deleteBook(ActionEvent evt) {
		BookDTO book = tbvBookInfo.getSelectionModel().getSelectedItem();
		if(book == null) {
			Alert alert = new Alert(AlertType.WARNING, "Please select a book", ButtonType.OK);
			alert.setHeaderText(null);
			alert.showAndWait();
			return;
		}
			Alert alert = new Alert(AlertType.INFORMATION, "Delete - " + book.getName() + " : " + book.getIdBook() + " ?", ButtonType.YES, ButtonType.NO);
			alert.setHeaderText(null);
			Optional<ButtonType> option = alert.showAndWait();
			if(option.get() == ButtonType.YES) {
				if(!bookDAO.deleteBook(book)) {
					Alert alert2 = new Alert(AlertType.ERROR,"Book already borrowed",ButtonType.OK);
					alert2.setHeaderText(null);
					alert2.showAndWait();
				}else {
					Alert alert2 = new Alert(AlertType.INFORMATION, "Delete book successful", ButtonType.OK);
					alert2.setHeaderText(null);
					alert2.showAndWait();
				}
				refresh(tabBooks);
			}
	}
	
	public void modifyBook(ActionEvent evt) {
		BookDTO book = tbvBookInfo.getSelectionModel().getSelectedItem();
		if(book == null) {
			Alert alert = new Alert(AlertType.WARNING, "Please select a book", ButtonType.OK);
			alert.setHeaderText(null);
			alert.showAndWait();
			return;
		}
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/view/StageUpdateBook/StageModifyBookInfo.fxml"));
		try {
			Parent root = loader.load();
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			ModifyBookController controller = loader.getController();
			
			controller.setBook(book);
			stage.showAndWait();
			refresh(tabBooks);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	@Override
	public void addMore(ActionEvent evt) {
		TextInputDialog dialog = new TextInputDialog();
		 
		dialog.setTitle("Add more book");
		dialog.setHeaderText("Enter quantity:");
		dialog.setContentText("Quantity:");
		 
		Optional<String> result = dialog.showAndWait();
		String input = result.get();
		try {
			int quantity = Integer.parseInt(input);
			if(quantity <= 0) {
				Alert alert = new Alert(AlertType.ERROR,"number invalid",ButtonType.OK);
				alert.setHeaderText(null);
				alert.showAndWait();
				refresh(tabBooks);
				return;
				
			}
			if(!bookDAO.addMore(tbvBookInfo.getSelectionModel().getSelectedItem(), quantity)) {
				Alert alert = new Alert(AlertType.ERROR,"Cannot add more book",ButtonType.OK);
				alert.setHeaderText(null);
				alert.showAndWait();
			}else {
				Alert alert = new Alert(AlertType.INFORMATION,quantity + " books added",ButtonType.OK);
				alert.setHeaderText(null);
				alert.showAndWait();
			}
			
			refresh(tabBooks);
		}catch (NumberFormatException e) {
		
			Alert alert = new Alert(AlertType.ERROR,"please enter number",ButtonType.OK);
			alert.setHeaderText(null);
			alert.showAndWait();
			return;
		}
	}
	@Override
	public void reduceBook() {
		TextInputDialog dialog = new TextInputDialog();
		 
		dialog.setTitle("Reduce book");
		dialog.setHeaderText("Enter quantity:");
		dialog.setContentText("Quantity:");
		 
		Optional<String> result = dialog.showAndWait();
		String input = result.get();
		try {
			int quantity = Integer.parseInt(input);
			if(quantity <= 0) {
				Alert alert = new Alert(AlertType.ERROR,"number invalid",ButtonType.OK);
				alert.setHeaderText(null);
				alert.showAndWait();
				return;
			}
			if(!bookDAO.reduceBook(tbvBookInfo.getSelectionModel().getSelectedItem(), quantity)) {
				Alert alert = new Alert(AlertType.ERROR,"Cannot reduce book",ButtonType.OK);
				alert.setHeaderText(null);
				alert.showAndWait();
			}else {
				Alert alert = new Alert(AlertType.INFORMATION,quantity + " books reduced",ButtonType.OK);
				alert.setHeaderText(null);
				alert.showAndWait();
			}
			
			refresh(tabBooks);
		}catch (NumberFormatException e) {

			Alert alert = new Alert(AlertType.ERROR,"please enter number",ButtonType.OK);
			alert.setHeaderText(null);
			alert.showAndWait();
			return;
		}
	}
}
