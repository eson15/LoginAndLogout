package service;

import domain.User;

public interface BusinessService {

	//user register
	void registerUser(User user);

	//user log in
	User loginUser(String username, String password);

}