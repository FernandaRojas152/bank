package ui;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Bank;
import model.Client;

public class ActionsController {
	private Client client;
	private QueueController q;
	private PrincipalWindowController principal;
	private LocalDate local;
	
	@FXML
	private Label clientName;

	@FXML
	private ToggleGroup actions;

	@FXML
	private RadioButton consignment;

	@FXML
	private RadioButton withdraw;

	@FXML
	private RadioButton cardPayment;

	@FXML
	private RadioButton cancellation;

	@FXML
	public void initialize() {
		clientName.setText(getActualClient().getName());
		
	}

	@FXML
	void makeAction(ActionEvent event) throws IOException {
		if(consignment.isSelected()) {
			consignment();
		}else if(withdraw.isSelected()) {
			withdraw();
		}else if(cancellation.isSelected()) {
			cancelation();
		}else if (cardPayment.isSelected()) {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CardPayment.fxml"));
			Scene scene= new Scene(fxmlLoader.load());
			Stage stage= new Stage();
			stage.getIcons().add(new Image(Main.class.getResourceAsStream("bank-flat.png")));
			stage.setTitle("Card Payment");
			stage.setScene(scene);
			stage.show();
		}
	}

	@FXML
	void back(ActionEvent event) {

	}

	public void consignment() {
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Please input the consignment amount");
		Optional<String> result = dialog.showAndWait();
		Double consignment= Double.valueOf(result.get());
		principal.getBank().deposit(getActualClient(), consignment);

	}

	public void withdraw() {
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Please input the withdraw amount");
		dialog.setHeaderText("You cannot input an amount bigger than your account amount");
		Optional<String> result = dialog.showAndWait();
		Double withdraw= Double.valueOf(result.get());
		principal.getBank().withdraw(getActualClient(), withdraw);
	}

	public void cancelation() throws IOException {
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("We're sad to see you go");
		dialog.setHeaderText("In case you want to come back, we will bring you back!");
		dialog.setContentText("Please input the comments:");
		Optional<String> result = dialog.showAndWait();
		principal.getBank().cancelAccount(getActualClient(), local, result.get());
	}

	public void setQ(QueueController q) {
		this.q = q;
	}
	
	public Client getActualClient() {
		if(q.getNormalClientSelected().equals(principal.getBank().getClientQueue().peek().getT().getName())) {
			client= principal.getBank().getClientQueue().dequeue().getT();
		}else if(q.getPriorityClientSelected().equals(principal.getBank().getClientHeap().max().getName())) {
			client= principal.getBank().getClientHeap().extract();
		}
		return client;
	}

	public void setPrincipal(PrincipalWindowController principal) {
		this.principal = principal;
	}
	
}
