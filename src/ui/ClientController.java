package ui;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Client;

public class ClientController {
	
	private PrincipalWindowController principal;

	@FXML
	private Label name;

	@FXML
	private Label idClient;

	@FXML
	private Label cardNumber;

	@FXML
	private Label account;

	@FXML
	private TextField clientSearched;

	@FXML
	private Label cardPayment;

	@FXML
	private Label bounding;
	
	@FXML
	private Button btnBack;

	@FXML
	public void initialize() {
	}

	@FXML
	public void back(ActionEvent event) {
		Stage stage = (Stage) btnBack.getScene().getWindow();
		stage.close();
		principal.getStage().show();
	}
	
	@FXML
	public void searchClient(ActionEvent event) {
		try {
			Client client = principal.searchClient(clientSearched.getText());
			name.setText(client.getName());
			idClient.setText(client.getId());
			account.setText(client.getAccount().getAccountNumber());
			cardNumber.setText(client.getCardNumber());
			cardPayment.setText(client.getPaymentDueDate().toString());
			bounding.setText(client.getMemberSinceDate().toString());
		}catch(NullPointerException e) {
			JOptionPane.showMessageDialog(null, "Client not found.");
		}
	}
	
	public void setPrincipal(PrincipalWindowController principal) {
		this.principal = principal;
	}
}
