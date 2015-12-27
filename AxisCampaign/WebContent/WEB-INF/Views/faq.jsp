<%@ include file="user-header.jsp"%>

<section class="db-content">
	<div class="heading">
		<div class="container">
			<c:if test="${not empty CampaignName}">
				<h1>
					<c:out value="${CampaignName}" />
					- FAQ's
				</h1>
			</c:if>

			<h1>FAQ's</h1>
		</div>
	</div>
	<div class="breadcramb">
		<div class="container">
			<c:if test="${not empty CampaignName}">
				<div>
					<a href="getDashboard"><icon class="home-icon"></icon></a>
					<icon class="brd-arrow"></icon>
					<c:out value="${CampaignName}" />
					<icon class="brd-arrow"></icon>
					FAQ's
				</div>
			</c:if>

			<%-- <div>
				<icon class="home-icon"></icon>
				<icon class="brd-arrow"></icon>
				<c:out value="${CampaignName}" />
				<icon class="brd-arrow"></icon>
				FAQ's
			</div> --%>
		</div>
	</div>
	<div class="content-sec">
		<div class="container">
			<div class="f-left">
				<ul
					class="responsive-accordion responsive-accordion-default bm-larger">

					<c:forEach items="${faqList}" var="faqList">
						<c:set var="count" value="${count + 1}" scope="page" />
						<li>
							<div class="responsive-accordion-head">
								<c:out value="${count}" />
								.&nbsp;
								<c:out value="${faqList.question}" />
								<i class="fa fa-chevron-down responsive-accordion-plus fa-fw"></i><i
									class="fa fa-chevron-up responsive-accordion-minus fa-fw"></i>
							</div>
							<div class="responsive-accordion-panel">
								<p>
									<c:out value="${faqList.answer}" />
									.
								</p>

							</div>
						</li>
					</c:forEach>

				</ul>
			</div>
			<aside class="f-right">
				<c:if test="${not empty commentStatusFailure}">
					<h4 class="error" style="color: red;">
						<c:out value="${commentStatusFailure}"></c:out>
					</h4>
				</c:if>

				<c:if test="${not empty commentStatusSuccess}">
					<h4 class="error" style="color: green;">
						<c:out value="${commentStatusSuccess}"></c:out>
					</h4>
				</c:if>

				<form action="insertComment" method="post">
					<input type="hidden" value="${campaignid}" name="campaignid">
					<input type="hidden" value="${CampaignName}" name="message">
					<textarea placeholder="Comments" name="subject"></textarea>
					<button>Submit</button>
				</form>
			</aside>
			<div class="clear"></div>
		</div>
	</div>
</section>


<%@ include file="user-footer.jsp"%>