package it.polito.tdp.teatrino;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.teatrino.model.Corso;
import it.polito.tdp.teatrino.model.Model;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class VisualizzaSequenzaController {

	    @FXML
	    private ResourceBundle resources;

	    @FXML
	    private URL location;

	    @FXML
	    private Label corso1;

	    @FXML
	    private Label gg1;

	    @FXML
	    private Label ora1;

	    @FXML
	    private Label corso2;

	    @FXML
	    private Label gg2;

	    @FXML
	    private Label ora2;

	    @FXML
	    private Label corso3;

	    @FXML
	    private Label gg3;

	    @FXML
	    private Label ora3;

	    @FXML
	    private Label corso4;

	    @FXML
	    private Label gg4;

	    @FXML
	    private Label ora4;
	    
	    @FXML
	    private Label corso5;

	    @FXML
	    private Label gg5;

	    @FXML
	    private Label ora5;

	    @FXML
	    private Label corso6;

	    @FXML
	    private Label gg6;

	    @FXML
	    private Label ora6;

	    @FXML
	    private Label corso7;

	    @FXML
	    private Label gg7;

	    @FXML
	    private Label ora7;

	    @FXML
	    private Label corso8;

	    @FXML
	    private Label gg8;

	    @FXML
	    private Label ora8;

	    @FXML
	    private Label corso9;

	    @FXML
	    private Label gg9;

	    @FXML
	    private Label ora9;

	    @FXML
	    private Label corso10;

	    @FXML
	    private Label gg10;

	    @FXML
	    private Label ora10;

	    @FXML
	    private Label txtProfitto;

		private Model model;

		private Stage stage;

	    @FXML
	    void initialize() {
	        assert corso1 != null : "fx:id=\"corso1\" was not injected: check your FXML file 'VisualizzaSequenza.fxml'.";
	        assert gg1 != null : "fx:id=\"gg1\" was not injected: check your FXML file 'VisualizzaSequenza.fxml'.";
	        assert ora1 != null : "fx:id=\"ora1\" was not injected: check your FXML file 'VisualizzaSequenza.fxml'.";
	        assert corso2 != null : "fx:id=\"corso2\" was not injected: check your FXML file 'VisualizzaSequenza.fxml'.";
	        assert gg2 != null : "fx:id=\"gg2\" was not injected: check your FXML file 'VisualizzaSequenza.fxml'.";
	        assert ora2 != null : "fx:id=\"ora2\" was not injected: check your FXML file 'VisualizzaSequenza.fxml'.";
	        assert corso3 != null : "fx:id=\"corso3\" was not injected: check your FXML file 'VisualizzaSequenza.fxml'.";
	        assert gg3 != null : "fx:id=\"gg3\" was not injected: check your FXML file 'VisualizzaSequenza.fxml'.";
	        assert ora3 != null : "fx:id=\"ora3\" was not injected: check your FXML file 'VisualizzaSequenza.fxml'.";
	        assert corso4 != null : "fx:id=\"corso4\" was not injected: check your FXML file 'VisualizzaSequenza.fxml'.";
	        assert gg4 != null : "fx:id=\"gg4\" was not injected: check your FXML file 'VisualizzaSequenza.fxml'.";
	        assert ora4 != null : "fx:id=\"ora4\" was not injected: check your FXML file 'VisualizzaSequenza.fxml'.";
	        assert corso5 != null : "fx:id=\"corso5\" was not injected: check your FXML file 'VisualizzaSequenza.fxml'.";
	        assert gg5 != null : "fx:id=\"gg5\" was not injected: check your FXML file 'VisualizzaSequenza.fxml'.";
	        assert ora5 != null : "fx:id=\"ora5\" was not injected: check your FXML file 'VisualizzaSequenza.fxml'.";
	        assert corso6 != null : "fx:id=\"corso6\" was not injected: check your FXML file 'VisualizzaSequenza.fxml'.";
	        assert gg6 != null : "fx:id=\"gg6\" was not injected: check your FXML file 'VisualizzaSequenza.fxml'.";
	        assert ora6 != null : "fx:id=\"ora6\" was not injected: check your FXML file 'VisualizzaSequenza.fxml'.";
	        assert corso7 != null : "fx:id=\"corso7\" was not injected: check your FXML file 'VisualizzaSequenza.fxml'.";
	        assert gg7 != null : "fx:id=\"gg7\" was not injected: check your FXML file 'VisualizzaSequenza.fxml'.";
	        assert ora7 != null : "fx:id=\"ora7\" was not injected: check your FXML file 'VisualizzaSequenza.fxml'.";
	        assert corso8 != null : "fx:id=\"corso8\" was not injected: check your FXML file 'VisualizzaSequenza.fxml'.";
	        assert gg8 != null : "fx:id=\"gg8\" was not injected: check your FXML file 'VisualizzaSequenza.fxml'.";
	        assert ora8 != null : "fx:id=\"ora8\" was not injected: check your FXML file 'VisualizzaSequenza.fxml'.";
	        assert corso9 != null : "fx:id=\"corso9\" was not injected: check your FXML file 'VisualizzaSequenza.fxml'.";
	        assert gg9 != null : "fx:id=\"gg9\" was not injected: check your FXML file 'VisualizzaSequenza.fxml'.";
	        assert ora9 != null : "fx:id=\"ora9\" was not injected: check your FXML file 'VisualizzaSequenza.fxml'.";
	        assert corso10 != null : "fx:id=\"corso10\" was not injected: check your FXML file 'VisualizzaSequenza.fxml'.";
	        assert gg10 != null : "fx:id=\"gg10\" was not injected: check your FXML file 'VisualizzaSequenza.fxml'.";
	        assert ora10 != null : "fx:id=\"ora10\" was not injected: check your FXML file 'VisualizzaSequenza.fxml'.";
	        assert txtProfitto != null : "fx:id=\"txtProfitto\" was not injected: check your FXML file 'VisualizzaSequenza.fxml'.";

	    }

		public void setModel(Model model, Stage s, List<Corso> corsi) {
			// TODO Auto-generated method stub
			this.model= model;
			this.stage = s;
			
			this.corso1.setText(corsi.get(0).getNome());
			this.gg1.setText(corsi.get(0).getGiorno());
			this.ora1.setText(corsi.get(0).getOraInizio()+" - "+corsi.get(0).getOraFine());
			
			this.corso2.setText(corsi.get(1).getNome());
			this.gg2.setText(corsi.get(1).getGiorno());
			this.ora2.setText(corsi.get(1).getOraInizio()+" - "+corsi.get(1).getOraFine());
			
			this.corso3.setText(corsi.get(2).getNome());
			this.gg3.setText(corsi.get(2).getGiorno());
			this.ora3.setText(corsi.get(2).getOraInizio()+" - "+corsi.get(2).getOraFine());
			
			this.corso4.setText(corsi.get(3).getNome());
			this.gg4.setText(corsi.get(3).getGiorno());
			this.ora4.setText(corsi.get(3).getOraInizio()+" - "+corsi.get(3).getOraFine());
			
			this.corso5.setText(corsi.get(4).getNome());
			this.gg5.setText(corsi.get(4).getGiorno());
			this.ora5.setText(corsi.get(4).getOraInizio()+" - "+corsi.get(4).getOraFine());
			
			this.corso6.setText(corsi.get(5).getNome());
			this.gg6.setText(corsi.get(5).getGiorno());
			this.ora6.setText(corsi.get(5).getOraInizio()+" - "+corsi.get(5).getOraFine());
			
			this.corso7.setText(corsi.get(6).getNome());
			this.gg7.setText(corsi.get(6).getGiorno());
			this.ora7.setText(corsi.get(6).getOraInizio()+" - "+corsi.get(6).getOraFine());
			
			this.corso8.setText(corsi.get(7).getNome());
			this.gg8.setText(corsi.get(7).getGiorno());
			this.ora8.setText(corsi.get(7).getOraInizio()+" - "+corsi.get(7).getOraFine());
			
			this.corso9.setText(corsi.get(8).getNome());
			this.gg9.setText(corsi.get(8).getGiorno());
			this.ora9.setText(corsi.get(8).getOraInizio()+" - "+corsi.get(8).getOraFine());
			
			this.corso10.setText(corsi.get(9).getNome());
			this.gg10.setText(corsi.get(9).getGiorno());
			this.ora10.setText(corsi.get(9).getOraInizio()+" - "+corsi.get(9).getOraFine());
			
			this.txtProfitto.setText(""+Math.round(model.getProfitto(corsi))+" €");
		}
	}

