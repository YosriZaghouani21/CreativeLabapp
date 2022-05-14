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
 * @author macbook
 * @param <T>
 */
public interface IService<T> {
    public void ajouterp(T p) throws SQLException;
    public void modifierp(T p) throws SQLException;
    public void deletep(T p) throws SQLException;
    public List<T> afficherterrain() throws SQLException;
    List<T> getAll();
    
    
}
