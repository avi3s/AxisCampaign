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
				<icon class="home-icon"></icon>
				<icon class="brd-arrow"></icon>
				Notification
			</div>
		</div>
	</div>
	<div class="content-sec">
		<div class="container">
			<ul class="file-list-type clearfix">

				<c:if test="${not empty campaignFileDetails}">
					<h4 style="color: red;">
						<c:out value="${campaignFileDetails}"></c:out>
					</h4>
				</c:if>
				<c:choose>

					<c:when test="${empty allCampaignFiles}">
					</c:when>

					<c:otherwise>
						<c:forEach items="${allCampaignFiles}" var="campaignFile">


							<li><c:choose>

									<c:when test="${campaignFile.extension=='pdf'}">
										<span class="file-listing-icon"><img
											src="<%=basePath%>resources/images/user/pdf.png" /></span>
									</c:when>

									<c:when test="${campaignFile.extension=='ppt'}">
										<span class="file-listing-icon"><img
											src="<%=basePath%>resources/images/user/power-point.png" /></span>
									</c:when>

									<c:when test="${campaignFile.extension=='pptx'}">
										<span class="file-listing-icon"><img
											src="<%=basePath%>resources/images/user/pdf.png" /></span>
									</c:when>



									<c:when test="${campaignFile.extension=='docs'}">
										<span class="file-listing-icon"><img
											src="<%=basePath%>resources/images/user/word.png" /></span>
									</c:when>


									<c:when test="${campaignFile.extension=='xls'}">
										<span class="file-listing-icon"><img
											src="<%=basePath%>resources/images/user/pdf.png" /></span>
									</c:when>

									<c:when test="${campaignFile.extension=='xlsx'}">
										<span class="file-listing-icon"><img
											src="<%=basePath%>resources/images/user/pdf.png" /></span>
									</c:when>

									<c:when test="${campaignFile.extension=='doc'}">
										<span class="file-listing-icon"><img
											src="<%=basePath%>resources/images/user/word.png" /></span>
									</c:when>

									<c:otherwise>
										<span class="file-listing-icon"><img
											src="<%=basePath%>resources/images/user/images.png" /></span>
									</c:otherwise>
								</c:choose>



								<h3>
									<c:out value="${campaignFile.fileName}" />
									<!-- <span>2.5kb</span> -->
								</h3> <!-- <p>Uploaded by User Name</p>  --> <span
								class="upload-time"> <fmt:formatDate type="both"
										value="${campaignFile.createTimeStamp}" /><br> <c:out
										value="${campaignFile.fileSize}" />&nbsp;

							</span> <!-- <a href="#">view</a> -->
								<c:if test="${campaignFile.fileType=='CFM'}">
									<a
										href="downloadCampaignFileForUser?campaign_file_id=${campaignFile.campaignFileId}&type=CFM">Download</a>
								</c:if> <c:if test="${campaignFile.fileType=='CFUM'}">
									<a
										href="downloadCampaignFileForUser?campaign_file_id=${campaignFile.campaignFileUserId}&type=CFUM">Download</a>
								</c:if>
								<c:if test="${campaignFile.fileType=='UFUM'}">
                                    <a
                                        href="downloadCampaignFileForUser?campaign_file_id=${campaignFile.userFileUploadId}&type=UFUM">Download</a>
                                </c:if></li>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
	</div>
</section>


<%@ include file="user-footer.jsp"%>