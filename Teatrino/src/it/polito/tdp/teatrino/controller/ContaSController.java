package it.polito.tdp.teatrino;

import it.polito.tdp.teatrino.db.TeatrinoDAO;
import it.polito.tdp.teatrino.model.Model;
import javafx.stage.Stage;

import java.net.URL;
import java.nio.channels.NonWritableChannelException;
import java.time.Month;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;

public class ContaSController {

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
    private Button btnBar;
    
    @FXML
    private LineChart<String, Double> scatterChart;
	private TeatrinoDAO dao;

    @FXML
    void openBar(ActionEvent event) {

    	try {
			
	    	System.out.println("Apri clienti");
	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("ContaB.fxml"));
			BorderPane root = (BorderPane) loader.load();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			ContaBController controller = loader.getController();
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
    void initialize() {
        assert btnDipendenti != null : "fx:id=\"btnDipendenti\" was not injected: check your FXML file 'ContaS.fxml'.";
        assert btnCorsi != null : "fx:id=\"btnCorsi\" was not injected: check your FXML file 'ContaS.fxml'.";
        assert btnClienti != null : "fx:id=\"btnClienti\" was not injected: check your FXML file 'ContaS.fxml'.";
        assert btnPianifica != null : "fx:id=\"btnPianifica\" was not injected: check your FXML file 'ContaS.fxml'.";
        assert btnEventi != null : "fx:id=\"btnEventi\" was not injected: check your FXML file 'ContaS.fxml'.";
        assert btnBar != null : "fx:id=\"btnBar\" was not injected: check your FXML file 'ContaS.fxml'.";
        assert scatterChart != null : "fx:id=\"scatterChart\" was not injected: check your FXML file 'ContaS.fxml'.";

    }
	
	public void setModel(Model model, Stage stage) {
		// TODO Auto-generated method stub
		this.model= model;
		this.stage = stage;
		dao = new TeatrinoDAO();
		
		XYChart.Series<String,Double> data = new XYChart.Series<String,Double>();
		
		for(Month month :  dao.getMonth()) {
			System.out.println(month);
			data.getData().add(new XYChart.Data<String,Double>( month.toString(), dao.getRicavi(month,model.getCorsiMap())));
		}

		scatterChart.getData().add(data);
		scatterChart.legendVisibleProperty().set(false);
		
		for (XYChart.Series<String, Double> s : scatterChart.getData()) {
            for (XYChart.Data<String, Double> d : s.getData()) {
                Tooltip.install(d.getNode(), new Tooltip(
                        d.getXValue().toString() + ", " +
                                "Ricavo : " + d.getYValue()+ " €"));

                d.getNode().setStyle("-fx-background-color: ORANGE;");
                //Adding class on hover
                d.getNode().setOnMouseEntered(event -> d.getNode().setStyle("-fx-background-color:blue;"));

                //Removing class on exit
                d.getNode().setOnMouseExited(event -> d.getNode().setStyle("-fx-background-color: ORANGE;"));
            }
        }
    }
	}

