<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.Utilisateur" %>
<!-- header -->
<jsp:include page="/utils/header.jsp">
	<jsp:param name="title" value="GiMi & Co" />
	<jsp:param name="page" value="/index.jsp" />
</jsp:include>

<%
	if (session.getAttribute("name") == null) {
		RequestDispatcher rd = request
				.getRequestDispatcher("index.jsp");
		rd.forward(request, response);
	} else {
%>
<!-- container -->
<div class="jumbotron text-center">
	<div class="row">
		<section class="col-lg-9 text-left">
			<form class="well">
				<legend>Mon profil</legend>
				<div class="row">
					<p class="col-lg-6"><%=((Utilisateur)session.getAttribute("otherUser")).getNom()%></p>
					<p class="col-lg-6"><%=((Utilisateur)session.getAttribute("otherUser")).getPrenom()%></p>
					<p class="col-lg-6"><%=((Utilisateur)session.getAttribute("otherUser")).getEmail()%></p>
					<p class="col-lg-6"><%=((Utilisateur)session.getAttribute("otherUser")).getLieuDeTravail()%></p>
				</div>
			</form>
		</section>

		<section class="col-lg-offset-1 col-lg-2">
			<div class="row">
				<p></p>
				<a href="Recherche"><button type="button"
						class="btn btn-block btn-primary">Retour à la recherche</button></a>
				<p></p>
				
				<p></p>
				<button type="button" class="btn btn-block btn-success"
					href="#envoyerMessage" data-toggle="modal">Envoyer un
					message</button>
				<div class="modal fade" id="envoyerMessage">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">X</button>
								<h4 class="modal-title text-left">Envoyer un message</h4>
							</div>
							<div class="modal-body">
							<form data-toggle="validator" role="form" action="/Projet/SendMessage" method="POST">
								<div class="form-group text-left">
									<label for="inputTitreMessage" class="control-label">Titre du message</label> 
									<input type="text" class="form-control"
										id="titreMessage" placeholder="Titre du message"
										name="titreMessage" required>
								</div>
								<div class="form-group text-left">
									<label for="inputMessage" class="control-label">Message</label>
									<textarea rows="4" class="form-control"
										id="inputMessage"
										placeholder="Message"
										name="inputMessage" required></textarea>
								</div>
								<div class="form-group text-right">
									<button type="submit" class="btn btn-primary">
										<span class="glyphicon glyphicon-envelope"></span> Envoyer un message
									</button>
								</div>
							</form>
							</div>
							
						</div>
					</div>
				</div>
			</div>
		</section>

		<div class="col-lg-9 text-left">
			<table class="table table-bordered table-striped text-left">
				<caption>
					<h4>Mes capacités</h4>
				</caption>
				<thead>
					<tr>
						<th>Nom</th>
						<th>Type</th>
						<th>Niveau</th>
						<th>Description</th>
					</tr>
				</thead>
				<tbody>
					<%=session.getAttribute("otherUserPersonalitesTable")%>
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