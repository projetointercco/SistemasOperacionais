<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Sistemas Operacionais | Escalonador e Memoria</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">
</head>

<body>
	<!-- Barra superior com os menus de navegação -->
	<c:import url="Menu.jsp" />
	<!-- Container Principal -->
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
			</div>
		</div>

		<h3 class="page-header">
			Adicionar Processo <span class="badge progress-bar-info">Novo</span>
		</h3>

		<c:if test="${not empty memoriaIndisponivel}">
			<div class="alert alert-danger" role="alert">O processo
				n&atilde;o foi inserido, pois n&atilde;o h&aacute; mem&oacute;ria
				dispon&iacute;vel.</div>
		</c:if>

		<form action="adicionar_processo" method="post">
			<!--  Nome do Processo -->
			<div class="row">
				<div class="form-group col-md-12">
					<label for="nome">Nome:</label> <input type="text"
						class="form-control" name="nome" required maxlength="15"
						placeholder="Nome do processo">
					<form:errors path="processo.nome" cssStyle="color:red" />
				</div>
			</div>

			<!--  Duração do Processo -->
			<div class="row">
				<div class="col">
					<div class="form-group col-md-6">
						<label for="duracao">Duração:</label> <input type="number" min="1"
							max="100" class="form-control" name="duracao" required
							placeholder="Dura&ccedil;&atilde;o do processo">
						<form:errors path="processo.duracao" cssStyle="color:red" />
					</div>
				</div>

				<!--  Prioridade do Processo -->
				<div class="col">
					<div class="form-group col-md-6">
						<label for="prioridade">Prioridade:</label> <select
							class="form-control" name="prioridade.id">
							<c:forEach var="prioridade" items="${prioridades}">
								<option value="${prioridade.id}">${prioridade.nome}</option>
							</c:forEach>
						</select>
					</div>
				</div>
			</div>

			<div id="actions" class="row">
				<div class="col-md-12">
					<button type="submit" class="btn btn-primary" name="acao"
						value="Salvar">Salvar</button>
					<a href="index" class="btn btn-danger">Cancelar</a>
				</div>
			</div>
		</form>
	</div>
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>

</html>