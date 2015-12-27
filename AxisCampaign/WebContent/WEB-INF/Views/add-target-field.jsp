<%@ include file="admin-header.jsp"%>

<script type="text/javascript" language="javascript">
	$(document)
			.ready(
					function() {

						var targetRoleId = $("#targetRoleId").val();
						if (targetRoleId == "") {
						} else {
							$("#roleId1").val(targetRoleId);
							getCampaign(targetRoleId);
						}

						var targetCampId = $('#targetCampId').val();
						//alert(targetCampId);
						if (targetCampId == "") {
						} else
							$("#campaignId1").val(targetCampId);

						var targetFieldId = $('#targetFieldId').val();
						if (targetFieldId == "") {
						} else
							$("#fieldType1").val(targetFieldId);

						var row_id = 0;
						//alert("ok");
						$("#add_more")
								.click(
										function() {
											//alert(row_id);
											row_id = row_id + 1;

											$("#maindiv").append(
													"<tr id=t"+row_id+"></tr>");
											//alert("div id created t"+row_id);

											$("#t" + row_id)
													.html(
															"<td>Target Field Name</td><td><input id=\"fieldName1\" type=\"text\" name=\"fieldName_array\" onBlur=\"checkFieldName(this.value)\"><input type=\"button\" class=\"buttonStyle2\" value=\"Cancel\" onclick=\"del("
																	+ row_id
																	+ ")\"></td>");

										});

						var i = 0;
						<c:forEach items="${targetFieldModel.fieldName_array}" var="temp">
						i = i + 1;
						row_id = row_id + 1;
						if (i != 1) {
							$("#maindiv").append("<tr id=t"+row_id+"></tr>");

							$("#t" + row_id)
									.html(
											"<td>Target Field Name</td><td><input id=\"fieldName1\" type=\"text\" name=\"fieldName_array\"  value=\"${temp}\" onBlur=\"checkFieldName(this.value)\"><input type=\"button\" class=\"buttonStyle2\" value=\"Cancel\" onclick=\"del("
													+ row_id + ")\"></td>>");
						}
						</c:forEach>

					});

	function del(row_id) {
		//alert("here is "+row_id);
		$("#t" + row_id).remove();
	}

	function getCampaign(roleid) {

		//alert("RoleId: "+roleid);

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

					for (var i = 0; i < len; ++i) {
						html += '<option value="' + data[i].campaignId + '">'
								+ data[i].campaignName + '</option>';
					}

					$('#campaignId').html(html);
					$('#campaignId1').html(html);

					/* var targetCampId = $('#targetCampId').val();
					//alert("campaign id: "+targetCampId);
					if(targetCampId == ""){}
					else
					$("#campaignId1").val(targetCampId);  */

				},
				error : function(e) {
					//alert("Error: " + e);
				}
			});
		} else {
			$('#campaignId1').html('<option value="0">--Select--</option>');
			$('#campaignId').html('<option value="0">--Select--</option>');
		}

	}

	function checkFieldName(fieldName) {
		//alert("Hi..");
		var roleId = $('#roleId1').val();
		var campId = $('#campaignId1').val();
		//alert("role Id: "+roleId+" campaign Id: "+campId+ " FieldName: "+fieldName );

		if (roleId != 0 && campId != 0 && fieldName != "") {
			// alert("Ok");
			$.ajax({
				type : "POST",
				url : "checkFieldName",
				data : "roleId=" + roleId + "&campId=" + campId + "&fieldName="
						+ fieldName,
				dataType : "text",
				success : function(data) {
					//alert("Ok. All right");

					$('#info').html(data);
					//$('#fieldName1').html("");
				},
				error : function(e) {
					//alert("Error: " + e);
				}
			});
		} else {
			$('#info').html("");
		}

	}
	/* --- for Target Field Details --- */
	function checkForm(saveTargetField)
	{
		if(saveTargetField.roleId1.value=="" || saveTargetField.roleId1.value=="Role")
		{
			alert ('Please Select a Role!');
			saveTargetField.roleId1.focus();
			return false;
		}
		if(saveTargetField.campaignId1.value=="" || saveTargetField.campaignId1.value=="Campaign Name")
		{
			alert ('Please Select a Campaign Name!');
			saveTargetField.campaignId1.focus();
			return false;
		}
		if(saveTargetField.fieldType1.value=="" || saveTargetField.fieldType1.value=="Field Type")
		{
			alert ('Please Select a Field Type!');
			saveTargetField.fieldType1.focus();
			return false;
		}
		if(saveTargetField.fieldName1.value=="" || saveTargetField.fieldName1.value=="Target Field Name")
		{
			alert ('Please Select a Target Field Name!');
			saveTargetField.fieldName1.focus();
			return false;
		}
		return true;
	}
	
	/* --- for Edit Target Field Details Client Side Validation --- */
	
	
	function checkFormEdit(updateTargetField)
	{
		if(updateTargetField.roleId.value=="" || updateTargetField.roleId.value=="Role")
		{
			alert ('Please Select a Role!');
			updateTargetField.roleId.focus();
			return false;
		}
		if(updateTargetField.campaignId.value=="" || updateTargetField.campaignId.value=="Campaign Name")
		{
			alert ('Please Select a Campaign Name!');
			updateTargetField.campaignId.focus();
			return false;
		}
		if(updateTargetField.fieldType.value=="" || updateTargetField.fieldType.value=="Field Type")
		{
			alert ('Please Select a Field Type!');
			updateTargetField.fieldType.focus();
			return false;
		}
		if(updateTargetField.fieldName1.value=="" || updateTargetField.fieldName1.value=="Target Field Name")
		{
			alert ('Please Select a Target Field Name!');
			updateTargetField.fieldName1.focus();
			return false;
		}
		return true;
	}
	
	
	
	
	
	
</script>
</head>
<body>
	<%-- <c:if test="${not empty param['errorMessage']}">
				<h4 style="color: red;">${param['errorMessage']}</h4>
			</c:if> --%>
	<c:choose>
		<c:when test="${param['update'] == 1 or update==1}">
			<div class="titleArea">
				<center>
					<h1>
						<strong>Update Target Field Details</strong>
					</h1>
				</center>
			</div>


			<form method="post" name="frm" id="updateTargetField"
				action="updateTargetField"
				onSubmit="return checkFormEdit(updateTargetField)">

				<c:if test="${not empty targetNotFound}">
					<h4 class="error" style="color: red;">
						<c:out value="${targetNotFound}"></c:out>
					</h4>
				</c:if>

				<c:if test="${not empty updateRoleId}">
					<h4 class="error" style="color: red;">
						<c:out value="${updateRoleId}"></c:out>
					</h4>
				</c:if>
				<c:if test="${not empty updateCampaignId}">
					<h4 class="error" style="color: red;">
						<c:out value="${updateCampaignId}"></c:out>
					</h4>
				</c:if>
				<c:if test="${not empty updateFieldType}">
					<h4 class="error" style="color: red;">
						<c:out value="${updateFieldType}"></c:out>
					</h4>
				</c:if>
				<c:if test="${not empty updateTargetName}">
					<h4 class="error" style="color: red;">
						<c:out value="${updateTargetName}"></c:out>
					</h4>
				</c:if>

				<table align="center" class="formStyles">
					<tr>
						<td><input type="hidden" value="${targetModel.targetFieldId}"
							name="targetFieldId" id="targetFieldId"></td>
						<td><c:set var="targetId" value="${targetModel.campaignId}" />
							<c:set var="rid" value="${targetModel.roleId}" /> <%-- <c:out value="${ rid}" /> --%>
							<c:set var="ftype" value="${targetModel.fieldType}" /> <%-- <c:out value="${ ftype}" />
			<c:set var="t10"  value="Top10"/>
			 <c:out value="${t10}" /> --%></td>
					</tr>

					<tr>
						<td>Select Role</td>
						<td><select name="roleId" id="roleId"
							onChange="getCampaign(this.value);">
								<option value="0">-- Select --</option>
								<c:forEach items="${roleList}" var="role">
									<c:set var="roleId" value="${role.roleId}" />

									<c:choose>
										<c:when test="${rid == roleId}">
											<option id="role" value="${role.roleId}" selected>${role.role_name}</option>
										</c:when>
										<c:otherwise>
											<option id="role" value="${role.roleId}">${role.role_name}</option>
										</c:otherwise>
									</c:choose>

								</c:forEach>
								<%-- <option value="${targetModel.roleId}">${targetModel.roleName}</option> --%>
						</select></td>
					</tr>

					<tr>
						<td>Campaign Name</td>
						<td><select name="campaignId" id="campaignId">
								<!-- <option value="0">-- Select --</option> -->
								<c:forEach items="${campaignList}" var="campaign">
									<c:set var="campId" value="${campaign.campaignId}" />

									<c:choose>
										<c:when test="${targetId == campId}">
											<option value="${campaign.campaignId}" selected>${campaign.campaignName}</option>
										</c:when>

										<c:otherwise>
											<option value="${campaign.campaignId}">${campaign.campaignName}</option>
										</c:otherwise>
									</c:choose>

								</c:forEach>
								<%-- <option value="${targetModel.campaignId}">${targetModel.campaignName}</option> --%>
						</select></td>
						<c:if test="${not empty errorMessage}">
							<h4 style="color: red;">${campaignId}</h4>
						</c:if>
					</tr>


					<tr>
						<td>Field Type</td>
						<td><select name="fieldType" id="fieldType" class="select">
								<!-- <option value="0">-- Select --</option> -->
								<c:set var="t10" value="Top10" />

								<c:choose>
									<c:when test="${ftype == t10 }">
										<%-- <option value="${targetModel.fieldType}" selected>${targetModel.fieldType}</option> --%>
										<option value="Top10" selected>Top10</option>
										<option value="Top20">Top20</option>
									</c:when>
									<c:otherwise>
										<option value="Top10">Top10</option>
										<option value="Top20" selected>Top20</option>
									</c:otherwise>
								</c:choose>

						</select></td>
					</tr>


					<tr>
						<td>Target Field Name</td>
						<td><c:set var="f" value="${targetModel.fieldName}" /> <input
							id="fieldName1" type="text" name="fieldName"
							value="${fn:escapeXml(f)}"> <%-- <input id="fieldName1" type="text"
							value="${targetModel.fieldName}" name="fieldName"> --%></td>
					</tr>


					<table cellpadding="0" cellspacing="0" width="100%"
						class="formStyles" id="maindiv"></table>

					<tr>
						<td colspan="2"><h4 class="error" style="color: red;">${targetFieldCount }</h4></td>
					</tr>

					<tr>
						<td colspan="2"><input type="submit" value="Save"
							class="buttonStyle"> <a href="viewTargetField"><input
								type="button" class="buttonStyle2" value="Cancel"
								id="backbutton"></a></td>
					</tr>

				</table>

			</form>
		</c:when>

		<c:otherwise>

			<c:choose>

				<c:when test="${not empty NoActiveRoleFound}">
					<h3>
						<font color="red"><c:out value="${NoActiveRoleFound}"></c:out></font>
					</h3>
				</c:when>
				<c:otherwise>
					<div class="titleArea">
						<center>
							<h1>
								<strong>Add Target Field Details</strong>
							</h1>
						</center>
					</div>


					<form class="fullForm" method="post" name="frm"
						id="saveTargetField" action="saveTargetField"
						onSubmit="return checkForm(saveTargetField)">

						<c:if test="${not empty roleId}">
							<h4 class="error" style="color: red;">
								<c:out value="${roleId}"></c:out>
							</h4>
						</c:if>
						<c:if test="${not empty campaignId}">
							<h4 class="error" style="color: red;">
								<c:out value="${campaignId}"></c:out>
							</h4>
						</c:if>
						<c:if test="${not empty fieldType}">
							<h4 class="error" style="color: red;">
								<c:out value="${fieldType}"></c:out>
							</h4>
						</c:if>
						<c:if test="${not empty targetName}">
							<h4 class="error" style="color: red;">
								<c:out value="${targetName}"></c:out>
							</h4>
						</c:if>


						<table align="center" class="formStyles">
							<tr>
								<td colspan="2"><input type="hidden" id="targetRoleId"
									value="${targetFieldModel.roleId}"></td>
							</tr>

							<tr>
								<td>Select Role</td>
								<td><select name="roleId" id="roleId1"
									onChange="getCampaign(this.value);">
										<option value="0">-- Select --</option>
										<!-- <div id="roleId1"></div> -->
										<c:forEach items="${roleList}" var="role">
											<option value="${role.roleId}">${role.role_name}</option>
										</c:forEach>
								</select></td>
							</tr>

							<tr>
								<td colspan="2"><input type="hidden" id="targetCampId"
									value="${targetFieldModel.campaignId}"></td>
							</tr>

							<tr>
								<td>Campaign Name</td>
								<td><select name="campaignId" id="campaignId1">
										<option value="0">-- Select --</option>
										<%-- <c:forEach items="${campaignList}" var="campaign">
							<option value="${campaign.campaignId}">${campaign.campaignName}</option>
						</c:forEach> --%>
								</select></td>
							</tr>

							<tr>
								<td colspan="2"><input type="hidden" id="targetFieldId"
									value="${targetFieldModel.fieldType}"></td>
							</tr>

							<tr>
								<td>Field Type</td>
								<td><select name="fieldType" id="fieldType1" class="select">
										<option value="0">-- Select --</option>
										<option value="Top10">Top10</option>
										<option value="Top20">Top20</option>
								</select></td>
							</tr>

							<%-- <tr>
						<td colspan="2"><h4 style="color: red;">${fieldType}</h4></td>
					</tr> --%>

							<tr>
								<td>Target Field Name</td>
								<td><input id="fieldName1" type="text"
									name="fieldName_array"
									value="${targetFieldModel.fieldName_array[0]}"
									onBlur="checkFieldName(this.value)"></td>

							</tr>

							<%-- <c:forEach var="i" begin="0" end="${targetFieldCount}">					   
					   <tr>
							<td colspan="2"><h4 style="color: red;">${targetName+i}</h4></td>
						</tr>
					</c:forEach> --%>

							<%-- <tr>
						<td colspan="2"><h4 style="color: red;">${targetName0}</h4></td>
					</tr> --%>

							<table cellpadding="0" cellspacing="0" width="100%"
								class="formStyles" id="maindiv"></table>

							<tr>

							</tr>

							<tr>
								<td colspan="2"><font color="red"><div id="info"></div></font></td>
							</tr>

							<tr>
								<td colspan="2"><h4 class="error" style="color: red;">${targetFieldCount }</h4></td>
							</tr>

							<tr>

								<td colspan="2"><input type="button" id="add_more"
									value="Add More Fields" class="buttonStyle"><input
									type="submit" value="Save" class="buttonStyle"></td>
								<td><a href="viewTargetField"><input type="button"
										class="buttonStyle2" value="Cancel" id="backbutton"></a></td>
							</tr>


						</table>

					</form>
				</c:otherwise>
			</c:choose>
		</c:otherwise>
	</c:choose>



	<%@ include file="admin-footer.jsp"%>