package ui;

import java.io.IOException;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class CardPaymentController {	
	@FXML
	private BorderPane mainPane;

	@FXML
	private Button btnBack;

	@FXML
	private TextField amountMoney;

	@FXML
	private Label accountAmount;

	@FXML
	private Label totalAmount;

	public PrincipalWindowController principalWindowController;
	public ActionsController actionsController;

	@FXML
	public void initialize() {
	}


	@FXML
	void payAmount(ActionEvent event) throws IOException {
		if(amountMoney.getText().compareTo(String.valueOf(actionsController.client().getCardAmount()))< 0) {
			principalWindowController.getBank().payCardAmount(actionsController.client());
			Platform.runLater(() -> {
				Alert dialog2 = new Alert(AlertType.INFORMATION, "You have pay successfully your cards", ButtonType.OK);
				dialog2.show();
			});
		}else {
			Platform.runLater(() -> {
				Alert dialog2 = new Alert(AlertType.ERROR, "Your amount is lower than your debt", ButtonType.OK);
				dialog2.show();
			});
		}
	}

	@FXML
	void payCash(ActionEvent event) throws IOException {
		if(amountMoney.getText().compareTo(String.valueOf(actionsController.client().getCardAmount()))< 0) {
			principalWindowController.getBank().payCardAmount(actionsController.client(), Double.parseDouble(amountMoney.getText()));
			Platform.runLater(() -> {
				Alert dialog2 = new Alert(AlertType.INFORMATION, "You have pay successfully your cards", ButtonType.OK);
				dialog2.show();
			});
		}else {
			Platform.runLater(() -> {
				Alert dialog2 = new Alert(AlertType.ERROR, "Your amount of cash is lower than your debt", ButtonType.OK);
				dialog2.show();
			});
		}
	}
	
	
	public void back(ActionEvent event) {
		Stage stage = (Stage) btnBack.getScene().getWindow();
		stage.close();
		actionsController.getStage().show();
	}
	
	public void changeText() {
		totalAmount.setText(String.valueOf(actionsController.client().getCardAmount()));
	}

	public void setActionsController(ActionsController actionsController) {
		this.actionsController = actionsController;
	}	

	public void setPrincipal(PrincipalWindowController principalWindowController) {
		this.principalWindowController = principalWindowController;
	}
}
