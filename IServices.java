/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportify.services;

import java.util.List;

/**
 *
 * @author MYLAPTOP
 */
public interface IServices<T> {
    
    void ajouter(T entity);
    void supprimer(T entity);
    void modifier(T entity);
    void rechercher (T entity);
    List<T> afficher();
}
