<%@ page language="java"%>
<%@ page import="model.Utilisateur" %>

<!-- header -->
<jsp:include page="/utils/header.jsp">
	<jsp:param name="title" value="GiMi & Co" />
	<jsp:param name="page" value="/index.jsp" />
</jsp:include>
<script type="text/javascript">
function idValue(value) {
	//On indique le lien est l'id de la personalite à modifier
	document.getElementById('modifierPersonaliteForm').action = "/Projet/UpdatePersonalite?id=" + value;
	//On remplit les champs avec les valeurs souhaité
	document.getElementById('nomPersonalite').value = document.getElementById('nomPersonalite_' + value).outerText;
	document.getElementById('niveauPersonalite').value = document.getElementById('niveauPersonalite_' + value).outerText;
	document.getElementById('descriptifPersonalite').value = document.getElementById('descriptifPersonalite_' + value).textContent;
	document.getElementById('typePersonalite').value = document.getElementById('typePersonalite_' + value).textContent;
	//On indique le lien avec l'id de la personalite à supprimer
	document.getElementById('supPersonaliteForm').action = "/Projet/DeletePersonalite?id=" + value;
	
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
		<section class="col-lg-9 text-left">
			<form class="well">
				<legend>Mon profil</legend>
				<div class="row">
					<p class="col-lg-6"><%=((Utilisateur)session.getAttribute("user")).getNom()%></p>
					<p class="col-lg-6"><%=((Utilisateur)session.getAttribute("user")).getPrenom()%></p>
					<p class="col-lg-6"><%=((Utilisateur)session.getAttribute("user")).getEmail()%></p>
					<p class="col-lg-6"><%=((Utilisateur)session.getAttribute("user")).getLieuDeTravail()%></p>
				</div>
			</form>
		</section>

		<section class="col-lg-offset-1 col-lg-2">
			<div class="row">
				<p></p>
				<a href=recherche.jsp><button type="button"
						class="btn btn-block btn-primary">Rechercher</button></a>
				<p></p>
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
								<form action="/Projet/DeleteUser">
								<button type="submit" class="btn btn-danger">
									<span class="glyphicon glyphicon-remove"></span> Confirmer la
									suppression du compte
								</button>
								</form>
							</div>
						</div>
					</div>
				</div>
				<p></p>
			</div>
		</section>
		
		<div class="modal fade" id="modifierPersonalite">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">X</button>
						<h4 class="modal-title text-left">Modifier Personalité</h4>
					</div>
					<div class="modal-body">
					<form data-toggle="validator" role="form" id="modifierPersonaliteForm" method="POST">
						<div class="form-group text-left">
							<label for="inputNamePersonalite" class="control-label">Nom de la Personalité</label>
							<input type="text" class="form-control" 
							id="nomPersonalite" placeholder="Nom de la personalité"
							name="nomPersonalite" required>
						</div>
						<div class="form-group text-left">
							<label for="inputTypePersonalite" class="control-label">Type de personalité</label>
							<select class="selectpicker form-control input-sm" id="typePersonalite" name="typePersonalite" required>
								<option value="Compétence">Compétence</option>
								<option value="Passion">Passion</option>
							</select>
						</div>
						<div class="form-group text-left">
							<label for="inputNiveauPersonalite" class="control-label">Niveau de la personalité (entre 0 et 10)</label>
							<input type="number" class="form-control"
							id="niveauPersonalite" placeholder="Niveau de la pérsonalité"  max="10" min="0"
							name="niveauPersonalite" required>
						</div>
						<div class="form-group text-left">
							<label for="inputDescriptifPersonalite" class="control-label">Descriptif de la personalité</label>
							<textarea rows="4" class="form-control" id="descriptifPersonalite"
							placeholder="Descriptif de la personalité (e.g. Projets associés)"
							name="descriptifPersonalite" required></textarea>
						</div>
						<div class="form-group text-right">
							<button type="submit" class="btn btn-success">
								<span class="glyphicon glyphicon-pencil"></span> Modifier
							</button>
						</div>
					</form>
					</div>
				</div>
			</div>
		</div>
		
		<div class="modal fade" id="suppressionPersonalite">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">X</button>
						<h4 class="modal-title text-left">Confirmation de la
									suppression</h4>
					</div>
					<div class="modal-body">Voulez-vous supprimer la personalité?</div>
					<div class="modal-footer">
						<form id="supPersonaliteForm" method="POST">
							<button type="submit" class="btn btn-danger">
								<span class="glyphicon glyphicon-remove"></span> Confirmer la
								suppression de la personalité
							</button>
						</form>
					</div>
				</div>
			</div>
		</div>
		
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
						<th>Déscription</th>
						<th>Modifier</th>
					</tr>
				</thead>
				<tbody>
					<%=session.getAttribute("userPersonalitesTable")%>
				</tbody>
			</table>
		</div>

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
								<form data-toggle="validator" role="form" action="/Projet/AddPersonalite?type=1" method="POST">
								<div class="form-group text-left">
									<label for="inputNameCompetence" class="control-label">Nom
										de la compétence</label> <input type="text" class="form-control"
										id="nom" placeholder="Nom de la compétence"
										name="nom" required>
								</div>
								<div class="form-group text-left">
									<label for="inputNiveauCompetence" class="control-label">Niveau
									de la compétence  (entre 0 et 10)</label>
									<input type="number" class="form-control"
									id="niveau" placeholder="Niveau de la copétence"  max="10" min="0"
									name="niveau" required>
								</div>
								<div class="form-group text-left">
									<label for="inputDescriptifCompetence" class="control-label">Descriptif
										de la compétence</label>
									<textarea rows="4" class="form-control"
										id="descriptif"
										placeholder="Descriptif de la compétence (e.g. Projets associés)"
										name="descriptif" required></textarea>
								</div>
								<div class="form-group text-right">
									<button type="submit" class="btn btn-success">
										<span class="glyphicon glyphicon-pencil"></span> Ajouter
									</button>
								</div>
								</form>
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
								<form data-toggle="validator" role="form" action="/Projet/AddPersonalite?type=2" method="POST">
								<div class="form-group text-left">
									<label for="inputNamePassion" class="control-label">Nom
										de la passion  (entre 0 et 10)</label> <input type="text" class="form-control"
										id="nom" placeholder="Nom de la passion" 
										name="nom" required>
								</div>
								<div class="form-group text-left">
									<label for="inputNiveauPassion" class="control-label">Niveau
										de la passion</label> <input type="number" class="form-control"
										id="niveau" placeholder="Niveau de la passion"  max="10" min="0"
										name="niveau" required>
								</div>
								<div class="form-group text-left">
									<label for="inputDescriptifPassion" class="control-label">Descriptif
										de la passion</label>
									<textarea rows="4" class="form-control" id="descriptif"
										placeholder="Descriptif de la passion (e.g. Projets associés)"
										name="descriptif" required></textarea>
								</div>
							
								<div class="form-group text-right">
									<button type="submit" class="btn btn-success">
										<span class="glyphicon glyphicon-pencil"></span> Ajouter
									</button>
								</div>
								</form>	
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