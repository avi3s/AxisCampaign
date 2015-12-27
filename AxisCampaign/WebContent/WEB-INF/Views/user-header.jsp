<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	//System.out.println("BasePath : " + basePath);
%>
<!DOCTYPE >


<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Axis Bank</title>
<!--[if lt IE 9]>
  <script src="js/user/html5.js"></script>
<![endif]-->

<link rel="stylesheet"
	href="<%=basePath%>resources/css/user/responsive-accordion.css"
	type="text/css">
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
	<div id="wraper">
		<header>
			<a id="responsive-menu-button" href="#sidr-main"><img
				src="<%=basePath%>resources/images/user/res-menu-icon.png"
				alt="mmenu" /></a>
			<div class="container">
				<div class="header-left">
					<div class="logo-wrapper">
						<div class="logo">
							<a href="getDashboard"><img
								src="<%=basePath%>resources/images/user/axis-logo.png"
								alt="logo" /></a>
						</div>
						<div class="slogan">
							<img src="<%=basePath%>resources/images/user/axis-slogan.png"
								alt="slogan" />
						</div>
						<div class="clear"></div>
					</div>
					<div class="q2-tablet-logo">
						<img src="<%=basePath%>resources/images/user/q2-logo.png" alt="q2" />
					</div>
					<div class="clear"></div>
				</div>
				<div class="header-right">
					<div class="q2-logo">
						<img
							src="<%=basePath%>resources/images/user/<c:out value="${sessionScope.logoIcon}"/>"
							alt="q2" />
						<%-- 	<img src="<%=basePath%>resources/images/user/q2-logo.png" alt="q2" /> --%>
					</div>
					<div class="notification-block">
						<ul class="notification-menu">
							<li class="notification-menu-circle"><a href="#"
								class="notification"></a>
								<div class="count">
									<c:out value="${userDetails.receivedNotificationsNumber}" />
								</div>
								<div class="notification-menu-drop">
									<ul class="notification-menu-list">
										<div class="top"></div>
										<c:choose>

											<c:when test="${empty topFiveNotifications}">
											</c:when>

											<c:otherwise>
												<c:forEach items="${topFiveNotifications}"
													var="notificationModel">
													<li><p>
															<a
																href="viewSingleNotification?id=<c:out value="${notificationModel.id}" />"><c:out
																	value="${notificationModel.message}" /></a>
														</p>
														<p class="time">
															<fmt:formatDate type="both"
																value="${notificationModel.createTimeStamp}" />
														</p></li>
												</c:forEach>

											</c:otherwise>
										</c:choose>
										<li><c:choose>
												<c:when test="${empty notificationsNotFound}">
													<a href="viewAllNotifications">View All Notifications</a>
												</c:when>

												<c:otherwise>
													<c:out value="${notificationsNotFound}" />
												</c:otherwise>
											</c:choose></li>

									</ul>
								</div></li>
							<li class="notification-menu-circle"><a href="#"
								class="file"></a>
								<div class="count">
									<c:out value="${noOfFiles}" />
								</div>
								<div class="notification-menu-drop">
									<ul class="notification-menu-list">
										<div class="top"></div>

										<c:choose>

											<c:when test="${empty campaignFiles}">
											</c:when>




											<c:otherwise>
												<c:forEach items="${campaignFiles}" var="campaignFile">
													<li><c:if test="${campaignFile.fileType=='CFM'}">
															<a
																href="downloadCampaignFileForUser?campaign_file_id=${campaignFile.campaignFileId}&type=CFM"><c:out
																	value="${campaignFile.fileName}" /></a>
														</c:if> <c:if test="${campaignFile.fileType=='CFUM'}">
															<a
																href="downloadCampaignFileForUser?campaign_file_id=${campaignFile.campaignFileUserId}&type=CFUM"><c:out
																	value="${campaignFile.fileName}" /></a>
														</c:if>
														<c:if test="${campaignFile.fileType=='UFUM'}">
                                                            <a
                                                                href="downloadCampaignFileForUser?campaign_file_id=${campaignFile.userFileUploadId}&type=UFUM"><c:out
                                                                    value="${campaignFile.fileName}" /></a>
                                                        </c:if>
														<p class="time">
															<fmt:formatDate type="both"
																value="${campaignFile.createTimeStamp}" />
														</p></li>
												</c:forEach>

											</c:otherwise>
										</c:choose>
										<li><c:choose>
												<c:when test="${not empty campaignFiles}">
													<a href="viewAllFiles">View All Files</a>
												</c:when>

												<c:otherwise>
													<c:out value="${noFiles}" />
												</c:otherwise>
											</c:choose></li>
									</ul>
								</div></li>
							<li class="myprofile-block"><span class="profile-pic">
									<div class="profile-pic-center">
										<img
											src="<%=basePath%>resources/ProfilePictures/small/<c:out value="${sessionScope.profilePicture}"/>"
											alt="profile-picture" />
									</div>
							</span>
								<div class="pl-name-part">
									<span class="profile-name">Hi, <c:out
											value="${sessionScope.employeeName}" /></span><span
										class="drop-arrow"></span>
								</div>
								<div class="pname-dropdown">
									<ul class="pname-dropdown-list">
										<div class="pl-top"></div>
										<li><span><img
												src="<%=basePath%>resources/images/user/my-pl-icon.png"
												alt="my-profile" /></span> <a href="viewProfile">My Profile</a></li>
										<li><span><img
												src="<%=basePath%>resources/images/user/upload-icon.png"
												alt="Help" /></span><a href="viewUserUploadFile">Upload Files</a></li>
										<li><span><img
												src="<%=basePath%>resources/images/user/help-icon.png"
												alt="Help" /></span><a href="#">Help</a></li>
										<li><span><img
												src="<%=basePath%>resources/images/user/logout-icon.png"
												alt="Logout" /></span> <a href="logout">Logout</a></li>
									</ul>
								</div></li>
						</ul>
						<div class="clear"></div>
					</div>
					<div class="clear"></div>
				</div>
				<div class="clear"></div>
			</div>
		</header>
		<!--------HEADER END------------>

		<div id="navigation">
			<nav class="nav">
				<div class="container">
					<ul class="nav-list">
						<li class="nav-list-item active"><a class="menu-main"
							href="getDashboard"><img
								src="<%=basePath%>resources/images/user/dashboard-icon.png"
								alt="dashboard" />Dashboard</a></li>

						<li class="nav-list-item"><a class="menu-main" href="#"><img
								src="<%=basePath%>resources/images/user/target-icon.png"
								alt="Targets" />Targets</a>
							<div class="sub-catagories">
								<div class="menu-top"></div>
								<ul class="sub-catagories-item">
									<c:choose>
										<c:when test="${empty allCampaigns}">
										</c:when>
										<c:otherwise>
											<c:forEach items="${allCampaigns}" var="campaignModel">
												<li><a
													href="userTarget?campaignid=${campaignModel.campaignId}"><c:out
															value="${campaignModel.campaignName}" /></a></li>
											</c:forEach>
										</c:otherwise>
									</c:choose>
								</ul>
							</div></li>



						<li class="nav-list-item"><a class="menu-main" href="#"><img
								src="<%=basePath%>resources/images/user/leader-icon.png"
								alt="Leader" />Leader Board</a>
							<div class="sub-catagories">
								<div class="menu-top"></div>
								<ul class="sub-catagories-item">
									<c:choose>
										<c:when test="${empty allCampaigns}">
										</c:when>
										<c:otherwise>
											<c:forEach items="${allCampaigns}" var="campaignModel">
												<li><a
													href="leaderBoardAcheivement?campaignid=${campaignModel.campaignId}"><c:out
															value="${campaignModel.campaignName}" /></a></li>
											</c:forEach>
										</c:otherwise>
									</c:choose>

								</ul>
							</div></li>


						<li class="nav-list-item"><a class="menu-main"
							href="viewUserEscalationMatrix"><img
								src="<%=basePath%>resources/images/user/escation-icon.png"
								alt="Escalation" />Escalation Matrix</a></li>


						<li class="nav-list-item"><a class="menu-main" href="#"><img
								src="<%=basePath%>resources/images/user/faq-icon.png" alt="Faq" />Faq's</a>
							<div class="sub-catagories">
								<div class="menu-top"></div>
								<ul class="sub-catagories-item">

									<c:choose>
										<c:when test="${empty allCampaigns}">
										</c:when>
										<c:otherwise>
											<c:forEach items="${allCampaigns}" var="campaignModel">
												<li><a
													href="viewFaqForUser?campaignid=${campaignModel.campaignId}"><c:out
															value="${campaignModel.campaignName}" /></a></li>
											</c:forEach>
										</c:otherwise>
									</c:choose>
								</ul>
							</div></li>


						<li class="nav-list-item"><a class="menu-main" href="#"><img
								src="<%=basePath%>resources/images/user/seat-icon.png"
								alt="Seat" />Seat Breakup</a>
							<div class="sub-catagories">
								<div class="menu-top"></div>
								<ul class="sub-catagories-item">
									<c:choose>
										<c:when test="${empty allCampaigns}">
										</c:when>
										<c:otherwise>
											<c:forEach items="${allCampaigns}" var="campaignModel">
												<li><a
													href="seat-breakup.html?campaignid=${campaignModel.campaignId}"><c:out
															value="${campaignModel.campaignName}" /></a></li>
											</c:forEach>
										</c:otherwise>
									</c:choose>
								</ul>
							</div></li>


						<li class="nav-list-item"><a class="menu-main" href="rewards"><img
								src="<%=basePath%>resources/images/user/rewards-icon.png"
								alt="Rewards" />Rewards</a>
							<div class="sub-catagories">
								<div class="menu-top"></div>
								<!-- <ul class="sub-catagories-item">
									<li><a href="rewards.html">Campaings 1</a></li>
									<li><a href="#">Campaings 2</a></li>
									<li><a href="#">Campaings 3</a></li>
									<li><a href="#">Campaings 4</a></li>
								</ul> -->
							</div></li>









						<li class="nav-list-item"><a class="menu-main" href=""
							target="_blank"><img
								src="<%=basePath%>resources/images/user/abr-icon.png"
								alt="MBR MIIS" />ABR MIS</a></li>


						<li class="nav-list-item"><a class="menu-main" href="#"><img
								src="<%=basePath%>resources/images/user/av-icon.png" alt="AV" />AV</a></li>
					</ul>
					<div class="clear"></div>
				</div>
			</nav>
		</div>
		<!--------NAV END------------>