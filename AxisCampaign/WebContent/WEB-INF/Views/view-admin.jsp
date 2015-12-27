<%@ include file="admin-header.jsp"%>

<body>
	<div class="titleArea">
		<center>
			<h1>
				<strong>Update Admin Profile</strong>
			</h1>
		</center>
	</div>
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
	<form action="updateAdminProfile" method="post">

		<table align="center" class="formStyles">
			<input type="hidden" name="userId"
				value="${fn:escapeXml(userModel.userId)}">
			<tr>
				<td>Employee Name</td>
				<td><input type="text" name="employeeName"
					value="${fn:escapeXml(userModel.employeeName)}"></td>
			</tr>

			<tr>
				<td>Employee Number</td>
				<td><input type="text" name="employeeNumber"
					value="${fn:escapeXml(userModel.employeeNumber)}"></td>
			</tr>

			<tr>
				<td>Password</td>
				<td><input type="text" name="password"
					value="${fn:escapeXml(userModel.password)}"></td>
			</tr>

			<tr>
				<td>Primary Telephone No</td>
				<td><input type="text" name="primaryTelephoneNumber"
					value="${fn:escapeXml(userModel.primaryTelephoneNumber)}"></td>
			</tr>

			<tr>
				<td>Email Address</td>
				<td><input type="text" name="emailAddress"
					value="${fn:escapeXml(userModel.emailAddress)}"></td>
			</tr>

			<tr>
				<td>&nbsp;</td>
				<td><input type="submit" value="Save"><a
					href="viewUser"><input type="button" value="Cancel"
						id="backbutton" class="buttonStyle2"></a></td>
			</tr>
		</table>

	</form>
	<%@ include file="admin-footer.jsp"%>