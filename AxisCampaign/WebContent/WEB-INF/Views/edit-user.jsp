<%@ include file="admin-header.jsp"%>

<script>
	$(document).ready(
			function() {
				var roleId2 = $("#roleId1").val();
				$("#roleId").val(roleId2);
				$("#subRoleLevelId").val("<c:out value="${userModelDetails.subRoleLevelId}"/>");
				$("#userParentId").val("<c:out value="${userModelDetails.userParentId}"/>");

				$("#roleId").change(
						function() {
							
							if($("#roleId").val() == '0') {
								$("#subRoleLevelId").empty();
								$("#subRoleLevelId").append("<option value=0>--Select--</option>");
							}
							else {
								$.post("./ajaxCallForSubRoleLevelList_User", {
									roleId : $("#roleId").val()
								}, function(data, status) {
									$("#subRoleLevelId").empty();
									$("#subRoleLevelId").append("<option value=0>--Select--</option>");
									$("#subRoleLevelId").append(data);

							});
							
						}

						});

				$("#subRoleLevelId").change(
						function() {
							$.post("./ajaxCallForUserList", {
								subRoleLevelId : $("#subRoleLevelId").val()
							}, function(data, status) {
								$("#userParentId").empty();
								$("#userParentId").append(
										"<option value=0>--Select--</option>");
								$("#userParentId").append(data);

							});

						});

			});
	
	/* --------------------Edit User Client-Side Validation-------------------------*/
	
	function checkForm(editUser)
	{
		//Parent Role Level
		if(editUser.roleId.value=="0" || editUser.roleId.value=="Role ID")
		{
			alert ('Please Select a Role Level!');
			editUser.roleId.focus();
			return false;
		}

		if(editUser.subRoleLevelId.value=="0" || editUser.subRoleLevelId.value=="Role ID")
		{
			alert ('Please Select a Sub Level Role!');
			editUser.subRoleLevelId.focus();
			return false;
		}	

		if(editUser.employeeName.value=="" || editUser.employeeName.value=="Employee Name")
		{
			alert ('Please Enter Employee Name!');
			editUser.employeeName.focus();
			return false;
		}

		if(editUser.employeeNumber.value=="" || editUser.employeeNumber.value=="Employee Number")
		{
			alert ('Please Enter Employee Number!');
			editUser.employeeNumber.focus();
			return false;
		}

		if(editUser.officeAddress.value=="" || editUser.officeAddress.value=="Office Address")
		{
			alert ('Please Enter Office Address!');
			editUser.officeAddress.focus();
			return false;
		}

		if(editUser.primaryTelephoneNumber.value=="" || editUser.primaryTelephoneNumber.value=="Your Primary Phone")
		{
			alert("Please Enter Primary Phone Number") ;
			editUser.primaryTelephoneNumber.focus() ;
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
			if (!editUser.primaryTelephoneNumber.value.match(/^(?:(?:\(?(?:00|\+)([1-4]\d\d|[1-9]\d?)\)?)?[\-\.\ \\\/]?)?((?:\(?\d{1,}\)?[\-\.\ \\\/]?){0,})(?:[\-\.\ \\\/]?(?:#|ext\.?|extension|x)[\-\.\ \\\/]?(\d+))?$/i))
			{
				alert("Please Enter a valid Phone Number") ;
				editUser.primaryTelephoneNumber.focus() ;
				editUser.primaryTelephoneNumber.value = '';
				return false;
			}

		if(editUser.secondaryTelephoneNumber.value=="" || editUser.secondaryTelephoneNumber.value=="Your Secondary Phone")
		{
			alert("Please Enter Secondary Phone Number") ;
			editUser.secondaryTelephoneNumber.focus() ;
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
			if (!editUser.secondaryTelephoneNumber.value.match(/^(?:(?:\(?(?:00|\+)([1-4]\d\d|[1-9]\d?)\)?)?[\-\.\ \\\/]?)?((?:\(?\d{1,}\)?[\-\.\ \\\/]?){0,})(?:[\-\.\ \\\/]?(?:#|ext\.?|extension|x)[\-\.\ \\\/]?(\d+))?$/i))
			{
				alert("Please Enter a valid Phone Number") ;
				editUser.secondaryTelephoneNumber.focus() ;
				editUser.secondaryTelephoneNumber.value = '';
				return false;
			}

		if(editUser.emailAddress.value=="" || editUser.emailAddress.value=="Your Email Address")
		{
			alert("Please Enter Your Email ID!") ;
			editUser.emailAddress.focus() ;
			return false;
		}
			if (!editUser.emailAddress.value.match(/^[\w\.\-]+@([\w\-]+\.)+[a-zA-Z]+$/))
			{
				alert("Please enter a valid E-mail ID!");
				editUser.emailAddress.focus();
				editUser.emailAddress.value = '';
				return false;
			}

		if(editUser.txtarea.value=="Your Message")
		{
			alert ('Please Enter Your Message!');
			editUser.txtarea.focus();
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
<h4 style="color: red;">${userRoleNotnull}</h4>
<h4 style="color: red;">${userSubRoleNotnull}</h4>
<h4 style="color: red;">${userEmployeeNumberNotnull}</h4>
<h4 style="color: red;">${userNameNotnull}</h4>
<h4 style="color: red;">${userEmailNotnull}</h4>
<h4 style="color: red;">${employeeNameNotnull}</h4>
<h4 style="color: red;">${userPrimaryTelephoneNotnull}</h4>
<h4 style="color: red;">${userEmployeeNumberAlreadypresent}</h4>
<h4 style="color: red;">${userEmailAlreadyPresent}</h4>
<h4 style="color: red;">${userPrimaryTelephoneAlreadyPresent}</h4>
<h4 style="color: red;">${usernameAlreadypresent}</h4>
<h4 style="color: red;">${phoneFormatNotCorrect}</h4>
<h4 style="color: red;">${emailFormatNotCorrect}</h4>

<div class="titleArea">
	<center>
		<h1>
			<strong>Update User</strong>
		</h1>
	</center>
</div>
<form action="editUserData" method="post" enctype="multipart/form-data"
	id="editUser" onSubmit="return checkForm(editUser)">
	<input type="hidden" value="${userModelDetails.roleId}" id="roleId1" />

	<table align="center" class="formStyles">
		<tr>
			<td>Select Role</td>
			<c:choose>
				<c:when test="${empty roleModelListDDL}">
				</c:when>

				<c:otherwise>
					<td><select name="roleId" id="roleId">
							<option value="0">--- Select ---</option>
							<c:forEach items="${roleModelListDDL}" var="roleModelDDL">
								<option value="<c:out value="${roleModelDDL.roleId}"/>">
									<c:out value="${roleModelDDL.role_name}" />
								</option>
							</c:forEach>
					</select></td>
				</c:otherwise>
			</c:choose>
		</tr>

		<tr>
			<td>Select Sub Level Role</td>
			<c:choose>
				<c:when test="${empty roleModelListDDL}">
				</c:when>

				<c:otherwise>
					<td><select name="subRoleLevelId" id="subRoleLevelId">
							<option value="0">--- Select ---</option>
							<c:forEach items="${subRoleLevelModelList}"
								var="subRoleLevelModel">
								<option
									value="<c:out value="${subRoleLevelModel.subRoleLevelId}"/>">
									<c:out value="${subRoleLevelModel.description}" />
								</option>
							</c:forEach>

					</select></td>
				</c:otherwise>
			</c:choose>
		</tr>

		<%-- <c:choose>
				<c:when test="${empty roleModelListDDL}">
				</c:when>

				<c:otherwise>
					<select name="userParentId" id="userParentId">
						<option value="0">--- Select ---</option>
						<c:forEach items="${userModelListDDL}" var="userModel">
							<option value="<c:out value="${userModel.userId}"/>"><c:out
									value="${userModel.employeeName}" /></option>
						</c:forEach>
					</select>
					</td>
				</c:otherwise>
			</c:choose>
 --%>
		<tr>
			<td colspan="2"><input type="hidden" name="userParentId"
				id="userParentId"></td>
		</tr>
		<tr>
			<td>Employee Name</td>
			<td><input type="text" name="employeeName" id="employeeName"
				value="${userModelDetails.employeeName}"></td>
		</tr>
		<tr>
			<td>User Name</td>
			<td><input type="text" name="userName" id="userName"
				value="${userModelDetails.userName}"></td>
		</tr>
		<tr>
			<td>Password</td>
			<td><input type="text" name="password" id="password"
				value="${userModelDetails.password}"></td>
		</tr>
		<tr>
			<td>Employee Number</td>
			<td><input type="text" name="employeeNumber" id="employeeNumber"
				value="${userModelDetails.employeeNumber}"></td>
		</tr>

		<!-- <tr>
			<td>Enter User Details</td>
			<td><input type="text" name="about" id="about"></td>
		</tr> -->
		<tr>
			<td>Office Address</td>
			<td><input type="text" name="officeAddress" id="officeAddress"
				value="${userModelDetails.officeAddress}"></td>
		</tr>
		<tr>
			<td>Primary Telephone No</td>
			<td><input type="text" name="primaryTelephoneNumber"
				id="primaryTelephoneNumber"
				value="${userModelDetails.primaryTelephoneNumber}"></td>
		</tr>
		<tr>
			<td>Secondary Telephone No</td>
			<td><input type="text" name="secondaryTelephoneNumber"
				id="secondaryTelephoneNumber"
				value="${userModelDetails.secondaryTelephoneNumber}"></td>
		</tr>
		<tr>
			<td>Email Address</td>
			<td><input type="text" name="emailAddress" id="emailAddress"
				value="${userModelDetails.emailAddress}"></td>
		<tr>
			<td colspan="2"><input type="hidden" name="userId" id="userId"
				value="${userModelDetails.userId}"></td>
		</tr>
		<tr>
			<td>About</td>
			<td><textarea id="txtarea" class="normal" rows="3"
					placeholder="Type your message..." name="about" id="about">${userModelDetails.about}</textarea>

			</td>
		</tr>

		<tr>
			<td>Upload Image</td>
			<td><input class="buttonStyle" type="file" readonly
				placeholder="Readonly input here…" name="profilePictureFile">
			</td>
		</tr>
		<tr>
			<td colspan="2"><input type="hidden" name="profilePicture"
				value="${fn:escapeXml(userModelDetails.profilePicture)}"></td>
		<tr>
		<tr>
			<td>&nbsp;</td>
			<td><input type="submit" value="Save"><a href="viewUser"><input
					type="button" value="Cancel" id="backbutton" class="buttonStyle2"></a></td>
		</tr>
	</table>

</form>

<%@ include file="admin-footer.jsp"%>