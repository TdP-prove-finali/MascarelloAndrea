package it.polito.tdp.teatrino;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.teatrino.model.Cliente;
import it.polito.tdp.teatrino.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class VisualizzaClientiController {

	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label labelNome;

    @FXML
    private Label labelCorso;

    @FXML
    private Label labelContatti;

    @FXML
    private Button btnPaga;

    @FXML
    private Button btnElimina;

	private Model model;

	private Stage stage;
	
    @FXML
    private Label labelDebito;

	private Cliente cliente;

    @FXML
    void elimina(ActionEvent event) {

    	try {
			
	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Elimina.fxml"));
			BorderPane root = (BorderPane) loader.load();
			Scene scene = new Scene(root);
			Stage s = new Stage();
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			EliminaController controller = loader.getController();
 			controller.setModel(model, s ,cliente);
			
 			s.setScene(scene);
			//s.setAlwaysOnTop(true);
 			stage.close();
			s.show();
	    	
	    	} catch (Exception e) {
				// TODO: handle exception
	    		e.printStackTrace();
			}
    	
    }

    @FXML
    void paga(ActionEvent event) {

    	try {
			
	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Paga.fxml"));
			BorderPane root = (BorderPane) loader.load();
			Scene scene = new Scene(root);
			Stage s = new Stage();
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			PagaController controller = loader.getController();
 			controller.setModel(model, s ,cliente);
			
 			s.setScene(scene);
			//s.setAlwaysOnTop(true);
 			stage.close();
			s.show();
	    	
	    	} catch (Exception e) {
				// TODO: handle exception
	    		e.printStackTrace();
			}
    	
    }

    @FXML
    void initialize() {
        assert labelNome != null : "fx:id=\"labelNome\" was not injected: check your FXML file 'VisualizzaClienti.fxml'.";
        assert labelCorso != null : "fx:id=\"labelCorso\" was not injected: check your FXML file 'VisualizzaClienti.fxml'.";
        assert labelContatti != null : "fx:id=\"labelContatti\" was not injected: check your FXML file 'VisualizzaClienti.fxml'.";
        assert labelDebito != null : "fx:id=\"labelDebito\" was not injected: check your FXML file 'VisualizzaClienti.fxml'.";
        assert btnPaga != null : "fx:id=\"btnPaga\" was not injected: check your FXML file 'VisualizzaClienti.fxml'.";
        assert btnElimina != null : "fx:id=\"btnElimina\" was not injected: check your FXML file 'VisualizzaClienti.fxml'.";
        
    }

	public void setModel(Model model, Stage stage, Cliente c) {
		
		// TODO Auto-generated method stub
		this.model= model;
		this.stage = stage;
		this.labelContatti.setText(c.getContatti());
		this.labelCorso.setText(model.getCorsiMap().get(c.getIdCorso()).getNome());
		this.labelNome.setText(c.getNome());
		this.labelDebito.setText((c.getDovuto() - c.getPagato())+" €");
		this.cliente = c;
		
	}
	
}
