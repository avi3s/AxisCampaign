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
			<strong>View FAQ</strong>
		</h1>
	</center>
</div>

<form action="addFaq" method="get">
	<input type="submit" value="Add Faq" class="buttonStyle">
</form>
<c:if test="${not empty faqMessage}">
	<tr>
		<td colspan="7">
			<h3>
				<font color="red"><c:out value="${faqMessage}"></c:out></font>
			</h3>

		</td>
	</tr>

</c:if>
</br>



<table id="example" class="genContentStyle">
	<thead>
		<tr>
			<th>Serial</th>
			<th>Question</th>
			<th>Answer</th>
			<th>Campaign Name</th>
			<th align="center" class="noarrow">
				<!-- Edit</th>
			<th align="center" class="noarrow">Delete -->
			</th>
		</tr>
	</thead>
	<tbody>
		<c:choose>
			<c:when test="${not empty faqNotFound}">
				<tr>
					<td colspan="7">
						<h3>
							<font color="red"><c:out value="${faqNotFound}"></c:out></font>
						</h3>

					</td>
				</tr>

			</c:when>
			<c:otherwise>
				<c:set var="count" value="0" scope="page" />
				<c:forEach items="${allFaqsView}" var="f">
					<c:set var="count" value="${count + 1}" scope="page" />
					<c:if test="${f.status == 'ACTIVE'}">
						<tr>

							<td align="center"><c:out value="${count}" /></td>
							<td align="center"><c:out value="${f.question}" /></td>
							<td align="center"><c:out value="${f.answer}" /></td>
							<td align="center"><c:out value="${f.campaignName}" /></td>
							<td align="center"><a
								href="editContentById?faqId=${f.faqId}"><img
									src="<%=basePath%>resources/images/admin/edit-new.png" /></a>
							<!-- </td>
							<td align="center"> -->
								<a href="inactiveContentById?faqId=${f.faqId}"><img
									src="<%=basePath%>resources/images/admin/delete-new.png" /></a></td>
						</tr>
					</c:if>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</tbody>
</table>

<%@ include file="admin-footer.jsp"%>