package it.polito.tdp.teatrino;

import it.polito.tdp.teatrino.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class OutputController {

    @FXML
    private Label labelText;

	private Stage stage;

	private Model model;

    @FXML
    void initialize() {
        assert labelText != null : "fx:id=\"labelText\" was not injected: check your FXML file 'Output.fxml'.";
    }

	public void setModel(Model model, Stage s, String output) {
		// TODO Auto-generated method stub
		this.model= model;
		this.stage = s;
		this.labelText.setText(output); 
		
	}
    
}