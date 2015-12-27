<%@ include file="admin-header.jsp"%>

<script type="text/javascript" language="javascript">
	$(document).ready(
			function() {
				var financialYear = $("#financialYear1").val();
				$("#financialYear").val(financialYear);
				var quarterlyID = $("#quarterlyID1").val();
				$("#quarterId").val(quarterlyID);

				var selected = $("#roleID_array_to_show").val();
				var obj = $('#roleID_array');

				var i = 0, size = selected.length;
				for (i; i < size; i++) {
					$("#roleID_array option[value='" + selected[i] + "']")
							.attr("selected", 1);
				}

			});
	
	function checkForm(editcampMgt)
	{
		if(editcampMgt.roleID_array.value=="" || editcampMgt.roleID_array.value=="Role Name")
		{
			alert ('Please Select the Role Name!');
			editcampMgt.roleID_array.focus();
			return false;
		}
		if(editcampMgt.campaignName.value=="" || editcampMgt.campaignName.value=="Campaign Name")
		{
			alert ('Please Enter Campaign Name!');
			editcampMgt.campaignName.focus();
			return false;
		}
		if(editcampMgt.financialYear.value=="" || editcampMgt.financialYear.value=="Financial Year")
		{
			alert ('Please Select the Financial Year!');
			editcampMgt.financialYear.focus();
			return false;
		}
		if(editcampMgt.quarterId.value=="" || editcampMgt.quarterId.value=="Quarter")
		{
			alert ('Please Select the Quarter!');
			editcampMgt.quarterId.focus();
			return false;
		}
		if(editcampMgt.txtarea.value=="" || editcampMgt.txtarea.value=="Message")
		{
			alert ('Please Type your Message!');
			editcampMgt.txtarea.focus();
			return false;
		}
		return true;
	}
	
	
	
	
	
	
	
	
	
	
</script>
<body>

	<%-- 	<c:choose>
		<c:when test="${campaignModel.requestType == 1}"> --%>


	<input type="hidden" value="${campaignModel.financialYear}"
		name="financialYear1" id="financialYear1">
	<input type="hidden" value="${campaignModel.quarterId}"
		name="quarterlyID1" id="quarterlyID1">

	<input type="hidden" value="${campaignModel.roleID_array_to_show}"
		id="roleID_array_to_show">


	<input type="hidden" value="${campaignModel.roleId}" name="role_id"
		id="role_id">

	<center>
		<h1>Update Campaign Details</h1>
	</center>


	<c:if test="${not empty Role_not_null}">
		<h4 style="color: red;">
			<c:out value="${Role_not_null}"></c:out>
		</h4>
	</c:if>
	<c:if test="${not empty Campaign_Name_not_null}">
		<h4 style="color: red;">
			<c:out value="${Campaign_Name_not_null}"></c:out>
		</h4>
	</c:if>
	<c:if test="${not empty Campaign_FinancialYear_not_null}">
		<h4 style="color: red;">
			<c:out value="${Campaign_FinancialYear_not_null}"></c:out>
		</h4>
	</c:if>
	<c:if test="${not empty Campaign_QuarterId_not_null}">
		<h4 style="color: red;">
			<c:out value="${Campaign_QuarterId_not_null}"></c:out>
		</h4>
	</c:if>
	<c:if test="${not empty Campaign_Description_not_null}">
		<h4 style="color: red;">
			<c:out value="${Campaign_Description_not_null}"></c:out>
		</h4>
	</c:if>
	<c:if test="${not empty Campaign_Logo_not_null}">
		<h4 style="color: red;">
			<c:out value="${Campaign_Logo_not_null}"></c:out>
		</h4>
	</c:if>
	<c:if test="${not empty Campaign_Name_Already_Exists}">
		<h4 style="color: red;">
			<c:out value="${Campaign_Name_Already_Exists}"></c:out>
		</h4>
	</c:if>

	<c:if test="${not empty campaignAdded}">
		<h4 style="color: red;">
			<c:out value="${campaignAdded}"></c:out>
		</h4>
	</c:if>

	<c:choose>


		<c:when test="${not empty roleNotFound}">
			<h3>
				<font color="red"><c:out value="${roleNotFound}"></c:out></font>
			</h3>

		</c:when>
		<c:otherwise>

			<form class="fullForm" method="post" name="frm"
				action="updateCampaign" enctype="multipart/form-data"
				id="editcampMgt" onSubmit="return checkForm(editcampMgt)">

				<table align="center" class="formStyles">

					<tr>
						<td><input type="hidden" value="${campaignModel.campaignId}"
							name="campaignId" id="id"></td>
					<tr>
					<tr>
						<td>Role Name</td>
						<td><select name="roleID_array" class="multipleselect"
							multiple id="roleID_array">
								<!-- <option value="">-- Select --</option> -->
								<c:forEach items="${roleList}" var="roleList">
									<option value="${roleList.roleId}">${roleList.role_name}</option>
								</c:forEach>
						</select></td>
					</tr>

					<tr>
						<td>Campaign Name</td>
						<td><input type="text" name="campaignName" id="campaignName"
							value="${fn:escapeXml(campaignModel.campaignName)}"></td>
					</tr>

					<tr>
						<td>Select Financial Year</td>
						<td><select name="financialYear" id="financialYear"
							class="select">
								<option value="">-- Select --</option>
								<option value="2015-2016">2015-2016</option>
								<option value="2016-2017">2016-2017</option>
								<option value="2017-2018">2017-2018</option>
								<option value="2018-2019">2018-2019</option>
								<option value="2019-2020">2019-2020</option>
						</select></td>
					</tr>

					<tr>
						<td>Select Quarter</td>
						<td><select name="quarterId" id="quarterId" class="select">
								<option value="">-- Select --</option>
								<option value="1">Quarter 1</option>
								<option value="2">Quarter 2</option>
								<option value="3">Quarter 3</option>
								<option value="4">Quarter 4</option>
						</select></td>
					</tr>


					<tr>
						<td>Campaign Description</td>
						<td><textarea id="txtarea" class="normal" rows="3"
								placeholder="Type your message..." name="campaignDescription">${campaignModel.campaignDescription}</textarea></td>
					</tr>

					<tr>
						<td></td>
						<td><a
							href="downloadCampaignLogo?id=${campaignModel.campaignId}">Download
								Campaign Logo</a></td>
					</tr>

					<tr>
						<td>Campaign Logo</td>
						<td><input class="buttonStyle" type="file" readonly
							placeholder="Readonly input here…" name="campaignLogoFile"
							id="campaignLogoFile"></td>
					</tr>

					<c:if test="${not empty campaignFileModels}">

						<tr>
							<td>Uploaded File List</td>
							<td><c:forEach items="${campaignFileModels}"
									var="campaignFileModel">


									<a
										href="downloadCampaignFile?campaign_file_id=${campaignFileModel.campaignFileId}">${campaignFileModel.fileName}</a>&nbsp;&nbsp;&nbsp;
							<a
										href="deleteCampaignFile?campaign_file_id=${campaignFileModel.campaignFileId}&campaign_id=${campaignModel.campaignId}">Delete</a>
									<br>
								</c:forEach></td>
						</tr>

					</c:if>

					<tr>
						<td>File Name</td>
						<td><input class="buttonStyle" type="file" readonly
							placeholder="Readonly input here…" name="fileName" multiple
							id="fileName"></td>
					</tr>

					<tr>
						<td>&nbsp;</td>
						<td><input type="submit" value="Save"
							onclick="return validate()"><a href="viewCampaign"><input
								type="button" value="Cancel" id="backbutton"
								class="buttonStyle2"></a></td>
					</tr>

				</table>
			</form>
		</c:otherwise>

	</c:choose>

	<%@ include file="admin-footer.jsp"%>