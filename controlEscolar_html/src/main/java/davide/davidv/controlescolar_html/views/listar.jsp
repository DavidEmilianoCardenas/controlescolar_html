<%-- 
    Document   : listar
    Created on : 18 mar 2024, 13:03:16
    Author     : Mati
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*" %>
<%@page import="java.sql.*" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        Connection conexion = getConnection();
        java.sql.ResultSet resultSet;
        
        resultSet = getValues(conexion, "carreras");
        
        closeConnection(conexion);
    </body>
</html>
