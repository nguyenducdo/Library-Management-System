package model;

import java.io.IOException;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Main extends Application{

	@Override
	public void start(Stage primaryStage){
//		 TODO Auto-generated method stub
		try {
	        Parent root = FXMLLoader.load(getClass().getResource("/view/Home.fxml"));
	            Scene scene = new Scene(root, 800, 560);
	        primaryStage.setTitle("Library Management System");
	        primaryStage.setScene(scene);
	        primaryStage.show();
	        
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}


}
