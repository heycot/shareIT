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
import model.bean.Adver;
import model.bean.News;
import model.bean.Users;
import model.dao.AdverDao;
import model.dao.NewsDao;

public class AdminStatisticalNewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminStatisticalNewController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(CheckLogin.checkLogin(request, response) == true) {
			HttpSession session = request.getSession();
			Users user = (Users) session.getAttribute("user");
			if(user.getId_roles() == 1) {
				NewsDao nd = new NewsDao();
				ArrayList<News> list = nd.getNewByStatis();
				request.setAttribute("listNew", list);
				
				RequestDispatcher rd = request.getRequestDispatcher("/admin/thongke/new.jsp");
				rd.forward(request, response);
			}else {
				response.sendRedirect(request.getContextPath() + "/admin");
			}
		}else {
			return;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
