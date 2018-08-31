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
public class AdminEditAdverController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public AdminEditAdverController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(CheckLogin.checkLogin(request, response) == true) {
			try {
				if (request.getParameter("id") != null) {
					int id = Integer.parseInt(request.getParameter("id"));
	
					AdverDao ad = new AdverDao();
					Adver adver = ad.getAdverById(id);
					request.setAttribute("adver", adver);
					RequestDispatcher rd = request.getRequestDispatcher("/admin/adver/edit.jsp");
					rd.forward(request, response);
				}else {
					response.sendRedirect(request.getContextPath() + "/admin/adver?msg=5");
					return;
				}
			} catch (Exception e) {
				response.sendRedirect(request.getContextPath() + "/admin/adver");
				return;
			}
		} else {
			return;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			String name = request.getParameter("name");
			int money = Integer.parseInt(request.getParameter("money"));
			String link = request.getParameter("link");
			String preview = request.getParameter("preview");
			

			AdverDao ad = new AdverDao();
			
			if("".equals(name) || "".equals(request.getParameter("money")) || "".equals(link)) {
				
				String picture = ad.getAdverById(id).getPicture();
				Adver adver = new Adver(id, name, picture, link, null, 0, money, preview);

				editFail(adver, "Vui lòng điền đầy đủ thông tin", request, response);
			}else {
				ArrayList<Adver> list = ad.getAdverByName(id, name);
				if(list.size() > 0) {
					String picture = ad.getAdverById(id).getPicture();
					Adver adver = new Adver(id, name, picture, link, null, 0, money, preview);
					editFail(adver, "Quảng cáo này đã tồn tại", request, response);
				}else {

				Adver adver = ad.getAdverById(id);

				Part part = request.getPart("hinhanh");
				String fileName = fileLibrary.getFileName(part);
				if (!fileName.isEmpty()) {
					if("".equals(adver.getPicture())){
						String[] hinhanh = fileName.split("[.]");
						long date = System.currentTimeMillis();
						fileName = date + "." + hinhanh[hinhanh.length - 1];
					}else {
					fileName = adver.getPicture();
					}
					
					String filePath = request.getServletContext().getRealPath("/files") + File.separator + adver.getPicture();
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
					fileName = adver.getPicture();
				}

				Adver adver2 = new Adver(id, name, fileName, link, null, 0, money, preview);
				int kq = ad.editAdver(adver2);
				if (kq > 0) {
					response.sendRedirect(request.getContextPath() + "/admin/adver?msg=2");
					return;
				} else {
					editFail(adver2, "Lỗi. Vui lòng thử lại sau", request, response);
				}
			}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void editFail(Adver adver, String msg, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		
		request.setAttribute("adver", adver);
		request.setAttribute("error", msg);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/adver/edit.jsp");
		dispatcher.forward(request, response);
	}

}
