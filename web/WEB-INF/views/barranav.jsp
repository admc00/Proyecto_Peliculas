<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="${pageContext.request.contextPath}/css/estilo.css" rel="stylesheet">
    </head>
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
            </c:if>

            <c:if test="${empty sessionScope.usuarioLogueado}">

                <a href="/Proyecto_Peliculas/usuario/login">Iniciar Sesión</a>
                <a href="/Proyecto_Peliculas/usuario/registro">Registrarse</a>
            </c:if>
        </div>

    </nav>
</html>
