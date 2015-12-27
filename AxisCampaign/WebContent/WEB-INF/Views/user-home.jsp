<%@ include file="user-header.jsp"%>
<script>
	function f() {
		window.open("<c:out value="mailto:${userDetails.emailAddress}"/>");
		return false;
	}
</script>

<div class="sliders">
	<ul class="rslides" id="slider2">
		<li><a href="#"><img
				src="<%=basePath%>resources/images/user/banner-1.jpg" alt=""></a></li>
		<li><a href="#"><img
				src="<%=basePath%>resources/images/user/banner-2.jpg" alt=""></a></li>
		<li><a href="#"><img
				src="<%=basePath%>resources/images/user/banner-3.jpg" alt=""></a></li>

		<c:choose>

			<c:when test="${empty userHeaderImagesList}">
			</c:when>

			<c:otherwise>
				<c:forEach items="${userHeaderImagesList}"
					var="userHeaderImageModel">
					<li><a href="#"><img
							src="<%=basePath%>/resources/UserHeaderImage/<c:out value="${userHeaderImageModel.imageName}" />"
							alt=""></a></li>
				</c:forEach>
			</c:otherwise>


		</c:choose>

	</ul>
</div>
<!--------BANNER END------------>



<div class="profile-detail-section">
	<div class="container">
		<div class="pp-sec">
			<div class="profile-pic-box">
				<div class="profile-pic-center">
					<img
						src="<%=basePath%>resources/ProfilePictures/small/<c:out
					value="${sessionScope.profilePicture}" />"
						alt="profile-pic" />
				</div>
			</div>
		</div>
		<div class="pd-sec">
			<span class="pl-name"><c:out
					value="${userDetails.employeeName}" /></span> <span class="call"><icon
					()
					class="ph-ico"></icon><span>Call :</span> <c:out
					value="${userDetails.primaryTelephoneNumber}" /> </span> <span
				class="email"><icon class="mail-ico"></icon><span>Email
					:</span> <a href="#" onclick="f()"><c:out
						value="${userDetails.emailAddress}" /></a></span>
			<p class="branch-n">
				<span>Branch :</span>
				<c:out value="${userDetails.subRoleLevelname}" />
			</p>
			<p class="pl-info">
				<c:out value="${userDetails.about}" />
				...
			</p>
		</div>
		<div class="clear"></div>
	</div>
</div>
<!--------PROFILE END------------>


<section class="db-content">
	<div class="breadcramb">
		<div class="container">
			<div>
				<a href="getDashboard"><icon class="home-icon"></icon></a>
				<icon class="brd-arrow"></icon>
				Dashboard
			</div>
		</div>
	</div>
	<div class="content-sec">
		<div class="container">
			<h1>
				Campaigns<br /> <span class="dw-line"></span>
			</h1>
			<div>
				<ul class="campaigns-box owl-carousel" id="owl-example">

					<c:choose>

						<c:when test="${empty topThreeCampaigns}">
							<c:out value="${campaignNotFound}" />
						</c:when>

						<c:otherwise>
							<c:forEach items="${topThreeCampaigns}" var="campaignModel">

								<li class="item"><icon class="cb-icon"> <img
										src="<%=basePath%>resources/CampaignLogo/<c:out value="${campaignModel.campaignLogo}" />"
										alt="campaigns 1" /></icon>

									<h3 class="text-center">
										<c:out value="${campaignModel.campaignName}" />
									</h3>

									<p class="text-center">
										<c:out value="${campaignModel.campaignDescription}" />
									</p>

									<div class="progress">
										<div class="meter">
											<span style="width: 40%"></span>
										</div>

										<p class="percentage">40%</p>
									</div>

									<div class="ranking b-r">
										<a href="#">Pan Bank Ranking - 20</a>
									</div>

									<div class="ranking c-r">
										<a href="#">Pan Circle Ranking - 30</a>
									</div>

									<div class="clear"></div></li>

							</c:forEach>

						</c:otherwise>

					</c:choose>


				</ul>
				<div class="clear"></div>
			</div>

			<div class="bottom-sec">
				<ul class="bottom-cont">
					<li><a href="getDashboard"><div class="one-three flip-container">
								<div class="content-box flipper">
									<div class="content-box-front">
										<img
											src="<%=basePath%>resources/images/user/rewards-big-icon.png"
											alt="Rewards-front" />
									</div>
									<div class="content-box-back">
										<div class="backface-content">
											<img
												src="<%=basePath%>resources/images/user/rewards-big-icon_back.png"
												alt="Rewards-back" />
										</div>
									</div>
								</div>
							</div>
							<p>Rewards</p></a></li>
					<li><a href="getDashboard"><div class="one-three flip-container">
								<div class="content-box flipper">
									<div class="content-box-front">
										<img
											src="<%=basePath%>resources/images/user/target-big-icon.png"
											alt="Targets-front" />
									</div>
									<div class="content-box-back">
										<div class="backface-content">
											<img
												src="<%=basePath%>resources/images/user/target-big-icon_back.png"
												alt="Targets-back" />
										</div>
									</div>
								</div>
							</div>
							<p>Targets</p></a></li>
					<li><a href="getDashboard"><div
								class="one-three flip-container">
								<div class="content-box flipper">
									<div class="content-box-front">
										<img src="<%=basePath%>resources/images/user/faq-big-icon.png"
											alt="FAQ�s-front" />
									</div>
									<div class="content-box-back">
										<div class="backface-content">
											<img
												src="<%=basePath%>resources/images/user/faq-big-icon_back.png"
												alt="FAQ�s-back" />
										</div>
									</div>
								</div>
							</div>
							<p>FAQ's</p></a></li>
					<li><a href="getDashboard"><div class="one-three flip-container">
								<div class="content-box flipper">
									<div class="content-box-front">
										<img
											src="<%=basePath%>resources/images/user/seat-big-icon.png"
											alt="Seat Breakup-front" />
									</div>
									<div class="content-box-back">
										<div class="backface-content">
											<img
												src="<%=basePath%>resources/images/user/seat-big-icon_back.png"
												alt="Seat Breakup-back" />
										</div>
									</div>
								</div>
							</div>
							<p>Seat Breakup</p></a></li>
					<li><a href="getDashboard"><div
								class="one-three flip-container">
								<div class="content-box flipper">
									<div class="content-box-front">
										<img
											src="<%=basePath%>resources/images/user/leader-big-icon.png"
											alt="Leader Board-front" />
									</div>
									<div class="content-box-back">
										<div class="backface-content">
											<img
												src="<%=basePath%>resources/images/user/leader-big-icon_back.png"
												alt="Leader Board-back" />
										</div>
									</div>
								</div>
							</div>
							<p>Leader Board</p></a></li>
					<li><a href="getDashboard"><div
								class="one-three flip-container">
								<div class="content-box flipper">
									<div class="content-box-front">
										<img
											src="<%=basePath%>resources/images/user/escation-big-icon.png"
											alt="Escalation Matrix-front" />
									</div>
									<div class="content-box-back">
										<div class="backface-content">
											<img
												src="<%=basePath%>resources/images/user/escation-big-icon_back.png"
												alt="Escalation Matrix-back" />
										</div>
									</div>
								</div>
							</div>
							<p>Escalation Matrix</p></a></li>
					<li><a href="getDashboard"><div class="one-three flip-container">
								<div class="content-box flipper">
									<div class="content-box-front">
										<img src="<%=basePath%>resources/images/user/abr-big-icon.png"
											alt="ABR MIS-front" />
									</div>
									<div class="content-box-back">
										<div class="backface-content">
											<img
												src="<%=basePath%>resources/images/user/abr-big-icon_back.png"
												alt="ABR MIS-back" />
										</div>
									</div>
								</div>
							</div>
							<p>ABR MIS</p></a></li>
					<li><a href="getDashboard"><div class="one-three flip-container">
								<div class="content-box flipper">
									<div class="content-box-front">
										<img src="<%=basePath%>resources/images/user/av-big-icon.png"
											alt="AV-front" />
									</div>
									<div class="content-box-back">
										<div class="backface-content">
											<img
												src="<%=basePath%>resources/images/user/av-big-icon_back.png"
												alt="AV-back" />
										</div>
									</div>
								</div>
							</div>
							<p>AV</p></a></li>
				</ul>
				<div class="clear"></div>
			</div>
		</div>
	</div>
</section>


<%@ include file="user-footer.jsp"%>