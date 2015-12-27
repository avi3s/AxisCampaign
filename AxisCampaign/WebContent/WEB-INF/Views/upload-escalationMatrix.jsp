<%@ include file="admin-header.jsp"%>
<script>
/* --- for Bulk Escalation Matrix --- */
function checkForm(bulkEscMatrixField)
{
	if(bulkEscMatrixField.campaignId.value=="0" || bulkEscMatrixField.campaignId.value=="Campaign Name")
	{
		alert ('Please Select a Campaign Name!');
		bulkEscMatrixField.campaignId.focus();
		return false;
	}
	if(bulkEscMatrixField.type.value=="0" || bulkEscMatrixField.type.value=="Type")
	{
		alert ('Please Select a Type!');
		bulkEscMatrixField.type.focus();
		return false;
	}
	return true;
}
</script>

<div class="titleArea">
	<center>
		<h1>
			<strong>Bulk Upload Escalation Matrix</strong>
		</h1>
	</center>
</div>
<c:if test="${not empty wrongFileFormat}">
	<c:out value="${wrongFileFormat}" />
</c:if>

<c:if test="${not empty successMessage}">
	<c:out value="${successMessage}" />
</c:if>
<c:if test="${not empty unstoredUserList}">
		The following list will has not been stored due to validation issues.
		<br>

	<table class="genContentStyle">
		<thead>
			<tr>
				<th>Sl no</th>
				<th>Name</th>
				<th>Email</th>
				<th>Contact Number</th>
				<th>Issue</th>
			</tr>
		</thead>
		<tbody>
			<c:set var="count" value="0" scope="page" />
			<c:forEach items="${unstoredUserList}" var="userModel">
				<c:set var="count" value="${count + 1}" scope="page" />
				<tr>
					<td><c:out value="${count}" /></td>
					<td><c:out value="${userModel.name}" /></td>
					<td><c:out value="${userModel.email}" /></td>
					<td><c:out value="${userModel.contactNumber}" /></td>
					<td><c:out value="${userModel.issue}" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<br>
	<br>

</c:if>
<br>
<c:if test="${not empty errorRowLists}">
		The following row numbers <c:forEach items="${errorRowLists}"
		var="rowNumber">
		<c:out value="${rowNumber.rowMissMatchCount}" />,
		                          </c:forEach> have not been inserted due to row mismatch

		</c:if>
<br>
<br>
<c:if test="${not empty file_not_supported}">
	<c:out value="${file_not_supported}" />
</c:if>


<form class="fullForm" id="bulkEscMatrixField"
	enctype="multipart/form-data" method="POST"
	action="addEscalationMatrixUpload"
	onSubmit="return checkForm(bulkEscMatrixField)">

	<c:choose>
		<c:when test="${not empty NoActiveRoleFound}">
			<h3>
				<font color="red"><c:out value="${NoActiveRoleFound}"></c:out></font>
			</h3>
		</c:when>
		<c:otherwise>

			<table align="center" class="formStyles">

				<tr>
					<td>Select Campaign Name</td>
					<td><select name="campaignId" id="campaignId" class="select">
							<option value="0">-- Select --</option>
							<c:forEach items="${campaignList}" var="campaign">
								<option value="${campaign.campaignId}">${campaign.campaignName}</option>
							</c:forEach>

					</select></td>
				</tr>
				<tr>
					<td>Field Type</td>
					<td><select name="type" id="type" class="select">
							<option value="0">-- Select --</option>
							<option value="Talisma">Talisma</option>
							<option value="Contest Team">Contest Team</option>
					</select></td>
				</tr>

				<tr>
					<td>Please Upload an Excel File</td>
					<td><input type="file" id="fileName" name="fileName" size="50" /></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><input type="submit" value="Save"><a
						href="viewEscalationMatrix"><input type="button"
							value="Cancel" id="backbutton" class="buttonStyle2"></a></td>
				</tr>
			</table>

		</c:otherwise>
	</c:choose>
</form>
<%@ include file="admin-footer.jsp"%>