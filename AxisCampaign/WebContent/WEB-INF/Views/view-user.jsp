<%@ include file="admin-header.jsp"%>

<script>
function myFunction(id) {
	var r = confirm("Are You Sure?????");
	if (r == true) {
		return true;
	} else {
		return false;
	}
}

</script>
<div class="titleArea">
	<center>
		<h1>
			<strong>User Details</strong>
		</h1>
	</center>
</div>
<form action="addUserForm" method="post">
	<input type="submit" value="Add" class="buttonStyle">
</form>

<form action="userUploadPage" method="post">
	<input type="submit" value="Add Bulk" class="buttonStyle">
</form>

</br>
<c:out value="${actionCompletionMessage}" />


<table id="example" class="genContentStyle">
	<thead>
		<tr>
			<th>Serial</th>
			<th>Employee Name</th>
			<th>Employee Number</th>
			<th>Primary Telephone Number</th>
			<th>Email Address</th>
			<th>Role Name</th>
			<th>Sub Role Level</th>
			<!-- 	<th>Parent User</th> -->
			<th align="center" class="noarrow">
				<!-- Edit</th>
			<th align="center" class="noarrow">Delete -->
			</th>
		</tr>
	</thead>
	<tbody>
		<c:choose>

			<c:when test="${empty userModelsListForTable}">
				<tr>
					<td colspan="10"><font color="red"><c:out
								value="${userListNotFound}"></c:out></font></td>
				</tr>

			</c:when>

			<c:otherwise>
				<c:set var="count" value="0" scope="page" />
				<c:forEach items="${userModelsListForTable}" var="userModel">
					<c:set var="count" value="${count + 1}" scope="page" />
					<tr>
						<td><c:out value="${count}" /></td>
						<td><c:out value="${userModel.employeeName}" /></td>
						<td><c:out value="${userModel.employeeNumber}" /></td>
						<td><c:out value="${userModel.primaryTelephoneNumber}" /></td>
						<td><c:out value="${userModel.emailAddress}" /></td>
						<td><c:out value="${userModel.roleName}" /></td>
						<td><c:out value="${userModel.subRoleLevelname}" /></td>
						<%-- <td><c:out value="${userModel.parentUserName}" /></td> --%>
						<td><a
							href="editUser?userId=<c:out value="${userModel.userId}"/>"><img
								src="<%=basePath%>resources/images/admin/edit-new.png" /></a>
						<!-- </td>
						<td -->
							<a href="deleteUser?userId=<c:out value="${userModel.userId}"/>"
							onclick="return myFunction(${userModel.userId})"><img
								src="<%=basePath%>resources/images/admin/delete-new.png" /></a></td>
					</tr>
				</c:forEach>

			</c:otherwise>

		</c:choose>
	</tbody>
</table>




<%@ include file="admin-footer.jsp"%>