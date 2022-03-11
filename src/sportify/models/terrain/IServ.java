/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportify.models.terrain;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author MYLAPTOP
 * @param <T>
 */
public interface IServ <T>{
    
    public void ajouterR(T r) throws SQLException;
    public void modifierR(T r) throws SQLException;
    public void deleteR(T r) throws SQLException;

    public List<T> afficherR() throws SQLException;
}
