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
			<caption>Borrows</caption>
			<thead>
				<tr>
					<th>User</th>
					<th>Book</th>
					<th>Issued Date</th>
					<th>Return Date</th>
					<th>Remark</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${borrows}" var="borrow">
					<tr>
						<td>${borrow.user_name}</td>
						<td>${borrow.book_name}<!-- <fmt:formatDate value="${todo.targetDate}" pattern="dd/MM/yyyy"/> --></td>
						<td>${borrow.issue_date}</td>
						<td>${borrow.return_date}</td>
						<td>${borrow.remarks}</td>
						<td><a type="button" class="btn btn-primary"
							href="/return?id=${borrow.id}">Return</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<!-- <div>
			<a class="button" href="/add-user">Add a User</a>
		</div> -->
	</div>
<%@ include file="common/footer.jspf" %>