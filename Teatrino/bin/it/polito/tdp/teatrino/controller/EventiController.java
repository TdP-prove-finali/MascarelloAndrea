package it.polito.tdp.teatrino;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;

import it.polito.tdp.teatrino.db.TeatrinoDAO;
import it.polito.tdp.teatrino.model.ClienteHidden;
import it.polito.tdp.teatrino.model.Evento;
import it.polito.tdp.teatrino.model.Model;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

public class EventiController {

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
    private Button btnMoney;

    @FXML
    private Button btnPianifica;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TableView<Evento> tableEvents;
    
    @FXML
    private TableColumn<Evento, String> nome;

    @FXML
    private TableColumn<Evento, LocalDate> data;

    @FXML
    private TableColumn<Evento, Double> entrata;

	private Model model;

	private Stage stage;
	
	@FXML
	private Button btnAggiungi;

	@FXML
	private Button btnEntrate;

	private TeatrinoDAO dao;

	@FXML
	void aggiungi(ActionEvent event) {

		try {
			
	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("AggiungiEvento.fxml"));
			BorderPane root = (BorderPane) loader.load();
			Scene scene = new Scene(root);
			Stage s = new Stage();
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			AggiungiEventoController controller = loader.getController();
 			controller.setModel(model, stage);
			
 			s.setScene(scene);
			//stage.setAlwaysOnTop(true);
			s.show();
	    	
	    	} catch (Exception e) {
				// TODO: handle exception
	    		e.printStackTrace();
			}
		
	}
	
	@FXML
    void entrate(ActionEvent event) {

		try {
			
	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("LineChartEventi.fxml"));
			BorderPane root = (BorderPane) loader.load();
			Scene scene = new Scene(root);
			Stage s = new Stage();
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			LineChartEventiController controller = loader.getController();
 			controller.setModel(model, stage);
			
 			s.setScene(scene);
			//stage.setAlwaysOnTop(true);
			s.show();
	    	
	    	} catch (Exception e) {
				// TODO: handle exception
	    		e.printStackTrace();
			}
		
    }

    @FXML
    void doCercaEvento(ActionEvent event) {

    	model.getEventi();
    	if(datePicker != null) {
    	ObservableList<Evento> values = FXCollections.observableArrayList(model.getEventi(datePicker.getValue()));
    	tableEvents.setItems(values);
    	} else {
    		
    		try {
	   			
	   	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Output.fxml"));
	   			BorderPane root = (BorderPane) loader.load();
	   			Scene scene = new Scene(root);
	   			Stage s = new Stage();
	   			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	   			
	   			OutputController controller = loader.getController();
	    			controller.setModel(model, s, "Inserisci una data!");
	   			
	    		s.setScene(scene);
	   			//s.setAlwaysOnTop(true);
	   			s.show();
	   	    	
	   	    	} catch (Exception e) {
	   				// TODO: handle exception
	   	    		e.printStackTrace();
	   			}
	    	 
    		
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
    void openMoney(ActionEvent event) {

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
        assert btnDipendenti != null : "fx:id=\"btnDipendenti\" was not injected: check your FXML file 'Eventi.fxml'.";
        assert btnCorsi != null : "fx:id=\"btnCorsi\" was not injected: check your FXML file 'Eventi.fxml'.";
        assert btnClienti != null : "fx:id=\"btnClienti\" was not injected: check your FXML file 'Eventi.fxml'.";
        assert btnMoney != null : "fx:id=\"btnMoney\" was not injected: check your FXML file 'Eventi.fxml'.";
        assert btnPianifica != null : "fx:id=\"btnPianifica\" was not injected: check your FXML file 'Eventi.fxml'.";
        assert datePicker != null : "fx:id=\"datePicker\" was not injected: check your FXML file 'Eventi.fxml'.";
        assert tableEvents != null : "fx:id=\"tableEvents\" was not injected: check your FXML file 'Eventi.fxml'.";
        assert btnAggiungi != null : "fx:id=\"btnAggiungi\" was not injected: check your FXML file 'Eventi.fxml'.";
        assert btnEntrate != null : "fx:id=\"btnEntrate\" was not injected: check your FXML file 'Eventi.fxml'.";
        
        nome.setCellValueFactory(new PropertyValueFactory<Evento, String>("nome"));
        data.setCellValueFactory(new PropertyValueFactory<Evento, LocalDate>("data"));
        entrata.setCellValueFactory(new PropertyValueFactory<Evento, Double>("entrata"));
    }
	
	public void setModel(Model model, Stage stage) {
		// TODO Auto-generated method stub
		this.model= model;
		this.stage = stage;
		this.dao = new TeatrinoDAO();
		
	}

}
