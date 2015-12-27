<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page session="true"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

</head>
<script src="http://code.jquery.com/jquery-latest.min.js"
	type="text/javascript"></script>

<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.7.2.js"></script>
<body>



	<script type="text/javascript" language="javascript">
		$(document)
				.ready(
						function() {
							var row_id = 0;

							$("#add_more")
									.click(
											function() {
												//alert(row_id);
												alert("Call on the add more jsp page")
												row_id = row_id + 1;
												//$("#t").append("<tr id="+row_id+"><td><div class=\"formGroup\"><label class=\"staticField\">Field Name</label><input class=\"normal\" id=\"fieldName\" type=\"text\" name=\"fieldName\"></div></td><td><input type=\"button\" value=\"Cancel\" onclick=\"del("+row_id+")\"></td></tr>");
												//$("#d"+row_id).html("<div class=\"formGroup\"><label class=\"staticField\">Field Name</label><div class=\"leftInput\"><input class=\"normal\" id=\"fieldName\" type=\"text\" name=\"fieldName_array\"><input class=\"normal\" type=\"button\" value=\"Cancel\" onclick=\"del("+row_id+")\"></div></div>");
												//$("#maindiv").find(':nth-child(1)').html("<div class=\"formGroup\"><label class=\"staticField\">Field Name</label><div class=\"leftInput\"><input class=\"normal\" id=\"fieldName\" type=\"text\" name=\"fieldName_array\"><input class=\"normal\" type=\"button\" value=\"Cancel\" onclick=\"del("+row_id+")\"></div></div>");
												$("#maindiv")
														.append(
																"<div id=t"+row_id+"></div>");
												//alert("div id created t"+row_id);

												//var temp_id=$("#maindiv").find(":nth-child("+row_id+")").attr('id');
												//alert("id :: "+temp_id);
												$("#t" + row_id)
														.html(
																"<div class=\"formGroup\"><label class=\"staticField\">Field Name</label><div><input class=\"normal\" id=\"fieldName\" type=\"text\" name=\"fieldName_array\"><br><input class=\"normal\" type=\"button\" value=\"Cancel\" onclick=\"del("
																		+ row_id
																		+ ")\"></div></div>");
												//alert("done");
												//alert(row_id);
											});

						});

		function del(row_id) {
			alert("here is " + row_id);
			$("#t" + row_id).remove();
		}
	</script>
	<div class="content">
		<div class="panelTitle">
			<center>
				<h1>Upload Acheivement Excel Sheet Page</h1>
			</center>
		</div>
		<form class="fullForm" method="post" name="frm"
			action="saveuploadAcheivementExcellSheet" id="updateAchievementValue"
			enctype="multipart/form-data"
			onSubmit="return checkForm(updateAchievementValue)">


			<div class="formGroup">
				<label class="staticField">Select Role</label>
				<div class="leftInput">
					<select name="roleId" class="select" id="step1">
						<option value="0">-- Select --</option>
						<c:forEach var="roleModel" items="${roleModels}" varStatus="loop">
							<option value="${roleModel.roleId}">${roleModel.role_name}</option>
						</c:forEach>
					</select>
				</div>
			</div>

			<div class="formGroup">
				<label class="staticField">Campaign Name</label>
				<div class="leftInput">
					<select name="campaignId" class="select" id="step2">
						<!-- <option value="0">-- Select --</option> -->
						<%-- <c:forEach items="${campaignList}" var="campList">
						<option value="${campList.id}">${campList.campaignName}</option>
					</c:forEach> --%>
					</select>
				</div>
			</div>

			<div class="formGroup">
				<label class="staticField">Upload Target File</label>
				<div class="leftInput">
					<input class="normal disable" type="file" readonly
						placeholder="Readonly input here…" name="fileName">
				</div>
			</div>







			<div id="maindiv"></div>


			<input type="submit" value="Submit">
		</form>
	</div>



</body>

<script type="text/javascript">
	$('#step1')
			.change(
					function() {
						var roleID = $("#step1").val();

						$
								.ajax({
									// url:'campaignagainstRoleId',
									url : 'campaignagainstRoleId?q=' + roleID,
									cache : false,
									type : 'GET',
									success : function(result) {
										var data = jQuery.parseJSON(result);
										var markup = "";
										for (var x = 0; x < data.length; x++) {
											// alert("Naru");
											markup += "<option value=" + data[x].campaignId + ">"
													+ data[x].campaignName
													+ "</option>";
											console.log(data[x].id);
											//                                                alert(data[x].id);
										}
										$('#step2').html(markup).show();

									},
									error : function(reponse) {
									}
								});
					});
</script>
</html>