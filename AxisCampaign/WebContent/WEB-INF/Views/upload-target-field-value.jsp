<%@ include file="admin-header.jsp"%>

<script type="text/javascript" language="javascript">
	$(document).ready(function() {
		var targetRoleId = $("#targetRoleId").val();
		if (targetRoleId == "") {
		} else {
			$("#roleId").val(targetRoleId);
			getCampaign(targetRoleId);
		}

		var targetCampId = $('#targetCampId').val();
		//alert(targetCampId);
		if (targetCampId == "") {
		} else
			$("#campaignId").val(targetCampId);

		/* var targetFile = $('#name').val();
		//alert("targetFile: "+targetFile);
		if (targetFile == "") {
		} else
			$("#fileName").val(targetFile); */
	});

	function getCampaign(val) {
		var roleid = $('#roleId').val();
		//alert(role);
		if (roleid != 0) {
			$.ajax({
				type : "POST",
				url : "showCampaignList",
				data : "roleid=" + roleid,
				dataType : "json",
				success : function(data) {
					//alert("Ok. All right");
					// alert(data[0].campaignId);
					var len = data.length;
					var html = '';
					var defaultoption = '';

					for (var i = 0; i < len; ++i) {
						html += '<option value="' + data[i].campaignId + '">'
								+ data[i].campaignName + '</option>';
					}

					defaultoption += '<option value="0">--Select--</option>';

					$('#campaignId').html(defaultoption + html);
				},
				error : function(e) {
					//alert("Error: "+e);
				}
			});
		} else {
			$('#campaignId').html('<option value="0">--Select--</option>');
		}

	}
	
	/* --- for Target Field Value Details --- */
	function checkForm(bulkTargetValue)
	{
		if(bulkTargetValue.roleId.value=="0" || bulkTargetValue.roleId.value=="Role")
		{
			alert ('Please Select a Role!');
			bulkTargetValue.roleId.focus();
			return false;
		}
		if(bulkTargetValue.campaignId.value=="0" || bulkTargetValue.campaignId.value=="Campaign Name")
		{
			alert ('Please Select a Campaign Name!');
			bulkTargetValue.campaignId.focus();
			return false;
		}
		return true; 
	}
</script>
<div class="titleArea">
	<center>
		<h1>
			<strong>Upload Bulk Target Field value</strong>
		</h1>
	</center>
</div>
<c:if test="${not empty param['errorMessage']}">
	<h4 style="color: red;">${param['errorMessage']}</h4>
</c:if>

<%-- <c:if test="${param.error != null}">
    	${param['errorMessage']}
	</c:if> --%>

<form enctype="multipart/form-data" method="POST"
	action="uploadTargetFieldValue" id="bulkTargetValue"
	onSubmit="return checkForm(bulkTargetValue)">

	<c:if test="${not empty uploadRoleId}">
		<h4 class="error" style="color: red;">
			<c:out value="${uploadRoleId}"></c:out>
		</h4>
	</c:if>
	<c:if test="${not empty uploadCampaignId}">
		<h4 class="error" style="color: red;">
			<c:out value="${uploadCampaignId}"></c:out>
		</h4>
	</c:if>
	<c:if test="${not empty uploadFile}">
		<h4 class="error" style="color: red;">
			<c:out value="${uploadFile}"></c:out>
		</h4>
	</c:if>

	<h4 class="error" style="color: red;">${fileFormatNotMatch }</h4>

	<h4 class="error" style="color: red;">
		<c:if test="${not empty mismatchedRowsList}">
				The following row numbers <c:forEach items="${mismatchedRowsList}"
				var="rowNumber">
				<c:out value="${rowNumber}" />,
			                          </c:forEach> have not been inserted due to row mismatch.

			</c:if>
	</h4>


	<c:choose>
		<c:when test="${not empty NoActiveRoleFound}">
			<h3>
				<font color="red"><c:out value="${NoActiveRoleFound}"></c:out></font>
			</h3>
		</c:when>
		<c:otherwise>

			<table class="formStyles">
				<tr>
					<td colspan="2"><input type="hidden" id="targetRoleId"
						value="${roleId}"></td>
				</tr>
				<tr>
					<td>Select Role</td>
					<td><select name="roleId" id="roleId"
						onChange="getCampaign(this.value);">
							<option value="0">-- Select --</option>
							<c:forEach items="${roleList}" var="role">
								<option value="${role.roleId}">${role.role_name}</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td colspan="2"><input type="hidden" id="targetCampId"
						value="${campaignId}"></td>
				</tr>
				<tr>
					<td>Campaign Name</td>
					<td><select name="campaignId" id="campaignId">
							<option value="0">-- Select --</option>

					</select></td>
				</tr>
				<tr>
					<td colspan="2"><input type="hidden" id="name" value="${name}"></td>
				</tr>
				<tr>
					<td>Upload Target File</td>
					<td colspan="2"><input type="file" id="fileName"
						name="fileName" size="50" class="buttonStyle" /></td>
				</tr>
				<tr>
					<td id="demoExcel"><a id="downloadExcel" href=""><input
							type="button" value="Download Demo Excel File" id="excell"
							class="buttonStyle2"></a></td>

					<td><input type="submit" value="Save"><a
						href="viewTargetFieldValue"><input type="button"
							value="Cancel" id="backbutton" class="buttonStyle2"></a></td>
				</tr>
			</table>

		</c:otherwise>
	</c:choose>
</form>

<%@ include file="admin-footer.jsp"%>