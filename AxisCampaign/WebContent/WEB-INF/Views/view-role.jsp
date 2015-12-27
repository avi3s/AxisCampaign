<%@ include file="admin-header.jsp"%>

<script>
function myFunction(id) {
	var r = confirm("Are You Sure?");
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
			<strong>Role Details</strong>
		</h1>
	</center>
</div>
<form action="addRoleForm" method="post">
	<input type="submit" value="Add Role" class="buttonStyle">
</form>
</br>

<c:out value="${actionCompletionMessage}" />


<table id="example" class="genContentStyle">
	<thead>

		<tr>
			<th>Serial</th>
			<th>Role Name</th>
			<th>Role Level</th>
			<th align="center" class="noarrow">
				<!-- Edit</th>
			<th align="center" class="noarrow">Delete -->
			</th>
		</tr>
	</thead>
	<tbody>

		<c:out value="${generalException}" />

		<c:choose>
			<c:when test="${empty roleModelListTable}">
				<tr>
					<td colspan="5"><font color="red"><c:out
								value="${roleModelListNotFound}"></c:out></font></td>
				</tr>
			</c:when>
			<c:otherwise>


				<c:set var="count" value="0" scope="page" />
				<c:forEach items="${roleModelListTable}" var="roleModel">
					<c:set var="count" value="${count + 1}" scope="page" />
					<tr>
						<td><c:out value="${count}" /></td>
						<td><c:out value="${roleModel.role_name}" /></td>
						<td><c:out value="${roleModel.role_level_name}" /></td>
						<td><a
							href="editRole?roleId=<c:out value="${roleModel.roleId}"/>"><img
								src="<%=basePath%>resources/images/admin/edit-new.png" /></a>
						<!-- </td>
						<td> -->
							<a href="deleteRole?roleId=<c:out value="${roleModel.roleId}"/>"
							onclick="return myFunction(${roleModel.roleId})"><img
								src="<%=basePath%>resources/images/admin/delete-new.png" /></a></td>
					</tr>
				</c:forEach>

			</c:otherwise>

		</c:choose>
	</tbody>
</table>




<%@ include file="admin-footer.jsp"%>