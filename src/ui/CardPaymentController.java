package ui;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
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
	private TextField amountMoney;

	@FXML
	private Label total;

	@FXML
	private Button btnBack;
	
	@FXML
    private Label accountAmount;

    @FXML
    private Label totalAmount;

	public ActionsController actionsController;

	@FXML
	public void initialize() {
		
	}

	@FXML
	void payAmount(ActionEvent event) throws IOException {
		load("payAmountAccount");
		totalAmount.setText(actionsController.client().getCardNumber());
		accountAmount.setText(String.valueOf(actionsController.client().getAmount()));
	}

	@FXML
	void payCash(ActionEvent event) throws IOException {
		load("payCash");
		System.out.println();
		total.setText("Hola");
	}

	private void load(String name) throws IOException {
		Parent root = null;
		try {
			root= FXMLLoader.load(getClass().getResource(name+".fxml"));
		} catch (IOException e) {
			Logger.getLogger(CardPaymentController.class.getName()).log(Level.SEVERE, null, e);
		}
		mainPane.setCenter(root);

	}

	@FXML
	void payWithCash(ActionEvent event) {
		
		if(amountMoney.getText().compareTo(String.valueOf(actionsController.client().getCardAmount()))> 0) {
			
			actionsController.getPrincipal().getBank().payCardAmount(actionsController.client(), Double.parseDouble(amountMoney.getText()));
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

	@FXML
	void payWithAmount(ActionEvent event) {
		if(amountMoney.getText().compareTo(String.valueOf(actionsController.client().getCardAmount()))> 0) {
			actionsController.getPrincipal().getBank().payCardAmount(actionsController.client());
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
	public void back(ActionEvent event) {
		Stage stage = (Stage) btnBack.getScene().getWindow();
		stage.close();
		actionsController.getStage().show();
	}

	public void setActionsController(ActionsController actionsController) {
		this.actionsController = actionsController;
	}
}
