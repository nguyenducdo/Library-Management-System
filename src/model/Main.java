package model;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{

	@Override
	public void start(Stage primaryStage){
//		 TODO Auto-generated method stub
		try {
	        Parent root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
//			Parent root = FXMLLoader.load(getClass().getResource("/view/BookInformation.fxml"));
	        Scene scene = new Scene(root);
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
