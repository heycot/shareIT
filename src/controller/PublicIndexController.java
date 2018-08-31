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


public class PublicIndexController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public PublicIndexController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		NewsDao ndao = new NewsDao();
        ArrayList<News> listNewPoint = ndao.getNewsTopPoint();
        
        ArrayList<News> listNew = ndao.getNewLimit(0, 10);
        
        request.setAttribute("listNews", listNew);
        request.setAttribute("listNewsPoint", listNewPoint);
        
		RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
