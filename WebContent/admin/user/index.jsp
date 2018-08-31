<%@page import="model.bean.Users"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/templates/admin/inc/dashboard.jsp" %>
<div class="content-wrapper py-3">
  <div class="container-fluid">
    <div class="card mb-3">
        <div class="card-header">
          <i class="fa fa-table"></i>
          Users
        </div>
        <%
         String msg = "";
         if(request.getParameter("msg") != null ){ 
        	 
        	 int msgInt = Integer.parseInt((String) request.getParameter("msg"));
        	 switch(msgInt){
        	 case 0: msg = "Không xóa được"; break;
        	 case 1: msg = "Thêm thành công"; break;
        	 case 2: msg = "Sửa thành công"; break;
        	 case 3: msg = "Xóa thành công"; break;
        	 default: msg = "Lỗi. Vui lòng thử lại sau";
        	 }
        %>
            <h5 style="text-align: center; margin-top: 20px; color: red;"><%=  msg%></h5>
        <% } %>
        <script type="text/javascript">
            $(document).ready(function(){
                $(document).on('change', '.checkall, .checkitem', function(){
                    var $_this = $(this);
                    document.getElementById("deleteall").style.display = "block";
                    if($_this.hasClass('checkall')){
                        $('.checkitem').prop('checked', $_this.prop('checked'));
                    }else{
                        var $_checkedall = true;
                        $('.checkitem').each(function(){
                            if(!$(this).is(':checked')){
                                $_checkedall = false;
                            }
                            $('.checkall').prop('checked', $_checkedall);
                        });
                    }
                    var $_uncheckedall = true;
                    $('.checkitem').each(function(){
                        if($(this).is(':checked')){
                            $_uncheckedall = false;
                        }
                        if($_uncheckedall){
                            document.getElementById("deleteall").style.display = "none";
                        }else{
                            document.getElementById("deleteall").style.display = "block";
                        }
                    });
                });
            });
        </script>
        <div class="card-body">
          <div class="table-responsive">
             <form action="<%= request.getContextPath()%>/admin/user/delete"  method="post">
                <input style="display: none; margin-left: 10px; margin-bottom: 10px; color: red" id="deleteall" type="submit" value="Delete">
                <table class="table table-bordered" width="100%" id="dataTable" cellspacing="0">
	              <thead>
	                <tr>
	                  <th width="3%">Xóa hết<input style="display: inline-block; margin-left: 5px;" type="checkbox" class="checkall"></th>
                  	  <th>Username</th>
	                  <th>Fullname</th>
	                  <th>Email</th>
	                  <th>Role</th>
	                  <th style="width: 1%;">Task</th>
	                </tr>
	              </thead>
	              <tbody>
	                <%
	                if(request.getAttribute("listUser") != null){
	                	ArrayList<Users> list = (ArrayList<Users>)request.getAttribute("listUser");
	                	for(Users inuser:list){
	                	%>
	                <tr>
	                <%
                       if(inuser.getId_roles() != 1){
                     %>
	                  <td>
                        <input type="checkbox" name="item<%= inuser.getId()%>" value="<%= inuser.getId()%>" class="checkitem" id="chkitem">
                      </td>
                      <%}else{ %>
                      <td>Không được xóa</td>
                      <%} %>
	                  <td><%= inuser.getUsername()%></td>
	                  <td><%= inuser.getFullname()%></td>
	                  <td><%= inuser.getEmail()%></td>
	                  <td><%= inuser.getNameroles()%></td>
	                  <td>
	                      <a title="Edit" href="<%= request.getContextPath()%>/admin/user/edit?uid=<%= inuser.getId()%>"><i class="fa fa-edit"></i></a>
	                      <%
	                        if(inuser.getId_roles() != 1){
	                      %>
	                      <a title="Delete" onclick="return confirm('Bạn có muốn xóa user này?')" href="<%= request.getContextPath()%>/admin/user/delete?uid=<%= inuser.getId()%>"><i class="fa fa-trash"></i></a>
	                      <%}%>
	                  </td>
	                </tr>
	                <%}}%>
	              </tbody>
	            </table>
            </form>
          </div>
        </div>
        <div class="card-footer small text-muted">
          Updated yesterday at 11:59 PM
        </div>
      </div>
    </div>
  </div>
<%@include file="/templates/admin/inc/footer.jsp" %>
