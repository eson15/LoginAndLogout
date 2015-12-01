package junit;

import java.util.Date;

import org.junit.Test;

import dao.UserDao;
import dao.impl.UserDaoXmlImpl;
import domain.User;
/**
 * @author Ni Shengwu
 * @description this is a test class for testing functions of UserDaoXmlImpl class
 * we can check the user.xml at yourprojectpath/WebRoot/WEB-INF/classes/user.xml after testing
 */
public class UserDaoTest {
	
	@Test
	public void testAddUser(){
		User user = new User();
		user.setId("1");
		user.setUsername("aaa");
		user.setPassword("123");
		user.setEmail("aa@126.com");
		user.setBirthday(new Date());
		UserDao dao = new UserDaoXmlImpl();
		dao.addUser(user);
	}

	@Test
	public void testFindUserByUsername(){
		String username = "aaa";
		UserDao dao = new UserDaoXmlImpl();
		User user = dao.findUser(username);
		System.out.println(user.getEmail());
	}
	
	@Test
	public void testFindUser(){
		String username = "aaa";
		String password = "123";
		UserDao dao = new UserDaoXmlImpl();
		User user = dao.findUser(username, password);
		System.out.println(user.getEmail());
	}
}
