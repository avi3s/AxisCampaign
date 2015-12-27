<%@ include file="admin-header.jsp"%>

<script type="text/javascript" language="javascript">
	$(document).ready(function() {
		$("#userTr").hide();
		var campaign = $("#campaign_id").val();
		//alert(campaign);
		var user = $("#user_id").val();
		//alert(user);
		$("#userIdDDL").val(user);

		if (campaign == null) {
			//alert("Please Insert A Campaign");
		} else {
			$("#campaignIdDDL").val(campaign);
		}
		$("#campaignIdDDL").change(function(data, status) {
			/* alert("okk :: "
					+ $("#campaignIdDDL").val()); */

			$.get("./ajaxCallForFetchingUserByCampaignId", {
				campaignId : $("#campaignIdDDL").val()
			}, function(data, status) {
				//alert(data);
				$("#userTr").show();
				$("#userIdDDL").empty();
				/* $("#userIdDDL")
						.append(
								"<option nvalue=0>---Select---</option>");
				 */$("#userIdDDL").append(data);

			});

			//alert("end");
		});
	});
	
	/* --- for Promotional File --- */
	function checkForm(promotionalFile)
	{
		if(promotionalFile.campaignIdDDL.value=="" || promotionalFile.campaignIdDDL.value=="Campaign Name")
		{
			alert ('Please Select the Campaign Name!');
			promotionalFile.campaignIdDDL.focus();
			return false;
		}
		if(promotionalFile.userIdDDL.value=="" || promotionalFile.userIdDDL.value=="User Name")
		{
			alert ('Please Select the User Name!');
			promotionalFile.userIdDDL.focus();
			return false;
		}
		return true;
	}
</script>
<body>

	<div class="titleArea">
		<center>
			<h1>
				<strong>Upload Promotional File</strong>
			</h1>
		</center>
	</div>

	<c:if test="${not empty Role_not_null}">
		<h4 class="error" style="color: red;">
			<c:out value="${Role_not_null}"></c:out>
		</h4>
	</c:if>
	<c:if test="${not empty User_not_null}">
		<h4 class="error" style="color: red;">
			<c:out value="${User_not_null}"></c:out>
		</h4>
	</c:if>
	<c:if test="${not empty File_name_not_null}">
		<h4 class="error" style="color: red;">
			<c:out value="${File_name_not_null}"></c:out>
		</h4>
	</c:if>

	<%-- <c:if test="${not empty param['errorMessage']}">
		<h4 style="color: red;">${param['errorMessage']}</h4>
	</c:if> --%>

	<c:choose>
		<c:when test="${not empty campaignNotFound}">
			<tr>
				<td colspan="7">
					<h3>
						<font color="red"><c:out value="${campaignNotFound}"></c:out></font>
					</h3>

				</td>
			</tr>

		</c:when>
		<c:otherwise>


			<form class="fullForm" method="post" name="frm"
				action="savepromotionalfile" id="promotionalFile"
				enctype="multipart/form-data"
				onSubmit="return checkForm(promotionalFile)">

				<table align="center" class="formStyles">
					<tr>
						<td><input type="hidden"
							value="${promotionalFileModel.campaignModel.campaignId}"
							id="campaign_id"></td>
						<td><input type="hidden"
							value="${promotionalFileModel.userModel.userId}" id="user_id"></td>
					</tr>
					<tr>
						<td>Campaign Name</td>
						<td><select name="campaignModel.campaignId" class="select"
							id="campaignIdDDL">
								<!-- <option value="0">-- Select --</option> -->
								<c:forEach items="${campaignList}" var="campList">
									<option value="${campList.campaignId}">${campList.campaignName}</option>
								</c:forEach>
						</select></td>
					</tr>

					<tr id="userTr">
						<td>User Name</td>
						<td><select name="userModel.userId" class="select"
							id="userIdDDL">
								<!-- <option value="0">-- Select --</option> -->
								<c:forEach items="${userList}" var="userList">
									<option value="${userList.userId}">${userList.userName}</option>
								</c:forEach>
						</select></td>
					</tr>

					<tr>
						<td>Upload Promotional File</td>
						<td><input class="buttonStyle" type="file" readonly
							placeholder="Readonly input here…" name="fileName1" multiple></td>
					</tr>

					<tr>
						<td>&nbsp;</td>
						<td><input type="submit" value="Save"><a
							href="viewPromotionalFileUpload"><input type="button"
								value="Cancel" id="backbutton" class="buttonStyle2"></a></td>
					</tr>

				</table>
			</form>

		</c:otherwise>
	</c:choose>

	<%@ include file="admin-footer.jsp"%>