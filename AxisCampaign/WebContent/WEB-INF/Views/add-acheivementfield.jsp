<%@ include file="admin-header.jsp"%>


<script type="text/javascript" language="javascript">
	$(document)
			.ready(
					function() {
						//alert("<c:out value="${a.fieldType}"/>");
						$("#step1").val("<c:out value="${a.roleId}"/>");

						$
								.ajax({
									// url:'campaignagainstRoleId',
									url : 'campaignagainstRoleId?q='
											+ '${a.roleId}',
									cache : false,
									type : 'GET',
									success : function(result) {
										var data = jQuery.parseJSON(result);
										var markup = "";
										for (var x = 0; x < data.length; x++) {
											// alert("Naru");
											markup += "<option value=" + data[x].campaignId + ">"
													+ data[x].campaignName
													+ "</option>";
											console.log(data[x].id);
											//                                                alert(data[x].id);
										}
										$('#step2').html(markup).show();

									},
									error : function(reponse) {
									}
								});

						$("#step2").val("<c:out value="${a.campaignId}"/>");
						$("#type_id").val("<c:out value="${a.fieldType}"/>");
						var row_id1 = 0;
						var i = 0;
						<c:forEach items="${a.fieldName_array}" var="temp">
						i++;
						if (i != 1) {
							row_id1 = row_id1 + 1;
							$("#maindiv").append("<tr id=t"+row_id1+"></tr>");

							$("#t" + row_id1)
									.html(
											"<td><div class=\"formGroup\"><label class=\"staticField\">Field Name</label></td><td><div><input id=\"fieldName1\" type=\"text\" name=\"fieldName_array\"  value=\"${temp}\" ><input type=\"button\" class=\"buttonStyle2\" value=\"Cancel\" onclick=\"del("
													+ row_id1
													+ ")\"></div></div></td>");

						}
						</c:forEach>

						var row_id = 0;

						$("#add_more")
								.click(
										function() {
											row_id = row_id + 1;
											$("#maindiv").append(
													"<tr id=t"+row_id+"></tr>");
											$("#t" + row_id)
													.html(
															"<td><div class=\"formGroup\"><label class=\"staticField\">Field Name</label></td><td><div><input class=\"normal\" id=\"fieldName\" type=\"text\" name=\"fieldName_array\"><input class=\"buttonStyle2\" type=\"button\" value=\"Cancel\"  onclick=\"del("
																	+ row_id
																	+ ")\"></div></div></td>");
										});

					});

	function del(row_id) {
		//alert("here is " + row_id);
		$("#t" + row_id).remove();
	}
	
	/* --- for Achievement Field Details --- */
	function checkForm(addAchievementDetails)
	{
		if(addAchievementDetails.step1.value=="" || addAchievementDetails.step1.value=="Role")
		{
			alert ('Please Select a Role!');
			addAchievementDetails.step1.focus();
			return false;
		}
		if(addAchievementDetails.step2.value=="" || addAchievementDetails.step2.value=="Campaign Name")
		{
			alert ('Please Select a Campaign Name!');
			addAchievementDetails.step2.focus();
			return false;
		}
		if(saveTargetField.type_id.value=="" || addAchievementDetails.type_id.value=="Field Type")
		{
			alert ('Please Select a Field Type!');
			saveTargetField.type_id.focus();
			return false;
		}
		if(addAchievementDetails.fieldName1.value=="" || addAchievementDetails.fieldName1.value=="Field Name")
		{
			alert ('Please Select a Field Name!');
			addAchievementDetails.fieldName1.focus();
			return false;
		}
		return true;
	}
</script>

<c:choose>

	<c:when test="${not empty NoActiveRoleFound}">
		<h3>
			<font color="red"><c:out value="${NoActiveRoleFound}"></c:out></font>
		</h3>
	</c:when>
	<c:otherwise>
		</br>

		<c:if test="${not empty duplicateValue}">


			<%-- <c:out value="${duplicateValue}"></c:out> --%>
			<h4 style="color: red;">${duplicateValue}</h4>



		</c:if>
		<div class="titleArea">
			<center>
				<h1>
					<strong>Add Achievement Field Details</strong>
				</h1>
			</center>
		</div>
		<!-- <form class="fullForm" id="addAchievementDetails" method="post" name="frm" action="saveAcheivementFields" onSubmit="return checkForm(addAchievementDetails)">
 -->
		<form class="fullForm" id="addAchievementDetails" method="post"
			name="frm" action="saveAcheivementFields"
			onSubmit="return checkForm(addAchievementDetails)">

			<h4 style="color: red;">${ddlselectroloeId}</h4>
			<h4 style="color: red;">${ddlselectcampaignId}</h4>
			<h4 style="color: red;">${ddlselectfieldType}</h4>
			<h4 style="color: red;">${ddlselectfieldnamearray}</h4>
			<h4 style="color: red;">${ddlselectfieldnamearray1}</h4>
			<h4 style="color: red;">${ddlselectfieldnamearray8}</h4>
			<h4 style="color: red;">${ddlselectfieldnamearray9}</h4>

			<table align="center" class="formStyles">
				<tr>
					<td>Select Role</td>
					<%-- <c:out value="${ddlselectroloeId}"></c:out> --%>
					<td><select name="roleId" class="select" id="step1">
							<option value=>-- Select --</option>
							<c:forEach var="roleModel" items="${roleModels}" varStatus="loop">
								<option value="${roleModel.roleId}">${roleModel.role_name}</option>
							</c:forEach>
					</select></td>


				</tr>

				<tr>
					<td>Campaign Name</td>
					<%-- <c:out value="${ddlselectcampaignId}"></c:out> --%>
					<td><select name="campaignId" class="select" id="step2">
							<option value=>-- Select --</option>
							<%-- <c:forEach items="${campaignList}" var="campList">
						<option value="${campList.id}">${campList.campaignName}</option>
					</c:forEach> --%>
					</select></td>

				</tr>

				<tr>
					<td>Field Type</td>
					<%-- <c:out value="${ddlselectfieldType}"></c:out> --%>
					<td><select name="fieldType" id="type_id" class="select">
							<option value=>-- Select --</option>
							<option value="Route1">Route1</option>
							<option value="Route2">Route2</option>
							<!-- <option value="route1">Route1</option>
						<option value="route2">Route2</option>
						<option value="normal">Normal</option> -->

					</select></td>

				</tr>


				<tr>
					<td>Field Name</td>
					<%-- <c:out value="${ddlselectfieldnamearray1}"></c:out> --%>
					<td><input class="normal" id="fieldName1" type="text"
						name="fieldName_array" value="${a.fieldName_array[0]}"></td>
				</tr>



				<table id="maindiv" cellpadding="0" cellspacing="0" width="100%"
					class="formStyles"></table>


				<tr>
					<td colspan="2"><input type="button" id="add_more"
						value="Add More Fields" class="buttonStyle"></td>

					<%-- <c:out value="${ddlselectfieldnamearray}"></c:out> --%>

				</tr>



				<tr>
					<td><input type="submit" value="Save" class="buttonStyle"><a
						href="viewacheivementfield"><input type="button"
							class="buttonStyle2" value="Cancel" id="backbutton"></a></td>
				</tr>
			</table>

		</form>
	</c:otherwise>
</c:choose>

<table id="t">
</table>

</body>

<script type="text/javascript">
	$('#step1')
			.change(
					function() {
						var roleID = $("#step1").val();

						$
								.ajax({
									// url:'campaignagainstRoleId',
									url : 'campaignagainstRoleId?q=' + roleID,
									cache : false,
									type : 'GET',
									success : function(result) {
										var data = jQuery.parseJSON(result);
										var markup = "";
										for (var x = 0; x < data.length; x++) {
											// alert("Naru");
											markup += "<option value=" + data[x].campaignId + ">"
													+ data[x].campaignName
													+ "</option>";
											console.log(data[x].id);
											//                                                alert(data[x].id);
										}
										$('#step2').html(markup).show();

									},
									error : function(reponse) {
									}
								});
					});
</script>


<%@ include file="admin-footer.jsp"%>