package ui;

import java.time.LocalDate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Bank;
import model.Client;

public class CanceledClientsController {
	private Bank bank;
	private PrincipalWindowController principal;
	
	@FXML
    private TableView<Client> table;
	
    @FXML
    private TableColumn<Client, String> name;

    @FXML
    private TableColumn<Client, String> id;

    @FXML
    private TableColumn<Client, LocalDate> cancelationDate;

    @FXML
    private TableColumn<Client, String> comments;
    
    @FXML
    public void initialize() {
    	bank= new Bank();
    	bank.data();
    	name.setCellValueFactory(new PropertyValueFactory<Client, String>("name"));
    	id.setCellValueFactory(new PropertyValueFactory<Client, String>("id"));
    	cancelationDate.setCellValueFactory(new PropertyValueFactory<Client, LocalDate>("cancelation"));
    	comments.setCellValueFactory(new PropertyValueFactory<Client, String>("comments"));
    	table.setItems(getClients());
    }
    @FXML
    void undoAction(ActionEvent event) throws Exception {
    	bank.undo();
    }
    
    public ObservableList<Client> getClients(){
    	ObservableList<Client> c = FXCollections.observableArrayList();
    	for(Client cl: bank.getClientStack()) {
    		System.out.println(bank.getClientStack().getFirst());
    		c.add(cl);
    	}
    	return c;
    }

}