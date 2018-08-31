package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Category;
import model.bean.News;
import model.dao.CategoryDao;
import model.dao.CommentDao;
import model.dao.NewsDao;


public class PublicCategoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public PublicCategoryController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int pages = 1;
		int start = 0;
		int count = 8;
		if(request.getParameter("pages") != null) {
			pages = Integer.parseInt(request.getParameter("pages"));
			start = (pages -1)*8;
			System.out.println("..............." + start);
		}
		if(request.getParameter("cid") != null) {
			int cid = Integer.parseInt(request.getParameter("cid"));
			
			CategoryDao cd = new CategoryDao();
			Category cat = cd.getCatById(cid);
			request.setAttribute("cat", cat);

			NewsDao nd = new NewsDao();
			int total = nd.getAllNewByIdCat(cid).size();
			request.setAttribute("total", String.valueOf(total));
			
			ArrayList<News> listNews = nd.getNewByIdCat(cid, start, count);
			
			request.setAttribute("listNews", listNews);
			request.setAttribute("pages", String.valueOf(pages));
			RequestDispatcher dispatcher = request.getRequestDispatcher("/cat.jsp");
			dispatcher.forward(request, response);
		}else {
			response.sendRedirect( request.getContextPath() + "");
			return;
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int id = Integer.parseInt(request.getParameter("id_news"));
			CommentDao cd = new CommentDao();
			int kq = cd.activeComment(0, id);
			PrintWriter out = response.getWriter();
			out.print("<div></div>");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
