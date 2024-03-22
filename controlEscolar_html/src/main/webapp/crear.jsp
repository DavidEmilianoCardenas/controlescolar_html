<%-- 
    Document   : crear
    Created on : 18 mar 2024, 13:01:00
    Author     : Mati
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Crear carrera</h1>
        <main>
            <form action="./serverltsaludo3" method="post">
            <input type="text" name="carrera"/>
            <input type="submit" title="Crear"/>
            </form>
        </main>
</html>
