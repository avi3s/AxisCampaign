<%@ include file="admin-header.jsp"%>

<script type="text/javascript" language="javascript">
	$(document).ready(function() {
		var subject = $("#subject").val();
		/*alert(receivedUserId)
		 $("#userId_array").val(receivedUserId); */
		/*  		  if(subject != "null"){
		 $("#roleList").hide();
		 $("#userList").hide();
		
		 }  */

		if (document.getElementById('role').checked) {
			$("#roleList").show();
			$("#userList").hide();

		}

		else if (document.getElementById('user').checked) {
			$("#roleList").hide();
			$("#userList").show();
		} else {
			$("#userList").hide();
			$("#roleList").hide();
		}

		$("#role").click(function() {
			var test = $(this).val();
			$("#userList").hide();
			$("#roleList").show();
		});

		$("#user").click(function() {
			var test = $(this).val();
			$("#userList").show();
			$("#roleList").hide();
		});
	});
</script>

<c:choose>
	<c:when test="${reply == 1}">
		<div class="titleArea">
			<center>
				<h1>
					<strong>Reply Notification</strong>
				</h1>
			</center>
		</div>
		<%-- 			<c:if test="${not empty errorMessage}">
				<h4 style="color: red;">${errorMessage}</h4>
			</c:if> --%>
		<h4 style="color: red;">${NotificationSubjectNotNull}</h4>
		<h4 style="color: red;">${NotificationMessageNotNull}</h4>
		<form action="addNotification" id="addNotification" method="post">

			<table align="center" class="formStyles">

				<input type="hidden" name="type" value="${reply}">
				<tr>
					<td colspan="2"><input type="hidden" name="receivedUserId"
						id="receivedUserId"
						value="${fn:escapeXml(replyNotification.receivedUserId)}"></td>
				</tr>

				<tr>
					<td>Subject</td>
					<td><input type="text" id="subject" name="subject"
						value="${fn:escapeXml(replyNotification.subject)}"></td>
				</tr>

				<%-- 				<c:choose>
					<c:when test="${replyNotification.roleId != 0}">

						<tr id="roleList">
							<td>Role List</td>
							<td><select name="roleId" class="select">
									<option value="0">-- Select --</option>
									<c:forEach items="${roleList}" var="role">
										<option value="${role.roleId}"
											${role.roleId == replyNotification.roleId ? 'selected="selected"' : ''}>${role.role_name}</option>
									</c:forEach>
							</select></td>
						</tr>

					</c:when>
					<c:otherwise>

						<tr id="userList">
							<td>UserList</td>
							<td><select name="userId" id="userId" class="select">
									<option value="0">-- Select --</option>
									<c:forEach items="${userlist}" var="user">
										<option value="${user.userId}"
											${replyNotification.roleId == 0 ? user.userId == replyNotification.receivedUserId ? 'selected="selected"' : '' : ''}>${user.employeeName}</option>
									</c:forEach>
							</select></td>
						</tr>

					</c:otherwise>
				</c:choose> --%>


				<tr>
					<td>Message</td>
					<td><textarea id="message" name="message">${replyNotification.message}</textarea></td>
				</tr>

				<tr>
					<td>&nbsp;</td>
					<td><input type="submit" value="Save"><a
						href="viewNotification"><input type="button" value="Cancel"
							id="backbutton" class="buttonStyle2"></a></td>
				</tr>

			</table>
		</form>
	</c:when>



	<c:otherwise>
		<div class="titleArea">
			<center>
				<h1>
					<strong>Add Notification</strong>
				</h1>
			</center>
		</div>
		<c:if test="${not empty errorMessage}">
			<h4 style="color: red;">${errorMessage}</h4>
		</c:if>
		<%-- 			<input type="hidden" name="receivedUserId" value="${notificationModel.receivedUserId}"> --%>
		<h4 style="color: red;">${NotificationRecievedUsedIdNotNull}</h4>
		<h4 style="color: red;">${NotificationSubjectNotNull}</h4>
		<h4 style="color: red;">${NotificationMessageNotNull}</h4>

		<form class="fullform" action="addNotification" id="addNotification"
			method="post">

			<table align="center" class="formStyles">

				<tr>
					<td>&nbsp;</td>

					<td><input type="radio" id="role" name="chooseButton"
						value="ROLE"> Role <span class="leftGap"></span><input
						type="radio" id="user" name="chooseButton" value="USER">
						User</td>
					</span>

				</tr>

				<tr id="userList">
					<td>UserList</td>
					<td><select name="userId_array" id="userId_array"
						class="multipleselect" multiple>
							<option value="0">-- Select --</option>
							<c:forEach items="${userlist}" var="user">
								<option value="${user.userId}">${user.employeeName}</option>
							</c:forEach>
					</select></td>
				</tr>

				<tr id="roleList">
					<td>Role List</td>
					<td><select name="roleId" class="select">
							<option value="0">-- Select --</option>
							<c:forEach items="${roleList}" var="role">
								<option value="${role.roleId}">${role.role_name}</option>
							</c:forEach>
					</select></td>
				</tr>

				<tr>
					<td>Subject</td>
					<td><input type="text" id="subject" name="subject"
						value="${fn:escapeXml(notificationModel.subject)}"></td>
				</tr>

				<tr>
					<td>Message</td>
					<td><textarea id="message" name="message">${notificationModel.message}</textarea></td>
				</tr>

				<tr>
					<td>&nbsp;</td>
					<td><input type="submit" value="Save"><a
						href="viewNotification"><input type="button" value="Cancel"
							id="backbutton" class="buttonStyle2"></a></td>
				</tr>

			</table>

		</form>

	</c:otherwise>

</c:choose>

<%@ include file="admin-footer.jsp"%>