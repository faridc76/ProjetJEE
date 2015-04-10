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

	<form data-toggle="validator" role="form"
		action="/Projet/RequestSignUpServlet" method="POST">

		<div class="row">
			<div class="col-lg-offset-4 col-lg-4">
				<h3>
					<strong><em>Modification du compte</em></strong>
				</h3>
			</div>

			<section class="col-lg-offset-1 col-lg-4">
				<div class="row">
					<div class="form-group">
						<label for="inputTitre" class="control-label">Titre</label> <input
							type="text" class="form-control" id="titre" value="Ingénieur"
							name="titre" required>
					</div>
					<div class="form-group">
						<label for="inputFirtName" class="control-label">Prénom</label> <input
							type="text" class="form-control" id="prenom" value="Prénom"
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
							de naissance</label> <input value="08/01/1993" type="text"
							class="form-control" id="datepicker" name="dateDeNaissance"
							required>
					</div>
					<div class="form-group">
						<label for="inputNumeroPortable" class="control-label">Numéro
							de téléphone portable</label> <input type="text"
							class="form-control bfh-phone" id="numeroPortable"
							value="235112233" name="numeroPortable"
							data-format="+33 d dd dd dd dd" required>
					</div>
				</div>
			</section>

			<section class="col-lg-2"></section>

			<section class="col-lg-4">
				<div class="row">
					<div class="form-group">
						<label for="inputEmail" class="control-label">Email</label> <input
							type="email" class="form-control" id="email" value="a@b.com"
							data-error="Bruh, adresse email invalide !" name="email" required>
						<div class="help-block with-errors"></div>
					</div>
					<div class="form-group">
						<label for="inputName" class="control-label">Nom</label> <input
							type="text" class="form-control" id="nom" value="Nom" name="nom"
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
							id="lieuDeTravail" value="Saint Etienne du Rouvray, France"
							name="lieuDeTravail" required>
					</div>
					<div class="form-group">
						<label for="inputNumeroPortable" class="control-label">Numéro
							de téléphone fixe</label> <input type="text"
							class="form-control bfh-phone" id="numeroPortable"
							value="644556677" name="numeroPortable"
							data-format="+33 d dd dd dd dd" required>
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
									data-off-text="<span class='glyphicon glyphicon-remove'></span>"
									checked>
							</div>
						</section>
						<section class="col-lg-4">
							<div class="make-switch">
								<input type="radio" name="radio" data-on-color="success"
									data-off-color="danger"
									data-on-text="
									<span class='glyphicon glyphicon-ok'></span>"
									data-label-text="Peu disponible"
									data-off-text="<span class='glyphicon glyphicon-remove'></span>">
							</div>
						</section>
						<section class="col-lg-4">
							<div class="make-switch">
								<input type="radio" name="radio" data-on-color="success"
									data-off-color="danger"
									data-on-text="<span class='glyphicon glyphicon-ok'></span>"
									data-label-text="Indisponible"
									data-off-text="<span class='glyphicon glyphicon-remove'></span>">
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
	</form>
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