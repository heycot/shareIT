<%@page import="library.StringLibrary"%>
<%@page import="model.bean.Comment"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="model.bean.Users"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="templates/public/inc/header.jsp" %>
  <section id="contentSection">
    <div class="row">
      <div class="col-lg-8 col-md-8 col-sm-8">
        <div class="left_content">
          <div class="single_page">
            <%
            if(request.getAttribute("news") != null){
            	News news = (News)request.getAttribute("news");
            %>
            <h1><%= news.getName()%></h1>
            <div class="post_commentbox"> 
            	<a href="#"><i class="fa fa-user"></i><%= news.getUsername()%></a> 
            	<%SimpleDateFormat sd = new SimpleDateFormat("dd:MM:YYYY"); %>
            	<span><i class="fa fa-calendar"></i><%= sd.format(news.getCreat_at())%></span> 
            	<a href="#"><i class="fa fa-tags"></i><%= news.getCname()%></a>
            	<span id="point"> <i class="fa fa-heart" aria-hidden="true"><%= news.getPoint()%></i></span>
            </div>
            <div class="single_page_content"> 
               <div style="width: 100%; height: auto;"> 
            	<img style="width: 100%; height: auto;" class="img-center" src="<%= request.getContextPath()%>/files/<%= news.getPicture()%>" alt="">
              </div>
              <blockquote><%= news.getPreview()%></blockquote>
              <p><%= news.getDetail()%></p>
            </div>
              <div class="post_commentbox" style="width: 100%; height: 50px;">
              <%
				Users userpublicc = (Users) session.getAttribute("userpublic");
				if(userpublicc != null){
              %>
              	 <a href="javascript:void(0);" onHover="color: red;" onclick="changpoint();"><i class="fa fa-heart-o fa-2x" onclick="love(<%= news.getId()%>);" ></i><span style="margin-left: 15px; font-size: 20px;">Yêu thích</span></a>
              <%}else{ %>
               <a href="javascript:void(0);" onHover="color: red;" onclick="checkLogin()"><i class="fa fa-heart-o fa-2x" onclick="love(<%= news.getId()%>);" ></i><span style="margin-left: 15px; font-size: 20px;">Yêu thích</span></a>
              <%} %>
              </div>
              <div class="xb-comments-block">
				<div class="xb-comment-form">
				<div style="margin-top: 10px;">
				<h4 style="margin-top: 10px; line-height: 50px;">Bình luận</h4>
				</div>
				<div class="media">
					<div class="media-left">
						<a href="javascript:;">
							 <img class="media-object" style="width: 50px; height: 50px; border-radius: 24px;" src="<%= request.getContextPath()%>/templates/public/images/nophoto.png">
						</a>
					</div>
					<div class="media-body" > 
						<% 
						if(userpublicc == null) {%>
						<div class="media-body">
						  <div style="float: left; margin-right: 5px;">
						  	<h4 class="media-heading">
								<span class="username" style="display: inline-block;">Guest</span>
							</h4>
						</div>
						<% } else { %>
						<div class="media-body">
						  <div style="float: left; margin-right: 5px;">
						  <h4 class="media-heading">
								<span class="username" style="display: inline-block;"><%=userpublicc.getUsername()%></span>
							</h4>
						</div>
						<% } %>
						<div style="float: left; max-width: 700px; margin-left: 5px; border-radius: 12px; box-shadow: 0 2px 4px 0 rgba(0,0,0,0.16),0 2px 10px 0 rgba(0,0,0,0.12)!important; background-color: #f1f1f1;">
						<form id="comment-form" style="width: 700px; height: 67px;" >
							<textarea style="padding-left: 5px; width: 645px; display:inline-block; float: left;" class="form-control" id="content" name="content" rows="2" placeholder="Viết bình luận"></textarea>
							<div id="error_validate"></div>
							<div class="submit-block" style="float: right; width: 55px; height: 100%;">
								<div class="message-block text-danger"></div>
								<%if(userpublicc != null){ %>
								<input style="width: 100%; height: 100%; background-color: lightseagreen; " onclick="doComment(<%= news.getId()%>)" type="button" class="btn1" value="Send" />
								<%}else{ %>
								<input style="width: 100%; height: 100%; background-color: lightseagreen; " onclick="checkLogin()" type="button" class="btn1" value="Send" />
								<%} %>
							</div>
						</form>
						</div>
					</div>
				</div>
			</div>
			</div>
            <div class="show-cmt">
				<div class="xb-comments-list">
					<div id="newComment"></div>
					<%
					if( request.getAttribute("listComment") != null){
					ArrayList<Comment> listCMT  = (ArrayList<Comment>) request.getAttribute("listComment");
						for(Comment cmt: listCMT) {
					%>
					<div class="comments-list" style="margin-top: 10px;" id="commentitem-<%= cmt.getId()%>">
					   <div class="media">
						  <div class="media-left">
							 <a href="javascript:;">
							 <img class="media-object" style="width: 50px; height: 50px; border-radius: 24px;" src="<%= request.getContextPath()%>/templates/public/images/nophoto.png" alt="<%=cmt.getUsername() %>">
							 </a>
						  </div>
						  <div class="media-body">
						  <div style="float: left; margin-right: 5px;">
						  <h4 class="media-heading">
								<span class="username" style="display: inline-block;"><%=cmt.getUsername() %></span>
							</h4>
						</div>
						   <div style="float: left; max-width: 700px; margin-left: 5px; border-radius: 12px; box-shadow: 0 2px 4px 0 rgba(0,0,0,0.16),0 2px 10px 0 rgba(0,0,0,0.12)!important; background-color: #f1f1f1;">
							<p style="padding-left: 5px; padding-right: 15px;"><%=cmt.getContent()%></p>
								<%SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY"); %>
							<%if(userpublicc != null){ %>
								<p class="date" style="display: inline-block; padding-left: 5px;"><%=sdf.format(cmt.getCreate_at())%><a style="padding-left: 20px; color: blue;" href="javascript:void(0);" onclick="noShowCmt(<%= cmt.getId()%>)">Ẩn comment</a></p>
							<%}else{ %>
								<p class="date" style="display: inline-block; padding-left: 5px;"><%=sdf.format(cmt.getCreate_at())%><a style="padding-left: 20px; color: blue;" href="javascript:void(0);" onclick="checkLogin()">Ẩn comment</a></p>
							<%} %>
							</div>
						  </div>
					   </div>
					</div>
					<% }} %>
				 </div>
             </div>
             <%} %>
            <div class="social_link">
              <ul class="sociallink_nav">
                <li><a href="#"><i class="fa fa-facebook"></i></a></li>
                <li><a href="#"><i class="fa fa-twitter"></i></a></li>
                <li><a href="#"><i class="fa fa-google-plus"></i></a></li>
                <li><a href="#"><i class="fa fa-linkedin"></i></a></li>
                <li><a href="#"><i class="fa fa-pinterest"></i></a></li>
              </ul>
            </div>
            <div class="related_post">
            <%
            	if(request.getAttribute("listNews") != null){
            %>
              <h2>Tin khác<i class="fa fa-thumbs-o-up" style="margin-left: 15px;"></i></h2>
              <ul class="spost_nav wow fadeInDown animated">
            <%
            		ArrayList<News> list = (ArrayList<News>)request.getAttribute("listNews");
            		if(list.size() > 0){
            			for(int i = 0; i< list.size(); i++){
        					String urlClugdetail = request.getContextPath() + "/chi-tiet/" + StringLibrary.makeSlug(list.get(i).getName()) 
        					+ "-" + list.get(i).getId() +".html";
            %>
            	<li>
                  <div class="media"> <a class="media-left" href="<%= urlClugdetail%>"> 
                  	<img src="<%= request.getContextPath()%>/files/<%= list.get(i).getPicture()%>" alt=""> </a>
                    <div class="media-body"> 
                    	<a class="catg_title" href="<%= urlClugdetail%>"><%= list.get(i).getName()%> </a>
                    </div>
                  </div>
               </li>
            <%			}
            		}
            	}
            %>
              </ul>
            </div>
          </div>
        </div>
      </div>
      </div>
      <div class="col-lg-4 col-md-4 col-sm-4">
        <aside class="right_content">
       		 <%@include file="/templates/public/inc/right_bar.jsp" %>
          <div class="single_sidebar">
            <%@include file="/templates/public/inc/right_bar2.jsp"%>
          </div>
        </aside>
      </div>
    </div>
  </section>
  <script type="text/javascript">
	function love(id){
		$.ajax({
			url: '<%=request.getContextPath()%>/chi-tiet',
			type: 'POST',
			cache: false,
			data: {
				//Dữ liệu gửi đi
				aid: id,
			},
			success: function(data){
				// Xử lý thành công
				$('#point').html(data);
			},
			error: function (){
			// Xử lý nếu có lỗi
				alert('fail');
			}
		});
	}
	function doComment(id){
		var content = $('#content').val();
		var url = window.location.href;
		$.ajax({
			url: '<%=request.getContextPath()%>/comment',
			type: 'POST',
			cache: false,
			data: {
				//Dữ liệu gửi đi
				content : content,
				id_news : id,
				url : url,
			},
			success: function(data){
				// Xử lý thành công
				$('#newComment').html(data);
			},
			error: function (){
			// Xử lý nếu có lỗi
				alert('fail');
			}
		});
	}
	function checkLogin(){
		alert("Vui lòng đăng nhập để sử dụng!");
	}
	function noShowCmt(id){
		$.ajax({
			url: '<%=request.getContextPath()%>/danh-muc',
			type: 'POST',
			cache: false,
			data: {
				//Dữ liệu gửi đi
				id_news : id,
			},
			success: function(data){
				// Xử lý thành công
				$('#commentitem-' + id).html(data);
			},
			error: function (){
			// Xử lý nếu có lỗi
				alert('fail');
			}
		});
	}
</script>
  <%@include file="templates/public/inc/footer.jsp" %>