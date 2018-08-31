<%@page import="model.dao.CategoryDao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.bean.Category"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/templates/admin/inc/dashboard.jsp" %>  
<div class="content-wrapper py-3">
  <div class="container-fluid">
    <div class="card mb-3">
        <div class="card-header">
          <i class="fa fa-table"></i>
          Bảng danh mục
        </div>
        <div>
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
       </div>
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
            <form action="<%= request.getContextPath()%>/admin/cat/delete"  method="post">
                <input style="display: none; margin-left: 10px; margin-bottom: 10px; color: red" id="deleteall" type="submit" value="Delete">
                <table class="table table-bordered" width="100%" id="dataTable" cellspacing="0">
                  <thead>
                    <tr>
                      <th width="20%">Delete All<input style="display: inline-block; margin-left: 15px;" type="checkbox" class="checkall"></th>
                      <th>Name</th>
                      <th width="20%">Service</th>
                    </tr>
                  </thead>
                  <tbody>
                  <%
                    if(request.getAttribute("listCat") != null){
                    	ArrayList<Category> listCat = (ArrayList<Category>)request.getAttribute("listCat");
                    	if(listCat.size() > 0){
                    	for(int i = 0; i < listCat.size(); i++){
                       if(listCat.get(i).getId_parent() == 0){
                      %>	
                     <tr>
                      	<td>
                        <input type="checkbox" name="item<%= listCat.get(i).getId()%>" value="<%= listCat.get(i).getId()%>" class="checkitem" id="chkitem">
                     	</td>
                     	<td>
                      	<a href="<%= request.getContextPath()%>/admin/cat/edit?cid=<%= listCat.get(i).getId()%>"><%= listCat.get(i).getName()%></a>
         		 	  <%
                        	int id = listCat.get(i).getId();
                        	CategoryDao cd = new CategoryDao();
                        	ArrayList<Category> list  = cd.getCatsChildren(id);
                        	if( list.size() > 0){
                        		for(int j = 0; j < list.size(); j++){
                        		%>
                        		<br>
                       		 	 <a style="margin-left: 50px; width: 200px; display: inline-block;"  href="<%= request.getContextPath()%>/danh-muc?cid=<%= list.get(j).getId()%>">---> <%= list.get(j).getName()%></a>
                       			<a style="margin-left: 50px;display: inline-block;" href="<%= request.getContextPath()%>/admin/cat/edit?cid=<%= list.get(j).getId()%>"><i class="fa fa-edit"></i></a>
                        		<a style="margin-left: 50px;display: inline-block;" href="<%= request.getContextPath()%>/admin/cat/delete?cid=<%= list.get(j).getId()%>" onclick="return confirm('Bạn có muốn xóa danh mục này?')"><i class="fa fa-trash"></i></a>
                       			<%  	
                        		}
                        	}
         			%>
                    </td>
                    <td>
                        <a style="display:block" href="<%= request.getContextPath()%>/admin/cat/edit?cid=<%= listCat.get(i).getId()%>"><i class="fa fa-edit"></i>Sửa</a>
                        <a style="display:block" href="<%= request.getContextPath()%>/admin/cat/delete?cid=<%= listCat.get(i).getId()%>" onclick="return confirm('Bạn có muốn xóa danh mục này?')"><i class="fa fa-trash"></i>Xóa</a>
                     </td>
                    </tr>
                    
                  <% }}}}%>
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
