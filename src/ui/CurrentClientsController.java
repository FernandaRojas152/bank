package ui;

import java.time.LocalDate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Bank;
import model.Client;

public class CurrentClientsController {
	private Bank bank;
	private Client client;
	private PrincipalWindowController principal;
	
	@FXML
    private TableView<Client> table;
	
    @FXML
    private TableColumn<Client,String> name;

    @FXML
    private TableColumn<Client, String> id;

    @FXML
    private TableColumn<Client, LocalDate> boundingTime;

    @FXML
    private TableColumn<Client, String> amount;
    
    public ObservableList<Client> getCurrentClients(){
    	ObservableList<Client> c= FXCollections.observableArrayList();
    	for (Client clients : bank.getClientList()) {
			c.add(clients);	
		}
    	return c;
    }
    
    @FXML
    public void initialize() {
    	bank= new Bank();
    	bank.data();
    	System.out.println(bank.getClientList().get(0).getName());
    	name.setCellValueFactory(new PropertyValueFactory<Client, String>("name"));
    	id.setCellValueFactory(new PropertyValueFactory<Client, String>("id"));
    	boundingTime.setCellValueFactory(new PropertyValueFactory<Client, LocalDate>("memberSinceDate"));
    	amount.setCellValueFactory(new PropertyValueFactory<Client, String>("cardAmount"));
    	table.setItems(getCurrentClients());
    }
    
    public void setPrincipal(PrincipalWindowController principal) {
		this.principal = principal;
	}
}
