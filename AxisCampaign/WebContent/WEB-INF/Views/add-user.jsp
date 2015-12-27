<%@ include file="admin-header.jsp"%>

<script>
	$(document)
			.ready(
					function() {

						<c:choose>
						<c:when test="${not empty userModel}">
						$("#roleId")
								.val("<c:out value="${userModel.roleId}"/>");

						if ("${userModel.roleId}" == '0')
							$('#subRoleLevelDDL').hide();
						else {
							$('#subRoleLevelDDL').show();
							$('#subRoleLevelId').val(
									"${userModel.subRoleLevelId}");
						}

						var roleId = $('#roleId').val();
						if (roleId == 0)
							$('#role_error').show();
						else
							$('#role_error').hide();

						</c:when>
						<c:otherwise>
						$('#subrole_error').hide();
						$('#role_error').hide();
						$('#subRoleLevelDDL').hide();
						</c:otherwise>
						</c:choose>

						/* $('#insertUser').submit(function() {
							return true;

						}); */

						$("#roleId")
								.change(
										function() {
											$
													.post(
															"./ajaxCallForSubRoleLevelList_User",
															{
																roleId : $(
																		"#roleId")
																		.val()
															},
															function(data,
																	status) {
																if (data.trim() == 'No Sub Role Levels Present') {
																	$("#d")
																			.html(
																					data);
																} else {
																	$(
																			'#subRoleLevelDDL')
																			.show();
																	$(
																			"#subRoleLevelId")
																			.empty();
																	$(
																			"#subRoleLevelId")
																			.append(
																					"<option value=0>--Select--</option>");
																	$(
																			"#subRoleLevelId")
																			.append(
																					data);
																}

															});

										});

						$("#subRoleLevelId").change(function() {
							$.post("./ajaxCallForUserList", {
								subRoleLevelId : $("#subRoleLevelId").val()
							}, function(data, status) {
								$("#userParentId").val(data);
							});

						});

					});
	
	/* --- for User Management --- */
	function checkForm(insertUser)
	{
		//Parent Role Level
		if(insertUser.roleId.value=="0" || insertUser.roleId.value=="Role ID")
		{
			alert ('Please Select a Role Level!');
			insertUser.roleId.focus();
			return false;
		}

		if(insertUser.subRoleLevelId.value=="0" || insertUser.subRoleLevelId.value=="Role ID")
		{
			alert ('Please Select a Sub Level Role!');
			insertUser.subRoleLevelId.focus();
			return false;
		}	

		if(insertUser.employeeName.value=="" || insertUser.employeeName.value=="Employee Name")
		{
			alert ('Please Enter Employee Name!');
			insertUser.employeeName.focus();
			return false;
		}

		if(insertUser.employeeNumber.value=="" || insertUser.employeeNumber.value=="Employee Number")
		{
			alert ('Please Enter Employee Number!');
			insertUser.employeeNumber.focus();
			return false;
		}

		/*if(insertUser.officeAddress.value=="" || insertUser.officeAddress.value=="Office Address")
		{
			alert ('Please Enter Office Address!');
			insertUser.officeAddress.focus();
			return false;
		}*/

		if(insertUser.primaryTelephoneNumber.value=="" || insertUser.primaryTelephoneNumber.value=="Your Primary Phone")
		{
			alert("Please Enter Primary Phone Number") ;
			insertUser.primaryTelephoneNumber.focus() ;
			return false;
		}
			/*if(isNaN(roleLvelMgt.phone.value)==true)
			{
				alert("Please Enter a valid Phone Number") ;
				roleLvelMgt.phone.focus() ;
				roleLvelMgt.phone.value = '';
				return false;
			}*/
			//if (!roleLvelMgt.phone.value.match(/^\+?([0-9]{2})\)?[-. ]?([0-9]{4})[-. ]?([0-9]{4})$/))
			if (!insertUser.primaryTelephoneNumber.value.match(/^(?:(?:\(?(?:00|\+)([1-4]\d\d|[1-9]\d?)\)?)?[\-\.\ \\\/]?)?((?:\(?\d{1,}\)?[\-\.\ \\\/]?){0,})(?:[\-\.\ \\\/]?(?:#|ext\.?|extension|x)[\-\.\ \\\/]?(\d+))?$/i))
			{
				alert("Please Enter a valid Phone Number") ;
				insertUser.primaryTelephoneNumber.focus() ;
				insertUser.primaryTelephoneNumber.value = '';
				return false;
			}

			/*if(insertUser.secondaryTelephoneNumber.value=="" || insertUser.secondaryTelephoneNumber.value=="Your Secondary Phone")
		{
			alert("Please Enter Secondary Phone Number") ;
			insertUser.secondaryTelephoneNumber.focus() ;
			return false;
		}
			if(isNaN(roleLvelMgt.phone.value)==true)
			{
				alert("Please Enter a valid Phone Number") ;
				roleLvelMgt.phone.focus() ;
				roleLvelMgt.phone.value = '';
				return false;
			}
			//if (!roleLvelMgt.phone.value.match(/^\+?([0-9]{2})\)?[-. ]?([0-9]{4})[-. ]?([0-9]{4})$/))
			if (!insertUser.secondaryTelephoneNumber.value.match(/^(?:(?:\(?(?:00|\+)([1-4]\d\d|[1-9]\d?)\)?)?[\-\.\ \\\/]?)?((?:\(?\d{1,}\)?[\-\.\ \\\/]?){0,})(?:[\-\.\ \\\/]?(?:#|ext\.?|extension|x)[\-\.\ \\\/]?(\d+))?$/i))
			{
				alert("Please Enter a valid Phone Number") ;
				insertUser.secondaryTelephoneNumber.focus() ;
				insertUser.secondaryTelephoneNumber.value = '';
				return false;
			}*/

		if(insertUser.emailAddress.value=="" || insertUser.emailAddress.value=="Your Email Address")
		{
			alert("Please Enter Your Email ID!") ;
			insertUser.emailAddress.focus() ;
			return false;
		}
			if (!insertUser.emailAddress.value.match(/^[\w\.\-]+@([\w\-]+\.)+[a-zA-Z]+$/))
			{
				alert("Please enter a valid E-mail ID!");
				insertUser.emailAddress.focus();
				insertUser.emailAddress.value = '';
				return false;
			}

		if(insertUser.txtarea.value=="Your Message")
		{
			alert ('Please Enter Your Message!');
			insertUser.txtarea.focus();
			return false;
		}
		return true;
	}
</script>

<style>
.error_show {
	display: none;
	margin-left: 10px;
}

.error_show {
	color: red;
	margin-left: 10px;
	display: show;
}
</style>

<div class="titleArea">
	<center>
		<h1>
			<strong>Add User</strong>
		</h1>
	</center>
</div>

<c:if test="${not empty userRoleNotnull}">
	<h4 class="error" style="color: red;">
		<c:out value="${userRoleNotnull}"></c:out>
	</h4>
</c:if>

<c:if test="${not empty userSubRoleNotnull}">
	<h4 class="error" style="color: red;">
		<c:out value="${userSubRoleNotnull}"></c:out>
	</h4>
</c:if>

<c:if test="${not empty userEmployeeNumberNotnull}">
	<h4 class="error" style="color: red;">
		<c:out value="${userEmployeeNumberNotnull}"></c:out>
	</h4>
</c:if>

<c:if test="${not empty userNameNotnull}">
	<h4 class="error" style="color: red;">
		<c:out value="${userNameNotnull}"></c:out>
	</h4>
</c:if>

<c:if test="${not empty userEmailNotnull}">
	<h4 class="error" style="color: red;">
		<c:out value="${userEmailNotnull}"></c:out>
	</h4>
</c:if>

<c:if test="${not empty employeeNameNotnull}">
	<h4 class="error" style="color: red;">
		<c:out value="${employeeNameNotnull}"></c:out>
	</h4>
</c:if>

<c:if test="${not empty userPrimaryTelephoneNotnull}">
	<h4 class="error" style="color: red;">
		<c:out value="${userPrimaryTelephoneNotnull}"></c:out>
	</h4>
</c:if>

<c:if test="${not empty userEmployeeNumberAlreadypresent}">
	<h4 class="error" style="color: red;">
		<c:out value="${userEmployeeNumberAlreadypresent}"></c:out>
	</h4>
</c:if>

<c:if test="${not empty userEmailAlreadyPresent}">
	<h4 class="error" style="color: red;">
		<c:out value="${userEmailAlreadyPresent}"></c:out>
	</h4>
</c:if>

<c:if test="${not empty userPrimaryTelephoneAlreadyPresent}">
	<h4 class="error" style="color: red;">
		<c:out value="${userPrimaryTelephoneAlreadyPresent}"></c:out>
	</h4>
</c:if>

<c:if test="${not empty usernameAlreadypresent}">
	<h4 class="error" style="color: red;">
		<c:out value="${usernameAlreadypresent}"></c:out>
	</h4>
</c:if>

<c:if test="${not empty phoneFormatNotCorrect}">
	<h4 class="error" style="color: red;">
		<c:out value="${phoneFormatNotCorrect}"></c:out>
	</h4>
</c:if>

<c:if test="${not empty emailFormatNotCorrect}">
	<h4 class="error" style="color: red;">
		<c:out value="${emailFormatNotCorrect}"></c:out>
	</h4>
</c:if>



<div id="d"></div>


<form id="insertUser" action="insertUserData" method="post"
	enctype="multipart/form-data" onSubmit="return checkForm(insertUser)">

	<c:choose>
		<c:when test="${not empty NoActiveRoleFound}">
			<h3>
				<font color="red"><c:out value="${NoActiveRoleFound}"></c:out></font>
			</h3>
		</c:when>
		<c:otherwise>
			<c:choose>
				<c:when test="${empty roleModelListDDL}">
				</c:when>
				<c:otherwise>
					<table align="center" class="formStyles">
						<tr>
							<td>Select Role</td>
							<td><select name="roleId" id="roleId">
									<option value="0">--- Select ---</option>
									<c:forEach items="${roleModelListDDL}" var="roleModelDDL">
										<option value="<c:out value="${roleModelDDL.roleId}"/>">
											<c:out value="${roleModelDDL.role_name}" />
										</option>
									</c:forEach>
							</select></td>
						</tr>
						<tr id="subRoleLevelDDL">
							<td>Select Sub Level Role</td>
							<td><select name="subRoleLevelId" id="subRoleLevelId">
									<option value="0">--Select--</option>

									<c:choose>
										<c:when test="${not empty subRoleLevelList}">
											<c:forEach items="${subRoleLevelList}"
												var="subRoleLevelModelDDL">

												<option
													value="<c:out value="${subRoleLevelModelDDL.subRoleLevelId}"/>">
													<c:out value="${subRoleLevelModelDDL.description}" />
												</option>
											</c:forEach>
										</c:when>

										<c:otherwise>
										</c:otherwise>
									</c:choose>

							</select></td>
						</tr>
						<!-- <tr><td colspan="2"><select name="userParentId" id="userParentId">
						</select>
					</td>
				</tr> -->
						<tr>
							<td colspan="2"><input type="hidden" name="userParentId"
								id="userParentId"
								value="${fn:escapeXml(userModel.userParentId)}"></td>
						</tr>
						<tr>
							<td>Employee Name</td>
							<td><input type="text" name="employeeName"
								value="${fn:escapeXml(userModel.employeeName)}"></td>
						</tr>
						<%-- <tr>
					<td>User Name</td>
					<td><input type="text" name="userName"
						value="${fn:escapeXml(userModel.userName)}"></td>
				</tr>
				<tr>
					<td>Password</td>
					<td><input type="password" name="password"
						value="${fn:escapeXml(userModel.password)}"></td>
				</tr> --%>
						<tr>
							<td>Employee Number</td>
							<td><input type="text" name="employeeNumber"
								value="${fn:escapeXml(userModel.employeeNumber)}"></td>
						</tr>

						<tr>
							<td>Office Address</td>
							<td><input type="text" name="officeAddress"
								value="${fn:escapeXml(userModel.officeAddress)}"></td>
						</tr>
						<tr>
							<td>Primary Telephone No</td>
							<td><input type="text" name="primaryTelephoneNumber"
								value="${fn:escapeXml(userModel.primaryTelephoneNumber)}"></td>
						</tr>
						<tr>
							<td>Secondary Telephone No</td>
							<td><input type="text" name="secondaryTelephoneNumber"
								value="${fn:escapeXml(userModel.secondaryTelephoneNumber)}"></td>
						</tr>
						<tr>
							<td>Email Address</td>
							<td><input type="text" name="emailAddress"
								value="${fn:escapeXml(userModel.emailAddress)}"></td>
						</tr>

						<tr>
							<td>About</td>
							<td><textarea id="txtarea" class="normal" rows="3"
									placeholder="Type your message..." name="about">${userModel.about}</textarea>

							</td>
						</tr>

						<tr>
							<td>Upload Image</td>
							<td><input type="file" readonly
								placeholder="Readonly input here…" name="profilePictureFile"
								class="buttonStyle"> <%-- <input type="text" name="profilePicture"
						value="${fn:escapeXml(userModel.profilePicture)}">
						 --%></td>
						</tr>

						<tr>
							<td>&nbsp;</td>
							<td><input type="submit" value="Save"><a
								href="viewUser"><input type="button" value="Cancel"
									id="backbutton" class="buttonStyle2"></a></td>
						</tr>
					</table>
				</c:otherwise>
			</c:choose>
		</c:otherwise>
	</c:choose>
</form>

<%@ include file="admin-footer.jsp"%>