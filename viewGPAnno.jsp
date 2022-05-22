<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital@1&display=swap" rel="stylesheet">
    <style>
        body{
            font-family: 'Montserrat', serif;
            font-size: 1.5rem;
        }
    </style>
</head>
<body>
<a href="index.html">
    <div class="d-flex justify-content-center mt-2">
        <img src="img/chequeredFlag.png" width="20%" height="20%">
        <img src="img/F1.png" width="30%" height="20%">
        <img src="img/chequeredFlag.png"  width="20%" height="20%">
    </div>
</a>
<hr class="pt-2">
<div class="table-responsive">
<table class="table table-hover">
    <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">Nome Gara</th>
            <th scope="col">Nome Gran Premio</th>
            <th scope="col">Località</th>
            <th scope="col">Nazione</th>
            <th scope="col">Data</th>
            <th scope="col">Ora</th>

        </tr>
    </thead>
    <c:forEach items="${visualizzaGPAnno}" var="visualizzaGPAnni">
        <tr>
            <td>${visualizzaGPAnni.round}</td>
            <td><a href="${visualizzaGPAnni.urlWikipediaNomeGara}">${visualizzaGPAnni.nomeGara}</a></td>
            <td><a href="${visualizzaGPAnni.urlWikipediaNomeGranPremio}">${visualizzaGPAnni.nomeGranPremio}</a></td>
            <td>${visualizzaGPAnni.località}</td>
            <td>${visualizzaGPAnni.paese}</td>
            <td>${visualizzaGPAnni.data}</td>
            <td>${visualizzaGPAnni.ora}</td>
        </tr>
    </c:forEach>
</table>
</div>
</body>
</html>