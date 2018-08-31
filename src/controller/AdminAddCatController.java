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


public class AdminAddCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public AdminAddCatController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws  IOException, ServletException {
		if(CheckLogin.checkLogin(request, response) == false) {
			return;
		}else {
			CategoryDao cd = new CategoryDao();
			ArrayList<Category> list = cd.getCats();
			request.setAttribute("listCat", list);
			RequestDispatcher rd = request.getRequestDispatcher("/admin/categories/add.jsp");
			rd.forward(request, response);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		if(request.getParameter("name") != null) {
			CategoryDao cd = new CategoryDao();
			String name = request.getParameter("name");
			int idcat = Integer.parseInt((String) request.getParameter("nameFa"));
			Category cat = new Category(0, name, idcat);
			ArrayList<Category> list = cd.getCatByName(0, name);
			if( list.size() > 0) {
				ArrayList<Category> listCat = cd.getCats();
				request.setAttribute("listCat", listCat);
				request.setAttribute("cat", cat);
				request.setAttribute("error", "Đã tồn tại danh mục này!");
				RequestDispatcher rd = request.getRequestDispatcher("/admin/categories/add.jsp");
				rd.forward(request, response);
			}else {
				int kq = cd.addCat(cat);
				if(kq > 0) {
					response.sendRedirect(request.getContextPath()  + "/admin/cat?msg=1");
					return;
				}else {
					ArrayList<Category> listCat = cd.getCats();
					request.setAttribute("listCat", list);
					request.setAttribute("cat", cat);
					request.setAttribute("error", "Lỗi. Không thêm được");
					RequestDispatcher rd = request.getRequestDispatcher("/admin/categories/add.jsp");
					rd.forward(request, response);
				}
			}
		}else {
			request.setAttribute("error", "Vui lòng nhập tên danh mục tin");
			RequestDispatcher rd = request.getRequestDispatcher("/admin/categories/add.jsp");
			rd.forward(request, response);
		}
	}

}
