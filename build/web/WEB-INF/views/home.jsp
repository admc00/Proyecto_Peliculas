<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inicio</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/estilo.css" rel="stylesheet">
    </head>
    <body class="d-flex flex-column vh-90">

        <jsp:include page="/WEB-INF/views/barranav.jsp" />

        <main class="container mt-5 flex-grow-1">
            <h1>Populares:</h1>
            <div class="d-flex flex-nowrap overflow-auto pb-1">
                <c:if test="${empty peliculas}">
                    <div class="col-12">
                        <p class="text-center">Cargando películas...</p>
                    </div>
                </c:if>

                <c:forEach var="pelicula" items="${peliculas}">
                    <div class="col-md-3 mb-3">
                        <div class="card shadow-sm me-4 flex-shrink-0" style="width: 300px; border: none;">
                            <img src="${pelicula.getPosterUrlCompleta()}" class="card-img-top rounded poster-accion" alt="${pelicula.titulo}" style="height: 350px; object-fit: cover;" 
                                 data-bs-toggle="modal" 
                                 data-bs-target="#detallePeliculaModal"
                                 data-id="${pelicula.idApi}"
                                 data-titulo="${pelicula.titulo}"
                                 data-fecha="${pelicula.fechaEstreno}"
                                 data-puntuacion="${pelicula.puntuacionMedia}"
                                 data-imagen="${pelicula.getPosterUrlCompleta()}"
                                 data-descripcion="${pelicula.descripcion}"
                                 
                                 
                                 data-url-detalles="${pageContext.request.contextPath}/pelicula/detalles?id=${pelicula.idApi}">
                            <div class="card-body d-flex flex-column">
                                <h5 class="card-title text-truncate" title="${pelicula.titulo}">${pelicula.titulo}</h5>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <h1>Comedia:</h1>
            <div class="d-flex flex-nowrap overflow-auto pb-1">
                <c:if test="${empty peliculasComedia}">
                    <div class="col-12">
                        <p class="text-center">Cargando películas...</p>
                    </div>
                </c:if>

                <c:forEach var="pelicula" items="${peliculasComedia}">
                    <div class="col-md-3 mb-3">
                        <div class="card shadow-sm me-4 flex-shrink-0" style="width: 300px; border: none;">
                            <img src="${pelicula.getPosterUrlCompleta()}" class="card-img-top rounded poster-accion" alt="${pelicula.titulo}" style="height: 350px; object-fit: cover;" 
                                 data-bs-toggle="modal" 
                                 data-bs-target="#detallePeliculaModal"
                                 data-id="${pelicula.idApi}"
                                 data-titulo="${pelicula.titulo}"
                                 data-fecha="${pelicula.fechaEstreno}"
                                 data-puntuacion="${pelicula.puntuacionMedia}"
                                 data-imagen="${pelicula.getPosterUrlCompleta()}"
                                 data-descripcion="${pelicula.descripcion}"
                                 
                                 
                                 data-url-detalles="${pageContext.request.contextPath}/pelicula/detalles?id=${pelicula.idApi}">
                            
                            <div class="card-body d-flex flex-column">
                                <h5 class="card-title text-truncate" title="${pelicula.titulo}">${pelicula.titulo}</h5>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <h1>Terror:</h1>
            <div class="d-flex flex-nowrap overflow-auto pb-1">
                <c:if test="${empty peliculasTerror}">
                    <div class="col-12">
                        <p class="text-center">Cargando películas...</p>
                    </div>
                </c:if>

                <c:forEach var="pelicula" items="${peliculasTerror}">
                    <div class="col-md-3 mb-3">
                        <div class="card shadow-sm me-4 flex-shrink-0" style="width: 300px; border: none;">
                            <img src="${pelicula.getPosterUrlCompleta()}" class="card-img-top rounded poster-accion" alt="${pelicula.titulo}" style="height: 350px; object-fit: cover;" 
                                 data-bs-toggle="modal" 
                                 data-bs-target="#detallePeliculaModal"
                                 
                                 data-titulo="${pelicula.titulo}"
                                 data-fecha="${pelicula.fechaEstreno}"
                                 data-puntuacion="${pelicula.puntuacionMedia}"
                                 data-imagen="${pelicula.getPosterUrlCompleta()}"
                                 data-descripcion="${pelicula.descripcion}"
                                 
                                data-url-detalles="${pageContext.request.contextPath}/pelicula/detalles?id=${pelicula.idApi}">
                            
                                 
                            <div class="card-body d-flex flex-column">
                                <h5 class="card-title text-truncate" title="${pelicula.titulo}">${pelicula.titulo}</h5>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </main>
        
        <div class="modal fade" id="detallePeliculaModal" tabindex="-1" aria-hidden="true">
            <div class="modal-dialog modal-lg modal-dialog-centered">
                <div class="modal-content" style="background-color: #222831; color: white;">
                    <div class="modal-header border-secondary">
                        <h5 class="modal-title">Detalles</h5>
                        <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-md-5">
                                <img id="modalImagen" src="" class="img-fluid rounded" alt="Poster">
                            </div>
                            <div class="col-md-7">
                                <h2 id="modalTitulo"></h2>
                                <p>Fecha: <span id="modalFecha"></span></p>
                                <p>Puntuación: <span id="modalPuntuacion" class="badge bg-warning text-dark"></span> / 10</p>
                                <p id="modalDescripcion"></p>
                            </div>
                        </div>
                    </div>
                    
                    <div class="modal-footer border-secondary">
                        
                        <a id="modalDetalles" href="#" class="btn btn-color">Mostrar más detalles</a>
                    </div>
                    
                </div>
            </div>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
        <script src="${pageContext.request.contextPath}/javascript/main.js"></script>

    </body>
</html>
