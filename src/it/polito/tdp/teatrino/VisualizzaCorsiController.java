package it.polito.tdp.teatrino;

import it.polito.tdp.teatrino.db.TeatrinoDAO;
import it.polito.tdp.teatrino.model.Corso;
import it.polito.tdp.teatrino.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class VisualizzaCorsiController {
	
	@FXML
    private Label lblNome;

    @FXML
    private Label lblCosto;

    @FXML
    private Label lblDip1;

    @FXML
    private Label lblDip2;

    @FXML
    private Label lblGiorno;

    @FXML
    private Label lblInizio;

    @FXML
    private Label lblFine;

    @FXML
    private Label lblTipo;

	private Model model;

	private Stage stage;
	
	@FXML
	private Button btnElimina;

	private Corso corso;

	private TeatrinoDAO dao;

	 @FXML
	 void elimina(ActionEvent event) {
		 
		 dao.eliminaCorso(corso);
		 model.getCorsiMap().remove(corso.getId(), corso);
		 this.stage.close();
		 
	 }

	public void setModel(Model model, Stage s, Corso corso) {
		// TODO Auto-generated method stub
		this.model= model;
		this.stage = s;
		this.corso = corso;
		dao = new TeatrinoDAO();
		
		this.lblNome.setText(corso.getNome());
		this.lblCosto.setText(""+corso.getCosto());
		this.lblDip1.setText(model.getDipendentiMap().get(corso.getDip1()).toString());
		if(model.getDipendentiMap().get(corso.getDip2()) != null)
				this.lblDip2.setText(model.getDipendentiMap().get(corso.getDip2()).toString());
		else this.lblDip2.setText("");
		this.lblGiorno.setText(corso.getGiorno());
		this.lblInizio.setText(corso.getOraInizio().toString());
		this.lblFine.setText(corso.getOraFine().toString());
		this.lblTipo.setText(corso.getTipo());
	}
	
}
