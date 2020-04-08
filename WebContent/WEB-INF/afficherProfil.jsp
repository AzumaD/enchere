<%@page import="fr.eni.encheres.bo.Utilisateur"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- Bootstrap core CSS -->
<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<title>${utilisateur.pseudo }</title>
</head>
<body>
<div class="p-5 ml-5">
	<h1>ENI - Enchères</h1>
</div>
<div class="container d-flex justify-content-center pt-5 mt-5">
	<div class="pr-5">
		<p>Pseudo : </p>
		<p>Nom : </p>
		<p>Prénom : </p>
		<p>Email : </p>
		<p>Téléphone : </p>
		<p>Rue : </p>
		<p>Code Postal : </p>
		<p>Ville : </p>
	</div>
	<div class="text-center pl-5">
		<p>${utilisateur.pseudo }</p>
		<p>${utilisateur.nom }</p>
		<p>${utilisateur.prenom }</p>
		<p>${utilisateur.email }</p>
		<p>${utilisateur.telephone }</p>
		<p>${utilisateur.rue }</p>
		<p>${utilisateur.codePostal }</p>
		<p>${utilisateur.ville }</p>
	</div>
</div>
</body>
</html>