<%@ include file="user-header.jsp"%>

<section class="db-content">
	<div class="heading">
		<div class="container">
			<h1>Upload File</h1>
		</div>
	</div>
	<div class="breadcramb">
		<div class="container">
			<div>
				<a href="getDashboard"><icon class="home-icon"></icon></a>
				<icon class="brd-arrow"></icon>
				Upload File
			</div>
		</div>
	</div>
	<div class="content-sec">
		<div class="container">
			<ul class="notification-list">
				<form class="fullForm" method="post" name="frm"
					action="saveUserUploadFile" enctype="multipart/form-data">


					<table align="center" class="formStyles">



						<tr>
							<td>Upload Image</td>
							<td><input class="buttonStyle" type="file" readonly
								placeholder="Readonly input here…" name="imageName" multiple
								id="imageName"></td>
						</tr>

						<tr>
							<td>&nbsp;</td>
							<td><input type="submit" value="Save" class="buttonStyle"
								onclick="return validate()"><a href="viewUserUploadFile"><input
									type="button" value="Cancel" id="backbutton"
									class="buttonStyle2"></a></td>
						</tr>

					</table>
				</form>
			</ul>
		</div>
	</div>
</section>
<%@ include file="user-footer.jsp"%>