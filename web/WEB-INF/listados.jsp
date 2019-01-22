<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="${pageContext.request.contextPath}/resources/Estilos.css" rel="stylesheet" type="text/css">
        <script src="${pageContext.request.contextPath}/resources/funciones.js"></script>
    </head>
    <body>
        <div id="presentacion">
            <div id="listado">
                <h1>Listado de Usuario</h1>
                    <form action="${pageContext.request.contextPath}/Control" method="post" id="formulario1">
                    <input type="hidden" name="accion" id="accion">
                    <table id="tabla">
                        <tr>
                            <th></th>
                            <th>Id usuario</th>
                            <th>Usuario</th>
                            <th>Password</th>
                        </tr>
                        <c:forEach var="x" items="${listaUsuarios}" varStatus="row">
                            <tr>
                                <td>
                                    <input type="checkbox" name="usuario" id="usuario" value="${x.id_usuario}">
                                </td>
                                <td>
                                    ${x.id_usuario}
                                </td>
                                <td>
                                    ${x.usuario}
                                </td>
                                <td>
                                    ${x.password}
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                    <div id="botonesListado">
                        <input type="button" id="agregar" value="agregar" onclick="validarFormulario(this)">
                        <input type="button" id="modificar" value="modificar" onclick="validarFormulario(this)">
                        <input type="button" id="eliminar" value="eliminar" onclick="validarFormulario(this)">
                        <a href="${pageContext.request.contextPath}/Control?accion=salir">Salir</a>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
