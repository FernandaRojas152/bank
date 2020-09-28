package ui;

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
	@FXML
    private TableView<Client> table;
	
    @FXML
    private TableColumn<Client,String> name;

    @FXML
    private TableColumn<Client, String> id;

    @FXML
    private TableColumn<Client, String> boundingTime;

    @FXML
    private TableColumn<Client, String> amount;
    
    public CurrentClientsController() {
		// TODO Auto-generated constructor stub
	}
    
    @FXML
    public void initialize() {
    	bank= new Bank();
    	bank.data();
    }
    
    public void showTable() {
    	configureTable();
    	table.setItems(getCurrentClients());
    }
    
    public void configureTable() {
    	name.setCellValueFactory(new PropertyValueFactory<Client, String>("name"));
    	id.setCellValueFactory(new PropertyValueFactory<Client, String>("id"));
    	boundingTime.setCellValueFactory(new PropertyValueFactory<Client, String>("boundingTime"));
    	amount.setCellValueFactory(new PropertyValueFactory<Client, String>("amount"));
    }
    
    public ObservableList<Client> getCurrentClients(){
    	ObservableList<Client> c= FXCollections.observableArrayList();
    	for (Client clients : bank.getClientList()) {
			c.add(clients);
		}
    	return c;
    }
}
