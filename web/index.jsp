<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="${pageContext.request.contextPath}/resources/Estilos.css" rel="stylesheet" type="text/css">
        <script src="${pageContext.request.contextPath}/resources/funciones.js"></script>
    </head>
    <body>
        <div id="presentacion">
            <h1>Bienvenido a nuestro sistema de autenticación</h1>
            <a href="${pageContext.request.contextPath}/Control?accion=listado">Presione aquí para ingresar</a>
        </div>
    </body>
</html>
