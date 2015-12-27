<%@ include file="admin-header.jsp"%>
<body>

	<div class="titleArea">
		<center>
			<h1>
				<strong><c:if test="${not empty UpdateInfo}">
						<c:out value="${UpdateInfo}"></c:out>
					</c:if></strong>
			</h1>
		</center>
	</div>

	<div id="example_wrapper" class="dashboard">
		<ul>
			<li class="campMgt"><a href="viewCampaign"
				title="Campaign Management"> <span><i
						class="fa fa-bullhorn"></i> Campaign Management</span>
			</a></li>
			<li class="userMgt"><a href="viewUser" title="User Management">
					<span><i class="fa fa-user"></i> User Management</span>
			</a></li>
			<li class="notMgt"><a href="viewNotification"
				title="Notification Management"> <span><i
						class="fa fa-bell-o"></i> Notification Management</span>
			</a></li>
			<li class="emMgt"><a href="viewEscalationMatrix"
				title="Escalation Matrix Management"> <span><i
						class="fa fa-puzzle-piece"></i> Escalation Matrix Management</span>
			</a></li>
			<li class="faqMgt"><a href="viewFaq" title="FAQ Management">
					<span><i class="fa fa-question-circle"></i> FAQ Management</span>
			</a></li>
			<li class="contMgt"><a href="viewContent"
				title="Content Management"> <span><i
						class="fa fa-file-text-o"></i> Content Management</span>
			</a></li>
		</ul>
	</div>


	<%@ include file="admin-footer.jsp"%>