<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <nav class="navbar">

        <div class="navbar-links">
            <form class="navbar-search"  >
                <input type="text" name="query" placeholder="Buscar películas...">
                <button type="submit">Buscar</button>
            </form>
        </div>

        <div class="navbar-links">
           
            <c:if test="${not empty sessionScope.usuarioLogueado}">
              
                <a>
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
