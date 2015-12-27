<%@ include file="admin-header.jsp"%>

<script>
	$(document).ready(
			function() {

				<c:choose>
				<c:when test="${empty roleModel}">
				</c:when>

				<c:otherwise>
				$("#roleLevelId").val(
						"<c:out value="${roleModel.roleLevelId}"/>");
				</c:otherwise>

				</c:choose>

				$("#roleLevelId").change(
						function() {
							$.post("./ajaxCallForSubRoleLevelList", {
								roleLevelId : $("#roleLevelId").val()
							}, function(data, status) {
								$("#subRoleLevelParentId").empty();
								$("#subRoleLevelParentId").append(
										"<option value=0>--Select--</option>");
								$("#subRoleLevelParentId").append(data);

							});

						});
			});
	/* --- for Role Management --- */
	function checkForm(roleMgt)
	{
		//Parent Role Level

		if(roleMgt.roleLevelId.value=="0" || roleMgt.roleLevelId.value=="Role Level")
		{
			alert ('Please Select a Role Level!');
			roleMgt.roleLevelId.focus();
			return false;
		}
		

		if(roleMgt.roleName.value=="" || roleMgt.roleName.value=="Role Name")
		{
			alert ('Please Enter a Role Name!');
			roleMgt.roleName.focus();
			return false;
		}
		return true;
	}
</script>
<div class="titleArea">
	<center>
		<h1>
			<strong>Add Role</strong>
		</h1>
	</center>
</div>

<form action="insertRoleData" method="post" id="roleLvelMgt"
	onSubmit="return checkForm(roleLvelMgt)">
	<c:out value="${generalException}" />

	<c:choose>
		<c:when test="${empty roleLevelModelList}">
			<font color="red"><c:out value="${roleLevelListNotFound}"></c:out></font>
		</c:when>

		<c:otherwise>

			<table>
				<tr>
					<td><font color="red"><c:out
								value="${ddlselectForRoleLevel}" /></font></td>
				</tr>
				<tr>
					<td><font color="red"><c:out
								value="${emptyfieldForRoleName}" /> <c:out
								value="${duplicateRoleName}" /></font></td>
				</tr>

			</table>

			<table align="center" class="formStyles">
				<tr>
					<td>Select Role Level</td>
					<td><select name="roleLevelId" id="roleLevelId">
							<option value="0">--- Select ---</option>
							<c:forEach items="${roleLevelModelList}" var="roleLevelModel">
								<option
									value="<c:out value="${roleLevelModel.parentRoleLevelId}"/>">
									<c:out value="${roleLevelModel.levelName}" />
								</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td>Enter Role Name</td>
					<td><input type="text" name="role_name"
						value="${roleModel.role_name}"></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><input type="submit" value="Save"><a
						href="viewRole"><input type="button" value="Cancel"
							id="backbutton" class="buttonStyle2"></a></td>
				</tr>
			</table>

		</c:otherwise>
	</c:choose>
</form>


<%@ include file="admin-footer.jsp"%>