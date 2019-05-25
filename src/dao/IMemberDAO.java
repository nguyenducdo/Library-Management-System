package dao;

import java.util.List;

import model.Member;

public interface IMemberDAO {
	List<Member> getAllMember();
	List<Member> searchBy(String column, String key);
	void update(Member member);
	void add(Member member);
	boolean delete(Member member);
}
