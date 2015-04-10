<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- header -->
<jsp:include page="/utils/header.jsp">
	<jsp:param name="title" value="GiMi & Co" />
	<jsp:param name="page" value="/index.jsp" />
</jsp:include>

<!-- container -->
<div class="jumbotron text-center">
	<h1>GiMi & Co</h1>
	<h2>Bienvenue sur le réseau d'entreprise !</h2>
	<br>
	<% if(session.getAttribute("name") == null) { %>
	<p>Cliquez sur le bouton si vous souhaitez créer un nouveau compte.</p>
	<p>
		<a href="creer-compte.jsp"><button class="btn btn-lg btn-primary">Créer
				compte</button></a>
	</p>
	<% } else { %>
		<a class="btn btn-lg btn-primary" href="/Projet/recherche.jsp">Cliquez ici pour commencer une recherche</a>
		<% } %>
	
</div>

<!-- footer -->
<jsp:include page="/utils/footer.jsp" />