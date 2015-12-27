<%@ include file="admin-header.jsp"%>

<script type="text/javascript" language="javascript">
	$(document).ready(
	//var campid = $("#campid").val();
	//$("#campaignId1").val(campid);
	function() {
		var campid = $("#campid").val();
		$("#campaignId1").val(campid);

		/*  var quarterlyID = $("#quarterlyID1").val();
		$("#quarterId").val(quarterlyID);

		var selected = $("#roleID_array_to_show").val();
		var obj = $('#roleID_array');

		var i = 0, size = selected.length;
		for (i; i < size; i++) {
			$("#roleID_array option[value='" + selected[i] + "']")
					.attr("selected", 1);
		} */

	});
	/* --- for FAQ Details --- */
	function checkForm(saveFaqValue)
	{
		if(saveFaqValue.campaignId1.value=="" || saveFaqValue.campaignId1.value=="Campaign Name")
		{
			alert ('Please Select a Campaign Name!');
			saveFaqValue.campaignId1.focus();
			return false;
		}
		if(saveFaqValue.question.value=="" || saveFaqValue.question.value=="Question")
		{
			alert ('Please Type the Questions!');
			saveFaqValue.question.focus();
			return false;
		}
		if(saveFaqValue.answer.value=="" || saveFaqValue.answer.value=="Answer")
		{
			alert ('Please Type the Answer!');
			saveFaqValue.answer.focus();
			return false;
		}
		return true;
	}
	
	/* --- for Edit FAQ Details --- */
	
	
function checkFormEdit(updateFaqValue1)
	{
		if(updateFaqValue1.campaignId.value=="" || updateFaqValue1.campaignId.value=="Campaign Name")
		{
			alert ('Please Select a Campaign Name!');
			updateFaqValue1.campaignId1.focus();
			return false;
		}
		if(updateFaqValue1.question.value=="" || updateFaqValue1.question.value=="Question")
		{
			alert ('Please Type the Questions!');
			updateFaqValue1.question.focus();
			return false;
		}
		if(updateFaqValue1.answer.value=="" || updateFaqValue1.answer.value=="Answer")
		{
			alert ('Please Type the Answer!');
			updateFaqValue1.answer.focus();
			return false;
		}
		return true;
   }
		
</script>

</head>

<body>

	<h3>${allFaqsView}</h3>


	<c:choose>

		<c:when test="${param['update'] == 1 or update==1}">
			<div class="titleArea">
				<center>
					<h1>
						<strong>Update FAQ Details</strong>
					</h1>
				</center>
			</div>

			<c:if test="${not empty param['errorMessage']}">
				<h4 style="color: red;">${param['errorMessage']}</h4>
			</c:if>

			<form class="fullForm" method="post" name="frm" id="updateFaqValue1"
				action="updateFaqValue"
				onSubmit="return checkFormEdit(updateFaqValue1)">

				<c:if test="${not empty CampaignIdNotNull}">
					<h4 class="error" style="color: red;">
						<c:out value="${CampaignIdNotNull}"></c:out>
					</h4>
				</c:if>
				<c:if test="${not empty QuestionIdNotNull}">
					<h4 class="error" style="color: red;">
						<c:out value="${QuestionIdNotNull}"></c:out>
					</h4>
				</c:if>
				<c:if test="${not empty AnswerIdNotNull}">
					<h4 class="error" style="color: red;">
						<c:out value="${AnswerIdNotNull}"></c:out>
					</h4>
				</c:if>

				<table align="center" class="formStyles">
					<tr>
						<td colspan="2"><input type="hidden" id="campid"
							value="${faqModel.campaignId}"></td>
					<tr>
						<td>Campaign Name</td>
						<td><select name="campaignId" id="campaignId">
								<!-- <option value="0">-- Select --</option> -->
								<c:forEach items="${allactiveCampaign}" var="f">
									<c:choose>
										<c:when test="${f.campaignId==faqmodel.campaignId}">
											<option value="${f.campaignId}" selected>${f.campaignName}</option>
										</c:when>
										<c:otherwise>
											<option value="${f.campaignId}">${f.campaignName}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
						</select></td>
					</tr>
					<%-- 
					<tr>
						<td>Question</td>
						<td><TextArea name="question" id="question">
						${faqmodel.question}
						</TextArea></td>
					</tr>

					<tr>
						<td>Answer</td>

						<td><TextArea name="answer" id="answer">
						${faqmodel.answer} 
						</TextArea></td>
					</tr> --%>

					<tr>
						<td>Question</td>
						<td><TextArea name="question" id="question"><c:out
									value="${faqmodel.question}" /></TextArea></td>
					</tr>

					<tr>
						<td>Answer</td>
						<td><TextArea name="answer" id="answer"><c:out
									value="${faqmodel.answer}" /></TextArea></td>
					</tr>
					<tr>
						<td><input type="hidden" value="${faqmodel.faqId}"
							name="faqId"></td>
						<td><input type="submit" value="Save"><a
							href="viewFaq"><input type="button" value="Cancel"
								id="backbutton" class="buttonStyle2"></a></td>
					</tr>

				</table>

			</form>
		</c:when>
		<c:otherwise>
			<div class="titleArea">
				<center>
					<h1>
						<strong>Create FAQ Details</strong>
					</h1>
				</center>
			</div>
			<c:if test="${not empty param['errorMessage']}">
				<h4 style="color: red;">${param['errorMessage']}</h4>
			</c:if>


			<form class="fullForm" method="post" name="frm" id="saveFaqValue"
				action="saveFaqValue" onSubmit="return checkForm(saveFaqValue)">

				<c:if test="${not empty CampaignIdNotNull}">
					<h4 class="error" style="color: red;">
						<c:out value="${CampaignIdNotNull}"></c:out>
					</h4>
				</c:if>
				<c:if test="${not empty QuestionIdNotNull}">
					<h4 class="error" style="color: red;">
						<c:out value="${QuestionIdNotNull}"></c:out>
					</h4>
				</c:if>
				<c:if test="${not empty AnswerIdNotNull}">
					<h4 class="error" style="color: red;">
						<c:out value="${AnswerIdNotNull}"></c:out>
					</h4>
				</c:if>

				<c:choose>
					<c:when test="${not empty NoActiveRoleFound}">
						<h3>
							<font color="red"><c:out value="${NoActiveRoleFound}"></c:out></font>
						</h3>
					</c:when>
					<c:otherwise>


						<table align="center" class="formStyles">

							<tr>
								<td colspan="2"><input type="hidden" id="campid"
									value="${faqModel.campaignId}"></td>
							<tr>
								<td>Campaign Name</td>
								<td><select name="campaignId" id="campaignId1">
										<!-- <option value="0">-- Select --</option> -->
										<c:forEach items="${allactiveCampaign}" var="f">
											<option value="${f.campaignId}">${f.campaignName}</option>
										</c:forEach>
								</select></td>
							</tr>

							<tr>
								<td>Question</td>
								<td><TextArea name="question"><c:out
											value="${faqModel.question}" /></TextArea></td>
							</tr>

							<tr>
								<td>Answer</td>
								<td><TextArea name="answer"><c:out
											value="${faqModel.answer}" /></TextArea></td>
							</tr>
							<tr>
								<td>&nbsp;</td>
								<td><input type="submit" value="Save"><a
									href="viewFaq"><input type="button" value="Cancel"
										id="backbutton" class="buttonStyle2"></a></td>
							</tr>

						</table>
					</c:otherwise>
				</c:choose>
			</form>
		</c:otherwise>
	</c:choose>

	<%@ include file="admin-footer.jsp"%>