<%@ include file="user-header.jsp"%>

<section class="db-content">
	<div class="heading">
		<div class="container">
			<h1>Profile</h1>
		</div>
	</div>
	<div class="breadcramb">
		<div class="container">
			<div>
				<a href="getDashboard"><icon class="home-icon"></icon></a>
				<icon class="brd-arrow"></icon>
				Profile
			</div>
		</div>
	</div>
	<div class="content-sec">
		<div class="container">

			<div class="profile-detail clearfix">
				<div class="profile-detail-left">
					<span class="profile-left-img"><img
						src="<%=basePath%>resources/ProfilePictures/<c:out value="${sessionScope.profilePicture}"/>"
						width="" height="" /></span>
					<div class="edit-profile">
						<a href="editProfile" class="edit-profile">edit Profile</a>
					</div>
				</div>
				<div class="profile-detail-right">
					<span class="pl-name"><c:out
							value="${sessionScope.employeeName}" /></span> <span class="call"><icon
							class="ph-ico"></icon><span>Call :</span>
					<c:out value="${sessionScope.primaryTelephoneNumber}" /></span> <span
						class="email"><icon class="mail-ico"></icon><span>Email
							:</span> <a href="#"><c:out value="${sessionScope.emailId}" /></a></span>
					<p class="branch-n">
						<span>Branch :</span>
						<c:out value="${sessionScope.branchName}" />
					</p>
					<p>
						<c:out value="${sessionScope.about}" />
					</p>

				</div>
			</div>

		</div>
	</div>
</section>


<%@ include file="user-footer.jsp"%>