package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library.CheckLogin;
import model.bean.Comment;
import model.dao.CommentDao;

public class AdminDeleteCommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AdminDeleteCommentController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(CheckLogin.checkLogin(request, response) == true) {
			int id = Integer.parseInt((String) request.getParameter("cmtid"));
			CommentDao cd = new CommentDao();
			int kq = cd.deleteComment(id);
			if( kq > 0) {
				response.sendRedirect(request.getContextPath() + "/admin/comment?msg=3");
				return;
			}else {
				response.sendRedirect(request.getContextPath() + "/admin/comment?msg=0");
				return;
			}
		}else {
			return;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int kq = 0;
		CommentDao cd = new CommentDao();
		ArrayList<Comment> list = cd.getComments();
		for(Comment cmt:list) {
			if(request.getParameter(String.valueOf("item" + cmt.getId())) != null){
				int id = Integer.parseInt(request.getParameter(String.valueOf("item" + cmt.getId())));
				kq = cd.deleteComment(id);
			}
		}
		if( kq > 0) {
			response.sendRedirect(request.getContextPath() + "/admin/comment?msg=3");
			return;
		}else {
			response.sendRedirect(request.getContextPath() + "/admin/comment?msg=0");
			return;
		}
	}

}
