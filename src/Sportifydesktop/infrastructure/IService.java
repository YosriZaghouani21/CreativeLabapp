/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Sportifydesktop.infrastructure;

import java.util.List;

/**
 *
 * @author zagho
 */
public interface IService<T> {
    void ajouter(T entity);
    void supprimer(int entity);
    void modifier(T entity);
     List<T> afficher();
}
