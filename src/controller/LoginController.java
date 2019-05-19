package controller;

import java.io.IOException;

import dao.StaffDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Staff;

public class LoginController implements ILoginController{
	private final StaffDAO staffDAO = new StaffDAO(); 
	@FXML
	private Button btnLogin;
	@FXML
	private TextField tfUsername;
	@FXML
	private PasswordField pfPass;
	public static int ID_STAFF = -1;
	
	@Override
	public void login(ActionEvent evt) {
		try {
			Staff staff;
			if((staff = staffDAO.getUser(tfUsername.getText(), pfPass.getText()))==null) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setHeaderText(null);
				alert.setContentText("Username or password is wrong");
				alert.getButtonTypes().setAll(ButtonType.OK);
				alert.showAndWait();
				return;
			}
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/Home.fxml"));
			Parent root = loader.load();
			ID_STAFF = staff.getId();
			Stage stage = (Stage)((Node)evt.getSource()).getScene().getWindow();
			stage.setScene(new Scene(root));
		} catch (IOException e) {
			// 
			e.printStackTrace();
		}
		
	}

}
