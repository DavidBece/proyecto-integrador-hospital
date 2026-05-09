<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<head>
    <title>Docentes</title>
</head>
<body>
    <h1>Lista de Docentes</h1>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Especialidad</th>
        </tr>
        <c:forEach var="docente" items="${docentes}">
            <tr>
                <td>${docente.id}</td>
                <td>${docente.nombre}</td>
                <td>${docente.especialidad}</td>
            </tr>
        </c:forEach>
    </table>
    <h2>Agregar Docente</h2>
    <form action="docente" method="post">
        <input type="hidden" name="action" value="create">
        Nombre: <input type="text" name="nombre" required><br>
        Especialidad: <input type="text" name="especialidad" required><br>
        <input type="submit" value="Agregar">
    </form>
</body>
</html>