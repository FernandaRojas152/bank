package ui;

import java.io.IOException;
import javax.swing.JOptionPane;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CardPaymentController {	
	
	@FXML
	private Button btnBack;
	
	@FXML
	private Button btnPayCash;
	
	@FXML
	private Button btnPayAccountBalance;

	@FXML
	private TextField cashAmount;
	
	@FXML
	private TextField changeAmount;

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
	void payCash(ActionEvent event) {
		try {
			Double change = principalWindowController.getBank().payCardAmount(actionsController.client(), Double.parseDouble(cashAmount.getText()));
			changeAmount.setText(change+"");
			btnPayCash.setDisable(true);
			btnPayAccountBalance.setDisable(true);	
			cashAmount.setDisable(true);
		}catch (RuntimeException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	@FXML
	void payAccountAmount(ActionEvent event) throws IOException {
		try {
			principalWindowController.getBank().payCardAmount(actionsController.client());
			accountAmount.setText(actionsController.client().getAccount().getAmount()+"");
			btnPayCash.setDisable(true);
			btnPayAccountBalance.setDisable(true);	
			cashAmount.setDisable(true);
			changeAmount.setDisable(true);
		}catch (RuntimeException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	@FXML
	public void back(ActionEvent event) {
		Stage stage = (Stage) btnBack.getScene().getWindow();
		stage.close();
		actionsController.getStage().show();
	}
	
	public void setCardAmount() {
		totalAmount.setText(String.valueOf(actionsController.client().getCardAmount()));
	}
	
	public void setAccountBalance() {
		accountAmount.setText(String.valueOf(actionsController.client().getAccount().getAmount()));
	}

	public void setActionsController(ActionsController actionsController) {
		this.actionsController = actionsController;
	}	

	public void setPrincipal(PrincipalWindowController principalWindowController) {
		this.principalWindowController = principalWindowController;
	}
}
