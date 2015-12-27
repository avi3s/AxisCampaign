<%@ include file="admin-header.jsp"%>

<script>
	$(document)
			.ready(
					function() {

						<c:choose>
						<c:when test="${empty subRoleLevelModel}">
						</c:when>

						<c:otherwise>
						//alert("${subRoleLevelModel.roleLevelId}");
						$("#roleLevelId").val(
								"${subRoleLevelModel.roleLevelId}");
						$("#subRoleLevelParentId").val(
								"${subRoleLevelModel.subRoleLevelParentId}");
						</c:otherwise>
						</c:choose>

						$("#roleLevelId")
								.change(
										function() {
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

																if (data.trim() == "No Parent Sub Role Levels Found") {
																	$("#d")
																			.html(
																					data);
																} else {
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
																}

															});

										});
					});
	/* --- for Sub Role Level Management --- */
	function checkForm(subRoleLvelMgt) {
		//Parent Role Level

		if (subRoleLvelMgt.roleLevelId.value == "0"
				|| subRoleLvelMgt.roleLevelId.value == "Role Level") {
			alert('Please Select a Role Level!');
			subRoleLvelMgt.roleLevelId.focus();
			return false;
		}

		if (subRoleLvelMgt.subRoleLevelParentId.value == "0"
				|| subRoleLvelMgt.subRoleLevelParentId.value == "Parent Sub Role Level") {
			alert('Please Select a Parent Sub Role Level!');
			subRoleLvelMgt.subRoleLevelParentId.focus();
			return false;
		}

		if (subRoleLvelMgt.description.value == ""
				|| subRoleLvelMgt.description.value == "Sub Role Name") {
			alert('Please Enter a Sub Role Name!');
			subRoleLvelMgt.description.focus();
			return false;
		}

		if (subRoleLvelMgt.uniqueId.value == ""
				|| subRoleLvelMgt.uniqueId.value == "Sol ID") {
			alert('Please Enter a Sol ID!');
			subRoleLvelMgt.uniqueId.focus();
			return false;
		}
		return true;
	}
</script>
<div class="titleArea">
	<center>
		<h1>
			<strong>Add Sub-Role Level</strong>
		</h1>
	</center>
</div>

<form action="insertSubRoleLevelData" method="post" id="subRoleLvelMgt"
	onSubmit="return checkForm(subRoleLvelMgt)">
	<c:choose>
		<c:when test="${empty roleLevelModelList}">
			<c:out value="${noRoleLevelDataFoundForDDL}" />
		</c:when>

		<c:otherwise>

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
								value="${emptyfieldForSubRoleName}" /> <c:out
								value="${duplicateSubRoleLevelName}" /></font></td>
				</tr>
				<tr>
					<td><font color="red"><c:out
								value="${emptyfieldForSolId}" /> <c:out
								value="${duplicateSolId}" /></font></td>
				</tr>
				<tr>
					<td><font color="red"><div id="d"></div></font></td>
				</tr>
			</table>

			<table align="center" class="formStyles">
				<tr>
					<td>Select Role Level Name</td>
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
						value="${subRoleLevelModel.description}"></td>
				</tr>
				<tr>
					<td>Enter Sol ID</td>
					<td><input type="text" name="uniqueId"
						value="${subRoleLevelModel.uniqueId}"></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><input type="submit" value="Save"><a
						href="viewSubRoleLevel"><input type="button" value="Cancel"
							id="backbutton" class="buttonStyle2"></a></td>
				</tr>
			</table>

		</c:otherwise>
	</c:choose>

</form>

<%@ include file="admin-footer.jsp"%>