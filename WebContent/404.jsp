<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="templates/public/inc/header.jsp" %>

  <section id="contentSection">
    <div class="row">
      <div class="col-lg-8 col-md-8 col-sm-8">
        <div class="left_content">
          <div class="error_page">
            <h3>We Are Sorry</h3>
            <h1>404</h1>
            <p>Unfortunately, the page you were looking for could not be found. It may be temporarily unavailable, moved or no longer exists</p>
            <span></span> <a href="<%= request.getContextPath()%>" class="wow fadeInLeftBig">Go to home page</a> </div>
        </div>
      </div>
    </div>
      <div style="height: 50px; width: 500px;"></div>
  </section>
  <%@include file="templates/public/inc/footer.jsp" %>