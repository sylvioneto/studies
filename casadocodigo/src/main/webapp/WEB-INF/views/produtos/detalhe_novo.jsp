<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Casa do Codigo - Cadastro de Produtos</title>
</head>
<body>
	<h1>Detalhes de Produtos</h1>
		<div>
			<label>Titulo</label>
			<form:input path="produto.titulo" readonly="true" />
		</div>
		<div>
			<label>Descricao</label>
			<form:textarea path="produto.descricao" rows="10" cols="20" ></form:textarea>
		</div>
		<div>
			<label>Paginas</label> 
			<form:input  path="produto.paginas" />
		</div>
		<div>
		    <label>Data de Lançamento</label>
		    <fmt:formatDate pattern="dd/MM/yyyy" value="${produto.dataLancamento.time}"/>
		</div>
		<c:forEach items="${produto.precos}" var="preco" varStatus="status">
			<div>
				<label>${preco.tipo}</label>
				<form:input path="produto.precos[${status.index}].valor" />
			</div>
		</c:forEach>
		<div>
			<label>Sumário</label>
			<input name="sumario" value="${produto.sumarioPath}"/>
		</div>
</body>
</html>