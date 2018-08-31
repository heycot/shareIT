<%@page import="model.bean.Category"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/templates/admin/inc/dashboard.jsp" %>
<div class="content-wrapper py-3">
  <div class="container-fluid">
        <div class="card mb-3">
          <div class="card-header">
            <i class="fa fa-fw fa-code"></i>
            New Category
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
          ArrayList<Category> listCat  = null;
          if(request.getAttribute("listCat") != null){
        	  listCat = (ArrayList<Category>) request.getAttribute("listCat");
          }
          String name = "";
          int idcat = 0;
          if(request.getAttribute("cat") != null){
        	  Category cat = (Category)request.getAttribute("cat");
        	  name = cat.getName();
        	  idcat = cat.getId_parent();
          }
          %>
              <form id="add-post" action="<%= request.getContextPath()%>/admin/cat/add" method="POST" >
              	<div class="form-group">
	              	<label>Name</label>
	              	<input class="form-control" id="txtname" type="text" name="name" value="<%= name%>" placeholder="Name of category"/>
              	</div>
              	<div class="form-group">
              		<label>Danh mục cha</label><br>
          			<select name="nameFa">
          				<option value="0"></option>
          			<%
          			if(listCat.size()>0){
          				for(Category catt: listCat){
          					if(catt.getId() == idcat){
          			%>
          			<option value="<%= catt.getId()%>" selected><%= catt.getName()%></option>
          			<%}else{
          			%><option value="<%= catt.getId()%>"><%= catt.getName()%></option>
          			<%	}
          			}}%>
          			</select>
      			</div>  
                <div class="error"></div>
              	<div>
              		<input class="btn btn-primary" type="submit" name="submit" value="Add" />
              	</div>
              </form>

            <script type="text/javascript">
      				$(document).ready(function() {
      					$("#add-post").validate({
      						rules: {
      							name:"required",
                                maxlength: 50
      						},
      						messages: {
      							name:"This field not null",
                                maxlength: "maxlength is 50 character"
      						}
      					});
      				});
                    
      			</script>
          </div>
        </div>
      </div>
  </div>
<%@include file="/templates/admin/inc/footer.jsp" %> 
