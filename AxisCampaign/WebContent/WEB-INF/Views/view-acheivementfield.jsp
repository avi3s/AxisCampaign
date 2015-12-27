
<%@ include file="admin-header.jsp"%>

<script type="text/javascript" language="javascript">
	function confirmDelete(id) {

		var r = confirm("Are You Sure?????");
		if (r == true) {
			return true;
		} else {
			return false;
		}
	}
</script>
<body>

	<div class="titleArea">
		<center>
			<h1>
				<strong>Acheivement Fields Details</strong>
			</h1>
		</center>
	</div>
	<form class="fullForm" method="get" name="addCampaignFields"
		action="addAcheivementFields">


		<input type="submit" class="buttonStyle"
			value="Add Acheivement Fields">
	</form>
	</br>

	<c:if test="${not empty afterAdded}">
		<%-- <c:out value="${afterAdded}"></c:out> --%>
		<h4 style="color: red;">${afterAdded}</h4>
	</c:if>

	<c:if test="${not empty updateValues}">

		<h4 style="color: red;">${updateValues}</h4>
		<%-- <c:out value="${updateValues}"></c:out> --%>




	</c:if>

	<c:if test="${not empty deleteValues}">
		<h4>${deleteValues}</h4>

		<%-- <c:out value="${deleteValues}"></c:out>		 --%>
	</c:if>




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
				<c:when test="${not empty acheivementNotFound}">
					<tr>
						<td colspan="7">
							<h3>
								<font color="red"><c:out value="${acheivementNotFound}"></c:out></font>
							</h3>

						</td>
					</tr>

				</c:when>
				<c:otherwise>

					<c:forEach var="acheivementModel" items="${acheivementModels}"
						varStatus="loop">
						<c:set var="count" value="${count + 1}" scope="page" />
						<tr>
							<td><c:out value="${count}" /></td>
							<td>${acheivementModel.campaignName}</td>
							<td>${acheivementModel.roleName}</td>
							<td>${acheivementModel.fieldName}</td>
							<%-- <td>${acheivementModel.fieldType}</td> --%>
							<td><a
								href="editAcheivement?id=${acheivementModel.acheivementId}"><img
									src="<%=basePath%>resources/images/admin/edit-new.png" /></a>
							<!-- </td>
							<td> -->
								<a
								href="deleteAcheivementValue?acheivementId=${acheivementModel.acheivementId}"
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