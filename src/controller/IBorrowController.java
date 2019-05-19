package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Tab;
import model.Member;

public interface IBorrowController {
	public void searchBook(ActionEvent evt);
	public void refresh(Tab tab);
	public void showTab(ActionEvent evt);
	public void turnBack(ActionEvent evt);
	public Member checkIDMember(ActionEvent evt);
	public void createBill(ActionEvent evt);
	public void searchDetailBill(ActionEvent evt);
	public void returnBook(ActionEvent evt);
	public void reportLosted(ActionEvent evt);
}
