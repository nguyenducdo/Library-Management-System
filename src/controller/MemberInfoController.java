package controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import controller.UpdateMemberController.ModifyMemberController;
import dao.MemberDAO;
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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Member;

public class MemberInfoController implements Initializable{
	private MemberDAO memberDAO = new MemberDAO();
	@FXML
	private Button btnMember,btnQuanLy;
	@FXML
	private Tab tabMembers,tabQuanLy;
	@FXML
	private TabPane tabPane;
	
	public Tab getTabMembers() {
		return tabMembers;
	}
	
	@FXML
	private TextField tfSearchMember;
	
	@FXML
	private Button btnSearchMember;
	@FXML
	private ComboBox<String> cbSearch; 
	
	@FXML
	private TableView<Member> tbvMemberInfo;
	@FXML
	private TableColumn<Member, String> idMemberCol;
	@FXML
	private TableColumn<Member, String> nameMemberCol;
	@FXML
	private TableColumn<Member, Integer> genderMemberCol;
	@FXML
	private TableColumn<Member, String> addressMemberCol;
	@FXML
	private TableColumn<Member, String> emailMemberCol;
	@FXML
	private TableColumn<Member, String> telMemberCol;
	
	private ObservableList<Member> listMembers;
	
	@FXML
	private ContextMenu contextMember;
	@FXML
	private MenuItem itemBorrowInfo,itemModifyUser,itemDeleteUser,itemAddUser;
	
//	
//	@FXML
//	private TableView<> tbvBorrowingInfo;
//	
//	private ObservableList<> listBorrowingInfo;
	
	@FXML
	private RadioButton radioSearchByID, radioSearchByName;
	@FXML
	private TextField tfSearchTab2;
	


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		initTbvMemberInfo();
		initCbSearch();
	} 
	
	private void initTbvMemberInfo() {
		idMemberCol.setCellValueFactory(new PropertyValueFactory<Member, String>("id"));
		nameMemberCol.setCellValueFactory(new PropertyValueFactory<Member, String>("name"));
		genderMemberCol.setCellValueFactory(new PropertyValueFactory<Member, Integer>("gender"));
		genderMemberCol.setCellFactory(column -> {
		    TableCell<Member, Integer> cell = new TableCell<Member, Integer>() {
		        @Override
		        protected void updateItem(Integer item, boolean empty) {
		            super.updateItem(item, empty);
		            if(empty) {
		                setText(null);
		            }
		            else {
		            	if(item == 0) setText("Female");
		            	else setText("Male");
		            }
		        }
		    };
		    return cell;
		});
		addressMemberCol.setCellValueFactory(new PropertyValueFactory<Member, String>("address"));
		emailMemberCol.setCellValueFactory(new PropertyValueFactory<Member, String>("email"));
		telMemberCol.setCellValueFactory(new PropertyValueFactory<Member, String>("tel"));
		
		listMembers = FXCollections.observableArrayList(memberDAO.getAllMember());
		tbvMemberInfo.setItems(listMembers);
		
		tbvMemberInfo.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {
			@Override
			public void handle(ContextMenuEvent event) {
				// TODO Auto-generated method stub
				contextMember.show(tbvMemberInfo,event.getScreenX(),event.getScreenY());
			}
		});
	}
	
	public void searchMemberInfo(ActionEvent evt) {
		String key = tfSearchMember.getText();
		if(key.equals("")) {
			refresh(tabMembers);
			return;
		}
		SelectionModel<String> selectionModel = cbSearch.getSelectionModel();
		String selected = selectionModel.getSelectedItem();
		if(selected == null) return;
		
		String column=null;
		
		if(selected.equalsIgnoreCase("ID")) {
			column = "id_member";
		}else if(selected.equalsIgnoreCase("Name")) {
			column = "name";
		}else if(selected.equalsIgnoreCase("Address")){
			column = "address";
		}else if(selected.equalsIgnoreCase("Email")) {
			column = "email";
		}else if(selected.equalsIgnoreCase("Telephone")) {
			column = "tel";
		}else {
			System.out.println("ComboBox ERROR!!!");
		}
		
		listMembers.clear();
		listMembers.addAll(memberDAO.searchBy(column,key));
	}
	
	public void showTab(ActionEvent evt) {
		SelectionModel<Tab> model = tabPane.getSelectionModel();
		if(evt.getSource() == btnMember) {
			model.select(tabMembers);
		}else if(evt.getSource() == btnQuanLy) {
			model.select(tabQuanLy);
		}
	}
	
	private void initCbSearch() {
		ObservableList<String> list = FXCollections.observableArrayList(new String[]{"ID","Name","Address","Email","Telephone"});
		cbSearch.setItems(list);
	}
	
	public void keyEvtHandle(KeyEvent evt) {
		if(evt.getCode() == KeyCode.BACK_SPACE) {
			if(evt.getSource() == tfSearchMember) {
				if(tfSearchMember.getText().equals("")) refresh(tabMembers);
			}
			
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
	
	public void refresh(Tab tab) {
		if(tab == tabMembers) {
			listMembers.clear();
			listMembers.addAll(memberDAO.getAllMember());
			SelectionModel<String> model = cbSearch.getSelectionModel();
			model.select(null);
		}else if(tab == tabQuanLy) {
//			r
		}
	}
	
	public void modify(ActionEvent evt) {
		try {
			Member member = tbvMemberInfo.getSelectionModel().getSelectedItem();
			if(member == null) {
				Alert alert = new Alert(AlertType.WARNING, "Please select a member", ButtonType.OK);
				alert.setHeaderText(null);
				alert.showAndWait();
				return;
			}
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/StageUpdateMember/StageModifyMemberInfo.fxml"));
			Parent root = loader.load();
			ModifyMemberController modifyMemberController = loader.getController();
			modifyMemberController.setMember(member);
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.showAndWait();
			refresh(tabMembers);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void add(ActionEvent evt) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/StageUpdateMember/StageAddMember.fxml"));
			Parent root = loader.load();
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.showAndWait();
			refresh(tabMembers);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 
	
	public void delete(ActionEvent evt) {
		Member member = tbvMemberInfo.getSelectionModel().getSelectedItem();
		if(member == null) {
			Alert alert = new Alert(AlertType.WARNING, "Please select a member", ButtonType.OK);
			alert.setHeaderText(null);
			alert.showAndWait();
			return;
		}
		Alert alert = new Alert(AlertType.WARNING,null,ButtonType.YES, ButtonType.NO);
		alert.setContentText("Delete member: "+member.getName() + " - ID: " + member.getId() +" ?");
		alert.setHeaderText(null);
		Optional<ButtonType> optional = alert.showAndWait();
		if(optional.get() == ButtonType.NO) return;
		memberDAO.delete(member);
		refresh(tabMembers);
	}
}
