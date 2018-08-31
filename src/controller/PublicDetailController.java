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

import model.bean.Comment;
import model.bean.News;
import model.dao.CommentDao;
import model.dao.NewsDao;

public class PublicDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PublicDetailController() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println(request.getParameter("nid"));
		if (request.getParameter("nid") == null) {
			NewsDao nd = new NewsDao();
			News news = nd.getNewByIdNewDefault();
			request.setAttribute("news", news);
			System.out.println("                             " + news.getUsername());
			System.out.println("                             " + news.getCname());
			
			CommentDao cd = new CommentDao();
			ArrayList<Comment> listcmt = cd.getCommentsByIdNews(news.getId());
			request.setAttribute("listComment", listcmt);
			
			
			ArrayList<News> listNews = nd.getNewsSameIdCat(news.getId_cat(), news.getId());
			request.setAttribute("listNews", listNews);
		}else {
			int nid = Integer.parseInt(request.getParameter("nid"));
			NewsDao nd = new NewsDao();
			News news = nd.getNewByIdNews(nid);
			request.setAttribute("news", news);
			System.out.println("                             " + news.getUsername());
			System.out.println("                             " + news.getCname());
			
			CommentDao cd = new CommentDao();
			ArrayList<Comment> listcmt = cd.getCommentsByIdNews(news.getId());
			request.setAttribute("listComment", listcmt);
			
			ArrayList<News> listNews = nd.getNewsSameIdCat(news.getId_cat(), news.getId());
			request.setAttribute("listNews", listNews);
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("/detail.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt((String) request.getParameter("aid"));
		NewsDao nd = new NewsDao();
		 News news = nd.getNewByIdNews(id);
		 int point = news.getPoint() + 1;
		 news.setPoint(point);
		 nd.editPointNews(news);
		 PrintWriter out = response.getWriter();
		 out.print(" <i class='fa fa-heart' aria-hidden='true'> " + point +"</i>");
	}

}
