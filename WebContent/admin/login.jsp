<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Login</title>
		<link rel="stylesheet"  type="text/css" href="<%= request.getContextPath()%>/templates/admin/css/bootstrap.min.css">
		<link href="style.css" type="text/css" rel="stylesheet" />
<link rel="shortcut icon" href="<%= request.getContextPath()%>/templates/public/images/logo.png" type="image/x-icon" />
	</head>
	<body>
		<div class="container">
			<div class="wrapper">
				<form action="<%= request.getContextPath()%>/admin/login" method="post" name="Login_Form" id="add-post" class="form-signin">
				    <h3 class="form-signin-heading">Please Sign In</h3>
					  <hr class="colorgraph"><br>
					  <% 
					  String error = "";
					  if(request.getAttribute("error") != null){
						  error = (String)request.getAttribute("error"); 
					  %>
					  <h5 style="color: red; text-algin: center;"><%= error%></h5>
					  <%} %>
					  <input type="text" class="form-control" name="username" placeholder="Username"  autofocus="" required />
					  <input type="password" class="form-control" name="password" placeholder="Password" required />
					  <button class="btn btn-lg btn-primary btn-block"  name="submit" value="Login" type="Submit">Login</button>
				</form>
			</div>
		</div>
		<div class="myslides">
		<img alt=""
			src="<%= request.getContextPath()%>/templates/admin/images/slide1.jpg"
			style="width: 100%">
		</div>
		<div class="myslides">
			<img alt=""
				src="<%= request.getContextPath()%>/templates/admin/images/slide2.jpg"
				style="width: 100%">
		</div>
		<div class="myslides">
			<img alt=""
				src="<%= request.getContextPath()%>/templates/admin/images/slide3.jpg"
				style="width: 100%">
		</div>
		<script type="text/javascript">
			var slideIndex = 0;
			showSlides();
			function showSlides() {
				var slides = document.getElementsByClassName("myslides");

				//cho cả 3 ảnh k hiển thị
				for (var i = 0; i < slides.length; i++) {
					slides[i].style.display = "none";
				}
				//trỏ đến pt slide tiếp theo
				slideIndex++;
				//nêu hết rồi thì quay lại 1
				if (slideIndex > slides.length) {
					slideIndex = 1;
				}
				//cho slide tại vị trí đang set hiển thị
				slides[slideIndex - 1].style.display = "block";
				setTimeout(showSlides, 5000);
			}
			$(document).ready(function() {
				$("#add-post").validate({
					// ignore: [],
					// debug: false,
					rules: {
						username:"required",
						password:"required",
					},
					messages: {
						username:"Please enter username",
						password:"Please enter password",
					}
				});
			});
		</script>
	</body>
</html>
