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
  <p>Cliquez sur le bouton si vous souhaitez créer un nouveau compte.</p>
  <% if(session.getAttribute("name") == null) { %>
    <p><a href="creer-compte.jsp"><button class="btn btn-lg btn-primary">Créer compte</button></a></p>
  <% } else { %>
    <p><a class="btn btn-lg btn-success" href="new-project.jsp">New project idea</a>
  <% } %>
</div>

<!-- footer -->
<jsp:include page="/utils/footer.jsp" />