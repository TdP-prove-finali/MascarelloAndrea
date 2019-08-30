package it.polito.tdp.teatrino;

import it.polito.tdp.teatrino.db.TeatrinoDAO;
import it.polito.tdp.teatrino.model.Dipendente;
import it.polito.tdp.teatrino.model.Model;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

import javax.sound.midi.ControllerEventListener;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class VisualizzaDipendentiController {

	    @FXML
	    private ResourceBundle resources;
	    
	    DipendentiController dip = new DipendentiController();

	    @FXML
	    private URL location;

	    @FXML
	    private Label labelNome;

	    @FXML
	    private Label labelCorso;

	    @FXML
	    private Label labelContatti;

	    @FXML
	    private Button btnElimina;

		private Model model;

		private Stage stage;

		private Dipendente cliente;

		private TeatrinoDAO dao;

	    @FXML
	    void elimina(ActionEvent event) {

	    	dao.eliminaDipendente(cliente);
	    	model.getDipendentiMap().remove(cliente.getId(), cliente);
	    	this.stage.close();
	    	
	    }

	    @FXML
	    void initialize() {
	        assert labelNome != null : "fx:id=\"labelNome\" was not injected: check your FXML file 'VisualizzaDipendenti.fxml'.";
	        assert labelCorso != null : "fx:id=\"labelCorso\" was not injected: check your FXML file 'VisualizzaDipendenti.fxml'.";
	        assert labelContatti != null : "fx:id=\"labelContatti\" was not injected: check your FXML file 'VisualizzaDipendenti.fxml'.";
	        assert btnElimina != null : "fx:id=\"btnElimina\" was not injected: check your FXML file 'VisualizzaDipendenti.fxml'.";

	    }

	
	public void setModel(Model model, Stage s, Dipendente c) {
		// TODO Auto-generated method stub
		this.model= model;
		this.stage = s;
		this.dao = new TeatrinoDAO();
		this.labelNome.setText(c.getNome());
		this.labelCorso.setText(""+c.getCompenso()+ " €/h");
		this.labelContatti.setText(c.getGiorno());
		this.cliente = c;
		
	}

}
