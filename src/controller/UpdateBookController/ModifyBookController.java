package controller.UpdateBookController;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import controller.BookInfoController;
import dao.BookDAO;
import dao.CategoryDAO;
import dao.PublisherDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.scene.control.ButtonType;
import model.Book;
import model.Category;
import model.Publisher;
import model.ClassDTO.BookDTO;

public class ModifyBookController implements Initializable{
	@FXML
	private TextField tfISBN, tfName, tfAuthor, tfQuantity;
	@FXML
	private ComboBox<String> cbCategory, cbPublisher;
	@FXML
	private DatePicker datePicker;
	
	private List<Category> listCategory;
	private List<Publisher> listPublisher;
	private ObservableList<String> nameCategories;
	private ObservableList<String> namePublisher;
	private final CategoryDAO categoryDAO = new CategoryDAO();
	private final PublisherDAO publisherDAO = new PublisherDAO();
	private final BookDAO bookDAO = new BookDAO();
	private Book modifiedBook;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		listCategory = categoryDAO.getAllCategory();
		listPublisher = publisherDAO.getAllPublisher();
		
		nameCategories = FXCollections.observableArrayList();
		namePublisher = FXCollections.observableArrayList();
		
		for(Category x : listCategory) {
			nameCategories.add(x.getNameCategory());
		}
		
		for(Publisher x : listPublisher) {
			namePublisher.add(x.getNamePublisher());
		}
		
		cbCategory.setItems(nameCategories);
		cbPublisher.setItems(namePublisher);
		
	}
	
	public void setBook(BookDTO modifiedBook) {
		this.modifiedBook = modifiedBook;
		tfISBN.setText(modifiedBook.getIdIsbn());
		tfName.setText(modifiedBook.getName());
		tfAuthor.setText(modifiedBook.getAuthor());
		tfQuantity.setText(String.valueOf(modifiedBook.getQuantity()));
		cbCategory.getSelectionModel().select(modifiedBook.getNameCategory());
		cbPublisher.getSelectionModel().select(modifiedBook.getNamePublisher());
		datePicker.setValue(modifiedBook.getPublishingYear().toLocalDate());
	}
	
	public void modify(ActionEvent evt) {
		
		String id_isbn = tfISBN.getText();
		String name = tfName.getText();
		String author = tfAuthor.getText();
		
		int indexCate = cbCategory.getSelectionModel().getSelectedIndex();
		int indexPub = cbPublisher.getSelectionModel().getSelectedIndex();
		
		if(id_isbn.isEmpty() || name.isEmpty() || author.isEmpty() || indexCate == -1 || indexPub == -1) {
			Alert alert = new Alert(AlertType.ERROR, "Enter Book info", ButtonType.OK);
			alert.setHeaderText(null);
			alert.showAndWait();
			return;
		}
		if(!checkISBN(id_isbn)) {
			Alert alert = new Alert(AlertType.ERROR, "ISBN code not valid", ButtonType.OK);
			alert.setHeaderText(null);
			alert.showAndWait();
			return;
		}
		
		
		int id_publisher = listPublisher.get(indexPub).getIdPublisher();
		int id_category = listCategory.get(indexCate).getIdCategory();
		Date publishing_year = Date.valueOf(datePicker.getValue());
		int quantity=-1;
		try {
			quantity = Integer.parseInt(tfQuantity.getText());
		}catch(NumberFormatException e) {
			if(!checkISBN(id_isbn)) {
				Alert alert = new Alert(AlertType.ERROR, "Quantity is not valid", ButtonType.OK);
				alert.setHeaderText(null);
				alert.showAndWait();
				return;
			}
		}
		
		Book book = new Book(modifiedBook.getIdBook(), id_isbn, name, author, id_category, id_publisher, publishing_year, quantity,modifiedBook.getRemain());
		
		
		Alert alert = new Alert(AlertType.CONFIRMATION, "Do you want to modify?", ButtonType.YES,ButtonType.NO);
		alert.setHeaderText(null);
		Optional<ButtonType> option = alert.showAndWait();
		if(option.get() == ButtonType.NO) {
			return;
		}
		bookDAO.modifyBook(book);
		Stage stage = (Stage)((Node)evt.getSource()).getScene().getWindow();
		stage.close();
		
	}
	
	private boolean checkISBN(String ISBN) {
		for(char x : ISBN.toCharArray()) {
			if(Character.isAlphabetic(x)) return false;
		}
		return true;
	}
}
