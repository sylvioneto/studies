<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Bookstore - Product maintenance</title>
</head>
<body>
	<h2>Product maintenance</h2>
	<form:form action="/bookstore/product" method="POST" modelAttribute="product" enctype="multipart/form-data"> 	
		<div>
			<label>Title</label>
			<form:input  path="title" />
			<form:errors path="title" />
		</div>
		<div>
			<label>Description</label>
			<form:textarea path="description" rows="10" cols="20" ></form:textarea>
			<form:errors path="description" />
		</div>
		<div>
			<label>Pages</label> 
			<form:input  path="pages" />
			<form:errors path="pages" />
		</div>
		<div>
		    <label>Release Date</label>
		    <form:input  path="releaseDt" />
		    <form:errors path="releaseDt" />
		</div>
		<c:forEach items="${prices}" var="price" varStatus="status">
			<div>
				 <label>${price}</label>
				 <form:input path="prices[${status.index}].amount" />
				 <form:hidden path="prices[${status.index}].pType" value="${price}" />
			</div>
		</c:forEach>
		<div>
			<label>Summary file</label>
			<input name="summary" type="file"/>
		</div>
		<button type="submit">Save</button>
	</form:form>
</body>
</html>