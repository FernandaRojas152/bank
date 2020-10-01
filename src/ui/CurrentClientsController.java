package ui;

import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
    private Button btnSortByName; 
    
    @FXML
    private Button btnSortByID;
    
    @FXML
    private Button btnSortByDate;
    
    @FXML
    private Button btnSortByAmount;
    
    private ObservableList<Client> observableList;

    @FXML
    public void initialize() {
    	observableList = FXCollections.observableArrayList();
    	name.setCellValueFactory(new PropertyValueFactory<Client, String>("name"));
    	name.setSortable(false);
    	id.setCellValueFactory(new PropertyValueFactory<Client, String>("id"));
    	id.setSortable(false);
    	boundingTime.setCellValueFactory(new PropertyValueFactory<Client, LocalDate>("memberSinceDate"));
    	boundingTime.setSortable(false);
    	amount.setCellValueFactory(new PropertyValueFactory<Client, String>("amount"));
    	amount.setSortable(false);
    	observableList = FXCollections.observableArrayList();
    }
    
    @FXML
    public void sort(ActionEvent event) {
    	
    	if(event.getSource().equals(btnSortByName))
    		principal.getBank().sortClientsByName();
    	else if(event.getSource().equals(btnSortByID))
    		principal.getBank().sortClientsByID();
    	else if(event.getSource().equals(btnSortByDate))
    		principal.getBank().sortClientsByTime();
    	else if(event.getSource().equals(btnSortByAmount))
    		principal.getBank().sortClientsByAmount();
    	table.setItems(getCurrentClients());
    }
    
    public ObservableList<Client> getCurrentClients(){
    	observableList.clear();
    	for (Client clients : principal.getBank().getClientList()) {
    		observableList.add(clients);	
		}
    	return observableList;
    }
    
    public void setPrincipal(PrincipalWindowController principal) {
		this.principal = principal;
		table.setItems(getCurrentClients());
	}
}
