<%@ include file="admin-header.jsp"%>
<div class="titleArea">
	<center>
		<h1>
			<strong>View Notification Details</strong>
		</h1>
	</center>
</div>
<div class="headingArea">
	<form action="addNotificationPage" method="post">
		<input type="submit" value="Add Notification" class="buttonStyle">
	</form>
</div>

<div class="tabArea">
	<ul class="tabs">
		<li class="activeState"><a href="#tab1">Sent Notification</a></li>
		<li><a href="#tab2">Recieved Notification</a></li>
		<!--                     <li><a href="#tab3">Your text</a></li> -->
	</ul>
	<div class="clear"></div>
	<div class="tabWrap">
		<div class="tabContent" id="tab1" style="display: block;">
			<div class="tableWrap">
				<table class="genContentStyle" id="example">
					<thead>
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
						<c:choose>
							<c:when test="${not empty sendNotificationNotFound}">
								<tr>
									<td colspan="4">
										<h3>
											<font color="red"><c:out
													value="${sendNotificationNotFound}"></c:out></font>
										</h3>

									</td>
								</tr>
							</c:when>
							<c:otherwise>
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
					</tbody>
					<%-- 				</c:if> --%>
					</c:forEach>
					</c:otherwise>
					</c:choose>
				</table>
			</div>
		</div>
		<div class="tabContent" id="tab2" style="display: block;">
			<div class="tableWrap">
				<table class="genContentStyle" id="example1">
					<thead>
						<tr>
							<th>Subject</th>
							<th>Message</th>
							<th>Sender</th>
							<!-- <td>Sent Status</td>
		<td>Receive Status</td> -->
							<th>Reply</th>
							<th>Delete</th>
						</tr>
					</thead>
					<tbody>
						<c:choose>
							<c:when test="${not empty receiveNotificationNotFound}">
								<tr>
									<td colspan="5">
										<h3>
											<font color="red"><c:out
													value="${receiveNotificationNotFound}"></c:out></font>
										</h3>

									</td>
								</tr>
							</c:when>
							<c:otherwise>
								<c:forEach items="${recievedNotificationList}" var="u">
									<%-- 				<c:if test="${u.status.ordinal() == 1}"> --%>
									<tr>
										<td><c:out value="${u.subject}" /></td>
										<td><c:out value="${u.message}" /></td>
										<td><c:out value="${u.sentUserName}" /></td>
										<%-- <td><c:out value="${u.sentStatus}" /></td>
			<td><c:out value="${u.receiveStatus}" /></td> --%>

										<td><a href="replyToNotificationById?id=${u.id}">Reply</a></td>
										<td><a href="deleteNotificationById?id=${u.id}">Delete</a></td>

									</tr>
					</tbody>
					<%-- 				</c:if> --%>
					</c:forEach>
					</c:otherwise>
					</c:choose>
				</table>
			</div>
		</div>
	</div>

</div>



<%@ include file="admin-footer.jsp"%>