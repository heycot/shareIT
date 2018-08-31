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
            Edit
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
              <form id="add-post" action="<%= request.getContextPath()%>/admin/adver/edit?id=<%= adver.getId()%>" method="post" enctype="multipart/form-data">
              	<div class="form-group">
	              	<label>Tên</label>
	              	<input class="form-control" type="text" name="name" value="<%= adver.getName()%>" />
              	</div>
                <div class="form-group">
	              	<label>Dường link</label>
	              	<input class="form-control" type="text" name="link" value="<%= adver.getLink()%>" />
              	</div>
              	<div class="form-group">
	              	<label>Tiền</label>
	              	<input class="form-control" type="text" name="money" value="<%= adver.getMoney()%>" />
              	</div>
              	<div class="form-group">
	              	<label>Giới thiệu</label>
	              	<input class="form-control" id="txtsummary" type="text" name="preview" value="<%= adver.getPreview()%>"/>
              	</div>
              	<div class="form-group" style="margin-bottom: 10px;">
              		<div style="height: auto; width: 250px; float: left;">
              			<label>Hình ảnh</label>
	              		<img src="<%= request.getContextPath()%>/files/<%= adver.getPicture()%>" alt="<%= adver.getName()%>" title="<%= adver.getName()%>" width=250px; height=auto;>
              		</div>
              		<div style="height: 100px; width: 250px; float: left; margin-left: 30px;">
              			<label>Hình ảnh mới</label>
              			<input class="form-control" type="file" name="hinhanh" onchange="loadFile(event)" value="" />
              		<img width="250px" height="auto" src="" id = "output">
              		</div>
              		<script>
        				  var loadFile = function(event) {
        				    var output = document.getElementById('output');
        				    output.src = URL.createObjectURL(event.target.files[0]);
        				  };
        			</script>
              		</div>
              		<div style="clear: both;"></div>
              	</div>
              	<div>
              		<input class="btn btn-primary" type="submit" name="submit" value="Update" />
              	</div>
              </form>
              <%  } %>

            <script type="text/javascript">
                $(document).ready(function() {
                    $("#add-post").validate({
  						ignore: [],
  			            debug: false,
                        rules: {
                            name: {required: true },
                            money: {required: true },
                            link: {required: true }
                        },
                        messages: {
                            name: {required: "Nhập tên quảng cáo" },
                            money: {required: "Nhập số tiền" },
                            link: {required: "Nhập đường link" }
                        }
                    });
                });
      			</script>
          </div>
        </div>
      </div>
  </div>
<%@include file="/templates/admin/inc/footer.jsp" %>
