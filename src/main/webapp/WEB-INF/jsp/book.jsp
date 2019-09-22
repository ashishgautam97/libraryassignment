<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
<div class="container">
	<form:form method="post" modelAttribute="book"> <!--  commandName="book" -->
		<form:hidden path="id" value="${book.id}"/>
		<fieldset class="form-group">
			<form:label path="name">Name <span class="text text-danger">*</span></form:label>
			<form:input path="name" type="text" class="form-control" value="${book.name}"
				required="required" />
			<form:errors path="name" cssClass="text-warning" />
		</fieldset>

		<fieldset class="form-group">
			<form:label path="isbn">ISBN <span class="text text-danger">*</span></form:label>
			<form:input path="isbn" type="text" class="form-control" value="${book.isbn}"
				required="required" />
			<form:errors path="isbn" cssClass="text-warning" />
		</fieldset>
		
		<fieldset class="form-group">
			<form:label path="quantity">Quantity <span class="text text-danger">*</span></form:label>
			<form:input path="quantity" type="number" class="form-control" value="${book.quantity}"
				required="required" />
			<form:errors path="quantity" cssClass="text-warning" />
		</fieldset>

		<button type="submit" class="btn btn-success">Add</button>
	</form:form>
</div>
<%@ include file="common/footer.jspf" %>