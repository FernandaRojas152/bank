package ui;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class CardPaymentController {
	
    @FXML
    private BorderPane mainPane;
    
    @FXML
    private TextField amountMoney;

    @FXML
    private Label total;
    
    @FXML
    private Button btnBack;
    
    public PrincipalWindowController principalWindowController;
    public ActionsController actionsController;
    
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
	public void back(ActionEvent event) {
		Stage stage = (Stage) btnBack.getScene().getWindow();
		stage.close();
		actionsController.getStage().show();
	}
    
    public void setActionsController(ActionsController actionsController) {
    	this.actionsController = actionsController;
    }	
    
    public void setPrincipal(PrincipalWindowController principalWindowController) {
    	this.principalWindowController = principalWindowController;
    }
}
