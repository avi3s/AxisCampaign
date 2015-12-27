<%@ include file="admin-header.jsp"%>

<script type="text/javascript" language="javascript">
	$(document).ready(function() {
		var campaign = $("#campaign_id").val();
		//alert(campaign);
		$("#campaignIdDDL").val(campaign);

		var user = $("#user_id").val();
		//alert(user);
		$("#userIdDDL").val(user);
		
		
		$("#campaignIdDDL").change(function(data,status){
			alert("okk :: "+$("#campaignIdDDL").val());
			
			$.get("./ajaxCallForFetchingUserByCampaignId", {
				campaignId : $("#campaignIdDDL").val()
			}, function(data, status) {
				//alert(data);
				$("#userIdDDL").empty();
				//$("#userIdDDL").append("<option nvalue=0>---Select---</option>");
				$("#userIdDDL").append(data);

			});
			
	
			
			//alert("end");
		});
	});
	
	
    //.....................Edit Promotional-File jsp Client-Side Validation...............//
	function checkForm(editpromotionalFile)
	{
		if(editpromotionalFile.campaignIdDDL.value=="" || editpromotionalFile.campaignIdDDL.value=="Campaign Name")
		{
			alert ('Please Select the Campaign Name!');
			editpromotionalFile.campaignIdDDL.focus();
			return false;
		}
		if(editpromotionalFile.userIdDDL.value=="" || editpromotionalFile.userIdDDL.value=="User Name")
		{
			alert ('Please Select the User Name!');
			editpromotionalFile.userIdDDL.focus();
			return false;
		}
		return true;
	}
	
	
	
	
	
	
</script>
<body>

	<center>
		<h1>Update Promotional File</h1>
	</center>

	<form class="fullForm" method="post" name="frm"
		action="updatepromotionalfile" enctype="multipart/form-data"
		id="editpromotionalFile"
		onSubmit="return checkForm(editpromotionalFile)">

		<c:if test="${not empty param['errorMessage']}">
			<h4 style="color: red;">${param['errorMessage']}</h4>
		</c:if>


		<table align="center" class="formStyles">

			<tr>
				<td><input type="hidden"
					value="${promotionalFileModel.campaignFileUserId}"
					name="campaignFileUserId" id="id"></td>
				<td><input type="hidden"
					value="${promotionalFileModel.campaignModel.campaignId}"
					id="campaign_id"></td>
			</tr>

			<tr>
				<td><input type="hidden"
					value="${promotionalFileModel.userModel.userId}" id="user_id"></td>
				<td><input type="hidden"
					value="${promotionalFileModel.fileName}" name="fileName"></td>
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

			<tr>
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
				<td colspan="2"><a
					href="downloadpromotionalfile?name=${promotionalFileModel.fileName}&id=${promotionalFileModel.campaignFileUserId}">${promotionalFileModel.fileName}</a>&nbsp;&nbsp;&nbsp;</td>
			</tr>
			<tr>
				<td>Upload Promotional File</td>
				<td><input class="buttonStyle" type="file" readonly
					placeholder="Readonly input here…" name="fileName1"></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td><input type="submit" value="Save"><a
					href="viewPromotionalFileUpload"><input type="button"
						value="Cancel" id="backbutton" class="buttonStyle2"></a></td>
			</tr>

		</table>


	</form>

	<%@ include file="admin-footer.jsp"%>