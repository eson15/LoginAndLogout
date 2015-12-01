package web.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;

import domain.RegisterFormBean;
import domain.User;
import exception.UserExistException;
import service.BusinessService;
import service.impl.BusinessServiceImpl;
import utils.WebUtils;

public class RegisterServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		RegisterFormBean formbean = WebUtils.requestToBean(request, RegisterFormBean.class);
		if(formbean.isValidate() == false){
			request.setAttribute("formbean", formbean);//formbean中封装了表单错误信息
			request.getRequestDispatcher("/WEB-INF/jsp/register.jsp").forward(request, response);//用来回显错误信息
			return;
		}
				
		try {
			//把formbean填充到user中去
			User user = new User();
			ConvertUtils.register(new DateLocaleConverter(), Date.class);//注册 字符串到日期的转换器
			BeanUtils.copyProperties(user, formbean);
			user.setId(WebUtils.makeID());
			
			BusinessService service = new BusinessServiceImpl();
			service.registerUser(user);
			request.setAttribute("message", "注册成功。本页面将在5秒后跳转到首页<meta http-equiv='refresh' content='5;url=/LoginAndLogout/index.jsp'");
			request.getSession().setAttribute("user", user);
			request.getRequestDispatcher("/message.jsp").forward(request, response);
			
		} catch(UserExistException e){ //这个异常可能在BusinessServiceImpl.java中的registerUser方法会抛出，这里需要对其进行处理，即用户已注册
			formbean.getErrors().put("username", "注册用户已存在");
			request.setAttribute("formbean", formbean);;
			request.getRequestDispatcher("/WEB-INF/jsp/register.jsp").forward(request, response);	
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "对不起，由于网络原因注册失败");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
		}
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
