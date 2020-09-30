package ui;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class CardPaymentController {	
    @FXML
    private BorderPane mainPane;
    
    @FXML
    private TextField amountMoney;

    @FXML
    private Label total;
    
    @FXML
    public void initialize() {
    	
    }
    
    @FXML
    void payAmount(ActionEvent event) throws IOException {
    	load("payAmountAccount");
    }

    @FXML
    void payCash(ActionEvent event) throws IOException {
    	load("payCash");
    }
    
    private void load(String name) throws IOException {
    	Parent root = null;
    	try {
    		root= FXMLLoader.load(getClass().getResource(name+".fxml"));
		} catch (IOException e) {
			Logger.getLogger(CardPaymentController.class.getName()).log(Level.SEVERE, null, e);
		}
    	mainPane.setCenter(root);
    	
    }

    @FXML
    void payWithCash(ActionEvent event) {
    	
    }
    
    @FXML
    void payWithAmount(ActionEvent event) {

    }
    
    @FXML
    void back(ActionEvent event) {

    }
    

}
