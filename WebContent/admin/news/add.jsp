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
            Thêm tin tức
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
          %>
              <form id="add-post" action="<%= request.getContextPath()%>/admin/news/add" method="post" enctype="multipart/form-data">
              	<div class="form-group">
	              	<label>Name</label>
	              	<input class="form-control" type="text" name="name" value="" />
              	</div>
              	<div class="form-group">
	              	<label>Danh mục</label>
	              	<select class="form-control" name="danhmuc">
              		<%
           			for(int i = 0; i < listCat.size(); i++){
           			%>
           				<option value="<%= listCat.get(i).getId()%>" ><%= listCat.get(i).getName()%></option>
           			<%}%>
	              	</select>
              	</div>
              	<div class="form-group">
	              	<label>Hình ảnh</label>
	              	<input class="form-control" type="file" name="picture" onchange="loadFile(event)" value="" />
              		<img width="250px" height="150px" src="" id = "output">
              	</div>
              	<script>
        				  var loadFile = function(event) {
        				    var output = document.getElementById('output');
        				    output.src = URL.createObjectURL(event.target.files[0]);
        				  };
        		</script>
        		<div class="form-group">
	              	<label>Giới thiệu</label>
	              	<textarea  class="form-control" name="preview" rows="8" cols="" id="motaeditor"  class="ckeditor"></textarea>
              	</div>
              	<div class="form-group">
	              	<label>Chi tiết</label>
	              	<textarea  class="form-control" name="detail" rows="8" cols="" id="chitieteditor"  class="ckeditor"></textarea>
              	</div>
              	<div>
              		<input class="btn btn-primary" type="submit" name="submit" value="Add" />
              	</div>
              </form>
             <script type="text/javascript">
      				$(document).ready(function() {
      					$("#add-post").validate({
      						ignore: [],
      			            debug: false,
      						rules: {
      							name:{ required: true},
      							preview:{ required: true},
      							detail:{ required: true}
      						},
      						messages: {
      							name:{ required: "Vui lòng nhập tên tin tức"},
      							preview:{ required: "Vui lòng nhập giới thiệu"},
      							detail: {required: "Vui lòng nhập nội dung"}
      						}
      					});
      				});
      		</script>
          </div>
        </div>
      </div>
  </div>
<%@include file="/templates/admin/inc/footer.jsp" %>
