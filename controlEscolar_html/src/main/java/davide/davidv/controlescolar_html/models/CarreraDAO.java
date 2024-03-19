/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package davide.davidv.controlescolar_html.models;

import davide.davidv.controlescolar_html.sql.DBConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mati
 */
public class CarreraDAO {
    
    private DBConnection dbConn;
    private Connection connection;

    public CarreraDAO(DBConnection dbConn) {
        this.dbConn = dbConn;
    }

    public DBConnection getDbConn() {
        return dbConn;
    }

    public void setDbConn(DBConnection dbConn) {
        this.dbConn = dbConn;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    private List<Carrera> executeQuery(String query){
        List<Carrera> users = new ArrayList<>();
        Connection conn = getConnection();
        if (conn == null){
            try{
                setConnection(dbConn.getConnection());
            } catch (SQLException | IOException e){
                e.printStackTrace();
            }
        }
        
        try (
                Statement stmt = getConnection().createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Carrera user = buildCarreraFromResultSet(rs);
                users.add(user);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return users;
    }
    
    private Carrera buildCarreraFromResultSet(ResultSet rs) throws SQLException{
        String name = rs.getString("name");
        Carrera carr= new Carrera(name);
        return carr;
    }
    
    
    public List<Carrera>findAllCarrera(){
        String qry = "SELECT * FROM carrera";
        List<Carrera> users = executeQuery(qry);
        return users;
        
        
    }
    
    
    
}
