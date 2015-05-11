<%@ page language="java"%>

<!-- header -->
<jsp:include page="/utils/header.jsp">
	<jsp:param name="title" value="GiMi & Co" />
	<jsp:param name="page" value="/index.jsp" />
</jsp:include>

<%
	if (session.getAttribute("user") != null) {
		RequestDispatcher rd = request
				.getRequestDispatcher("index.jsp");
		rd.forward(request, response);
	} else {
%>
<!-- container -->
<div class="jumbotron text-center">

	<div class="row">
		<div class="col-lg-offset-4 col-lg-4">
			<h3>
				<strong><em>Inscription</em></strong>
			</h3>

			<form data-toggle="validator" role="form"
				action="/Projet/SignUpServlet" method="POST">
				<div class="form-group">
					<label for="inputName" class="control-label">Nom</label> <input
						type="text" class="form-control" id="nom"
						placeholder="Entrez votre nom" name="nom" required>
				</div>
				<div class="form-group">
					<label for="inputFirtName" class="control-label">Prénom</label> <input
						type="text" class="form-control" id="prenom"
						placeholder="Entrez votre prénom" name="prenom" required>
				</div>
				<div class="form-group">
					<label for="inputEmail" class="control-label">Email</label> <input
						type="email" class="form-control" id="email"
						placeholder="Entrez votre adresse e-mail"
						data-error="Bruh, adresse email invalide !" name="email" required>
					<div class="help-block with-errors"></div>
					<div class="form-group">
						<label for="inputPassword" class="control-label">Mot de
							passe</label> <input type="password" data-minlength="6"
							class="form-control" id="password"
							placeholder="Entrez votre mot de passe" name="password" required>
						<span class="help-block">6 caractères minimum</span>
					</div>
					<div class="form-group">
						<label for="inputPasswordConfirm" class="control-label">Confirmation
							de mot de passe</label> <input type="password" class="form-control"
							id="inputPasswordConfirm" data-match="#password"
							data-match-error="Oups, les mots de passes ne correspondent pas !"
							placeholder="Confirmation de mot de passe" required>
						<div class="help-block with-errors"></div>
					</div>
				</div>
				<div class="form-group">
					<button type="submit" class="btn btn-success">
						<span class="glyphicon glyphicon-pencil"></span> Envoyer
					</button>
				</div>
			</form>
		</div>
	</div>
</div>
<%
	}
%>
<!-- footer -->
<jsp:include page="/utils/footer.jsp" />