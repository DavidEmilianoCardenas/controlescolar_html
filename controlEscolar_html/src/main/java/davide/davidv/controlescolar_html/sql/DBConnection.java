/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package davide.davidv.controlescolar_html.sql;

import java.sql.*;

public class DBConnection {
    public static void main(String[] args) {
        Connection connection = null;

        try {
            // Cargar el driver de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establecer la conexión
            String url = "jdbc:mysql://localhost/control_escolar";
            String username = "root";
            String password = "1234";
            connection = DriverManager.getConnection(url, username, password);

            // Comprobar si la conexión fue exitosa
            if (connection != null) {
                System.out.println("¡Conexión exitosa a la base de datos!");
            } else {
                System.out.println("Error al conectar a la base de datos.");
            }
        } catch (ClassNotFoundException e) {
            System.out.println("No se pudo encontrar el driver de MySQL.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Error al establecer la conexión a la base de datos.");
            e.printStackTrace();
        } finally {
            // Cerrar la conexión
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión.");
                e.printStackTrace();
            }
        }
    }
}