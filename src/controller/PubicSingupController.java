package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library.StringLibrary;
import model.bean.Roles;
import model.bean.Users;
import model.dao.RolesDao;
import model.dao.UsersDao;

public class PubicSingupController extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public PubicSingupController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Users user = new Users(0, "", "", "", 0, "", "");
		request.setAttribute("userSingUp", user);
		RequestDispatcher rd = request.getRequestDispatcher("/singup.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		try {
			String username = request.getParameter("username");
			String password = StringLibrary.md5(request.getParameter("password"));
			String email = request.getParameter("email");
			String fullname = request.getParameter("fullname");
			int id_roles = 3;

			UsersDao ud = new UsersDao();
			Users user = new Users(0, username, password, fullname, id_roles, "", email);

			
			ArrayList<Users> list = ud.getUsers();
			boolean check = false;
			for(Users users:list) {
				if(users.getUsername().equals(user.getUsername()) || users.getEmail().equals(user.getEmail())) {
					check = true;
					break;
				}
			}
			if( check == true) {
				addFail(user, "Tài khoản này đã tồn tại. Đăng nhập để sử dụng", request, response);
			}else {

				int kq = ud.addUser(user);
				if(kq > 0) {
					response.sendRedirect(request.getContextPath()  + "/login?msg=1");
					return;
				}else {
					addFail(user, "Lỗi. Vui lòng thử lại sau", request, response);
				}
			}
		}catch( Exception e) {
			addFail(null, "Lỗi. Vui lòng thử lại sau", request, response);
			e.printStackTrace();
		}
	}
	
	public void addFail(Users user, String err, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RolesDao rod = new RolesDao();
		
		request.setAttribute("userSingUp", user);
		request.setAttribute("error", err);
		RequestDispatcher rd = request.getRequestDispatcher("/singup.jsp");
		rd.forward(request, response);
	
	}

}
