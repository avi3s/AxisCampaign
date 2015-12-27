<%@ include file="user-header.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

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
						<li><a href="#tab-1">Route 1</a></li>
						<li><a href="#tab-2">Route 2</a></li>
					</ul>

					<div id="tab-1">
						<div class="table-sec leader-board">
						
						<c:if test="${ empty headerList}">
						    <h4 style="color: red;">No Achievement Found for Route 1</h4>
						
						</c:if>
						
						<c:if test="${not empty headerList}">
							<table cellpadding="0" cellspacing="0" width="100%"
								class="table display compact nowrap example">

								
									<thead>

										<c:forEach var="entry" items="${headerList}">

											<th align="left" style="background-color: #130f10"><c:out
													value="${entry}" /></th>

										</c:forEach>




									</thead>
									<c:if test="${fn:length(bodycontents) > 0}">
										<tbody>

											<c:set var="length" value="${bodycontents.get(0).size()}"></c:set>
											<c:forEach varStatus="current" begin="1" end="${length}">
												<c:set var="index" value="${current.count-1}"></c:set>
												<tr>
													<c:forEach var="columns" items="${bodycontents}">
														<td><c:out value="${columns.get(index)}" /></td>
													</c:forEach>
												</tr>
											</c:forEach>

										</tbody>
									</c:if>
								


								
							</table>
							</c:if>
							<c:if test="${not empty DataNotFound}">
							<table cellpadding="0" cellspacing="0" width="100%"
								class="table display compact nowrap example">
									<tbody>
										<tr>
											<td>
												<h4 style="color: red;">${DataNotFound}</h4>
											</td>
										</tr>
									</tbody>
							</table>

							</c:if>
							
						</div>
					</div>
					<div id="tab-2">
						<div class="table-sec leader-board">
						<c:if test="${ empty headerList2}">
						    <h4 style="color: red;">No Achievement Found for Route 2</h4>
						
						</c:if>
							<c:if test="${not empty headerList2}">
							<table cellpadding="0" cellspacing="0" width="100%"
								class="table display compact nowrap example">

								
									<thead>

										<c:forEach var="entry2" items="${headerList2}">

											<th align="left" style="background-color: #130f10"><c:out
													value="${entry2}" /></th>

										</c:forEach>




									</thead>
									<c:if test="${fn:length(bodycontents2) > 0}">
										<tbody>

											<c:if test="${not empty bodycontents2}">
												<c:set var="length2" value="${bodycontents2.get(0).size()}"></c:set>
												<c:forEach varStatus="current2" begin="1" end="${length2}">
													<c:set var="index2" value="${current2.count-1}"></c:set>
													<tr>
														<c:forEach var="columns2" items="${bodycontents2}">
															<td><c:out value="${columns2.get(index2)}" /></td>
														</c:forEach>
													</tr>
												</c:forEach>
											</c:if>

										</tbody>
									</c:if>
								


								
							</table>

							</c:if>

							<c:if test="${not empty DataNotFound}">
							<table cellpadding="0" cellspacing="0" width="100%"
								class="table display compact nowrap example">
									<tbody>
										<tr>
											<td>
												<h4 style="color: red;">${DataNotFound}</h4>
											</td>
										</tr>
									</tbody>
							</table>

							</c:if>












							<%-- <table cellpadding="0" cellspacing="0" width="100%"
								class="table display compact nowrap example">
								<thead>
									<c:forEach var="entry2" items="${headerList2}">

										<th align="left" style="background-color: #130f10"><c:out
												value="${entry2}" /></th>

									</c:forEach>
								</thead>
								<tbody>
									<c:if test="${not empty bodycontents}">
										<c:set var="length2" value="${bodycontents2.get(0).size()}"></c:set>
										<c:forEach varStatus="current2" begin="1" end="${length2}">
											<c:set var="index2" value="${current2.count-1}"></c:set>
											<tr>
												<c:forEach var="columns2" items="${bodycontents2}">
													<td><c:out value="${columns2.get(index2)}" /></td>
												</c:forEach>
											</tr>
										</c:forEach>
									</c:if>
								</tbody>
							</table> --%>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>

<%@ include file="user-footer.jsp"%>