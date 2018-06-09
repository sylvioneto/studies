<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Bookstore - ${product.title}</title>
</head>
<body>
	<article id="${product.id}">
		<h1>${product.title}</h1>
		<p>${product.description}</p>
		<p>Pages: ${product.pages}</p>
		<p>
			Release Date:
			<fmt:formatDate pattern="dd/MM/yyyy"
				value="${product.releaseDt.time}" />
		</p>
	</article>
	<section>
		<form action='<c:url value="/cart/add" />' method="POST">
			<input type="hidden" value="${product.id}" name="productId">
			<!-- Radio button to select the price -->
			<p>
				<strong>Prices</strong>
			</p>
			<c:forEach items="${product.prices}" var="price">
				<input type="radio" name="priceType" value="${price.pType}" required="required"/>${price.pType}<br>
				<p>${price.amount}</p>
			</c:forEach>
			<button type="submit" title="Buy Now ${product.title}">Buy</button>
		</form>
		<a href='<c:url value="/product" />'>Back to List of Products</a>
	</section>

</body>
</html>