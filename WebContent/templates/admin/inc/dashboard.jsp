<%@page import="model.bean.Users"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

  <head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <meta name="description" content="">
    <meta name="author" content="">
    <title>Admin</title>

    <!-- Bootstrap core CSS -->
    <link href="<%= request.getContextPath()%>/templates/admin/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom fonts for this template -->
    <link href="<%= request.getContextPath()%>/templates/admin/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- Plugin CSS -->
    <link href="<%= request.getContextPath()%>/templates/admin/vendor/datatables/dataTables.bootstrap4.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="<%= request.getContextPath()%>/templates/admin/css/sb-admin.css" rel="stylesheet">
	<link rel="shortcut icon" href="<%= request.getContextPath()%>/templates/public/images/logo.png" type="image/x-icon" />

 	 <script src="<%= request.getContextPath()%>/templates/admin/vendor/jquery/jquery.min.js"></script>
	<script src="<%= request.getContextPath()%>/templates/admin/vendor/jquery/jquery.validate.min.js"></script>

  	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

  <link rel="stylesheet prefetch" href="https://cdn.jsdelivr.net/bootstrap.tagsinput/0.8.0/bootstrap-tagsinput.css">
    <style type="text/css">
      .hiden{display:none}
      .error{color:red}

    .label-info {background-color: #5bc0de;}
    </style>

  </head>

  <body class="fixed-nav" id="page-top">

    <!-- Navigation -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top" id="mainNav">
      <a class="navbar-brand" href="">ADMIN CP</a>
      <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarResponsive">
        <ul class="navbar-nav navbar-sidenav">
          <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Dashboard">
            <a class="nav-link" href="/admin">
              <i class="fa fa-fw fa-dashboard"></i>
              <span class="nav-link-text">
                Dashboard</span>
            </a>
          </li>
          <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Components">
            <a class="nav-link nav-link-collapse collapsed" data-toggle="collapse" href="#user">
              <i class="fa fa-fw fa-user"></i>
              <span class="nav-link-text">
                User</span>
            </a>
            <ul class="sidenav-second-level collapse" id="user">
			<%
			Users user = (Users) session.getAttribute("user");
			if(user.getId_roles() == 1){
			%>
              <li>
                <a href="<%= request.getContextPath()%>/admin/user"><i class="fa fa-fw fa-users"></i> Người dùng</a>
              </li>
              <li>
                <a href="<%= request.getContextPath()%>/admin/user/add"><i class="fa fa-fw fa-plus"></i> Thêm người dùng</a>
              </li>
              <%}%>
              <li>
                <a href="<%= request.getContextPath()%>/admin"><i class="fa fa-fw fa-info"></i> Tài khoản của bạn</a>
              </li>
            </ul>
          </li>

          <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Components">
            <a class="nav-link nav-link-collapse collapsed" data-toggle="collapse" href="#book">
              <i class="fa fa-list" ></i>
              <span class="nav-link-text">
                Tin tức</span>
            </a>
            <ul class="sidenav-second-level collapse" id="book">
              <li>
                <a href="<%= request.getContextPath()%>/admin/news"><i class="fa fa-list" ></i> Tin</a>
              </li>
              <li>
                <a href="<%= request.getContextPath()%>/admin/news/add"><i class="fa fa-fw fa-plus"></i> Thêm tin</a>
              </li>
				<li>
                <a href="<%= request.getContextPath()%>/admin/comment"><i class="fa fa-comments-o" ></i> Comment</a>
              </li>
            </ul>
          </li>
          <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Components">
            <a class="nav-link nav-link-collapse collapsed" data-toggle="collapse" href="#cat">
              <i class="fa fa-fw fa-tag"></i>
              <span class="nav-link-text">
                Danh mục tin</span>
            </a>
            <ul class="sidenav-second-level collapse" id="cat">
              <li>
                <a href="<%= request.getContextPath()%>/admin/cat"><i class="fa fa-fw fa-tag"></i> Danh mục</a>
              </li>
              <li>
                <a href="<%= request.getContextPath()%>/admin/cat/add"><i class="fa fa-fw fa-plus"></i> Thêm danh mục</a>
              </li>
            </ul>
          </li>
        <li class="nav-item" data-toggle="tooltip" data-placement="right" title="contact">
            <a class="nav-link nav-link-collapse collapsed" data-toggle="collapse" href="#cmt">
              <i class="fa fa-product-hunt" ></i>
              <span class="nav-link-text">
                Quảng cáo</span>
          </a>
          <ul class="sidenav-second-level collapse" id="cmt">
            <li>
              <a href="<%= request.getContextPath()%>/admin/adver"><i class="fa fa-fw fa-list"></i> Quảng cáo</a>
            </li>
            <li>
              <a href="<%= request.getContextPath()%>/admin/adver/add"><i class="fa fa-fw fa-plus"></i> Thêm quảng cáo</a>
            </li>
          </ul>
        </li>
       
        <%
		if(user.getId_roles() == 1){
		%>
        <li class="nav-item" data-toggle="tooltip" data-placement="right" title="contact">
            <a class="nav-link nav-link-collapse collapsed" data-toggle="collapse" href="#tk">
              <i class="fa fa-database" ></i>
              <span class="nav-link-text">
                Thống kê</span>
          </a>
          <ul class="sidenav-second-level collapse" id="tk">
            <li>
              <a href="<%= request.getContextPath()%>/admin/statistical/adver"><i class="fa fa-database"></i> Quảng cáo</a>
            </li>
            <li>
              <a href="<%= request.getContextPath()%>/admin/statistical/news"><i class="fa fa-database"></i> Tin Tức</a>
            </li>
             <li>
               <a href="<%= request.getContextPath()%>/admin/statistical/user"><i class="fa fa-database"></i> User</a>
             </li>
             <li>
               <a href="<%= request.getContextPath()%>/admin/statistical/comment"><i class="fa fa-database"></i> Comment</a>
             </li>
          </ul>
        </li>
        <%}%>
        </ul>
        <script type="text/javascript">
      jQuery(document).ready(function($){
        var url = window.location.href;
          $(".nav-item a").each(function() {
              if(url == (this.href)) {
                  $(this).closest("li").addClass("active");
              }
          });
      });
     </script>
        <ul class="navbar-nav sidenav-toggler">
          <li class="nav-item">
            <a class="nav-link text-center" id="sidenavToggler">
              <i class="fa fa-fw fa-angle-left"></i>
            </a>
          </li>
        </ul>
        <ul class="navbar-nav ml-auto">

          <li class="nav-item">
            <a class="nav-link" data-toggle="modal" data-target="#exampleModal">
              <i class="fa fa-fw fa-sign-out"></i>
              Logout</a>
          </li>
        </ul>
      </div>
    </nav>
