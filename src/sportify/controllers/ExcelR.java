/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportify.controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author MYLAPTOP
 */
public class ExcelR {
    
     private Connection connexion;
        private ResultSet resultSet ;
    private PreparedStatement pste;
     public void Excel() throws SQLException{
      
         
connexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/sportifydatabase", "root", "");
pste= connexion.prepareStatement("select * from reservationterrain");
      resultSet=pste.executeQuery("select * from reservationterrain");
      XSSFWorkbook workbook = new XSSFWorkbook(); 
      XSSFSheet spreadsheet = workbook.createSheet("List des reservations");
      
      XSSFRow row = spreadsheet.createRow(1);
      XSSFCell cell;
      cell = row.createCell(1);
      cell.setCellValue("Client");
      cell = row.createCell(2);
      cell.setCellValue("Terrain");
      cell = row.createCell(3);
      cell.setCellValue("Date Reservation");
      cell = row.createCell(4);
      cell.setCellValue("Nombre Heure");
    
      int i = 2;

      while(resultSet.next()) {
         row = spreadsheet.createRow(i);
         cell = row.createCell(1);
         cell.setCellValue(resultSet.getString("idClient"));
         cell = row.createCell(2);
         cell.setCellValue(resultSet.getString("referenceTerrain"));
         cell = row.createCell(3);
         cell.setCellValue(resultSet.getString("dateReservation"));
         cell = row.createCell(4);
         cell.setCellValue(resultSet.getString("nombreHeure"));
         i++;
      }

      FileOutputStream out=null;
    try {
        out = new FileOutputStream(new File("C:\\Users\\MYLAPTOP\\Desktop\\excel.cvs"));
    } catch (FileNotFoundException ex) {
        Logger.getLogger(ExcelAp.class.getName()).log(Level.SEVERE, null, ex);
    }
    try {
        workbook.write(out);
    } catch (IOException ex) {
        Logger.getLogger(ExcelAp.class.getName()).log(Level.SEVERE, null, ex);
    }
    try {
        out.close();
    } catch (IOException ex) {
        Logger.getLogger(ExcelAp.class.getName()).log(Level.SEVERE, null, ex);
    }
      //System.out.println("Fichier Créer");
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Fichier créer avec succes!");
            alert.show();
        
        

      
}

  
}
