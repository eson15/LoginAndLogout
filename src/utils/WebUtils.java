package utils;

import java.util.Enumeration;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.validator.constraints.Email;

public class WebUtils {
	
	public static<T> T requestToBean(HttpServletRequest request, Class<T> clazz){
		
		try {
			T bean = clazz.newInstance();
			Enumeration<String> e = request.getParameterNames();//get all parameter names
			while(e.hasMoreElements()){
				String name = e.nextElement();
				String value = request.getParameter(name); // get parameter values
				BeanUtils.setProperty(bean, name, value);
			}
			return bean;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}	
	}

	//create a unique id in the word
	public static String makeID(){
		return UUID.randomUUID().toString();
	}
}
