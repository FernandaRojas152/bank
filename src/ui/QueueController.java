package ui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class QueueController {

    @FXML
    private ListView<String> priorityQueue;

    @FXML
    private ListView<String> normalQueue;

    @FXML
    void attendClient(ActionEvent event) throws IOException {
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
