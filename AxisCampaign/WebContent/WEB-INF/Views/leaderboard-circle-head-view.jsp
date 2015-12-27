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
				<div class="table-sec leader-board">
					<table cellpadding="0" cellspacing="0" width="100%"
						class="table display compact nowrap example">
						<c:if test="${not empty DataNotFound}">

							<h4 style="color: red;">${DataNotFound}</h4>

						</c:if>
						<thead>
							<c:if test="${not empty headerList}">
								<c:forEach var="entry" items="${headerList}">

									<th align="left" style="background-color: #130f10"><c:out
											value="${entry}" /></th>

								</c:forEach>

							</c:if>

						</thead>
						<tbody>

							<c:if test="${not empty bodycontents}">
								<c:set var="length" value="${bodycontents.get(0).size()}"></c:set>
								<c:forEach varStatus="current" begin="1" end="${length}">
									<c:set var="index" value="${current.count-1}"></c:set>
									<tr>
										<c:forEach var="columns" items="${bodycontents}">
											<td><c:out value="${columns.get(index)}" /></td>
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
</section>


<%@ include file="user-footer.jsp"%>