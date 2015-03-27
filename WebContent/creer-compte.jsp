<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- header -->
<jsp:include page="/utils/header.jsp">
  <jsp:param name="title" value="Projet" />
  <jsp:param name="page" value="/index.jsp" />
</jsp:include>

<%  if(session.getAttribute("name") != null) { 
       RequestDispatcher rd =
       request.getRequestDispatcher("index.jsp");
       rd.forward(request, response);
    } else { %>
<!-- container -->
<div class="jumbotron text-center">
	<form action="/Projet/RequestSignUpServlet">
		<input type="text" placeholder="Nom" name="nom">
		<input type="text" placeholder="Prénom" name="prenom">
		<input type="email" placeholder="Email" name="email">
		<input type="password" placeholder="Mot de passe" name="password">
		<input type="password" placeholder="Confirmation de mot de passe" name="password2">
	</form>
</div>
<% } %>
<!-- footer -->
<jsp:include page="/utils/footer.jsp" />