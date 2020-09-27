package ui;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Bank;
import model.Client;

public class QueueController {
	private Bank bank;
	
    @FXML
    private ListView<Client> priorityQueue;

    @FXML
    private ListView<Client> normalQueue;
    
    public QueueController() {
    	bank= new Bank();
	}
    
    public void initialize() {
    	
    }
    
    @FXML
    void attendClient(ActionEvent event) throws IOException {
    	//priorityQueue.setItems(bank.getClientHeap());
    	//normalQueue.getItems().add(bank.getClientQueue());
    	
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AttendClient.fxml"));
    	Scene scene= new Scene(fxmlLoader.load());
    	Stage stage= new Stage();
    	stage.getIcons().add(new Image(Main.class.getResourceAsStream("bank-flat.png")));
		stage.setTitle("Attend Client");
		stage.setScene(scene);
		stage.show();
    }

    @FXML
    void back(ActionEvent event) {

    }

}
