package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import library.StringLibrary;
import model.bean.Users;
import model.dao.UsersDao;

public class PublicLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PublicLoginController() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println(request.getServletContext().getRealPath(""));
		RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = StringLibrary.md5(request.getParameter("password"));
		
		if ("".equals(username) ) {
			request.setAttribute("error", "Vui lòng nhập username");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
			dispatcher.forward(request, response);
		} else if("".equals(password)){
			request.setAttribute("error", "Vui lòng nhập password");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
			dispatcher.forward(request, response);
		}else {
			UsersDao ud = new UsersDao();
			Users user = ud.getUser(username, password);
			if (user != null) {
				HttpSession session = request.getSession();
				session.setAttribute("userpublic", user);
				response.sendRedirect(request.getContextPath());
			} else {
				request.setAttribute("error", "Sai username hoặc password");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
				dispatcher.forward(request, response);
			}
		}
	}

}
