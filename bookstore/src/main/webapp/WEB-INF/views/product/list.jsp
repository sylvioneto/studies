<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<c:url value="/" var="contextPath" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Bookstore - Product list</title>
<style>
table, th, td {
    border: 1px solid black;
    border-collapse: collapse;
}
th, td {
    padding: 1px;
}
</style>
</head>
<body>
	<h2>List of Products</h2>
	<p>${success}</p>
	<p>${fail}</p>
	<p><a href="<c:url value="/cart"/>">Your cart (${cart.quantity})</a></p>

	<table style="width:80%">
		<tr>
			<th>Title</th>
			<th>Description</th>
			<th>Pages</th>
			<th>Release Date</th>
			<th colspan="2">Prices</th>
		</tr>
		<c:forEach items="${products}" var="product">
			<tr>
				<td><a href="<c:url value="/product/detail/${product.id}"/>">${product.title}</a></td>
				<td>${product.description}</td>
				<td>${product.pages}</td>
				<td><fmt:formatDate pattern="dd/MM/yyyy" value="${product.releaseDt.time}" /></td>
				<c:forEach items="${product.prices}" var="price">
					<td>${price.pType} = ${price.amount}</td>
				</c:forEach>
			</tr>
		</c:forEach>
	</table>
</body>
</html>