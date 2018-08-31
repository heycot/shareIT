<%@page import="model.dao.NewsDao"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="model.bean.News"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/templates/admin/inc/dashboard.jsp" %>
<div class="content-wrapper py-3">
  <div class="container-fluid">
    <div class="card mb-3">
        <div class="card-header">
          <i class="fa fa-table"></i>
          News table
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
             <form action="<%= request.getContextPath()%>/admin/news/delete"  method="post">
                <input style="display: none; margin-left: 10px; margin-bottom: 10px; color: red" id="deleteall" type="submit" value="Delete">
                <table class="table table-bordered" width="100%" id="dataTable" cellspacing="0">
              	<thead>
                <tr>
                  <th width="3%">Xóa hết<input style="display: inline-block; margin-left: 5px;" type="checkbox" class="checkall"></th>
                  <th>Tên tin tức</th>
                  <th>Danh mục tin</th>
                  <th>Người tạo</th>
                  <th>Ngày tạo</th>
                  <th>Hình ảnh</th>
                  <th>Số lượt yêu thích</th>
                  <th>Trang thái</th>
                  <th>Tùy chọn</th>
                </tr>
              </thead>
              <tfoot>
                <tr>
                  <th width="3%">Xóa hết<input style="display: inline-block; margin-left: 5px;" type="checkbox" class="checkall"></th>
                  <th>Tên tin tức</th>
                  <th>Danh mục tin</th>
                  <th>Người tạo</th>
                  <th>Ngày tạo</th>
                  <th>Hình ảnh</th>
                  <th>Số lượt yêu thích</th>
                  <th>Trang thái</th>
                  <th>Tùy chọn</th>
                </tr>
              </tfoot>
              <tbody>
                  <%
                  if( request.getAttribute("listNews") != null){
                	  ArrayList<News> listNews = (ArrayList<News>) request.getAttribute("listNews");
                	  for(News news: listNews){
                	%>
               <tr>
                	<td>
                        <input type="checkbox" name="item<%= news.getId()%>" value="<%= news.getId()%>" class="checkitem" id="chkitem">
                    </td>
                    <td><a href="<%= request.getContextPath()%>/admin/news/edit?nid=<%= news.getId()%>"><%= news.getName()%></a> </td>
                	<td><%= news.getCname()%></td>
                	<td><%= news.getUsername()%></td>
                	<%
                	SimpleDateFormat sd = new SimpleDateFormat("dd/MM/YYYY");
                	%>
                	<td><%= sd.format(news.getCreat_at())%></td>
                	<td><img src="<%= request.getContextPath()%>/files/<%= news.getPicture()%>" alt="<%= news.getName()%>" title="<%= news.getName()%>" width=200px;></td>
                	<td><%= news.getPoint()%></td>
                	<td style="width: 2%; text-align: center">
                        <%
                          if(news.getStatus() == 0) {
                        %>
                        <img src="<%= request.getContextPath()%>/templates/admin/images/deactive.gif" id="active-<%=news.getId()%>" onclick="checkActive(0, <%=news.getId()%>);" />
                        <%} 
                        if(news.getStatus() == 1) {%>
                            <img src="<%= request.getContextPath()%>/templates/admin/images/active.gif" id="active-<%=news.getId()%>" onclick="checkActive(1, <%=news.getId()%>);" />
                        <%}%>
                     </td>
                	<td>
                        <a style="display: inline-block" href="<%= request.getContextPath()%>/admin/news/edit?nid=<%= news.getId()%>"><i class="fa fa-edit"></i>Sửa</a>
                        <a style="display: inline-block" href="<%= request.getContextPath()%>/admin/news/delete?nid=<%= news.getId()%>" onclick="return confirm('Bạn có muốn xóa tin này')"><i class="fa fa-trash"></i>Xóa</a>
                     </td>
                </tr>
                	<%
                	  }
                  }
                  %>
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
  <script type="text/javascript">
	function checkActive(active, id){
		$.ajax({
			url: '<%=request.getContextPath()%>/admin/comment',
			type: 'POST',
			cache: false,
			data: {
				//Dữ liệu gửi đi
				aid : id,
				aactive : active,
			},
			success: function(data){
				// Xử lý thành công
				$('#active-' + id).html(data);
			},
			error: function (){
			// Xử lý nếu có lỗi
				alert('fail');
			}
		});
	}
</script>
<%@include file="/templates/admin/inc/footer.jsp" %>
