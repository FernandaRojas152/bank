package ui;

import java.time.LocalDate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Client;

public class CurrentClientsController {

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

    @FXML
    public void initialize() {
    	name.setCellValueFactory(new PropertyValueFactory<Client, String>("name"));
    	id.setCellValueFactory(new PropertyValueFactory<Client, String>("id"));
    	boundingTime.setCellValueFactory(new PropertyValueFactory<Client, LocalDate>("memberSinceDate"));
    	amount.setCellValueFactory(new PropertyValueFactory<Client, String>("amount"));
    }
    
    public ObservableList<Client> getCurrentClients(){
    	ObservableList<Client> c= FXCollections.observableArrayList();
    	for (Client clients : principal.getBank().getClientList()) {
			c.add(clients);	
		}
    	return c;
    }
    
    public void setPrincipal(PrincipalWindowController principal) {
		this.principal = principal;
		table.setItems(getCurrentClients());
	}
}
