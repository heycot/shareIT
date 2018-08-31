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
				int pages =  1;
				int total = 0;
			 	if (request.getAttribute("pages") != null) {
			        pages = Integer.parseInt((String)request.getAttribute("pages"));
			    }
		 		int idCat = 1;
		 		String cname = "";
				if(request.getAttribute("cat") != null){
					Category cat = (Category) request.getAttribute("cat");
					idCat = cat.getId();
					cname = cat.getName();
					%>
			<%	}
			%>
			<%
			if (request.getAttribute("listNews") != null && request.getAttribute("total") != null) {
				ArrayList<News> listnews = (ArrayList<News>) request.getAttribute("listNews");
				total = Integer.parseInt((String) request.getAttribute("total"));
				for (int i = 0; i < listnews.size(); i++) {
					String urlClugdetail = request.getContextPath() + "/chi-tiet/" + StringLibrary.makeSlug(listnews.get(i).getName()) 
					+ "-" + listnews.get(i).getId() +".html";
					
			%>
			<div class="single_post_content_left">
              <ul class="business_catgnav  wow fadeInDown">
                <li>
                   
                  <figure class="bsbig_fig"> 
                  	<a href="<%= urlClugdetail%>" class="featured_img" > 
                  		<img width=300px; height=250px; alt="" src="<%= request.getContextPath()%>/files/<%= listnews.get(i).getPicture()%>"> <span class="overlay"></span> 
                  	</a>
                  	<figcaption> 
                   	<a href="<%= urlClugdetail%>"><%= listnews.get(i).getName()%></a> 
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
			<div class="paginator">
				<table>
					<tr>
				<%
				if(total > 8){
					int back = 1;
					if(pages <= 1){
						back = 1;
					}else{back = pages -1;}
	
					String urlClugback = request.getContextPath() + "/danh-muc/" + StringLibrary.makeSlug(cname) 
							+ "-" + idCat + "/pages-" + back +".html";
				%>
				<td >
				<a style="text-decoration: none" href="<%=urlClugback %>" class="button_pages_back"> << </a>
				</td>
				<%
					int endpages = (int)Math.ceil((float)total/8);
					int start, end;
					if( pages == 1){
						start = 1;
						end = 3;
					}else if(pages == endpages){
						start = pages - 2;
						end = pages;
					}else{
						start = pages - 1;
						end = pages + 1;
					}
					
	               	//Lap so pages
	               	for (int i = start; i <= end; i++) {
	   					String urlClug = request.getContextPath() + "/danh-muc/" + StringLibrary.makeSlug(cname) 
	   							+ "-" + idCat + "/pages-" + i +".html";
	          			 	if (pages == i) {%> 
					<td>
						<a style="text-decoration: none; color: red" href="<%=urlClug%>"><%=i%></a>
					</td>
	           		<%} else {%>
	           		<td>
					<a style="text-decoration: none" href="<%=urlClug%>"><%=i%></a>
					</td>
	   				<%} }
	               	int next = pages;
	   				if(pages < endpages){
	   					next = pages + 1;
	   				}
	
					String urlClugnext = request.getContextPath() + "/danh-muc/" + StringLibrary.makeSlug(cname) 
							+ "-" + idCat + "/pages-" + next +".html";
	   				%>
	   				<td>
					<a style="text-decoration: none" href="<%= urlClugnext%>" class="button_pages_next">>></a>
					</td>
					<%} %>
					</tr>
					</table>
				</div>
			</div>
			<div class="col-lg-4 col-md-4 col-sm-4">
        		<%@include file="/templates/public/inc/right_bar.jsp" %>
      			<%@include file="/templates/public/inc/right_bar2.jsp"%>
      		</div>
        </div>
      </div>
    </div>
  </section>
  <%@include file="templates/public/inc/footer.jsp" %>