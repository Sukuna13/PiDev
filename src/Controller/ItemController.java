package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import edu.esprit.tests.MainClass;
import edu.esprit.tests.MyListener;
import model.Food;

public class ItemController {
    @FXML
    private Label nameLabel;

    @FXML
    private Label priceLable;

    @FXML
    private ImageView img;

    @FXML
    private void click(MouseEvent mouseEvent) {
        myListener.onClickListener(food);
    }

    private Food food;
    private MyListener myListener;

    public void setData(Food food, MyListener myListener) {
        this.food = food;
        this.myListener = myListener;
        nameLabel.setText(food.getName());
        priceLable.setText(food.getPoid()+"");
        Image image = new Image(getClass().getResourceAsStream(food.getImgSrc()));
        img.setImage(image);
    }
}
