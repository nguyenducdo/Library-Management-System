package controller;

import javafx.event.ActionEvent;

public interface IStaffInfoController {
	public void changeStateComponent(boolean state);
	public void refresh();
	public void save(ActionEvent evt);
	public void cancel(ActionEvent evt);
	public void turnBack(ActionEvent evt);
}
