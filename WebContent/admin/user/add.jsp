<%@page import="java.util.ArrayList"%>
<%@page import="model.bean.Roles"%>
<%@page import="model.bean.Users"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/templates/admin/inc/dashboard.jsp" %>
<div class="content-wrapper py-3">
  <div class="container-fluid">
        <div class="card mb-3">
          <div class="card-header">
            <i class="fa fa-fw fa-code"></i>
            Add User
          </div>
          <%
         String msg ="";
         if(request.getAttribute("error") != null ){ 
        	 msg = (String) request.getAttribute("error");
         %>
            <h5 style="text-align: center; margin-top: 20px; color: red;"><%=  msg%></h5>
        <% } %>	
          <div class="card-body">
				<%
					if(request.getAttribute("adduser") != null && request.getAttribute("listRoles") != null) {
						Users adduser = (Users) request.getAttribute("adduser");
						ArrayList<Roles> listRoles = (ArrayList<Roles>) request.getAttribute("listRoles");
				%>
				<form id="add-post" action="<%= request.getContextPath()%>/admin/user/add" method="post">
					<div class="form-group">
						<label>Username</label>
						<input class="form-control" type="text" name="username" value="<%= adduser.getUsername()%>"/>
					</div>
					
					<div class="form-group">
						<label>Fullname</label>
						<input class="form-control" name="fullname" value="<%= adduser.getFullname()%>"/>
					</div>

					<div class="form-group">
						<label>Email</label>
						<input class="form-control" name="email" value="<%= adduser.getEmail()%>"/>
					</div>
					<div class="form-group">
		              	<label>Role</label>
		              	<select class="form-control" name="role_id">
						<% 
							for(Roles roles:listRoles){ 
							String selected="" ;
							if(adduser.getId_roles() == roles.getId()) selected="selected";
						%>
							<option <%= selected %> value="<%= roles.getId()%>" ><%= roles.getName()%></option>
						<% } %>
						</select>
					</div>
					<div class="form-group">
							<label>Password</label>
							<input type="password" class="form-control" name="password" value="<%= adduser.getPassword()%>"/>
					</div>
					
					<div>
						<input class="btn btn-primary" type="submit" name="submit" value="Add" />
					</div>
				</form>
				<%} %>
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
        </div>
      </div>
  </div>
<%@include file="/templates/admin/inc/footer.jsp" %>
