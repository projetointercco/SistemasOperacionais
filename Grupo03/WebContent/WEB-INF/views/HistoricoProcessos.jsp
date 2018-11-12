<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- atualiza a página a cada 2 segundos -->
<meta http-equiv="refresh" content="2">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Sistemas Operacionais | Escalonador e Memoria</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">
</head>

<body>

	<!-- Barra superior com os menus de navegação -->
	<c:import url="Menu.jsp" />

	<!--  container principal -->
	<!--  Processos em execução -->
	<div id="main" class="container">
		<br />
		<h3 class="page-header">
			Processos <span class="badge progress-bar-warning">Todos</span>
		</h3>
		<c:if test="${not empty processos}">
			<div class="table-responsive col-md-12">
				<table class="table table-striped table-hover">
					<thead>
						<tr>
							<th>#</th>
							<th>Nome</th>
							<th>Duração</th>
							<th>Prioridade</th>
							<th>Início</th>
							<th>Fim</th>
							<th>Estado</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="processo" items="${processos}">
							<tr>
								<td align="left">${processo.id}</td>
								<td align="left">${processo.nome }</td>
								<td align="left">${processo.duracao }</td>
								<td align="left">${processo.prioridade.nome }</td>
								<td align="left"><fmt:formatDate
										value="${processo.horaInicio }" pattern="dd/MM/yyyy HH:mm:ss" /></td>
								<td align="left"><fmt:formatDate
										value="${processo.horaFim }" pattern="dd/MM/yyyy HH:mm:ss" /></td>
								<td align="left">${processo.estado.nome }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</c:if>
		<c:if test="${empty processos}">
			<div class="alert alert-info" role="alert">N&atilde;o h&aacute;	processos para serem exibidos.</div>
		</c:if>
	</div>

	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>

</html>