/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportify.models.user;

/**
 *
 * @author zagho
 */
public class Session {

    private static int id;
    private static String nom;
    private static String prenom;
    private static String email;
    private static String type;
    private static String password;
    private static String numero;
    public static User UserStatic;
    private static String VerificationCode ="2XXX";

    public Session() {
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        Session.id = id;
    }

    public static String getNom() {
        return nom;
    }

    public static void setNom(String nom) {
        Session.nom = nom;
    }

    public static String getPrenom() {
        return prenom;
    }

    public static void setPrenom(String prenom) {
        Session.prenom = prenom;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        Session.email = email;
    }

    public static String getType() {
        return type;
    }

    public static void setType(String type) {
        Session.type = type;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        Session.password = password;
    }

    public static String getNumero() {
        return numero;
    }

    public static void setNumero(String numero) {
        Session.numero = numero;
    }

    public static User getUserStatic() {
        return UserStatic;
    }

    public static void setUserStatic(User UserStatic) {
        Session.UserStatic = UserStatic;
    }

    public static String getVerificationCode() {
        return VerificationCode;
    }

    public static void setVerificationCode(String VerificationCode) {
        Session.VerificationCode = VerificationCode;
    }

    public static class getUserStatic {

        public getUserStatic() {
        }
    }

}
