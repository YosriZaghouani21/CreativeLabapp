/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportify.services.events;

import Sportifydesktop.infrastructure.DBconnector;
import Sportifydesktop.infrastructure.IService;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sportify.models.events.Evenement;
import java.util.*;
import java.util.stream.Collectors;
import sportify.models.events.Categorieevenement;

/**
 *
 * @author Asus
 */
public class eventCrud implements IService<Evenement> {

    private Connection conn;
    private PreparedStatement pst;
    private Statement ste;

    public eventCrud() {
        conn = DBconnector.getInstance().getCnx();
    }

    @Override
    public void ajouter(Evenement E) {
         boolean test=true;
            test=Verief(E);
            if(test==false){
        String req = "INSERT INTO `evenement` (`NomEv`, `DateEv`, `Localisation`, `Description`, `idCategEv`) VALUES (?,?,?,?,?)";

        try {

            pst = conn.prepareStatement(req);
            pst.setString(1, E.getNomEv());
            pst.setString(2, E.getDateEv());
            pst.setString(3, E.getLocalisation());
            pst.setString(4, E.getDesccription());
            pst.setInt(5, E.getIdCategEv());
            pst.executeUpdate();
            System.out.println("evenement ajouté");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }}
             else System.out.println("Evenement deja existe");
    }

    @Override
    public void supprimer(int idEv) {
        try {
            String req = "DELETE FROM evenement where idEv=" + idEv;
            Statement st = new DBconnector().getCnx().createStatement();
            st.executeUpdate(req);
            System.out.println("Evenment supprimé !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Evenement E) {
        try {
            String req = "UPDATE evenement SET NomEv='" + E.getNomEv() + "', DateEv='" + E.getDateEv() + "', Localisation='" + E.getLocalisation() + "', Description='" + E.getDesccription() + "', idCategEv=" + E.getIdCategEv() + " WHERE idEv=" + E.getIdEv();
            Statement st = new DBconnector().getCnx().createStatement();
            st.executeUpdate(req);
            System.out.println("Evenement modifée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public boolean updateEvent(int idEv ,String nomEv , String DateEv, String localisation, String desccription, int idCategEv ) {
        String requete = "UPDATE evenement SET  nomEv= ? , DateEv = ?, localisation = ?, description = ?, idCategEv = ? where idEv = ? ";
        try {
            Connection cnx = new DBconnector().getCnx();
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, idEv);
            pst.setString(2, nomEv);
            pst.setString(3, DateEv);
            pst.setString(4, localisation);
            pst.setString(5, desccription);
            pst.setInt(6, idCategEv);

            

            if (pst.executeUpdate() != 0) {
                System.out.println("Evenement Modifié");
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
    public List<Evenement> afficher() {
        List<Evenement> Ev = new ArrayList<>();

        String req = "SELECT * from `evenement`";

        try {
            pst = conn.prepareStatement(req);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Evenement E = new Evenement();
                E.setIdEv(rs.getInt("idEv"));
                E.setNomEv(rs.getString(2));
                E.setDateEv(rs.getString(3));
                E.setLocalisation(rs.getString(4));
                E.setDesccription(rs.getString(5));
                E.setIdCategEv(rs.getInt(6));
                Ev.add(E);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return Ev;
    }

    public void rechercher(String index) {
        List<Evenement> result = afficher().stream().filter(line -> index.equals(line.getNomEv())).collect(Collectors.toList());
        System.out.println("----------");
        result.forEach(System.out::println);

      }
    public void TrierParNOM() {
        eventCrud sa = new eventCrud();
        List<Evenement> NomParNOm = sa.afficher().stream().sorted(Comparator.comparing(Evenement::getNomEv)).collect(Collectors.toList());
        NomParNOm.forEach(System.out::println);
    }
    
      public boolean Verief(Evenement c) {
        boolean test = false;
        String req = "SELECT * FROM evenement WHERE  `nomEv`='" + c.getNomEv()+ "'" + "AND `DateEv`='"
                + c.getDateEv()+ "'";
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
