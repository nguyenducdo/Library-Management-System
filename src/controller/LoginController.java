package controller;

import java.io.IOException;

import dao.StaffDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {
	final StaffDAO staffDAO = new StaffDAO(); 
	@FXML
	private Button btnLogin;
	@FXML
	private TextField tfUsername;
	@FXML
	private PasswordField pfPass;
	public void login(ActionEvent evt) {
		try {
			if(staffDAO.getUser(tfUsername.getText(), pfPass.getText())==null) {
				System.out.println("Sai ten dang nhap hoac mat khau");
				return;
			}
			Parent root = FXMLLoader.load(getClass().getResource("/view/Home.fxml"));
			Stage stage = (Stage)((Node)evt.getSource()).getScene().getWindow();
			stage.setScene(new Scene(root));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
