<%@ include file="user-header.jsp"%>

<section class="db-content">
	<div class="heading">
		<div class="container">
			<h1>Notification</h1>
		</div>
	</div>
	<div class="breadcramb">
		<div class="container">
			<div>
				<a href="getDashboard"><icon class="home-icon"></icon></a>
				<icon class="brd-arrow"></icon>
				Notification
			</div>
		</div>
	</div>
	<div class="content-sec">
		<div class="container">
			<ul class="notification-list">
				<c:choose>

					<c:when test="${empty viewAllNotificationsList}">
					</c:when>

					<c:otherwise>
						<c:forEach items="${viewAllNotificationsList}"
							var="notificationModel">
							<li><a
								href="viewSingleNotification?id=<c:out value="${notificationModel.id}" />"><c:out
										value="${notificationModel.message}" /></a> <span><fmt:formatDate
										type="both" value="${notificationModel.createTimeStamp}" /></span></li>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
	</div>
</section>


<%@ include file="user-footer.jsp"%>