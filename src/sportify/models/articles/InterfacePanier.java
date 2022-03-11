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
public interface InterfacePanier <T> {
    
    public void AddPanier(T entity)throws SQLException;
    public void DeletPanier(T entity);
    public void UpdatePanier(T entity);
    public List<T> display();
    public void FindPanier(T entity);
    public List<Panier> displayPanierUser(T entity);
    public List<Article> displayArticlePanier(int id);
    public void DeleteArticleFromPanier(T entity)throws SQLException;
    
}