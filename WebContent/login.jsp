<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="templates/public/inc/header.jsp" %>
  <section id="sliderSection">
    <div class="row">
      <div class="col-lg-8 col-md-8 col-sm-8">
       <form action="<%= request.getContextPath()%>/login" method="post" name="Login_Form" id="add-post" class="form-signin">
		    <h3 class="form-signin-heading">Please Sign In</h3>
			  <% 
			  String error = "";
			  if(request.getAttribute("error") != null){
				  error = (String)request.getAttribute("error"); 
			  %>
			  <h5 style="color: red; text-algin: center;"><%= error%></h5>
			  <%} %>
			  <% 
			  if(request.getParameter("msg") != null){
				  int msg = Integer.parseInt((String) request.getParameter("msg"));
				  if(msg == 1){
			  %>
			  <h5 style="color: red; text-algin: center;">Đăng kí thành công. Đăng nhập để sử dụng</h5>
			  <%} }%>
			  <input type="text" class="form-control" name="username" placeholder="Username"  autofocus="" required />
			  <input type="password" class="form-control" name="password" placeholder="Password" required />
			  <button class="btn btn-lg btn-primary btn-block"  name="submit" value="Login" type="Submit">Login</button>
		</form> 
    <div style="width: 100%; height: 30px;"></div>
    </div>
  </section>
  <%@include file="templates/public/inc/footer.jsp" %>