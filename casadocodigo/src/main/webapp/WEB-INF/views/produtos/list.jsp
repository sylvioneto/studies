<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<c:url value="/" var="contextPath" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Casa do Codigo - Lista de Produtos</title>
</head>
<body>
	<h1>Lista de Produtos</h1>
	<p> ${sucesso} </p>
	
	<table>
		<tr>
			<td>Titulo</td>
			<td>Descricao</td>
			<td>Pagina</td>
			<td>Data Lancamento</td>
		</tr>
		<c:forEach items="${produtos}" var="produto">
			<tr>
				<td>
					<a href="${contextPath}produtos/detalhe/${produto.id}">${produto.titulo}</a>
				</td>
				<td>${produto.descricao}</td>
				<td>${produto.paginas}</td>
				<td><fmt:formatDate pattern="dd/MM/yyyy" value="${produto.dataLancamento.time}"/></td>
			</tr>
		</c:forEach>
	</table>
	<p> Seu carrinho (${carrinhoCompras.quantidade})</p>
</body>
</html>