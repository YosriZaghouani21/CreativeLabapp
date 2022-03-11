/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportify.services.coachservice;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author salim
 */
public interface ServiceCoachInterface<T> {
    public void AddCoach(T entity) throws SQLException;
  public void DeletCoach(int id_coach) throws SQLException; 
    public void modifierCoach(T entity)throws SQLException ;    
    List<T> GetById();
   
}
