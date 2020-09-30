package ui;

import java.io.IOException;
import heap.IHeap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
    
    @FXML
    private Button btnBack;
    
    private PrincipalWindowController principal;

    @FXML
    public void initialize() {
    }
    
    @FXML
    void attendClient(ActionEvent event) throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AttendClient.fxml"));
    	Pane root = fxmlLoader.load();
		actions= fxmlLoader.getController();
		actions.setPrincipal(principal);
		actions.setQ(this);
		actions.setClient(principal.getBank().getClientQueue().peek().getT());
    	Scene scene= new Scene(root);
    	Stage stage= new Stage();
    	stage.getIcons().add(new Image(Main.class.getResourceAsStream("bank-flat.png")));
		stage.setTitle("Attend Client");
		stage.setResizable(false);
		stage.setScene(scene);
		stage.show();
		stage = (Stage) btnBack.getScene().getWindow();
		stage.hide();
    }
    
    @FXML
    void attendPriorityClient(ActionEvent event) throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AttendClient.fxml"));
    	Pane root = fxmlLoader.load();
		actions= fxmlLoader.getController();
		actions.setPrincipal(principal);
		actions.setQ(this);
		actions.setClient(principal.getBank().getClientHeap().max());
    	Scene scene= new Scene(root);
    	Stage stage= new Stage();
    	stage.getIcons().add(new Image(Main.class.getResourceAsStream("bank-flat.png")));
		stage.setTitle("Attend Client");
		stage.setResizable(false);
		stage.setScene(scene);
		stage.show();
		stage = (Stage) btnBack.getScene().getWindow();
		stage.hide();
    }
    
	public void back(ActionEvent event) {
		Stage stage = (Stage) btnBack.getScene().getWindow();
		stage.close();
		principal.getStage().show();
	}
    
    public void getNormalQueue() {
    	normalQueue.getItems().clear();
    	for (Client client : principal.getBank().getClientQueue()) {
			normalQueue.getItems().add(client.getName());
		}
    }
    
    public void getPriorityQueue() {
    	priorityQueue.getItems().clear();
    	IHeap<Client> aux = principal.getBank().getClientHeap();
    	IHeap<Client> clientHeap = new IHeap<Client>(100, true);
    	while(!aux.isEmpty()) {
    		clientHeap.insert(aux.max());
    		priorityQueue.getItems().add(aux.extract().getName());
    	}
    	principal.getBank().setHeap(clientHeap);
    }
    
    public void setPrincipal(PrincipalWindowController principal) {
		this.principal = principal;
	 	getPriorityQueue();
    	getNormalQueue();
	 	updateQueues();
	}
    
    public void updateQueues() {
    	getPriorityQueue();
    	getNormalQueue();
    }
    
    public Stage getStage() {
    	return (Stage)btnBack.getScene().getWindow();
    }
}