package it.polito.tdp.teatrino;

import java.net.URL;
import java.util.ResourceBundle;

import javax.management.modelmbean.ModelMBean;

import it.polito.tdp.teatrino.db.TeatrinoDAO;
import it.polito.tdp.teatrino.model.Cliente;
import it.polito.tdp.teatrino.model.ClienteHidden;
import it.polito.tdp.teatrino.model.Corso;
import it.polito.tdp.teatrino.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MenuController {
	
		private Model model;
		private Stage stage;

	 	@FXML
	    private ResourceBundle resources;

	    @FXML
	    private URL location;

	    @FXML
	    private Button btnClienti;

	    @FXML
	    private Button btnMoney;

	    @FXML
	    private Button btnPianifica;

	    @FXML
	    private Button btnEventi;

	    @FXML
	    private Button btnCorsi;

	    @FXML
	    private Button btnDipendenti;
	    
		private TeatrinoDAO dao;

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
	        assert btnClienti != null : "fx:id=\"btnClienti\" was not injected: check your FXML file 'Teatrino menu.fxml'.";
	        assert btnMoney != null : "fx:id=\"btnMoney\" was not injected: check your FXML file 'Teatrino menu.fxml'.";
	        assert btnPianifica != null : "fx:id=\"btnPianifica\" was not injected: check your FXML file 'Teatrino menu.fxml'.";
	        assert btnEventi != null : "fx:id=\"btnEventi\" was not injected: check your FXML file 'Teatrino menu.fxml'.";
	        assert btnCorsi != null : "fx:id=\"btnCorsi\" was not injected: check your FXML file 'Teatrino menu.fxml'.";
	        assert btnDipendenti != null : "fx:id=\"btnDipendenti\" was not injected: check your FXML file 'Teatrino menu.fxml'.";

	    }

		public void setModel(Model model2, Stage primaryStage) {
			// TODO Auto-generated method stub
			this.model = model2;
			this.stage = primaryStage;
			this.dao = new TeatrinoDAO();
			
			for(Corso corso : dao.getCorsi(model2.getCorsiMap())) {
				
				model.getCorsiMap().put(corso.getId(), corso);
				System.out.println(model.getCorsiMap().toString());
			}
			
			for(Cliente cliente : dao.getClienti()) {
				
				model.getClientiMap().put(cliente.getId(), cliente);
				model.clientiHidden.add(new ClienteHidden(cliente.getNome(), model.getCorsiMap().get(cliente.getIdCorso()).getNome(), cliente.getContatti()));
				//System.out.println(model.getClientiMap().toString());
				//System.out.println(model.clientiHidden.toString());
			}
			
		}
	
}
