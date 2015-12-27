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
			<strong>Target Field Value Details</strong>
		</h1>
	</center>
</div>
<form action="addTargetFieldValue" method="post">
	<input type="submit" value="Add" class="buttonStyle">
</form>
<form action="addTargetFieldValueUpload" method="post">
	<input type="submit" value="Add Bulk" class="buttonStyle">
</form>

</br>

<%-- <c:if test="${not empty mismatchedRowsList}">
		The following row numbers <c:forEach items="${mismatchedRowsList}"
			var="rowNumber">
			<c:out value="${rowNumber}" />,
		                          </c:forEach> have not been inserted due to row mismatch

</c:if> --%>

<p>
	<font color="red">${totalTargetValue } </font>
</p>

<table id="example" class="genContentStyle">
	<thead>
		<tr>
			<th>Serial</th>
			<th>Field Name</th>
			<th>Field Value</th>
			<th align="center" class="noarrow">
				<!-- Edit</th>
			<th align="center" class="noarrow">Delete -->
			</th>
		</tr>
	</thead>
	<tbody>
		<c:choose>
			<c:when test="${not empty targetValueNotFound}">
				<tr>
					<td colspan="5">
						<h3>
							<font color="red"><c:out value="${targetValueNotFound}"></c:out></font>
						</h3>

					</td>
				</tr>

			</c:when>
			<c:otherwise>
				<c:set var="count" value="0" scope="page" />
				<c:forEach items="${targetValueList}" var="targetValue">
					<c:set var="count" value="${count + 1}" scope="page" />
					<tr>
						<td><c:out value="${count}" /></td>
						<td>${targetValue.fieldName}</td>
						<td>${targetValue.fieldValue}</td>
						<td><a
							href="editTargetFieldValue?id=${targetValue.targetFieldValueId}"><img
								src="<%=basePath%>resources/images/admin/edit-new.png" /></a>
						<!-- </td>
						<td> -->
							<a
							href="deleteTargetFieldValue?id=${targetValue.targetFieldValueId}"
							onclick="return confirmDelete()"><img
								src="<%=basePath%>resources/images/admin/delete-new.png" /></a></td>
					</tr>


				</c:forEach>

			</c:otherwise>
		</c:choose>
	</tbody>
</table>

<%-- <p>
	<font color="red">${noOfRecords }</font>
</p> --%>


<%@ include file="admin-footer.jsp"%>