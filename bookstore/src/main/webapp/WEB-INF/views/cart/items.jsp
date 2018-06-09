<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<c:url value="/" var="contextPath" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Bookstore - Your cart</title>
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
	<h2>Your cart</h2>
	<p>${sucess}</p>
	<p>Your cart (${cart.quantity})</p>
	<table style="width: 80%">
		<thead>
			<tr>
				<th>Item</th>
				<th>Price</th>
				<th>Quantity</th>
				<th>Total</th>
				<th>Remove</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${cart.items}" var="item">
				<tr>
					<td>${item.product.title}</td>
					<td>${item.priceType}</td>
					<td>${cart.getQuantity(item)}</td>
					<td>${cart.getTotal(item)}</td>
					<td>
						<form action="${s:mvcUrl('CC#remove').arg(0, item.product.id).arg(1, item.priceType).build() }" method="POST">
							<input type="submit" name="remove" value="X" />
						</form>
					</td>
				</tr>
			</c:forEach>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="3">
					<form action="<c:url value="/payment/checkout"/>" method="POST">
						<input type="submit" class="checkout" name="checkout"
							value="Checkout" />
					</form>
				</td>
				<td class="numeric-cell">${cart.getTotal()}</td>
			</tr>
		</tfoot>
	</table>
	<a href='<c:url value="/product" />'>Back to List of Products</a>
</body>
</html>