package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.Users;

public class AdminLogOut extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminLogOut() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Users user = (Users) request.getAttribute("user");
		if( user == null) {
			response.sendRedirect(request.getContextPath() + "/admin/login");
			return;
		}else {
		session.removeAttribute("user");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
