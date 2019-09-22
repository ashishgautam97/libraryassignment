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
			<caption>Books</caption>
			<thead>
				<tr>
					<th>Name</th>
					<th>ISBN</th>
					<th>Quantity</th>
					<th>Available</th>
					<th></th>
					<th></th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${books}" var="book">
					<tr>
						<td>${book.name}</td>
						<td>${book.isbn}<!-- <fmt:formatDate value="${todo.targetDate}" pattern="dd/MM/yyyy"/> --></td>
						<td>${book.quantity}</td>
						<td>${book.available}</td>
						<td><a type="button" class="btn btn-success"
							href="/update-book?id=${book.id}">Update</a></td>
						<td><a type="button" class="btn btn-warning"
							href="/delete-book?id=${book.id}">Delete</a></td>
						<td>
							<c:choose>
								<c:when test = "${book.available > 0}">
						            <a type="button" class="btn btn-info"
													href="/issue-book?id=${book.id}">Issue</a>
						         </c:when>
						         <c:otherwise>
						            <a type="button" class="btn btn-info btn-disable">Issue</a>
						         </c:otherwise>
							</c:choose>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div>
			<a class="button" href="/add-book">Add a Book</a>
			 <a class="button" href="/borrow-list">View Borrowed Books</a>
		</div>
	</div>
<%@ include file="common/footer.jspf" %>