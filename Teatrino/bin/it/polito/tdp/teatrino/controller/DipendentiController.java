package it.polito.tdp.teatrino;


import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.teatrino.db.TeatrinoDAO;
import it.polito.tdp.teatrino.model.Cliente;
import it.polito.tdp.teatrino.model.ClienteHidden;
import it.polito.tdp.teatrino.model.Corso;
import it.polito.tdp.teatrino.model.Dipendente;
import it.polito.tdp.teatrino.model.Model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class DipendentiController {

	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnCorsi;

    @FXML
    private Button BtnClienti;

    @FXML
    private Button btnMoney;

    @FXML
    private Button btnPianifica;

    @FXML
    private Button btnEventi;

    @FXML
    private TextField txtCorsi;

    @FXML
    private TableView<Dipendente> tableDipendenti;
    
    @FXML
    private TableColumn<Dipendente, String> idCol;

    @FXML
    private TableColumn<Dipendente,String> nomeCol;

    @FXML
    private TableColumn<Dipendente, String> giornoCol;
    
	private Model model;

	private Stage stage;

	private TeatrinoDAO dao;
	
	@FXML
    private Button btnAggiungi;

    @FXML
    private Button btnVisualizza;

    @FXML
    void aggiungi(ActionEvent event) {

    	try {
 			
 	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("aggiungiDipendente.fxml"));
 			BorderPane root = (BorderPane) loader.load();
 			Scene scene = new Scene(root);
 			Stage s = new Stage();
 			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
 			
 			AggiungiDipendenteController controller = loader.getController();
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
    void visual(ActionEvent event) {

    	try {
			
	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("DipendentiUscite.fxml"));
			BorderPane root = (BorderPane) loader.load();
			Scene scene = new Scene(root);
			Stage s = new Stage();
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			DipendentiUsciteController controller = loader.getController();
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
    void visualizza(MouseEvent event) {

    	if(event.getClickCount() == 2){
            Dipendente cliente = tableDipendenti.getSelectionModel().getSelectedItem();
            System.out.println(cliente);
            if(cliente != null)
            	try {
        			
        	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("VisualizzaDipendenti.fxml"));
        			BorderPane root = (BorderPane) loader.load();
        			Scene scene = new Scene(root);
        			Stage s = new Stage();
        			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        			
        			VisualizzaDipendentiController controller = loader.getController();
         			controller.setModel(model, s , cliente);
        			
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
        assert btnCorsi != null : "fx:id=\"btnCorsi\" was not injected: check your FXML file 'Dipendenti.fxml'.";
        assert BtnClienti != null : "fx:id=\"BtnClienti\" was not injected: check your FXML file 'Dipendenti.fxml'.";
        assert btnMoney != null : "fx:id=\"btnMoney\" was not injected: check your FXML file 'Dipendenti.fxml'.";
        assert btnPianifica != null : "fx:id=\"btnPianifica\" was not injected: check your FXML file 'Dipendenti.fxml'.";
        assert btnEventi != null : "fx:id=\"btnEventi\" was not injected: check your FXML file 'Dipendenti.fxml'.";
        assert tableDipendenti != null : "fx:id=\"tableDipendenti\" was not injected: check your FXML file 'Dipendenti.fxml'.";
        assert idCol != null : "fx:id=\"idCol\" was not injected: check your FXML file 'Dipendenti.fxml'.";
        assert nomeCol != null : "fx:id=\"nomeCol\" was not injected: check your FXML file 'Dipendenti.fxml'.";
        assert giornoCol != null : "fx:id=\"giornoCol\" was not injected: check your FXML file 'Dipendenti.fxml'.";
        assert btnAggiungi != null : "fx:id=\"btnAggiungi\" was not injected: check your FXML file 'Dipendenti.fxml'.";
        assert btnVisualizza != null : "fx:id=\"btnVisualizza\" was not injected: check your FXML file 'Dipendenti.fxml'.";
        assert txtCorsi != null : "fx:id=\"txtCorsi\" was not injected: check your FXML file 'Dipendenti.fxml'.";
        
        idCol.setCellValueFactory(new PropertyValueFactory<Dipendente, String>("id"));
        nomeCol.setCellValueFactory(new PropertyValueFactory<Dipendente, String>("nome"));
        giornoCol.setCellValueFactory(new PropertyValueFactory<Dipendente, String>("giorno"));

    }

	public void setModel(Model model, Stage stage) {
		// TODO Auto-generated method stub
		
		this.model= model;
		this.stage= stage;
		dao = new TeatrinoDAO();
		ObservableList<Dipendente> values = FXCollections.observableArrayList(dao.getDipendenti(model.getDipendentiMap()));
    	tableDipendenti.setItems(values);
		
	}

	public TableView<Dipendente> getTableDipendenti() {
		return tableDipendenti;
	}

	public void setTableDipendenti(TableView<Dipendente> tableDipendenti) {
		this.tableDipendenti = tableDipendenti;
	}
}

