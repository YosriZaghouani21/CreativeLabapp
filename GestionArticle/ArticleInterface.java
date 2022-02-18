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
public interface ArticleInterface<T> {
    
    public void AddArticle(T entity);
    public void DeletArticle(int ref);
    public void UpdateArticle(int ref, int id, String Des, float prix, String Marque);
    List<T> display();
    public void FindArticle(T entity);
    
    
    
    
}
