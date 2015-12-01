package utils;

import java.io.InputStream;
import java.util.Properties;

import dao.UserDao;
/**
 * @author Ni Shengwu
 * @description DaoFactory is used to create a usedao according to properties file which use Singleton Pattern
 */
public class DaoFactory {

	private static UserDao userdao = null;
	
	private DaoFactory() {
		try {
			InputStream in = DaoFactory.class.getClassLoader().getResourceAsStream("daoconfig.properties");
			Properties prop = new Properties();
			prop.load(in);
			
			String className = prop.getProperty("userdao");
			userdao = (UserDao) Class.forName(className).newInstance();//create a userdao according to classname
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	private static final DaoFactory instance = new DaoFactory();
	
	//return a instance of DaoFactory
	public static DaoFactory getInstance(){
		return instance;
	}
	
	//return a UserDao object
	public UserDao createUserDao(){
		return userdao;
	}
	
}
