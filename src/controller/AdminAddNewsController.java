package controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import library.CheckLogin;
import library.fileLibrary;
import model.bean.Category;
import model.bean.News;
import model.bean.Users;
import model.dao.CategoryDao;
import model.dao.NewsDao;

@MultipartConfig
public class AdminAddNewsController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminAddNewsController() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(CheckLogin.checkLogin(request, response) == true) {
			CategoryDao cd = new CategoryDao();
			ArrayList<Category> listcat = cd.getCats();
			request.setAttribute("listCat", listcat);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/news/add.jsp");
			dispatcher.forward(request, response);
		} else {
			return;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		try {
			if(request.getParameter("name") == null || request.getParameter("preview") == null || request.getParameter("detail")== null ) {
				request.setAttribute("error", "Vui lòng điền tất cả các thông tin");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/news/add.jsp");
				dispatcher.forward(request, response);
			}else {

				String name = request.getParameter("name");
				int id_cat = Integer.parseInt(request.getParameter("danhmuc"));
				String preview = request.getParameter("preview");
				String detail = request.getParameter("detail");
				System.out.println("name " + name);
				System.out.println("mo ta " + preview);
				System.out.println("chi tiet " + detail);
				
				Part part = request.getPart("picture");
				String fileName = fileLibrary.getFileName(part);
				if (!fileName.isEmpty()) {
					System.out.println(fileName);
					String[] hinhanh = fileName.split("[.]");
					long date = System.currentTimeMillis();
					fileName = date + "." + hinhanh[hinhanh.length - 1];
					System.out.println(fileName);
					String dirPath = request.getServletContext().getRealPath("/files");
					File file = new File(dirPath);
					if (!file.exists()) {
						file.mkdirs();
					}

					String filePath = dirPath + File.separator + fileName;
					part.write(filePath);
					System.out.println(dirPath);
				}
//				HttpSession session = request.getSession();
//				Users user = (Users) session.getAttribute("user");
				NewsDao nd = new NewsDao();
//				int id_user = user.getId();
				int id_user = 1;
				News news = new News(0, name, preview, detail, id_cat, fileName, "", 1, id_user, "", 0, null);
				ArrayList<News> list = nd.getNewsByName(0, name);
				if( list.size() > 0) {
					CategoryDao cd = new CategoryDao();
					ArrayList<Category> listCat = cd.getCats();
					request.setAttribute("listCat", listCat);
					request.setAttribute("news", news);
					request.setAttribute("error", "Đã tồn tại tin tức này!");
					RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/news/add.jsp");
					dispatcher.forward(request, response);
				}
				int kq = nd.addNews(news);
				if (kq > 0) {
					response.sendRedirect(request.getContextPath() + "/admin/news?msg=1");
				} else {
					request.setAttribute("error", "Lỗi. không thêm được");
					RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/news/add.jsp");
					dispatcher.forward(request, response);
				}
			}
		} catch (Exception e) {
			CategoryDao cd = new CategoryDao();
			ArrayList<Category> listCat = cd.getCats();
			request.setAttribute("listCat", listCat);
			request.setAttribute("error", "Vui lòng điền tất cả các thông tin");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/news/add.jsp");
			dispatcher.forward(request, response);
			e.printStackTrace();
		}
	}

}
