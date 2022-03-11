package sportify.controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import sportify1.Sportify1;
import main.MyListener;

import sportify.models.coachs.Coach;

public class ItemCoachController {
    @FXML
    private Label nameLabel;
    @FXML
    private Label priceLable;
    @FXML
    private ImageView img;
    @FXML
    private Label prenomLabel;

    @FXML
    private void click(MouseEvent mouseEvent) {
        myListener.onClickListener(coach);
    }

    private Coach coach;
    private MyListener myListener;

    public void setData(Coach coach, MyListener myListener) {
        
        this.coach = coach;
        this.myListener = myListener;
        nameLabel.setText(coach.getNom());
        prenomLabel.setText(coach.getPrenom());
        priceLable.setText(Sportify1.CURRENCY + coach.getPrixHeure());
        Image image = new Image(getClass().getResourceAsStream(coach.getImg()));
        img.setImage(image);
        
    }
}
