<%@ include file="admin-header.jsp"%>
<script>
	$(document).ready(function() {
		
		<c:choose>
			<c:when test="${empty parentDeactivated}">
				$("#parentRoleLevelId").val("<c:out value="${roleLevelModel.parentRoleLevelId}"/>");
			</c:when>
		
			<c:otherwise>
				$("#parentRoleLevelId").val("0");
				$("#validation1").append("<tr><td><font color=\"red\"><c:out value="${parentDeactivated}"/></font></td></tr>");
			</c:otherwise>
		</c:choose>
		
				

});
	/*-------------------------------Edit Role Level Client-Side Validation------------------------------------*/
	function checkForm(editroleLvelMgt)
	{
		//Parent Role Level

		if(editroleLvelMgt.parentRoleLevelId.value=="0" || editroleLvelMgt.parentRoleLevelId.value=="Your Name")
		{
			alert ('Please Select a Parent Role Level!');
			editroleLvelMgt.parentRoleLevelId.focus();
			return false;
		}
		

		if(editroleLvelMgt.levelName.value=="" || editroleLvelMgt.levelName.value=="Your Name")
		{
			alert ('Please Enter a Name!');
			editroleLvelMgt.levelName.focus();
			return false;
		}
		return true;
	}
	
	
	
	
	
	
	
</script>
<div class="titleArea">
	<center>
		<h1>
			<strong>Update Role Level</strong>
		</h1>
	</center>
</div>
<form action="editRoleLevelData" method="post" id="editroleLvelMgt"
	onSubmit="return checkForm(editroleLvelMgt)">
	<c:choose>
		<c:when test="${empty roleLevelModelListDDL}">
			<c:out value="${noRoleLevelDataFoundForDDL}" />
		</c:when>

		<c:otherwise>

			<table id="validation1">
				<tr>
					<td><font color="red"><c:out
								value="${ddlSelectForRoleLevelParentId}" /></font></td>
				</tr>
				<tr>
					<td><font color="red"><c:out
								value="${emptyFieldForLevelName}" />
							<c:out value="${duplicateRoleLevelName}" /></font></td>
				</tr>




			</table>

			<table align="center" class="formStyles">
				<tr>
					<td>Select Parent Role Level Name</td>
					<td><select name="parentRoleLevelId" id="parentRoleLevelId">
							<option value="0">--- Select ---</option>
							<c:choose>
								<c:when test="${empty roleLevelModelListDDL}">
								</c:when>
								<c:otherwise>

									<c:set var="count" value="0" scope="page" />
									<c:forEach items="${roleLevelModelListDDL}"
										var="roleLevelModel">
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
					<td>Role Level Name</td>
					<td><input type="text" name="levelName"
						value="<c:out value="${roleLevelModel.levelName}"/>">
				</tr>

				<tr>
					<td><input type="hidden" name="rowLevelId"
						value="<c:out value="${roleLevelModel.rowLevelId}"/>"></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><input type="submit" value="Save"><a
						href="viewRoleLevel"><input type="button" value="Cancel"
							id="backbutton" class="buttonStyle2"></a></td>
				</tr>
			</table>

		</c:otherwise>
	</c:choose>
	<br>

</form>
<%@ include file="admin-footer.jsp"%>