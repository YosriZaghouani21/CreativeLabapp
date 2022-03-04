/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Sportify.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import sportify.model.Coach;

/**
 *
 * @author zagho
 */
public class DBconnector {
    private String url = "jdbc:mysql://localhost:3306/sportifydatabase";
    private String user = "root";
    private String password = "";

    private Connection cnx;
    private static DBconnector instance;
    
    private DBconnector() {
        
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

