package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class HomeController	implements Initializable{
	@FXML
	private Button btnBookInformation;
	private Parent parentBookInfo;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		try {
			parentBookInfo = FXMLLoader.load(getClass().getResource("/view/BookInformation.fxml"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	
	public void changeScene(ActionEvent e) {
		long start = System.currentTimeMillis();
		Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
		if(e.getSource() == btnBookInformation) {
			stage.setScene(new Scene(parentBookInfo));
		}
		long end = System.currentTimeMillis();
		System.out.println("Time : "+(end-start));
	}
}
