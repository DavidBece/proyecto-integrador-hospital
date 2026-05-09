<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<head>
    <title>Servicios</title>
</head>
<body>
    <h1>Lista de Servicios</h1>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Nombre</th>
        </tr>
        <c:forEach var="servicio" items="${servicios}">
            <tr>
                <td>${servicio.id}</td>
                <td>${servicio.nombre}</td>
            </tr>
        </c:forEach>
    </table>
    <h2>Agregar Servicio</h2>
    <form action="servicio" method="post">
        <input type="hidden" name="action" value="create">
        Nombre: <input type="text" name="nombre" required><br>
        <input type="submit" value="Agregar">
    </form>
</body>
</html>