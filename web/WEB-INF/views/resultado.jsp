<%-- 
    Document   : resultado
    Created on : 12 nov 2025, 12:11:38
    Author     : admc0
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Resultados de "${terminoBusqueda}"</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/estilo.css" rel="stylesheet">
    </head>
    <body class="d-flex flex-column vh-100">

        <jsp:include page="/WEB-INF/views/barranav.jsp" />

        <main class="container mt-5">
            <h1>Resultados para: "${terminoBusqueda}"</h1>

            <div class="row">
                <c:if test="${empty peliculas}">
                    <p>No se encontraron resultados.</p>
                </c:if>

                <%-- Iteramos sobre la lista 'peliculas' pasada desde el servlet --%>
                <c:forEach var="pelicula" items="${peliculas}">
                    <div class="col-md-3 mb-4">
                        <div class="card h-100">
                            <img src="${pelicula.getPosterUrlCompleta()}" class="card-img-top" alt="${pelicula.titulo}">
                            <div class="card-body">
                                <h5 class="card-title">${pelicula.titulo}</h5>
                                <p class="card-text">${pelicula.fechaEstreno}</p>
                                <a href="${pageContext.request.contextPath}/pelicula/detalles?id=${pelicula.idApi}" class="btn btn-color">
                                    Ver Detalles
                                </a>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </main>
    </body>
</html>
