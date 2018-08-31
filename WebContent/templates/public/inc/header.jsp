<!DOCTYPE html>
<%@page import="library.StringLibrary"%>
<%@page import="model.bean.Users"%>
<%@page import="model.bean.News"%>
<%@page import="model.dao.NewsDao"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="model.bean.Category"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.dao.CategoryDao"%>
<html>
<head>
<title>NewsFeed</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/templates/public/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/templates/public/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/templates/public/css/animate.css">
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/templates/public/css/font.css">
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/templates/public/css/li-scroller.css">
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/templates/public/css/slick.css">
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/templates/public/css/jquery.fancybox.css">
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/templates/public/css/theme.css">
<link rel="stylesheet"  type="text/css" href="<%= request.getContextPath()%>/templates/admin/css/bootstrap.min.css">
<link href="<%= request.getContextPath()%>/admin/style.css" type="text/css" rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/templates/public/css/style.css">
<link rel="shortcut icon" href="<%= request.getContextPath()%>/templates/public/images/logo.png" type="image/x-icon" />

<script src="<%= request.getContextPath()%>/templates/public/js/html5shiv.min.js"></script>
<script src="<%= request.getContextPath()%>/templates/public/js/respond.min.js"></script>


</head>
<body>
<div id="preloader">
  <div id="status">&nbsp;</div>
</div>
<a class="scrollToTop" href="#"><i class="fa fa-angle-up"></i></a>
<div class="containerPage">
  <section id="navArea">
    <nav class="navbar navbar-inverse" role="navigation">
      <div class="navbar-header">
        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar"> <span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </button>
      </div>
      <div id="navbar" class="navbar-collapse collapse">
        <ul class="nav navbar-nav main_nav">
          <li class="active"><a href="<%= request.getContextPath()%>"><span class="fa fa-home desktop-home"></span><span class="mobile-show">Home</span></a></li>
          <%
          CategoryDao cd = new CategoryDao();
          ArrayList<Category> listCat = cd.getCats();
          if(listCat.size() > 0){
              for(int i = 0; i < listCat.size(); i++){
            	if(listCat.get(i).getId_parent() == 0){
        		int id = listCat.get(i).getId();
       		 	ArrayList<Category> list = cd.getCatsChildren(id);
        		if( list.size() == 0){
					String urlClugcatd = request.getContextPath() + "/danh-muc/" + StringLibrary.makeSlug(listCat.get(i).getName()) 
					+ "-" + listCat.get(i).getId() +".html";
      			 %>
             	<li><a href="<%= urlClugcatd%>"><%= listCat.get(i).getName()%></a></li>
            	<%	
        		}else{
       			%>
       			<li class="dropdown"> 
          			<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><%= listCat.get(i).getName()%></a>
       			<ul class="dropdown-menu" role="menu">
       		 	<%
       	  			 for(int j = 0; j < list.size(); j++){
     					String urlClugcatc = request.getContextPath() + "/danh-muc/" + StringLibrary.makeSlug(list.get(j).getName()) 
    					+ "-" + list.get(j).getId() +".html";
       			%>
        		 	 <li><a href="<%= urlClugcatc%>"><%= list.get(j).getName()%></a></li>
        		<%   } %>
  				</ul>
          		</li>   
          	<%}}} }%>
        </ul>
		<div style="float: right; width: 120px;">
		<ul>
		<%
		Users userpublic = (Users) session.getAttribute("userpublic");
		if(userpublic == null){
			String url = request.getRequestURI();
			%>
            <li class="botton-header"><a href="<%= request.getContextPath()%>/login" title="Login">Login</a></li>
            <% %>
            <li class="botton-header"><a href="<%= request.getContextPath()%>/signup" title="Signup">Signup</a></li>
        <%
		}else{
			%>
			<li class="dropdown">
            <a href="javascript:;" id="usernamebotton" class="dropdown-toggle xb-login-toggle-button" data-toggle="dropdown"><%= userpublic.getUsername() %><span class="caret"></span></a>
            <ul class="xb-login-inner dropdown-menu">

                <li id="logout-botton">
                    <a style="color: white;" href="<%= request.getContextPath()%>/logout" title="Logout" data-method="post">Logout</a>
                </li>
            </ul>
        	</li>
  		  <% } %>
		</ul>
		</div>
	    <div id="search" class="sb-search">
			<form action="<%= request.getContextPath()%>/search" method="post">
				<input class="search_text" placeholder="Enter your search term..." type="search" value="" name="search">
				<input class="search_botton" type="submit" value="">
				<span class="sb-icon-search"></span>
			</form>
		</div>
      </div>
    </nav>
  </section>