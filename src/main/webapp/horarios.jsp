<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<head>
    <title>Horarios</title>
</head>
<body>
    <h1>Lista de Horarios</h1>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Día Semana</th>
            <th>Hora Inicio</th>
            <th>Hora Fin</th>
        </tr>
        <c:forEach var="horario" items="${horarios}">
            <tr>
                <td>${horario.id}</td>
                <td>${horario.diaSemana}</td>
                <td>${horario.horaInicio}</td>
                <td>${horario.horaFin}</td>
            </tr>
        </c:forEach>
    </table>
    <h2>Agregar Horario</h2>
    <form action="horario" method="post">
        Día Semana: <input type="text" name="diaSemana" required><br>
        Hora Inicio: <input type="time" name="horaInicio" required><br>
        Hora Fin: <input type="time" name="horaFin" required><br>
        <input type="submit" value="Agregar">
    </form>
</body>
</html>