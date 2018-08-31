<%@page import="model.bean.Roles"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/templates/admin/inc/dashboard.jsp" %>
<div class="content-wrapper py-3">
  <div class="container-fluid">
        <div class="card mb-3">
          <div class="card-header">
            <i class="fa fa-fw fa-code"></i>
            Edit Profile
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
			if(request.getAttribute("listRoles") != null) {
				ArrayList<Roles> listRoles = (ArrayList<Roles>) request.getAttribute("listRoles");
          %>
			<form id="edit-post" action="<%= request.getContextPath()%>/admin?uid=<%=user.getId() %>" method="post">
              <div class="form-group">
	              	<label>Username</label>
					<input autocomplete="off" class="form-control" type="text" name="username" value="<%=user.getUsername() %>" disabled/>
			 </div>
			<div class="form-group">
				<label>Fullname</label>
				<input autocomplete="off" class="form-control" name="fullname" value="<%=user.getFullname() %>" />
			</div>
			<div class="form-group">
				<label>Email</label>
				<input class="form-control" name="email" value="<%=user.getEmail() %>"/ disabled="disabled">
			</div>
			<div class="form-group">
				<label>Role</label>
				<select class="form-control" name="role_id">
				<% 
					for(Roles roles:listRoles){ 
					String selected="" ;
					if(user.getId_roles() == 1) {
					%>
						<option selected  value="1" >adminstrator</option>
					<% } %>
					if(user.getId_roles() == roles.getId()) {selected="selected";}
					%>
						<option <%= selected %> value="<%= roles.getId()%>" ><%= roles.getName()%></option>
					<% } %>
				</select>
			</div>
			<div class="form-group">
             	<label>password</label>
             	<a id="genarate" class="btn btn-primary">Genarate</a>
            </div>
           	<script type="text/javascript">
            	$(document).ready(function(){
            	    $("#genarate").click(function(){
            	        $("#pass").toggleClass("hiden");
            	    });
            	});

           	</script>
           	<div id="pass" class="form-group hiden">
            	<label>Password</label>
            	<input class="form-control" type="password" name="password" value="" />
           	</div>
			<div>
				<input class="btn btn-primary" type="submit" name="submit" value="Update" />
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
								password: { 
									required : true
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
								password:{
									required: "Please enter this field"
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
