<%@ include file="admin-header.jsp"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<script type="text/javascript" language="javascript">
	$(document).ready(
			function() {

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

				//alert("roleid: "+targetRoleId+" camp id: "+targetCampId);

				if (targetRoleId != "" && targetCampId != ""
						&& targetRoleId != 0 && targetCampId != 0) {
					//alert("Ok");
					createDynamicTargetFieldValues(targetRoleId, targetCampId);
				}
				/* else{
					alert("Not Ok.");
				} */

			});

	function getCampaign(roleid) {
		//var roleid = $('#roleId').val();
		//alert("roleid: "+roleid);
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

					var targetCampId = $('#targetCampId').val();
					//alert(targetCampId);
					if (targetCampId == "") {
					} else
						$("#campaignId").val(targetCampId);

				},
				error : function(e) {
					//alert("Error: " + e);
				}
			});
		} else {
			$('#campaignId').html('<option value="0">--Select--</option>');
		}

	}

	function createDynamicFields() {
		var campid = $('#campaignId').val();
		//alert(campid);
		var roleid = $('#roleId').val();
		//alert("roleId: "+ roleid);

		if (campid != 0) {
			$
					.ajax({
						type : "POST",
						url : "createDynamicFields",
						data : "roleid=" + roleid + "&campid=" + campid,
						dataType : "json",
						success : function(data) {
							//alert(data[0]);
							var len = data.length;
							var html = '';
							var hidden = '';
							var info = '';
							//alert("campid: "+data[len-1]);
							for (var i = 0; i < len - 1; ++i) {
								html += "<tr><td>"
										+ data[i]
										+ "</td><td><input id=\"fieldName\" type=\"text\" name=\"fieldValue_array\"></td></tr>";
								//html += "<div class=\"formGroup\"><label class=\"staticField\">"+data[i]+"\</label><div class=\"leftInput\"><input class=\"normal\" id=\"fieldName\" type=\"text\" name=\"fieldName_array\"></div></div>";
							}
							//hidden += "<div class=\"formGroup\"><div class=\"leftInput\"><input class=\"normal\" id=\"campId\" type=\"hidden\" name=\"campId\" value="+data[len-1]+"></div></div>";

							hidden += "<input id=\"campId\" type=\"hidden\" name=\"campId\" value="+data[len-1]+">";

							info += html + hidden;
							$('#maindiv').html(info);

						},
						error : function(e) {
							//alert("Error: " + e);
						}
					});
		} else {
			$('#maindiv').html('');
		}
	}

	function createDynamicTargetFieldValues(targetRoleId, targetCampId) {
		//alert("***OK*****");

		$
				.ajax({
					type : "POST",
					url : "createDynamicTargetFieldValues",
					data : "roleid=" + targetRoleId + "&campid=" + targetCampId,
					dataType : "json",
					success : function(data) {
						var array_values = new Array();

						var len = data.length;
						//alert("length: "+len);
						var html = '';
						//var hidden = '';
						//var info = '';	
						//alert("fieldValue : "+ "${targetFieldValueModel.fieldValue_array[0]}");

						<c:forEach items="${targetFieldValueModel.fieldValue_array}" var="temp">
						array_values.push("${temp}");
						</c:forEach>

						/* if(len == 1){
							html += "<tr><td>"
								+ data
								+ "</td><td><input id=\"fieldName\" type=\"text\" name=\"fieldValue_array\" value=""></td></tr>";
						} else{  */
						for (i = 0; i < len; ++i) {

							html += "<tr><td>"
									+ data[i]
									+ "</td><td><input id=\"fieldName\" type=\"text\" name=\"fieldValue_array\" value="+array_values[i]+"></td></tr>";
							//alert("html : "+html);

						}
						//}
						//alert(html);
						$('#maindiv').html(html);

					},
					error : function(e) {
						//alert("Error: " + e);
					}
				});
	}
	/* --- for Target Field Value Details --- */
	function checkForm(saveTargetFieldValue)
	{
		if(saveTargetFieldValue.roleId.value=="0" || saveTargetFieldValue.roleId.value=="Role")
		{
			alert ('Please Select a Role!');
			saveTargetFieldValue.roleId.focus();
			return false;
		}
		if(saveTargetFieldValue.campaignId.value=="0" || saveTargetFieldValue.campaignId.value=="Campaign Name")
		{
			alert ('Please Select a Campaign Name!');
			saveTargetFieldValue.campaignId.focus();
			return false;
		}
		return true;
	}
	
	/* --- for Target Field Value Edit  Details --- */
	function checkFormEdit(updateTargetFieldValue)
	{
		if(updateTargetFieldValue.roleId.value=="0" || updateTargetFieldValue.roleId.value=="Role")
		{
			alert ('Please Select a Role!');
			updateTargetFieldValue.roleId.focus();
			return false;
		}
		if(updateTargetFieldValue.campaignId.value=="0" || updateTargetFieldValue.campaignId.value=="Campaign Name")
		{
			alert ('Please Select a Campaign Name!');
			updateTargetFieldValue.campaignId.focus();
			return false;
		}
		
		
		if(updateTargetFieldValue.fieldName1.value=="" || updateTargetFieldValue.fieldName1.value=="Target Field Name")
		{
			alert ('Please Select a Target Field Name!');
			updateTargetFieldValue.fieldName1.focus();
			return false;
		}
		
		return true;
	}
	
	
	
	
	
</script>


<c:choose>
	<c:when test="${param['update'] == 1 or update==1}">
		<div class="titleArea">
			<center>
				<h1>
					<strong>Update Target Field Value Details</strong>
				</h1>
			</center>
		</div>
		<%-- <c:if test="${not empty param['errorMessage']}">
			<h4 style="color: red;">${param['errorMessage']}</h4>
		</c:if> --%>
		<form class="fullForm" method="post" name="frm"
			id="updateTargetFieldValue" action="updateTargetFieldValue"
			onSubmit="return checkFormEdit(updateTargetFieldValue)">

			<c:if test="${not empty targetValue}">
				<h4 class="error" style="color: red;">
					<c:out value="${targetValue}"></c:out>
				</h4>
			</c:if>

			<table align="center" class="formStyles">
				<tr>
					<td colspan="2"><input type="hidden"
						value="${targetFieldValueModel.targetFieldValueId}"
						name="targetFieldValueId" id="targetFieldValueId"></td>
				</tr>

				<tr>
					<td>${targetFieldValueModel.fieldName}</td>
					<td><c:set var="f" value="${targetFieldValueModel.fieldValue}" />
						<input id="fieldName1" type="text" name="fieldValue"
						value="${fn:escapeXml(f)}"> <%-- <td><input id="fieldName1" type="text"
						value="${targetValueModel.fieldValue}" name="fieldValue"></td> --%>
				</tr>

				<tr>
					<td>&nbsp;</td>
					<td><input type="submit" class="buttonStyle" value="Save"><a
						href="viewTargetFieldValue"><input type="button"
							value="Cancel" id="backbutton" class="buttonStyle2"></a></td>
				</tr>


			</table>
		</form>
	</c:when>
	<c:otherwise>
		<div class="titleArea">
			<center>
				<h1>
					<strong>Add Target Field Value Details</strong>
				</h1>
			</center>
		</div>
		<c:choose>
			<c:when test="${not empty NoActiveRoleFound}">
				<h3>
					<font color="red"><c:out value="${NoActiveRoleFound}"></c:out></font>
				</h3>
			</c:when>
			<c:otherwise>

				</br>

				<%-- <c:if test="${not empty param['errorMessage']}">
			<h4 style="color: red;">${param['errorMessage']}</h4>
		</c:if> --%>

				<form method="post" name="frm" id="saveTargetFieldValue"
					action="saveTargetFieldValue"
					onSubmit="return checkForm(saveTargetFieldValue)">

					<c:if test="${not empty targetValueRoleId}">
						<h4 class="error" style="color: red;">
							<c:out value="${targetValueRoleId}"></c:out>
						</h4>
					</c:if>
					<c:if test="${not empty targetValueCampaignId}">
						<h4 class="error" style="color: red;">
							<c:out value="${targetValueCampaignId}"></c:out>
						</h4>
					</c:if>
					<c:if test="${not empty targetValue}">
						<h4 class="error" style="color: red;">
							<c:out value="${targetValue}"></c:out>
						</h4>
					</c:if>

					<table align="center" class="formStyles">
						<tr>
							<td colspan="2"><input type="hidden" id="targetRoleId"
								value="${targetFieldValueModel.roleId}"></td>
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
								value="${targetFieldValueModel.campaignId}"></td>
						</tr>

						<tr>
							<td>Campaign Name</td>
							<td><select name="campaignId" id="campaignId"
								onChange="javascript:createDynamicFields(this);">
									<option value="0">-- Select --</option>

							</select></td>
						</tr>

						<!-- <tr>
					<td colspan="2"><div id="maindiv"></div></td>
				</tr> -->
						<tr>
							<td colspan="2" style="padding: 0; width: 100%;">
								<table id="maindiv" cellpadding="0" cellspacing="0" width="100%"
									class="formStyles"></table>
							</td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td><input type="submit" class="buttonStyle" value="Save"><a
								href="viewTargetFieldValue"><input type="button"
									value="Cancel" id="backbutton" class="buttonStyle2"></a></td>
						</tr>

					</table>


				</form>
			</c:otherwise>
		</c:choose>
	</c:otherwise>
</c:choose>


<%@ include file="admin-footer.jsp"%>