/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sportify;

/**
 *
 * @author zagho
 */
public class Config {
    private static final String url="jdbc:mysql://127.0.0.1:3306/sportifydatabase";
    private static final String user = "root";
    private static final String password = "";

    public static String getUrl() {
        return url;
    }

    public static String getUser() {
        return user;
    }

    public static String getPassword() {
        return password;
    }

}
