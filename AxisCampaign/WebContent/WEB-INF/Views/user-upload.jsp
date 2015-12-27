<%@ include file="admin-header.jsp"%>
<script>
/* --- for Bulk User Management --- */
function checkForm(bulkUser)
{
	//Parent Role Level
	if(bulkUser.roleId.value=="0" || bulkUser.roleId.value=="Role ID")
	{
		alert ('Please Select a Role Level!');
		bulkUser.roleId.focus();
		return false;
	}
	return true;
}
</script>
<div class="titleArea">
	<center>
		<h1>
			<strong>Add Bulk User Details</strong>
		</h1>
	</center>
</div>
<c:if test="${not empty unstoredUserList}">
		The following list will has not been stored due to validation issues.
		<br>
	<table class="genContentStyle">

		<tr>
			<th>Email Address</th>
			<th>Employee Name</th>
			<th>Employee Number</th>
			<th>Primary telephone</th>
			<th>Reason</th>
		</tr>

		<c:forEach items="${unstoredUserList}" var="userModel">
			<tr>
				<td><c:out value="${userModel.emailAddress}" /></td>
				<td><c:out value="${userModel.employeeName}" /></td>
				<td><c:out value="${userModel.employeeNumber}" /></td>
				<td><c:out value="${userModel.primaryTelephoneNumber}" /></td>
				<td><c:forEach items="${userModel.error_message_list}"
						var="error_message">
						<c:out value="${error_message}" />
					</c:forEach></td>
			</tr>
		</c:forEach>
	</table>
	<br>
	<br>
</c:if>
<c:if test="${not empty mismatchedRowsList}">
		The following row numbers <c:forEach items="${mismatchedRowsList}"
		var="rowNumber">
		<c:out value="${rowNumber}" />,
		                          </c:forEach> have not been inserted due to row mismatch

		</c:if>
<br>
<br>
<br>
<c:if test="${not empty file_not_supported}">
	<c:out value="${file_not_supported}" />
</c:if>


<form class="fullForm" enctype="multipart/form-data" method="POST"
	action="bulkUploadUser" id="bulkUser"
	onSubmit="return checkForm(bulkUser)">

	<c:choose>
		<c:when test="${not empty NoActiveRoleFound}">
			<h3>
				<font color="red"><c:out value="${NoActiveRoleFound}"></c:out></font>
			</h3>
		</c:when>
		<c:otherwise>

			<table align="center" class="formStyles">
				<tr>
					<td>Select Role Name</td>
					<td><select name="roleId" id="roleId" class="select">
							<option value="0">-- Select --</option>
							<c:forEach items="${roleList}" var="role">
								<option value="${role.roleId}">${role.role_name}</option>
							</c:forEach>
					</select></td>
				</tr>

				<tr>
					<td>Select Excel/CSV File</td>
					<td><input type="file" id="fileName" name="fileName" size="50"
						class="buttonStyle" /></td>
				</tr>
				<tr>
					<td id="demoExcel"><a id="downloadExcel" href="userDemoExcel"><input
							type="button" value="Download Demo Excel File" id="excell"
							class="buttonStyle2"></a></td>

					<td><input type="submit" value="Upload Bulk"><a
						href="viewUser"><input type="button" value="Cancel"
							id="backbutton" class="buttonStyle2"></a></td>
				</tr>
			</table>
		</c:otherwise>
	</c:choose>
</form>



<%@ include file="admin-footer.jsp"%>