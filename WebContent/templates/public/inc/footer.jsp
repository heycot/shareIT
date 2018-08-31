<%@page import="library.StringLibrary"%>
<%@page import="model.bean.Category"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.dao.CategoryDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <footer id="footer">
    <div class="footer_top">
      <div class="row">
        <div class="col-lg-4 col-md-4 col-sm-4">
          <div class="footer_widget wow fadeInLeftBig">
            <h2>introduce</h2>
            <p>Blog của chúng tôi hoạt động nhằm cung cấp cho độc giả các kiến thức bổ ích về lập trình website 
            cũng như các vấn đề trong học tập và làm việc mà nhiều bạn hay gặp phải hoàn toàn miễn phí.</p>
            <p>Chúng tôi cho phép các bạn bình luận, nêu ý kiến tự do nhưng trong những trường hợp có nội dung không phù hợp sẽ bị xóa.</p>
          	<p>Chúng tôi có các sản phẩm có ích từ nhà mua quảng cáo. Chúng tôi cam kết sẽ không ảnh hưởng đến bạn. </p>
          	<h4 style='text-align: center; font-family: inherit;'>Chúc các bạn học tập vui vẻ</h4>
          </div>
        </div>
        <div class="col-lg-4 col-md-4 col-sm-4">
          <div class="footer_widget wow fadeInDown">
            <h2>Tag</h2>
            <ul class="tag_nav">
            <%
            CategoryDao cdf = new CategoryDao();
            ArrayList<Category> listCatf = cdf.getCats();
            if(listCatf.size() > 0){
                for(int i = 0; i < listCatf.size(); i++){ 
					String urlClugcatf = request.getContextPath() + "/danh-muc/" + StringLibrary.makeSlug(listCatf.get(i).getName()) 
					+ "-" + listCatf.get(i).getId() +".html";
                %>
              <li><a href="<%= urlClugcatf%>"><%= listCatf.get(i).getName()%></a></li>
              <%}} %>
            </ul>
          </div>
        </div>
        <div class="col-lg-4 col-md-4 col-sm-4">
          <div class="footer_widget wow fadeInRightBig">
            <h2>Contact</h2>
            <p>Mọi liên hệ vui lòng gửi mail về địa chỉ: shareit2017@gmail.com.</p>
            <address>
            60 Ngô Sỹ Liên - Hòa Khánh Bắc - Liên Chiểu - Đà Nẵng, USA Phone: 123-326-789 Fax: 123-546-567
            </address>
          </div>
        </div>
      </div>
    </div>
    <div class="footer_bottom">
      <p class="copyright">Copyright &copy; 2017 <a href="<%= request.getContextPath()%>">Share It</a></p>
      <p class="developer">Developed By ShareItGroup</p>
    </div>
  </footer>
</div>
<script src="<%= request.getContextPath()%>/templates/public/js/jquery.min.js"></script> 
<script src="<%= request.getContextPath()%>/templates/public/js/wow.min.js"></script> 
<script src="<%= request.getContextPath()%>/templates/public/js/bootstrap.min.js"></script> 
<script src="<%= request.getContextPath()%>/templates/public/js/slick.min.js"></script> 
<script src="<%= request.getContextPath()%>/templates/public/js/jquery.li-scroller.1.0.js"></script> 
<script src="<%= request.getContextPath()%>/templates/public/js/jquery.newsTicker.min.js"></script> 
<script src="<%= request.getContextPath()%>/templates/public/js/jquery.fancybox.pack.js"></script> 
<script src="<%= request.getContextPath()%>/templates/public/js/custom.js"></script>
</body>
</html>