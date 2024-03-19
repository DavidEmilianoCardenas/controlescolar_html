/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package davide.davidv.controlescolar_html.models;

import davide.davidv.controlescolar_html.sql.DBConnection;
import java.util.List;

/**
 *
 * @author Mati
 */
public class CarreraService {
    
    private CarreraDAO carreraDAO;

    public CarreraService() {
        DBConnection dbConnection = new DBConnection("db.properties");
        this.carreraDAO = new CarreraDAO(dbConnection);
    }
    
    public List<Carrera> getCarreraList(){
        return carreraDAO.findAllCarrera();
    }
    
    
}
