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
			<strong>View User Specific File Upload Management Details</strong>
		</h1>
	</center>
</div>
<form class="fullForm" method="get" name="frm"
	action="addpromotionalfileuploadpage">
	<input type="submit" value="Add Promotional File" class="buttonStyle">
</form>

<c:if test="${not empty userFileAdded}">
	<h4 style="color: red;">
		<c:out value="${userFileAdded}"></c:out>
	</h4>
</c:if>

<br>
<table id="example" class="genContentStyle">
	<thead>
		<tr>
			<th align="center">Serial</th>
			<th align="center">Campaign Name</th>
			<th align="center">User Name</th>
			<!-- <th align="center">File Name</th> -->
			<th align="center">Create Date</th>
			<th align="center" class="noarrow">
				<!-- Edit</th>
			<th align="center" class="noarrow">Delete -->
			</th>
		</tr>
	</thead>
	<tbody>
		<c:choose>
			<c:when test="${not empty promotionalFileNotFound}">
				<tr>
					<td colspan="7">
						<h3>
							<font color="red"><c:out
									value="${promotionalFileNotFound}"></c:out></font>
						</h3>

					</td>
				</tr>

			</c:when>
			<c:otherwise>
				<c:set var="count" value="0" scope="page" />
				<c:forEach items="${adminpromotionalFileList}"
					var="adminpromotionalFileList">
					<c:set var="count" value="${count + 1}" scope="page" />
					<tr>
						<td align="center"><c:out value="${count}" /></td>
						<td align="center">${adminpromotionalFileList.campaignModel.campaignName}</td>
						<td align="center">${adminpromotionalFileList.userModel.employeeName}</td>
						<%-- <td align="center">${adminpromotionalFileList.fileName}</td> --%>
						<td align="center">${adminpromotionalFileList.createTimeStamp}</td>
						<td align="center"><a
							href="editpromotionalfile?id=${adminpromotionalFileList.campaignFileUserId}"><img
								src="<%=basePath%>resources/images/admin/edit-new.png" /></a>
						<!-- </td>
						<td align="center"> -->
							<a
							href="deletepromotionalfile?id=${adminpromotionalFileList.campaignFileUserId}"
							onclick="return myFunction()"><img
								src="<%=basePath%>resources/images/admin/delete-new.png" /></a></td>
					</tr>
				</c:forEach>

			</c:otherwise>
		</c:choose>
	</tbody>
</table>

<%@ include file="admin-footer.jsp"%>