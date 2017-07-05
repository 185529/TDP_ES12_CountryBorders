/**
 * Sample Skeleton for 'CountryBorders.fxml' Controller Class
 */

package it.polito.tdp.countryborders;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.countryborders.model.Country;
import it.polito.tdp.countryborders.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class CountryBordersController {
	
	private Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="boxPartenza"
    private ComboBox<Country> boxPartenza; // Value injected by FXMLLoader

    @FXML // fx:id="boxDestinazione"
    private ComboBox<Country> boxDestinazione; // Value injected by FXMLLoader

    @FXML // fx:id="textResult"
    private TextArea textResult; // Value injected by FXMLLoader

    @FXML
    void handlePercorso(ActionEvent event) {

    }

    @FXML
    void handleRaggiungibili(ActionEvent event) {
    	
    	Country partenza = boxPartenza.getValue();
    	
    	if(partenza==null){
    		textResult.appendText("ERRORE\n");
    		return;
    	}
    	
    	List<Country> raggiungibili = model.getRaggiungibili(partenza);
    	
    	textResult.appendText(raggiungibili.toString());
    	
    	boxDestinazione.getItems().clear();
    	boxDestinazione.getItems().addAll(raggiungibili);;

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert boxPartenza != null : "fx:id=\"boxPartenza\" was not injected: check your FXML file 'CountryBorders.fxml'.";
        assert boxDestinazione != null : "fx:id=\"boxDestinazione\" was not injected: check your FXML file 'CountryBorders.fxml'.";
        assert textResult != null : "fx:id=\"textResult\" was not injected: check your FXML file 'CountryBorders.fxml'.";

    }

	/**
	 * @param model the model to set
	 */
	public void setModel(Model model) {
		
		this.model = model;
		
		// popolo la prima tendina
		
		boxPartenza.getItems().addAll(model.getCountries());
		
	}
}
