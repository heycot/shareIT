<%@page import="library.StringLibrary"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="templates/public/inc/header.jsp" %>
  <section id="sliderSection">
    <div class="row">
      <div class="col-lg-8 col-md-8 col-sm-8">
        <div class="slick_slider">
        <%
        if( request.getAttribute("listNewsPoint") != null){
        ArrayList<News> listNewPoint = (ArrayList<News>) request.getAttribute("listNewsPoint");
        if( listNewPoint.size() > 0){
        	for(int i = 0; i < listNewPoint.size(); i++){
				String urlClugdetail = request.getContextPath() + "/chi-tiet/" + StringLibrary.makeSlug(listNewPoint.get(i).getName()) 
				+ "-" + listNewPoint.get(i).getId() +".html";
       	%>
        	<div class="single_iteam"> 
        		<a href="<%= urlClugdetail%>"> 
        			<img src="<%= request.getContextPath()%>/files/<%= listNewPoint.get(i).getPicture()%>" alt="">
        		</a>
            	<div class="slider_article">
              		<h2><a class="slider_tittle" href="<%= urlClugdetail%>"><%= listNewPoint.get(i).getName()%></a></h2>
              		<p><%= listNewPoint.get(i).getPreview()%></p>
            	</div>
          	</div>	
       <% 	}} }  %>
        </div>
      </div>
      <div class="col-lg-4 col-md-4 col-sm-4">
 	 	<%@include file="/templates/public/inc/right_bar.jsp" %>
 	 </div>
    </div>
  </section>
  <section id="contentSection">
    <div class="row">
      <div class="col-lg-8 col-md-8 col-sm-8">
        <div class="left_content">
          <div class="single_post_content">
          <%
          if(request.getAttribute("listNews") != null){
        	  ArrayList<News> listNew = ( ArrayList<News>) request.getAttribute("listNews");
        	  if(listNew.size() > 0){
        		  for(int i = 0; i < listNew.size(); i++){
        			  String urlClugdetail = request.getContextPath() + "/chi-tiet/" + StringLibrary.makeSlug(listNew.get(i).getName()) 
      				+ "-" + listNew.get(i).getId() +".html";
        %>		 
        	<div class="single_post_content_left">
              <ul class="business_catgnav  wow fadeInDown">
                <li>
                  <figure class="bsbig_fig"> 
                  	<a href="<%= urlClugdetail%>" class="featured_img"> 
                  		<img width=300px; height=250px; alt="" src="<%= request.getContextPath()%>/files/<%= listNew.get(i).getPicture()%>"> <span class="overlay"></span> 
                  	</a>
                    <figcaption> 
                    	<a href="<%= urlClugdetail%>"><%= listNew.get(i).getName()%></a> 
                    </figcaption>
                    <p><%= listNew.get(i).getPreview()%></p>
                  </figure>
                </li>
              </ul>
            </div>
         
        <%		  }
        	  }
          }
          
          %>
          </div>
        </div>
      </div>
      <div class="col-lg-4 col-md-4 col-sm-4">
 	 	<%@include file="/templates/public/inc/right_bar2.jsp" %>
 	 </div>
    </div>
  </section>
  <%@include file="templates/public/inc/footer.jsp" %>