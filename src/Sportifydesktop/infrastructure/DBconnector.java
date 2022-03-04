/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Sportifydesktop.infrastructure;
import java.sql.*;
import sportify1.Config;
/**
 *
 * @author zagho
 */
public class DBconnector {
    private String url = Config.getUrl();
    private String user = Config.getUser();
    private String password = Config.getPassword();

    private Connection cnx;
    private static DBconnector instance;
    
    public DBconnector() {
        
        try {
            cnx = DriverManager.getConnection(url, user, password);
            System.out.println("Database Connected");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public static DBconnector getInstance() {
        if(instance == null){
            instance = new DBconnector();
        }
        return instance;
    }

    public Connection getCnx() {
        return cnx;
    }    
}
