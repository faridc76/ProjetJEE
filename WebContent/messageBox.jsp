<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

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
		<div class="col-lg-9 text-left">
			<h3>Boite de réception</h3>
		</div>
		<div class="col-lg-offset-1 col-lg-2">
			<a href=recherche.jsp><button class="btn">Retour à la recherche</button></a>
			<p></p>
			<button type="button" class="btn btn-primary"
			href="#envoyerMessage" data-toggle="modal">Envoyer un
			message</button>
		</div>
		<div class="modal fade" id="envoyerMessage">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">X</button>
						<h4 class="modal-title text-left">Envoyer un message</h4>
					</div>
					<div class="modal-body">
						<div class="form-group text-left">
							<label for="inputDestinataireMessage" class="control-label">Destinataire
								</label> <input type="text" class="form-control"
								id="destinataireMessage" placeholder="Destinataire"
								name="destinataireMessage" required>
						</div>
						<div class="form-group text-left">
							<label for="inputTitreMessage" class="control-label">Titre
								du message</label> <input type="text" class="form-control"
								id="titreMessage" placeholder="Titre du message"
								name="titreMessage" required>
						</div>
						<div class="form-group text-left">
							<label for="inputMessage" class="control-label">Message</label>
							<textarea rows="4" class="form-control" id="inputMessage"
								placeholder="Message" name="inputMessage" required></textarea>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary" data-dismiss="modal">
							<span class="glyphicon glyphicon-envelope"></span> Envoyer un
							message
						</button>
					</div>
				</div>
			</div>
		</div>
		<div class="col-lg-12">
			<table class="table table-bordered table-striped text-left">
				<caption>
					<h4>Résultats</h4>
				</caption>
				<thead>
					<tr>
						<th>De</th>
						<th>Titre</th>
						<th>Date</th>
						<th>Message</th>
						<th>Pièce jointe</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th>Chouakria Farid</th>
						<th>JavaJEE</th>
						<th>10/04/2015</th>
						<th>Vite fait ta partie on est en retard !!</th>
						<th><a
							class="glyphicon glyphicon-arrow-down center-block btn btn-primary"
							href="#"></a></th>
						<th>
							<div class="btn-group">
								<a class="glyphicon glyphicon-share-alt btn btn-success"
									href="#"></a> <a
									class="glyphicon glyphicon-remove btn btn-danger" href="#"></a>
							</div>
						</th>
					</tr>
					<tr>
						<th>Serais Sébastien</th>
						<th>Retard Java</th>
						<th>09/04/2015</th>
						<th>Bonjour,<br>Veuillez rendre votre partie demain ou
							vous aurez la note de 0.<br> Cordialement,<br> Mr
							Serais
						</th>
						<th><a
							class="glyphicon glyphicon-arrow-down center-block btn btn-primary"
							href="#"></a></th>
						<th>
							<div class="btn-group">
								<a class="glyphicon glyphicon-share-alt btn btn-success"
									href="#"></a> <a
									class="glyphicon glyphicon-remove btn btn-danger" href="#"></a>
							</div>
						</th>
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