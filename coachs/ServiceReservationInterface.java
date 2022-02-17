/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportify.models.coachs;

import java.sql.Date;
import java.util.List;

/**
 *
 * @author salim
 */
public interface ServiceReservationInterface<S> {
    public void AddReservation(S entity);
    public void DeleteReservation(int RefRes);
    public void modifierReservation(int RefRes ,int id_coach ,int Id_user,Date Date_res,int nbrHeur,float PrixTotal);
     List<S> Afficher();
    
}
