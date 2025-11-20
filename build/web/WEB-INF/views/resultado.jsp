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
    <body class="d-flex flex-column vh-90">
        <jsp:include page="/WEB-INF/views/barranav.jsp" />
        <main class="container mt-5 flex-grow-1">
            <h1>Resultados para: "${terminoBusqueda}"</h1>

            <div class="row">
                <c:if test="${empty peliculas}">
                    <p>No se encontraron resultados.</p>
                </c:if>


                <c:forEach var="pelicula" items="${peliculas}">
                    <div class="col-md-3 mb-4">
                        <div class="card h-100">
                            <img src="${pelicula.getPosterUrlCompleta()}" class="card-img-top" alt="${pelicula.titulo}" style="height: 350px;">
                            <div class="card-body">
                                <h5 class="card-title">${pelicula.titulo}</h5>
                                <p class="card-text">${pelicula.fechaEstreno}</p>
                                <button type="button" class="btn btn-color mt-auto" 
                                        data-bs-toggle="modal" 
                                        data-bs-target="#detallePeliculaModal"
                                        data-titulo="${pelicula.titulo}"
                                        data-fecha="${pelicula.fechaEstreno}"
                                        data-puntuacion="${pelicula.puntuacionMedia}"
                                        data-imagen="${pelicula.getPosterUrlCompleta()}"
                                        data-descripcion="${pelicula.descripcion}">
                                    Ver Detalles
                                </button>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </main>

        <div class="modal fade" id="detallePeliculaModal" tabindex="-1" aria-labelledby="modalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg modal-dialog-centered">
                <div class="modal-content" style="background-color: #222831; color: white;">
                    <div class="modal-header border-secondary">
                        <h5 class="modal-title" id="modalLabel">Detalles de la Película</h5>
                        <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-md-5">
                                <img id="modalImagen" src="" class="img-fluid rounded shadow" alt="Poster">
                            </div>
                            <div class="col-md-7">
                                <h2 id="modalTitulo" class="fw-bold mb-3"></h2>
                                <p><strong>Fecha de Estreno:</strong> <span id="modalFecha"></span></p>
                                <p><strong>Puntuación:</strong> <span id="modalPuntuacion" class="badge bg-warning text-dark"></span> / 10</p>
                                <hr class="border-secondary">
                                <h5>Sinopsis</h5>
                                <p id="modalDescripcion" class="text-light"></p>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer border-secondary">
                        <%-- Aquí podrías poner el botón para ir a escribir reseña si quisieras --%>
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                    </div>
                </div>
            </div>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
        <script src="${pageContext.request.contextPath}/javascript/main.js"></script>
        
    </body>
</html>
