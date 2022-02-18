/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionArticle;

import java.util.List;

/**
 *
 * @author dali
 */
public interface InterfacePanier <T> {
    
    public void AddPanier(T entity);
    public void DeletPanier(int ref);
    public void UpdatePanier(int id, int ref, int id_user);
    List<T> display();
    public void FindPanier(T entity);
}