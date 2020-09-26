package ui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;
import model.Bank;

public class PrincipalWindowController {
	public PrincipalWindowController() {
		// TODO Auto-generated constructor stub
	}
	
    @FXML
    private RadioButton queue;

    @FXML
    private RadioButton information;

    @FXML
    void start(ActionEvent event) throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ClientQueue.fxml"));
    	Scene scene= new Scene(fxmlLoader.load());
    	Stage stage= new Stage();
		stage.setTitle("Waiting line");
		stage.setScene(scene);
		stage.show();
    }

}
