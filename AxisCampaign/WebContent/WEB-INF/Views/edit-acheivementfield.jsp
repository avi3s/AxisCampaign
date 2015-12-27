<%@ include file="admin-header.jsp"%>

<script type="text/javascript" language="javascript">
	$(document)
			.ready(
					function() {

						$("#add_more")
								.click(
										function() {
											//alert(row_id);
											//alert("Call on the add more jsp page")
											row_id = row_id + 1;
											//$("#t").append("<tr id="+row_id+"><td><div class=\"formGroup\"><label class=\"staticField\">Field Name</label><input class=\"normal\" id=\"fieldName\" type=\"text\" name=\"fieldName\"></div></td><td><input type=\"button\" value=\"Cancel\" onclick=\"del("+row_id+")\"></td></tr>");
											//$("#d"+row_id).html("<div class=\"formGroup\"><label class=\"staticField\">Field Name</label><div class=\"leftInput\"><input class=\"normal\" id=\"fieldName\" type=\"text\" name=\"fieldName_array\"><input class=\"normal\" type=\"button\" value=\"Cancel\" onclick=\"del("+row_id+")\"></div></div>");
											//$("#maindiv").find(':nth-child(1)').html("<div class=\"formGroup\"><label class=\"staticField\">Field Name</label><div class=\"leftInput\"><input class=\"normal\" id=\"fieldName\" type=\"text\" name=\"fieldName_array\"><input class=\"normal\" type=\"button\" value=\"Cancel\" onclick=\"del("+row_id+")\"></div></div>");
											$("#maindiv")
													.append(
															"<div id=t"+row_id+"></div>");
											//alert("div id created t"+row_id);

											//var temp_id=$("#maindiv").find(":nth-child("+row_id+")").attr('id');
											//alert("id :: "+temp_id);
											$("#t" + row_id)
													.html(
															"<div class=\"formGroup\"><label class=\"staticField\">Field Name</label><div><input class=\"normal\" id=\"fieldName\" type=\"text\" name=\"fieldName_array\"><br><input class=\"normal\" type=\"button\" value=\"Cancel\" onclick=\"del("
																	+ row_id
																	+ ")\"></div></div>");
											//alert("done");
											//alert(row_id);
										});

					});

	function del(row_id) {
		alert("here is " + row_id);
		$("#t" + row_id).remove();
	}

	// Edit Achievement Field Client Side Validation
	function checkForm(editAchievementDetails) {
		if (editAchievementDetails.step1.value == ""
				|| editAchievementDetails.step1.value == "Role") {
			alert('Please Select a Role!');
			editAchievementDetails.step1.focus();
			return false;
		}
		if (editAchievementDetails.step2.value == ""
				|| editAchievementDetails.step2.value == "Campaign Name") {
			alert('Please Select a Campaign Name!');
			editAchievementDetails.step2.focus();
			return false;
		}
		if (editAchievementDetails.type_id.value == ""
				|| editAchievementDetails.type_id.value == "Field Type") {
			alert('Please Select a Field Type!');
			editAchievementDetails.type_id.focus();
			return false;
		}
		if (editAchievementDetails.fieldName1.value == ""
				|| editAchievementDetails.fieldName1.value == "Field Name") {
			alert('Please Select a Field Name!');
			editAchievementDetails.fieldName1.focus();
			return false;
		}
		return true;
	}
</script>
<div class="titleArea">
	<center>
		<h1>
			<strong>Edit Acheivement Field Page</strong>
		</h1>
	</center>
</div>


<form class="fullForm" id="editAchievementDetails" method="post"
	name="frm" action="saveEditedAcheivementValues"
	onSubmit="return checkForm(editAchievementDetails)">
	<%-- <c:out value="${ddlselectroloeId}"></c:out> --%>
	<h4 style="color: red;">${ddlselectroloeId}</h4>
	<h4 style="color: red;">${ddlselectcampaignId}</h4>
	<h4 style="color: red;">${ddlselectfieldnamearray2}</h4>
	<h4 style="color: red;">${ddlselectfieldType}</h4>
	<!-- <table align="center" class="formStyles"> -->

	<table align="center" class="formStyles">



		<tr>
			<td>Select Role</td>
			<%-- <c:out value="${ddlselectroloeId}"></c:out> --%>
			<td><select name="roleId" class="select" id="step1">
					<option value=>-- Select --</option>
					<c:forEach var="roleModel" items="${roleModels}">
						<c:choose>
							<c:when
								test="${roleModel.role_name == acheivementModel.roleName}">
								<option value="${roleModel.roleId}" selected>${roleModel.role_name}</option>
							</c:when>
							<c:otherwise>
								<option value="${roleModel.roleId}">${roleModel.role_name}</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
			</select></td>
		</tr>


		<tr>
			<td>Campaign Name</td>
			<%-- <c:out value="${ddlselectcampaignId}"></c:out> --%>
			<td><select name="campaignId" class="select" id="step2">
					<option value=>-- Select --</option>
					<script>
						var selectedState = '${acheivementModel.campaignName}'
					</script>
			</select></td>

		</tr>




		<tr>
			<td>Field Name</td>
			<%-- <c:out value="${ddlselectfieldnamearray1}"></c:out> --%>
			<td><input class="normal" id="fieldName1" type="text"
				name="updatefieldName"
				value="${fn:escapeXml(acheivementModel.updatefieldName)}"></td>
		</tr>

		<tr>
			<td>Field Type</td>
			<td><select name="fieldType" id="type_id" class="select">
					<option value=>-- Select --</option>
					<c:if test="${acheivementModel.fieldType=='Route1'}">
						<option value="Route1" selected="selected">${acheivementModel.fieldType}</option>
						<option value="Route2">Route2</option>
					</c:if>
					<c:if test="${acheivementModel.fieldType=='Route2'}">
						<option value="Route2" selected="selected">${acheivementModel.fieldType}</option>
						<option value="Route1">Route1</option>
					</c:if>




			</select></td>

		</tr>


		<div id="maindiv"></div>

		<input class="normal" id="fieldName2" type="hidden"
			name="acheivementId" value="${acheivementModel.acheivementId}">


		<!-- <input type="submit" class="buttonStyle" value="Submit">
			<a href="viewacheivementfield"><input type="button" class="buttonStyle" value="Cancel" id="backbutton"></a> -->
		<tr>
			<td>&nbsp;</td>
			<td><input type="submit" value="Save" class="buttonStyle"><a
				href="viewacheivementfield"><input type="button"
					class="buttonStyle2" value="Cancel" id="backbutton"></a></td>
		</tr>




	</table>
</form>
</div>



</body>

<script type="text/javascript">
	$('#step1')
			.change(
					function() {
						var roleID = $("#step1").val();
						$("#step2").html("");
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

											markup += "<option value=" + data[x].campaignId + ">"
													+ data[x].campaignName
													+ "</option>";

										}
										$('#step2').html(markup).show();

									},
									error : function(reponse) {
									}
								});
					});
</script>

<script type="text/javascript">
	$(document)
			.ready(
					function() {

						//Onload call for Campaign Against Role Id
						var roleID = $("#step1").val();
						$
								.ajax({
									url : 'campaignagainstRoleIdonload?q='
											+ roleID,
									cache : true,
									type : "GET",
									success : function(result) {
										var data = jQuery.parseJSON(result);
										var markup = "";
										for (var x = 0; x < data.length; x++) {
											if (selectedState == data[x].campaignName) {
												markup += "<option value=" + data[x].campaignId + " selected>"
														+ data[x].campaignName
														+ "</option>";
											} else {
												markup += "<option value=" + data[x].campaignId + ">"
														+ data[x].campaignName
														+ "</option>";
											}

											console.log(data[x].campaignId);
											//    alert(data[x].id);
										}
										$('#step2').html(markup).show();
										/* setTimeout(function() {
										   next2();
										}, 50); */
									},
									error : function(reponse) {
									}
								});
					});
</script>


<%@ include file="admin-footer.jsp"%>