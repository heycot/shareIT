<%@page import="java.util.ArrayList"%>
<%@page import="model.dao.AdverDao"%>
<%@page import="model.bean.Adver"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="col-lg-4 col-md-4 col-sm-4" style="width: 100%">
        <aside class="right_content">
          <div class="single_sidebar" style="background-color: white;">
            <h2><span>Quảng cáo</span></h2>
            <ul class="spost_nav">
            <%
            AdverDao vd = new AdverDao();
            ArrayList<Adver> listAdver = vd.getAdvers();
            if(listAdver.size() > 0){
            	for(Adver adver: listAdver){
            %>
            <li>
                <div class="media wow fadeInDown"> 
                	<a href="<%= adver.getLink()%>"  target="_blank" class="media-left"> 
                		<img alt="" src="<%= request.getContextPath()%>/files/<%= adver.getPicture()%>"> 
                	</a>
                  <div class="media-body"> 
                  	<a href="<%= adver.getLink()%>"   target="_blank" class="catg_title"><span style="color: red; font-weight: bold;"><%= adver.getName()%></span></a> 
                  </div>
                  <p style="margin-top: 10px;"><%= adver.getPreview()%></p>
                </div>
              </li>
            <%	}
            }
            %>
            </ul>
          </div>
        </aside>
      </div>