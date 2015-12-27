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
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Axis Campaign Portal</title>
<link href="<%=basePath%>resources/css/admin/style.css" rel="stylesheet"
	type="text/css">
<link href="<%=basePath%>resources/css/admin/responsive.css"
	rel="stylesheet" type="text/css">
<script src="<%=basePath%>resources/js/admin/jquery-1.11.2.min.js"></script>
<script src="<%=basePath%>resources/js/admin/custom.js"></script>
<!--[if lt IE 9]>
		<script src="js/admin/html5.js" ></script>
  	<![endif]-->
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

<body class="logBg">
	<!--Header-->
	<header class="header">
	<div class="fixedArea">
		<figure id="logo"> <a href="#"><img
			src="<%=basePath%>resources/images/admin/logo.png" width="168"
			height="40" alt=""></a> </figure>
		<h3>Badhti ka naam zindagi...</h3>
	</div>
	</header>
	<!--/Header-->

	<!--Body-->
	<div class="fixedArea">
		<div class="loginArea">
			<h2>Axis Campaign Portal Admin</h2>
			<h3>Login to Access</h3>
			<c:out value="${generalException}" />
			<form action="adminLogin" method="post">
				<!-- <c:if test="${not empty param['invalidLogin']}">
				<h4 style="color: red;">${param['invalidLogin']}</h4>
			</c:if> -->
				<h4 style="color: red;">
					<c:out value="${invalidLogin}" />
				</h4>

				<input name="userName" type="text" placeholder="Use your short name">
				<input name="password" type="password" placeholder="* * * * *">
				<input name="" type="submit"><br>
				<h4 style="color: red;">
					<c:out value="${emptyfieldForUserName}" />
					<c:out value="${emptyfieldForPassword}" />
				</h4>
			</form>
			<!-- <p class="productTour">
				<a href="#">Take product tour</a>
			</p> -->
		</div>
	</div>
	<!--/Body-->

	<!--Footer-->
	<footer class="footer">
	<div class="fixedArea">
		<p class="copyright" align="center">Copyright &copy; 2015 Axis
			Bank, India.</p>
		<!-- <p>
			<span><a href="#">Help</a> | </span>Developed by INDUSNET TECHNOLOGY
		</p>
		<div class="clear"></div> -->
	</div>
	</footer>
	<!--/Footer-->

</body>
</html>
