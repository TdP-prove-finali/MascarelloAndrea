package it.polito.tdp.teatrino;

import java.util.concurrent.ArrayBlockingQueue;

import com.sun.javafx.charts.Legend;

import it.polito.tdp.teatrino.db.TeatrinoDAO;
import it.polito.tdp.teatrino.model.Corso;
import it.polito.tdp.teatrino.model.Dipendente;
import it.polito.tdp.teatrino.model.Model;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class DipendentiUsciteController {

    @FXML
    private BarChart<String, Double> barChart;
	private Model model;
	private Stage stage;
	
	String[] palette = {"#FFB399", "#FF33FF", "#FFFF99", "#00B3E6", 
			  "#E6B333", "#3366E6", "#999966", "#99FF99", "#B34D4D",
			  "#80B300", "#809900", "#E6B3B3", "#6680B3", "#66991A", 
			  "#FF99E6", "#CCFF1A", "#FF1A66", "#E6331A", "#33FFCC",
			  "#66994D", "#66664D","#E666B3"};

	public void setModel(Model model, Stage stage) {
		// TODO Auto-generated method stub
		this.model= model;
		this.stage = stage;
		TeatrinoDAO dao = new TeatrinoDAO();
		
		XYChart.Series<String, Double> statistiche = new XYChart.Series<String, Double>();
		
    	for(Dipendente d : dao.getDipendenti()) {
    		statistiche.getData().add(createData(d.getNome(), dao.calcolaPagamenti(d)));
    		System.out.println(d.getNome()+", "+dao.calcolaPagamenti(d));
    	}
    	
    	barChart.getData().add(statistiche);
    	barChart.setLegendVisible(false);
    	
    	int i= 0;
    	
    	for(Dipendente dipendente : dao.getDipendenti()) {
    		String string = ".data"+i+".chart-bar";
    		barChart.lookup(string).setStyle("-fx-bar-fill:"+ palette[i] +";");
    		i++;
    	}
	}
	
	private XYChart.Data<String, Double> createData(String string, double valore) {
    	XYChart.Data<String, Double> data = new XYChart.Data<String, Double>(string, valore);
    	
    	data.nodeProperty().addListener(new ChangeListener<Node>() {
            @Override public void changed(ObservableValue<? extends Node> ov, Node oldNode, final Node node) {
              if (node != null)
                displayLabelForData(data);
            }
        });
    	
    	return data;
    }
    
    private void displayLabelForData(XYChart.Data<String, Double> data) {
        final Node node = data.getNode();
        final Text dataText = new Text(data.getYValue() + " €");
        node.parentProperty().addListener(new ChangeListener<Parent>() {
          @Override public void changed(ObservableValue<? extends Parent> ov, Parent oldParent, Parent parent) {
            Group parentGroup = (Group) parent;
            parentGroup.getChildren().add(dataText);
          }
        });

        node.boundsInParentProperty().addListener(new ChangeListener<Bounds>() {
          @Override public void changed(ObservableValue<? extends Bounds> ov, Bounds oldBounds, Bounds bounds) {
            dataText.setLayoutX(Math.round(bounds.getMinX() + bounds.getWidth() / 2 - dataText.prefWidth(-1) / 2));
            dataText.setLayoutY(Math.round((bounds.getMinY()+5) - dataText.prefHeight(-1) * 0.5));
          }
        });
      }
    
    @FXML
    void initialize() {
        assert barChart != null : "fx:id=\"barChart\" was not injected: check your FXML file 'DipendentiUscite.fxml'.";

    }

}