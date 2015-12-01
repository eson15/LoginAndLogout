package service.impl;

import service.BusinessService;
import utils.DaoFactory;
import dao.UserDao;
import dao.impl.UserDaoXmlImpl;
import domain.User;
import exception.UserExistException;

public class BusinessServiceImpl implements BusinessService {
	
	UserDao dao = DaoFactory.getInstance().createUserDao();//user dao factory to create a certain dao according to properties file
	
	//user register
	@Override
	public void registerUser(User user) throws UserExistException {
		if(dao.findUser(user.getUsername()) != null){
			//checked exception 编译时异常，抛上去必须要处理
			//unchecked exception 运行时异常，上一层无需处理
			//这里抛编译时异常的原因：我想上一层程序处理这个异常，以给用户一个友好的提示
			throw new UserExistException("user already exist!");
		}
		dao.addUser(user);
	}
	
	//user log in
	@Override
	public User loginUser(String username, String password){
		return dao.findUser(username, password);
	}
	
}
