<%@ include file="admin-header.jsp"%>
<body>
	<div class="formGroup">


		<%-- <c:if test="${empty var1}">
    var1 is empty or null.
</c:if> --%>
		<%-- <c:if test="${not empty acheivementModeljsp.message}">
   <label class="staticField">${acheivementModeljsp.message}</label>
</c:if> --%>
		<%-- <label class="staticField">${acheivementModeljsp.message}</label>
  <label class="staticField">${acheivementModeljsp.message1}</label>
   <label class="staticField">${acheivementModeljsp.message2}</label> --%>
		<%-- <c:choose>
    <c:when test="${acheivementModeljsp.message1 ne 'No Value'}">
   
       <label class="staticField">${acheivementModeljsp.message1}</label>
        <br />
    </c:when>  
    <c:when test="${acheivementModeljsp.message2 != 'No Value'}">
    
        <label class="staticField">${acheivementModeljsp.message2}</label>
        <br />
    </c:when>  
      
    <c:otherwise>

        <label class="staticField">${acheivementModeljsp.message}</label>
        <br />
    </c:otherwise>
</c:choose>
			
			<div class="leftInput">
				<input class="normal"  border="5" id="fieldName1" type="text"  name="fieldValue" value="${acheivementFieldValueModel.fieldValue}">
				<input class="normal" id="fieldName1" type="hidden" name="acheivementFieldValueId" value="${acheivementFieldValueModel.acheivementFieldValueId}">
				<div class="col-xs-7">
					<span class="error">This field is required</span>
				</div>
			</div>
		</div>
 --%>

		<%-- <c:forEach var="temp" items="${mapsValue}" varStatus="loop">
						<option value="${roleModel.roleId}">${roleModel.role_name}</option>
					</c:forEach> --%>
		<%-- <c:forEach var="entry" items="${mapsValue}">
  Row No: <c:out value="${entry.key}" />
			<c:forEach var="temp1" items="${entry.value}" varStatus="loop">
				<c:out value="${temp1}" />
			</c:forEach>
		</c:forEach> --%>
		<h1 bgcolor="Black">Inserted Rows Number</h1>
		<table border="1" bordercolor="GREEN">
			<th>Inserted Rows</th>
			<c:if test="${empty insertedRows}">


				<%-- <c:out value="${duplicateValue}"></c:out> --%>

				<tr>

					<td><c:out value="No Rows Inserted" /></td>
				</tr>


			</c:if>

			<c:forEach var="list" items="${insertedRows}">
				<tr>

					<td><c:out value="${list}" /></td>
				</tr>
			</c:forEach>


		</table>



		<h2>Rows with Values not inserted due to column mismatch</h2>
		<table border="1" bordercolor="RED">
			<tr>
				<th>Row No</th>
				<th>Data</th>
			</tr>

			<c:if test="${empty mapsValue}">


				<%-- <c:out value="${duplicateValue}"></c:out> --%>

				<tr>

					<td><c:out value="All Field Values Inserted Correctly" /></td>
				</tr>


			</c:if>



			<c:forEach var="entry" items="${mapsValue}">
				<tr>
					<td><c:out value="${entry.key}" /></td>
					<c:set var="count" value=" ${entry.key}" />
					<td>
						<table border="1">
							<tr>
								<c:forEach var="temp1" items="${entry.value}" varStatus="loop">
									<td><c:out value="${temp1}" /></td>
								</c:forEach>
								<%-- <c:forEach var="temp2" items="${blankSpaceRows}">
								
								<c:out value="${temp2}" />
								<c:out value="${count}" />
								<c:out value="Due to blank space" />
									<c:if test="${'1'=='count'}">
										
										<c:out value="${temp2}" />
										
										<c:out value="Due to blank space" />
										
										

									</c:if>

								</c:forEach> --%>
							</tr>
						</table>


					</td>
				</tr>
			</c:forEach>




		</table>
</body>
<%@ include file="admin-footer.jsp"%>