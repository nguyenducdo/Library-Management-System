package controller.CreateBillController;

import java.sql.Date;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import model.Book;

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
	
	private SelectedBook selectedBook;
	
	public void setBook(Book book) {
		selectedBook = new SelectedBook(book, null);
		
	}
	
	public void initTbvSelectedBook() {
		idBookCol.setCellValueFactory(new PropertyValueFactory<SelectedBook, String>("idBook"));
		nameBookCol.setCellValueFactory(new PropertyValueFactory<SelectedBook, String>("name"));
		authorBookCol.setCellValueFactory(new PropertyValueFactory<SelectedBook, String>("author"));
		publishingYearCol.setCellValueFactory(new PropertyValueFactory<SelectedBook, Date>("publishingYear"));
		dateCol.setCellValueFactory(new PropertyValueFactory<SelectedBook, Date>("return_date"));
		
		dateCol.setCellFactory(new Callback<TableColumn<SelectedBook,Date>, TableCell<SelectedBook,Date>>() {
			
			@Override
			public TableCell<SelectedBook, Date> call(TableColumn<SelectedBook, Date> param) {
				// TODO Auto-generated method stub
				
				return null;
			}
		});
		tbvSelectedBook.setEditable(true);
	}
	
	class SelectedBook extends Book{
		Date return_date;
		
		public SelectedBook(Book book, Date return_date) {
			super(book);
			this.return_date = return_date;
		}
		
		public Date getReturn_date() {
			return return_date;
		}
		
		public void setReturn_date(Date return_date) {
			this.return_date = return_date;
		}

	}
	
}
