<%-- 
    Document   : borrar
    Created on : 27 mar 2024, 11:16:33
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
            <%
                String name = request.getParameter("name");
                ControlEscolar ce = new ControlEscolar();
                ce.deleteCarrera("carreras", name);
                
                RequestDispatcher rd = request.getRequestDispatcher("./listar.jsp");
                rd.forward(request, response);
            %>
    </body>
</html>
