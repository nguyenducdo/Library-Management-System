package controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;
import model.Staff;

public class HomeController	implements Initializable{
	@FXML
	private Button btnBookInformation, btnStaffInformation, btnLogout, btnMember,btnBorrow;
	private Parent parentBookInfo, parentStaffInfo;
	
	private static int idStaffLogin = -1;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	public void changeScene(ActionEvent e) {
//		Thread t = new Thread(new Runnable() {
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//				JOptionPane.showMessageDialog(null, "Loading,,,");
//			}
//		} );
//		t.start();
		long start = System.currentTimeMillis();
		Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
		try {
			if(e.getSource() == btnBookInformation) {
				parentBookInfo = FXMLLoader.load(getClass().getResource("/view/BookInformation.fxml"));
				stage.setScene(new Scene(parentBookInfo));
			}
			else if(e.getSource() == btnStaffInformation) {
				FXMLLoader loader = new FXMLLoader();
					loader.setLocation(getClass().getResource("/view/StaffInformation.fxml"));
					parentStaffInfo = loader.load();
					StaffInfoController staffInfoController = loader.getController();
					staffInfoController.setIdStaffLogin(idStaffLogin);
					stage.setScene(new Scene(parentStaffInfo));
				
			}else if(e.getSource() == btnLogout) {
					Parent root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
					stage.setScene(new Scene(root));
			}else if(e.getSource() == btnMember) {
				Parent root = FXMLLoader.load(getClass().getResource("/view/MemberInformation.fxml"));
				stage.setScene(new Scene(root));
			}else if(e.getSource() == btnBorrow) {
				Parent root = FXMLLoader.load(getClass().getResource("/view/BorrowBooksManagement.fxml"));
				stage.setScene(new Scene(root));
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
//		t.interrupt();
		long end = System.currentTimeMillis();
		System.out.println("Time : "+(end-start));
	}

	/**
	 * @return the idStaffLogin
	 */
	public final int getIdStaffLogin() {
		return idStaffLogin;
	}

	/**
	 * @param idStaffLogin the idStaffLogin to set
	 */
	public final void setIdStaffLogin(int idStaffLogin) {
		this.idStaffLogin = idStaffLogin;
	}

	
	
	
}
