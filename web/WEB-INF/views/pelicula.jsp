<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${detalles.titulo} - Detalles</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/estilo.css" rel="stylesheet">
    </head>
    <body class="d-flex flex-column vh-90">

        <jsp:include page="/WEB-INF/views/barranav.jsp"/>


        <main class="flex-grow-1">

            <c:if test="${not empty detalles}">
                
                <div class="movie-header-bg" style="background-color: #222842;">

                    <div class="container movie-content">
                        <div class="row align-items-center">

                            <%-- Columna Izquierda: Póster --%>
                            <div class="col-md-4 col-lg-3 text-center mb-4 mb-md-0">
                                <img src="${detalles.getPosterUrlCompleta()}" 
                                     class="img-fluid poster-grande">

                                <div class="mt-3 d-grid">
                                    <a href="javascript:history.back()" class="btn btn-color w-100">
                                        Volver
                                    </a>
                                </div>
                            </div>

                            <%-- Columna Derecha: Información --%>
                            <div class="col-md-8 text-white ">
                                <h1 class="display-4 fw-bold mb-3">${detalles.titulo}</h1>

                                <div class="mb-4">
                                    <span class="badge bg-warning fs-5 me-3">
                                        ⭐ ${detalles.puntuacionMedia}
                                    </span>
                                    <span class="fs-5 text-secondary">
                                        📅 Estreno: ${detalles.fechaEstreno}
                                    </span>
                                </div>

                                <h4 class="border-bottom border-secondary pb-2 mb-3">Sinopsis</h4>
                                <p class="lead fs-6" style="line-height: 1.8;">
                                    ${detalles.descripcion}
                                </p>

                                <%-- SECCIÓN DE RESEÑAS (FORMULARIO) --%>
                                <div class="card bg-dark border-secondary mt-5">
                                    <div class="card-header border-secondary text-white">
                                        <h5 class="mb-0">Dejar una Reseña</h5>
                                    </div>
                                    <div class="card-body text-white">

                                        <c:if test="${not empty sessionScope.usuarioLogueado}">
                                            <form action="${pageContext.request.contextPath}/resena/guardar" method="POST">
                                                <input type="hidden" name="idApiPelicula" value="${detalles.idApi}">
                                                <input type="hidden" name="tituloPelicula" value="${detalles.titulo}">
                                                <input type="hidden" name="posterPelicula" value="${detalles.getPosterUrlCompleta()}">

                                                <div class="mb-3">
                                                    <label class="form-label">Tu Puntuación (0-10):</label>
                                                    <input type="number" name="puntuacion" class="form-control bg-dark text-white border-secondary" min="0" max="10" required>
                                                </div>

                                                <div class="mb-3">
                                                    <label class="form-label">Tu Comentario:</label>
                                                    <textarea name="texto" class="form-control bg-dark text-white border-secondary" rows="3" required placeholder="Escribe qué te pareció la película..."></textarea>
                                                </div>

                                                <button type="submit" class="btn btn-color w-100">Publicar Reseña</button>
                                            </form>
                                        </c:if>

                                        <c:if test="${empty sessionScope.usuarioLogueado}">
                                            <div class="text-center py-3">
                                                <p class="mb-3 text-white">Debes iniciar sesión para escribir una reseña.</p>
                                                <a href="${pageContext.request.contextPath}/usuario/login" class="btn btn-color">Iniciar Sesión</a>
                                            </div>
                                        </c:if>

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </c:if>

            <c:if test="${empty detalles}">
                <div class="container mt-5 text-center">
                    <div class="alert alert-danger">
                        <h3>Datos no disponibles</h3>
                        <p>No se ha podido cargar la información de la película.</p>
                        <a href="${pageContext.request.contextPath}/home" class="btn btn-primary">Volver al Inicio</a>
                    </div>
                </div>
            </c:if>

        </main>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
        <script src="${pageContext.request.contextPath}/javascript/main.js"></script>
    </body>
</html>
