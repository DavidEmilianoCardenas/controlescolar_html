<%-- 
    Document   : home
    Created on : 18 mar 2024, 13:00:36
    Author     : Mati
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page import="davide.davidv.controlescolar_html.models.Carrera" %>
<%@ page import="davide.davidv.controlescolar_html.models.CarreraService" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            List<Carrera> carreras  = (List<Carrera>) request.getAttribute("carrera_list");
        %>
        <h1>Control escolar</h1>
        <h2>Carrera</h2>
        <ul>
            <li><a href="crear.jsp">Crear Carrera</a></li>
            <li><a href="listar.jsp">Listar Carreras</a></li>
        </ul>
    </body>
</html>
