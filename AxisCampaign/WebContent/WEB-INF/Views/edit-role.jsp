<%@ include file="admin-header.jsp"%>

<script>
	$(document).ready(function() {
		
		<c:choose>
		<c:when test="${empty parentDeactivated}">
			$("#roleLevelId").val("<c:out value="${roleModel.roleLevelId}"/>");
		</c:when>
	
		<c:otherwise>
			$("#parentRoleLevelId").val("0");
			$("#validation1").append("<tr><td><font color=\"red\"><c:out value="${parentDeactivated}"/></font></td></tr>");
		</c:otherwise>
		</c:choose>
	

	});
	/* --- for Role Management Edit Client Side Validation --- */
	function checkForm(editroleMgt)
	{
		//Parent Role Level

		if(editroleMgt.roleLevelId.value=="0" || editroleMgt.roleLevelId.value=="Role Level")
		{
			alert ('Please Select a Role Level!');
			editroleMgt.roleLevelId.focus();
			return false;
		}
		

		if(editroleMgt.roleName.value=="" || editroleMgt.roleName.value=="Role Name")
		{
			alert ('Please Enter a Role Name!');
			editroleMgt.roleName.focus();
			return false;
		}
		return true;
	}
		
</script>
<div class="titleArea">
	<center>
		<h1>
			<strong> Update Role </strong>
		</h1>
	</center>
</div>
<form action="editRoleData" method="post" id="editroleMgt"
	onSubmit="return checkForm(editroleMgt)">
	<table id="validation1">
		<tr>
			<td><font color="red"><c:out
						value="${ddlselectForRoleLevel}" /></font></td>
		</tr>
		<tr>
			<td><font color="red"><c:out
						value="${emptyfieldForRoleName}" />
					<c:out value="${duplicateRoleName}" /></font></td>
		</tr>

	</table>
	<table align="center" class="formStyles">
		<tr>
			<td>Select Role Level</td>
			<td><select name="roleLevelId" id="roleLevelId">
					<option value="0">--- Select ---</option>
					<c:choose>
						<c:when test="${empty roleLevelModelList}">
						</c:when>
						<c:otherwise>

							<c:set var="count" value="0" scope="page" />
							<c:forEach items="${roleLevelModelList}" var="roleLevelModel">
								<option
									value="<c:out value="${roleLevelModel.parentRoleLevelId}"/>">
									<c:out value="${roleLevelModel.levelName}" />
								</option>
							</c:forEach>
						</c:otherwise>
					</c:choose>

			</select></td>
		</tr>

		<tr>
			<td><input type="hidden" name="roleId"
				value="<c:out value="${roleModel.roleId}"/>"></td>
		</tr>

		<tr>
			<td>Enter Role name</td>

			<td><input type="text" name="role_name"
				value="<c:out value="${roleModel.role_name}"/>"></td>
		</tr>

		<tr>
			<td>&nbsp;</td>
			<td><input type="submit" value="Save"><a href="viewRole"><input
					type="button" value="Cancel" id="backbutton" class="buttonStyle2"></a></td>
		</tr>
	</table>
</form>

<%@ include file="admin-footer.jsp"%>