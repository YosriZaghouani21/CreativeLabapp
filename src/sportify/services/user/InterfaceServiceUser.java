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
public interface InterfaceServiceUser extends IService<User> {
        @Override
    public User GetById(int ID);

   // @Override
    public List <User> Getall();

    @Override
    public void Update(User entity);

    @Override
    public void Delete(User entity);

    @Override
    public User Add(User entity);
    





    
}
