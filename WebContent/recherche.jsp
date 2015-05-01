<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>ProjetJEE</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="/Projet/dist/bootstrap/3.3.4/css/bootstrap.min.css">
<link href="/Projet/dist/bootstrap-switch.css" rel="stylesheet">
<link rel="stylesheet" href="/Projet/dist/jqueryui/1.11.4/jquery-ui.css">
<style type="text/css" title="currentStyle">
	@import "/Projet/dist/DataTables/media/css/demo_page.css";
	@import "/Projet/dist/DataTables/media/css/demo_table_jui.css";
	@import "/Projet/dist/DataTables/examples/examples_support/themes/smoothness/jquery-ui-1.8.4.custom.css";
	
</style>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="/Projet/dist/jquery/2.1.3/jquery.min.js"></script>
<script src="/Projet/dist/DataTables/media/js/jquery.dataTables.js"></script>
<script src="/Projet/dist/bootstrap/3.3.4/js/bootstrap.min.js"></script>

<script type="text/javascript" charset="utf-8">
		$(document).ready(function() {
			oTable = $('#example').dataTable( {
				"bJQueryUI": true,
				"sPaginationType": "full_numbers",
			    "sAjaxSource": "/Projet/Recherche",
				"oLanguage": {
					"sUrl": "dist/DataTables/langue/langue_FR.txt"
				}
			})
		});
</script>
</head>
<body>

	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand"
					href="<%=request.getContextPath()%>/index.jsp"></a>
			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<!-- EMPTY -->
				</ul>
				<div class="navbar-header">
					<a class="navbar-brand"
						href="<%=request.getContextPath()%>/index.jsp">GiMi & Co</a>
				</div>
				<%
					if (session.getAttribute("name") == null) {
				%>
				<form class="navbar-form form-inline pull-right"
					action="/Projet/LoginServlet" method="post">
					<input type="text" class="form-control" placeholder="E-mail"
						name="login"> <input type="password" class="form-control"
						placeholder="Mot de passe" name="password">
					<button type="submit" class="btn btn-primary">Se connecter</button>
				</form>
				<%
					} else {
				%>
				<ul class="nav navbar-nav navbar-right">
					<li><a href=messageBox.jsp><span class="glyphicon glyphicon-envelope" style="color:blue"></span></a> </li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-expanded="false"><%=session.getAttribute("name")%><span
							class="caret"></span></a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="/Projet/ShowProfil">Informations
									sur le compte</a></li>
							<li><a href="/Projet/LogoutServlet">Déconnexion</a></li>
						</ul></li>						
				</ul>
				<%
					}
				%>
			</div>
		</div>
	</nav>

<div class="container">
<%
	if (session.getAttribute("name") == null) {
		RequestDispatcher rd = request
				.getRequestDispatcher("index.jsp");
		rd.forward(request, response);
	} else {
%>
<!-- container -->
<table cellpadding="0" cellspacing="0" border="0" class="display" id="example">
	<thead>
		<tr>
			<th width="15%">Nom</th>
			<th width="15%">Titre</th>
			<th width="15%">Email</th>
			<th width="15%">Type</th>
			<th width="15%">Personalité</th>
			<th width="5%">Niveau</th>
			<th width="10%">Disponibilité</th>
			<th width="10%">Site</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td colspan="5" class="dataTables_empty">Loading data from server</td>
		</tr>
	</tbody>
	<tfoot>
		<tr>
			<th>Nom</th>
			<th>Titre</th>
			<th>Email</th>
			<th>Type</th>
			<th>Personalité</th>
			<th>Niveau</th>
			<th>Disponibilité</th>
			<th>Site</th>
		</tr>
	</tfoot>
</table>
<%
	}
%>
<!-- footer -->
<jsp:include page="/utils/footer.jsp" />