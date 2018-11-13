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
		<div class="container">
			<div class="row">
				<div class="col-md-3">
					<!-- <button type="button" class="btn btn-lg btn-success"> -->
					<h4>
						Mem&oacute;ria Total: <span class="badge badge-light">${memoriaTotal }</span>
					</h4>
					<!--  </button> -->
				</div>
				<div class="col-md-3">
					<!--  <button type="button" class="btn btn-lg btn-warning"> -->
					<h4>
						Mem&oacute;ria Dispon&iacute;vel: <span class="badge badge-light">${memoriaDisponivel }</span>
					</h4>
					<!-- </button> -->
				</div>
				<div class="col-lg-6 offset-col-md-3">
					<div class="btn-group" role="group">
						<a href="index_estatico" class="btn btn-lg btn-warning disabled">Pausar</a>
						<a href="index" class="btn btn-lg btn-success">Retomar</a>
					</div>
				</div>
			</div>
		</div>

		<h3 class="page-header">
			Processos <span class="badge progress-bar-success">em Execução</span>
		</h3>
		<c:if test="${not empty processosExecutando}">
			<div class="table-responsive col-md-12">
				<table class="table table-striped table-hover">
					<thead>
						<tr>
							<th>#</th>
							<th>Nome</th>
							<th>Dura&ccedil;&atilde;o</th>
							<th>Dura&ccedil;&atilde;o Atual</th>
							<th>Prioridade</th>
							<th>In&iacute;cio</th>
							<th>Fim</th>
							<th>Estado</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="processo" items="${processosExecutando}">
							<tr>
								<td align="left">${processo.id}</td>
								<td align="left">${processo.nome }</td>
								<td align="left">${processo.duracao }</td>
								<td align="left">${processo.duracaoAtual }</td>
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
		<c:if test="${empty processosExecutando}">
			<div class="alert alert-info" role="alert">N&atilde;o h&aacute;
				processos para serem exibidos.</div>
		</c:if>
	</div>

	<!--  Processos na fila -->
	<div id="main" class="container">
		<h3 class="page-header">
			Processos <span class="badge progress-bar-danger">na Fila</span>
		</h3>
		<c:if test="${not empty processos}">
			<div class="table-responsive col-md-12">
				<table class="table table-striped table-hover">
					<thead>
						<tr>
							<th>#</th>
							<th>Nome</th>
							<th>Dura&ccedil;&atilde;o</th>
							<th>Prioridade</th>
							<th>In&iacute;cio</th>
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
			<div class="alert alert-info" role="alert">N&atilde;o h&aacute;
				processos para serem exibidos.</div>
		</c:if>
	</div>



	<!--  Particoes na memoria -->
	<div id="main" class="container">
		<h3 class="page-header">
			Parti&ccedil;&otilde;es <span class="badge progress-bar-info">na
				Mem&oacute;ria</span>
		</h3>
		<c:if test="${not empty particoes}">
			<div class="table-responsive col-md-12">
				<table class="table table-striped table-hover">
					<thead>
						<tr>
							<th>Tamanho</th>
							<th>Disponibilidade</th>
							<th>Processo</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="particao" items="${particoes}">
							<tr>
								<td align="left">${particao.tamanho}</td>
								<td align="left">${particao.disponibilidade }</td>
								<td align="left">${particao.processo.nome }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</c:if>
		<c:if test="${empty particoes}">
			<div class="alert alert-info" role="alert">N&atilde;o h&aacute;
				parti&ccedil;&otilde;es para serem exibidas.</div>
		</c:if>
	</div>

	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>

</html>