package it.polito.tdp.teatrino;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.teatrino.db.TeatrinoDAO;
import it.polito.tdp.teatrino.model.Cliente;
import it.polito.tdp.teatrino.model.Corso;
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

public class AggiungiController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtNome;

    @FXML
    private ComboBox<Corso> boxCorsi;

    @FXML
    private TextField txtContatti;

    @FXML
    private Button btnAggiungi;

	private Stage stage;

	private Model model;

	private TeatrinoDAO dao;

    @FXML
    void aggiungi(ActionEvent event) {
    	
    	String output;
    	
    	if(!txtNome.getText().equals("") && boxCorsi != null) {
    		
    		Cliente cliente = new Cliente(model.getClientiMap().values().size()+1, txtNome.getText(), boxCorsi.getValue().getId(), txtContatti.getText(), 0, 0);
    		model.getClientiMap().put(model.getClientiMap().values().size()+1, cliente);
    		dao.aggiunta(cliente);
    		dao.iscrivi(cliente, boxCorsi.getValue().getId());
    		output = "Cliente aggiunto correttamente!";
    		
    	} else {
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
    	 
    	 System.out.println(model.getClientiMap());
    }

    @FXML
    void initialize() {
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'AggiungiCliente.fxml'.";
        assert boxCorsi != null : "fx:id=\"boxCorsi\" was not injected: check your FXML file 'AggiungiCliente.fxml'.";
        assert txtContatti != null : "fx:id=\"txtContatti\" was not injected: check your FXML file 'AggiungiCliente.fxml'.";
        assert btnAggiungi != null : "fx:id=\"btnAggiungi\" was not injected: check your FXML file 'AggiungiCliente.fxml'.";

    }

	public void setModel(Model model, Stage s) {
		// TODO Auto-generated method stub
		this.model= model;
		this.stage = s;
		dao = new TeatrinoDAO();
		this.boxCorsi.getItems().addAll(model.getCorsiMap().values());
	}
}
