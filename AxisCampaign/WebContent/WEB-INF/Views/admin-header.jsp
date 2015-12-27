<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	//System.out.println("BasePath : " + basePath);
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
<script src="<%=basePath%>resources/js/admin/jquery-1.11.2.min.js"></script>

<script src="<%=basePath%>resources/js/admin/custom.js"></script>

<script src="<%=basePath%>resources/js/admin/jquery.dataTables.min.js"></script>
<link href="<%=basePath%>resources/css/admin/jquery.dataTables.min.css"
	rel="stylesheet" type="text/css">
<script
	src="<%=basePath%>resources/js/admin/dataTables.responsive.min.js"></script>
<link href="<%=basePath%>resources/css/admin/dataTables.responsive.css"
	rel="stylesheet" type="text/css">

<!-- <script src='https://cdn.datatables.net/1.10.10/js/jquery.dataTables.min.js'></script>
<link href='https://cdn.datatables.net/1.10.10/css/jquery.dataTables.min.css' rel='stylesheet' type='text/css' />
<script src='https://cdn.datatables.net/responsive/1.0.0/js/dataTables.responsive.min.js'></script> 
<link href='https://cdn.datatables.net/responsive/1.0.0/css/dataTables.responsive.css' rel='stylesheet' type='text/css' /> -->

<!--[if lt IE 9]>
		<script src="js/admin/html5.js" ></script>
  	<![endif]-->

<script type="text/javascript">
	$(document)
			.ready(
					function() {
						//alert("ok");
						$('#example')
								.DataTable(
										{
											responsive : true,
											"pagingType" : "full_numbers",
											language : {
												searchPlaceholder : "Search",
												"paginate" : {
													"previous" : "< Previous",
			            "next" : "Next >",
													"first" : "< First",
			            "last" : "Last >"
												}
											}
										});

						$('#example1')
								.DataTable(
										{
											responsive : true,
											"pagingType" : "full_numbers",
											language : {
												searchPlaceholder : "Search",
												"paginate" : {
													"previous" : "< Previous",
	            "next" : "Next >",
													"first" : "< First",
	            "last" : "Last >"
												}
											}
										});

						$(".dataTables_filter label").contents().first()
								.remove();
					});
</script>
</head>

<body>
	<div class="wholeWrap">
		<!--Header-->
		<header class="header">
		<div class="fixedArea">
			<figure id="logo"> <a href="refreshAdminHome"><img
				src="<%=basePath%>resources/images/admin/logo.png" width="168"
				height="40" alt=""></a> </figure>
			<h3>Badhti ka naam zindagi...</h3>
		</div>
		<a href="logout" class="logOut">Logout</a> </header>
		<!--/Header-->

		<!--Left Area-->
		<div class="leftArea">
			<!-- <div class="profileArea">
				<figure> <a href="editAdminProfile"><img
					src="<%=basePath%>resources/images/admin/avatar.png" width="58"
					height="58" alt=""></a> </figure>
				<h2>
					<c:out value="${sessionScope.employeeName}" />
				</h2>
			</div> -->
			<nav class="nav" id="nav">
			<ul>

				<!-- <li id="menuIco5"><a href="viewCampaign">Campaign
						Management</a> -->
				<li id="menuIco5"><a href="javascript:void(0);">Campaign
						Management</a>
					<ul>

						<li id="menuIco15"><a href="viewCampaign">View Campaign </a></li>

						<li id="menuIco11"><a href="viewTargetField">Target Field
								Management</a></li>

						<li id="menuIco12"><a href="viewTargetFieldValue">Target
								Field Value Management</a></li>

						<li id="menuIco13"><a href="viewacheivementfield">Achievement
								Field Management</a></li>

						<li id="menuIco14"><a
							href="viewacheivementfieldvalueManagement">Achievement Field
								Value Management</a></li>

						<li id="menuIco6"><a href="viewPromotionalFileUpload">User
								Specific File Upload Management</a></li>

					</ul></li>

				<!-- <li id="menuIco4"><a href="viewUser">User Management</a> -->

				<li id="menuIco4"><a href="javascript:void(0);">User
						Management</a>
					<ul>

						<li id="menuIco16"><a href="viewUser">View User</a></li>
						<li id="menuIco1"><a href="viewRoleLevel">Role Level
								Management</a></li>

						<li id="menuIco2"><a href="viewSubRoleLevel">Sub Role
								Level Management</a></li>

						<li id="menuIco3"><a href="viewRole">Role Management</a></li>
					</ul></li>



				<li id="menuIco8"><a href="viewNotification">Notification
						Management</a></li>


				<li id="menuIco9"><a href="viewEscalationMatrix">Escalation
						Matrix Management</a></li>

				<li id="menuIco10"><a href="viewFaq">FAQ Management</a></li>


				<li id="menuIco7"><a href="viewContent">Content Management
				</a></li>

				<li id="menuIco17"><a href="editAdminProfile">Update
						Profile</a></li>

				<li id="menuIco18"><a href="viewUserHeaderPicture">Upload
						User Header Picture </a></li>


			</ul>
			</nav>
			<a href="#" class="menuSh" title="Menu"></a>
		</div>
		<!--/Left Area-->

		<!--Body Area-->
		<div class="bodyWrap">
			<div class="topBar">
				<div class="fixedArea">
					<!--<a href="#" class="menuSh" title="Menu"></a>
					 <div class="searchArea">
						<input name="search" type="text" placeholder="Search"> <input
							name="" type="submit">
					</div> 
					<a href="logout" class="logOut">Logout</a>  -->
				</div>
			</div>

			<div class="bodyArea">
				<div class="fixedArea">
					<div class="contentPart">

						<!-- <div class="headingArea">
							<h3>listing Screen</h3>
							<p>listing Screen</p>
						</div> -->