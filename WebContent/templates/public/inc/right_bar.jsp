<%@page import="library.StringLibrary"%>
<%@page import="model.bean.News"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.dao.NewsDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

      <div class="col-lg-4 col-md-4 col-sm-4" style="width: 100%">
        <div class="latest_post">
          <h2><span>Latest post</span></h2>
          <div class="latest_post_container">
            <div id="prev-button"><i class="fa fa-chevron-up"></i></div>
            <ul class="latest_postnav">
            <%
            	NewsDao ndNews = new NewsDao();
            	ArrayList<News> ltNews = ndNews.getNewLimit(0, 6);
            	if( ltNews.size() > 0){
            		for(int i = 0; i < ltNews.size(); i++){
    					String urlClugdetail = request.getContextPath() + "/chi-tiet/" + StringLibrary.makeSlug(ltNews.get(i).getName()) 
    					+ "-" + ltNews.get(i).getId() +".html";
            %>
            <li>
                <div class="media"> 
                	<a href="<%= urlClugdetail%>" class="media-left"> 
                		<img alt="" src="<%= request.getContextPath()%>/files/<%= ltNews.get(i).getPicture()%>"> 
                	</a>
                  	<div class="media-body"> 
                  		<a href="<%= urlClugdetail%>" class="catg_title"><span style="font-weight: bold; color: blue;"><%= ltNews.get(i).getName()%></span></a> 
                  	</div>
                  	<p><%= ltNews.get(i).getPreview()%></p>
                </div>
              </li>
            <%		}
            	}
            %>
            </ul>
            <div id="next-button"><i class="fa  fa-chevron-down"></i></div>
          </div>
        </div>
      </div>