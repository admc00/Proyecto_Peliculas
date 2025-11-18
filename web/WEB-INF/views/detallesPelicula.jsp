<%-- 
    Document   : detallesPelicula
    Created on : 13 nov 2025, 10:49:09
    Author     : FX506
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Detalles de ${pelicula.titulo}</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/estilo.css" rel="stylesheet">
    </head>
    <body class="d-flex flex-column vh-90">
       <jsp:include page="/WEB-INF/views/barranav.jsp" />
       <main class=container mt-5>
           <h1 >Detalles de la pelicula: "${pelicula.titulo}" </h1>
           <div>
               
               
           </div>
       </main>
    </body>
</html>
