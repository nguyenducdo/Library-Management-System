package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class HomeController {
	@FXML
	private Button btnBookInformation;
	
	public void changeScene(ActionEvent e) {
		if(e.getSource() == btnBookInformation) {
			Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
			try {
				Parent root = FXMLLoader.load(getClass().getResource("/view/BookInformation.fxml"));
				stage.setScene(new Scene(root));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
