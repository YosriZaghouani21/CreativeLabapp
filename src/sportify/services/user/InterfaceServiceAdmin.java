/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportify.services.user;

//import java.util.List;
import Sportifydesktop.infrastructure.IService;
import sportify.models.user.Admin;

/**
 *
 * @author zagho
 */
public interface InterfaceServiceAdmin extends IService<Admin> {

    @Override
    public Admin GetById(int ID);

   // @Override
   // public List<Admin> Getall();

    @Override
    public void Update(Admin entity);

    @Override
    public void Delete(Admin entity);

    @Override
    public Admin Add(Admin entity);
    
}