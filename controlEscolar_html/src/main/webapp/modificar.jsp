<%-- 
    Document   : modificar
    Created on : 27 mar 2024, 11:31:50
    Author     : mati
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
        <h1>modificar</h1>
        <main>
            
            <%
                String id = request.getParameter("surname");
                String newName = request.getParameter("newName");
                ControlEscolar ce = new ControlEscolar();
                ce.updateData("carreras",id, newName);
                
                RequestDispatcher rd = request.getRequestDispatcher("./listar.jsp");
                rd.forward(request, response);
            %>
        </main>
    </body>
</html>
