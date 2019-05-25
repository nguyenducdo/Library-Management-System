package dao;

import model.Staff;

public interface IStaffDAO {
	Staff getUser(String user, String pass);
	Staff getUser(int ID);
	void modifyStaffInfo(Staff newStaff);
}
