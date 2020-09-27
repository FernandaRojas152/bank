package ui;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Client;

public class PrincipalWindowController {
	private Client client;

	@FXML
	private ToggleGroup options;

	@FXML
	private RadioButton queue;

	@FXML
	private RadioButton information;

	public PrincipalWindowController() {
	}

	public void initialize() {
		queue.setToggleGroup(options);
		information.setToggleGroup(options);	
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
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ClientInformation.fxml"));
			Scene scene= new Scene(fxmlLoader.load());
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

}