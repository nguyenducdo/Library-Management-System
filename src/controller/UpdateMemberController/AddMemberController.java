package controller.UpdateMemberController;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import dao.MemberDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.Member;

public class AddMemberController implements Initializable{
	private MemberDAO memberDAO = new MemberDAO();
	@FXML
	private TextField tfName,tfEmail,tfTelephone,tfAddress;
	@FXML
	private RadioButton radioMale, radioFemale;
	@FXML
	private Button btnAdd;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		refresh();
	}
	
	public void refresh() {
		tfName.setText("");
		tfAddress.setText("");
		tfEmail.setText("");
		tfTelephone.setText("");
	}
	
	public void add(ActionEvent evt) {
		String name = tfName.getText();
		String address = tfAddress.getText();
		String email = tfEmail.getText();
		String tel = tfTelephone.getText();
		if(name.isEmpty() || address.isEmpty() || email.isEmpty() || tel.isEmpty()) {
			Alert alert = new Alert(AlertType.INFORMATION, "Please enter full info", ButtonType.OK);
			alert.setHeaderText(null);
			alert.showAndWait();
			return;
		}
		Alert alert = new Alert(AlertType.CONFIRMATION, "Add new member?", ButtonType.YES, ButtonType.NO);
		alert.setHeaderText(null);
		Optional<ButtonType> optional = alert.showAndWait();
		if(optional.get() == ButtonType.NO) return;
		int gender;
		if(radioFemale.isSelected()) gender = 0;
		else gender = 1;
		Member member = new Member(null, name, gender, address, tel, email);
		memberDAO.add(member);
		Stage stage = (Stage)((Node)evt.getSource()).getScene().getWindow();
		stage.close();
	}

	
}
