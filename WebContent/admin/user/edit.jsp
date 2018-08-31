<%@page import="java.util.ArrayList"%>
<%@page import="model.bean.Roles"%>
<%@page import="model.bean.Users"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/templates/admin/inc/dashboard.jsp" %>
<div class="content-wrapper py-3">
  <div class="container-fluid">
        <div class="card mb-3">
          <div class="card-header">
            <i class="fa fa-fw fa-code"></i>
            Edit User
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
			if(request.getAttribute("edituser") != null && request.getAttribute("listRoles") != null) {
				Users edituser = (Users) request.getAttribute("edituser");
				ArrayList<Roles> listRoles = (ArrayList<Roles>) request.getAttribute("listRoles");
			%>
              <form id="edit-post" action="<%= request.getContextPath()%>/admin/user/edit/<%=edituser.getId() %>" method="post">
              	<div class="form-group">
	              	<label>Username</label>
					<input autocomplete="off" class="form-control" type="text" name="username" value="<%=edituser.getUsername() %>" disabled/>
              	</div>
              	
              	<div class="form-group">
	              	<label>Fullname</label>
					<input autocomplete="off" class="form-control" name="fullname" value="<%=edituser.getFullname() %>" />
				</div>
              	<div class="form-group">
	              	<label>Role</label>
	              	<select class="form-control" name="role_id">
					<% 
						for(Roles roles:listRoles){ 
						String selected="" ;
						if(edituser.getId_roles() == roles.getId()){ selected="selected";}
					%>
						<option <%= selected %> value="<%= roles.getId()%>" ><%= roles.getName()%></option>
					<% } %>
				</select>
				</div>
				<div class="form-group">
					<label>Password</label>
					<input type="password" class="form-control" name="password" value="" placeholder="********"/>
				</div>
              	<div>
              		<input class="btn btn-primary" type="submit" name="submit" value="Update" />
              	</div>
              </form>
              <% } %>
              
			<script type="text/javascript">
      				$(document).ready(function() {
      					$("#edit-post").validate({
      						ignore: [],
      			            debug: false,
      						rules: {
								fullname: { required : true },
								password: {  required : true}
      						},
      						messages: {
								fullname:{
									required: "Vui lòng nhập họ tên"
								},
								password:{
									required: "Vui lòng nhập password"
								}}
      					});
      				});
      			</script>
          </div>
        </div>
      </div>
  </div>
<%@include file="/templates/admin/inc/footer.jsp" %>
