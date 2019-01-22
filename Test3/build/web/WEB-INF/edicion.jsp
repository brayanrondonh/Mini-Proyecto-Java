<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="${pageContext.request.contextPath}/resources/Estilos.css" rel="stylesheet" type="text/css">
        <script src="${pageContext.request.contextPath}/resources/funciones.js"></script>
    </head>
    <body>
        <div id="presentacion">
            <div id="insertar">
                <form action="${pageContext.request.contextPath}/Control" id="formulario2" method="post">
                <input type="hidden" name="accion" value="procesar">
            
                <div id="login">
                    <h1 style="margin-bottom: 50px;">Procesando Datos</h1> 
                    <input type="hidden" name="id" value="${usuarios.id_usuario}">
                    Usuario: <input type="text" name="usuario" required="required" value="${usuarios.usuario}"><br>
                    Password: <input type="password" name="password" required="required" value="${usuarios.password}"><br>
                </div>          
                <div style="padding: 10px; margin-top: 15px; border-radius: 25px;">
                <input type="submit" value="Enviar" style="padding: 30px; margin-top: 15px; border-radius: 25px;">
                <input type="reset" value="Borrar" style="padding: 30px; margin-top: 15px; border-radius: 25px;">
                </div>
                </form>
            </div>
        </div>
    </body>
</html>
