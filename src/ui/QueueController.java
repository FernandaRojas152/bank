package ui;

import java.io.IOException;

import heap.IHeap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Bank;
import model.Client;
import queue.IQueue;

public class QueueController {
	private Bank bank;
	
    @FXML
    private ListView<String> priorityQueue;

    @FXML
    private ListView<String> normalQueue;
    
    public QueueController() {
    	bank= new Bank();
	}
    
    public void initialize() {
    	bank.data();
    	getPriorityQueue();
    	getNormalQueue();
    }
    
    @FXML
    void attendClient(ActionEvent event) throws IOException {
    	priorityQueue.getSelectionModel().getSelectedIndex();
    	//priorityQueue.setItems(bank.getClientHeap());
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
    
    public void getNormalQueue() {
    	IQueue<Client> q = new IQueue<Client>();
    	q= bank.getClientQueue();
    	while(!bank.getClientQueue().isEmpty()) {	
    		normalQueue.getItems().add(q.dequeue().getT().getName());
    	}
    }
    
    public void getPriorityQueue() {
    	IHeap<Client> h= new IHeap<Client>(100, true);
    	h= bank.getClientHeap();
    	while(!bank.getClientHeap().isEmpty()) {
    		priorityQueue.getItems().add(h.extract().getName());
    	}
    }

}
