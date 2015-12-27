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

			<form method="post" action="saveUserChanges"
				enctype="multipart/form-data">

				<div class="profile-detail clearfix">
					<div class="profile-detail-left">
						<span class="profile-left-img"><img
							src="<%=basePath%>resources/ProfilePictures/<c:out value="${sessionScope.profilePicture}"/>"
							width="" height="" /></span>
						<div class="edit-profile">
							<input type="file" name="profilePictureFile" class="edit-profile" />Change
							Image

							<!-- <a href="#" class="edit-profile">Change
							Image</a> -->
						</div>
					</div>
					<div class="profile-detail-right">

						<input type="hidden" name="profilePicture"
							value="${userModel.profilePicture}">

						<!-- <label>Name</label> <input type="text" name=""> <label>Email</label>
						<input type="email" name=""> <label>Branch</label> <input
							type="text" name=""> <label>Password</label> <input
							type="password" name="">  -->

						<label>Secondary Telephone No</label> <input type="text"
							name="secondaryTelephoneNumber"
							value="${userModel.secondaryTelephoneNumber}"> <label>Description</label>
						<textarea name="about" rows="3"
							placeholder="Type your Details....">${userModel.about}</textarea>

						<input type="submit" class="save-changes" value="Save Changes">
						<!-- <a href="saveUserChanges" class="save-changes">Save Changes</a> -->

					</div>
				</div>
			</form>
		</div>
	</div>
</section>

<%@ include file="user-footer.jsp"%>