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
import model.bean.News;
import model.bean.Users;
import model.dao.CategoryDao;
import model.dao.NewsDao;
import model.dao.UsersDao;

public class AdminDeleteUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public AdminDeleteUserController() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Users user = (Users) session.getAttribute("user");
		if (user != null) {
			try {
				int uid = Integer.parseInt(request.getParameter("uid"));
				if(user.getId_roles() == 1 && uid != 1) {
					UsersDao ud = new UsersDao();
					int kq = ud.deleteUser(uid);
					if(kq > 0) {
						response.sendRedirect(request.getContextPath() + "/admin/user?msg=3");
						return;
					}else {
						response.sendRedirect(request.getContextPath() + "/admin/user?msg=0");
						return;
					}
				}else {
					response.sendRedirect(request.getContextPath() + "/admin");
				}
			}catch( Exception e) {
				e.printStackTrace();
			}
		} else {
			response.sendRedirect(request.getContextPath() + "/admin/login");
			return;
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(CheckLogin.checkLogin(request, response) == true) {
			try {
				int kq = 0;
				UsersDao ud = new UsersDao();
				ArrayList<Users> list = ud.getUsers();
				for(Users user: list) {

					if(request.getParameter(String.valueOf("item" + user.getId())) != null) {
						int uid = Integer.parseInt(request.getParameter(String.valueOf("item" + user.getId())));
						NewsDao nd = new NewsDao();
						ArrayList<News> listNew = nd.getNewByIdUser(uid); 
						if(listNew.size() > 0) {
							for(News news:listNew) {
								kq = nd.EditNewUserCreatAt(news.getId());
							}
						}
						kq = ud.deleteUser(uid);
						
					}
				}
			
				if(kq > 0) {
					response.sendRedirect(request.getContextPath() + "/admin/user?msg=3");
					return;
				}else {
					response.sendRedirect(request.getContextPath() + "/admin/user?msg=0");
					return;
				}
			}catch( Exception e) {
				e.printStackTrace();
			}
		} else {
			return;
		}
	}

}
