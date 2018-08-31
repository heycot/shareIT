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

public class AdminDeleteCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public AdminDeleteCatController() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(CheckLogin.checkLogin(request, response) == true) {
			try {
				int cid = Integer.parseInt(request.getParameter("cid"));
				CategoryDao cd = new CategoryDao();
				ArrayList<Category> list = cd.getCatsChildren(cid);
				if(list.size() > 0) {
					for(Category cat:list) {
						int kq = cd.deleteCat(cat.getId());
					}
				}
				int kq = cd.deleteCat(cid);
				if(kq > 0) {
					response.sendRedirect(request.getContextPath() + "/admin/cat?msg=3");
					return;
				}else {
					response.sendRedirect(request.getContextPath() + "/admin/cat?msg=0");
					return;
				}
			}catch( Exception e) {
				e.printStackTrace();
			}
		} else {
			return;
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(CheckLogin.checkLogin(request, response) == true) {
			try {
				int kq = 0;
				CategoryDao cd = new CategoryDao();
				ArrayList<Category> list = cd.getCats();
				for(Category cat: list) {

					if(request.getParameter(String.valueOf("item" + cat.getId())) != null) {
						int cid = Integer.parseInt(request.getParameter(String.valueOf("item" + cat.getId())));
						ArrayList<Category> listCat = cd.getCatsChildren(cid);
						if(list.size() > 0) {
							for(Category catt:listCat) {
								kq = cd.deleteCat(catt.getId());
							}
						}
						kq = cd.deleteCat(cid);
						
					}
				}
			
				if(kq > 0) {
					response.sendRedirect(request.getContextPath() + "/admin/cat?msg=3");
					return;
				}else {
					response.sendRedirect(request.getContextPath() + "/admin/cat?msg=0");
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
