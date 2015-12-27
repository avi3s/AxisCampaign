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

					<c:when test="${empty singleNotification}">
					</c:when>

					<c:otherwise>
						<li><c:out value="${singleNotification.subject}" /><span><c:out
									value="${singleNotification.message}" /></span><span><c:out
									value="${singleNotification.sentUserName}" /></span><span><fmt:formatDate
									type="both" value="${singleNotification.createTimeStamp}" /></span></li>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
	</div>
</section>
<%@ include file="user-footer.jsp"%>