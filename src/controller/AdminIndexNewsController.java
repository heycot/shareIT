package controller;

import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library.CheckLogin;
import model.bean.News;
import model.dao.NewsDao;

public class AdminIndexNewsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public AdminIndexNewsController() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response );
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(CheckLogin.checkLogin(request, response) == true){
			NewsDao nd = new NewsDao();
			
			ArrayList<News> list =  nd.getNews();
			request.setAttribute("listNews", list);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/news/index.jsp");
			dispatcher.forward(request, response);
		}else {
			return;
		}
	}

}
