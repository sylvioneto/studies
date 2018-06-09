<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Bookstore</title>
</head>
<body>
	<h1>Bookstore home page</h1>
	<p>
		<a href="${contextPath}product/form">Add a new product</a>
	</p>
	<p>
		<a href="${contextPath}product">List of products</a>
	</p>
	<p>
		<a href="${contextPath}cart">Cart ${cart.quantity}</a>
	</p>
</body>
</html>