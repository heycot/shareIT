<%@page import="library.StringLibrary"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="templates/public/inc/header.jsp" %>
<div style="float: right;">
</div>
<section id="contentSection">
    <div class="row">
      <div class="col-lg-8 col-md-8 col-sm-8">
        <div class="left_content">
          	<div class="single_post_content">
			<%
				int total = 0;
		 		int idCat = 1;
		 		String search = "mmmmmmm";
				if(request.getAttribute("search") != null){
					search = (String) request.getAttribute("search");
			%>
			<h5 style="text-algin:center;">Kết quả cho: <%= search%></h5>
			<%	}
			%>
			<%
			if (request.getAttribute("listNewsSearch") != null && request.getAttribute("total") != null) {
				ArrayList<News> listnews = (ArrayList<News>) request.getAttribute("listNewsSearch");
				total = Integer.parseInt((String) request.getAttribute("total"));
				for (int i = 0; i < listnews.size(); i++) {
					String urlClugdetail = request.getContextPath() + "/chi-tiet/" + StringLibrary.makeSlug(listnews.get(i).getName()) 
					+ "-" + listnews.get(i).getId() +".html";
					
			%>
			<div class="single_post_content_left">
              <ul class="business_catgnav  wow fadeInDown">
                <li>
                   
                  <figure class="bsbig_fig"> 
                  	<a href="<%= request.getContextPath()%>/chi-tiet?nid=<%= listnews.get(i).getId()%>" class="featured_img" > 
                  		<img width=290px; height= 250px; alt="" src="<%= request.getContextPath()%>/files/<%= listnews.get(i).getPicture()%>"> <span class="overlay"></span> 
                  	</a>
                  	<figcaption> 
                   	<a href="<%= request.getContextPath()%>/chi-tiet?nid=<%= listnews.get(i).getId()%>"><%= listnews.get(i).getName()%></a> 
                   </figcaption>
                    <p style="float: right; "><%= listnews.get(i).getPreview()%></p>
                   
                  </figure>
                </li>
              </ul>
            </div>
			<%
				}
				}
			%>
     		 </div>
			</div>
        </div>
		<div class="col-lg-4 col-md-4 col-sm-4">
       		<%@include file="/templates/public/inc/right_bar.jsp" %>
   			<%@include file="/templates/public/inc/right_bar2.jsp"%>
   		</div>
      </div>
    </div>
  </section>
  <%@include file="templates/public/inc/footer.jsp" %>