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
			<strong>Campaign Details</strong>
		</h1>
	</center>
</div>
<form method="get" name="frm" action="addCampaign">
	<input type="submit" value="Add Campaign" class="buttonStyle">
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
			<th align="center">Name</th>
			<th align="center">Description</th>
			<th align="center">Financial Year</th>
			<th align="center">Quarter</th>
			<th align="center" class="noarrow">
				<!-- Edit</th>
			<th align="center" class="noarrow">Delete -->
			</th>
		</tr>
	</thead>
	<tbody>
		<c:choose>
			<c:when test="${not empty campaignNotFound}">
				<tr>
					<td colspan="7">
						<h3>
							<font color="red"><c:out value="${campaignNotFound}"></c:out></font>
						</h3>

					</td>
				</tr>

			</c:when>
			<c:otherwise>
				<c:set var="count" value="0" scope="page" />
				<c:forEach items="${campaignList}" var="campaignList">
					<c:set var="count" value="${count + 1}" scope="page" />
					<tr>
						<td align="center"><c:out value="${count}" /></td>
						<td align="center">${campaignList.campaignName}</td>
						<td align="center">${campaignList.campaignDescription}</td>
						<td align="center">${campaignList.financialYear}</td>

						<c:if test="${campaignList.quarterId == '1'}">
							<td align="center">Q1</td>
						</c:if>

						<c:if test="${campaignList.quarterId == '2'}">
							<td align="center">Q2</td>
						</c:if>

						<c:if test="${campaignList.quarterId == '3'}">
							<td align="center">Q3</td>
						</c:if>

						<c:if test="${campaignList.quarterId == '4'}">
							<td align="center">Q4</td>
						</c:if>

						<td align="center"><a
							href="editCampaign?id=${campaignList.campaignId}"><img
								src="<%=basePath%>resources/images/admin/edit-new.png" /></a> <%-- <a href="editCampaign?id=${campaignList.campaignId}">Edit</a></td> --%>
							<!-- <td align="center"> -->
							<a href="deleteCampaign?id=${campaignList.campaignId}"
							onclick="return myFunction()"><img
								src="<%=basePath%>resources/images/admin/delete-new.png" /></a> <%-- <a href="deleteCampaign?id=${campaignList.campaignId}"
						onclick="return myFunction()">Delete</a></td> --%>
					</tr>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</tbody>
</table>

<%@ include file="admin-footer.jsp"%>