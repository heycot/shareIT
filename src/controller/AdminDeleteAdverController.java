package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library.CheckLogin;
import model.bean.Adver;
import model.dao.AdverDao;

public class AdminDeleteAdverController extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public AdminDeleteAdverController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(CheckLogin.checkLogin(request, response) == true) {
			if(request.getParameter("id") != null) {
				int id = Integer.parseInt((String)request.getParameter("id"));
				AdverDao ad = new AdverDao();
				int kq = ad.activeComment(0, id);
				if( kq > 0) {
					response.sendRedirect(request.getContextPath() + "/admin/adver?msg=3");
					return;
				}else {
					response.sendRedirect(request.getContextPath() + "/admin/adver?msg=0");
					return;
				}
			}
		}else {
			return;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdverDao ad = new AdverDao();
		System.out.println("dlete post");
		int kq = 0;
		ArrayList<Adver> list = ad.getAdvers();
		for(Adver adver:list) {
			if(request.getParameter(String.valueOf("item" + adver.getId())) != null) {
				int id = Integer.parseInt(request.getParameter(String.valueOf("item" + adver.getId())));
				kq += ad.activeComment(0, id);
				System.out.println("delete " + id);
			}
		}
		if( kq > 0) {
			response.sendRedirect(request.getContextPath() + "/admin/adver?msg=3");
			return;
		}else {
			response.sendRedirect(request.getContextPath() + "/admin/adver?msg=0");
			return;
		}
	}

}
