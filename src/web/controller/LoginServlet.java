package web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.User;
import service.BusinessService;
import service.impl.BusinessServiceImpl;

public class LoginServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		BusinessService service = new BusinessServiceImpl();
		User user = service.loginUser(username, password);
		if(user != null){
			request.getSession().setAttribute("user", user);//将user存到session中
			request.setAttribute("message", "恭喜"+user.getUsername()+"登陆成功。本页面将在5秒后跳转到首页<meta http-equiv='refresh' content='5;url=/LoginAndLogout/index.jsp'");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
		}
		else{
			request.setAttribute("message", "对不起，用户名或密码错误！");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
