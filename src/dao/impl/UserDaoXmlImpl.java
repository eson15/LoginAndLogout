package dao.impl;

import java.text.SimpleDateFormat;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;

import utils.XmlUtils;
import dao.UserDao;
import domain.User;
/**
 * @author Ni Shengwu
 * @description This dao is to operate xml file including add user to xml file and find user from xml file
 */
public class UserDaoXmlImpl implements UserDao {
	
	@Override
	public void addUser(User user){
		try {
			Document document = XmlUtils.getDocument();
			Element root = document.getRootElement();
			
			Element user_node = root.addElement("user");//create user node and been son of root
			user_node.setAttributeValue("id", user.getId());
			user_node.setAttributeValue("username", user.getUsername());
			user_node.setAttributeValue("password", user.getPassword());
			user_node.setAttributeValue("email", user.getEmail());
			user_node.setAttributeValue("birthday", user.getBirthday().toLocaleString());
			
			XmlUtils.WriteToXml(document);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public User findUser(String username){
		try {
			Document document = XmlUtils.getDocument();
			Element e = (Element) document.selectSingleNode("//user[@username='"+username+"']");//find element by xpath
			
			if(e == null) return null;
			User user = new User();
			user.setId(e.attributeValue("id"));
			user.setUsername(e.attributeValue("username"));
			user.setPassword(e.attributeValue("password"));
			user.setEmail(e.attributeValue("email"));
			String birthday = e.attributeValue("birthday");//get a String birthday not Date
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");//set date format
			user.setBirthday(sdf.parse(birthday));
			
			return user;
	
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public User findUser(String username, String password){
		try {
			Document document = XmlUtils.getDocument();
			Element e = (Element) document.selectSingleNode("//user[@username='"+username+"' and @password='"+password+"']");//find element by xpath
			
			if(e == null) return null;
			User user = new User();
			user.setId(e.attributeValue("id"));
			user.setUsername(e.attributeValue("username"));
			user.setPassword(e.attributeValue("password"));
			user.setEmail(e.attributeValue("email"));
			String birthday = e.attributeValue("birthday");//get a String birthday not Date
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");//set date format
			user.setBirthday(sdf.parse(birthday));
			
			return user;
	
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
