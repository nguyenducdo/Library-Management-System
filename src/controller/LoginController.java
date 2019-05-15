package controller;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.Staff;

public class LoginController implements Initializable{
	private final StaffDAO staffDAO = new StaffDAO(); 
	@FXML
	private Button btnLogin;
	@FXML
	private TextField tfUsername;
	@FXML
	private PasswordField pfPass;
	public static int ID_STAFF = -1;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
