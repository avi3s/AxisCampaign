<%@ include file="admin-header.jsp"%>
<body>
	<div class="titleArea">
		<center>
			<h1>
				<strong>Update User Header Image</strong>
			</h1>
		</center>
	</div>

	<form class="fullForm" method="post" name="frm"
		action="updateUserHeaderPic" enctype="multipart/form-data">

		<table align="center" class="formStyles">

			<tr>
				<td><input type="hidden"
					value="${userHeaderImageModel.userHeaderImageId}"
					name="userHeaderImageId"></td>
				<td><input type="hidden"
					value="${userHeaderImageModel.imageName}" name="imageName"></td>
			</tr>

			<tr>
				<td>Upload User Header Images</td>
				<td><input class="buttonStyle" type="file" readonly
					placeholder="Readonly input here…" name="imageName1"></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td><input type="submit" value="Save"><a
					href="viewUserHeaderPicture"><input type="button"
						value="Cancel" id="backbutton" class="buttonStyle2"></a></td>
			</tr>

		</table>


	</form>

	<%@ include file="admin-footer.jsp"%>