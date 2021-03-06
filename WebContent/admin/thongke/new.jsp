<%@page import="model.bean.News"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="model.bean.Adver"%>
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
          Tin Tức
        </div>
        <div class="card-body">
          <div class="table-responsive">
             <form action="<%= request.getContextPath()%>/admin/user/delete"  method="post">
                <input style="display: none; margin-left: 10px; margin-bottom: 10px; color: red" id="deleteall" type="submit" value="Delete">
                <table class="table table-bordered" width="100%" id="dataTable" cellspacing="0">
	              <thead>
	                <tr>
	                  <th>Thời gian</th>
                  	  <th>Số lượng thêm</th>
                  	  <th>Số lượng ngừng Xem</th>
	                </tr>
	              </thead>
	              <tbody>
	                <%
	                if(request.getAttribute("listNew") != null){
	                	ArrayList<News> list = (ArrayList<News>)request.getAttribute("listNew");
	                	
	    				Date date = new Date();
	                	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	    				String datestr = sdf.format(date);
	    				String[] tg = datestr.split("/");
	    				int thang = Integer.parseInt(tg[1]);
	    				int nam = Integer.parseInt(tg[2]);
	    				
	    				String creat = "";
	    				String die = "";
	                	for(int i = 2017; i <= nam; i++){
	                		int themnam = 0;
	                		int ngungnam = 0;
	                		int n = 0;
	                		if( i < nam){
	                			n = 12;
	                		}else{
	                			n = thang;
	                		}
	                		for(int j = 1; j <= n; j++){
	                			int tongthang = 0;
	                			int them = 0;
	                			int ngung = 0;
	                			for(News adver:list){
	                				creat = sdf.format(adver.getCreat_at());
	                				String[] creatStr = creat.split("/");
	                				int adthang = Integer.parseInt(creatStr[1]);
	                				int adnam = Integer.parseInt(creatStr[2]);
	                				if(adthang == j && adnam == i){
	                					them ++;
	                				}
	                				
	                				die = sdf.format(adver.getDie_at());
	                				String[] dieStr = creat.split("/");
	                				adthang = Integer.parseInt(dieStr[1]);
	                				adnam = Integer.parseInt(dieStr[2]);
	                				if(adthang == j && adnam == i){
	                					ngung ++;
	                				}
	                			}%>
	                		<tr>
	                			<td><%= i%>:<%= j%></td>
	                			<td><%= them%></td>
	                			<td><%= ngung%></td>
	                		</tr>
	                		<%
	                		themnam += them;
	                		ngungnam += ngung;
	                		}%>
	                		<tr>
	                			<td colspan="2" ><span style="font-weight: bold; color: red;">Tổng số tin thêm trong năm <%= i%>:  </span><span style="font-weight: bold; color: blue;"><%= themnam%>  tin</span></td>
	                			<td colspan="2" ><span style="font-weight: bold; color: red;">Tổng số tin xóa trong năm <%= i%>:  </span><span style="font-weight: bold; color: blue;"><%= ngungnam%>  tin</span></td>
	                		</tr>
	                	<%}
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
<%@include file="/templates/admin/inc/footer.jsp" %>
