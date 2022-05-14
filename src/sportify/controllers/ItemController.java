package sportify.controllers;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import sportify.mainart.Main;
import sportify.mainart.MyListener;
import org.controlsfx.control.Rating;
import sportify.models.articles.Article;
import sportify.models.articles.rating;
import sportify.models.user.Session;
import sportify.services.articles.ServiceRating;

public class ItemController {
    @FXML
    private Label nameLabel;

    @FXML
    private Label priceLable;

    @FXML
    private ImageView img;
    @FXML
    private AnchorPane zf;
    @FXML
    private Rating rating;
    private List<rating> listArt = new ArrayList<>();
    
    

    @FXML
    private void click(MouseEvent mouseEvent) {
        myListener.onClickListener(article);
    }

    private Article article;
    private MyListener myListener;

    public void setData(Article article, MyListener myListener) {
        
        this.article = article;
        this.myListener = myListener;
        nameLabel.setText(article.getMarque());
        priceLable.setText(Main.CURRENCY + article.getPrix_article());
        Image image = new Image(getClass().getResourceAsStream(article.getPathImg()));
        img.setImage(image);
        System.out.println("litem article"+nameLabel.getText()+priceLable.getText());
    }

    @FXML
    private void dragerating(MouseEvent event) {
    }

    @FXML
    private void ratingvalue(MouseEvent event) {
        Session.getUserStatic();
        System.out.println(Session.getUserStatic().getId() + "Client en Reservation client");
        ServiceRating serviceRating= new ServiceRating();
        rating r = new rating();
        r.setIdarticle(article.getRef_articl());
        r.setIduser(Session.getUserStatic().getId());
        r.setNbrate((int) rating.getRating());
        try{
        serviceRating.AddRating(r);
            System.out.println("rate ajoute corrct");
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
            
        }
        
         
    }
    private void SetRating(){
        int a=0;
        rating.setRating(0);
    ServiceRating serviceRating= new ServiceRating();
    try{
     listArt.addAll(serviceRating.display(article.getRef_articl()));
    }catch (SQLException ex) {
            System.out.println(ex.getMessage());
            
        }
    for (int i = 0; i < listArt.size(); i++) {
        a=listArt.get(i).getNbrate();
    }
        System.out.println(a);
    rating.setRating(a);
    
    }
}
