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
			<strong>Escalation Matrix Details</strong>
		</h1>
	</center>
</div>
<form method="get" name="frm" action="addescalationmatrixpage">
	<input type="submit" value="Add" class="buttonStyle">
</form>
<form action="escalationUploadPage" method="get">
	<input type="submit" value="Add Bulk" class="buttonStyle">
</form>
<c:if test="${not empty escalationMessage}">
	<tr>
		<td colspan="7">
			<h3>
				<font color="red"><c:out value="${escalationMessage}"></c:out></font>
			</h3>

		</td>
	</tr>

</c:if>


</br>


<table id="example" class="genContentStyle">
	<thead>
		<tr>
			<th>Serial</th>
			<th>Campaign Name</th>
			<th>Name</th>
			<th>Email Id</th>
			<th>Contact Number</th>
			<th>Type</th>
			<th>Create Date</th>
			<th align="center" class="noarrow">
				<!-- Edit</th>
			<th align="center" class="noarrow">Delete -->
			</th>
		</tr>
	</thead>
	<tbody>
		<c:choose>
			<c:when test="${not empty escalationMatrixNotFound}">
				<tr>
					<td colspan="8">
						<h3>
							<font color="red"><c:out
									value="${escalationMatrixNotFound}"></c:out></font>
						</h3>

					</td>
				</tr>

			</c:when>
			<c:otherwise>
				<c:set var="count" value="0" scope="page" />
				<c:forEach items="${adminEscalationMatrixList}"
					var="adminEscalationMatrixList">
					<c:set var="count" value="${count + 1}" scope="page" />
					<tr>
						<td><c:out value="${count}" /></td>

						<%-- 	<td>${adminEscalationMatrixList.id}</td>  --%>
						<td>${adminEscalationMatrixList.campaignName}</td>
						<td>${adminEscalationMatrixList.name}</td>
						<td>${adminEscalationMatrixList.email}</td>
						<td>${adminEscalationMatrixList.contactNumber}</td>
						<td>${adminEscalationMatrixList.type}</td>
						<td>${adminEscalationMatrixList.createDate}</td>
						<td><a
							href="editescalationmatrix?id=${adminEscalationMatrixList.id}"><img
								src="<%=basePath%>resources/images/admin/edit-new.png" /></a>
						<!-- </td>
						<td> -->
							<a
							href="deleteescalationmatrix?id=${adminEscalationMatrixList.id}"
							onclick="return myFunction()"><img
								src="<%=basePath%>resources/images/admin/delete-new.png" /></a></td>
					</tr>
				</c:forEach>

			</c:otherwise>
		</c:choose>
	</tbody>
</table>

<%@ include file="admin-footer.jsp"%>