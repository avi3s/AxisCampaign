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
	$(document).ready(function() {

		/* 		$('#updateContent').submit(function() {
		 flag = cms_customvalidation();
		 if (flag)
		 return true;
		 else
		 return false;
		 }); */

		$('#addContent').submit(function() {
			flag = cms_customvalidation();
			if (flag)
				return true;
			else
				return false;
		});
	});
	
	/* --- for Add Content --- */
	function checkForm(addContent)
	{
		if(addContent.pageName.value=="" || addContent.pageName.value=="Page Name")
		{
			alert ('Please Enter the Page Name!');
			addContent.pageName.focus();
			return false;
		}
		if(addContent.path.value=="" || addContent.path.value=="Path")
		{
			alert ('Please Enter the Path!');
			addContent.path.focus();
			return false;
		}
		if(addContent.pageContent.value=="" || addContent.pageContent.value=="Page Content")
		{
			alert ('Please Enter Content!');
			addContent.pageContent.focus();
			return false;
		}
		return true;
	}
	/* --- for Edit Content Client Side Validation --- */
	
	function checkFormEdit(editContent)
	{
		if(editContent.pageName1.value=="" || editContent.pageName1.value=="Page Name")
		{
			alert ('Please Enter the Page Name!');
			editContent.pageName1.focus();
			return false;
		}
		if(editContent.path1.value=="" || editContent.path1.value=="Path")
		{
			alert ('Please Enter the Path!');
			editContent.path1.focus();
			return false;
		}
		if(editContent.pageContent1.value=="" || editContent.pageContent1.value=="Page Content")
		{
			alert ('Please Enter Content!');
			editContent.pageContent1.focus();
			return false;
		}
		return true;
	}
	
	
	
	
	
	
	
	
	
</script>

<c:choose>
	<c:when test="${update == 1}">
		<c:if test="${not empty errorMessage}">
			<h4 style="color: red;">${errorMessage}</h4>
		</c:if>

		<div class="titleArea">
			<center>
				<h1>
					<strong>Update Content</strong>
				</h1>
			</center>
		</div>


		<h4 style="color: red;">${cmsPageNotNull}</h4>
		<h4 style="color: red;">${cmsPathNotNull}</h4>
		<h4 style="color: red;">${cmsContentNotNull}</h4>

		<form action="updateContent" method="post" id="editContent"
			onSubmit="return checkFormEdit(editContent)">

			<input type="hidden" name="id" value="${viewContentById.id}">

			<table class="formStyles">
				<tr>
					<td>PageName</td>
					<td><input type="text" id="pageName1" name="pageName"
						value="${fn:escapeXml(viewContentById.pageName)}"></td>
				</tr>
				<tr>
					<td>Path</td>
					<td><input type="text" id="path1" name="path"
						value="${fn:escapeXml(viewContentById.path)}"></td>
				</tr>
				<tr>
					<td>PageContent</td>
					<td><textarea class="textarea" id="pageContent1"
							placeholder="Enter text ..." style="width: 95%; height: 200px;"
							name="pageContent">${viewContentById.pageContent}</textarea></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><input type="submit" value="Save" style="margin-left: 0;"><a
						href="viewContent"><input type="button" value="Cancel"
							id="backbutton" class="buttonStyle2"></a></td>
				</tr>
			</table>
		</form>
	</c:when>

	<c:otherwise>
		<div class="content">
			<c:if test="${not empty errorMessage}">
				<h4 style="color: red;">${errorMessage}</h4>
			</c:if>

			<div class="titleArea">
				<center>
					<h1>
						<strong>Add Content</strong>
					</h1>
				</center>
			</div>


			<h4 style="color: red;">${cmsPageNotNull}</h4>
			<h4 style="color: red;">${cmsPathNotNull}</h4>
			<h4 style="color: red;">${cmsContentNotNull}</h4>


			<form class="fullform" action="addContent" id="addContent"
				method="post" onSubmit="return checkForm(addContent)">
				<table class="formStyles">
					<tr>
						<td>PageName</td>
						<td><input type="text" id="pageName" name="pageName"
							value="${fn:escapeXml(cmsModel.pageName)}"></td>
					</tr>
					<tr>
						<td>Path</td>
						<td><input type="text" id="path" name="path"
							value="${fn:escapeXml(cmsModel.path)}"></td>
					</tr>
					<tr>
						<td>PageContent</td>
						<td><textarea id="pageContent" class="textarea"
								placeholder="Enter text ..." style="width: 95%; height: 200px;"
								name="pageContent">${fn:escapeXml(cmsModel.pageContent)}</textarea>
						</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td><input type="submit" class="buttonStyle" value="Save"
							style="margin-left: 0"><a href="viewContent"><input
								type="button" value="Cancel" id="backbutton"
								class="buttonStyle2"></a></td>
					</tr>
				</table>

			</form>

		</div>
	</c:otherwise>
</c:choose>

<script src="<%=basePath%>resources/js/admin/wysihtml5-0.3.0.js"
	type="text/javascript"></script>
<script src="<%=basePath%>resources/js/admin/jquery-1.9.1.min.js"
	type="text/javascript"></script>
<script src="<%=basePath%>resources/js/admin/bootstrap.min.js"
	type="text/javascript"></script>
<script src="<%=basePath%>resources/js/admin/bootstrap-wysihtml5.js"
	type="text/javascript"></script>
<script>
	$('.textarea').wysihtml5({
		"font-styles" : true, //Font styling, e.g. h1, h2, etc. Default true
		"emphasis" : true, //Italics, bold, etc. Default true
		"lists" : true, //(Un)ordered lists, e.g. Bullets, Numbers. Default true
		parser : function(html) {
			return html;
		}, //Button which allows you to edit the generated HTML. Default false
		"link" : true, //Button to insert a link. Default true
		"image" : true, //Button to insert an image. Default true,
		"color" : true,
		"html" : true, //Button to change color of font  
	});
</script>
<%@ include file="admin-footer.jsp"%>