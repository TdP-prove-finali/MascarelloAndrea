package it.polito.tdp.teatrino;

import it.polito.tdp.teatrino.db.TeatrinoDAO;
import it.polito.tdp.teatrino.model.Cliente;
import it.polito.tdp.teatrino.model.Model;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class EliminaController {

	private Model model;
	private Stage stage;
	private Cliente cliente;
	
	    @FXML
	    private ResourceBundle resources;

	    @FXML
	    private URL location;

	    @FXML
	    private Button siElimina;

	    @FXML
	    private Button noElimina;
		private TeatrinoDAO dao;

	    @FXML
	    void elimina(ActionEvent event) {
	    	
	    	dao.elimina(cliente);
	    	model.getClientiMap().remove(cliente);
	    	dao.eliminaIscrizione(cliente);
	    	stage.close();

	    }

	    @FXML
	    void ritorna(ActionEvent event) {

	    	stage.close();
	    	
	    }

	    @FXML
	    void initialize() {
	        assert siElimina != null : "fx:id=\"siElimina\" was not injected: check your FXML file 'Elimina.fxml'.";
	        assert noElimina != null : "fx:id=\"noElimina\" was not injected: check your FXML file 'Elimina.fxml'.";

	    }


	public void setModel(Model model, Stage s, Cliente cliente) {
		// TODO Auto-generated method stub
		this.model= model;
		this.stage = s;
		this.cliente = cliente;
		dao = new TeatrinoDAO();
	}

}
