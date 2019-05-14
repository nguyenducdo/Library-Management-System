package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import dao.BookDAO;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Book;
import model.BookDTO;

public class BorrowController implements Initializable{
	@FXML
	private TableView<Book> tbvBookInfoTab2;
	
	private ObservableList<Book> listBookInfo;
	private BookDAO bookDAO = new BookDAO();
	
	@FXML
	private TableColumn<Book, String> idBookColTab2,nameColTab2,authorColTab2;
	@FXML
	private TableColumn<Book, Integer> remainingColTab2;
	@FXML
	private TableColumn<Book, Date> publishingYearColTab2;
	@FXML
	private TableColumn<Book, Boolean> checkColTab2;
	@FXML
	private Button btnCreateBill,btnSearchBookTab2,btnCheckIDMemberTab2;
	@FXML
	private TextField tfSearchBookTab2, tfSearchIDMemberTab2;
	
	@FXML
	private Label lbNameTab2,lbEmailTab2;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		initTbvBookInfoTab2();
	}
	
	List<Book> list = new ArrayList<Book>();
	
	private void initTbvBookInfoTab2() {
		
		idBookColTab2.setCellValueFactory(new PropertyValueFactory<Book, String>("idBook"));
		nameColTab2.setCellValueFactory(new PropertyValueFactory<Book, String>("name"));
		authorColTab2.setCellValueFactory(new PropertyValueFactory<Book, String>("author"));
		publishingYearColTab2.setCellValueFactory(new PropertyValueFactory<Book, Date>("publishingYear"));
		remainingColTab2.setCellValueFactory(new PropertyValueFactory<Book, Integer>("remain"));
		
		checkColTab2.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Book,Boolean>, ObservableValue<Boolean>>() {
			@Override
			public ObservableValue<Boolean> call(CellDataFeatures<Book, Boolean> param) {
				// TODO Auto-generated method stub
				Book book = param.getValue();
				SimpleBooleanProperty booleanProp = new SimpleBooleanProperty(list.contains(book));
				booleanProp.addListener(new ChangeListener<Boolean>() {
					@Override
	                   public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue,
	                           Boolean newValue) {
							if(newValue == false) {
								list.remove(book);
							}else {
								list.add(book);
							}
	                   }
				});
				
				return booleanProp;
			}
		});
		
		checkColTab2.setCellFactory(new Callback<TableColumn<Book, Boolean>, TableCell<Book, Boolean>>() {
	           @Override
	           public TableCell<Book, Boolean> call(TableColumn<Book, Boolean> p) {
	               CheckBoxTableCell<Book, Boolean> cell = new CheckBoxTableCell<Book, Boolean>();
	               cell.setAlignment(Pos.CENTER);
	               return cell;
	           }
	       });
		
		listBookInfo = FXCollections.observableArrayList(bookDAO.getAllBook());
		tbvBookInfoTab2.setItems(listBookInfo);
		tbvBookInfoTab2.setEditable(true);
	}
	
	public void searchBook(ActionEvent evt) {
		String str = tfSearchBookTab2.getText();
		if(str.isEmpty()) return;
		int index = 0;
		for (Book x : listBookInfo) {
			if(x.getIdBook().contains(str)) {
				tbvBookInfoTab2.getSelectionModel().select(index);
				tbvBookInfoTab2.scrollTo(index);
				return;
			}
			index++;
		}
	}

	public void turnBack(ActionEvent evt) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/view/Home.fxml"));
			Stage stage = (Stage)((Node)evt.getSource()).getScene().getWindow();
			stage.setScene(new Scene(root));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
