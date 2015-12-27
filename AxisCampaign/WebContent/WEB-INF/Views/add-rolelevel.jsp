<%@ include file="admin-header.jsp"%>

<script>
	$(document)
			.ready(
					function() {

						<c:choose>
						<c:when test="${empty roleLevelModel}">
						</c:when>

						<c:otherwise>
						$("#parentRoleLevelId")
								.val(
										"<c:out value="${roleLevelModel.parentRoleLevelId}"/>");
						$("#levelName").val(
								"<c:out value="${roleLevelModel.levelName}"/>");
						</c:otherwise>

						</c:choose>

					});
	function checkForm(roleLvelMgt)
	{
		//Parent Role Level

		if(roleLvelMgt.parentRoleLevelId.value=="0" || roleLvelMgt.parentRoleLevelId.value=="Your Name")
		{
			alert ('Please Select a Parent Role Level!');
			roleLvelMgt.parentRoleLevelId.focus();
			return false;
		}
		

		if(roleLvelMgt.levelName.value=="" || roleLvelMgt.levelName.value=="Your Name")
		{
			alert ('Please Enter a Name!');
			roleLvelMgt.levelName.focus();
			return false;
		}
		return true;
	}
</script>
<div class="titleArea">
	<center>
		<h1>
			<strong>Add Role Level</strong>
		</h1>
	</center>
</div>
<form action="insertRoleLevelData" method="post" id="roleLvelMgt"
	onSubmit="return checkForm(roleLvelMgt)">


	<c:choose>
		<c:when test="${empty roleLevelModelListDDL}">
			<c:out value="${noRoleLevelDataFoundForDDL}" />
		</c:when>

		<c:otherwise>

			<table>
				<tr>
					<td><font color="red"><c:out
								value="${ddlSelectForRoleLevelParentId}" /></font></td>
				</tr>
				<tr>
					<td><font color="red"><c:out
								value="${emptyFieldForLevelName}" /> <c:out
								value="${duplicateRoleLevelName}" /></font></td>
				</tr>

			</table>


			<table align="center" class="formStyles">

				<tr>

					<td>Select Parent Role Level Name</td>
					<td><select name="parentRoleLevelId" id="parentRoleLevelId">
							<option value="0">--- Select ---</option>
							<c:set var="count" value="0" scope="page" />
							<c:forEach items="${roleLevelModelListDDL}" var="roleLevelModel">
								<option
									value="<c:out value="${roleLevelModel.parentRoleLevelId}"/>">
									<c:out value="${roleLevelModel.levelName}" />
								</option>
							</c:forEach>

					</select></td>
				</tr>
				<tr>
					<td>Enter Name</td>
					<td><input type="text" name="levelName" id="levelName"></td>
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

</form>

<%@ include file="admin-footer.jsp"%>
