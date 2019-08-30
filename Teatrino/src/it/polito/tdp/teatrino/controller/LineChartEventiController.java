package it.polito.tdp.teatrino;

import it.polito.tdp.teatrino.db.TeatrinoDAO;
import it.polito.tdp.teatrino.model.Model;
import javafx.stage.Stage;

import java.net.URL;
import java.time.Month;
import java.util.ResourceBundle;

import com.sun.javafx.charts.Legend;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Tooltip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class LineChartEventiController {

	private Model model;
	private Stage stage;
	private TeatrinoDAO dao;

	public void setModel(Model model, Stage stage) {
		// TODO Auto-generated method stub
		this.model= model;
		this.stage = stage;
		dao = new TeatrinoDAO();
		
		XYChart.Series<String,Double> data = new XYChart.Series<String,Double>();
		XYChart.Series<String,Double> data2 = new XYChart.Series<String,Double>();
		
		for(Month month :  dao.getMonthEventi(2019)) {
			System.out.println(month);
			data.getData().add(new XYChart.Data<String,Double>(month.toString(), dao.getRicaviEventi(2019,month,model.getEventi())));
		}
		
		for(Month month :  dao.getMonthEventi(2018)) {
			System.out.println(month);
			data2.getData().add(new XYChart.Data<String,Double>(month.toString(), dao.getRicaviEventi(2018,month,model.getEventi())));
		}

		LineChart.getData().add(data);
		LineChart.getData().add(data2);
		
		data.getNode().setStyle("-fx-stroke: red");
		data2.getNode().setStyle("-fx-stroke: blue");
		
		Legend legend = (Legend)LineChart.lookup(".chart-legend");
        
        Legend.LegendItem li1= new Legend.LegendItem("2019", new Rectangle(8,4,Color.RED));
        Legend.LegendItem li2=new Legend.LegendItem("2018", new Rectangle(8,4,Color.BLUE));

        legend.getItems().setAll(li1,li2);
		
		for (XYChart.Series<String, Double> s : LineChart.getData()) {
            for (XYChart.Data<String, Double> d : s.getData()) {
                Tooltip.install(d.getNode(), new Tooltip(
                        d.getXValue().toString() + ", " +
                                "Ricavo : " + d.getYValue()+ " €"));

                d.getNode().setStyle("-fx-background-color: black;");
                //Adding class on hover
                d.getNode().setOnMouseEntered(event -> d.getNode().setStyle("-fx-background-color:green;"));

                //Removing class on exit
                d.getNode().setOnMouseExited(event -> d.getNode().setStyle("-fx-background-color: black;"));
            }
        }
	}

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private LineChart<String, Double> LineChart;

	@FXML
	void initialize() {
	    assert LineChart != null : "fx:id=\"LineChart\" was not injected: check your FXML file 'LineChartEventi.fxml'.";

	}
}
