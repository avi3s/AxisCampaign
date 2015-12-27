<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	//System.out.println("BasePath : " + basePath);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link href="<%=basePath%>resources/css/user/style.css" rel="stylesheet"
	type="text/css">
<link href="<%=basePath%>resources/css/user/slider.css" rel="stylesheet"
	type="text/css">
<link href="<%=basePath%>resources/css/user/responsive-tabs.css"
	rel="stylesheet" type="text/css" />
<link href="<%=basePath%>resources/css/user/owl.carousel.css"
	rel="stylesheet">
<link href="<%=basePath%>resources/css/user/owl.theme.css"
	rel="stylesheet">
<link href="<%=basePath%>resources/css/user/responsive.css"
	rel="stylesheet" type="text/css">
<link href="<%=basePath%>resources/css/user/mmenu.css" rel="stylesheet"
	type="text/css">
<link href="<%=basePath%>resources/css/user/jquery.dataTables.min.css"
	rel="stylesheet" type="text/css" />
<link
	href="<%=basePath%>resources/css/user/responsive.dataTables.min.css"
	rel="stylesheet" type="text/css">

<script src="<%=basePath%>resources/js/user/jquery-1.9.1.min.js"></script>
<script src="<%=basePath%>resources/js/user/jquery-migrate-1.2.1.js"></script>
<script src="<%=basePath%>resources/js/user/responsiveslides.min.js"></script>
<script src="<%=basePath%>resources/js/user/owl.carousel.min.js"></script>
<script src="<%=basePath%>resources/js/user/mmenu.js"
	type="text/javascript"></script>
<script src="<%=basePath%>resources/js/user/responsive-accordion.min.js"></script>
<script src="<%=basePath%>resources/js/user/jquery.responsiveTabs.js"></script>
<script src="<%=basePath%>resources/js/user/jquery.dataTables.min.js"></script>
<script
	src="<%=basePath%>resources/js/user/dataTables.responsive.min.js"></script>
<script src="<%=basePath%>resources/js/user/custom.js"></script>

<script>
 $(document).ready(function() {
	 function disableBack() {
		   window.history.forward()
		  }

		  window.onload = disableBack();
		  window.onpageshow = function(evt) {
		   if (evt.persisted)
		    disableBack()
		  } 

 });
</script>
</head>

<body class="loginBg">
	<header>
	<div class="container">
		<div class="header-left">
			<div class="logo-wrapper">
				<div class="logo">
					<a href="index.html"><img
						src="<%=basePath%>resources/images/user/axis-logo.png" alt="logo" /></a>
				</div>
				<div class="slogan">
					<img src="<%=basePath%>resources/images/user/axis-slogan.png"
						alt="slogan" />
				</div>
				<div class="clear"></div>
			</div>
			<div class="clear"></div>
		</div>

	</div>

	</header>


	<div class="logBodyWrap">
		<div class="loginWrap">
			<!-- <h2>
				<span>User Login</span>
			</h2> -->
			<h4 style="color: red;">
				<c:out value="${invalidLogin}" />
			</h4>
			<form action="userLogin" method="post">
				<input name="userName" type="text" placeholder="User Name">
				<h4 style="color: red; padding-bottom: 18px;">
					<c:out value="${emptyfieldForUserName}" />

				</h4>
				<div class="clear"></div>
				<input name="password" type="password" placeholder="* * * * * *">
				<h4 style="color: red; padding-bottom: 18px;"">
					<c:out value="${emptyfieldForPassword}" />
				</h4>
				<div class="clear"></div>
				<input name="login" type="submit" value="Login">

				<!-- <a href="#">Forgot
					Password? Click Here</a> -->
			</form>
			<div class="clear"></div>
		</div>
	</div>
	<footer> Copyright 2015 Axis Bank, India </footer>
</body>
</html>
