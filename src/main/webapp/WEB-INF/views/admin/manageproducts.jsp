<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<link
	href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css"
	rel="stylesheet">
<title>Admin</title>
</head>
<body>

	<table class="table">
		<tr>
			<th>Productnaam</th>
			<th>Prijs</th>
			<th></th>
		</tr>
		<c:forEach items="${products}" var="product">
			<tr>
				<td>${product.name}</td>
				<td>${product.price}</td>
				<td>
					<form method="post" action="${pageContext.request.contextPath}/admin/manageproducts">
						<input type="hidden" name="action" value="delete" /> 
						<input type="hidden" name="productId" value="${product.id}" /> 
						<input type="submit" name="action" value="Verwijder" class="btn btn-danger" />
					</form>
				</td>
			</tr>
		</c:forEach>
	</table>

	<div class="offset1">
		<form class="form-horizontal" method="post">
			<fieldset>
				<legend>Nieuw product</legend>
				<input type="hidden" name="action" value="add">
				<div class="control-group">
					<label class="control-label" for="inputProductName">Product
						naam</label>
					<div class="controls">
						<input type="text" id="inputProductName" name="productName" placeholder="Product naam">
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="inputProductPrice">Prijs</label>
					<div class="controls">
						<input type="text" name="productPrice" id="inputProductPrice" placeholder="Prijs">
					</div>
				</div>
				<div class="control-group">
					<div class="controls">
						<button type="submit" class="btn btn-success">Opslaan</button>
					</div>
				</div>
			</fieldset>
		</form>
	</div>
</body>
</html>