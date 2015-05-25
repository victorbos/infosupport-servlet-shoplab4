<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%>
<jsp:include page="header.jsp"/>
	
<table class="table">
	<tr>
		<th>Product</th>
		<th>Aantal</th>
		<th>Subtotaal</th>
	</tr>
	
	<c:forEach items="${sessionScope.basket.items}" var="basketItem">	
		<tr>
			<td>${basketItem.product.name}</td>
			<td>${basketItem.amount}</td>
			<td><f:formatNumber value="${basketItem.product.price * basketItem.amount}" pattern="EUR #.00" /></td>
		</tr>
	</c:forEach>
	<tr>
		<th>Totaal:</th>
		<th></th>
		<th><f:formatNumber value="${basket.total}" pattern="EUR #.00" /></th>
	</tr>	
</table>
	
<jsp:include page="footer.jsp"/>