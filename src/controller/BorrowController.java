package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;

import controller.CreateBillController.CreateBillController;
import dao.BookDAO;
import dao.BorrowDAO;
import dao.MemberDAO;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Book;
import model.BorrowingInfo;
import model.DetailBill;
import model.Member;
import model.ClassDTO.BookBill;
import model.ClassDTO.BookDTO;

public class BorrowController implements Initializable{
	@FXML
	private TabPane tabPane;
	
	@FXML
	private Tab tabCreateBill, tabReturnBook,tabListBorrow;
	@FXML
	private Button btnCreateBillTab, btnListBorrowTab, btnReturnBookTab,btnBigbtn;
	
	
	@FXML
	private TableView<BookBill> tbvBookBill;
	private ObservableList<BookBill> listBookBill;
	@FXML
	private TableColumn<BookBill, String> idBillColTab1,idBookColTab1,nameBookColTab1,idMemberColTab1,nameMemberColTab1,stateColTab1;
	@FXML
	private TableColumn<BookBill, Date> dateColTab1;
	
	
	@FXML
	private TableView<Book> tbvBookInfoTab2;
	private ObservableList<Book> listBookInfo;
	public List<Book> listSelectedBook = new ArrayList<Book>();
	private BookDAO bookDAO = new BookDAO();
	private BorrowDAO borrowDAO = new BorrowDAO();
	
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
	
	
	
	@FXML
	private TableView<DetailBill> tbvDetailBill;
	private ObservableList<DetailBill> listDetailBill;
	private List<DetailBill> listSelectedDBill = new ArrayList<DetailBill>();
	@FXML
	private TableColumn<DetailBill, String> idBookColTab3,stateColTab3,nameBookColTab3;
	@FXML
	private TableColumn<DetailBill, Date> returnDateColTab3;
	@FXML
	private TableColumn<DetailBill, Boolean> checkColTab3;
	
	@FXML
	private TextField tfSearchBillTab3;
	@FXML
	private Label lbIDMemberTab3, lbNameMemberTab3, lbIDStaffTab3, lbBorrowingDateTab3;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		initTbvBookInfoTab2();
		initTbvDetailBill();
		initTbvBookBill();
		tabPane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
			 @Override
		     public void changed(ObservableValue<? extends Tab> ov, Tab t, Tab t1) {
				 if(t1.equals(tabListBorrow)) {
					 refresh(tabListBorrow);
				 }else if(t1.equals(tabCreateBill)) {
					 refresh(tabCreateBill);
				 }else if(t1.equals(tabReturnBook)) {
					 refresh(tabReturnBook);
				 }
		     }
		});
	}
	
	
	
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
				SimpleBooleanProperty booleanProp = new SimpleBooleanProperty(listSelectedBook.contains(book));
				booleanProp.addListener(new ChangeListener<Boolean>() {
					@Override
	                   public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue,
	                           Boolean newValue) {
							if(newValue == false) {
								listSelectedBook.remove(book);
								for (Book book2 : listSelectedBook) {
									System.out.println("REMOVE " + book2.getIdBook());
								}
							}else {
								listSelectedBook.add(book);
								for (Book book2 : listSelectedBook) {
									System.out.println("ADD " + book2.getIdBook());
								}
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
		for (Book book : listBookInfo) {
			if(book.getRemain()<=0) listBookInfo.remove(book);
		}
		tbvBookInfoTab2.setItems(listBookInfo);
		tbvBookInfoTab2.setEditable(true);
	}
	
	private void initTbvDetailBill() {
		idBookColTab3.setCellValueFactory(new PropertyValueFactory<DetailBill,String>("id_book"));	
		nameBookColTab3.setCellValueFactory(new PropertyValueFactory<DetailBill,String>("book_name"));
		returnDateColTab3.setCellValueFactory(new PropertyValueFactory<DetailBill,Date>("return_date"));
		stateColTab3.setCellValueFactory(new PropertyValueFactory<DetailBill,String>("name_state"));
		checkColTab3.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DetailBill,Boolean>, ObservableValue<Boolean>>() {
			@Override
			public ObservableValue<Boolean> call(CellDataFeatures<DetailBill, Boolean> param) {
				// TODO Auto-generated method stub
				DetailBill detailBill = param.getValue();
				SimpleBooleanProperty booleanProp = new SimpleBooleanProperty(listSelectedDBill.contains(detailBill));
				booleanProp.addListener(new ChangeListener<Boolean>() {
					@Override
	                   public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue,
	                           Boolean newValue) {
							if(newValue == false) {
								listSelectedDBill.remove(detailBill);
								for (DetailBill book2 : listSelectedDBill) {
									System.out.println("REMOVE " + book2.getId_book());
								}
							}else {
								listSelectedDBill.add(detailBill);
								for (DetailBill book2 : listSelectedDBill) {
									System.out.println("ADD " + book2.getId_book());
								}
							}
	                   }
				});
				
				return booleanProp;
			}
		});
		
		checkColTab3.setCellFactory(new Callback<TableColumn<DetailBill, Boolean>, TableCell<DetailBill, Boolean>>() {
	           @Override
	           public TableCell<DetailBill, Boolean> call(TableColumn<DetailBill, Boolean> p) {
	               CheckBoxTableCell<DetailBill, Boolean> cell = new CheckBoxTableCell<DetailBill, Boolean>();
	               cell.setAlignment(Pos.CENTER);
	               return cell;
	           }
	     });
		listDetailBill = FXCollections.observableArrayList();
		System.out.println("LIST DETAIL: " + listDetailBill);
		tbvDetailBill.setItems(listDetailBill);
		tbvDetailBill.setEditable(true);
	}
	
	public void initTbvBookBill() {
		idBillColTab1.setCellValueFactory(new PropertyValueFactory<BookBill,String>("id_bill"));
		idBookColTab1.setCellValueFactory(new PropertyValueFactory<BookBill,String>("id_book"));
		nameBookColTab1.setCellValueFactory(new PropertyValueFactory<BookBill,String>("name_book"));
		idMemberColTab1.setCellValueFactory(new PropertyValueFactory<BookBill,String>("id_member"));
		nameMemberColTab1.setCellValueFactory(new PropertyValueFactory<BookBill,String>("name_member"));
		dateColTab1.setCellValueFactory(new PropertyValueFactory<BookBill,Date>("borrowing_date"));
		stateColTab1.setCellValueFactory(new PropertyValueFactory<BookBill,String>("name_state"));
		
		listBookBill = FXCollections.observableArrayList(borrowDAO.getBookBillInfo(null));
		tbvBookBill.setItems(listBookBill);
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
	
	public void refresh(Tab tab) {
		if(tab==tabCreateBill) {
			listBookInfo.clear();
			listBookInfo.addAll(bookDAO.getAllBook());
			listSelectedBook.clear();
			lbEmailTab2.setText("");
			lbNameTab2.setText("");
		}else if(tab == tabReturnBook) {
			listDetailBill.clear();
			listSelectedDBill.clear();
			lbBorrowingDateTab3.setText("");
			lbIDMemberTab3.setText("");
			lbIDStaffTab3.setText("");
			lbNameMemberTab3.setText("");
		}else if(tab == tabListBorrow) {
			listBookBill.clear();
			listBookBill.addAll(borrowDAO.getBookBillInfo(null));
		}
	}
	
	public void showTab(ActionEvent evt) {
		if(evt.getSource() == btnCreateBillTab) {
			tabPane.getSelectionModel().select(tabCreateBill);
//			refresh(tab);
		}else if(evt.getSource() == btnListBorrowTab || evt.getSource() == btnBigbtn) {
			tabPane.getSelectionModel().select(tabListBorrow);
		}else if(evt.getSource() == btnReturnBookTab) {
			tabPane.getSelectionModel().select(tabReturnBook);
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
	
	public Member checkIDMember(ActionEvent evt) {
		String key = tfSearchIDMemberTab2.getText();
		if(key.isEmpty()) {
			return null;
		}
		List<Member> list = new MemberDAO().searchBy("id_member",key);
		if(list.size()==0) {
			lbNameTab2.setText("This member not exists");
			lbEmailTab2.setText("");
			return null;
		}
		Member mem = list.get(0);
		lbNameTab2.setText("Name: " + mem.getName());
		lbEmailTab2.setText("Email: "+ mem.getEmail());
		return mem;
	}
	
	public void createBill(ActionEvent evt) {
		try {
			if(listSelectedBook.size()==0) {
				Alert alert = new Alert(AlertType.ERROR, "Choose at least a book", ButtonType.OK);
				alert.setHeaderText(null);
				alert.showAndWait();
				return;
			}
			Member member;
			if((member = checkIDMember(evt))==null) {
				Alert alert = new Alert(AlertType.ERROR, "Please enter id member", ButtonType.OK);
				alert.setHeaderText(null);
				alert.showAndWait();
				return;
			}
			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/StageBorrowInfo/StageCreateBill.fxml"));
			Parent root = loader.load();
			CreateBillController controller = loader.getController();
			controller.setListBook(listSelectedBook);
			controller.setInfoUser(member);
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.showAndWait();
			refresh(tabCreateBill);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void searchDetailBill(ActionEvent evt) {
		String id = tfSearchBillTab3.getText();
		if(id.isEmpty()) {
			Alert alert = new Alert(AlertType.ERROR, "Please enter bill id", ButtonType.OK);
			alert.setHeaderText(null);
			alert.showAndWait();
			refresh(tabReturnBook);
			return;
		}
		List<DetailBill> tmpList = borrowDAO.getDetailBill2(id, null);
		if(tmpList==null) {
			System.out.println("Error");
			return;
		}
		if(tmpList.size()==0) {
			Alert alert = new Alert(AlertType.INFORMATION, "Data is Empty", ButtonType.OK);
			alert.setHeaderText(null);
			alert.showAndWait();
			refresh(tabReturnBook);
			return;
		}
		
		listDetailBill.clear();
		listDetailBill.addAll(tmpList);
		
		BorrowingInfo borrowInfo = borrowDAO.searchBorrowInfo("id_bill", id).get(0);
		lbIDMemberTab3.setText("ID Member: " + borrowInfo.getId_member());
		lbNameMemberTab3.setText("Name: "+borrowInfo.getName_member());
		lbIDStaffTab3.setText("ID Staff: "+ borrowInfo.getId_staff());
		lbBorrowingDateTab3.setText("Borrowing Date: "+borrowInfo.getBorrowing_date());
	}
	
	public void returnBook(ActionEvent evt) {
		if(listSelectedDBill.size()==0) {
			Alert alert = new Alert(AlertType.ERROR,"Error...",ButtonType.OK);
			alert.setHeaderText(null);
			alert.showAndWait();
			return;
		}
		Alert alert = new Alert(AlertType.CONFIRMATION,"Are you sure?",ButtonType.YES, ButtonType.NO);
		alert.setHeaderText(null);
		Optional<ButtonType> optional = alert.showAndWait();
		if(optional.get() == ButtonType.NO) return;
		borrowDAO.returnBook(listSelectedDBill);
		searchDetailBill(evt);
	}
}
