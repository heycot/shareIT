<%@page import="java.util.ArrayList"%>
<%@page import="model.bean.Category"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/templates/admin/inc/dashboard.jsp" %>  
<div class="content-wrapper py-3">
  <div class="container-fluid">
        <div class="card mb-3">
          <div class="card-header">
            <i class="fa fa-fw fa-code"></i>
            Chỉnh sửa danh mục
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
              int id = -1;
              String name = "";
              int idfa = 0;
              Category cat = null;
              if(request.getAttribute("cat") != null){
            	  cat = (Category) request.getAttribute("cat");
                  id = cat.getId();
                  name = cat.getName();
                  idfa = cat.getId_parent();
              }
              ArrayList<Category> listCat  = null;
              if(request.getAttribute("listCat") != null){
            	  listCat = (ArrayList<Category>) request.getAttribute("listCat");
              }
             %>
              <form id="add-post" action="<%= request.getContextPath()%>/admin/cat/edit?cid=<%= id%>" method="post">
              	<div class="form-group">
	              	<label>Tên</label>
	              	<input class="form-control" type="text" name="name" value="<%= name%>" />
              	</div>
              	<div class="form-group">
              		<label>Danh mục cha</label><br>
          			<select name="nameFa">
          				<option value="0"></option>
          			<%
          			if(listCat.size()>0){
          				for(Category catt: listCat){
          					if(idfa == catt.getId()){
		          			%>
		          			<option value="<%= catt.getId()%>" selected><%= catt.getName()%></option>
		          			<%
          					}else{
		          			%>		
		          			<option value="<%= catt.getId()%>"><%= catt.getName()%></option>
		          			<%}}}%>
          			</select>
      			</div>  
              	<div>
              		<input class="btn btn-primary" type="submit" name="submit" value="Update" />
              	</div>
              </form>

            <script type="text/javascript">
      				$(document).ready(function() {
      					$("#add-post").validate({
      						ignore: [],
      			            debug: false,
      						rules: {
      							name:"required"
      						},
      						messages: {
      							name:"Name is not null"
      						}
      					});
      				});
      			</script>
          </div>
        </div>
      </div>
  </div>
<%@include file="/templates/admin/inc/footer.jsp" %> 
