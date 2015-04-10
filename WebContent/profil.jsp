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
		<section class="col-lg-9 text-left">
			<form class="well">
				<legend>Mon profil</legend>
				<div class="row">
					<p class="col-lg-6">Coltrane</p>
					<p class="col-lg-6">John</p>
					<p class="col-lg-6">a@b.com</p>
					<p class="col-lg-6">Saint Etienne du Rouvray, France</p>
				</div>
			</form>
		</section>

		<section class="col-lg-offset-1 col-lg-2">
			<div class="row">
				<p></p>
				<a href=recherche.jsp><button type="button"
						class="btn btn-block btn-primary">Retour à la recherche</button></a>
				<p></p>
				<a href=modif-compte.jsp><button type="button"
						class="btn btn-block btn-warning">
						<span class="glyphicon glyphicon-pencil"></span> Modifier mon
						profil
					</button></a>
				<p></p>
				<button type="button" href="#suppressionCompte"
					class="btn btn-block btn-danger" data-toggle="modal">
					<span class="glyphicon glyphicon-remove"></span> Supprimer le
					compte
				</button>
				<div class="modal fade" id="suppressionCompte">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">X</button>
								<h4 class="modal-title text-left">Confirmation de la
									suppression</h4>
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
				<p></p>
				<button type="button" class="btn btn-block btn-primary"
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
								<div class="form-group text-left">
									<label for="inputTitreMessage" class="control-label">Titre du message</label> <input type="text" class="form-control"
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
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-success"
									data-dismiss="modal">
									<span class="glyphicon glyphicon-envelope"></span> Envoyer un message
								</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>

		<section class="col-lg-9 text-left">
			<form class="well">
				<legend>Récapitulatif des compétences / passions</legend>
				<div class="row">
					<section class="well col-lg-6">
						<legend id="nomCompetence1">Saxophoniste</legend>
						<p class="col-lg-12 text-left">Joue du saxophone comme
							personne !</p>
						<button type="button" data-toggle="modal"
							href="#modifierCompetence"
							class="col-lg-offset-2 col-lg-4 btn btn-warning">
							<span class="glyphicon glyphicon-pencil"></span> Modifier
						</button>
						<div class="modal fade" id="modifierCompetence">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal">X</button>
										<h4 class="modal-title text-left">Modification d'une
											compétence</h4>
									</div>
									<div class="modal-body">
										<div class="form-group text-left">
											<label for="inputNameCompetence" class="control-label">Nom
												de la compétence</label> <input type="text" class="form-control"
												id="nomCompetence" value="Nom de la compétence"
												name="nomCompetence" required>
										</div>
										<div class="form-group text-left">
											<label for="inputNiveauCompetence" class="control-label">Niveau
												de la compétence</label> <input type="text" class="form-control"
												id="niveauCompetence" value="Niveau de la compétence"
												name="niveauCompetence" required>
										</div>
										<div class="form-group text-left">
											<label for="inputDescriptifCompetence" class="control-label">Descriptif
												de la compétence</label>
											<textarea rows="4" class="form-control"
												id="descriptifCompetence"
												placeholder="Descriptif de la compétence (e.g. Projets associés)"
												name="descriptifCompetence" required></textarea>
										</div>
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-warning"
											data-dismiss="modal">
											<span class="glyphicon glyphicon-pencil"></span> Modifier
										</button>
									</div>
								</div>
							</div>
						</div>
						<p class="col-lg-1"></p>
						<button type="button" data-toggle="modal"
							href="#suppressionCompetence" class="col-lg-4 btn btn-danger">
							<span class="glyphicon glyphicon-remove"></span> Supprimer
						</button>
						<div class="modal fade" id="suppressionCompetence">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal">X</button>
										<h4 class="modal-title text-left">Confirmation de la
											suppression</h4>
									</div>
									<div class="modal-body">Voulez-vous supprimer cette
										compétence (Saxophoniste) ?</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-danger"
											data-dismiss="modal">
											<span class="glyphicon glyphicon-remove"></span> Confirmer la
											suppression
										</button>
									</div>
								</div>
							</div>
						</div>
					</section>
					<section class="well col-lg-6">
						<legend>Cuisiner</legend>
						<p class="col-lg-12 text-left">N'est pas fort du tout en
							cuisine ...</p>
						<button type="button" data-toggle="modal" href="#modifierPassion"
							class="col-lg-offset-2 col-lg-4 btn btn-warning">
							<span class="glyphicon glyphicon-pencil"></span> Modifier
						</button>
						<div class="modal fade" id="modifierPassion">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal">X</button>
										<h4 class="modal-title text-left">Modification d'une
											passion</h4>
									</div>
									<div class="modal-body">
										<div class="form-group text-left">
											<label for="inputNamePassion" class="control-label">Nom
												de la passion</label> <input type="text" class="form-control"
												id="nomPassion" value="Nom de la passion" name="nomPassion"
												required>
										</div>
										<div class="form-group text-left">
											<label for="inputNiveauPassion" class="control-label">Niveau
												de la passion</label> <input type="text" class="form-control"
												id="niveauPassion" value="Niveau de la passion"
												name="niveauPassion" required>
										</div>
										<div class="form-group text-left">
											<label for="inputDescriptifPassion" class="control-label">Descriptif
												de la passion</label>
											<textarea rows="4" class="form-control"
												id="descriptifPassion"
												placeholder="Descriptif de la passion (e.g. Projets associés)"
												name="descriptifPassion" required></textarea>
										</div>
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-warning"
											data-dismiss="modal">
											<span class="glyphicon glyphicon-pencil"></span> Modifier
										</button>
									</div>
								</div>
							</div>
						</div>
						<p class="col-lg-1"></p>
						<button type="button" data-toggle="modal"
							href="#suppressionPassion" class="col-lg-4 btn btn-danger">
							<span class="glyphicon glyphicon-remove"></span> Supprimer
						</button>
						<div class="modal fade" id="suppressionPassion">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal">X</button>
										<h4 class="modal-title text-left">Confirmation de la
											suppression</h4>
									</div>
									<div class="modal-body">Voulez-vous supprimer cette
										passion (Cuisine) ?</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-danger"
											data-dismiss="modal">
											<span class="glyphicon glyphicon-remove"></span> Confirmer la
											suppression
										</button>
									</div>
								</div>
							</div>
						</div>
					</section>
				</div>
			</form>
		</section>

		<section class="col-lg-1"></section>

		<section class="col-lg-2">
			<div class="row">
				<p></p>
				<button type="button" href="#ajouterCompetence" data-toggle="modal"
					class="btn btn-block btn-success">Ajouter une compétence</button>
				<div class="modal fade" id="ajouterCompetence">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">X</button>
								<h4 class="modal-title text-left">Ajout d'une compétence</h4>
							</div>
							<div class="modal-body">
								<div class="form-group text-left">
									<label for="inputNameCompetence" class="control-label">Nom
										de la compétence</label> <input type="text" class="form-control"
										id="nomCompetence" placeholder="Nom de la compétence"
										name="nomCompetence" required>
								</div>
								<div class="form-group text-left">
									<label for="inputNiveauCompetence" class="control-label">Niveau
										de la compétence</label> <input type="text" class="form-control"
										id="niveauCompetence" placeholder="Niveau de la compétence"
										name="niveauCompetence" required>
								</div>
								<div class="form-group text-left">
									<label for="inputDescriptifCompetence" class="control-label">Descriptif
										de la compétence</label>
									<textarea rows="4" class="form-control"
										id="descriptifCompetence"
										placeholder="Descriptif de la compétence (e.g. Projets associés)"
										name="descriptifCompetence" required></textarea>
								</div>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-success"
									data-dismiss="modal">
									<span class="glyphicon glyphicon-pencil"></span> Ajouter
								</button>
							</div>
						</div>
					</div>
				</div>
				<p></p>
				<button type="button" href="#ajouterPassion" data-toggle="modal"
					class="btn btn-block btn-success">Ajouter une passion</button>
				<div class="modal fade" id="ajouterPassion">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">X</button>
								<h4 class="modal-title text-left">Ajout d'une passion</h4>
							</div>
							<div class="modal-body">
								<div class="form-group text-left">
									<label for="inputNamePassion" class="control-label">Nom
										de la passion</label> <input type="text" class="form-control"
										id="nomPassion" placeholder="Nom de la passion"
										name="nomPassion" required>
								</div>
								<div class="form-group text-left">
									<label for="inputNiveauPassion" class="control-label">Niveau
										de la passion</label> <input type="text" class="form-control"
										id="niveauPassion" placeholder="Niveau de la passion"
										name="niveauPassion" required>
								</div>
								<div class="form-group text-left">
									<label for="inputDescriptifPassion" class="control-label">Descriptif
										de la passion</label>
									<textarea rows="4" class="form-control" id="descriptifPassion"
										placeholder="Descriptif de la passion (e.g. Projets associés)"
										name="descriptifPassion" required></textarea>
								</div>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-success"
									data-dismiss="modal">
									<span class="glyphicon glyphicon-pencil"></span> Ajouter
								</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>
	</div>
</div>
<%
	}
%>
<!-- footer -->
<jsp:include page="/utils/footer.jsp" />