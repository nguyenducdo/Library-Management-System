package controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import dao.StaffDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.Staff;

public class StaffInfoController implements Initializable, IStaffInfoController{
	
	@FXML
	private Label lbID;
	@FXML
	private TextField tfName, tfTelephone, tfAddress, tfEmail, tfUser;
	@FXML
	private PasswordField pfPass;
	@FXML
	private Button btnUpdate, btnSave, btnCancel, btnChangeAvatar;
	@FXML
	private RadioButton radioMale, radioFemale;
	private final StaffDAO staffDAO = new StaffDAO();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//
		refresh();
	}

	public void clickUpdate(ActionEvent evt) {
		changeStateComponent(true);
	}

	@Override
	public void refresh() {
		Staff staffLogin = staffDAO.getUser(LoginController.ID_STAFF);
		changeStateComponent(false);
		lbID.setText(staffLogin.getId()+"");
		tfName.setText(staffLogin.getName()+"");
		if(staffLogin.getGender()==1) {
			radioMale.setSelected(true);
		}else if(staffLogin.getGender()==0){
			radioFemale.setSelected(true);
		}
		tfTelephone.setText(staffLogin.getTel());
		tfAddress.setText(staffLogin.getAddress());
		tfEmail.setText(staffLogin.getEmail());
		tfUser.setText(staffLogin.getUsername());
		pfPass.setText(staffLogin.getPassword());
		
	}

	@Override
	public void changeStateComponent(boolean state) {
		btnSave.setDisable(!state);
		btnCancel.setDisable(!state);
		btnChangeAvatar.setDisable(!state);
		tfName.setEditable(state);
		radioFemale.setDisable(!state);
		radioMale.setDisable(!state);
		tfTelephone.setEditable(state);
		tfAddress.setEditable(state);
		tfEmail.setEditable(state);
		tfUser.setEditable(state);
		pfPass.setEditable(state);
	}

	@Override
	public void save(ActionEvent evt) {
		Alert alert = new Alert(AlertType.CONFIRMATION, "Save the changes?", ButtonType.YES, ButtonType.NO);
		alert.setContentText(null);
		Optional<ButtonType> option = alert.showAndWait();
		if(option.get() == ButtonType.NO) return;
		String name = tfName.getText();
		String address = tfAddress.getText();
		String tel = tfTelephone.getText();
		String email = tfEmail.getText();
		String username = tfUser.getText();
		String password = pfPass.getText();
		int gender = radioMale.isSelected()?1:0;
		staffDAO.modifyStaffInfo(new Staff(LoginController.ID_STAFF, name, gender, address, tel, email, username, password));
		refresh();
	}

	@Override
	public void cancel(ActionEvent evt) {
		turnBack(evt);
	}

	@Override
	public void turnBack(ActionEvent evt) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/view/Home.fxml"));
			Stage stage = (Stage)((Node)evt.getSource()).getScene().getWindow();
			stage.setScene(new Scene(root));
		} catch (IOException e) {
			// 
			e.printStackTrace();
		}
	}

	
}
