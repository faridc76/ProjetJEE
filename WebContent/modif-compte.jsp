<%@ page language="java"%>
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
			<div class="col-lg-offset-4 col-lg-4">
				<h3>
					<strong><em>Modification du compte</em></strong>
				</h3>
			</div>
			<form data-toggle="validator" role="form"
			action="/Projet/SaveUpdate" method="POST">
			<section class="col-lg-offset-1 col-lg-4">
				<div class="row">
					<div class="form-group">
						<label for="inputTitre" class="control-label">Titre</label> <input
							type="text" class="form-control" id="titre" value="<%=((Utilisateur)session.getAttribute("user")).getTitre() %>"
							name="titre" required>
					</div>
					<div class="form-group">
						<label for="inputFirtName" class="control-label">Prénom</label> <input
							type="text" class="form-control" id="prenom" value="<%=((Utilisateur)session.getAttribute("user")).getPrenom() %>"
							name="prenom" required>
					</div>
					<div class="form-group">
						<label for="inputPassword" class="control-label">Mot de
							passe</label> <input type="password" data-minlength="6"
							class="form-control" id="password" placeholder="Mot de passe"
							name="password" required> <span class="help-block">6
							caractères minimum</span>
					</div>
					<div class="form-group">
						<label for="inputDateDeNaissance" class="control-label">Date
							de naissance</label> <input value="<%=((Utilisateur)session.getAttribute("user")).getDate() %>" type="text"
							class="form-control" id="datepicker" name="dateDeNaissance"  readonly="true"
							required>
					</div>
					<div class="form-group">
						<label for="inputNumeroPortable" class="control-label">Numéro
							de téléphone portable</label> <input type="text"
							class="form-control bfh-phone" id="numeroPortable"
							value="<%=((Utilisateur)session.getAttribute("user")).getNumPortable() %>" name="numeroPortable"
							data-format="+33 d dd dd dd dd" required>
					</div>
				</div>
			</section>

			<section class="col-lg-2"></section>

			<section class="col-lg-4">
				<div class="row">
					<div class="form-group">
						<label for="inputEmail" class="control-label">Email</label> <input
							type="email" class="form-control" id="email" value="<%=((Utilisateur)session.getAttribute("user")).getEmail() %>"
							data-error="Bruh, adresse email invalide !" name="email" required>
						<div class="help-block with-errors"></div>
					</div>
					<div class="form-group">
						<label for="inputName" class="control-label">Nom</label> <input
							type="text" class="form-control" id="nom" value="<%=((Utilisateur)session.getAttribute("user")).getNom() %>" name="nom"
							required>
					</div>
					<div class="form-group">
						<label for="inputPasswordConfirm" class="control-label">Confirmation
							de mot de passe</label> <input type="password" class="form-control"
							id="inputPasswordConfirm" data-match="#password"
							data-match-error="Oups, les mots de passes ne correspondent pas !"
							placeholder="Confirmation de mot de passe" required> <span
							class="help-block"></span>
						<div class="help-block with-errors">
							</br>
						</div>
					</div>
					<div class="form-group">
						<label for="inputLieuDeTravail" class="control-label">Lieu
							de travail</label> <input type="text" class="form-control"
							id="lieuDeTravail" value="<%=((Utilisateur)session.getAttribute("user")).getLieuDeTravail() %>"
							name="lieuDeTravail" required>
					</div>
					<div class="form-group">
						<label for="inputNumeroFixe" class="control-label">Numéro
							de téléphone fixe</label> <input type="text"
							class="form-control bfh-phone" id="numeroFixe"
							value="<%=((Utilisateur)session.getAttribute("user")).getNumFix() %>" 
							name="numeroFixe" data-format="+33 d dd dd dd dd" required>
					</div>
				</div>
			</section>

			<section class="col-lg-offset-1 col-lg-10">
				</br>
				<div class="control-group">
					<div class="controls">
						<section class="col-lg-4">
							<div class="make-switch">
								<input type="radio" name="radio" data-on-color="success"
									data-off-color="danger"
									data-on-text="<span class='glyphicon glyphicon-ok'></span>"
									data-label-text="Disponible"
									value="0"
									data-off-text="<span class='glyphicon glyphicon-remove'></span>"
									<% if ((Integer)session.getAttribute("checked") == 0) {%>
										<%="checked" %>
									<% } %>
									>
							</div>
						</section>
						<section class="col-lg-4">
							<div class="make-switch">
								<input type="radio" name="radio" data-on-color="success"
									data-off-color="danger"
									data-on-text="<span class='glyphicon glyphicon-ok'></span>"
									data-label-text="Peu disponible"
									value="1"
									data-off-text="<span class='glyphicon glyphicon-remove'></span>"
									<% if ((Integer)session.getAttribute("checked") == 1) {%>
										<%="checked" %>
									<% } %>
									>
							</div>
						</section>
						<section class="col-lg-4">
							<div class="make-switch">
								<input type="radio" name="radio" data-on-color="success"
									data-off-color="danger"
									data-on-text="<span class='glyphicon glyphicon-ok'></span>"
									data-label-text="Indisponible"
									value="2"
									data-off-text="<span class='glyphicon glyphicon-remove'></span>"
									<% if ((Integer)session.getAttribute("checked") == 2) {%>
										<%="checked" %>
									<% } %>
									>
							</div>
						</section>
					</div>
				</div>
			</section>

			<div class="col-lg-offset-4 col-lg-4">
				</br>
				<div class="form-group">
					<button type="submit" class="btn btn-success">
						<span class="glyphicon glyphicon-pencil"></span> Envoyer
					</button>
				</div>
			</div>
			</form>
			</br>

			<div class="col-lg-offset-8 col-lg-4">

				<button type="button" href="#suppressionCompte"
					class="btn btn-danger" data-toggle="modal">
					<span class="glyphicon glyphicon-remove"></span> Supprimer le
					compte
				</button>
				<div class="modal fade" id="suppressionCompte">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">X</button>
								<h4 class="modal-title text-left">Confirmation de la suppression</h4>
							</div>
							<div class="modal-body">Voulez-vous supprimer votre profil
								?</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-danger"
									data-dismiss="modal">
									<span class="glyphicon glyphicon-remove"></span> Confirmer la
									suppression du compte
								</button>
			
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
</div>

<%
	}
%>
<script type="text/javascript">
	$("[name='radio']").bootstrapSwitch();
</script>
<script type="text/javascript">
	$("#datepicker").datepicker();
</script>
<!--

//-->
</script>
<!-- footer -->
<jsp:include page="/utils/footer.jsp" />