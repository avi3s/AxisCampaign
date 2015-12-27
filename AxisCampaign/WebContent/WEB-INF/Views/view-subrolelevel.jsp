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
			<strong>Sub-Role Level Details</strong>
		</h1>
	</center>
</div>
<form action="addSubRoleLevelForm" method="post">
	<input type="submit" value="Add Sub Role Level" class="buttonStyle">
</form>


<c:out value="${actionCompletionMessage}" />
</br>


<table id="example" class="genContentStyle">
	<thead>
		<tr>
			<th>Serial</th>
			<th>Sub Level Name</th>
			<th>Sol Id</th>
			<th>Category</th>
			<th>Designated Head</th>
			<th align="center" class="noarrow">
				<!-- Edit</th>
			<th align="center" class="noarrow">Delete -->
			</th>
		</tr>
	</thead>
	<tbody>
		<c:choose>
			<c:when test="${empty subRoleLevelModelListTable}">
				<tr>
					<td colspan="7"><font color="red"><c:out
								value="${noSubRoleLevelsPresent}" /></font></td>
				</tr>
			</c:when>

			<c:otherwise>


				<c:set var="count" value="0" scope="page" />
				<c:forEach items="${subRoleLevelModelListTable}"
					var="subRoleLevelModel">
					<c:set var="count" value="${count + 1}" scope="page" />
					<tr>
						<td><c:out value="${count}" /></td>
						<td><c:out value="${subRoleLevelModel.description}" /></td>
						<td><c:out value="${subRoleLevelModel.uniqueId}" /></td>
						<td><c:out value="${subRoleLevelModel.category}" /></td>
						<td><c:out value="${subRoleLevelModel.parent}" /></td>
						<td><a
							href="editSubRoleLevel?subRoleLevelId=<c:out value="${subRoleLevelModel.subRoleLevelId}"/>"><img
								src="<%=basePath%>resources/images/admin/edit-new.png" /></a>
						<!-- </td>
						<td> -->
							<a
							href="deleteSubRoleLevel?subRoleLevelId=<c:out value="${subRoleLevelModel.subRoleLevelId}"/>"
							onclick="return myFunction(${subRoleLevelModel.subRoleLevelId})"><img
								src="<%=basePath%>resources/images/admin/delete-new.png" /></a></td>
					</tr>
				</c:forEach>
			</c:otherwise>
		</c:choose>

	</tbody>
</table>

<%@ include file="admin-footer.jsp"%>