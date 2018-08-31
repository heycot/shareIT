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

public class AdminEditCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminEditCatController() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(CheckLogin.checkLogin(request, response) == true){
			try {
				int cid = Integer.parseInt(request.getParameter("cid"));
				System.out.println(cid);
				CategoryDao cd = new CategoryDao();
				Category cat = cd.getCatById(cid);
				ArrayList<Category> listCat = cd.getCats();
				
				request.setAttribute("listCat", listCat);
				request.setAttribute("cat", cat);
				RequestDispatcher rd = request.getRequestDispatcher("/admin/categories/edit.jsp");
				rd.forward(request, response);
			}catch( Exception e) {
				e.printStackTrace();
			}
		} else {
			return;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		if (request.getParameter("name") != null ) {
			try {
				CategoryDao cd = new CategoryDao();
				String name = request.getParameter("name");
				int cid = Integer.parseInt(request.getParameter("cid"));
				int idfa = 0;
				if(!request.getParameter("nameFa").equals("") ) {
					idfa = Integer.parseInt(request.getParameter("nameFa"));
				}

				Category cat = new Category(cid, name, idfa);
				ArrayList<Category> list = cd.getCatByName(cid, name);
				if( list.size() > 0) {
					System.out.println(list.get(0).getName());
					ArrayList<Category> listCat = cd.getCats();
					request.setAttribute("listCat", listCat);
					request.setAttribute("cat", cat);
					request.setAttribute("error", "Danh mục này đã tồn tại!");
					RequestDispatcher rd = request.getRequestDispatcher("/admin/categories/edit.jsp");
					rd.forward(request, response);
				}else {
					int kq = cd.editCat(cat);
					if (kq > 0) {
						response.sendRedirect(request.getContextPath() + "/admin/cat?msg=2");
						return;
					} else {
						ArrayList<Category> listCat = cd.getCats();
						request.setAttribute("listCat", listCat);
						request.setAttribute("cat", cat);
						request.setAttribute("error", "không thể sửa danh mục này");
						RequestDispatcher rd = request.getRequestDispatcher("/admin/categories/edit.jsp");
						rd.forward(request, response);
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			request.setAttribute("error", "Vui lòng nhập tên danh mục tin");
			RequestDispatcher rd = request.getRequestDispatcher("/admin/categories/edit.jsp");
			rd.forward(request, response);
		}
	}

}
