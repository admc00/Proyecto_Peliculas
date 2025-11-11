<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/estilo.css" rel="stylesheet">
    </head>
    <<body>
        <nav class="navbar">

            <a href="/Proyecto_Peliculas/home" class="navbar-logo">
                <img src="${pageContext.request.contextPath}/img/logo.png">
            </a>

            <form class="navbar-search"  >
                <input type="text" name="query" placeholder="Buscar películas...">
                <button type="submit">Buscar</button>
            </form>

            <div class ="navbar-usuario">
                <c:if test="${not empty sessionScope.usuarioLogueado}">

                    <a href="/Proyecto_Peliculas/usuario/perfil">
                        Mi Perfil (${sessionScope.usuarioLogueado.nombreUsuario})
                    </a>



                    <a href="/Proyecto_Peliculas/usuario/logout">Cerrar Sesión</a>

                    <div class="position-fixed bottom-0 end-0 p-3" style="z-index: 11">
                        <div id="liveToast" class="toast" role="alert" aria-live="assertive" aria-atomic="true">
                            <div class="toast-header">
                                <img src="..." class="rounded me-2" alt="...">
                                <strong class="me-auto">Notificación</strong>
                                <small>Justo Ahora</small>
                                <button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Close"></button>
                            </div>
                            <div class="toast-body"  id="liveToastBody">
                                   
                            </div>
                        </div>
                    </div>



                </c:if>

                <c:if test="${empty sessionScope.usuarioLogueado}">

                    <a href="/Proyecto_Peliculas/usuario/login">Iniciar Sesión</a>
                    <a href="/Proyecto_Peliculas/usuario/registro">Registrarse</a>
                </c:if>



            </div>

        </nav>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
        <script src="${pageContext.request.contextPath}/javascript/main.js"></script>
    </body>
</html>
