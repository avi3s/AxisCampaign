<%@ include file="admin-header.jsp"%>

<script>
	function confirmDelete(id) {
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
			<strong>Role Level Details</strong>
		</h1>
	</center>
</div>
<body>
	<form action="addRoleLevelForm" method="post">
		<input type="submit" value="Add Role Level" class="buttonStyle">
	</form>
	</br>
	<c:out value="${actionCompletionMessage}" />


	<table id="example" class="genContentStyle">

		<thead>
			<tr>
				<th>Serial</th>
				<th>Level Name</th>
				<th>Parent Name</th>
				<th align="center" class="noarrow">
					<!-- Edit</th>
				<th align="center" class="noarrow">Delete -->
				</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>

				<c:when test="${empty roleLevelModelListTable}">
					<tr>
						<td colspan="5"><font color="red"><c:out
									value="${noRoleLevelDataFoundForTableView}" /></font></td>
					</tr>
				</c:when>

				<c:otherwise>
					<c:set var="count" value="0" scope="page" />
					<c:forEach items="${roleLevelModelListTable}" var="roleLevelModel">
						<c:set var="count" value="${count + 1}" scope="page" />

						<tr align="center">
							<td><c:out value="${count}" /></td>
							<td><c:out value="${roleLevelModel.levelName}" /></td>
							<td><c:out value="${roleLevelModel.parentRoleLevelName}" /></td>
							<td><a
								href="editRoleLevel?rowLevelId=<c:out value="${roleLevelModel.rowLevelId}"/>"><img
									src="<%=basePath%>resources/images/admin/edit-new.png" /></a>
							<!-- </td>
							<td> -->
								<a
								href="deleteRoleLevel?rowLevelId=<c:out value="${roleLevelModel.rowLevelId}"/>"
								onclick="return confirmDelete(${roleLevelModel.rowLevelId})"><img
									src="<%=basePath%>resources/images/admin/delete-new.png" /></a></td>
						</tr>

					</c:forEach>
				</c:otherwise>
			</c:choose>
		</tbody>



	</table>

	<%@ include file="admin-footer.jsp"%>