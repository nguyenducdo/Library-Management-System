package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Tab;

public interface IBookInfoController {
	public void refresh(Tab tab);
	public void turnBack(ActionEvent evt);
	public void showTab(ActionEvent evt);
	public void searchBookInfo(ActionEvent evt);
	public void searchCategories(ActionEvent evt);
	public void searchPublisher();
	public void showAddBookStage(ActionEvent evt);
	public void deleteBook(ActionEvent evt);
	public void modifyBook(ActionEvent evt);
	public void addMore(ActionEvent evt);
	public void reduceBook();
}
