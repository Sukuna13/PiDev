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
import edu.esprit.tests.MainClass;
import edu.esprit.tests.MyListener;
import model.Food;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MarketController implements Initializable {
    @FXML
    private VBox chosenfoodCard;

    @FXML
    private Label foodNameLable;

    @FXML
    private Label foodPriceLabel;

    @FXML
    private ImageView foodImg;

    @FXML
    private ScrollPane scroll;

    @FXML
    private GridPane grid;
    
    @FXML
    private Text decrip;

    private List<Food> foods = new ArrayList<>();
    private Image image;
    private MyListener myListener;
    @FXML
    private Button Retour;
    

    private List<Food> getData() {
        List<Food> foods = new ArrayList<>();
        Food food;

        food = new Food();
        food.setName("Riz");
        food.setPoid("100g");
        food.setImgSrc("/img/riz.png");
        food.setColor("A7745B");
        food.setDescrip("129 CAL \n 0.28g FAT \n 27.9g CARBS \n 3g PROTEIN");
        foods.add(food);
        
        food = new Food();
        food.setName("Pomme de terre");
        food.setPoid("100g");
        food.setImgSrc("/img/batata.png");
        food.setColor("A7745B");
        food.setDescrip("149 CAL \n 7g FAT \n 20,06g CARBS \n 2,32g PROTEIN");
        foods.add(food);
        
        food = new Food();
        food.setName("Poulet");
        food.setPoid("100g");
        food.setImgSrc("/img/djej.png");
        food.setColor("A7745B");
        food.setDescrip("195 CAL \n 7,72g FAT \n 0g CARBS \n 30g PROTEIN");
        foods.add(food);
        
        food = new Food();
        food.setName("Poisson");
        food.setPoid("100g");
        food.setImgSrc("/img/7out.png");
        food.setColor("A7745B");
        food.setDescrip("126 CAL \n 3,44g FAT \n 0,33g CARBS \n 21,94g PROTEIN");
        foods.add(food);
        
        food = new Food();
        food.setName("Viande");
        food.setPoid("100g");
        food.setImgSrc("/img/l7am.png");
        food.setColor("A7745B");
        food.setDescrip("252 CAL \n 15,01g FAT \n 0g CARBS \n 27,29g PROTEIN");
        foods.add(food);
        
        food = new Food();
        food.setName("Oeuf");
        food.setPoid("100g");
        food.setImgSrc("/img/egg.png");
        food.setColor("A7745B");
        food.setDescrip("74 CAL \n 5g FAT \n 0,38g CARBS \n 6,29g PROTEIN");
        foods.add(food);
        
        food = new Food();
        food.setName("Salade");
        food.setPoid("100g");
        food.setImgSrc("/img/salade.jpg");
        food.setColor("A7745B");
        food.setDescrip("64 CAL \n 3,3g FAT \n 7,92g CARBS \n 1,39g PROTEIN");
        foods.add(food);
        
        food = new Food();
        food.setName("Yaourt");
        food.setPoid("100g");
        food.setImgSrc("/img/ya.png");
        food.setColor("A7745B");
        food.setDescrip("160 CAL \n 1,5g FAT \n 30g CARBS \n 6g PROTEIN");
        foods.add(food);
        
        food = new Food();
        food.setName("Flocon d'avoine");
        food.setPoid("100g");
        food.setImgSrc("/img/choufen.png");
        food.setColor("A7745B");
        food.setDescrip("384 CAL \n 6,3g FAT \n 67g CARBS \n 16g PROTEIN");
        foods.add(food);
        
        food = new Food();
        food.setName("Brocoli");
        food.setPoid("100g");
        food.setImgSrc("/img/brocoli.png");
        food.setColor("A7745B");
        food.setDescrip("34 CAL \n 0,37g FAT \n 6,64g CARBS \n 2,82g PROTEIN");
        foods.add(food);

        return foods;
    }

    private void setChosenfood(Food food) {
        foodNameLable.setText(food.getName());
        foodPriceLabel.setText(""+food.getPoid());
        decrip.setText(food.getDescrip());
        image = new Image(getClass().getResourceAsStream(food.getImgSrc()));
        foodImg.setImage(image);
        chosenfoodCard.setStyle("-fx-background-color: #" + food.getColor() + ";\n" +
                "    -fx-background-radius: 30;");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        foods.addAll(getData());
        if (foods.size() > 0) {
            setChosenfood(foods.get(0));
            myListener = new MyListener() {
                @Override
                public void onClickListener(Food food) {
                    setChosenfood(food);
                }
            };
        }
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < foods.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/views/item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemController itemController = fxmlLoader.getController();
                itemController.setData(foods.get(i),myListener);

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
    private void retour(ActionEvent event) throws IOException {
        
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("../gui/result.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
        
        
    }
        
        
    }


