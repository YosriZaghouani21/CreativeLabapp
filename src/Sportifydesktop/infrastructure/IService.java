/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Sportifydesktop.infrastructure;

/**
 *
 * @author zagho
 */
public interface IService<T> {
    T Add(T entity);
    void Delete(T entity);
    void Update(T entity);
    //List<T> Getall();
    T GetById(int ID);
}
