package dao;

import domain.User;

public interface UserDao {

	void addUser(User user);

	User findUser(String username);

	User findUser(String username, String password);

}