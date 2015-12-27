<%@ include file="user-header.jsp"%>

<section class="db-content">
	<div class="heading">
		<div class="container">
		<c:if test="${not empty CampaignName}">
				<h1>
					
					- Branch achievement bucket in <c:out value="${CampaignName}" /> &nbsp;<c:out value="${CampaignQuater}" /><!-- Q2 -->
				</h1>
			</c:if>
			<!-- <h1>Campaign 1 - Leaderboard</h1> -->
		</div>
	</div>
	<div class="breadcramb">
		<div class="container">
			<div>
				<a href="getDashboard"><icon class="home-icon"></icon></a>
				<icon class="brd-arrow"></icon>
				<c:out value="${CampaignName}" />
				<icon class="brd-arrow"></icon>
				Leaderboard
			</div>
		</div>
	</div>
	<div class="content-sec">
		<div class="container">
			<div class="table-block">
				<div id="horizontalTab">
					<ul>
						<li><a href="#tab-1">My Position</a></li>
						<li><a href="#tab-2">Top 20</a></li>
					</ul>

					<div id="tab-1">
						<div class="table-sec leader-board">
							<table cellpadding="0" cellspacing="0" width="100%"
								class="table display compact nowrap example">
								<c:if test="${not empty DataNotFound}">

									<h4 style="color: red;">${DataNotFound}</h4>

								</c:if>
								<thead>
									<c:if test="${not empty LeaderHeader}">
										<c:forEach var="entry" items="${LeaderHeader}">

											<th align="left" style="background-color: #130f10"><c:out
													value="${entry.header}" /></th>

										</c:forEach>
									</c:if>
								</thead>
								<tbody>
									<c:if test="${not empty bodycontainsdetails}">
										<c:forEach var="columns" items="${bodycontainsdetails}">
											<tr>
												<c:forEach var="mapvalue" items="${columns.value}">
													<td><c:out value="${mapvalue}" /></td>
												</c:forEach>
											</tr>
										</c:forEach>
									</c:if>
								</tbody>

							</table>
						</div>
					</div>
					<div id="tab-2">
						<div class="table-sec leader-board">
							<table cellpadding="0" cellspacing="0" width="100%"
								class="table display compact nowrap example">
								<thead>
									<c:if test="${not empty LeaderHeaderTop20}">
										<c:forEach var="entry" items="${LeaderHeaderTop20}">

											<th align="left" style="background-color: #130f10"><c:out
													value="${entry.header}" /></th>

										</c:forEach>
									</c:if>
								</thead>
								<tbody>
									<c:if test="${not empty bodycontainsdetailsTop20}">
										<c:forEach var="columns" items="${bodycontainsdetailsTop20}">
											<tr>
												<c:forEach var="mapvalue" items="${columns.value}">

													<td><c:out value="${mapvalue}" /></td>

												</c:forEach>

											</tr>
										</c:forEach>
									</c:if>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>


<%@ include file="user-footer.jsp"%>