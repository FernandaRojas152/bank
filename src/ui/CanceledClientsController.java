package ui;

import java.time.LocalDate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Client;

public class CanceledClientsController {

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
    
    private PrincipalWindowController principal;
    
    @FXML
    public void initialize() {
    	name.setCellValueFactory(new PropertyValueFactory<Client, String>("name"));
    	id.setCellValueFactory(new PropertyValueFactory<Client, String>("id"));
    	cancelationDate.setCellValueFactory(new PropertyValueFactory<Client, LocalDate>("cancelation"));
    	comments.setCellValueFactory(new PropertyValueFactory<Client, String>("comments"));
    }
    
    public void undoAction(ActionEvent event) {
    	try {
			principal.getBank().undo();
			table.setItems(getCancelledClients());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public ObservableList<Client> getCancelledClients() {
    	ObservableList<Client> c= FXCollections.observableArrayList();
    	for (Client clients : principal.getBank().getClientStack()) {
			c.add(clients);	
		}
    	return c;
    }
    
    public void setPrincipal(PrincipalWindowController principal) {
		this.principal = principal;
		table.setItems(getCancelledClients());
	}
}