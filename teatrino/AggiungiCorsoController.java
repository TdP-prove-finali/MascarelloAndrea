package it.polito.tdp.teatrino;

import java.awt.event.InputEvent;
import java.net.URL;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.ResourceBundle;

import it.polito.tdp.teatrino.db.TeatrinoDAO;
import it.polito.tdp.teatrino.model.Corso;
import it.polito.tdp.teatrino.model.Dipendente;
import it.polito.tdp.teatrino.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.converter.LocalTimeStringConverter;

public class AggiungiCorsoController {

	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtCosto;

    @FXML
    private ComboBox<Dipendente> boxDip1;

    @FXML
    private ComboBox<Dipendente> boxDip2;

    @FXML
    private ComboBox<String> boxGiorno;

    @FXML
    private TextField txtInizio;

    @FXML
    private TextField txtFine;

    @FXML
    private ComboBox<String> boxTipo;

    @FXML
    private Button btnAdd;

	private Model model;

	private Stage stage;

	private TeatrinoDAO dao;

    @FXML
    void addCorso(ActionEvent event) {

    	String output;
    	
    	try {
        	if(!txtNome.getText().equals("") && !txtCosto.getText().equals("") && this.boxDip1 != null && this.boxGiorno != null
        			&& this.boxTipo != null && !this.txtInizio.equals("00:00") && !this.txtFine.equals("00:00") && !boxDip1.getValue().equals(boxDip2.getValue())) {
        		
        		Corso corso = new Corso("c"+(model.getCorsiMap().size()+1), txtNome.getText(), Integer.parseInt(txtCosto.getText()), boxDip1.getValue().getId(), boxDip2.getValue().getId(), 
        				boxGiorno.getValue(), LocalTime.parse(txtInizio.getText()), LocalTime.parse(txtFine.getText()), boxTipo.getValue());
        		dao.aggiungiCorso(corso);
        		model.getCorsiMap().put(corso.getId(), corso);
        		output = "Corso aggiunto correttamente!";
        		
        	} else {
    			output = "Errore, ricontrolla il tuo input";
    		}
    	} catch (Exception e) {
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
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'AggiungiCorso.fxml'.";
        assert txtCosto != null : "fx:id=\"txtCosto\" was not injected: check your FXML file 'AggiungiCorso.fxml'.";
        assert boxDip1 != null : "fx:id=\"boxDip1\" was not injected: check your FXML file 'AggiungiCorso.fxml'.";
        assert boxDip2 != null : "fx:id=\"boxDip2\" was not injected: check your FXML file 'AggiungiCorso.fxml'.";
        assert boxGiorno != null : "fx:id=\"boxGiorno\" was not injected: check your FXML file 'AggiungiCorso.fxml'.";
        assert txtInizio != null : "fx:id=\"txtInizio\" was not injected: check your FXML file 'AggiungiCorso.fxml'.";
        assert txtFine != null : "fx:id=\"txtFine\" was not injected: check your FXML file 'AggiungiCorso.fxml'.";
        assert boxTipo != null : "fx:id=\"boxTipo\" was not injected: check your FXML file 'AggiungiCorso.fxml'.";
        assert btnAdd != null : "fx:id=\"btnAdd\" was not injected: check your FXML file 'AggiungiCorso.fxml'.";

    }

	public void setModel(Model model, Stage s) {
		// TODO Auto-generated method stub
		this.model= model;
		this.stage = s;
		
		dao = new TeatrinoDAO();
		
		for(Dipendente dipendente : dao.getDipendenti()) {
			boxDip1.getItems().add(dipendente);
		}
		
		for(Dipendente dipendente : dao.getDipendenti()) {
			boxDip2.getItems().add(dipendente);
		}
		
		ArrayList<String> settimana = new ArrayList<String>();
		settimana.add("Lunedì");
		settimana.add("Martedì");
		settimana.add("Mercoledì");
		settimana.add("Giovedì");
		settimana.add("Venerdì");
		settimana.add("Sabato");
		settimana.add("Domenica");
		
		this.boxGiorno.getItems().addAll(settimana);
		
		this.boxTipo.getItems().add("LEZ");
		this.boxTipo.getItems().add("TRIM");
		}
	
}
