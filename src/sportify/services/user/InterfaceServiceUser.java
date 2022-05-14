/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package sportify.services.user;
import sportify.models.user.User;
import Sportifydesktop.infrastructure.IService;
import java.util.List;

/**
 *
 * @author zagho
 */
public interface InterfaceServiceUser <T>{
       
 void ajouter(T entity);
    void supprimer(int entity);
    void modifier(T entity);
     List<T> afficher();
    User returnuser (String name , String password);


    
}
