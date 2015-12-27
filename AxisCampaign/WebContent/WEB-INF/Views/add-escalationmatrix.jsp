<%@ include file="admin-header.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<link href="<%=basePath%>resources/css/admin/bootstrap.min1.css"
	type="text/css" rel="stylesheet">
<%-- <link href="<%=basePath%>resources/css/admin/style1.css" type="text/css"
	rel="stylesheet"> --%>
<link href="<%=basePath%>resources/css/admin/bootstrap-wysihtml5.css"
	type="text/css" rel="stylesheet">
<%-- <script src="<%=basePath%>resources/js/admin/jquery-1.9.1.min.js"
	type="text/javascript"></script> --%>
<link
	href='https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700'
	rel='stylesheet' type='text/css'>
</head>

<script type="text/javascript" language="javascript">
	$(document).ready(
	//var campid = $("#campid").val();
	//$("#campaignId1").val(campid);
	function() {
		var campid = $("#campid").val();
		$("#campaignId1").val(campid);

		var type = $("#type").val();
		$("#type1").val(type);

	});
	
	/* --- for Escalation Matrix --- */
	function checkForm(escalationMatrixField)
	{
		if(escalationMatrixField.campaignId1.value=="" || escalationMatrixField.campaignId1.value=="Campaign Name")
		{
			alert ('Please Select the Campaign Name!');
			escalationMatrixField.campaignId1.focus();
			return false;
		}
		if(escalationMatrixField.name.value=="" || escalationMatrixField.name.value=="Name")
		{
			alert ('Please Enter a Name!');
			escalationMatrixField.name.focus();
			return false;
		}
		if(escalationMatrixField.email.value=="" || escalationMatrixField.email.value=="Email ID")
		{
			alert("Please Enter Your Email ID!") ;
			escalationMatrixField.email.focus() ;
			return false;
		}
			if (!escalationMatrixField.email.value.match(/^[\w\.\-]+@([\w\-]+\.)+[a-zA-Z]+$/))
			{
				alert("Please enter a valid E-mail ID!");
				escalationMatrixField.email.focus();
				escalationMatrixField.email.value = '';
				return false;
			}
		if(escalationMatrixField.contactNumber.value=="" || escalationMatrixField.contactNumber.value=="Contact Number")
		{
			alert("Please Enter Contact Number") ;
			escalationMatrixField.contactNumber.focus() ;
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
			if (!escalationMatrixField.contactNumber.value.match(/^(?:(?:\(?(?:00|\+)([1-4]\d\d|[1-9]\d?)\)?)?[\-\.\ \\\/]?)?((?:\(?\d{1,}\)?[\-\.\ \\\/]?){0,})(?:[\-\.\ \\\/]?(?:#|ext\.?|extension|x)[\-\.\ \\\/]?(\d+))?$/i))
			{
				alert("Please Enter a valid Contact Number") ;
				escalationMatrixField.contactNumber.focus() ;
				escalationMatrixField.contactNumber.value = '';
				return false;
			}
		if(escalationMatrixField.type1.value=="" || escalationMatrixField.type1.value=="Type")
		{
			alert ('Please Select a Type!');
			escalationMatrixField.type1.focus();
			return false;
		}
		return true;
	}
	
	/*-----------------Edit Escalation Matrix Client-Side Validation------------------------*/
	
	function checkFormEdit(updateematrix)
	{
		if(updateematrix.campaignId1.value=="" || updateematrix.campaignId1.value=="Campaign Name")
		{
			alert ('Please Select the Campaign Name!');
			updateematrix.campaignId1.focus();
			return false;
		}
		if(updateematrix.name.value=="" || updateematrix.name.value=="Name")
		{
			alert ('Please Enter a Name!');
			updateematrix.name.focus();
			return false;
		}
		if(updateematrix.email.value=="" || updateematrix.email.value=="Email ID")
		{
			alert("Please Enter Your Email ID!") ;
			updateematrix.email.focus() ;
			return false;
		}
			if (!updateematrix.email.value.match(/^[\w\.\-]+@([\w\-]+\.)+[a-zA-Z]+$/))
			{
				alert("Please enter a valid E-mail ID!");
				updateematrix.email.focus();
				updateematrix.email.value = '';
				return false;
			}
		if(updateematrix.contactNumber.value=="" || updateematrix.contactNumber.value=="Contact Number")
		{
			alert("Please Enter Contact Number") ;
			updateematrix.contactNumber.focus() ;
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
			if (!updateematrix.contactNumber.value.match(/^(?:(?:\(?(?:00|\+)([1-4]\d\d|[1-9]\d?)\)?)?[\-\.\ \\\/]?)?((?:\(?\d{1,}\)?[\-\.\ \\\/]?){0,})(?:[\-\.\ \\\/]?(?:#|ext\.?|extension|x)[\-\.\ \\\/]?(\d+))?$/i))
			{
				alert("Please Enter a valid Contact Number") ;
				updateematrix.contactNumber.focus() ;
				updateematrix.contactNumber.value = '';
				return false;
			}
		if(updateematrix.type1.value=="" || updateematrix.type1.value=="Type")
		{
			alert ('Please Select a Type!');
			updateematrix.type1.focus();
			return false;
		}
		return true;
	}
	
	
	
	
	
	
	
	
	
</script>


<c:choose>
	<c:when test="${update == 1}">
		<div class="titleArea">
			<center>
				<h1>
					<strong>Update Escalation Matrix</strong>
				</h1>
			</center>
		</div>
		<%-- <c:if test="${not empty errorMessage}"> --%>
		<h4 style="color: red;">${SelectCampaignId}</h4>
		<h4 style="color: red;">${emailIdNotBeNull}</h4>
		<h4 style="color: red;">${escalationNameNotNull}</h4>
		<h4 style="color: red;">${contactNumberShouldNotBeNull}</h4>
		<h4 style="color: red;">${typeShouldNotBeNull}</h4>
		<h4 style="color: red;">${emailIdNotValid}</h4>
		<h4 style="color: red;">${ContactNumberNotValid}</h4>
		<h4 style="color: red;">${EmailAlreadyExist}</h4>
		<h4 style="color: red;">${ContactNumberAlreadyExist}</h4>



		<form class="fullForm" method="post" name="frm" id="updateematrix"
			action="updateescalationmatrix"
			onSubmit="return checkFormEdit(updateematrix)">


			<input type="hidden" name="id"
				value="${adminescalationMatrixModel.id}">

			<%-- 	<input type="hidden"
					value="${adminEscalationMatrixModel.campaignId}" name="campaignId"
					id="campaignId"> --%>

			<table align="center" class="formStyles">
				<tr>

					<td colspan="2"><input type="hidden" id="campid"
						value="${adminescalationMatrixModel.campaignId}"></td>
				<tr>
					<td>Campaign Name</td>
					<td><select name="campaignId" class="select" id="campaignId1">
							<option value="0">-- Select --</option>
							<c:forEach items="${campaignList}" var="campaign">
								<option value="${campaign.campaignId}">${campaign.campaignName}</option>
							</c:forEach>
					</select></td>
				</tr>



				<tr>
					<td>Email Id</td>
					<td><input class="normal" type="text" id="email" name="email"
						value="${fn:escapeXml(adminescalationMatrixModel.email)}">
					</td>
				</tr>

				<tr>
					<td>Name</td>
					<td><input class="normal" type="text" id="name" name="name"
						value="${fn:escapeXml(adminescalationMatrixModel.name)}">

					</td>
				</tr>

				<tr>
					<td>Contact Number</td>
					<td><input class="normal" type="text" id="contactNumber"
						name="contactNumber"
						value="${fn:escapeXml(adminescalationMatrixModel.contactNumber)}">
					</td>
				</tr>

				<tr>

					<td colspan="2"><input type="hidden" id="type"
						value="${adminescalationMatrixModel.type}"></td>
				</tr>
				<tr>
					<td>Field Type</td>
					<td><select name="type" id="type1" class="select">
							<!-- <option value="select" selected>-- Select --</option> -->
							<option value="Talisma">Talisma</option>
							<option value="ContestTeam">Contest Team</option>
					</select></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><input type="submit" value="Save"><a
						href="viewEscalationMatrix"><input type="button"
							value="Cancel" id="backbutton" class="buttonStyle2"></a></td>
				</tr>
			</table>
		</form>
	</c:when>

	<c:otherwise>
		<div class="content">

			<div class="titleArea">
				<center>
					<h1>
						<strong>Add Escalation Matrix</strong>
					</h1>
				</center>
			</div>

			<c:choose>
				<c:when test="${not empty roleNotFound}">
					<h3>
						<font color="red"><c:out value="${roleNotFound}"></c:out></font>
					</h3>

				</c:when>
				<c:otherwise>

					</br>
					<h4 style="color: red;">${SelectCampaignId}</h4>
					<h4 style="color: red;">${emailIdNotBeNull}</h4>
					<h4 style="color: red;">${escalationNameNotNull}</h4>
					<h4 style="color: red;">${contactNumberShouldNotBeNull}</h4>
					<h4 style="color: red;">${typeShouldNotBeNull}</h4>
					<h4 style="color: red;">${emailIdNotValid}</h4>
					<h4 style="color: red;">${ContactNumberNotValid}</h4>
					<h4 style="color: red;">${EmailAlreadyExist}</h4>
					<h4 style="color: red;">${ContactNumberAlreadyExist}</h4>


					<form class="fullForm" method="post" name="frm"
						id="escalationMatrixField" action="saveEscalationMatrix"
						onSubmit="return checkForm(escalationMatrixField)">

						<table align="center" class="formStyles">

							<tr>
								<td colspan="2"><input type="hidden" id="campid"
									value="${escalationMatrixModel.campaignId}"></td>
							<tr>
							<tr>
								<td>Campaign Name</td>
								<td><select name="campaignId" id="campaignId1"
									class="select">
										<option value="0">-- Select --</option>
										<c:forEach items="${campaignList}" var="campaign">
											<option value="${campaign.campaignId}">${campaign.campaignName}</option>
										</c:forEach>
								</select></td>
							</tr>



							<tr>
								<td>Name</td>
								<td><input id="name" type="text" name="name"
									value="${fn:escapeXml(escalationMatrixModel.name)}">
							</tr>


							<tr>
								<td>Email Id</td>
								<td><input id="email" type="text" name="email"
									value="${fn:escapeXml(escalationMatrixModel.email)}">
							</tr>


							<tr>
								<td>Contact Number</td>
								<td><input id="contactNumber" type="text"
									name="contactNumber"
									value="${fn:escapeXml(escalationMatrixModel.contactNumber)}">
								</td>
							</tr>


							<tr>
								<td colspan="2"><input type="hidden" id="type"
									value="${escalationMatrixModel.type}"></td>
							<tr>
								<td>Field Type</td>
								<td><select name="type" id="type1" class="select">
										<!-- <option value="select" selected="selected">-- Select --</option> -->
										<option value="Talisma">Talisma</option>
										<option value="ContestTeam">Contest Team</option>
								</select></td>
							</tr>

							<tr>
								<td>&nbsp;</td>
								<td><input type="submit" value="Save"><a
									href="viewEscalationMatrix"><input type="button"
										value="Cancel" id="backbutton" class="buttonStyle2"></a></td>
							</tr>
						</table>

					</form>
				</c:otherwise>
			</c:choose>
		</div>
	</c:otherwise>
</c:choose>


<%@ include file="admin-footer.jsp"%>