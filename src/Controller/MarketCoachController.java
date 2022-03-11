package Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import main.Main;
import main.MyListener;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sportify.models.coachs.Coach;

import sportify.services.coachservice.CoachService;

public class MarketCoachController implements Initializable {

    private String idcat;
    private String desc;
    private String prix;
    private Parent root;
    private Stage stage;
    private Scene scene;
    private String marque;
    private String img;


    @FXML
    private GridPane grid;

    private List<Coach> listArt = new ArrayList<>();
    Coach coach = new Coach();
    @FXML
    private VBox choosarticle;
    @FXML
    private Label articleNameLable;
    @FXML
    private Label articlePriceLabel;
    @FXML
    private ImageView articleImg;

    @FXML
    private ScrollPane scroll;
    @FXML
    private TextField search;

   

    private Image image;
    private MyListener myListener;
    @FXML
    private Button reserveid;
    @FXML
    private ComboBox<String> idcombo;
    @FXML
    private Label articlePrenomLable;
    @FXML
    private Button buttonadd1;
    @FXML
    private Button buttonadd2;
    @FXML
    private Button buttonadd;
    @FXML
    private Button statistique;
  

 

    private void setChosenFruit(Coach coach) {
        articleNameLable.setText(coach.getNom());
        articlePrenomLable.setText(coach.getPrenom());
        articlePriceLabel.setText(Main.CURRENCY + coach.getPrixHeure());
        image = new Image(getClass().getResourceAsStream(coach.getImg()));
        articleImg.setImage(image);
        choosarticle.setStyle("-fx-background-radius: 30;");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showScreen();
        idcombo.getItems().add("Par Nom");
        idcombo.getItems().add("Par Prenom");
        idcombo.getItems().add("Par Prix");
        

    }

    @FXML
    private void hundelbutton(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/viewsCoach/FormulaireCoach.fxml"));
            fxmlLoader.load();
            Parent p = fxmlLoader.getRoot();
            Stage stage = new Stage();
            stage.setTitle("ABC");
            stage.setScene(new Scene(p));
            stage.show();
            
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    

    @FXML
    private void deletbutton(ActionEvent event) {
         CoachService cs = new CoachService();
        try {
            System.out.println("id coach"+coach.getId());
            cs.DeletCoach(coach.getId());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("DEtelt succes");
            alert.setContentText("Delete succes");
            alert.show();
            cs.DeletCoach(coach.getId());
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @FXML
    private void updatearticle(ActionEvent event) throws IOException {
        CoachService cs=new CoachService();
        Coach c = new Coach();
        
        c = cs.getCoachById(coach);
        System.out.println("update"+c);

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/viewsCoach/updateCoach.fxml"));
        fxmlLoader.load();
                

        UpdateCoachController updateController = fxmlLoader.getController();
        updateController.setTextField(c.getId(),c.getNom(),c.getPrenom(), c.getDisponibilité(),c.getPrixHeure(),c.getImg());
              
        
        Parent p = fxmlLoader.getRoot();
        Stage stage = new Stage();
        stage.setTitle("update");
        stage.setScene(new Scene(p));
        stage.show();
        showScreen();

    }

    public void showScreen() {
        CoachService coachService = new CoachService();
        //System.out.println("test" + coachService.GetById());
        listArt.addAll(coachService.GetById());

        if (listArt.size() > 0) {
            setChosenFruit(listArt.get(0));
            myListener = new MyListener() {
                @Override
                public void onClickListener(Coach coachevent) {
                    setChosenFruit(coachevent);
                    coach.setId(coachevent.getId());
                    coach.setNom(coachevent.getNom());
                    coach.setPrenom(coachevent.getPrenom());
                    coach.setDisponibilité(coachevent.getDisponibilité());
                    coach.setPrixHeure(coachevent.getPrixHeure());
                    coach.setImg(coachevent.getImg());
                    //System.out.println("Coach get"+coach);
                    

                }
            };
        }
        int column = 0;
        int row = 1;
        grid.getChildren().clear();
        try {
            for (int i = 0; i < listArt.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/viewsCoach/itemCoach.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                ItemCoachController itemController = fxmlLoader.getController();
                itemController.setData(listArt.get(i), myListener);

                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width

                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void refrechbuton(ActionEvent event) {
        listArt.clear();
        showScreen();
    }

    
    
    
    @FXML
    private void searchButton(ActionEvent event) {
        listArt.clear();
        String marque= search.getText();
        CoachService coachService = new CoachService();
        System.out.println("recherche" + coachService.rechercheCoach(marque));
        List<Coach> list=new ArrayList();
        for (Coach c:coachService.GetById()){
            if (c.getNom().toUpperCase().contains(marque.toUpperCase()) || c.getPrenom().toUpperCase().contains(marque.toUpperCase())  ){
                list.add(c);
            }
        }
        listArt.addAll(list);


        if (listArt.size() > 0) {
            setChosenFruit(listArt.get(0));
            myListener = new MyListener() {
                public void onClickListener(Coach coach) {
                    setChosenFruit(coach);
                    coach.setId(coach.getId());
                    coach.setNom(coach.getNom());
                    coach.setPrenom(coach.getPrenom());
                    coach.setImg(coach.getImg());
                    coach.setDisponibilité(coach.getDisponibilité());
                    coach.setPrixHeure(coach.getPrixHeure());
                    

                }
            };
        }
        int column = 0;
        int row = 1;
        grid.getChildren().clear();
        try {
            for (int i = 0; i < listArt.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/viewsCoach/itemCoach.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                ItemCoachController itemController = fxmlLoader.getController();
                itemController.setData(listArt.get(i), myListener);

                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width

                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void trierarticle(ActionEvent event) {
        listArt.clear();
        CoachService coachService = new CoachService();

        if (idcombo.getValue().equals("Par Nom")){
            listArt.addAll(coachService.TreeCoach());
        }
        else if (idcombo.getValue().equals("Par Prenom")){
            listArt.addAll(coachService.TreeCoachPrenom());
            
        }
        else if (idcombo.getValue().equals("Par Prix")){
            listArt.addAll(coachService.TreeCoachPrix());
            
        }
        
        if (listArt.size() > 0) {
            setChosenFruit(listArt.get(0));
            myListener = new MyListener() {
                public void onClickListener(Coach coach) {
                     setChosenFruit(coach);
                    coach.setId(coach.getId());
                    coach.setNom(coach.getNom());
                    coach.setPrenom(coach.getPrenom());
                    coach.setImg(coach.getImg());
                    coach.setDisponibilité(coach.getDisponibilité());
                    coach.setPrixHeure(coach.getPrixHeure());

                }
            };
        }
        int column = 0;
        int row = 1;
        grid.getChildren().clear();
        try {
            for (int i = 0; i < listArt.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/viewsCoach/itemCoach.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                ItemCoachController itemController = fxmlLoader.getController();
                itemController.setData(listArt.get(i), myListener);

                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width

                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    
     @FXML
    private void ReserverCoach(ActionEvent event) {
     try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/viewsCoach/reservationFXML.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("ABC");
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
     
    }

    @FXML
    private void statistique(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) statistique.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/viewsCoach/Chart.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }
}

 
    


