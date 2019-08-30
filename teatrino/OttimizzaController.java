package it.polito.tdp.teatrino;

import it.polito.tdp.teatrino.model.Corso;
import it.polito.tdp.teatrino.model.Model;
import javafx.stage.Stage;

import java.net.URL;
import java.time.Duration;
import java.time.LocalTime;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class OttimizzaController {

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
	    private Button btnMoney;

	    @FXML
	    private Button btnEventi;

	    @FXML
	    private TextField txtInizioL;

	    @FXML
	    private TextField txtFineL;

	    @FXML
	    private TextField txtInizioMa;

	    @FXML
	    private TextField txtFineMa;

	    @FXML
	    private TextField txtInizioMe;

	    @FXML
	    private TextField txtFineMe;

	    @FXML
	    private TextField txtInizioG;

	    @FXML
	    private TextField txtFineG;

	    @FXML
	    private TextField txtInizioV;

	    @FXML
	    private TextField txtFineV;

	    @FXML
	    private TextField txtInizioS;

	    @FXML
	    private TextField txtFineS;

	    @FXML
	    private TextField txtInizioD;

	    @FXML
	    private TextField txtFineD;
	    
	    @FXML
	    private Button btnCalcola;
	    
	    
	    @FXML
	    void ricorsivo(ActionEvent event) {

	    	String output = "";
	    	
	    	try {
	    		LocalTime lunediI = LocalTime.parse(txtInizioL.getText());
		    	LocalTime lunediF = LocalTime.parse(txtFineL.getText());
		    	LocalTime martediI = LocalTime.parse(txtInizioMa.getText());
		    	LocalTime martediF = LocalTime.parse(txtFineMa.getText());
		    	LocalTime mercolediI = LocalTime.parse(txtInizioMe.getText());
		    	LocalTime mercolediF = LocalTime.parse(txtFineMe.getText());
		    	LocalTime giovediI = LocalTime.parse(txtInizioG.getText());
		    	LocalTime giovediF = LocalTime.parse(txtFineG.getText());
		    	LocalTime venerdiI = LocalTime.parse(txtInizioV.getText());
		    	LocalTime venerdiF = LocalTime.parse(txtFineV.getText());
		    	LocalTime sabatoI = LocalTime.parse(txtInizioS.getText());
		    	LocalTime sabatoF = LocalTime.parse(txtFineS.getText());
		    	LocalTime domenicaI = LocalTime.parse(txtInizioD.getText());
		    	LocalTime domenicaF = LocalTime.parse(txtFineD.getText());
		    	
		    	if(lunediI.compareTo(LocalTime.of(7, 00)) >= 0 && (martediI.compareTo(LocalTime.of(7, 00)) >= 0) && mercolediI.compareTo(LocalTime.of(7, 00)) >= 0 &&
		    			mercolediI.compareTo(LocalTime.of(7, 00)) >= 0 && giovediI.compareTo(LocalTime.of(7, 00)) >= 0 &&
		    			venerdiI.compareTo(LocalTime.of(7, 00)) >= 0 && sabatoI.compareTo(LocalTime.of(7, 00)) >= 0 && domenicaI.compareTo(LocalTime.of(7, 00)) >= 0) {
		
		    	
		    	Duration orariTotali = Duration.between(lunediI, lunediF).plus(Duration.between(martediI, martediF)).plus(Duration.between(mercolediI, mercolediF)).plus(
		    	Duration.between(giovediI, giovediF)).plus(Duration.between(venerdiI, venerdiF)).plus(Duration.between(sabatoI, sabatoF)).plus(Duration.between(domenicaI,domenicaF));
	    		
		    	Duration orariNecessari = Duration.between(LocalTime.of(17, 00), LocalTime.of(17, 00));
		    	
		    	for(Corso corso : model.getCorsiMap().values()) {
		    		
		    		System.out.println(Duration.between(corso.getOraInizio(), corso.getOraFine()).toMinutes());
		    		orariNecessari = orariNecessari.plusMinutes(Duration.between(corso.getOraInizio(), corso.getOraFine()).toMinutes());
		    		
		    	}
		    	System.out.println(orariTotali.toMinutes());
		    	System.out.println(orariNecessari.toMinutes());
		    	
		    	if(orariTotali.compareTo(orariNecessari) >= 0) {
		    		
		    	if(lunediI.compareTo(lunediF) <= 0 && martediI.compareTo(martediF) <= 0 && mercolediI.compareTo(mercolediF) <= 0 && giovediI.compareTo(giovediF) <= 0
		    			&& venerdiI.compareTo(venerdiF) <= 0 && sabatoI.compareTo(sabatoF) <= 0 && domenicaI.compareTo(domenicaF) <= 0) {
		    		
		    		List<Corso> corsi = model.trovaSequenza(lunediI,lunediF,martediI,martediF,mercolediI,mercolediF,giovediI,giovediF,venerdiI,venerdiF,
	    				sabatoI,sabatoF,domenicaI,domenicaF);
	    		
		    		for(Corso corso : corsi) {
	    			
		    			System.out.println(corso.toStringRicorsiva());
		    			
		    		}
		    		
		    		System.out.println("Con profitto : "+model.getProfitto(corsi));
		    		
		    		if(corsi.size() > 0) {
		    		
		    		try {
		      			
		      	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("VisualizzaSequenza.fxml"));
		      			BorderPane root = (BorderPane) loader.load();
		      			Scene scene = new Scene(root);
		      			Stage s = new Stage();
		      			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		      			
		      			VisualizzaSequenzaController controller = loader.getController();
		       			controller.setModel(model, s, corsi);
		      			
		       			s.setScene(scene);
		      			//s.setAlwaysOnTop(true);
		      			s.show();
		      	    	
		      	    	} catch (Exception e) {
		      				// TODO: handle exception
		      	    		e.printStackTrace();
		      			}
		    		
		    		} else {
		    			
		    			output = "Nessuna sequenza disponibile con questi orari!";
			    		
			    		try {
			      			
			      	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Output.fxml"));
			      			BorderPane root = (BorderPane) loader.load();
			      			Scene scene = new Scene(root);
			      			Stage s = new Stage();
			      			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			      			
			      			OutputController controller = loader.getController();
			       			controller.setModel(model, s, output);
			      			
			       			s.setScene(scene);
			      			//s.setAlwaysOnTop(true);
			      			s.show();
			      	    	
			      	    	} catch (Exception e) {
			      				// TODO: handle exception
			      	    		e.printStackTrace();
			      			}
		    		}
		    		
		    		
		    	}else {
		    		
		    		output = "Fine disponibilità non può essere prima dell'inizio!";
		    		
		    		try {
		      			
		      	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Output.fxml"));
		      			BorderPane root = (BorderPane) loader.load();
		      			Scene scene = new Scene(root);
		      			Stage s = new Stage();
		      			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		      			
		      			OutputController controller = loader.getController();
		       			controller.setModel(model, s, output);
		      			
		       			s.setScene(scene);
		      			//s.setAlwaysOnTop(true);
		      			s.show();
		      	    	
		      	    	} catch (Exception e) {
		      				// TODO: handle exception
		      	    		e.printStackTrace();
		      			}
		    		
		    	}
		    	} else {
		    		
		    		output = "Non ci sono abbastanza ore disponibili!";
		    		
		    		try {
		      			
		      	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Output.fxml"));
		      			BorderPane root = (BorderPane) loader.load();
		      			Scene scene = new Scene(root);
		      			Stage s = new Stage();
		      			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		      			
		      			OutputController controller = loader.getController();
		       			controller.setModel(model, s, output);
		      			
		       			s.setScene(scene);
		      			//s.setAlwaysOnTop(true);
		      			s.show();
		      	    	
		      	    	} catch (Exception e) {
		      				// TODO: handle exception
		      	    		e.printStackTrace();
		      			}
		    		
		    	}
		    	} else {
		    		
		    		output = "La disponibilità può partire dalle 7:00 AM";
		    		
		    		try {
		      			
		      	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Output.fxml"));
		      			BorderPane root = (BorderPane) loader.load();
		      			Scene scene = new Scene(root);
		      			Stage s = new Stage();
		      			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		      			
		      			OutputController controller = loader.getController();
		       			controller.setModel(model, s, output);
		      			
		       			s.setScene(scene);
		      			//s.setAlwaysOnTop(true);
		      			s.show();
		      	    	
		      	    	} catch (Exception e) {
		      				// TODO: handle exception
		      	    		e.printStackTrace();
		      			}
		    		
		    	}
	    	
	    	} catch (Exception e) {
				// TODO: handle exception
	    		e.printStackTrace();
	    		output = "Ricontrolla gli input!";
	    		try {
	      			
	      	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Output.fxml"));
	      			BorderPane root = (BorderPane) loader.load();
	      			Scene scene = new Scene(root);
	      			Stage s = new Stage();
	      			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	      			
	      			OutputController controller = loader.getController();
	       			controller.setModel(model, s, output);
	      			
	       			s.setScene(scene);
	      			//s.setAlwaysOnTop(true);
	      			s.show();
	      	    	
	      	    	} catch (Exception e2) {
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
	    void initialize() {
	        assert btnDipendenti != null : "fx:id=\"btnDipendenti\" was not injected: check your FXML file 'Ottimizzazione.fxml'.";
	        assert btnCorsi != null : "fx:id=\"btnCorsi\" was not injected: check your FXML file 'Ottimizzazione.fxml'.";
	        assert btnClienti != null : "fx:id=\"btnClienti\" was not injected: check your FXML file 'Ottimizzazione.fxml'.";
	        assert btnMoney != null : "fx:id=\"btnMoney\" was not injected: check your FXML file 'Ottimizzazione.fxml'.";
	        assert btnEventi != null : "fx:id=\"btnEventi\" was not injected: check your FXML file 'Ottimizzazione.fxml'.";
	        assert txtInizioL != null : "fx:id=\"txtInizioL\" was not injected: check your FXML file 'Ottimizzazione.fxml'.";
	        assert txtFineL != null : "fx:id=\"txtFineL\" was not injected: check your FXML file 'Ottimizzazione.fxml'.";
	        assert txtInizioMa != null : "fx:id=\"txtInizioMa\" was not injected: check your FXML file 'Ottimizzazione.fxml'.";
	        assert txtFineMa != null : "fx:id=\"txtFineMa\" was not injected: check your FXML file 'Ottimizzazione.fxml'.";
	        assert txtInizioMe != null : "fx:id=\"txtInizioMe\" was not injected: check your FXML file 'Ottimizzazione.fxml'.";
	        assert txtFineMe != null : "fx:id=\"txtFineMe\" was not injected: check your FXML file 'Ottimizzazione.fxml'.";
	        assert txtInizioG != null : "fx:id=\"txtInizioG\" was not injected: check your FXML file 'Ottimizzazione.fxml'.";
	        assert txtFineG != null : "fx:id=\"txtFineG\" was not injected: check your FXML file 'Ottimizzazione.fxml'.";
	        assert txtInizioV != null : "fx:id=\"txtInizioV\" was not injected: check your FXML file 'Ottimizzazione.fxml'.";
	        assert txtFineV != null : "fx:id=\"txtFineV\" was not injected: check your FXML file 'Ottimizzazione.fxml'.";
	        assert txtInizioS != null : "fx:id=\"txtInizioS\" was not injected: check your FXML file 'Ottimizzazione.fxml'.";
	        assert txtFineS != null : "fx:id=\"txtFineS\" was not injected: check your FXML file 'Ottimizzazione.fxml'.";
	        assert txtInizioD != null : "fx:id=\"txtInizioD\" was not injected: check your FXML file 'Ottimizzazione.fxml'.";
	        assert txtFineD != null : "fx:id=\"txtFineD\" was not injected: check your FXML file 'Ottimizzazione.fxml'.";
	        assert btnCalcola != null : "fx:id=\"btnCalcola\" was not injected: check your FXML file 'Ottimizzazione.fxml'.";
	        
	    }

	public void setModel(Model model, Stage stage) {
		// TODO Auto-generated method stub
		this.model= model;
		this.stage = stage;
	}

}
