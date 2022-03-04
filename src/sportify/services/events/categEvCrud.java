/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportify.services.events;

import sportify.models.events.Categorieevenement;
import Sportifydesktop.infrastructure.DBconnector;
import Sportifydesktop.infrastructure.IService;
import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author Asus
 */
public class categEvCrud implements IService<Categorieevenement> {

    private Connection conn;
    private PreparedStatement pst;
    private Statement ste;

    public categEvCrud() {
        conn = DBconnector.getInstance().getCnx();
    }

    @Override
    public void ajouter(Categorieevenement C) {
            boolean test=true;
            test=Verief(C);
        if(test==false)
        {String req = "INSERT INTO `categorieevenement` (`NomCategEv`, `TypeCategEv`) VALUES (?,?)";
        
        try {

            pst = conn.prepareStatement(req);
            pst.setString(1, C.getNomCategEv());
            pst.setString(2, C.getTypeCategEv());
            pst.executeUpdate();
            System.out.println("categorie ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }}
              else System.out.println("categorie deja existe");
    }
    
    

    @Override
    public void supprimer(int idCategEv) {
        try {
            String req = "DELETE FROM categorieevenement where idCategEv=" + idCategEv;
            Statement st = new DBconnector().getCnx().createStatement();
            st.executeUpdate(req);
            System.out.println("Categorie supprimée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Categorieevenement C) {
        try {
            String req = "UPDATE categorieevenement SET nomCategEv='" + C.getNomCategEv() + "', TypeCategEv='" + C.getTypeCategEv() + "' where idCategEv=" + C.getIdCategEv();
            Statement st = new DBconnector().getCnx().createStatement();
            st.executeUpdate(req);
            System.out.println("Categorie modifée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public boolean updateCat(int idCategEv,String nomCategEv , String TypeCategEv) {
        String requete = "UPDATE categorieevenement SET  nomCategEv= ? , TypeCategEv = ? where idCategEv = ? ";
        try {
            Connection cnx = new DBconnector().getCnx();
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, idCategEv);
            pst.setString(2, nomCategEv);
            pst.setString(3, TypeCategEv);

            

            if (pst.executeUpdate() != 0) {
                System.out.println("Categorie Modifié");
            } else {
                System.out.println("Erreur");
            }
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return false;
    }

    @Override
    public List<Categorieevenement> afficher() {
        List<Categorieevenement> Categ = new ArrayList<>();

        String req = "SELECT * from `categorieevenement`";

        try {
            pst = conn.prepareStatement(req);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Categorieevenement C = new Categorieevenement();
                C.setIdCategEv(rs.getInt(1));
                C.setNomCategEv(rs.getString(2));
                C.setTypeCategEv(rs.getString(3));
                Categ.add(C);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return Categ;
    }

    public void rechercher(String index) {
        List<Categorieevenement> result = afficher().stream().filter(line -> index.equals(line.getNomCategEv())).collect(Collectors.toList());
        System.out.println("----------");
        result.forEach(System.out::println);

    }
     public void rechercher1(String index) {
        List<Categorieevenement> result = afficher().stream().filter(line -> index.equals(line.getTypeCategEv())).collect(Collectors.toList());
        System.out.println("----------");
        result.forEach(System.out::println);

    }

    public void TrierParNOM() {
        categEvCrud sa = new categEvCrud();
        List<Categorieevenement> NomParNOm = sa.afficher().stream().sorted(Comparator.comparing(Categorieevenement::getNomCategEv)).collect(Collectors.toList());
        NomParNOm.forEach(System.out::println);
    }
    
    
       public boolean Verief(Categorieevenement c) {
        boolean test = false;
        String req = "SELECT * FROM Categorieevenement WHERE  `nomCategEv`='" + c.getNomCategEv()+ "'" + "AND `TypeCategEv`='"
                + c.getTypeCategEv()+ "'";
        try {
            ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            rs.last();
            int nbrRow = rs.getRow();
            if (nbrRow != 0) {
                test = true;
            } else {
                test = false;
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return test;

    }

}
