package domain;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;

public class RegisterFormBean {
	
	private String username;
	private String password;
	private String password2;
	private String email;
	private String birthday;
	private Map<String, String> errors = new HashMap<String, String>();//store register error information
	
	public boolean isValidate(){
		boolean isOK = true;
		if(this.username == null || this.username.trim().equals("")){
			isOK = false;
			errors.put("username", "用户名不能为空");
		}
		else if(!this.username.matches("[a-zA-Z]{3,8}")) {//匹配3-8的任意字母
			isOK = false;
			errors.put("username", "用户名必须是3到8为的字母");
		}
		if(this.password == null || this.password.trim().equals("")) {
			isOK = false;
			errors.put("password", "密码不能为空");
		}
		else if(!this.password.matches("\\d{3,8}")) {//匹配3-8位任意数字
			isOK = false;
			errors.put("password", "密码必须是3到8位的数字");
		}
		
		if(this.password2 != null) {
			if(!this.password2.equals(this.password)) {
				isOK = false;
				errors.put("password2", "两次密码不一致");
			}
		}
		
		//xxx@sina.com
		if(this.email != ""){
			if(!this.email.matches("\\w+@\\w+(\\.\\w+)+")) {
				isOK = false;
				errors.put("email", "邮箱是非法邮箱");
			}
		}
		
		if(this.birthday != ""){
			try{
				DateLocaleConverter conver = new DateLocaleConverter();
				conver.convert(this.birthday);
			} catch (Exception e) {
				isOK = false;
				errors.put("birthday", "生日必须要是一个日期");
			}
		}
		return isOK;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword2() {
		return password2;
	}
	public void setPassword2(String password2) {
		this.password2 = password2;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public Map<String, String> getErrors() {
		return errors;
	}
	public void setErrors(Map<String, String> errors) {
		this.errors = errors;
	}
}
