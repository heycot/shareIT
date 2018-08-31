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
import library.StringLibrary;
import model.bean.Roles;
import model.bean.Users;
import model.dao.RolesDao;
import model.dao.UsersDao;

public class AdminUserProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminUserProfileController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(CheckLogin.checkLogin(request, response) == true) {
			RolesDao rod = new RolesDao();
			ArrayList<Roles> list = rod.getRoles();
			request.setAttribute("listRoles", list);
			RequestDispatcher rd = request.getRequestDispatcher("/admin/user/profile.jsp");
			rd.forward(request, response);
		}else {
			return;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		try {
			int uid = Integer.parseInt(request.getParameter("uid"));
			String fullname = request.getParameter("fullname");
			int id_roles = Integer.parseInt(request.getParameter("role_id"));
			
			String password = "";
			if( request.getParameter("password") == null) {
				HttpSession session  = request.getSession();
				Users user = (Users) session.getAttribute("user");
				password = user.getPassword();
			}else {
				password = StringLibrary.md5(request.getParameter("password"));
			}
			
			UsersDao ud = new UsersDao();
			Users user = new Users(uid, null, password, fullname, id_roles, null, null);
			int kq = ud.editUser(user);
			if (kq > 0) {
				response.sendRedirect(request.getContextPath() + "/admin/user?msg=2");
				return;
			} else {
				request.setAttribute("error", "Lỗi. Không sửa được");
				RequestDispatcher rd = request.getRequestDispatcher("/admin/user/profile.jsp");
				rd.forward(request, response);
			}
		} catch (Exception e) {
			request.setAttribute("error", "Lỗi.Vui lòng nhập lại");
			RequestDispatcher rd = request.getRequestDispatcher("/admin/user/profile.jsp");
			rd.forward(request, response);
			e.printStackTrace();
		}
	}

}
