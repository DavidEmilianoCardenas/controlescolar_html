
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;
import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author mati
 */
public class ControlEscolar {
    
    public static void main(String[] args) {

        Connection conexion = getConnection();
        
        menu(conexion);
        

        closeConnection(conexion);
    }
    
    public static Connection getConnection()
    {
        try {
            Properties props = new Properties();
            props.load(new FileInputStream("config/db.properties"));
            String username = props.getProperty("username");
            String password = props.getProperty("password");
            String bd_name = props.getProperty("bd");

            String host = "jdbc:mysql://localhost/control_escolar";
             try {
                Class.forName("com.mysql.cj.jdbc.Driver");

                Connection conexion = DriverManager.getConnection(host, username, password);

                return conexion;
            } catch (SQLException | ClassNotFoundException ex) {
                System.out.println("Error en la conexion de la base de datos");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;
    }

    public static void menu (Connection con) {
        Scanner sc = new Scanner(System.in);
        boolean correctInput = false;
        while (!correctInput) {
            System.out.println("Que quieres hacer?\n    0- Salir    \n    1- Gestionar Carreras:\n    2- Gestionar Alumnos:\n    3- Matricular Alumno:");
            switch (sc.nextByte()) {
                case 0:
                    correctInput = true;
                    break;
                case 1:
                    menuEspecifico(sc, con, "carreras");
                    break;
                case 2:
                    menuEspecifico(sc, con, "alumnos");
                    break;
                case 3:
                    Matricular(sc, con);
                    break;
                default:
                    System.out.println("Elige una de las opciones disponibles");
                    break;
            }
        }
        sc.close();
    }

    public static void menuEspecifico (Scanner sc, Connection con, String bd_name) {
        System.out.println("MENU " + bd_name.toUpperCase() +"\n   1- Añadir "+ bd_name+"\n" +
                "   2- Actualizar "+ bd_name+"\n" +
                "   3- Ver "+ bd_name+"\n" +
                "   4- Borrar "+ bd_name+"");
        switch (sc.nextByte()) {
            case 1:
                sc.nextLine();
                System.out.println("Que "+ bd_name+" quieres añadir");
                String carr = sc.nextLine();
                InsertData(bd_name, carr, con);
                break;
            case 2:
                sc.nextLine();
                System.out.println("Que "+ bd_name+" quiere actualizar (Seleccione un numero)");
                getValues(con,bd_name);
                int idCarrera = sc.nextInt();
                sc.nextLine();
                System.out.println("Elige el nuevo nombre:");
                String newName = sc.nextLine();
                updateData(bd_name,idCarrera,newName,con);
                break;
            case 3:
                sc.nextLine();
                getValues(con, bd_name);
                break;
            case 4:
                sc.nextLine();
                System.out.println("Que "+ bd_name+" quieres borrar");
                getValues(con, bd_name);
                carr = sc.nextLine();
                deleteCarrera(bd_name, carr, con);
                break;
            default:
                System.out.println("Selecciona una de las opciones disponibles");
        }
    }
    
    public static void InsertData(String table_name, String name, Connection con)
    {
        try
        {
            String Query = "INSERT INTO " + table_name + " (nombre) VALUES("
                    + "\"" + name +  "\")";
            
            Statement st = con.createStatement();
            System.out.println(Query);
            st.executeUpdate(Query);
        }catch (SQLException ex)
        {
            System.out.println(ex);
        }
    }

    public static void updateData (String table_name, int id,String newName, Connection con) {
        try
        {
            System.out.println("Entra al try");
            String Query = "UPDATE " + table_name +
                    " SET nombre = " +
                    "\'" + newName +  "\' " +
                    "WHERE id = " + id ;

            Statement st = con.createStatement();
            System.out.println(Query);
            st.executeUpdate(Query);
        }catch (SQLException ex)
        {
            System.out.println(ex);
        }
    }


    public static void getValues(Connection conn, String table_name)
    {
        try
        {
            Connection conexion = conn;
            String Query = "SELECT * FROM " + table_name;
            Statement st = conexion.createStatement();
            java.sql.ResultSet resultSet;
            resultSet = st.executeQuery(Query);
            
            while(resultSet.next())
            {
                System.out.println("ID: " + resultSet.getString("id") + " | " +
                    " Nombre: " + resultSet.getString("nombre"));
            }
        }catch (SQLException ex)
        {
            System.out.println("Error en la adquisición de datos");
        }
    }

    public static void deleteCarrera(String table_name, String nombre, Connection con)
    {
        try
        {
            String Query = "DELETE FROM " + table_name + " WHERE nombre = \"" + nombre + "\"";
            Statement st = con.createStatement();
            st.executeUpdate(Query);
        }catch(SQLException ex)
        {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error borrando el registro especificado");
        }
    }
    
    public static void Matricular(Scanner sc, Connection con)
    {
        try
        {
            sc.nextLine();
            //getValues(con, "alumnos");
            System.out.println("Que alumno quiere matricular?");
            String nombre = sc.nextLine();
            
            String Query = "SELECT * FROM alumnos WHERE nombre LIKE \"%" + nombre + "%\"";
            
            Statement st = con.createStatement();
            java.sql.ResultSet resultSet;
            resultSet = st.executeQuery(Query);
            
            Map<Short, String> list = new HashMap<Short, String>();
            
            int count = 0;
            while(resultSet.next())
            {
                list.put(resultSet.getShort("ID"), resultSet.getString("nombre"));
            }
            
            count = list.size();
            
            if(count > 0)
            {
                if(count > 1)
                {
                    boolean confirmed = false;
                    short alumnoID = 0;
                    do
                    {
                        for(Map.Entry<Short, String> listAlum : list.entrySet())
                        {
                            System.out.println("ID: " + listAlum.getKey() + " | " +
                                " Nombre: " + listAlum.getValue());
                        }
                        
                        System.out.println("Elija el alumno por su ID");
                        alumnoID = sc.nextShort();
                        sc.nextLine();
                        
                        
                        if(list.get(alumnoID) != null)
                        {
                            nombre = list.get(alumnoID);
                            confirmed = true;
                        }else
                        {
                            System.out.println("ID equivocado");
                        }
                    }while(confirmed == false);
                    
                }
                
                MatricularNombre(nombre, sc, con);
          }
            else
            {
                System.out.println("Alumno no existe en el instituto");
            }
        }catch(SQLException ex)
        {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error matriculando al alumno");
        }
    }
    
    public static void MatricularNombre(String nombre, Scanner sc, Connection con)
    {
        
        try
        {
            String Query = "SELECT DISTINCT a.nombre, c.nombre FROM carreras c \n" +
                "JOIN semestres s ON s.idCarreras = c.id \n" +
                "JOIN materias m ON m.idSemestre = s.id\n" +
                "JOIN alumnos_materias am ON am.idMaterias = m.id\n" +
                "JOIN alumnos a ON am.idAlumnos = a.id WHERE a.nombre LIKE \"" + nombre +"\" GROUP BY c.nombre, a.nombre;";
            
            Statement st = con.createStatement();
            java.sql.ResultSet resultSet;
            resultSet = st.executeQuery(Query);
            
            Map<String, String> list = new HashMap<String, String>();
            
            int count = 0;
            while(resultSet.next())
            {
                list.put(resultSet.getString("a.nombre"), resultSet.getString("c.nombre"));
            }
            
            count = list.size();
            
            if(count > 0)
            {
                System.out.println("El alumno ya esta matriculado en una carrera");
            }
            else
            {
                System.out.println("Que carrera quiere cursar?");
                getValues(con, "carreras");
                String carr = sc.nextLine();
                
                Query = "SELECT DISTINCT c.id FROM carreras c \n" +
                "JOIN semestres s ON s.idCarreras = c.id \n" +
                "JOIN materias m ON m.idSemestre = s.id wHERE c.nombre LIKE \"" + carr + "\"";
                
                resultSet = st.executeQuery(Query);
                resultSet.next();
                short idCarr = resultSet.getShort("ID");
                Query = "SELECT id FROM alumnos WHERE nombre LIKE \"" + nombre + "\"";
                resultSet = st.executeQuery(Query);
                resultSet.next();
                short idAlumn = resultSet.getShort("ID");
                
                System.out.println(idCarr + " " + idAlumn);
                
                Query = "INSERT INTO `control_escolar`.`alumnos_materias` (`idAlumnos`, `idMaterias`) VALUES ('" + idAlumn +"', '" + idCarr +"');";
                System.out.println(Query);
                st.executeUpdate(Query);
            }
        }catch(SQLException ex)
        {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error matriculando al alumno");
        }   
    }
    
    public static void closeConnection(Connection con)
    {
        try
        {
            con.close();
            JOptionPane.showMessageDialog(null, "Se ha finalizado la conexión con el servidor");
        }catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "No se ha podido finalizar la conexión con el servidor");

        }
    }
}
