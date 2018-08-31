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
          Quảng cáo
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
                  	  <th>Số lượng ngừng quảng cáo</th>
	                  <th>Tổng tiền</th>
	                </tr>
	              </thead>
	              <tbody>
	                <%
	                if(request.getAttribute("listAdver") != null){
	                	ArrayList<Adver> list = (ArrayList<Adver>)request.getAttribute("listAdver");
	                	
	    				Date date = new Date();
	                	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	    				String datestr = sdf.format(date);
	    				String[] tg = datestr.split("/");
	    				int thang = Integer.parseInt(tg[1]);
	    				int nam = Integer.parseInt(tg[2]);
	    				
	    				String creat = "";
	    				String die = "";
	                	for(int i = 2017; i <= nam; i++){
	                		
	                		int tongnam = 0;
	                		
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
	                			for(Adver adver:list){
	                				creat = sdf.format(adver.getCreat_at());
	                				String[] creatStr = creat.split("/");
	                				int adthang = Integer.parseInt(creatStr[1]);
	                				int adnam = Integer.parseInt(creatStr[2]);
	                				if(adthang == j && adnam == i){
	                					tongthang += adver.getMoney();
	                					them ++;
	                				}
	                				
	                				die = sdf.format(adver.getDie_at());
	                				String[] dieStr = creat.split("/");
	                				adthang = Integer.parseInt(dieStr[1]);
	                				adnam = Integer.parseInt(dieStr[2]);
	                				if(adthang == j && adnam == i){
	                					tongthang += adver.getMoney();
	                					ngung ++;
	                				}
	                			}%>
	                		<tr>
	                			<td><%= i%>:<%= j%></td>
	                			<td><%= them%></td>
	                			<td><%= ngung%></td>
	                			<td><%= tongthang%>$</td>
	                		</tr>
	                		<%
	                		tongnam += tongthang;
	                		}%>
	                		<tr>
	                			<td colspan="2" ><span style="font-weight: bold; color: red;">Tổng số tiền thu trong năm: <%= i%></span></td>
	                			<td colspan="2"><span style="font-weight: bold; color: red;"><%= tongnam%>$</span></td>
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
