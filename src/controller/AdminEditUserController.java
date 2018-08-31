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

import library.StringLibrary;
import model.bean.Category;
import model.bean.Roles;
import model.bean.Users;
import model.dao.CategoryDao;
import model.dao.RolesDao;
import model.dao.UsersDao;

public class AdminEditUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminEditUserController() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Users admin = (Users) session.getAttribute("user");
		if (admin != null) {
			try {
				int uid = Integer.parseInt(request.getParameter("uid"));
				if (admin.getId() == uid || admin.getId_roles() == 1) {
					UsersDao ud = new UsersDao();
					Users user = ud.getUserById(uid);
					request.setAttribute("edituser", user);
					
					RolesDao rod = new RolesDao();
					ArrayList<Roles> list = rod.getRoles();
					request.setAttribute("listRoles", list);
					
					RequestDispatcher rd = request.getRequestDispatcher("/admin/user/edit.jsp");
					rd.forward(request, response);
				
				} else {
					response.sendRedirect(request.getContextPath() + "/admin");
					return;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			response.sendRedirect(request.getContextPath() + "/admin/login");
			return;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		try {
			int uid = Integer.parseInt(request.getParameter("uid"));
			String password = StringLibrary.md5(request.getParameter("password"));
			String fullname = request.getParameter("fullname");
			int id_roles = Integer.parseInt(request.getParameter("role_id"));
		
			UsersDao ud = new UsersDao();
			Users user = new Users(uid, null, password, fullname, id_roles, null, null);
			int kq = ud.editUser(user);
			if (kq > 0) {
				response.sendRedirect(request.getContextPath() + "/admin/user?kq=2");
				return;
			} else {
				request.setAttribute("error", "Lỗi. Không sửa được");
				RequestDispatcher rd = request.getRequestDispatcher("/admin/user/add.jsp");
				rd.forward(request, response);
			}
		} catch (Exception e) {
			request.setAttribute("error", "Lỗi.Vui lòng nhập lại");
			RequestDispatcher rd = request.getRequestDispatcher("/admin/user/add.jsp");
			rd.forward(request, response);
			e.printStackTrace();
		}
	}

}
