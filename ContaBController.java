package it.polito.tdp.teatrino;

import java.net.URL;
import java.time.Month;
import java.util.ResourceBundle;
import java.util.Set;

import com.sun.javafx.charts.Legend;

import it.polito.tdp.teatrino.db.TeatrinoDAO;
import it.polito.tdp.teatrino.model.Corso;
import it.polito.tdp.teatrino.model.Dipendente;
import it.polito.tdp.teatrino.model.Model;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ContaBController {

	private Model model;
	private Stage stage;
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnDipendenti;

    @FXML
    private Button btnCorsi;

    @FXML
    private Button btnClienti;

    @FXML
    private Button btnPianifica;

    @FXML
    private Button btnEventi;

    @FXML
    private Button btnScatter;
    
    @FXML
    private BarChart<String, Double> lineChart;
	private TeatrinoDAO dao;
	
	String[] palette = {"red", "blue", "green", "orange", 
			  "yellow", "black", "grey", "purple", "pink",
			  "maroon", "#809900", "#E6B3B3", "#6680B3", "#66991A", 
			  "#FF99E6", "#CCFF1A", "#FF1A66", "#E6331A", "#33FFCC",
			  "#66994D", "#66664D","#E666B3"};

    @FXML
    void openClienti(ActionEvent event) {

    	try {
			
    	System.out.println("Apri clienti");
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Clienti.fxml"));
		BorderPane root = (BorderPane) loader.load();
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
		ClientiController controller = loader.getController();
			controller.setModel(model, stage);
		
			stage.setScene(scene);
		//stage.setAlwaysOnTop(true);
		stage.show();
    	
    	} catch (Exception e) {
			// TODO: handle exception
    		e.printStackTrace();
		}
    }

    @FXML
    void openCorsi(ActionEvent event) {

    	try {
			
	    	System.out.println("Apri clienti");
	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Corsi.fxml"));
			BorderPane root = (BorderPane) loader.load();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			CorsiController controller = loader.getController();
 			controller.setModel(model, stage);
			
 			stage.setScene(scene);
			//stage.setAlwaysOnTop(true);
			stage.show();
	    	
	    	} catch (Exception e) {
				// TODO: handle exception
	    		e.printStackTrace();
			}
    	
    }

    @FXML
    void openDipendenti(ActionEvent event) {
    	
    	try {
			
	    	System.out.println("Apri clienti");
	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Dipendenti.fxml"));
			BorderPane root = (BorderPane) loader.load();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			DipendentiController controller = loader.getController();
 			controller.setModel(model, stage);
			
 			stage.setScene(scene);
			//stage.setAlwaysOnTop(true);
			stage.show();
	    	
	    	} catch (Exception e) {
				// TODO: handle exception
	    		e.printStackTrace();
			}

    }

    @FXML
    void openEventi(ActionEvent event) {

    	try {
			
	    	System.out.println("Apri clienti");
	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Eventi.fxml"));
			BorderPane root = (BorderPane) loader.load();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			EventiController controller = loader.getController();
 			controller.setModel(model, stage);
			
 			stage.setScene(scene);
			//stage.setAlwaysOnTop(true);
			stage.show();
	    	
	    	} catch (Exception e) {
				// TODO: handle exception
	    		e.printStackTrace();
			}
    	
    }

    @FXML
    void openPianifica(ActionEvent event) {

    	try {
    	System.out.println("Apri clienti");
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Ottimizzazione.fxml"));
		BorderPane root = (BorderPane) loader.load();
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
		OttimizzaController controller = loader.getController();
			controller.setModel(model, stage);
		
			stage.setScene(scene);
		//stage.setAlwaysOnTop(true);
		stage.show();
    	
    	} catch (Exception e) {
			// TODO: handle exception
    		e.printStackTrace();
		}
    }

    @FXML
    void openScatter(ActionEvent event) {

    	try {
        	System.out.println("Apri clienti");
        	FXMLLoader loader = new FXMLLoader(getClass().getResource("ContaS.fxml"));
    		BorderPane root = (BorderPane) loader.load();
    		Scene scene = new Scene(root);
    		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
    		
    		ContaSController controller = loader.getController();
    		controller.setModel(model, stage);
    		
    		stage.setScene(scene);
    		//stage.setAlwaysOnTop(true);
    		stage.show();
        	
        	} catch (Exception e) {
    			// TODO: handle exception
        		e.printStackTrace();
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
    
    public void setModel(Model model, Stage s) {
    	
    	this.model = model;
    	this.stage = s;
    	dao = new TeatrinoDAO();
    	
		XYChart.Series<String, Double> statistiche = new XYChart.Series<String, Double>();
		
    	for(Corso month : model.getCorsiMap().values()) {
    		statistiche.getData().add(createData(month.toString(), dao.calcolaRicavi(month)));
    		System.out.println(month+", "+dao.calcolaRicavi(month));
    	}
    	
    	lineChart.getData().add(statistiche);
    	lineChart.getXAxis().setTickLabelsVisible(false);
    	lineChart.getYAxis().setAutoRanging(false);
    	
    	int i= 0;
    	
    	for(Corso dipendente : model.getCorsiMap().values()) {
    		String string = ".data"+i+".chart-bar";
    		lineChart.lookup(string).setStyle("-fx-bar-fill:"+ palette[i] +";");
    		i++;
    	}
    	
        Legend legend = (Legend)lineChart.lookup(".chart-legend");
        
        Legend.LegendItem li1= new Legend.LegendItem("Piccolissimi", new Rectangle(8,4,Color.RED));
        Legend.LegendItem li2=new Legend.LegendItem("Favolosi", new Rectangle(8,4,Color.BLUE));
        Legend.LegendItem li3=new Legend.LegendItem("Orlando", new Rectangle(8,4,Color.GREEN));
        Legend.LegendItem li4=new Legend.LegendItem("Adulti II", new Rectangle(8,4,Color.ORANGE));
        Legend.LegendItem li5=new Legend.LegendItem("Medie", new Rectangle(8,4,Color.YELLOW));
        Legend.LegendItem li6=new Legend.LegendItem("Superiori", new Rectangle(8,4,Color.BLACK));
        Legend.LegendItem li7=new Legend.LegendItem("Adulti I", new Rectangle(8,4,Color.GREY));
        Legend.LegendItem li8=new Legend.LegendItem("Universitari", new Rectangle(8,4,Color.PURPLE));
        Legend.LegendItem li9=new Legend.LegendItem("Puck", new Rectangle(8,4,Color.PINK));
        Legend.LegendItem li10=new Legend.LegendItem("Giuliette&Romei", new Rectangle(8,4,Color.MAROON));
        legend.getItems().setAll(li1,li2,li3,li4,li5,li6,li7,li8,li9,li10);
        
    }

    @FXML
    void initialize() {
        assert btnDipendenti != null : "fx:id=\"btnDipendenti\" was not injected: check your FXML file 'ContaB.fxml'.";
        assert btnCorsi != null : "fx:id=\"btnCorsi\" was not injected: check your FXML file 'ContaB.fxml'.";
        assert btnClienti != null : "fx:id=\"btnClienti\" was not injected: check your FXML file 'ContaB.fxml'.";
        assert btnPianifica != null : "fx:id=\"btnPianifica\" was not injected: check your FXML file 'ContaB.fxml'.";
        assert btnEventi != null : "fx:id=\"btnEventi\" was not injected: check your FXML file 'ContaB.fxml'.";
        assert btnScatter != null : "fx:id=\"btnScatter\" was not injected: check your FXML file 'ContaB.fxml'.";
        assert lineChart != null : "fx:id=\"lineChart\" was not injected: check your FXML file 'ContaB.fxml'.";


    }
}
