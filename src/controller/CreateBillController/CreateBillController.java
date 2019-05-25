package controller.CreateBillController;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import controller.LoginController;
import dao.BorrowDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Book;
import model.Bill;
import model.Member;
import model.ClassDTO.SelectedBook;
import view.StageBorrowInfo.DatePickerCell;

public class CreateBillController {
	
	@FXML
	private TableView<SelectedBook> tbvSelectedBook;
	private ObservableList<SelectedBook> listSelectedBook;
	
	@FXML
	private TableColumn<SelectedBook, String> idBookCol;
	@FXML
	private TableColumn<SelectedBook, String> nameBookCol;
	@FXML
	private TableColumn<SelectedBook, String> authorBookCol;
	@FXML
	private TableColumn<SelectedBook, Date> publishingYearCol;
	@FXML
	private TableColumn<SelectedBook, Date> dateCol;
	
	@FXML
	private Label lbID,lbName,lbBorrowingDate, lbStaff, lbQuantity;
	
	private Member member;
	
	public void setInfoUser(Member member) {
		this.member = member;
		lbID.setText("ID: " + this.member.getId());
		lbName.setText("Name: "+ this.member.getName());
		lbBorrowingDate.setText("Borrowing Date: " + new Date(System.currentTimeMillis()));
		lbStaff.setText("Staff: " + LoginController.ID_STAFF);
		lbQuantity.setText("Quantity: "+listSelectedBook.size());
	}
	
	public void setListBook(List<Book> list) {
		if(list==null) {
			return;
		}
		listSelectedBook = FXCollections.observableArrayList();
		System.out.println("** " + listSelectedBook);
		for (Book book : list) {
			listSelectedBook.add(new SelectedBook(book, null));
		}
		
		initTbvSelectedBook();
	}
	
	public void initTbvSelectedBook() {
		idBookCol.setCellValueFactory(new PropertyValueFactory<SelectedBook, String>("idBook"));
		nameBookCol.setCellValueFactory(new PropertyValueFactory<SelectedBook, String>("name"));
		authorBookCol.setCellValueFactory(new PropertyValueFactory<SelectedBook, String>("author"));
		publishingYearCol.setCellValueFactory(new PropertyValueFactory<SelectedBook, Date>("publishingYear"));
		dateCol.setCellValueFactory(new PropertyValueFactory<SelectedBook, Date>("returnDate"));
		
		dateCol.setCellFactory(new Callback<TableColumn<SelectedBook,Date>, TableCell<SelectedBook,Date>>() {	
			@Override
			public TableCell<SelectedBook, Date> call(TableColumn<SelectedBook, Date> param) {
				// TODO Auto-generated method stub
				DatePickerCell datePick = new DatePickerCell(listSelectedBook);
				return datePick;
			}
		});
		
		tbvSelectedBook.setEditable(true);
		tbvSelectedBook.setItems(listSelectedBook);
		
	}
	
	public void create(ActionEvent evt) {
		int index = 0;
		for (SelectedBook selectedBook : listSelectedBook) {
			if(selectedBook.getReturnDate() == null) {
				tbvSelectedBook.getSelectionModel().select(index);
				tbvSelectedBook.scrollTo(index);
				Alert alert = new Alert(AlertType.WARNING, "Please choose return date", ButtonType.OK);
				alert.setHeaderText(null);
				alert.showAndWait();
				return;
			}
			index++;
		}
		Alert alert = new Alert(AlertType.CONFIRMATION, "Create this bill?", ButtonType.YES,ButtonType.NO);
		alert.setHeaderText(null);
		Optional<ButtonType> optional = alert.showAndWait();
		if(optional.get() == ButtonType.NO) return;
		System.out.println(listSelectedBook.toArray());
		new BorrowDAO().createBill(new Bill(null, this.member.getId(), this.member.getName(), LoginController.ID_STAFF,new Date(System.currentTimeMillis())), listSelectedBook);
		close(evt);
		Alert alert2 = new Alert(AlertType.INFORMATION, "Create Successul", ButtonType.OK);
		alert2.setHeaderText(null);
		alert2.showAndWait();
		// BORROWING DATE
		// create bill
	}
	
	public void close(ActionEvent evt) {
		Stage stage = (Stage)((Node)evt.getSource()).getScene().getWindow();
		stage.close();
	}
	
}
