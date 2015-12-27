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
			<strong>Content Details</strong>
		</h1>
	</center>
</div>
<form method="get" name="frm" action="addContentPage">
	<input type="submit" value="Add Content/Pages" class="buttonStyle">
</form>
<c:if test="${not empty cmsAdded}">
	<tr>
		<td colspan="7">
			<h3>
				<font color="red"><c:out value="${cmsAdded}"></c:out></font>
			</h3>

		</td>
	</tr>

</c:if>

</br>


<table id="example" class="genContentStyle">
	<thead>

		<tr>
			<th>Serial</th>
			<th>PageName</th>
			<th>Path</th>
			<th>Create Date</th>
			<th align="center" class="noarrow">
				<!-- Edit</th>
			<th align="center" class="noarrow">Delete -->
			</th>
		</tr>
	</thead>
	<tbody>
		<c:choose>
			<c:when test="${not empty contentNotFound}">
				<tr>
					<td colspan="7">
						<h3>
							<font color="red"><c:out value="${contentNotFound}"></c:out></font>
						</h3>

					</td>
				</tr>

			</c:when>
			<c:otherwise>
				<c:set var="count" value="0" scope="page" />
				<c:forEach items="${allContentView}" var="u">
					<c:if test="${u.status.ordinal() == 1}">
						<c:set var="count" value="${count + 1}" scope="page" />
						<tr>
							<td><c:out value="${count}" /></td>
							<td><c:out value="${u.pageName}" /></td>
							<td><c:out value="${u.path}" /></td>
							<td><c:out value="${u.createTimeStamp}" /></td>
							<td><a href="viewContentById?id=${u.id}"><img
									src="<%=basePath%>resources/images/admin/edit-new.png" /></a> <!-- </td>
							<td> -->
								<a href="deleteContentById?id=${u.id}"
								onclick="return myFunction()"><img
									src="<%=basePath%>resources/images/admin/delete-new.png" /></a></td>
						</tr>
					</c:if>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</tbody>
</table>

<%@ include file="admin-footer.jsp"%>