<%@page import="model.bean.Adver"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/templates/admin/inc/dashboard.jsp" %>
<div class="content-wrapper py-3">
  <div class="container-fluid">
        <div class="card mb-3">
          <div class="card-header">
            <i class="fa fa-fw fa-code"></i>
            New Series
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
          if(request.getAttribute("adver") != null){
        	  Adver adver = (Adver) request.getAttribute("adver");
          %>
              <form id="add-post" action="<%= request.getContextPath()%>/admin/adver/add" method="POST"  enctype="multipart/form-data">
              	<div class="form-group">
	              	<label>Tên</label>
	              	<input class="form-control" id="txtname" type="text" name="name" value="<%= adver.getName()%>" placeholder="Tên quảng cáo"/>
              	</div>
                <div class="form-group">
	              	<label>Link</label>
	              	<input class="form-control" id="txtsummary" type="text" name="link" value="<%= adver.getLink()%>" placeholder="Đường link quảng cáo"/>
              	</div>
              	<div class="form-group">
	              	<label>Tiền</label>
	              	<input class="form-control" id="txtsummary" type="text" name="money" value="<%= adver.getMoney()%>" placeholder="Số tiền quảng cáo"/>
              	</div>
              	<div class="form-group">
	              	<label>Giới thiệu</label>
	              	<input class="form-control" id="txtsummary" type="text" name="preview" value="<%= adver.getPreview()%>" placeholder="giới thiệu sản phẩm"/>
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
                <div class="error"></div>
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
                                name: {required: true },
                                money: {required: true },
                                link: {required: true },
                                preview: {required: true}
                            },
                            messages: {
                                name: {required: "Nhập tên quảng cáo" },
                                money: {required: "Nhập số tiền" },
                                link: {required: "Nhập đường link" },
                                link: {required: "Nhập giới thiệu" }
                            }
      					});
      				});
      			</script>
          </div>
        </div>
      </div>
  </div>
<%@include file="/templates/admin/inc/footer.jsp" %>
