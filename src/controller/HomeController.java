package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import dao.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class HomeController	implements Initializable, IHomeController{
	@FXML
	private Button btnBookInformation, btnStaffInformation, btnLogout, btnMember,btnBorrow,btnStatistics;
	private Parent parentBookInfo, parentStaffInfo;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		updateDB();
	}
	@Override
	public void changeScene(ActionEvent e) {
//		Thread t = new Thread(new Runnable() {
//			@Override
//			public void run() {
//				// 
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
			}else if(e.getSource() == btnStatistics) {
				Parent root = FXMLLoader.load(getClass().getResource("/view/Statistics.fxml"));
				stage.setScene(new Scene(root));
			}
		} catch (IOException e1) {
			// 
			e1.printStackTrace();
		}
//		t.interrupt();
		long end = System.currentTimeMillis();
		System.out.println("Time : "+(end-start));
	}

	@Override
	public void updateDB() {
		Connection cnn = DBConnection.open();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = (PreparedStatement) cnn.prepareStatement("call update_state()");
			ps.execute();
		} catch (SQLException e) {
			// 
			e.printStackTrace();
		}finally {
			DBConnection.close(rs, ps, cnn);
		}
	}
	
}
