
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registro</title>
    </head>
    <body>
        <h1>Crear Cuenta</h1>
        <form method="POST" action="/Proyecto_Peliculas/usuario/registro">
            <div>
                <label>Nombre de Usuario:</label>
                <input type="text" name="nombreUsuario" required>
            </div>
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

            <button type="submit">Registrarme</button>
        </form>

        <p>¿Ya tienes cuenta? <a href="login">Inicia Sesión</a></p>
    </body>
</html>
