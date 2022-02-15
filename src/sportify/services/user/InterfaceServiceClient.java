/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package sportify.services.user;

import sportify.models.user.Client;
import Sportifydesktop.infrastructure.IService;


/**
 *
 * @author zagho
 */
public interface InterfaceServiceClient extends IService<Client> {
     @Override
    public Client GetById(int ID);

   // @Override
   // public List<Admin> Getall();

    @Override
    public void Update(Client entity);

    @Override
    public void Delete(Client entity);

    @Override
    public Client Add(Client entity);
    
    
}
