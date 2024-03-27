<%-- 
    Document   : listar
    Created on : 18 mar 2024, 13:03:16
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
        <h1>Lista de carreras</h1>
        <main>
            <TABLE BORDER=3>
            <%
                Connection conexion = ControlEscolar.getConnection();
                ResultSet resultSet;
                
                if(conexion !=null)
                {
                    resultSet = ControlEscolar.getValues(conexion, "carreras");
                    while(resultSet.next())
                    {
            %>
            <TR>
                <TD>Carreras</TD>
                <TD><%= resultSet.getString("nombre") %></TD>
                <td>
                    <form action="borrar.jsp" method="post">
                        <input type="hidden" name="name" value="<%= resultSet.getString("nombre") %>"/>
                        <input type="text" value="<%= resultSet.getString("id") %>" name="surname"/>
                        <input type="submit" value="Borrar"/>
                    </form>
                </td>
                <td>
                    <form action="modificarServlet" method="post">
                        <input type="hidden" name="name" value="<%= resultSet.getString("nombre") %>"/>
                        <input type="submit" value="Modificar" class="button"/>
                    </form>
                </td>
            </TR>
            
            <%
                    }

                    ControlEscolar.closeConnection(conexion);
                }
                else
                {   
            %>
             <TR>
                <TD>Carreras</TD>
                <TD>NOT FOUND</TD>
            </TR>
            <%
                }
            %>
            
            </TABLE>
        </main>
    </body>
</html>
