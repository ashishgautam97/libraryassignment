<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
<div class="container">
	<form:form method="post" modelAttribute="user"> <!--  commandName="book" -->
		<form:hidden path="id" value="${user.id}"/>
		<fieldset class="form-group">
			<form:label path="name">Name <span class="text text-danger">*</span></form:label>
			<form:input path="name" type="text" class="form-control" value="${user.name}"
				required="required" />
			<form:errors path="name" cssClass="text-warning" />
		</fieldset>

		<fieldset class="form-group">
			<form:label path="mobile">Mobile <span class="text text-danger">*</span></form:label>
			<form:input path="mobile" type="text" class="form-control" value="${user.mobile}"
				required="required" />
			<form:errors path="mobile" cssClass="text-warning" />
		</fieldset>

		<button type="submit" class="btn btn-success">Add</button>
	</form:form>
</div>
<%@ include file="common/footer.jspf" %>