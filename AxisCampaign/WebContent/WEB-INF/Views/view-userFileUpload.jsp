<%@ include file="user-header.jsp"%>

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

<section class="db-content">
	<div class="heading">
		<div class="container">
			<h1>File Upload</h1>
		</div>
	</div>
	<div class="breadcramb">
		<div class="container">
			<div>
				<a href="getDashboard"><icon class="home-icon"></icon></a>
				<icon class="brd-arrow"></icon>
				File Upload
			</div>
		</div>
	</div>
	<div class="content-sec">
		<div class="container">
			<ul class="notification-list">
				<form method="POST" name="frm" action="addUserUploadFile">
					<input type="submit" value="Upload File" class="buttonStyle">
				</form>

				<c:if test="${not empty fileUploaded}">
					<h4 style="color: red;">
						<c:out value="${fileUploaded}"></c:out>
					</h4>
				</c:if>

				<br>

				<table id="example" class="genContentStyle">
					<thead>
						<tr>
							<th align="center">Serial</th>
							<th align="center">File Name</th>
							<th align="center" class="noarrow">
								<!-- Edit</th>
			<th align="center" class="noarrow">Delete -->
							</th>
						</tr>
					</thead>
					<tbody>
						<c:choose>
							<c:when test="${not empty userFileUpload}">
								<tr>
									<td colspan="3">
										<h3>
											<font color="red"><c:out value="${userFileUpload}"></c:out></font>
										</h3>

									</td>
								</tr>

							</c:when>
							<c:otherwise>
								<c:set var="count" value="0" scope="page" />
								<c:forEach items="${userFileUploadList}"
									var="UserFileUploadModel">
									<c:set var="count" value="${count + 1}" scope="page" />
									<tr>
										<td align="center"><c:out value="${count}" /></td>
										<td align="center">${UserFileUploadModel.fileName}</td>

										<td align="center"><a
											href="updateUserUploadFile?id=${UserFileUploadModel.userFileUploadId}"
											onclick="return myFunction()"><img
												src="<%=basePath%>resources/images/admin/delete-new.png" /></a></td>
									</tr>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</tbody>
				</table>
			</ul>
		</div>
	</div>
</section>
<%@ include file="user-footer.jsp"%>