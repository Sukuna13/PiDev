package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

import fitnessny.configr.MyConnection;
import fitnessny.entities.Utilisateur;
import fitnessny.service.CreateService;
import fitnessny.service.GetService;
import gui.Coach.GestionCoachController;
import gui.Salle.GestionCourController;
import gui.Sportif.GestionSportifController;
import javafx.animation.Animation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.util.Duration;

public class InscriptionController implements Initializable {

    @FXML
    private Button uploadButton;

    @FXML
    private Text exists;

    @FXML
    private Text telControll;
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private DatePicker datenais;
    @FXML
    private TextField adressemail;
    @FXML
    private TextField mdp;

    @FXML
    private ChoiceBox<String> whoami = new ChoiceBox<>();
    private String[] acteurs = {"Administrateur", "Coach", "Sportif", "Gérant"};
    @FXML
    private TextField numtel;
    @FXML
    private TextField adresse;

    @FXML
    private Text adressenonvalide;

    int nbValid;

    FileInputStream fileinputSteam;

    @FXML
    public void saveit(ActionEvent event) {
        if (nom.getText().length() == 0) {
            nom.setStyle("-fx-border-color:red; -fx-border-width:2px;");
            new animatefx.animation.Shake(nom).setDelay(Duration.seconds(0)).play();
        } else {
            nbValid++;
            nom.setStyle(null);
        }

        if (!isValidEmailAddress(adressemail.getText()) && adressemail.getText().length() != 0) {
            adressenonvalide.setText("Adresse non valide !");
            adressemail.setStyle("-fx-border-color:red; -fx-border-width:2px;");
            new animatefx.animation.Shake(adressemail).setDelay(Duration.seconds(0)).play();
            System.out.println(adressemail.getText() + "not valid");
        } else {
            adressenonvalide.setText("");
            adressemail.setStyle(null);
            nbValid++;

        }

        if (adressemail.getText().length() == 0) {
            adressemail.setStyle("-fx-border-color:red; -fx-border-width:2px;");
            new animatefx.animation.Shake(adressemail).setDelay(Duration.seconds(0)).play();
            System.out.println(adressemail.getText() + "not valid");
        } else {
            nbValid++;
            adressemail.setStyle(null);
        }

        if (adresse.getText().length() == 0) {
            adresse.setStyle("-fx-border-color:red; -fx-border-width:2px;");
            new animatefx.animation.Shake(adresse).setDelay(Duration.seconds(0)).play();
        } else {
            nbValid++;
            adresse.setStyle(null);
        }

//	    		
//	    	 if(mdp.getText().length()==0) {
//	    		mdp.setStyle("-fx-border-color:red; -fx-border-width:2px;");
//	    		new animatefx.animation.Shake(mdp).setDelay(Duration.seconds(0)).play();
//	    	}else {
//	    		mdp.setStyle(null);
//	    	}
        //controle de saisie de mot de passe
        boolean isValid = true;
        if (mdp.getText().toString().length() > 15 || mdp.getText().toString().length() < 8) {
            mdp.setStyle("-fx-border-color:red; -fx-border-width:2px;");
            new animatefx.animation.Shake(mdp).setDelay(Duration.seconds(0)).play();
            passwordctrl.setText("Longueur invalide ! [8-15] ");
            isValid = false;
        }
        String upperCaseChars = "(.*[A-Z].*)";
        if (!mdp.getText().toString().matches(upperCaseChars)) {
            mdp.setStyle("-fx-border-color:red; -fx-border-width:2px;");
            new animatefx.animation.Shake(mdp).setDelay(Duration.seconds(0)).play();
            passwordctrl.setText("Insérer du majuscule");
            isValid = false;
        }
        String lowerCaseChars = "(.*[a-z].*)";
        if (!mdp.getText().toString().matches(lowerCaseChars)) {
            mdp.setStyle("-fx-border-color:red; -fx-border-width:2px;");
            new animatefx.animation.Shake(mdp).setDelay(Duration.seconds(0)).play();
            passwordctrl.setText("Insérer du minuscule");
            isValid = false;
        }
        String numbers = "(.*[0-9].*)";
        if (!mdp.getText().toString().matches(numbers)) {
            mdp.setStyle("-fx-border-color:red; -fx-border-width:2px;");
            new animatefx.animation.Shake(mdp).setDelay(Duration.seconds(0)).play();
            passwordctrl.setText("Insérer des chiffres");
            isValid = false;
        }
        String specialChars = "(.*[@,#,$,%].*$)";
        if (!mdp.getText().toString().matches(specialChars)) {
            mdp.setStyle("-fx-border-color:red; -fx-border-width:2px;");
            new animatefx.animation.Shake(mdp).setDelay(Duration.seconds(0)).play();
            passwordctrl.setText("Insérer des caractères spéciaux (@,#,$,%)");
            isValid = false;
        }

        if (isValid) {
            passwordctrl.setText("");
            mdp.setStyle(null);
            nbValid++;
        }

        if (datenais.getValue() == null) {
            datenais.setStyle("-fx-border-color:red; -fx-border-width:2px;");
            new animatefx.animation.Shake(datenais).setDelay(Duration.seconds(0)).play();
        } else {
            datenais.setStyle(null);
            nbValid++;
        }

        if (whoami.getValue() == null) {
            whoami.setStyle("-fx-border-color:red; -fx-border-width:2px;");
            new animatefx.animation.Shake(whoami).setDelay(Duration.seconds(0)).play();
        } else {
            whoami.setStyle(null);
            nbValid++;
        }

        if (prenom.getText().length() == 0) {
            prenom.setStyle("-fx-border-color:red; -fx-border-width:2px;");
            new animatefx.animation.Shake(prenom).setDelay(Duration.seconds(0)).play();
        } else {
            prenom.setStyle(null);
            nbValid++;
        }

//	    	 if(numtel.getText().length()==0) {
//	    		 numtel.setStyle("-fx-border-color:red; -fx-border-width:2px;");
//	    		new animatefx.animation.Shake(numtel).setDelay(Duration.seconds(0)).play();
//	    		
//	    	}else {
        Boolean valid = true;
        if (numtel.getText().length() != 8) {
            numtel.setStyle("-fx-border-color:red; -fx-border-width:2px;");
            new animatefx.animation.Shake(numtel).setDelay(Duration.seconds(0)).play();
            telControll.setText("Longueur invalide !");

            try {
                Long.parseLong(numtel.getText().toString());
            } catch (NumberFormatException e) {
                telControll.setText("Numéro invalide ");
            }
        } else if (numtel.getText().length() == 8) {
            try {
                Long.parseLong(numtel.getText().toString());
            } catch (NumberFormatException e) {
                numtel.setStyle("-fx-border-color:red; -fx-border-width:2px;");
                new animatefx.animation.Shake(numtel).setDelay(Duration.seconds(0)).play();
                telControll.setText("Numéro invalide ");
                valid = false;
            }
            if (valid) {
                telControll.setText("");
                numtel.setStyle(null);
                nbValid++;
            }
        }

        if (nbValid == 9) {

            System.out.println("entering to method insc---------------");

            String name = nom.getText();
            String prenoom = prenom.getText();
            String mailadress = adressemail.getText();
            String motdp = mdp.getText();
            String whoamii = whoami.getValue();
            String tel = numtel.getText();
            String adress = adresse.getText();
            System.out.println("BEFORE CRYPT--------");
            //String cryptedpw = BCrypt.hashpw(motdp, BCrypt.gensalt());
            //	System.out.println(cryptedpw);
            Date datenaissance = Date.valueOf(datenais.getValue());

            CreateService gs = new CreateService();
            GetService getsv = new GetService();
            if (getsv.isexist(mailadress, motdp)) {

                exists.setText("existe !");
            } else {

                exists.setText("");

                Utilisateur user = new Utilisateur(name, prenoom, adress, tel, motdp, mailadress, datenaissance, whoamii, null, null, fileinputSteam, null);

                //getting connection
                MyConnection cnx = MyConnection.getInstance();
                gs.addUser(user);
                System.out.println("added");
                if (user.getWhoami().equals("Super") || user.getWhoami().equals("Administrateur")) {
                    AdminHome.connected = user;
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminHome.fxml"));
                    try {
                        Parent root = loader.load();
                        AdminHome hc = loader.getController();
                        hc.setTantque(user, user.getNom());
                        //pour ne pas instancier une autre scene
                        nom.getScene().setRoot(root);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                if (user.getWhoami().equals("Coach")) {
                    GestionCoachController.connected = user;
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Coach/GestionCoach.fxml"));
                    try {
                        Parent root = loader.load();
                        GestionCoachController hc = loader.getController();
//				hc.setTantque(user, user.getNom());
                        //pour ne pas instancier une autre scene
                        nom.getScene().setRoot(root);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (user.getWhoami().equals("Sportif")) {
                    GestionSportifController.connected = user;
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Sportif/GestionSportif.fxml"));
                    try {
                        Parent root = loader.load();
                        GestionCoachController hc = loader.getController();
                        //hc.setTantque(user, user.getNom());
                        //pour ne pas instancier une autre scene
                        nom.getScene().setRoot(root);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (user.getWhoami().equals("Gérant")) {
                    GestionCourController.connected = user;
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Salle/GestionCour.fxml"));
                    try {
                        Parent root = loader.load();
                        GestionCourController hc = loader.getController();
                        //hc.setTantque(user, user.getNom());
                        //pour ne pas instancier une autre scene
                        nom.getScene().setRoot(root);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }

        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        nbValid = 0;
        whoami.getItems().addAll(acteurs);

    }

    public boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }

    public void toLogin(ActionEvent e) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
        try {
            Parent root = loader.load();
            //pour ne pas instancier une autre scene
            nom.getScene().setRoot(root);

        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }

    @FXML
    private Text passwordctrl;

    public void uploadImage(ActionEvent e) {

        FileChooser filechooser = new FileChooser();
        File file = filechooser.showOpenDialog(uploadButton
                .getScene().getWindow());
        try {
            fileinputSteam = new FileInputStream(file);

        } catch (Exception e2) {
            // TODO: handle exception
        }

    }

}
