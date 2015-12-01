package service;

import domain.User;
import exception.UserExistException;

public interface BusinessService {

	//user register
	void registerUser(User user) throws UserExistException;

	//user log in
	User loginUser(String username, String password);

}