<%@ include file="admin-header.jsp"%>
<body>
	<div class="titleArea">
		<center>
			<h1>
				<strong>Add User Header Image</strong>
			</h1>
		</center>
	</div>


	</br>

	<form class="fullForm" method="post" name="frm"
		action="saveUserHeaderPic" enctype="multipart/form-data"
		id="userHeaderMgt">


		<table align="center" class="formStyles">



			<tr>
				<td>Upload Image</td>
				<td><input class="buttonStyle" type="file" readonly
					placeholder="Readonly input here…" name="imageName" multiple
					id="fileName"></td>
			</tr>

			<tr>
				<td>&nbsp;</td>
				<td><input type="submit" value="Save"
					onclick="return validate()"><a href="viewCampaign"><input
						type="button" value="Cancel" id="backbutton" class="buttonStyle2"></a></td>
			</tr>

		</table>
	</form>

	<%@ include file="admin-footer.jsp"%>