<%@ include file="admin-header.jsp"%>

<script>
	$(document)
			.ready(
					function() {

						$("#roleLevelId")
								.val(
										"<c:out value="${subRoleLevelModel.roleLevelId}"/>")
						$("#subRoleLevelParentId")
								.val(
										"<c:out value="${subRoleLevelModel.subRoleLevelParentId}"/>")

						$("#roleLevelId")
								.change(
										function() {
											alert($("#roleLevelId").val());
											$
													.post(
															"./ajaxCallForSubRoleLevelList",
															{
																roleLevelId : $(
																		"#roleLevelId")
																		.val()
															},
															function(data,
																	status) {
																alert("done");
																alert(data);
																$(
																		"#subRoleLevelParentId")
																		.empty();
																$(
																		"#subRoleLevelParentId")
																		.append(
																				"<option value=0>--Select--</option>");
																$(
																		"#subRoleLevelParentId")
																		.append(
																				data);

															});

										});
					});
	
	/* --- for Sub Role Edit Level Management --- */
	function checkForm(editsubRoleLvelMgt) {
		//Parent Role Level

		if (editsubRoleLvelMgt.roleLevelId.value == "0"
				|| editsubRoleLvelMgt.roleLevelId.value == "Role Level") {
			alert('Please Select a Role Level!');
			editsubRoleLvelMgt.roleLevelId.focus();
			return false;
		}

		if (editsubRoleLvelMgt.subRoleLevelParentId.value == "0"
				|| editsubRoleLvelMgt.subRoleLevelParentId.value == "Parent Sub Role Level") {
			alert('Please Select a Parent Sub Role Level!');
			editsubRoleLvelMgt.subRoleLevelParentId.focus();
			return false;
		}

		if (editsubRoleLvelMgt.description.value == ""
				|| editsubRoleLvelMgt.description.value == "Sub Role Name") {
			alert('Please Enter a Sub Role Name!');
			editsubRoleLvelMgt.description.focus();
			return false;
		}

		if (editsubRoleLvelMgt.uniqueId.value == ""
				|| editsubRoleLvelMgt.uniqueId.value == "Sol ID") {
			alert('Please Enter a Sol ID!');
			editsubRoleLvelMgt.uniqueId.focus();
			return false;
		}
		return true;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
</script>
<div class="titleArea">
	<center>
		<h1>
			<strong>Update Sub-Role Level</strong>
		</h1>
	</center>
</div>
<form action="editSubRoleLevelData" method="post"
	id="editsubRoleLvelMgt" onSubmit="return checkForm(editsubRoleLvelMgt)">

	<table>
		<tr>
			<td><font color="red"><c:out
						value="${ddlselectForRoleLevel}" /></font></td>
		</tr>
		<tr>
			<td><font color="red"><c:out
						value="${ddlselectForSubRoleLevelParentId}" /></font></td>
		</tr>
		<tr>
			<td><font color="red"><c:out
						value="${emptyfieldForSubRoleName}" />
					<c:out value="${duplicateSubRoleLevelName}" /></font></td>
		</tr>
		<tr>
			<td><font color="red"><c:out
						value="${emptyfieldForSolId}" />
					<c:out value="${duplicateSolId}" /></font></td>
		</tr>

	</table>

	<table align="center" class="formStyles">
		<tr>
			<td>Select Role Level Name</td>
			<td><select name="roleLevelId" id="roleLevelId">
					<option value="0">--- Select ---</option>

					<c:choose>
						<c:when test="${empty roleLevelModelList}">
						</c:when>
						<c:otherwise>

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
			<td>Select Parent Sub Role Level Name</td>
			<td><select name="subRoleLevelParentId"
				id="subRoleLevelParentId">
					<option value="0">--- Select ---</option>

					<c:choose>
						<c:when test="${empty subRoleLevelModelList}">
						</c:when>
						<c:otherwise>

							<c:forEach items="${subRoleLevelModelList}"
								var="subRoleLevelModel">
								<option
									value="<c:out value="${subRoleLevelModel.subRoleLevelId}"/>">
									<c:out value="${subRoleLevelModel.description}" />
								</option>
							</c:forEach>
						</c:otherwise>
					</c:choose>
			</select></td>
		</tr>
		<tr>
			<td>Enter Sub Role Name</td>
			<td><input type="text" name="description"
				value="<c:out value="${subRoleLevelModel.description}"/>"></td>
		</tr>
		<tr>
			<td>Enter Sol ID</td>
			<td><input type="text" name="uniqueId"
				value="<c:out value="${subRoleLevelModel.uniqueId}"/>"></td>
		</tr>
		<tr>
			<td colspan="2"><input type="hidden" name="subRoleLevelId"
				value="<c:out value="${subRoleLevelModel.subRoleLevelId}"/>"></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td><input type="submit" value="Save"><a
				href="viewSubRoleLevel"><input type="button" value="Cancel"
					id="backbutton" class="buttonStyle2"></a></td>
		</tr>
	</table>


</form>

<%@ include file="admin-footer.jsp"%>