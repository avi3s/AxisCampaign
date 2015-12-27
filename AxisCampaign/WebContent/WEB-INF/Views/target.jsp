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
						<thead>
							<tr>
								<th align="left" style="background-color: #130f10">Circle
									Name</th>
								<th align="left" style="background-color: #221c1e">Region</th>
								<th align="left" style="background-color: #2f292b">Group</th>
								<th align="left" style="background-color: #130f10">Ranking</th>
								<th align="left" style="background-color: #221c1e">No. of
									Branches to qualify</th>
								<th align="left" style="background-color: #2f292b">% Ach</th>
								<th align="left" style="background-color: #130f10">0-20%</th>
								<th align="left" style="background-color: #221c1e">21-40%</th>
								<th align="left" style="background-color: #31282b">41-60%</th>
								<th align="left"
									style="background-color: #130f10; border-right-color: #494546">61-80%</th>
								<th align="left"
									style="background-color: #221c1e; border-right-color: #494546">81-99%</th>
								<th align="left" style="background-color: #2f292b">100%+</th>
								<th align="left" style="background-color: #cf0d66">Total
									Branches</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td data-title="Circle Name">Chennai</td>
								<td data-title="Region">South</td>
								<td data-title="Group">Group A</td>
								<td data-title="Ranking">1</td>
								<td data-title="No. of Branches to qualify">50</td>
								<td data-title="% Ach">172%</td>
								<td data-title="0-20%" class="achvmt">21</td>
								<td data-title="21-40%">24</td>
								<td data-title="41-60%">27</td>
								<td data-title="61-80%">30</td>
								<td data-title="81-99%">33</td>
								<td data-title="100%+">36</td>
								<td data-title="Total Branches">171</td>
							</tr>
							<tr>
								<td data-title="Circle Name">Delhi</td>
								<td data-title="Region">South</td>
								<td data-title="Group">Group A</td>
								<td data-title="Ranking">2</td>
								<td data-title="No. of Branches to qualify">50</td>
								<td data-title="% Ach">172%</td>
								<td data-title="0-20%" class="achvmt">21</td>
								<td data-title="21-40%">24</td>
								<td data-title="41-60%">27</td>
								<td data-title="61-80%">30</td>
								<td data-title="81-99%">33</td>
								<td data-title="100%+">36</td>
								<td data-title="Total Branches">171</td>
							</tr>
							<tr>
								<td data-title="Circle Name">Chennai</td>
								<td data-title="Region">South</td>
								<td data-title="Group">Group A</td>
								<td data-title="Ranking">3</td>
								<td data-title="No. of Branches to qualify">50</td>
								<td data-title="% Ach">172%</td>
								<td data-title="0-20%" class="achvmt">21</td>
								<td data-title="21-40%">24</td>
								<td data-title="41-60%">27</td>
								<td data-title="61-80%">30</td>
								<td data-title="81-99%">33</td>
								<td data-title="100%+">36</td>
								<td data-title="Total Branches">171</td>
							</tr>
							<tr>
								<td data-title="Circle Name">Chennai</td>
								<td data-title="Region">South</td>
								<td data-title="Group">Group A</td>
								<td data-title="Ranking">4</td>
								<td data-title="No. of Branches to qualify">50</td>
								<td data-title="% Ach">172%</td>
								<td data-title="0-20%" class="achvmt">21</td>
								<td data-title="21-40%">24</td>
								<td data-title="41-60%">27</td>
								<td data-title="61-80%">30</td>
								<td data-title="81-99%">33</td>
								<td data-title="100%+">36</td>
								<td data-title="Total Branches">171</td>
							</tr>
							<tr>
								<td data-title="Circle Name">Chennai</td>
								<td data-title="Region">South</td>
								<td data-title="Group">Group A</td>
								<td data-title="Ranking">5</td>
								<td data-title="No. of Branches to qualify">50</td>
								<td data-title="% Ach">172%</td>
								<td data-title="0-20%" class="achvmt">21</td>
								<td data-title="21-40%">24</td>
								<td data-title="41-60%">27</td>
								<td data-title="61-80%">30</td>
								<td data-title="81-99%">33</td>
								<td data-title="100%+">36</td>
								<td data-title="Total Branches">171</td>
							</tr>
							<tr>
								<td data-title="Circle Name">Chennai</td>
								<td data-title="Region">South</td>
								<td data-title="Group">Group A</td>
								<td data-title="Ranking">6</td>
								<td data-title="No. of Branches to qualify">50</td>
								<td data-title="% Ach">172%</td>
								<td data-title="0-20%" class="achvmt">21</td>
								<td data-title="21-40%">24</td>
								<td data-title="41-60%">27</td>
								<td data-title="61-80%">30</td>
								<td data-title="81-99%">33</td>
								<td data-title="100%+">36</td>
								<td data-title="Total Branches">171</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</section>


<%@ include file="user-footer.jsp"%>