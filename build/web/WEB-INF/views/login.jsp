
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <h1>Iniciar Sesion</h1>

        <form method="POST" action="/Proyecto_Peliculas/usuario/login">
            <div>
                <label>Email:</label>
                <input type="email" name="email" required>
            </div>
            <div>
                <label>Contraseña:</label>
                <input type="password" name="contrasena" required>
            </div>

            <c:if test="${not empty error}">
                <p style="color:red;">${error}</p>
            </c:if>

            <button type="submit">Entrar</button>
        </form>

        <p>¿No tienes cuenta? <a href="registro">Regístrate</a></p>

    </body>
</html>
