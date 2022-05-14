/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportify.models.articles;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author dali
 */
public interface CtegorieArt<T> {
    
    public void AddCategorie(T entity)throws SQLException ;
    public void DeletCategorie(T entity)throws SQLException;
    public List<T> display();
    public void UpdateCategirie(T entity)throws SQLException;
    public T getArticleById(T entity);
    
}
