
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
            <form action="${pageContext.request.contextPath}/Control" method="post">
            <input type="hidden" name="accion" value="login">
            <div id="login">
                <div class="input">
                    <label for="usuario">Ingrese usuario:</label>
                    <input type="text" name="usuario">
                </div>
                <div class="input">
                    <label for="password">Ingrese su Password:</label>
                    <input type="text" name="password">
                </div>
                <div style="color: red;">${mensaje}</div>
                <div id="botones">
                    <input type="submit" value="Enviar">
                    <input type="reset" value="Borrar">
                </div>                
            </div>           
        </form>
        </div>
    </body>
</html>
