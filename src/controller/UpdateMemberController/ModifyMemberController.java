package controller.UpdateMemberController;

import java.util.Optional;

import dao.MemberDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.Member;

public class ModifyMemberController {
	private MemberDAO memberDAO = new MemberDAO();
	@FXML
	private TextField tfName,tfEmail,tfTelephone,tfAddress;
	@FXML
	private RadioButton radioMale, radioFemale;
	@FXML
	private Button btnApply;
	
	private Member member;

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
		refresh();
	}
	
	public void refresh() {
		if(member == null) return;
		tfName.setText(member.getName());
		tfAddress.setText(member.getAddress());
		tfEmail.setText(member.getEmail());
		tfTelephone.setText(member.getTel());
		if(member.getGender() == 0) {
			radioFemale.setSelected(true);
		}else {
			radioMale.setSelected(true);
		}
	}
	
	public void modify(ActionEvent evt) {
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
		
		if(!tel.matches("\\+?\\d+")) {
			Alert alert = new Alert(AlertType.ERROR, "Tel number is not valid", ButtonType.OK);
			alert.setHeaderText(null);
			alert.showAndWait();
			return;
		}
		
		Alert alert = new Alert(AlertType.CONFIRMATION, "Save the change?", ButtonType.YES, ButtonType.NO);
		alert.setHeaderText(null);
		Optional<ButtonType> optional = alert.showAndWait();
		if(optional.get() == ButtonType.NO) return;
		
		int gender;
		if(radioFemale.isSelected()) gender = 0;
		else gender = 1;
		member.setName(name);
		member.setAddress(address);
		member.setEmail(email);
		member.setTel(tel);
		member.setGender(gender);
		memberDAO.update(member);
		refresh();
		Stage stage = (Stage)((Node)evt.getSource()).getScene().getWindow();
		stage.close();
		Alert alert2 = new Alert(AlertType.INFORMATION,"Modify successful!",ButtonType.OK);
		alert2.setHeaderText(null);
		alert2.showAndWait();
	}

}
