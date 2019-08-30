package it.polito.tdp.teatrino;

import java.util.ResourceBundle;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import it.polito.tdp.teatrino.db.TeatrinoDAO;
import it.polito.tdp.teatrino.model.Model;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class AggiungiEventoController {

	private Model model;
	private Stage stage;

	public void setModel(Model model, Stage stage) {
		// TODO Auto-generated method stub
		this.model= model;
		this.stage = stage;
		dao = new TeatrinoDAO();
		
	}
	
	 @FXML
	    private ResourceBundle resources;

	    @FXML
	    private URL location;

	    @FXML
	    private TextField txtNome;

	    @FXML
	    private DatePicker datePicker;

	    @FXML
	    private TextField txtEntrata;

	    @FXML
	    private Button btnAggiungi;

	    @FXML
	    void doAggiungi(ActionEvent event) {

	    	String output = "";
	    	System.out.println("AOOOOO");
	    	boolean h = false;
	    	
	    	if(!txtNome.getText().equals("") && !datePicker.getValue().equals(null) && !txtEntrata.getText().equals("")) {
	    		
	    		try {
	    			double entrata = Double.parseDouble(txtEntrata.getText());
	    	dao.aggiungiEvento(txtNome.getText(),datePicker.getValue(),entrata);
	    	output = "Aggiunta avvenuta correttamente!";
	    	System.out.println("AOOOOO2");
	    		} catch (NumberFormatException e) {
					// TODO: handle exception
	    			try {
			   			
			   	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Output.fxml"));
			   			BorderPane root = (BorderPane) loader.load();
			   			Scene scene = new Scene(root);
			   			Stage s = new Stage();
			   			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			   			
			   			OutputController controller = loader.getController();
			    		controller.setModel(model, s, "Errore formato dati");
			    		h = true;
			   			
			    		s.setScene(scene);
			   			//s.setAlwaysOnTop(true);
			   			s.show();
			   	    	
			   	    	} catch (Exception er) {
			   				// TODO: handle exception
			   	    		e.printStackTrace();
			   			}
	    		
	    	} 
	    	
			}else output = "Mancanza dati!";
	    	
	    	if(h== false) {
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
	    	
	    }
		private TeatrinoDAO dao;

	    @FXML
	    void initialize() {
	        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'AggiungiEvento.fxml'.";
	        assert datePicker != null : "fx:id=\"datePicker\" was not injected: check your FXML file 'AggiungiEvento.fxml'.";
	        assert txtEntrata != null : "fx:id=\"txtEntrata\" was not injected: check your FXML file 'AggiungiEvento.fxml'.";
	        assert btnAggiungi != null : "fx:id=\"btnAggiungi\" was not injected: check your FXML file 'AggiungiEvento.fxml'.";

	    }

}
