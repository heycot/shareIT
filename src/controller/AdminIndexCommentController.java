package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library.CheckLogin;
import model.bean.Comment;
import model.dao.CommentDao;

public class AdminIndexCommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public AdminIndexCommentController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(CheckLogin.checkLogin(request, response) == true) {
			CommentDao cd = new CommentDao();
			ArrayList<Comment> list = cd.getComments();
			for(Comment cmt:list) {
				System.out.println(cmt.getContent());
			}
			request.setAttribute("listCmt", list);
			RequestDispatcher rd = request.getRequestDispatcher("/admin/comment/index.jsp");
			rd.forward(request, response);
		}else {return;}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		int id = Integer.parseInt((String)request.getParameter("aid"));
		int active = Integer.parseInt((String) request.getParameter("aactive"));
		CommentDao cd = new CommentDao();
		int kq = cd.activeComment((1 - active), id);
		PrintWriter out = response.getWriter();
		if(1 == active) {
			out.print("<a href='javascript:void(0)' style='display: inline-block, width: 10px; height: 10px;' onclick='checkActive(0, <%=cmt.getId()%>);'><img src=" + request.getContextPath() + "/templates/admin/images/deactive.gif></a>");
		} else {
			out.print("<a href='javascript:void(0)' style='display: inline-block, width: 10px; height: 10px;'  onclick='checkActive(0, <%=cmt.getId()%>);'><img src=" + request.getContextPath() + "/templates/admin/images/active.gif ></a>");
		}
	}

}
