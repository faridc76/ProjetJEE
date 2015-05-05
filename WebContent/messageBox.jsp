<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- header -->
<jsp:include page="/utils/header.jsp">
	<jsp:param name="title" value="GiMi & Co" />
	<jsp:param name="page" value="/index.jsp" />
</jsp:include>
<script type="text/javascript">
function idValue (value) {
	document.getElementById('formReponse').action = "/Projet/SendMessage?id=" + value;
	document.getElementById('formSuppression').action = "/Projet/DeleteMessage?id=" + value;
}
</script>

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
			<a href=recherche.jsp><button class="btn btn-default">Rechercher</button></a>
			<p></p>
		</div>
		<div class="modal fade" id="envoyerMessage">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">X</button>
						<h4 class="modal-title text-left">Envoyer un message</h4>
					</div>
					<div class="modal-body">
						<form data-toggle="validator" role="form" id="formReponse" 
						 method="POST">
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
						<div class="form-group text-right">
							<button type="submit" class="btn btn-primary">
								<span class="glyphicon glyphicon-envelope"></span> Envoyer un
								message
							</button>
						</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		<div class="modal fade" id="suppressionMessage">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">X</button>
						<h4 class="modal-title text-left">Confirmation de la
									suppression</h4>
					</div>
					<div class="modal-body">Voulez-vous supprimer le message?</div>
					<div class="modal-footer">
						<form id="formSuppression" method="POST">
							<button type="submit" class="btn btn-danger">
								<span class="glyphicon glyphicon-remove"></span> Confirmer
							</button>
						</form>
					</div>
				</div>
			</div>
		</div>
		<div class="col-lg-12">
			<table class="table table-bordered table-striped text-left">
				<caption></caption>
				<thead>
					<tr>
						<th>De</th>
						<th>Titre</th>
						<th>Date</th>
						<th>Message</th>
						<th>Statut</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>
					<%=session.getAttribute("userMessagesTable")%>
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