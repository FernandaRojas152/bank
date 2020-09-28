package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Client;

public class ClientController {
	private PrincipalWindowController principal;
	private Client client;

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
	public void initialize() {
	}

	@FXML
	public void back(ActionEvent event) {

	}
	
	@FXML
	void searchClient(ActionEvent event) {
		client= principal.searchClient(clientSearched.getText());
		name.setText(client.getName());
		idClient.setText(client.getiD());
		account.setText(client.getAccount().getAccountNumber());
		cardNumber.setText(client.getCardNumber());
		cardPayment.setText(client.getPaymentDueDate().toString());
		bounding.setText(client.getMemberSinceDate().toString());
	}
	
	public void setPrincipal(PrincipalWindowController principal) {
		this.principal = principal;
	}
}
