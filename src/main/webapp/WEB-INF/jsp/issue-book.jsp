<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
<div class="container">
	<form:form method="post" modelAttribute="borrow">
			<form:hidden path="book_id" value="${book_id}" />
		<fieldset class="form-group">
			<form:label path="book_name">Book <span class="text text-danger">*</span></form:label>
			<form:input path="book_name" type="text" class="form-control"
				required="required" value="${book_name}" readonly="true" />
			<form:errors path="book_name" cssClass="text-warning" />
		</fieldset>

		<fieldset class="form-group">
			<form:label path="user_id">User <span class="text text-danger">*</span></form:label>
			<form:select path="user_id" required="required" cssClass="form-control">
				<c:forEach items="${users}" var="user">
   					<form:option value="${user.id}" label="${user.name} [${user.mobile}]" />
   				</c:forEach>
			</form:select>
			<form:errors path="user_id" cssClass="text-warning" />
		</fieldset>
		
		<fieldset class="form-group">
			<form:label path="return_date">Return Date <span class="text text-danger">*</span></form:label>
			<form:input path="return_date" class="form-control targetDate"
				required="required" />
			<form:errors path="return_date" cssClass="text-warning" />
		</fieldset>
		
		<fieldset class="form-group">
			<form:label path="remarks">Remarks</form:label>
			<form:textarea path="remarks" rows="3" cols="50" cssClass="form-control" />
			<form:errors path="remarks" cssClass="text-warning" />
		</fieldset>
		
		<button type="submit" class="btn btn-success">Add</button>
	</form:form>
</div>
<%@ include file="common/footer.jspf" %>