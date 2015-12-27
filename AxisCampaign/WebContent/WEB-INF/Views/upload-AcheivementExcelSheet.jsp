<%@ include file="admin-header.jsp"%>
<script type="text/javascript" language="javascript">
	function savedata() {
		localStorage.SetItem("file", getElementById("filename1").value);
	}
	window.onload = function() {
		document.getElementById("filename1").value = localStorage
				.getItem("file");
	}

	$(document)
			.ready(
					function() {

						var row_id = 0;
						$("#step1").val("<c:out value="${roleId}"/>");

						$
								.ajax({
									// url:'campaignagainstRoleId',
									url : 'campaignagainstRoleId?q='
											+ '${roleId}',
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
											//console.log(data[x].id);
											//                                                alert(data[x].id);
										}
										$('#step2').html(markup).show();

									},
									error : function(reponse) {
									}
								});

						$("#step2").val("<c:out value="${campaignId}"/>");
						$("#filename1").val("<c:out value="${name}"/>");
						$("#naru").val("<c:out value="${name}"/>");

						$("#step2").change(function(){
							//alert("here is :: "+$("#step1").val());
							//alert("here is 2 :: "+$("#step2").val());
							
							/* $.get("./generatedExcellSheet",{roleId : $("#step1").val(),campId : $("#step2").val()}, function(data,success){
								//alert(data);
								$("#path").val(data);
								$("#downloadExcel").attr("href","downloadAcheivementFieldValueExcel?excelPath="+data);*/
								
								if($("#step1").val() == '') {
									alert("Please Select A Role Name");
								} else if($("#step2").val() == '') {
									alert("Please Select A Campaign Name");
								} else {
								$("#downloadExcel").attr(
										"href",
										"downloadAcheivementFieldValueExcel?roleId="
												+ $("#step1").val() + "&campId="
												+ $("#step2").val());
								}
							});
							
							//alert("end");
							return false;
						//});

					});

	function del(row_id) {
		alert("here is " + row_id);
		$("#t" + row_id).remove();
	}

	function createDynamicFieldsArray(step1, step2) {
		//alert("Ok..");
		var campId = $('#step2').val();
		var roleId = $('#step1').val();

		var roleId = step1;
		var campId = step2;
		if (campId != 0) {
			$.ajax({
				url : "generatedExcellSheet?roleId=" + roleId + "&campId="
						+ campId,
				cache : false,
				type : 'GET',
				success : function(result) {
					alert(result);
				}
			});
		}
	}
	/* --- for Upload Achievement Field Value --- */
	function checkForm(updateAchievementValue)
	{
		if(updateAchievementValue.step1.value=="0" || updateAchievementValue.step1.value=="Role")
		{
			alert ('Please Select a Role!');
			updateAchievementValue.step1.focus();
			return false;
		}
		if(updateAchievementValue.step2.value=="0" || updateAchievementValue.step2.value=="Campaign Name")
		{
			alert ('Please Select a Campaign Name!');
			updateAchievementValue.step2.focus();
			return false;
		}
		return true;
	}
</script>
<div class="titleArea">
	<center>
		<h1>
			<strong>Upload Achievement Field Value</strong>
		</h1>
	</center>
</div>


<form class="fullForm" method="post" name="frm"
	action="saveuploadAcheivementExcellSheet" id="updateAchievementValue"
	enctype="multipart/form-data"
	onSubmit="return checkForm(updateAchievementValue)">
	<h4 style="color: red;">${ddlselectroloeId}</h4>
	<h4 style="color: red;">${ddlselectcampaignId}</h4>
	<h4 style="color: red;">${ddlselectfile}</h4>
	<h4 style="color: red;">${ddlselectfile1}</h4>

	<c:if test="${not empty sizeofmaps}">


		<%-- <c:out value="${duplicateValue}"></c:out> --%>

		<h4 style="color: red;">
			<c:out value="${sizeofmaps}"></c:out>
			Rows not Inserted due to data row mismatch
		</h4>


	</c:if>
	<c:if test="${not empty sizeofrowsinserted}">


		<%-- <c:out value="${duplicateValue}"></c:out> --%>

		<h4 style="color: red;">
			<c:out value="${sizeofrowsinserted}"></c:out>
			Rows has been Inserted Successfully
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
					<td>Select Role</td>
					<td><select name="roleId" class="select" id="step1">
							<option value="">-- Select --</option>
							<c:forEach var="roleModel" items="${roleModels}" varStatus="loop">
								<option value="${roleModel.roleId}">${roleModel.role_name}</option>
							</c:forEach>
					</select></td>
				</tr>

				<tr>
					<td>Campaign Name</td>
					<td><select name="campaignId" class="select" id="step2">
							<!-- onChange="javascript:createDynamicFields(this); "> -->
							<option value="">-- Select --</option>
							<%-- <c:forEach items="${campaignList}" var="campList">
						<option value="${campList.id}">${campList.campaignName}</option>
					</c:forEach> --%>
					</select></td>
				</tr>

				<tr>
					<td>Upload Acheivement File</td>
					<td><input id="filename1" type="file"
						placeholder="Readonly input here…" name="fileName"
						onselect="savedata();"></td>

				</tr>


				<input type="hidden" name="path" id="path" />
				<tr>
					<td id="demoExcel"><a id="downloadExcel" href=""><input
							type="button" value="Download Demo Excel File" id="excell"
							class="buttonStyle2"></a></td>


					<td><input type="submit" class="buttonStyle" value="Save"><a
						href="viewacheivementfieldvalueManagement"><input
							type="button" value="Cancel" id="backbutton" class="buttonStyle2"></a></td>
				</tr>

			</table>
		</c:otherwise>
	</c:choose>
</form>

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
										var markup = '<option value="">-- Select --</option>';
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

	function savedata() {
		localStorage.SetItem("file", getElementById("filename1").value);
	}
	window.onload = function() {
		document.getElementById("filename1").value = localStorage
				.getItem("file");
	}

	document.getElementById("filename1").onchange = function() {
		var fileName = this.value;
		var fileExtension = fileName.substr(fileName.length - 4);

		console.log(fileExtension);
		if (fileExtension != ".xls") {
			alert("That ain't no .xls file! Please Select a .xls File");
		}
	}


</script>


<%@ include file="admin-footer.jsp"%>