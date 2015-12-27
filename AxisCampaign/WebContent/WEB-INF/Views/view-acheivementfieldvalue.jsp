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
			<strong>Achievement Fields Value Details</strong>
		</h1>
	</center>
</div>
<form class="fullForm" method="get" name="addCampaignFields"
	action="addAcheivementFieldValues">

	<input type="submit" value="Add" class="buttonStyle">
</form>

<form class="fullForm" method="get"
	action="updateacheivementexcellsheet">

	<input type="submit" value="Add Bulk" class="buttonStyle">
</form>
</br>

<c:if test="${not empty afterAdded}">


	<%-- 	<c:out value="${afterAdded}"></c:out> --%>
	<h4 style="color: red;">${afterAdded}</h4>



</c:if>

<c:if test="${not empty updatedValue}">


	<%-- <c:out value="${updatedValue}"></c:out> --%>

	<h4 style="color: red;">${updatedValue}</h4>


</c:if>


<table id="example" class="genContentStyle">
	<thead>
		<tr>
			<th>Serial</th>
			<th>Field Name</th>
			<th>Field Value</th>
			<!-- <th>Create Date</th>-->
			<th align="center" class="noarrow">
				<!-- Edit</th>
			<th align="center" class="noarrow">Delete -->
			</th>
		</tr>
	</thead>
	<tbody>
		<c:choose>
			<c:when test="${not empty acheivementFieldValueNotFound}">
				<tr>
					<td colspan="7">
						<h3>
							<font color="red"><c:out
									value="${acheivementFieldValueNotFound}"></c:out></font>
							<%-- <h4 style="color: red;">${acheivementFieldValueNotFound}</h4> --%>
						</h3>

					</td>
				</tr>

			</c:when>
			<c:otherwise>
				<c:forEach var="acheivementModel"
					items="${acheivementFieldValueModels}" varStatus="loop">
					<c:set var="count" value="${count + 1}" scope="page" />
					<tr>
						<td><c:out value="${count}" /></td>
						<td>${acheivementModel.fieldName}</td>
						<td>${acheivementModel.fieldValue}</td>

						<td><a
							href="editAcheivementFieldValue?acheivementFieldValueId=${acheivementModel.acheivementFieldValueId}"><img
								src="<%=basePath%>resources/images/admin/edit-new.png" /></a>
						<!-- </td>
						<td> -->
							<a
							href="deleteAcheivementFieldValue?acheivementFieldValueId=${acheivementModel.acheivementFieldValueId}"
							onclick="return confirmDelete()"><img
								src="<%=basePath%>resources/images/admin/delete-new.png" /></a></td>
					</tr>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</tbody>
</table>

<script type="text/javascript">
	function anisu() {
		document.getElementById('aniisuu').style.display = 'block';
	}
</script>


<%@ include file="admin-footer.jsp"%>