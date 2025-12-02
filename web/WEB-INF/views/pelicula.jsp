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

                <div style="background-color: #222842; padding: 60px 0;">

                    <div class="container">
                        <div class="row align-items-center">


                            <div class="col-md-4 col-lg-3 text-center mb-4 mb-md-0">
                                <img src="${detalles.getPosterUrlCompleta()}" 
                                     class="img-fluid poster-grande">

                                <div class="mt-4">
                                    <a href="#" onclick="history.back(); return false;" class="btn btn-color w-100">
                                        Volver
                                    </a>
                                </div>
                            </div>


                            <div class="col-md-8 col-lg-9 text-white ">
                                <h1 class="display-4 fw-bold mb-3">${detalles.titulo}</h1>

                                <div class="mb-4">
                                    <span class="badge bg-warning fs-5 me-3">
                                        ⭐ ${detalles.puntuacionMedia}
                                    </span>
                                    <span class="badge bg-secondary fs-5">
                                        📅 Estreno: ${detalles.fechaEstreno}
                                    </span>
                                </div>

                                <h4 class="border-bottom border-secondary pb-2 mb-3">Sinopsis</h4>
                                <p class="lead fs-5 opacity-75" style="line-height: 1.8;">
                                    ${detalles.descripcion}
                                </p>
                            </div>
                        </div>
                    </div>
                </div>


                <div class="container mt-5 mb-5 bg-light border rounded">


                    <div class="row justify-content-center">
                        <div class="col-md-10 col-lg-8">

                            <h3 class="mt-2 mb-4 border-bottom pb-2 text-dark">Reseñas</h3>

                            <div class="card shadow-sm border mb-3">
                                <div class="card-header bg-light border-bottom pt-4 px-4">
                                    <h5 class="mb-0 fw-bold text-dark">Deja tu opinión</h5>
                                </div>
                                <div class="card-body p-4 bg-light">

                                    <c:if test="${not empty sessionScope.usuarioLogueado}">
                                        <form action="${pageContext.request.contextPath}/resena/guardar" method="POST">
                                            <input type="hidden" name="idApiPelicula" value="${detalles.idApi}">
                                            <input type="hidden" name="tituloPelicula" value="${detalles.titulo}">
                                            <input type="hidden" name="posterPelicula" value="${detalles.getPosterUrlCompleta()}">
                                            <input type="hidden" name="descripcionPelicula" value="${detalles.descripcion}">
                                            <input type="hidden" name="fechaPelicula" value="${detalles.fechaEstreno}">
                                            <input type="hidden" name="puntuacionMedia" value="${detalles.puntuacionMedia}">

                                            <div class="row mb-3">
                                                <div class="col-md-4">
                                                    <label class="form-label fw-bold text-secondary">Puntuación</label>
                                                    <select name="puntuacion" class="form-select" >
                                                        <option value="10">10 - Obra Maestra</option>
                                                        <option value="9">9 - Excelente</option>
                                                        <option value="8">8 - Muy Buena</option>
                                                        <option value="7">7 - Buena</option>
                                                        <option value="6">6 - Interesante</option>
                                                        <option value="5">5 - Pasable</option>
                                                        <option value="4">4 - Regular</option>
                                                        <option value="0">0 - Mala</option>
                                                        <option value="" selected disable hidden >Elige una opcion</option>
                                                    </select>
                                                </div>
                                            </div>

                                            <div class="mb-3">
                                                <label class="form-label fw-bold text-secondary">Tu Comentario</label>
                                                <textarea name="texto" class="form-control" rows="3" placeholder="¿Qué te ha parecido la película?" required></textarea>
                                            </div>

                                            <div class="d-grid">
                                                <button type="submit" class="btn btn-color text-dark py-2">Publicar Reseña</button>
                                            </div>
                                        </form>
                                    </c:if>

                                    <c:if test="${empty sessionScope.usuarioLogueado}">
                                        <div class="alert alert-light text-center border-0 mb-1 text-dark bg-light">
                                            Para dejar una reseña, <a href="${pageContext.request.contextPath}/usuario/login" class="fw-bold text-decoration-none">inicia sesión</a>.
                                        </div>
                                    </c:if>

                                </div>
                            </div>

                            <div class="mt-5 mb-3">
                                <h4 class="mb-4 text-dark border-bottom pb-2">Opiniones de la Comunidad <span class="badge bg-secondary rounded-pill fs-6 align-middle ms-2">${listaResenas.size()}</span></h4>

                                <c:if test="${empty listaResenas}">
                                    <div class="text-center py-4 text-muted bg-white border rounded shadow-sm ">
                                        <p>Aún no hay reseñas. ¡Sé el primero en opinar!</p>
                                    </div>
                                </c:if>

                                <c:forEach var="resena" items="${listaResenas}">
                                    <div class="card mb-3 border-0 shadow-sm">
                                        <div class="card-body">
                                            <div class="d-flex justify-content-between align-items-center mb-2">
                                                <div class="d-flex align-items-center">

                                                    <div class="bg-light rounded-circle d-flex align-items-center justify-content-center me-2" style="width: 40px; height: 40px;">
                                                        <span class="fw-bold text-secondary">${resena.usuario.nombreUsuario.charAt(0).toString().toUpperCase()}</span>
                                                    </div>
                                                    <div>
                                                        <h6 class="mb-0 fw-bold text-dark">${resena.usuario.nombreUsuario}</h6>
                                                        <small class="text-muted" style="font-size: 0.8rem;">${resena.fecha}</small>
                                                    </div>
                                                </div>


                                                <div class="d-flex align-items-center">

                                                    <span class="badge ${resena.puntuacion >= 7 ? 'bg-success' : (resena.puntuacion >= 5 ? 'bg-warning' : 'bg-danger')} rounded-pill">
                                                        ${resena.puntuacion} / 10
                                                    </span>


                                                    <c:if test="${resena.usuario.id == sessionScope.usuarioLogueado.id and not empty sessionScope.usuarioLogueado}">


                                                        <div class="dropdown ms-1 border-4">
                                                            <button class="btn p-0 fs-4" data-bs-toggle="dropdown">
                                                                &#8942;
                                                            </button>

                                                            <ul class="dropdown-menu dropdown-menu-end shadow border-0">
                                                                <li>
                                                                    <a class="dropdown-item" href="#">
                                                                        Editar
                                                                    </a>

                                                                </li>
                                                                <li>
                                                                    <form action="${pageContext.request.contextPath}/resena/eliminar" method="POST">
                                                                        <input type="hidden" name="idResena" value="${resena.id}">
                                                                        <input type="hidden" name="idApiPelicula" value="${detalles.idApi}">
                                                                        <button type="submit" class="dropdown-item text-danger">
                                                                            Eliminar
                                                                        </button>
                                                                    </form>
                                                                </li>

                                                            </ul>
                                                        </div>




                                                    </c:if>   
                                                </div>
                                            </div>
                                            <div>
                                                <p class="card-text text-secondary mt-3 ms-1">
                                                    ${resena.texto}
                                                </p>
                                            </div>        
                                        </div>
                                    </div>
                                </c:forEach>
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
                        <a href="${pageContext.request.contextPath}/home" class="btn btn-color">Volver al Inicio</a>
                    </div>
                </div>
            </c:if>

        </main>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
        <script src="${pageContext.request.contextPath}/javascript/main.js"></script>
    </body>
</html>
