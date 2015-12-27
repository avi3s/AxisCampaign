<%@ include file="user-header.jsp"%>

<section class="db-content">
	<div class="heading">
		<div class="container">
			<h1>
				<!-- Campaign 1 - -->
				Escalation Matrix
			</h1>
		</div>
	</div>
	<div class="breadcramb">
		<div class="container">
			<div>
				<a href="getDashboard"><icon class="home-icon"></icon></a>
				<!-- <icon class="brd-arrow"></icon>
				Campaign 1 -->
				<icon class="brd-arrow"></icon>
				Escalation Matrix
			</div>
		</div>
	</div>
	<div class="content-sec">
		<div class="container">
			<h3>Branch Escalation regarding MIS Reports:</h3>
			<div class="table-sec">
				<div class="first-level">
					<h4>
						<img src="<%=basePath%>resources/images/user/pink-arrow.jpg"
							alt="arrow" />First level ABR through Talisma
					</h4>
					<table cellpadding="0" cellspacing="0"
						class="table display compact nowrap example">
						<thead>
							<tr>
								<th align="left" bgcolor="#130f10">Name</th>
								<th align="left" bgcolor="#221c1e">Email ID</th>
								<th align="left" bgcolor="#2e2c2d">Contact Number</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${escalationMatrixModels}"
								var="EscalationMatrixList" varStatus="talisma">
								<c:if test="${EscalationMatrixList.type == 'Talisma'}">
									<tr>
										<td>${EscalationMatrixList.name}</td>
										<td>${EscalationMatrixList.email}</td>
										<td>${EscalationMatrixList.contactNumber}</td>
									</tr>
								</c:if>
							</c:forEach>
							<!-- <tr>
								<td>Vipul Patki</td>
								<td>vipul.patki@axisbank.com</td>
								<td>71316713</td>
							</tr>
							<tr>
								<td>Mahesh Koria</td>
								<td>mahesh.koria@axisbank.com</td>
								<td>71316718</td>
							</tr>
 -->
						</tbody>
					</table>
				</div>
			</div>

			<div class="table-sec">
				<div class="second-level">
					<h4>
						<img src="<%=basePath%>resources/images/user/pink-arrow.jpg"
							alt="arrow" />Second level through Contest Team
					</h4>
					<table cellpadding="0" cellspacing="0"
						class="table display compact nowrap example">
						<thead>
							<tr>
								<th align="left" bgcolor="#d20e64"
									style="border-right-color: #e85c99">Name</th>
								<th align="left" bgcolor="#e11971"
									style="border-right-color: #e85c99">Email ID</th>
								<th align="left" bgcolor="#ef247d">Contact Number</th>
							</tr>
						</thead>
						<tbody>

							<c:forEach items="${escalationMatrixModels}"
								var="EscalationMatrixList" varStatus="Contestteam">
								<c:if test="${EscalationMatrixList.type == 'ContestTeam'}">
									<tr>
										<td>${EscalationMatrixList.name}</td>
										<td>${EscalationMatrixList.email}</td>
										<td>${EscalationMatrixList.contactNumber}</td>
									</tr>
								</c:if>
							</c:forEach>

							<!-- <tr>
								<td>Milind Sawant</td>
								<td>Milind.Sawant@axisbank.com</td>
								<td>24254359</td>
							</tr>
							<tr>
								<td>Poulabi Sidheswaran</td>
								<td>Poulabi.Sidheswaran@axisbank.com</td>
								<td>24254358</td>
							</tr>
							<tr>
								<td>Mahesh Koria</td>
								<td>mahesh.koria@axisbank.com</td>
								<td>24254356</td>
							</tr>
							<tr>
								<td>Mahesh Koria</td>
								<td>mahesh.koria@axisbank.com</td>
								<td>24254355</td>
							</tr> -->

						</tbody>
					</table>
				</div>
			</div>
			<div class="clear"></div>
		</div>
	</div>
</section>


<%@ include file="user-footer.jsp"%>