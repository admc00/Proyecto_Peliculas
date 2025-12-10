<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav class="navbar">
    <a href="/Proyecto_Peliculas/" class="navbar-logo">
        <img src="${pageContext.request.contextPath}/img/logo.png">
    </a>

    <form class="navbar-search" method="GET" action="${pageContext.request.contextPath}/pelicula/buscar">
        <input type="text" name="query" placeholder="Buscar películas...">
        <button type="submit">Buscar</button>
    </form>

    <div class ="navbar-usuario">
        <c:if test="${not empty sessionScope.usuarioLogueado}">

            <a href="/Proyecto_Peliculas/usuario/perfil">
                Mi Perfil (${sessionScope.usuarioLogueado.nombreUsuario})
            </a>

            <a href="/Proyecto_Peliculas/usuario/logout">Cerrar Sesión</a>

            <a class="navbar-cesta" data-bs-toggle="offcanvas" href="#offcanvasCesta" role="button" aria-controls="offcanvasCesta">
                <img src="${pageContext.request.contextPath}/img/bookmark.png"/>
                <c:if test="${not empty sessionScope.cestaPeliculas and sessionScope.cestaPeliculas.size() > 0}">
                    <span class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger" style="font-size: 0.7rem;">
                        ${sessionScope.cestaPeliculas.size()}
                    </span>
                </c:if>
            </a>


        </c:if>

        <c:if test="${empty sessionScope.usuarioLogueado}">

            <a href="/Proyecto_Peliculas/usuario/login">Iniciar Sesión</a>
            <a href="/Proyecto_Peliculas/usuario/registro">Registrarse</a>
        </c:if>

    </div>



    <div class="position-fixed bottom-0 end-0 p-3" style="z-index: 11">
        <div id="liveToast" class="toast" role="alert" aria-live="assertive" aria-atomic="true">
            <div class="toast-header">
                <img class="toast-icon me-2" class="img-fluid" src="${pageContext.request.contextPath}/img/logo.png" class="rounded me-2" alt="...">
                <strong class="me-auto">Notificación</strong>
                <small>Justo Ahora</small>
                <button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Close"></button>
            </div>
            <div class="toast-body"  id="liveToastBody">

            </div>
        </div>
    </div>

</nav>

<div class="offcanvas offcanvas-end" tabindex="-1" id="offcanvasCesta" aria-labelledby="offcanvasCestaLabel">

    <div class="offcanvas-header border-bottom">
        <h5 class="offcanvas-title fw-bold" id="offcanvasCestaLabel">Mis películas</h5>
        <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"></button>
    </div>

    <div class="offcanvas-body d-flex flex-column">

        <c:if test="${empty sessionScope.cestaPeliculas}">
            <div class="text-center my-auto text-muted">
                <p class="fs-1"></p>
                <p>Tu lista está vacía</p>
            </div>
        </c:if>

        <c:if test="${not empty sessionScope.cestaPeliculas}">

            <div class="flex-grow-1 overflow-auto">
                <c:forEach var="peli" items="${sessionScope.cestaPeliculas}">
                    <div class="card mb-3 border-0 border-bottom">
                        <div class="row g-0 align-items-center py-2">
                            <div class="col-3">
                                <img src="${peli.posterUrlCompleta}" class="img-fluid rounded" alt="${peli.titulo}">
                            </div>
                            <div class="col-7 px-2">
                                <h6 class="mb-1 text-truncate" title="${peli.titulo}">${peli.titulo}</h6>
                                <small class="text-muted">${peli.puntuacionMedia}</small>
                            </div>
                            <div class="col-2 text-end">

                                <a href="${pageContext.request.contextPath}/pelicula/eliminar?id=${peli.idApi}" class="btn text-danger border-0">
                                    X
                                </a>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>

            <div class="mt-auto border-top pt-3">
                <div class="d-flex justify-content-between mb-3 fw-bold">
                    <span>Total películas:</span>
                    <span>${sessionScope.cestaPeliculas.size()}</span>
                </div>
            </div>
        </c:if>
    </div>
</div>    