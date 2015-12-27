<%@ include file="admin-header.jsp"%>

<%-- <c:if test="${not empty datanotThere}">
		
		
								<c:out value="${datanotThere}"></c:out>
							
		
		
		
		</c:if> --%>
<div class="titleArea">
	<center>
		<h1>
			<strong>Update Acheivement Field Value</strong>
		</h1>
	</center>
</div>
<
<script type="text/javascript">
	/*
	 * Edit Achievement Field Value Client Side Validation
	 */
	function checkForm(editAchievementValueForm) {
		if (editAchievementValueForm.step1.value == ""
				|| editAchievementValueForm.step1.value == "Role") {
			alert('Please Select a Role!');
			editAchievementValueForm.step1.focus();
			return false;
		}
		if (editAchievementValueForm.step2.value == ""
				|| editAchievementValueForm.step2.value == "Campaign Name") {
			alert('Please Select a Campaign Name!');
			editAchievementValueForm.step2.focus();
			return false;
		}
		return true;
	}
</script>

<form class="fullForm" id="editAchievementValueForm" method="post"
	name="frm" action="saveEditedAcheivementFieldValues"
	onSubmit="return checkForm(editAchievementValueForm)">

	<c:if test="${not empty datanotThere}">
		<%-- <c:out value="${datanotThere}"></c:out>	 --%>
		<h4 style="color: red;">${datanotThere}</h4>

	</c:if>

	<table align="center" class="formStyles">
		<tr>
			<td>${acheivementFieldValueModel.fieldName}</td>

			<td><input class="normal" border="5" id="fieldName1" type="text"
				name="fieldValue"
				value="${fn:escapeXml(acheivementFieldValueModel.fieldValue)}"></td>

		</tr>

		<input class="normal" id="fieldName1" type="hidden" required
			name="acheivementFieldValueId"
			value="${acheivementFieldValueModel.acheivementFieldValueId}">

		<tr>
			<td>&nbsp;</td>
			<td><input type="submit" class="buttonStyle" value="Update">
				<a href="viewacheivementfieldvalueManagement"><input
					type="button" class="buttonStyle2" value="Cancel" id="backbutton"></a></td>
		</tr>
	</table>




</form>


<%@ include file="admin-footer.jsp"%>