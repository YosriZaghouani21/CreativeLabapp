/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sportify.models.user;

/**
 *
 * @author zagho
 */
public class User {
       public  int Id;
    private  String Nom ;
    private String Prenom;
    private String Adresse;
    private String Email;
    private String Password;
    private String Type;
     private String Num;

    public User(String Type) {
        this.Type = Type;
    }

    public User(int Id, String Nom, String Prenom, String Adresse, String Email, String Password, String Type, String Num) {
        this.Id = Id;
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.Num = Num;
        this.Adresse = Adresse;
        this.Email = Email;
        this.Password = Password;
        this.Type = Type;
    }

    public User(String Nom, String Prenom, String Adresse, String Email, String Password, String Num) {
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.Adresse = Adresse;
        this.Email = Email;
        this.Password = Password;
        this.Num = Num;
    }
    
    

    public User(String Nom, String Prenom, String Adresse, String Email, String Password, String Type, String Num) {
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.Num = Num;
        this.Adresse = Adresse;
        this.Email = Email;
        this.Password = Password;
        this.Type = Type;
    }

    public User(String Email, String Password) {
        this.Email = Email;
        this.Password = Password;
    }
    
    
   
    public User(int Id) {
        this.Id = Id;
    }

    public User() {
    }

  

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public void setPrenom(String Prenom) {
        this.Prenom = Prenom;
    }

    public String getNum() {
        return Num;
    }

    public void setNum(String Num) {
        this.Num = Num;
    }

    public String getAdresse() {
        return Adresse;
    }

    public void setAdresse(String Adresse) {
        this.Adresse = Adresse;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    @Override
    public String toString() {
        return "User{" + "Id=" + Id + ", Nom=" + Nom + ", Prenom=" + Prenom + ", Num=" + Num + ", Adresse=" + Adresse + ", Email=" + Email + ", Password=" + Password + ", Type=" + Type + '}';
    }

    
    
    
}
