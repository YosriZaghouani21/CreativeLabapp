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
public interface ArticleInterface<T> {
    
    public T getArticleById(T entity);
    public void AddArticle(T entity) throws SQLException;
    public boolean VeriefAritcle(T entity) throws SQLException;
    public void DeletArticle(T entity) throws SQLException;
    public void UpdateArticle(T entity) throws SQLException;
    List<Article> display() throws SQLException;
    public List<Article> FindArticle(String Marque) throws SQLException;
    public List<Article> TreeAticleListe() throws SQLException;
    
    
    
    
}
