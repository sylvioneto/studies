<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Casa do Codigo - Cadastro de Produtos</title>
</head>
<body>
	<form action="/casadocodigo/produtos" method="POST">
		<div>
			<label>Titulo</label> <input type="text" name="titulo" />
		</div>
		<div>
			<label>Descricao</label>
			<textarea rows="10" cols="20" name="descricao"></textarea>
		</div>
		<div>
			<label>Paginas</label> <input type="text" name="paginas" />
		</div>
		<c:forEach items="${tipos}" var="tipoPreco" varStatus="status">
			<div>
				<label>${tipoPreco}</label> 
				<input type="text" name="precos[${status.index}].valor"/>
				<input type="hidden" name="precos[${status.index}].tipo" value="${tipoPreco}"/>
			</div>
		</c:forEach>
		<button type="submit">Cadastrar</button>
	</form>
</body>
</html>