<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<head>
    <title>Registros de Ingreso</title>
</head>
<body>
    <h1>Lista de Registros de Ingreso</h1>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Fecha</th>
            <th>Hora Entrada</th>
            <th>Hora Salida</th>
        </tr>
        <c:forEach var="registro" items="${registros}">
            <tr>
                <td>${registro.id}</td>
                <td>${registro.fecha}</td>
                <td>${registro.horaEntrada}</td>
                <td>${registro.horaSalida}</td>
            </tr>
        </c:forEach>
    </table>
    <h2>Agregar Registro</h2>
    <form action="registroIngreso" method="post">
        Fecha: <input type="date" name="fecha" required><br>
        Hora Entrada: <input type="time" name="horaEntrada" required><br>
        <input type="submit" value="Agregar">
    </form>
</body>
</html>