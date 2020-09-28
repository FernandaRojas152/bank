package ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import model.Bank;
import model.Client;

public class CanceledClientsController {
	private Bank bank;
	
    @FXML
    private TableColumn<Client, String> name;

    @FXML
    private TableColumn<Client, String> id;

    @FXML
    private TableColumn<Client, String> cancelationDate;

    @FXML
    private TableColumn<Client, String> comments;
    
    public CanceledClientsController() {
		bank= new Bank();
	}
    
    @FXML
    public void initialize() {
    	bank.data();
    	
    }
    @FXML
    void undoAction(ActionEvent event) {

    }
    
    public void configureTable() {
    	
    }
    
    public ObservableList<Client> getClients(){
    	//ObservableList<Client> cli = FXCollections.observableArrayList();
    	return null;
    }

}