package it.polito.tdp.teatrino;

import it.polito.tdp.teatrino.db.TeatrinoDAO;
import it.polito.tdp.teatrino.model.Cliente;
import it.polito.tdp.teatrino.model.Model;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class PagaController {

	private Model model;
	private Stage stage;

	    @FXML
	    private ResourceBundle resources;

	    @FXML
	    private URL location;

	    @FXML
	    private TextField txtPagato;

	    @FXML
	    private Button btnPaga;
		private Cliente cliente;
		private TeatrinoDAO dao;

	    @FXML
	    void pagamento(ActionEvent event) {

	    	String output = "";
	    	
	    	try {
				
	    		double val = Double.parseDouble(txtPagato.getText());
	    		System.out.println(val);
	    		if(cliente.getDovuto() < (val + cliente.getPagato())) {
	    			output = "Inserito importo errato!";
	    		} else {
	    			
	    			dao.setPagato(val,cliente);
	    			cliente.setPagato(cliente.getPagato()+val);
	    			output = "Pagamento registrato!";
	    			
	    		}
	    		
			} catch (NumberFormatException e) {
				// TODO: handle exception
				output = "Inserisci un valore numerico!";
			}
	    	
	    	try {
    			
    	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("VisualizzaClienti.fxml"));
    			BorderPane root = (BorderPane) loader.load();
    			Scene scene = new Scene(root);
    			Stage s = new Stage();
    			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
    			
    			VisualizzaClientiController controller = loader.getController();
     			controller.setModel(model, s , cliente);
    			
     			s.setScene(scene);
    			//s.setAlwaysOnTop(true);
    			s.show();
    	    	
    	    	} catch (Exception e) {
    				// TODO: handle exception
    	    		e.printStackTrace();
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
	    	 
	    	stage.close();
	    }

	    @FXML
	    void initialize() {
	        assert txtPagato != null : "fx:id=\"txtPagato\" was not injected: check your FXML file 'Paga.fxml'.";
	        assert btnPaga != null : "fx:id=\"btnPaga\" was not injected: check your FXML file 'Paga.fxml'.";

	    }

	public void setModel(Model model, Stage s,Cliente c) {
		// TODO Auto-generated method stub
		this.model= model;
		this.stage = s;
		this.cliente = c;
		dao = new TeatrinoDAO();
	}

}
