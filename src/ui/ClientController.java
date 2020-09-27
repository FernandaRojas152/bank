package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import model.Bank;

public class ClientController {
	private Bank bank;
	private PrincipalWindowController principal;

    @FXML
    private Label name;

    @FXML
    private Label idClient;

    @FXML
    private Label boundingTIme;

    @FXML
    private Label amountC;
    
    public ClientController() {
		bank= new Bank();
		principal= new PrincipalWindowController();
	}
    
    public void initialize() {
    	principal.getCurrentClient();
    	name.setText(principal.getCurrentClient().getName());
    	idClient.setText(" ");
    	boundingTIme.setText(" ");
    	amountC.setText(" ");
    }
    
    public 
    @FXML
    void back(ActionEvent event) {
    	
    }

}
