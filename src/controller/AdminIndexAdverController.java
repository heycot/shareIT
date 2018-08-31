package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library.CheckLogin;
import model.bean.Adver;
import model.dao.AdverDao;
import model.dao.CommentDao;

public class AdminIndexAdverController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public AdminIndexAdverController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(CheckLogin.checkLogin(request, response) == true){
			AdverDao ad = new AdverDao();
			ArrayList<Adver> list = ad.getAdvers();
			request.setAttribute("listAdver", list);
			RequestDispatcher rd = request.getRequestDispatcher("/admin/adver/index.jsp");
			rd.forward(request, response);
		} else {
			return;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		int id = Integer.parseInt((String)request.getParameter("aid"));
		int active = Integer.parseInt((String) request.getParameter("aactive"));
		AdverDao ad = new AdverDao();
		int kq = ad.activeComment((1 - active), id);
		PrintWriter out = response.getWriter();
		if(1 == active) {
			out.print("<a href='javascript:void(0)' style='display: inline-block, width: 10px; height: 10px;' onclick='checkActive(0, <%=cmt.getId()%>);'><img src=" + request.getContextPath() + "/templates/admin/images/deactive.gif></a>");
		} else {
			out.print("<a href='javascript:void(0)' style='display: inline-block, width: 10px; height: 10px;'  onclick='checkActive(0, <%=cmt.getId()%>);'><img src=" + request.getContextPath() + "/templates/admin/images/active.gif ></a>");
		}
	}

}
