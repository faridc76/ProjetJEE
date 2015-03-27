<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>ProjetJEE</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">


    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <!-- JS - bootstrap.min.js contain all plugins in a single file. Include only one. -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
</head>
<body>

<nav class="navbar navbar-default">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="<%= request.getContextPath()%>/index.jsp"></a>
    </div>
    
    <!-- Collect the nav links, forms, and other content for toggling -->
	<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">    
        <!-- EMPTY -->
      </ul>
    <% if(session.getAttribute("name") == null) { %>
      <form class="navbar-form form-inline pull-right"  action="/Projet/RequestLoginServlet" method="post">
        <input type="text" placeholder="Email" name="login">
        <input type="password" placeholder="Password" name="password">
        <button type="submit" class="btn">Se connecter</button>
      </form>
    <% } else { %>
	  <ul class="nav navbar-nav navbar-right">
        <li class="dropdown">
          	<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><%= session.getAttribute("name") %><span class="caret"></span></a>
        	<ul class="dropdown-menu" role="menu">
	            <li>
	            	<a href="/Projet/InformationCompteServlet">Informations sur le compte</a>
	            </li>
	            <li>
	    			<a href="/Projet/RequestLogoutServlet">Déconnexion</a>
	    		</li>
          	</ul>
        </li>
      </ul>			
    <% } %>   
    </div> 
  </div>
</nav>

<div class="container">

<!-- container, body and HTML tags are still opened -->