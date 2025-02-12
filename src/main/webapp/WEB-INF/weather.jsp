<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Weather Information</title>
</head>
<body>
<h1>Weather for ${weather.resolvedAddress}</h1>
<p>${weather.description}</p>
<table border="1">
    <tr>
        <th>Date</th>
        <th>Max Temp (°F)</th>
        <th>Min Temp (°F)</th>
        <th>Conditions</th>
    </tr>
    <c:forEach var="day" items="${weather.days}">
        <tr>
            <td>${day.datetime}</td>
            <td>${day.tempmax}</td>
            <td>${day.tempmin}</td>
            <td>${day.conditions}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
