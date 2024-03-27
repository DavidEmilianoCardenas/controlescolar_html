<%-- 
    Document   : crear
    Created on : 18 mar 2024, 13:01:00
    Author     : Mati
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*" %>
<%@page import="java.sql.*" %>
<link href="CSS/styles.css" rel="stylesheet" type="text/css">
<%@page import="davide.davidv.controlescolar_html.sql.ControlEscolar" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Crear carrera</h1>
        <main>
            <%
                String name = request.getParameter("carrera");
                ControlEscolar ce = new ControlEscolar();
                Connection conexion = ce.getConnection();
                ce.InsertData("carreras", name, conexion);
                
                ce.closeConnection(conexion);
                RequestDispatcher rd = request.getRequestDispatcher("./listar.jsp");
                rd.forward(request, response);
            %>
        </main>
</html>
