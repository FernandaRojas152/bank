package ui;

import java.time.LocalDate;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
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
    
    ObservableList<Client> observableList;
    private PrincipalWindowController principal;
    
    @FXML
    public void initialize() {
    	name.setCellValueFactory(new PropertyValueFactory<Client, String>("name"));
    	id.setCellValueFactory(new PropertyValueFactory<Client, String>("id"));
    	cancelationDate.setCellValueFactory(new PropertyValueFactory<Client, LocalDate>("cancelation"));
    	comments.setCellValueFactory(new PropertyValueFactory<Client, String>("comments"));
    	observableList = FXCollections.observableArrayList();
    }
    
    public void undoAction(ActionEvent event) throws Exception {
    	try {
			principal.getBank().undo();
			table.setItems(getCancelledClients());
		} catch (NullPointerException e) {
			Platform.runLater(() -> {
				e.getMessage();
				Alert dialog = new Alert(AlertType.ERROR, "There are not more more clients to return.", ButtonType.OK);
				dialog.show();
			});
		}
    }
    
    public ObservableList<Client> getCancelledClients() {
    	observableList.clear();
    	for (Client client : principal.getBank().getClientStack()) {
			observableList.add(client);
		}
    	return observableList;
    }
    
    public void setPrincipal(PrincipalWindowController principal) {
		this.principal = principal;
		table.setItems(getCancelledClients());
	}
}