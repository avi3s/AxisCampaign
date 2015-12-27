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

<form action="addNotificationPage" method="post">
	<input type="submit" value="Add User/Role Wise Notification"
		class="buttonStyle">
</form>
<br>
<br>
<h1>Sent Notification</h1>

<c:if test="${not empty notificationAdded}">
	<tr>
		<td colspan="7">
			<h3>
				<font color="red"><c:out value="${notificationAdded}"></c:out></font>
			</h3>

		</td>
	</tr>

</c:if>



<table id="example" class="genContentStyle">
	<thead>
		<c:choose>
			<c:when test="${not empty notificationNotFound}">
				<tr>
					<td colspan="7">
						<h3>
							<font color="red"><c:out value="${notificationNotFound}"></c:out></font>
						</h3>

					</td>
				</tr>
			</c:when>
			<c:otherwise>
				<tr>

					<th>Subject</th>
					<th>Message</th>
					<th>Recipient</th>
					<!-- <td>Sent Status</td>
		<td>Receive Status</td> -->
					<!-- 				<th>Edit</th> -->
					<th>Delete</th>
				</tr>
	</thead>
	<tbody>
		<c:forEach items="${sentNotificationList}" var="u">
			<%-- 				<c:if test="${u.status.ordinal() == 1}"> --%>
			<tr>

				<td><c:out value="${u.subject}" /></td>
				<td><c:out value="${u.message}" /></td>
				<td><c:out value="${u.receivedUserName}" /></td>
				<%-- <td><c:out value="${u.sentStatus}" /></td>
			<td><c:out value="${u.receiveStatus}" /></td> --%>

				<%-- 			<td><a href="viewNotificationById?id=${u.id}">Update</a></td> --%>
				<td><a href="deleteNotificationById?id=${u.id}"
					onclick="return myFunction()"><img
						src="<%=basePath%>resources/images/admin/delete-new.png" /></a></td>

			</tr>
			<%-- 				</c:if> --%>
		</c:forEach>
		</c:otherwise>
		</c:choose>
	</tbody>
</table>
<%-- <h1>Recieved Notification</h1>


<table class="genContentStyle">
	<thead>
		<tr>
			<th>subject</th>
			<th>message</th>
			<th>Sender</th>
			<!-- <td>Sent Status</td>
		<td>Receive Status</td> -->
			<th></th>
			<th></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${recievedNotificationList}" var="u">
							<c:if test="${u.status.ordinal() == 1}">
			<tr>
				<td><c:out value="${u.subject}" /></td>
				<td><c:out value="${u.message}" /></td>
				<td><c:out value="${u.sentUserName}" /></td>
				<td><c:out value="${u.sentStatus}" /></td>
			<td><c:out value="${u.receiveStatus}" /></td>

				<td><a href="replyToNotificationById?id=${u.id}"><img
						src="<%=basePath%>resources/images/admin/edit-new.png" /></a></td>
				<td><a href="deleteNotificationById?id=${u.id}"><img
						src="<%=basePath%>resources/images/admin/delete-new.png" /></a></td>

			</tr>
		</c:forEach>
	</tbody>
</table> --%>
<%@ include file="admin-footer.jsp"%>