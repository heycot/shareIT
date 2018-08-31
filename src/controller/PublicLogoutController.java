package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.Users;

public class PublicLogoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PublicLogoutController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Users user = (Users) request.getAttribute("userpublic");
		if( user == null) {
			response.sendRedirect(request.getContextPath());
			return;
		}else {
		session.removeAttribute("userpublic");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Users user = (Users) request.getAttribute("userpublic");
		if( user == null) {
			response.sendRedirect(request.getContextPath());
			return;
		}else {
		session.removeAttribute("userpublic");
		}
	}

}
