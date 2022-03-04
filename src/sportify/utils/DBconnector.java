/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sportify.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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

