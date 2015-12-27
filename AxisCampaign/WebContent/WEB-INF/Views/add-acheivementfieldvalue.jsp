<%@ include file="admin-header.jsp"%>

<script type="text/javascript" language="javascript">
	function createDynamicFields() {
		//alert("Ok..");
		var campId = $('#step2').val();
		var roleId = $('#step1').val();

		if (campId != 0) {
			$
					.ajax({
						url : "createDynamicFieldforAcheivementField?roleId="
								+ roleId + "&campId=" + campId,
						cache : false,
						type : 'GET',
						success : function(result) {
							var data = jQuery.parseJSON(result);
							var len = data.length;
							var html = '';
							var hidden = '';
							var info = '';
							/* alert("campid: "+data[len-1]); */
							for (var i = 0; i < len; ++i) {
								// html += '<option value="' + data[i]['value'] + '">' + data[i]['label'] + '</option>';
								html += "<tr><td><label class=\"staticField\">"
										+ data[i].fieldName
										+ "\</label></td><td><div class=\"leftInput\"><input class=\"normal\" id=\"fieldName\" type=\"text\" name=\"fieldValue_array\"></div></td></tr>";
								hidden += "<tr><td style=\"padding:0; font-size:0; line-height:0; border-width:0;\"><div class=\"formGroup\"><div class=\"leftInput\"><input class=\"normal\" id=\"acheivementId\" type=\"hidden\" name=\"acheivement_id\" value="+data[i].acheivementId+"></div></div></td></tr>";
							}
							/*  hidden += "<div class=\"formGroup\"><div class=\"leftInput\"><input class=\"normal\" id=\"acheivementId\" type=\"text\" name=\"acheivementId\" value="+data[len-1]+"></div></div>"; */
							info += html + hidden;
							$('#maindiv').html(info);
						},
						error : function(e) {
							//alert("Error: " + e);
						}
					});
		}
	}

	function createDynamicFieldsArray(step1, step2) {
		//alert("Ok..");
		/* var campId = $('#step2').val();
		var roleId = $('#step1').val(); */

		var roleId = step1;
		var campId = step2;
		if (campId != 0) {
			$
					.ajax({
						url : "createDynamicFieldforAcheivementField?roleId="
								+ roleId + "&campId=" + campId,
						cache : false,
						type : 'GET',
						success : function(result) {
							var data = jQuery.parseJSON(result);
							var len = data.length;
							var html = '';
							var hidden = '';
							var info = '';
							/* ${fn:escapeXml(acheivementModel.updatefieldName)} */
							/* alert("campid: "+data[len-1]); */
							var field_array = new Array();

							<c:forEach items="${a.fieldValue_array}" var="temp">
							field_array.push("${fn:escapeXml(temp)}");
							</c:forEach>

							for (var i = 0; i < field_array.length; ++i) {
								// html += '<option value="' + data[i]['value'] + '">' + data[i]['label'] + '</option>';
								html += "<tr><td><div class=\"formGroup\"><label class=\"staticField\">"
										+ data[i].fieldName
										+ "\</label></td><td><div class=\"leftInput\"><input class=\"normal\" id=\"fieldName\" type=\"text\" name=\"fieldValue_array\" value="+field_array[i]+"></div></div></td></tr>";
								hidden += "<tr><td><div class=\"formGroup\"><div class=\"leftInput\"><input class=\"normal\" id=\"acheivementId\" type=\"hidden\" name=\"acheivement_id\" value="+data[i].acheivementId+"></div></div></td></tr>";
							}
							/*  hidden += "<div class=\"formGroup\"><div class=\"leftInput\"><input class=\"normal\" id=\"acheivementId\" type=\"text\" name=\"acheivementId\" value="+data[len-1]+"></div></div>"; */
							info += html + hidden;
							$('#maindiv').html(info);
						},
						error : function(e) {
							//alert("Error: " + e);
						}
					});
		}
	}

	//alert(campid);
	/* if(campid != 0){
	$.ajax({			
		type: "POST",
		url: "createDynamicFields",
		data: "campid="+campid,			
		dataType: "json", 
		success: function(data){				
			//alert(data[0]);
			var len = data.length;
			var html = '';
			var hidden = '';
			var info= '';
			alert("campid: "+data[len-1]);
			for (var i = 0; i < len-1; ++i) {
			   // html += '<option value="' + data[i]['value'] + '">' + data[i]['label'] + '</option>';
			   html += "<div class=\"formGroup\"><label class=\"staticField\">"+data[i]+"\</label><div class=\"leftInput\"><input class=\"normal\" id=\"fieldName\" type=\"text\" name=\"fieldName_array\"></div></div>";
			}  
			hidden += "<div class=\"formGroup\"><div class=\"leftInput\"><input class=\"normal\" id=\"campId\" type=\"hidden\" name=\"campId\" value="+data[len-1]+"></div></div>";
			info += html+hidden;
			$('#maindiv').html(info);
		},
		error: function(e){
			alert("Error: "+e);
		}
	});
	}
	else{
		$('#maindiv').html('');
	}	
	} */
</script>



<script type="text/javascript" language="javascript">
	$(document)
			.ready(
					function() {
						// $("#step1").val("<c:out value="${a.roleId}"/>");
						//alert("<c:out value="${a.roleId}"/>");
						$("#step1").val("<c:out value="${a.roleId}"/>");
						// var cars="<c:out value="${a.fieldValue_array}"/>";

						// console.log(cars.length);
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
											//console.log(data[x].id);
											//                                                alert(data[x].id);
										}
										$('#step2').html(markup).show();

									},
									error : function(reponse) {
									}
								});

						$("#step2").val("<c:out value="${a.campaignId}"/>");
						// alert("<c:out value="${a.campaignId}"/>");

						createDynamicFieldsArray("${a.roleId}",
								"${a.campaignId}");

						var row_id = 0;

						$("#add_more")
								.click(
										function() {
											//alert(row_id);
											//alert("Call on the add more jsp page")
											row_id = row_id + 1;
											//$("#t").append("<tr id="+row_id+"><td><div class=\"formGroup\"><label class=\"staticField\">Field Name</label><input class=\"normal\" id=\"fieldName\" type=\"text\" name=\"fieldName\"></div></td><td><input type=\"button\" value=\"Cancel\" onclick=\"del("+row_id+")\"></td></tr>");
											//$("#d"+row_id).html("<div class=\"formGroup\"><label class=\"staticField\">Field Name</label><div class=\"leftInput\"><input class=\"normal\" id=\"fieldName\" type=\"text\" name=\"fieldName_array\"><input class=\"normal\" type=\"button\" value=\"Cancel\" onclick=\"del("+row_id+")\"></div></div>");
											//$("#maindiv").find(':nth-child(1)').html("<div class=\"formGroup\"><label class=\"staticField\">Field Name</label><div class=\"leftInput\"><input class=\"normal\" id=\"fieldName\" type=\"text\" name=\"fieldName_array\"><input class=\"normal\" type=\"button\" value=\"Cancel\" onclick=\"del("+row_id+")\"></div></div>");
											$("#maindiv").append(
													"<tr id=t"+row_id+"></tr>");
											//alert("div id created t"+row_id);

											//var temp_id=$("#maindiv").find(":nth-child("+row_id+")").attr('id');
											//alert("id :: "+temp_id);
											$("#t" + row_id)
													.html(
															"<td><div class=\"formGroup\"><label class=\"staticField\">Field Name</label></td><td><div><input class=\"normal\" id=\"fieldName\" type=\"text\" name=\"fieldName_array\"><br><input class=\"normal\" type=\"button\" value=\"Cancel\" onclick=\"del("
																	+ row_id
																	+ ")\"></div></div></td>");
											//alert("done");
											//alert(row_id);
										});

						//var form = $('#saveAchievementForm');

						$('#saveAchievementButton').on('click', function() {

							console.log('here');

							validation();
						});

					});

	function del(row_id) {
		//alert("here is "+row_id);
		$("#t" + row_id).remove();
	}

	function validation() {
		$.each($('.normal'), function() {
			//var fieldValue = this.val();
			console.log(this);
			/* if(isBlank(fieldValue)){
				//pick your message showing element and show
				
				console.log('gym');
			} */
		});
	}

	function isBlank(string) {
		if (string == null || string == '') {
			return true;
		}

		return false;
	}
	/* --- for Achievement Field Value --- */
	function checkForm(saveAchievementForm)
	{
		if(saveAchievementForm.step1.value=="" || saveAchievementForm.step1.value=="Role")
		{
			alert ('Please Select a Role!');
			saveAchievementForm.step1.focus();
			return false;
		}
		if(saveAchievementForm.step2.value=="" || saveAchievementForm.step2.value=="Campaign Name")
		{
			alert ('Please Select a Campaign Name!');
			saveAchievementForm.step2.focus();
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


		<c:out value="${ddlselectvalidFieldValue}"></c:out>
		<c:if test="${not empty duplicateValue}">


			<%-- <c:out value="${duplicateValue}"></c:out> --%>

			<h4 style="color: red;">${duplicateValue}</h4>


		</c:if>
		<div class="titleArea">
			<center>
				<h1>
					<strong>Add Achievement Field Value</strong>
				</h1>
			</center>
		</div>
		<form id="saveAchievementForm" class="fullForm" method="post"
			name="frm" action="saveAcheivementFieldValue"
			onSubmit="return checkForm(saveAchievementForm)">

			<%-- <c:out value="${ddlselectvalidFieldValue}"></c:out> --%>

			<h4 style="color: red;">${ddlselectroloeId}</h4>
			<h4 style="color: red;">${ddlselectcampaignId}</h4>
			<h4 style="color: red;">${ddlselectfieldnamearray}</h4>
			<h4 style="color: red;">${ddlselectfieldnamearray8}</h4>
			<h4 style="color: red;">${ddlselectfieldnamearray9}</h4>
			<%-- <c:out value="${ddlselectroloeId}"></c:out>
		<c:out value="${ddlselectcampaignId}"></c:out>
		<c:out value="${ddlselectfieldnamearray}"></c:out> --%>


			<table align="center" class="formStyles">
				<tr>
					<td>Select Role</td>
					<td><select name="roleId" class="select" id="step1">
							<option value=>-- Select --</option>
							<c:forEach var="roleModel" items="${roleModels}" varStatus="loop">
								<option value="${roleModel.roleId}">${roleModel.role_name}</option>
							</c:forEach>
					</select></td>
				</tr>

				<tr>
					<td>Campaign Name</td>
					<td><select name="campaignId" class="select" id="step2"
						onblur="javascript:createDynamicFields(this);"
						onChange="javascript:createDynamicFields(this); ">
							<option value=>-- Select --</option>
							<%-- <c:forEach items="${campaignList}" var="campList">
						<option value="${campList.id}">${campList.campaignName}</option>
					</c:forEach> --%>
					</select></td>
				</tr>
				<tr>
					<td colspan="2" style="padding: 0; width: 100%;">
						<table id="maindiv" cellpadding="0" cellspacing="0" width="100%"
							class="formStyles"></table>
					</td>
				</tr>

				<!-- <tr>
			<td>
				<div id="maindiv"></div>

			</td>
		</tr> -->
				<tr>
					<td>&nbsp;</td>
					<td><input type="submit" class="buttonStyle" value="Save"><a
						href="viewacheivementfieldvalueManagement"><input
							type="button" value="Cancel" id="backbutton" class="buttonStyle2"></a></td>
				</tr>
			</table>
		</form>

	</c:otherwise>
</c:choose>

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

											markup += "<option value=" + data[x].campaignId + ">"
													+ data[x].campaignName
													+ "</option>";
											// console.log(data[x].id);
											//                                                alert(data[x].id);
										}
										$('#step2').html(markup).show();
										createDynamicFields();

									},
									error : function(reponse) {
									}
								});
					});
</script>


<%@ include file="admin-footer.jsp"%>