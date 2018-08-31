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
import javax.servlet.http.Part;

import library.CheckLogin;
import library.fileLibrary;
import model.bean.Adver;
import model.dao.AdverDao;
@MultipartConfig
public class AdminAddAdverController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public AdminAddAdverController() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(CheckLogin.checkLogin(request, response) == true) {
			Adver adver = new Adver(0, "", "", "", null, 0, 0, "");
			request.setAttribute("adver", adver);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/adver/add.jsp");
			dispatcher.forward(request, response);
		} else {
			return;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		try {

			String name = request.getParameter("name");
			int money = Integer.parseInt(request.getParameter("money"));
			String link = request.getParameter("link");
			String preview = request.getParameter("preview");
			
			if("".equals(name) || "".equals(request.getParameter("money")) || "".equals(link)) {

				Adver adver = new Adver(0, name, "", link, null, 0, money, preview);
				request.setAttribute("adver", adver);
				request.setAttribute("error", "Lỗi. không thêm được");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/adver/add.jsp");
				dispatcher.forward(request, response);
			}
			
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
			
			AdverDao ad = new AdverDao();
			Adver adver = new Adver(0, name, fileName, link, null, 0, money, preview);
			ArrayList<Adver> list = ad.getAdverByName(0, name);
			
			if( list.size() > 0) {
				request.setAttribute("adver", adver);
				request.setAttribute("error", "Đã tồn tại quảng cáo này!");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/adver/add.jsp");
				dispatcher.forward(request, response);
			}
			int kq = ad.addAdver(adver);
			if (kq > 0) {
				response.sendRedirect(request.getContextPath() + "/admin/adver?msg=1");
			} else {
				request.setAttribute("adver", adver);
				request.setAttribute("error", "Lỗi. không thêm được");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/adver/add.jsp");
				dispatcher.forward(request, response);
			}
		} catch (Exception e) {
			request.setAttribute("error", "Lỗi. Nhập số tiền là số nguyên");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/adver/add.jsp");
			dispatcher.forward(request, response);
			e.printStackTrace();
		}
	}

}
