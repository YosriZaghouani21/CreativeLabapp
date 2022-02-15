/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sportify.models.user;

/**
 *
 * @author zagho
 */
public class Client {
    
    public  int IdClient;
    public  String NomClient ;
    public String PrenomClient;
    public int NumClient;
    public String AdresseClient;
    public String EmailClient;
    public String Password;
    public int IdAdmin;

    public Client(int IdClient, String NomClient, String PrenomClient, int NumClient, String AdresseClient, String EmailClient, String Password, int IdAdmin) {
        this.IdClient = IdClient;
        this.NomClient = NomClient;
        this.PrenomClient = PrenomClient;
        this.NumClient = NumClient;
        this.AdresseClient = AdresseClient;
        this.EmailClient = EmailClient;
        this.Password = Password;
        this.IdAdmin = IdAdmin;
    }

    public Client(String NomClient, String PrenomClient, int NumClient, String AdresseClient, String EmailClient, String Password, int IdAdmin) {
        this.NomClient = NomClient;
        this.PrenomClient = PrenomClient;
        this.NumClient = NumClient;
        this.AdresseClient = AdresseClient;
        this.EmailClient = EmailClient;
        this.Password = Password;
        this.IdAdmin = IdAdmin;
    }

    public Client(int IdClient) {
        this.IdClient = IdClient;
    }

    public Client() {
    }

    public int getIdClient() {
        return IdClient;
    }

    public void setIdClient(int IdClient) {
        this.IdClient = IdClient;
    }

    public String getNomClient() {
        return NomClient;
    }

    public void setNomClient(String NomClient) {
        this.NomClient = NomClient;
    }

    public String getPrenomClient() {
        return PrenomClient;
    }

    public void setPrenomClient(String PrenomClient) {
        this.PrenomClient = PrenomClient;
    }

    public int getNumClient() {
        return NumClient;
    }

    public void setNumClient(int NumClient) {
        this.NumClient = NumClient;
    }

    public String getAdresseClient() {
        return AdresseClient;
    }

    public void setAdresseClient(String AdresseClient) {
        this.AdresseClient = AdresseClient;
    }

    public String getEmailClient() {
        return EmailClient;
    }

    public void setEmailClient(String EmailClient) {
        this.EmailClient = EmailClient;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public int getIdAdmin() {
        return IdAdmin;
    }

    public void setIdAdmin(int IdAdmin) {
        this.IdAdmin = IdAdmin;
    }
    
    
}
