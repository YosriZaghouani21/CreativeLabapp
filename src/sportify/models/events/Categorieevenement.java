/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sportify.models.events;

/**
 *
 * @author zagho
 */
public class Categorieevenement {
    private int idCategEv;
    private String nomCategEv;
    private String TypeCategEv;

    public Categorieevenement() {
    }

    public Categorieevenement(int idCategEv, String nomCategEv, String TypeCategEv) {
        this.idCategEv = idCategEv;
        this.nomCategEv = nomCategEv;
        this.TypeCategEv = TypeCategEv;
    }

    public Categorieevenement(String nomCategEv, String TypeCategEv) {
        this.nomCategEv = nomCategEv;
        this.TypeCategEv = TypeCategEv;
    }

    
    public int getIdCategEv() {
        return idCategEv;
    }

    public void setIdCategEv(int idCategEv) {
        this.idCategEv = idCategEv;
    }

    public String getNomCategEv() {
        return nomCategEv;
    }

    public void setNomCategEv(String nomCategEv) {
        this.nomCategEv = nomCategEv;
    }

    public String getTypeCategEv() {
        return TypeCategEv;
    }

    public void setTypeCategEv(String TypeCategEv) {
        this.TypeCategEv = TypeCategEv;
    }

    @Override
    public String toString() {
        return "Categorieevenement{" + "idCategEv=" + idCategEv + ", nomCategEv=" + nomCategEv + ", TypeCategEv=" + TypeCategEv + '}';
    }
    
    
    
}
