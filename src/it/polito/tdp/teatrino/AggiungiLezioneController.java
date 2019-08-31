package it.polito.tdp.teatrino;

import java.awt.MenuItem;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.sun.javafx.scene.control.SelectedCellsMap;

import it.polito.tdp.teatrino.db.TeatrinoDAO;
import it.polito.tdp.teatrino.model.Cliente;
import it.polito.tdp.teatrino.model.Corso;
import it.polito.tdp.teatrino.model.Lezione;
import it.polito.tdp.teatrino.model.Model;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ComboBox;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class AggiungiLezioneController {

	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Corso> boxCorso;

    @FXML
    private DatePicker datePicker;

    @FXML
    private ComboBox<ComboBoxItemWrap<Cliente>> boxPresenze;

    @FXML
    private Button btnAdd;

	private ArrayList<Cliente> selected;

	private String[] assenti;

    @FXML
    void addLesson(ActionEvent event) {
    	
    	int presenze = 0;
    	String output = "";

    	if(this.boxCorso.getValue() != null && this.datePicker.getValue() != null) {
    		
    		System.out.println(assenti);
    		presenze = (dao.getIscritti(boxCorso.getValue()).size() - assenti.length);
    		System.out.println(presenze);
    		Lezione lezione = new Lezione("L"+(model.getLezioniMap().size()+1), this.boxCorso.getValue().getId(), datePicker.getValue(), presenze);
    		dao.aggiungiLezione(lezione);
    		model.getLezioniMap().put(lezione.getId(), lezione);
    		for(Cliente cliente : dao.getIscritti(boxCorso.getValue())) {
    			
    			dao.setDovuto(boxCorso.getValue().getCosto(), cliente);
    			model.getClientiMap().get(cliente.getId()).setDovuto(cliente.getDovuto() + boxCorso.getValue().getCosto());
    			System.out.println(cliente.getDovuto());
    			
    		}
    		output = "Aggiunta lezione correttamente!";
    		
    	} else {
    		
    		output = "Mancanza dati!";
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
    	
    }
    
    public class ComboBoxItemWrap<T> {

        private BooleanProperty check = new SimpleBooleanProperty(false);
        private ObjectProperty<T> item = new SimpleObjectProperty<>();

        ComboBoxItemWrap() {
        }

        ComboBoxItemWrap(T item) {
            this.item.set(item);
        }

        ComboBoxItemWrap(T item, Boolean check) {
            this.item.set(item);
            this.check.set(check);
        }

        public BooleanProperty checkProperty() {
            return check;
        }

        public Boolean getCheck() {
            return check.getValue();
        }

        public void setCheck(Boolean value) {
            check.set(value);
        }

        public ObjectProperty<T> itemProperty() {
            return item;
        }

        public T getItem() {
            return item.getValue();
        }

        public void setItem(T value) {
            item.setValue(value);
        }

        @Override
        public String toString() {
            return item.getValue().toString();
        }
    }
    
    @FXML
    void addMenu(ActionEvent event) {
    	
    	boxPresenze.getItems().clear();
    	selected = new ArrayList<Cliente>();
    	
    	boxPresenze.setCellFactory( c -> {
            ListCell<ComboBoxItemWrap<Cliente>> cell = new ListCell<ComboBoxItemWrap<Cliente>>(){
                @Override
                protected void updateItem(ComboBoxItemWrap<Cliente> item, boolean empty) {
                    super.updateItem(item, empty);
                    if (!empty) {
                        final CheckBox cb = new CheckBox(item.toString());
                        cb.selectedProperty().bind(item.checkProperty());
                        setGraphic(cb);
                    }
                }
            };

            cell.addEventFilter(MouseEvent.MOUSE_RELEASED, evento -> {
                cell.getItem().checkProperty().set(!cell.getItem().checkProperty().get());
                StringBuilder sb = new StringBuilder();
                boxPresenze.getItems().filtered( f-> f!=null).filtered( f-> f.getCheck()).forEach( p -> {
                    sb.append( "; "+p.getItem());
                    if(!selected.contains(p.getItem()))
                    		selected.add(p.getItem());
                    System.out.println(selected);
                    System.out.println(sb);

                });
                final String string = sb.toString();
                boxPresenze.setPromptText(string.substring(Integer.min(2, string.length())));
                assenti = boxPresenze.getPromptText().split(";");
                for(String s : assenti)
                	System.out.println(s.trim());
            });

            return cell;
        });
    	
    	for(Cliente cliente : dao.getIscritti(this.boxCorso.getValue())) {
    		
    		this.boxPresenze.getItems().add(new ComboBoxItemWrap<Cliente>(cliente));
    		
    	}
    	
    }

    @FXML
    void initialize() {
        assert boxCorso != null : "fx:id=\"boxCorso\" was not injected: check your FXML file 'AggiungiLezione.fxml'.";
        assert datePicker != null : "fx:id=\"datePicker\" was not injected: check your FXML file 'AggiungiLezione.fxml'.";
        assert boxPresenze != null : "fx:id=\"boxPresenze\" was not injected: check your FXML file 'AggiungiLezione.fxml'.";
        assert btnAdd != null : "fx:id=\"btnAdd\" was not injected: check your FXML file 'AggiungiLezione.fxml'.";

    }
	
	private Model model;
	private Stage stage;

	private TeatrinoDAO dao;

	public void setModel(Model model, Stage s) {
		// TODO Auto-generated method stub
		this.model=model;
		this.stage = s;
		dao = new TeatrinoDAO();
		
		for(Corso c : model.getCorsiMap().values()) {
			
			boxCorso.getItems().add(c);
			
		}
	
	}

}
