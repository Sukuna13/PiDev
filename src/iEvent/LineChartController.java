/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iEvent;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import java.util.Date;
import entities.Event;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import java.text.DateFormatSymbols;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.time.LocalDate;
import java.util.Calendar;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;
import static javax.swing.UIManager.get;
import services.EventCrud;
/**
 * FXML Controller class
 *
 * @author oussa
 */
public class LineChartController implements Initializable {
    @FXML    
    private LineChart<String, Integer> Linechart;
    @FXML
    private CategoryAxis xAxis;

    private ObservableList<String> monthNames = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        String[] months = DateFormatSymbols.getInstance(Locale.ENGLISH).getMonths();
        // Convert it to a list and add it to our ObservableList of months.
        monthNames.addAll(Arrays.asList(months));
        EventCrud s = new EventCrud();
        List<Event> l= s.afficherEventCrud();
        // Assign the month names as categories for the horizontal axis.
        
        xAxis.setCategories(monthNames);
        setEventData(l);
    }
    
    public void setEventData(List<Event> events) {
       
    	// Count the number of people having their birthday in a specific month.
        int[] monthCounter = new int[12];
        for (Event p : events) {
           Calendar calendar = Calendar.getInstance();
           calendar.setTime(p.getDateDebut());
          
           int month = p.getDateDebut().getMonth();
            System.out.println(month);
           monthCounter[month]++;
        }

        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        
        // Create a XYChart.Data object for each month. Add it to the series.
        for (int i = 0; i < monthCounter.length; i++) {
        	series.getData().add(new XYChart.Data<>(monthNames.get(i), monthCounter[i]));
        }
        
         Linechart.getData().add(series);
        
    } 
}