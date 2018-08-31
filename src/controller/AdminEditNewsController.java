package controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
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
public class AdminEditNewsController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminEditNewsController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(CheckLogin.checkLogin(request, response) == true) {
			try {
				CategoryDao cd = new CategoryDao();
				ArrayList<Category> listcat = cd.getCats();
				request.setAttribute("listCat", listcat);

				if (request.getParameter("nid") != null) {
					int nid = Integer.parseInt(request.getParameter("nid"));
					
					NewsDao nd = new NewsDao();
					News news = nd.getNewByIdNews(nid);
					request.setAttribute("news", news);
				}else {
					response.sendRedirect(request.getContextPath() + "/admin/news?msg=2");
					return;
				}
			

				RequestDispatcher rd = request.getRequestDispatcher("/admin/news/edit.jsp");
				rd.forward(request, response);
			} catch (Exception e) {
				response.sendRedirect(request.getContextPath() + "/admin/news");
				return;
			}
		} else {
			return;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		try {
			int nid = Integer.parseInt(request.getParameter("nid"));
			String name = request.getParameter("name");
			int id_cat = Integer.parseInt(request.getParameter("danhmuc"));
			String preview = request.getParameter("preview");
			String detail = request.getParameter("detail");

			NewsDao nd = new NewsDao();
			
			if("".equals(name) || "".equals(preview) || "".equals(preview)) {
				
				String picture = nd.getNewByIdNews(nid).getPicture();
				News news = new News(nid, name, preview, detail, id_cat, picture, "", 1, 1, "", 1, null);

				editFail(news, "Vui lòng điền đầy đủ thông tin", request, response);
			}else {
				ArrayList<News> list = nd.getNewsByName(nid, name);
				if(list.size() > 0) {

					String picture = nd.getNewByIdNews(nid).getPicture();
					News news = new News(nid, name, preview, detail, id_cat, picture, "", 1, 1, "", 1, null);

					editFail(news, "Tin tức này đã tồn tại", request, response);
				}else {

				News news1 = nd.getNewByIdNews(nid);

				Part part = request.getPart("hinhanh");
				String fileName = fileLibrary.getFileName(part);
				if (!fileName.isEmpty()) {
					if("".equals(news1.getPicture())){
						String[] hinhanh = fileName.split("[.]");
						long date = System.currentTimeMillis();
						fileName = date + "." + hinhanh[hinhanh.length - 1];
					}else {
					fileName = news1.getPicture();
					}
					
					String filePath = request.getServletContext().getRealPath("/files") + File.separator + news1.getPicture();
					File file = new File( filePath);
					file.delete();
					
					String dirPath = request.getServletContext().getRealPath("/files");
					file = new File(dirPath);
					if (!file.exists()) {
						file.mkdirs();
					}

					filePath = dirPath + File.separator + fileName;
					part.write(filePath);
					System.out.println(dirPath);
				}else {
					fileName = news1.getPicture();
				}

				News news2 = new News(nid, name, preview, detail, id_cat, fileName, "", 0, 0, "", 0, null);
				int kq = nd.editNews(news2);
				if (kq > 0) {
					response.sendRedirect(request.getContextPath() + "/admin/news?msg=2");
				} else {
					editFail(news2, "Lỗi. Vui lòng thử lại sau", request, response);
				}
			}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void editFail(News news, String msg, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		CategoryDao cd = new CategoryDao();
		ArrayList<Category> listcat = cd.getCats();
		request.setAttribute("listCat", listcat);
		
		request.setAttribute("news", news);
		request.setAttribute("error", msg);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/news/edit.jsp");
		dispatcher.forward(request, response);
	}
}
