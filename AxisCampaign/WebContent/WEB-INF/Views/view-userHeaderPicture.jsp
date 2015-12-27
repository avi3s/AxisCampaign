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
			<strong>User Header Image Details</strong>
		</h1>
	</center>
</div>
<form method="get" name="frm" action="addUserHeader">
	<input type="submit" value="Add User Header Image" class="buttonStyle">
</form>


<c:if test="${not empty campaignAdded}">
	<h4 style="color: red;">
		<c:out value="${campaignAdded}"></c:out>
	</h4>
</c:if>

<br>

<table id="example" class="genContentStyle">
	<thead>
		<tr>
			<th align="center">Serial</th>
			<th align="center">Image Name</th>
			<th align="center" class="noarrow">
				<!-- Edit</th>
			<th align="center" class="noarrow">Delete -->
			</th>
		</tr>
	</thead>
	<tbody>
		<c:choose>
			<c:when test="${not empty userHeaderPic}">
				<tr>
					<td colspan="3">
						<h3>
							<font color="red"><c:out value="${userHeaderPic}"></c:out></font>
						</h3>

					</td>
				</tr>

			</c:when>
			<c:otherwise>
				<c:set var="count" value="0" scope="page" />
				<c:forEach items="${userHeaderImageList}" var="userHeaderImageModel">
					<c:set var="count" value="${count + 1}" scope="page" />
					<tr>
						<td align="center"><c:out value="${count}" /></td>
						<td align="center">${userHeaderImageModel.imageName}</td>


						<td align="center"><a
							href="edituserHeaderImage?id=${userHeaderImageModel.userHeaderImageId}"><img
								src="<%=basePath%>resources/images/admin/edit-new.png" /> <a
								href="deleteuserHeaderImage?id=${userHeaderImageModel.userHeaderImageId}"
								onclick="return myFunction()"><img
									src="<%=basePath%>resources/images/admin/delete-new.png" /></a></td>
					</tr>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</tbody>
</table>

<%@ include file="admin-footer.jsp"%>