/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import static javafx.scene.input.KeyCode.B;
import javafx.scene.input.MouseEvent;
import util.maConnexion;

/**
 * FXML Controller class
 *
 * @author infoevo
 */
public class StatistiquesController implements Initializable {

    @FXML
    private LineChart<?, ?> linechart;
    @FXML
    private PieChart piechart;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inilLinechart();
        initpiechart();
    }    
    @FXML
    private void inilLinechart(){
        XYChart.Series series = new XYChart.Series();
        series.getData().add(new XYChart.Data( "Janvier", 8 ));
        series.getData().add(new XYChart.Data( "Mars", 10 ));
        series.getData().add(new XYChart.Data( "Mai", 15 ));
        series.getData().add(new XYChart.Data( "Juillet", 12 ));
        series.getData().add(new XYChart.Data( "Septembre", 10 ));
        series.getData().add(new XYChart.Data( "Novembre", 8 ));
        linechart.getData().addAll(series);
        
   
    }

    @FXML
    private void initpiechart() {
        try {
            // SELECT COUNT(*) FROM utilisateur WHERE nombre_achat > 0
            Connection cnx = maConnexion.getInstance().getCnx();

            String req = "SELECT COUNT(*) AS rowcount FROM  produit WHERE categorie_prod like '%Vetements%' ";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            rs.next();
            int nb = rs.getInt(1);
            
            String req1 = "SELECT COUNT(*) AS rowcount FROM  produit WHERE categorie_prod like '%chaussures%' ";
            
            ResultSet rs1 = st.executeQuery(req1);
            rs1.next();
            int nb1 = rs1.getInt(1);
            
            String req2 = "SELECT COUNT(*) AS rowcount FROM  produit WHERE categorie_prod like '%Accessoires%' ";
            
            ResultSet rs2 = st.executeQuery(req2);
            rs2.next();
            int nb2 = rs2.getInt(1);
            
            
            String req3 = "SELECT COUNT(*) AS rowcount FROM  produit WHERE categorie_prod like '%Materiel sportif%' ";
            
            ResultSet rs3 = st.executeQuery(req3);
            rs3.next();
            int nb3 = rs3.getInt(1);
            
            String req4 = "SELECT COUNT(*) AS rowcount FROM  produit WHERE categorie_prod like '%Produits nutritifs%' ";
            
            ResultSet rs4 = st.executeQuery(req2);
            rs4.next();
            int nb4 = rs4.getInt(1);

            
            ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                    new PieChart.Data( "Vetements",nb ),
                    new PieChart.Data( "chaussures",15),
                    new PieChart.Data( "Accessoires",10),
                    new PieChart.Data( "Materiel sportif",8),
                    new PieChart.Data( "Produits nutritifs",50)
            );
            piechart.setData(pieChartData);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    
 
    }
    
}
