<%@ include file="admin-header.jsp"%>

<script>
	function confirmDelete() {
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
			<strong>Target Fields Details</strong>
		</h1>
	</center>
</div>
<form action="addTargetField" method="post">
	<input type="submit" value="Add Target Field" class="buttonStyle">
</form>

</br>

<p>
	<font color="red">${targetFieldCount } </font>
</p>


<table id="example" class="genContentStyle">
	<thead>
		<tr>
			<th>Serial</th>
			<th>Campaign Name</th>
			<th>Role Name</th>
			<th>Field Name</th>
			<!-- <th>Field Type</th> -->
			<th align="center" class="noarrow">
				<!-- Edit</th>
			<th align="center" class="noarrow">Delete -->
			</th>
		</tr>
	</thead>
	<tbody>
		<c:choose>
			<c:when test="${not empty targetNotFound}">
				<tr>
					<td colspan="7">
						<h3>
							<font color="red"><c:out value="${targetNotFound}"></c:out></font>
						</h3>

					</td>
				</tr>

			</c:when>
			<c:otherwise>
				<c:set var="count" value="0" scope="page" />
				<c:forEach items="${targetList}" var="target">
					<c:set var="count" value="${count + 1}" scope="page" />
					<tr>
						<td><c:out value="${count}" /></td>
						<td>${target.campaignName }</td>
						<td>${target.roleName }</td>
						<td>${target.fieldName }</td>
						<%-- <td>${target.fieldType }</td> --%>
						<td><a href="editTargetField?id=${target.targetFieldId}"><img
								src="<%=basePath%>resources/images/admin/edit-new.png" /></a>
						<!-- </td>
						<td> -->
							<a href="deleteTargetField?id=${target.targetFieldId}"
							onclick="return confirmDelete()"><img
								src="<%=basePath%>resources/images/admin/delete-new.png" /></a></td>
					</tr>

				</c:forEach>

			</c:otherwise>
		</c:choose>
	</tbody>
</table>


<%@ include file="admin-footer.jsp"%>