package it.polito.tdp.teatrino;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import it.polito.tdp.teatrino.db.TeatrinoDAO;
import it.polito.tdp.teatrino.model.Cliente;
import it.polito.tdp.teatrino.model.Dipendente;
import it.polito.tdp.teatrino.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class AggiungiDipendenteController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtCompenso;

    @FXML
    private ComboBox<String> boxGiorni;

    @FXML
    private Button btnAggiungi;

	private Model model;

	private Stage stage;

	private TeatrinoDAO dao;

    @FXML
    void aggiungi(ActionEvent event) {

    String output;
    	
	try {
    	if(!txtNome.getText().equals("") && !txtCompenso.getText().equals("") && boxGiorni != null) {
    		
    		Dipendente cliente = new Dipendente("D"+(model.getDipendentiMap().size()+1), txtNome.getText(), Integer.parseInt(txtCompenso.getText()), boxGiorni.getValue());
    		model.getDipendentiMap().put("D"+(model.getDipendentiMap().size()+1), new Dipendente("D"+model.getDipendentiMap().size()+1, txtNome.getText(), Integer.parseInt(txtCompenso.getText()), boxGiorni.getValue()));
    		dao.aggiuntaDipendente(cliente);
    		output = "Dipendente aggiunto correttamente!";
    		
    	} else {
			output = "Errore, ricontrolla il tuo input";
		}
	} catch (NumberFormatException e) {
		// TODO: handle exception
		output = "Errore, ricontrolla il tuo input";
	}
    	
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

    @FXML
    void initialize() {
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'aggiungiDipendente.fxml'.";
        assert txtCompenso != null : "fx:id=\"txtCompenso\" was not injected: check your FXML file 'aggiungiDipendente.fxml'.";
        assert boxGiorni != null : "fx:id=\"boxGiorni\" was not injected: check your FXML file 'aggiungiDipendente.fxml'.";
        assert btnAggiungi != null : "fx:id=\"btnAggiungi\" was not injected: check your FXML file 'aggiungiDipendente.fxml'.";

    }

	public void setModel(Model model, Stage s) {
		// TODO Auto-generated method stub
		this.model= model;
		this.stage = s;
		dao = new TeatrinoDAO();
		ArrayList<String> settimana = new ArrayList<String>();
		settimana.add("Lunedì");
		settimana.add("Martedì");
		settimana.add("Mercoledì");
		settimana.add("Giovedì");
		settimana.add("Venerdì");
		settimana.add("Sabato");
		settimana.add("Domenica");
		
		this.boxGiorni.getItems().addAll(settimana);
	}
}

