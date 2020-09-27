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
	
	public void data() {
		BufferedReader br;
		BufferedReader br2;
		try {
			br = new BufferedReader(new FileReader(new File("resources\\database.txt")));
			br2 = new BufferedReader(new FileReader(new File("resources\\canceledAccounts.txt")));

			String data = br.readLine();
			String data2 = br2.readLine();

			while(data!=null) {

				String[] dataArray = data.split(", ");
				Account a = new Account(Double.parseDouble(dataArray[6]), dataArray[5]);
				bank.fillClientData(dataArray[0], dataArray[1], dataArray[2], LocalDate.parse(dataArray[3]), 
						LocalDate.parse(dataArray[4]), a, dataArray[7], Double.parseDouble(dataArray[8]));
				data = br.readLine();
			}

			while(data2!=null) {
				String[] dataArray = data2.split(", ");
				Account a = new Account(Double.parseDouble(dataArray[6]), dataArray[5]);
				bank.fillCanceledClientData(dataArray[0], dataArray[1], dataArray[2], LocalDate.parse(dataArray[3]), 
						LocalDate.parse(dataArray[4]), a, dataArray[7], Double.parseDouble(dataArray[8]), LocalDate.parse(dataArray[9]),
						dataArray[10]);
				data2 = br2.readLine();
			}
			br.close();
			br2.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@FXML
	void searchClient(ActionEvent event) {
		data();
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
