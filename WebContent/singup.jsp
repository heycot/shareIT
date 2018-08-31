<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="templates/public/inc/header.jsp" %>
  <section id="sliderSection">
    <div class="row">
      <div class="col-lg-8 col-md-8 col-sm-8">
      <%
         String msg ="";
         if(request.getAttribute("error") != null ){ 
        	 msg = (String) request.getAttribute("error");
         %>
            <h5 style="text-align: center; margin-top: 20px; color: red;"><%=  msg%></h5>
        <% } %>
		<form id="add-post" action="<%= request.getContextPath()%>/signup" method="post">
		<%
		if(request.getAttribute("userSingUp") != null){
		Users userSingUp = (Users) request.getAttribute("userSingUp");
		
		%>
			<div class="form-group">
				<label>Username</label>
				<input class="form-control" type="text" name="username" value="<%= userSingUp.getUsername()%>"/>
			</div>
			
			<div class="form-group">
				<label>Fullname</label>
				<input class="form-control" name="fullname" value="<%= userSingUp.getFullname()%>"/>
			</div>

			<div class="form-group">
				<label>Email</label>
				<input class="form-control" name="email" value="<%= userSingUp.getEmail()%>"/>
			</div>
			<div class="form-group">
				<label>Password</label>
				<input type="password" class="form-control" name="password" value="<%= userSingUp.getPassword()%>"/>
			</div>
			
			<div>
				<input class="btn btn-primary" type="submit" name="submit" value="Sign Up" />
			</div>
		<%} %>
		</form>
        <div style="width: 100%; height: 50px;"></div>
          	<script type="text/javascript">
   				$(document).ready(function() {
   					$("#add-post").validate({
   						ignore: [],
   			            debug: false,
   						rules: {
							username: { 
								required : true,
								minlength: 6
							},
							fullname: { 
								required : true
							},
							email: { 
								required : true,
								email : true
							},
							password: { 
								required : true,
								minlength: 6
							}
 						},
 					messages: {
						username: 
						{
							required : "Please enter this field",
							minlength : "The length of username must be longer than 6"
						},
						fullname:{
							required: "Please enter this field"
						},
						email:{
							required: "Please enter this field",
							email : "Please enter an email"
						},
						password:{
							required: "Please enter this field",
							minlength : "The length of password must be longer than 6"
						}
  					}
  				});
  			});
    		</script>
        </div>
  <%@include file="templates/public/inc/footer.jsp" %>