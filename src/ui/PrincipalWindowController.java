package ui;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Bank;
import model.Client;

public class PrincipalWindowController {
	private Bank bank;
	private Client client;
	private ClientController cc;

	@FXML
	private ToggleGroup options;

	@FXML
	private RadioButton queue;

	@FXML
	private RadioButton information;

	@FXML
	public void initialize() {
		bank= new Bank();
		bank.data();
	}	

	@FXML
	void start(ActionEvent event) throws IOException {
		if(queue.isSelected()) {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ClientQueue.fxml"));
			Scene scene= new Scene(fxmlLoader.load());
			Stage stage= new Stage();
			stage.getIcons().add(new Image(Main.class.getResourceAsStream("bank-flat.png")));
			stage.setTitle("Waiting line");
			stage.setResizable(false);
			stage.setScene(scene);
			stage.show();
		}else if(information.isSelected()) {	
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(getClass().getResource("ClientInformation.fxml"));
			Pane root = fxmlLoader.load();		
			cc = fxmlLoader.getController();
			cc.setPrincipal(this);
			Scene scene= new Scene(root);
			Stage stage= new Stage();
			stage.getIcons().add(new Image(Main.class.getResourceAsStream("bank-flat.png")));
			stage.setTitle("Client Information");
			stage.setResizable(false);
			stage.setScene(scene);
			stage.show();
		}
	}

	public Client getCurrentClient() {
		return client;
	}
	
	public void setCurrentClient(Client client) {
		this.client= client;
	}
	
    @FXML
    void canceledClients(ActionEvent event) throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CancelledClients.fxml"));
		Scene scene= new Scene(fxmlLoader.load());
		Stage stage= new Stage();
		stage.getIcons().add(new Image(Main.class.getResourceAsStream("bank-flat.png")));
		stage.setTitle("Client Information");
		stage.setResizable(false);
		stage.setScene(scene);
		stage.show();
    }

    @FXML
    void clientDatabase(ActionEvent event) throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CurrentClients.fxml"));
		Scene scene= new Scene(fxmlLoader.load());
		Stage stage= new Stage();
		stage.getIcons().add(new Image(Main.class.getResourceAsStream("bank-flat.png")));
		stage.setTitle("Client Information");
		stage.setResizable(false);
		stage.setScene(scene);
		stage.show();
    }
    
    public Client searchClient(String id) {
    	return bank.searchClient(id);
    }
}