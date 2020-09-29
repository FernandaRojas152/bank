package ui;

import java.io.IOException;

import heap.IHeap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Client;

public class QueueController {
	private ActionsController actions;
	
    @FXML
    private ListView<String> priorityQueue;

    @FXML
    private ListView<String> normalQueue;
    
    private PrincipalWindowController principal;

    @FXML
    public void initialize() {
    }
    
    @FXML
    void attendClient(ActionEvent event) throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AttendClient.fxml"));
    	Pane root= fxmlLoader.load();
		actions= fxmlLoader.getController();
		actions.setQ(this);
    	Scene scene= new Scene(root);
    	Stage stage= new Stage();
    	stage.getIcons().add(new Image(Main.class.getResourceAsStream("bank-flat.png")));
		stage.setTitle("Attend Client");
		stage.setResizable(false);
		stage.setScene(scene);
		stage.show();
    }

    @FXML
    void back(ActionEvent event) {
    	
    }
    
    public void getNormalClientSelected() {
    	
    	normalQueue.getSelectionModel().getSelectedIndex();	
    }
    
    public void getPriorityClientSelected() {
    	priorityQueue.getSelectionModel().getSelectedIndex();
    }
    
    public void getNormalQueue() {
    	for (Client client : principal.getBank().getClientQueue()) {
			normalQueue.getItems().add(client.getName());
		}
    }
    
    public void getPriorityQueue() {
    	IHeap<Client> h= new IHeap<Client>(100, true);
    	h= principal.getBank().getClientHeap();
    	while(!h.isEmpty()) {
    		priorityQueue.getItems().add(h.extract().getName());
    	}
    }
    
    public void setPrincipal(PrincipalWindowController principal) {
		this.principal = principal;
	 	getPriorityQueue();
    	getNormalQueue();
	}
}
