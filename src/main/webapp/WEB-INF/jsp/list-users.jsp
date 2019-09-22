<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
	
	<div class="container">
		<%-- <form:form method="post" action="/search-book">
			<fieldset class="form-group">
				<form:label path="search">Search</form:label>
				<form:input path="search" type="text" class="form-control" required="required" value="${search}" />
				<form:errors path="desc" cssClass="text-warning" />
				<button type="submit" class="btn btn-success">Search</button>
			</fieldset>
		</form:form> --%>
		<table class="table table-striped">
			<caption>Users</caption>
			<thead>
				<tr>
					<th>Name</th>
					<th>Contact</th>
					<th>Issued Book</th>
					<th></th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${users}" var="user">
					<tr>
						<td>${user.name}</td>
						<td>${user.mobile}<!-- <fmt:formatDate value="${todo.targetDate}" pattern="dd/MM/yyyy"/> --></td>
						<td>${user.issuedBook}</td>
						<td><a type="button" class="btn btn-success"
							href="/update-user?id=${user.id}">Update</a></td>
						<td><a type="button" class="btn btn-warning"
							href="/delete-user?id=${user.id}">Delete</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div>
			<a class="button" href="/add-user">Add a User</a>
			 <a class="button" href="/borrow-list">View Borrowed Books</a>
		</div>
	</div>
<%@ include file="common/footer.jspf" %>