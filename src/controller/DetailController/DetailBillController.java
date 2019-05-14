package controller.DetailController;

import java.sql.Date;
import java.util.List;

import dao.BorrowDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.BorrowingInfo;
import model.DetailBill;

public class DetailBillController {
	@FXML
	private TableView<DetailBill> tbvDetail;
	private ObservableList<DetailBill> listDetailBill;
	@FXML
	private TableColumn<DetailBill, String> idBillCol;
	@FXML
	private TableColumn<DetailBill, String> idBookCol;
	@FXML
	private TableColumn<DetailBill, String> nameBookCol;
	@FXML
	private TableColumn<DetailBill, Date> borrowingDateCol;
	@FXML
	private TableColumn<DetailBill, Date> returnDateCol;
	@FXML
	private TableColumn<DetailBill, String> nameStateCol;
	
	private String id_bill=null;
	private BorrowDAO borrowDAO = new BorrowDAO();
	
	private void initTbvDetail() {
		idBillCol.setCellValueFactory(new PropertyValueFactory<DetailBill, String>("id_bill"));
		idBookCol.setCellValueFactory(new PropertyValueFactory<DetailBill, String>("id_book"));
		nameBookCol.setCellValueFactory(new PropertyValueFactory<DetailBill, String>("book_name"));
		borrowingDateCol.setCellValueFactory(new PropertyValueFactory<DetailBill, Date>("borrowing_date"));
		returnDateCol.setCellValueFactory(new PropertyValueFactory<DetailBill, Date>("return_date"));
		nameStateCol.setCellValueFactory(new PropertyValueFactory<DetailBill, String>("name_state"));
		
		listDetailBill = FXCollections.observableArrayList(borrowDAO.getDetailBill(id_bill, null));
		tbvDetail.setItems(listDetailBill);
	}
	
	public void setId_bill(String id_bill) {
		this.id_bill = id_bill;
		initTbvDetail();
	}
}
