package it.polito.tdp.teatrino;

import it.polito.tdp.teatrino.db.TeatrinoDAO;
import it.polito.tdp.teatrino.model.Cliente;
import it.polito.tdp.teatrino.model.ClienteHidden;
import it.polito.tdp.teatrino.model.Corso;
import it.polito.tdp.teatrino.model.Dipendente;
import it.polito.tdp.teatrino.model.Model;
import javafx.stage.Stage;
import java.net.URL;
import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

public class CorsiController {

	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnDipendenti;

    @FXML
    private Button btnClienti;

    @FXML
    private Button btnMoney;

    @FXML
    private Button btnPianifica;

    @FXML
    private Button btnEventi;

    @FXML
    private TableView<Corso> tableCorsi;

    @FXML
    private TableColumn<Corso, String> colNome;

    @FXML
    private TableColumn<Corso, Integer> colCosto;

    @FXML
    private TableColumn<Corso, String> colDip1;

    @FXML
    private TableColumn<Corso, String> colDip2;
    
    @FXML
    private TableColumn<Corso, String> colGiorno;
  
    @FXML
    private TableColumn<Corso, String> colTipo;

	private Model model;

	private Stage stage;

	private TeatrinoDAO dao;
	
	@FXML
	private Button btnLezioni;

	@FXML
	private Button btnCorso;

	@FXML
	void aggiungiCorso(ActionEvent event) {
		
		try {
			
	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("AggiungiCorso.fxml"));
			BorderPane root = (BorderPane) loader.load();
			Scene scene = new Scene(root);
			Stage s = new Stage();
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			AggiungiCorsoController controller = loader.getController();
 			controller.setModel(model, s );
			
 			s.setScene(scene);
			//s.setAlwaysOnTop(true);
			s.show();
	    	
	    	} catch (Exception e) {
				// TODO: handle exception
	    		e.printStackTrace();
			}

	}
	
	@FXML
    void visualizza(MouseEvent event) {
		
		if(event.getClickCount() == 2){
            Corso corso = tableCorsi.getSelectionModel().getSelectedItem();
            System.out.println(corso);
            if(corso != null)
            	try {
        			
        	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("VisualizzaCorsi.fxml"));
        			BorderPane root = (BorderPane) loader.load();
        			Scene scene = new Scene(root);
        			Stage s = new Stage();
        			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        			
        			VisualizzaCorsiController controller = loader.getController();
         			controller.setModel(model, s , corso);
        			
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
	void aggiungiLezione(ActionEvent event) {

		try {
			
	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("AggiungiLezione.fxml"));
			BorderPane root = (BorderPane) loader.load();
			Scene scene = new Scene(root);
			Stage s = new Stage();
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			AggiungiLezioneController controller = loader.getController();
 			controller.setModel(model, s);
			
 			s.setScene(scene);
			//s.setAlwaysOnTop(true);
			s.show();
	    	
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
        assert btnDipendenti != null : "fx:id=\"btnDipendenti\" was not injected: check your FXML file 'Corsi.fxml'.";
        assert btnClienti != null : "fx:id=\"btnClienti\" was not injected: check your FXML file 'Corsi.fxml'.";
        assert btnMoney != null : "fx:id=\"btnMoney\" was not injected: check your FXML file 'Corsi.fxml'.";
        assert btnPianifica != null : "fx:id=\"btnPianifica\" was not injected: check your FXML file 'Corsi.fxml'.";
        assert btnEventi != null : "fx:id=\"btnEventi\" was not injected: check your FXML file 'Corsi.fxml'.";
        assert tableCorsi != null : "fx:id=\"tableCorsi\" was not injected: check your FXML file 'Corsi.fxml'.";
        assert colNome != null : "fx:id=\"colNome\" was not injected: check your FXML file 'Corsi.fxml'.";
        assert colCosto != null : "fx:id=\"colCosto\" was not injected: check your FXML file 'Corsi.fxml'.";
        assert colGiorno != null : "fx:id=\"colGiorno\" was not injected: check your FXML file 'Corsi.fxml'.";
        assert colTipo != null : "fx:id=\"colTipo\" was not injected: check your FXML file 'Corsi.fxml'.";
        
        colNome.setCellValueFactory(new PropertyValueFactory<Corso, String>("nome"));
        colCosto.setCellValueFactory(new PropertyValueFactory<Corso, Integer>("costo"));
        this.colDip1.setCellValueFactory(new PropertyValueFactory<Corso, String>("dip1"));
        this.colDip2.setCellValueFactory(new PropertyValueFactory<Corso, String>("dip2"));
        colGiorno.setCellValueFactory(new PropertyValueFactory<Corso, String>("giorno"));
        colTipo.setCellValueFactory(new PropertyValueFactory<Corso, String>("tipo"));

    }
	
	public void setModel(Model model, Stage stage) {
		// TODO Auto-generated method stub
		this.model=model;
		this.stage = stage;
		dao = new TeatrinoDAO();
		
		ObservableList<Corso> values = FXCollections.observableArrayList(model.getCorsiMap().values());
    	tableCorsi.setItems(values);
	}

}
