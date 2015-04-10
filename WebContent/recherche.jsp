<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- header -->
<jsp:include page="/utils/header.jsp">
	<jsp:param name="title" value="GiMi & Co" />
	<jsp:param name="page" value="/index.jsp" />
</jsp:include>

<%
	if (session.getAttribute("name") != null) {
		RequestDispatcher rd = request
				.getRequestDispatcher("index.jsp");
		rd.forward(request, response);
	} else {
%>
<!-- container -->
<div class="jumbotron text-center">
	<div class="row">
		<div class="row">
			<section class="col-lg-9 ">
				<form data-toggle="validator" role="form"
					action="/Projet/RequestSignUpServlet" method="POST">
					<div class="row">
						<div class="form-group">
							<h5 class="col-lg-2">
								<strong>Recherche : </strong>
							</h5>
							<div class="col-lg-8">
								<input type="text" class="form-control" id="recherche"
									placeholder="Entrez un nom, une compétence ou une passion"
									name="recherche" required>
							</div>
							<button type="submit" class="col-lg-2 btn btn-default">
								<span class="glyphicon glyphicon-eye-open"></span> Rechercher
							</button>
						</div>
					</div>
				</form>
			</section>
			<section class="col-lg-offset-1 col-lg-2">
				<a href=profil.jsp><button type="button" class="btn btn-block btn-primary">Mon
					profil</button></a>
			</section>
		</div>
		<div class="col-lg-12">
			<table class="table table-bordered table-striped text-left">
				<caption>
					<h4>Résultats</h4>
				</caption>
				<thead>
					<tr>
						<th>Nom</th>
						<th>Prénom</th>
						<th>Titre</th>
						<th>E-mail</th>
						<th>Compétence</th>
						<th>Niveau</th>
						<th>Disponibilité</th>
						<th>Site</th>
						<th>Plus ..</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th>Chouakria</th>
						<th>Farid</th>
						<th>Fonctionaire</th>
						<th>farid.chouakria@gmail.com</th>
						<th>HTML</th>
						<th>5</th>
						<th>Disponible</th>
						<th>Rouen</th>
						<th>Sympathique</th>
					</tr>
					<tr>
						<th>Deneuve</th>
						<th>Alexandre</th>
						<th>Etudiant</th>
						<th>alexandre.deneuve@wanadoo.fr</th>
						<th>Bootstrap</th>
						<th>10</th>
						<th>Disponible</th>
						<th>Rouen</th>
						<th>Sympa</th>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</div>
<%
	}
%>
<!-- footer -->
<jsp:include page="/utils/footer.jsp" />