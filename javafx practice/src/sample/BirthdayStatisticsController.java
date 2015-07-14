package sample;

/**
 * Created by Patrick on 7/13/2015.
 */

import java.text.DateFormatSymbols;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;

public class BirthdayStatisticsController
{
    @FXML private BarChart<String, Integer> barChart;                                   //variables are attached to UI
    @FXML private CategoryAxis xAxis;
    private ObservableList<String> monthNames = FXCollections.observableArrayList();

    @FXML private void initialize()                                                     //block provides month names
    {
        String[] months = DateFormatSymbols.getInstance(Locale.ENGLISH).getMonths();
        monthNames.addAll(Arrays.asList(months));

       // xAxis.setCategories(monthNames);                                              //line causes errrors?
    }

    public void setPersonData(List<Person> persons)
    {
        int[] monthCounter = new int[12];                               //creates an int array
        for(Person p : persons)                                         //iterates through the people
        {                                                               //adding one to the count of the
            int month = p.getBirthday().getMonthValue()-1;              //index that represents their birth
            monthCounter[month]++;                                      //month
        }

        XYChart.Series<String,Integer> series = new XYChart.Series<>(); //sets up the chart data

        for(int i = 0; i < monthCounter.length; i++)                    //block correlates data and labels
        {
            series.getData().add(new XYChart.Data<>(monthNames.get(i),monthCounter[i]));
        }

        barChart.getData().add(series);                                 //adds data to graph
    }
}