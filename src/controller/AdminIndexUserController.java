package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import library.CheckLogin;
import model.bean.Category;
import model.bean.Users;
import model.dao.CategoryDao;
import model.dao.UsersDao;


public class AdminIndexUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public AdminIndexUserController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(CheckLogin.checkLogin(request, response) == true){
			UsersDao ud = new UsersDao();
			ArrayList<Users> listUser = ud.getUsers();
			request.setAttribute("listUser", listUser);
			RequestDispatcher rd = request.getRequestDispatcher("/admin/user/index.jsp");
			rd.forward(request, response);
		} else {
			return;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
