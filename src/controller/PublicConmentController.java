package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.Comment;
import model.bean.Users;
import model.dao.CommentDao;

public class PublicConmentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PublicConmentController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		if( request.getParameter("content") != null) {
			try {
				String content = request.getParameter("content");
				int nid = Integer.parseInt(request.getParameter("id_news"));
				HttpSession session = request.getSession();
				Users userpublic = (Users) session.getAttribute("userpublic");

				
				CommentDao cd = new CommentDao();
				Date date = new Date();
				Comment cmt = new Comment(0, userpublic.getId(), userpublic.getUsername(), nid, "", content, null, 1);
				int kq = cd.addComment(cmt);
				PrintWriter out = response.getWriter();
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); 
				System.out.println(cmt.getCreate_at());
				String datestr = sdf.format(date);
				out.print(" <div class='media'>" + 
						"	 <div class='media-left'>" + 
						"	 <a href='javascript:;'>" + 
						"	 <img class='media-object' style='width: 50px; height: 50px; border-radius: 24px;' src='" + request.getContextPath() + "/templates/public/images/nophoto.png' alt='" + cmt.getUsername() + "'>" + 
						"	</a>" + 
						"	</div>" + 
						"	 <div class='media-body'>" + 
						"	<div style='float: left; margin-right: 5px;'>" + 
						"	 <h4 class='media-heading'>" + 
						"	<span class='username' style='display: inline-block;'>" + cmt.getUsername() + "</span>" + 
						"	</h4>" + 
						"	</div>" + 
						"	<div style='float: left; max-width: 700px; margin-left: 5px; border-radius: 12px; box-shadow: 0 2px 4px 0 rgba(0,0,0,0.16),0 2px 10px 0 rgba(0,0,0,0.12)!important; background-color: #f1f1f1;'>" + 
						"	<p style='padding-left: 5px; padding-right: 15px;'>" + content  + "</p>" + 
						"	<p class='date' style='display: inline-block; padding-left: 5px;'>" + datestr + "</p>" + 
						"	</div>" + 
						"	 </div>" + 
						"	 </div>");
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else {
			
		}
	}

}
