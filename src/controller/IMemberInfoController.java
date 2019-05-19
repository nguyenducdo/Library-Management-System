package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Tab;

public interface IMemberInfoController {
	public void searchMemberInfo(ActionEvent evt);
	public void showTab(ActionEvent evt);
	public void turnBack(ActionEvent evt);
	public void refresh(Tab tab);
	public void modify(ActionEvent evt);
	public void add(ActionEvent evt);
	public void delete(ActionEvent evt);
	public void searchTab2(ActionEvent evt);
	public void autoSearch(ActionEvent evt);
}
