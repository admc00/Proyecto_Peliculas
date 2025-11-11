
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registro</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/estilo.css" rel="stylesheet">
    </head>
    <body class="d-flex flex-column vh-100">

        <jsp:include page="/WEB-INF/views/barranav.jsp" />

        <h1 class="card-title text-center mb-5 pt-5">Crear Cuenta</h1>

        <main  class="d-flex flex-grow-1 align-items-start justify-content-center pt-3">

            <div class="card" style="width: 400px;">
                <div class="card-body p-4>">
                    <form   method="POST" action="/Proyecto_Peliculas/usuario/registro">
                        <div class="mb-3">
                            <label class="form-label" >Nombre de Usuario:</label>
                            <input type="text" class="form-control" name="nombreUsuario" required>
                        </div>
                        <div class="mb-3">
                            <label class="form-label" >Email:</label>
                            <input type="email" class="form-control" name="email" required>
                        </div>
                        <div class="mb-3">
                            <label class="form-label" >Contraseña:</label>
                            <input type="password" class="form-control" name="contrasena" required>
                        </div>

                        <c:if test="${not empty error}">
                            <p style="color:red;">${error}</p>
                        </c:if>

                        <button class="btn btn-color w-100" type="submit">Registrarme</button>
                    </form>

                    <p class="pt-3">¿Ya tienes cuenta? <a href="login">Inicia Sesión</a></p>
                </div>


            </div>

        </main>


        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
        <script src="${pageContext.request.contextPath}/javascript/main.js"></script>
    </body>
</html>
