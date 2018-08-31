<%@page import="java.text.SimpleDateFormat"%>
<%@page import="model.bean.Adver"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/templates/admin/inc/dashboard.jsp" %>
<div class="content-wrapper py-3">
  <div class="container-fluid">
    <div class="card mb-3">
        <div class="card-header">
          <i class="fa fa-table"></i>
          Series Table
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
              <form action="<%= request.getContextPath()%>/admin/adver/delete"  method="post">
                  <input style="display: none; margin-left: 10px; margin-bottom: 10px; color: red" id="deleteall" type="submit" value="Delete">
                  <table class="table table-bordered" width="100%" id="dataTable" cellspacing="0">
                  <thead>
                    <tr>
                      <th width="3%">Xóa hết<input style="display: block" type="checkbox" class="checkall"></th>
                      <th>Tên</th>
                      <th>Hình ảnh</th>
                      <th>link</th>
                      <th>Ngày đăng</th>
                      <th>Trạng thái</th>
                      <th>Tiền</th>
                      <th>Giới thiệu</th>
                      <th width="10%">Tác vụ</th>
                    </tr>
                  </thead>
                  <tbody>
                  <%
                    if(request.getAttribute("listAdver") != null){
                    ArrayList<Adver> list  = (ArrayList<Adver>) request.getAttribute("listAdver");
                    for(Adver adver:list){
                  %>
                    <tr>
                      <td>
                        <input type="checkbox" name="item<%= adver.getId()%>" value="<%= adver.getId()%>" class="checkitem" id="chkitem">
                     </td>
                      <td>
                        <a href="<%= request.getContextPath()%>/admin/adver/edit/<%= adver.getId()%>"><%= adver.getName()%></a>
                      </td>
                      <td><img src="<%= request.getContextPath()%>/files/<%= adver.getPicture()%>" width=250px; height=auto;></td>
                      <td><a target="_blank" href="<%= adver.getLink()%>"><i class="fa fa-map-marker" aria-hidden="true"></i>Xem</a></td>
                      <% SimpleDateFormat sd = new SimpleDateFormat("dd/MM/YYYY"); %>
                      <td><%= sd.format(adver.getCreat_at())%></td>
                      <td style="width: 2%; text-align: center">
                        <%
                          if(adver.getStatus() == 0) {
                        %>
                        <div id="active-<%=adver.getId()%>"> 
                        <a href="javascript:void(0)"  style='display: inline-block, width: 10px; height: 10px;' onclick="checkActive(0, <%=adver.getId()%>);"><img src="<%= request.getContextPath()%>/templates/admin/images/deactive.gif"/></a>
                        </div>
                        <%} 
                        if(adver.getStatus() == 1) {%>
                        <div id="active-<%=adver.getId()%>">    
                        	<a href="javascript:void(0)"  style='display: inline-block, width: 10px; height: 10px;' onclick="checkActive(1, <%=adver.getId()%>);"><img src="<%= request.getContextPath()%>/templates/admin/images/active.gif" /></a>
                        </div>
                        <%}%>
                     </td>
                     <td><%= adver.getMoney()%> $</td>
                      <td>
                        <a style="display:block" href="<%= request.getContextPath()%>/admin/adver/edit?id=<%= adver.getId()%>"><i class="fa fa-edit"></i>Sửa</a>
                        <a style="display:block" href="<%= request.getContextPath()%>/admin/adver/delete?id=<%= adver.getId()%>" onclick="return confirm('Bạn có muốn xóa quảng cáo này?')"><i class="fa fa-trash"></i>Xóa</a>
                      </td>
                      <td><%= adver.getPreview()%></td>
                    </tr>
                  <%}}else {%>
                      <div style='color: red'>Không có quảng cáo nào </div>
                 <%  }
                  %>
                  </tbody>
                  <tfoot>
                    <tr>
                      <th width="3%">Xóa hết<input style="display: block" type="checkbox" class="checkall"></th>
                      <th>Tên</th>
                      <th>Hình ảnh</th>
                      <th>link</th>
                      <th>Ngày đăng</th>
                      <th>Trạng thái</th>
                      <th>Tiền</th>
                      <th width="10%">Tác vụ</th>
                    </tr>
                  </tfoot>
                </table>
            </form>
          </div>
        <div class="card-footer small text-muted">
          Updated yesterday at 11:59 PM
        </div>
      </div>
    </div>
  </div>
   <script>
  function checkActive(active, id){
		$.ajax({
			url: '<%=request.getContextPath()%>/admin/adver',
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
