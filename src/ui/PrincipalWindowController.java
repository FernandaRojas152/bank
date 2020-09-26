package ui;

import java.io.IOException;
import java.util.Optional;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class PrincipalWindowController {
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
			stage.setScene(scene);
			stage.show();
		}else if(information.isSelected()) {
			TextInputDialog dialog = new TextInputDialog();
			dialog.setTitle("Search Client");
			dialog.setContentText("Please input client's id:");
			Optional<String> result = dialog.showAndWait();
			if(!result.get().isEmpty()) {
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ClientInformation.fxml"));
				Scene scene= new Scene(fxmlLoader.load());
				Stage stage= new Stage();
				stage.getIcons().add(new Image(Main.class.getResourceAsStream("bank-flat.png")));
				stage.setTitle("Client Information");
				stage.setScene(scene);
				stage.show();
			}

		}

	}

}
