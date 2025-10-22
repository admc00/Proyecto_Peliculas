<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inicio</title>
    </head>
    <body>

    <c:if test="${empty sessionScope.usuarioLogueado}">
        <% response.sendRedirect("login");%>
    </c:if>

    <c:if test="${not empty sessionScope.usuarioLogueado}">
        <h1>¡Bienvenido, ${sessionScope.usuarioLogueado.nombreUsuario}!</h1>

        <p>Inicio de la aplicacion.</p>

        <a>Cerrar Sesión</a>
    </c:if>

</body>
</html>
