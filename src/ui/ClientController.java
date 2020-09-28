package ui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Account;
import model.Bank;
import model.Client;

public class ClientController {
	private Bank bank;
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


	public ClientController() {
		bank= new Bank();
		principal= new PrincipalWindowController();
	}

	public void initialize() {
		
	}

	public 
	@FXML
	void back(ActionEvent event) {

	}
	
	@FXML
	void searchClient(ActionEvent event) {
		bank.data();
		client= bank.searchClient(clientSearched.getText());
		name.setText(client.getName());
		idClient.setText(client.getiD());
		account.setText(client.getAccount().getAccountNumber());
		cardNumber.setText(client.getCardNumber());
		cardPayment.setText(client.getPaymentDueDate().toString());
		bounding.setText(client.getMemberSinceDate().toString());
	}

	public PrincipalWindowController getPrincipal() {
		return principal;
	}

	public void setPrincipal(PrincipalWindowController principal) {
		this.principal = principal;
	}
}
