package it.polito.tdp.teatrino;

import it.polito.tdp.teatrino.db.TeatrinoDAO;
import it.polito.tdp.teatrino.model.Cliente;
import it.polito.tdp.teatrino.model.ClienteHidden;
import it.polito.tdp.teatrino.model.Model;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

public class ClientiController {

	private Model model;
	private Stage stage;
	
	@FXML
    private Button btnDipendenti;

    @FXML
    private Button btnCorsi;

    @FXML
    private Button btnMoney;

    @FXML
    private Button btnPianifica;

    @FXML
    private Button btnEventi;

    @FXML
    private TextField txtClienti;

    @FXML
    private TableView<ClienteHidden> tableClienti;

    @FXML
    private TableColumn<ClienteHidden, String> nomeCol;

    @FXML
    private TableColumn<ClienteHidden, String> corsoCol;

    @FXML
    private TableColumn<ClienteHidden, String> contattiCol;
    
	private TeatrinoDAO dao;
	
	 @FXML
	 private Button btnAggiungi;

	 
	 @FXML
	 void aggiungiCliente(ActionEvent event) {

		 try {
 			
 	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("AggiungiCliente.fxml"));
 			BorderPane root = (BorderPane) loader.load();
 			Scene scene = new Scene(root);
 			Stage s = new Stage();
 			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
 			
 			AggiungiController controller = loader.getController();
  			controller.setModel(model, s);
 			
  			s.setScene(scene);
 			//s.setAlwaysOnTop(true);
 			s.show();
 	    	
 	    	} catch (Exception e) {
 				// TODO: handle exception
 	    		e.printStackTrace();
 			}
		 
     }
		 

    @FXML
    void cercaClienti(KeyEvent event){
    	
    	String string = "";
    	Character character = null;
    	character = event.getCharacter().charAt(event.getCharacter().length()-1);
    	if(Character.isAlphabetic(character))
    		string = txtClienti.getText()+character;
    	else string = txtClienti.getText();
    	System.out.println(string);
    	ObservableList<ClienteHidden> values = FXCollections.observableArrayList(dao.cercaCliente(string,model.getCorsiMap()));
    	tableClienti.setItems(values);
    	System.out.println("Partita ricerca");
    	
    }
    
    public void cercaClienti(String s) {
    	ObservableList<ClienteHidden> values = FXCollections.observableArrayList(dao.cercaCliente(s,model.getCorsiMap()));
    	tableClienti.setItems(values);
    }
    
    @FXML
    void visualizza(MouseEvent event) {

        if(event.getClickCount() == 2){
            ClienteHidden cliente = tableClienti.getSelectionModel().getSelectedItem();
            System.out.println(cliente);
            Cliente c = dao.getCliente(cliente.getNome(),model.getClientiMap());
            if(c != null)
            	try {
        			
        	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("VisualizzaClienti.fxml"));
        			BorderPane root = (BorderPane) loader.load();
        			Scene scene = new Scene(root);
        			Stage s = new Stage();
        			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        			
        			VisualizzaClientiController controller = loader.getController();
         			controller.setModel(model, s , c);
        			
         			s.setScene(scene);
        			//s.setAlwaysOnTop(true);
        			s.show();
        	    	
        	    	} catch (Exception e) {
        				// TODO: handle exception
        	    		e.printStackTrace();
        			}
            }
    	
    }
  

    @FXML
    void openCorsi(ActionEvent event) {

    	try {
			
	    	System.out.println("Apri clienti");
	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Corsi.fxml"));
			BorderPane root = (BorderPane) loader.load();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			CorsiController controller = loader.getController();
 			controller.setModel(model, stage);
			
 			stage.setScene(scene);
			//stage.setAlwaysOnTop(true);
			stage.show();
	    	
	    	} catch (Exception e) {
				// TODO: handle exception
	    		e.printStackTrace();
			}
    	
    }

    @FXML
    void openDipendenti(ActionEvent event) {
    	
    	try {
			
	    	System.out.println("Apri clienti");
	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Dipendenti.fxml"));
			BorderPane root = (BorderPane) loader.load();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			DipendentiController controller = loader.getController();
 			controller.setModel(model, stage);
			
 			stage.setScene(scene);
			//stage.setAlwaysOnTop(true);
			stage.show();
	    	
	    	} catch (Exception e) {
				// TODO: handle exception
	    		e.printStackTrace();
			}

    }

    @FXML
    void openEventi(ActionEvent event) {

    	try {
			
	    	System.out.println("Apri clienti");
	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Eventi.fxml"));
			BorderPane root = (BorderPane) loader.load();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			EventiController controller = loader.getController();
 			controller.setModel(model, stage);
			
 			stage.setScene(scene);
			//stage.setAlwaysOnTop(true);
			stage.show();
	    	
	    	} catch (Exception e) {
				// TODO: handle exception
	    		e.printStackTrace();
			}
    	
    }

    @FXML
    void openMoney(ActionEvent event) {

    	try {
			
	    	System.out.println("Apri clienti");
	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("ContaB.fxml"));
			BorderPane root = (BorderPane) loader.load();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			ContaBController controller = loader.getController();
 			controller.setModel(model, stage);
			
 			stage.setScene(scene);
			//stage.setAlwaysOnTop(true);
			stage.show();
	    	
	    	} catch (Exception e) {
				// TODO: handle exception
	    		e.printStackTrace();
			}
    	
    }

    @FXML
    void openPianifica(ActionEvent event) {

    	try {
    	System.out.println("Apri clienti");
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Ottimizzazione.fxml"));
		BorderPane root = (BorderPane) loader.load();
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
		OttimizzaController controller = loader.getController();
			controller.setModel(model, stage);
		
			stage.setScene(scene);
		//stage.setAlwaysOnTop(true);
		stage.show();
    	
    	} catch (Exception e) {
			// TODO: handle exception
    		e.printStackTrace();
		}
    }
    
    /*public void handleEvent(KeyEvent e) 
    {
        String s = e.getCharacter();     
        if(!s.equals("")) {
        	cercaClienti();
        	System.out.println("Cerca clienti");
        }
    }*/
    
    public void setModel(Model m, Stage s) {
    	
    	this.model = m;
    	this.stage = s;
    	ObservableList<ClienteHidden> values = FXCollections.observableArrayList(model.clientiHidden);
    	tableClienti.setItems(values);
    	dao = new TeatrinoDAO();
    	/*txtClienti.setOnKeyTyped(new EventHandler<KeyEvent>() 
        {
            public void handle(final KeyEvent keyEvent) 
            {
                handleEvent(keyEvent);
            }
        });*/
   
    }

    @FXML
    void initialize() {
        assert btnDipendenti != null : "fx:id=\"btnDipendenti\" was not injected: check your FXML file 'Clienti.fxml'.";
        assert btnCorsi != null : "fx:id=\"btnCorsi\" was not injected: check your FXML file 'Clienti.fxml'.";
        assert btnMoney != null : "fx:id=\"btnMoney\" was not injected: check your FXML file 'Clienti.fxml'.";
        assert btnPianifica != null : "fx:id=\"btnPianifica\" was not injected: check your FXML file 'Clienti.fxml'.";
        assert btnEventi != null : "fx:id=\"btnEventi\" was not injected: check your FXML file 'Clienti.fxml'.";
        assert tableClienti != null : "fx:id=\"tableClienti\" was not injected: check your FXML file 'Clienti.fxml'.";
        assert txtClienti != null : "fx:id=\"txtCercaCliente\" was not injected: check your FXML file 'Clienti.fxml'.";
        assert btnAggiungi != null : "fx:id=\"btnAggiungi\" was not injected: check your FXML file 'Clienti.fxml'.";

        nomeCol.setCellValueFactory(new PropertyValueFactory<ClienteHidden, String>("nome"));
        corsoCol.setCellValueFactory(new PropertyValueFactory<ClienteHidden, String>("corso"));
        contattiCol.setCellValueFactory(new PropertyValueFactory<ClienteHidden, String>("contatti"));
        
    }
}
