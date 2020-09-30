package ui;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;
import javax.swing.JOptionPane;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Client;

public class ActionsController {
	
	@FXML
	private Label clientName; 
	
	@FXML
	private TextField tfAccountBalance;
	
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
    private Button btnBack;
    
    @FXML
    private Button btAccept;
    
    @FXML
    private TextField tfComments;
	
	private QueueController queueController;
	private PrincipalWindowController principal;
	private CardPaymentController cardPaymentController;
	private Client client;
	
	@FXML
	public void initialize() {
		consignment.setToggleGroup(actions);
		withdraw.setToggleGroup(actions);
		cardPayment.setToggleGroup(actions);
		cancellation.setToggleGroup(actions);
	}
	
	@FXML
	public void makeAction(ActionEvent event) throws IOException {
		if(consignment.isSelected()) {
			consignment();
		}else if (withdraw.isSelected()) {
			withdraw();
		}else if (cancellation.isSelected()) {
			cancelation(client, LocalDate.now(), tfComments.getText());
		}else if (cardPayment.isSelected()) {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CardPayment.fxml"));
			Scene scene= new Scene(fxmlLoader.load());
			Stage stage= new Stage();
			cardPaymentController = fxmlLoader.getController();
			cardPaymentController.setActionsController(this);
			cardPaymentController.setPrincipal(principal);
			cardPaymentController.setCardAmount();
			cardPaymentController.setAccountBalance();
			stage.getIcons().add(new Image(Main.class.getResourceAsStream("bank-flat.png")));
			stage.setTitle("Actions");
			stage.setScene(scene);
			stage.show();
			stage = (Stage) btnBack.getScene().getWindow();
			stage.hide();
		}
	}
	
	@FXML
	public void back(ActionEvent event) {
		Stage stage = (Stage) btnBack.getScene().getWindow();
		stage.close();
		queueController.getStage().show();
	}
	
	public void consignment() {
		try {
			TextInputDialog dialog = new TextInputDialog();
			dialog.setTitle("Please enter the consignment amount.");
			Optional<String> result = dialog.showAndWait();
			Double consignment= Double.valueOf(result.get());
			principal.getBank().deposit(client, consignment);
			tfAccountBalance.setText(client.getAccount().getAmount()+"");
		}catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Invalid Entry");
		}catch (RuntimeException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	public void withdraw() {
		try {
			TextInputDialog dialog = new TextInputDialog();
			dialog.setTitle("Please enter your withdrawal.");
			dialog.setHeaderText("You cannot enter an amount greater than your account balance.");
			Optional<String> result = dialog.showAndWait();
			Double withdraw= Double.valueOf(result.get());
			principal.getBank().withdraw(client, withdraw);
			tfAccountBalance.setText(client.getAccount().getAmount()+"");
		}catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Invalid Entry");
		}catch (RuntimeException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	public void cancelation(Client client, LocalDate cancelationDate, String cancelationComments) throws IOException {
		if(!tfComments.getText().equals("")) {
			principal.getBank().cancelAccount(client, cancelationDate, cancelationComments);
			next();
		}else 
			JOptionPane.showMessageDialog(null, "Please! State the reason why you are cancelling your account.");
	}
	
	@FXML
	public void next() {
		if(client.getPriority().equals(client.NORMAL))
			principal.getBank().next();
		else
			principal.getBank().priorityNext();
		Stage stage = (Stage) btnBack.getScene().getWindow();
		stage.close();
		queueController.updateQueues();
		queueController.getStage().show();
	}

	public void setQueueController(QueueController q) {
		this.queueController = q;
	}

	public void setPrincipal(PrincipalWindowController principal) {
		this.principal = principal;
	}
	
	public void setClient(Client client) {
		this.client = client;
		clientName.setText(client.getName());
	}
	
	public Client getClient() {
		return client;
	}

	public Stage getStage() {
		return (Stage) btnBack.getScene().getWindow();
	}

	public void setAccountBalance(double amount) {
		tfAccountBalance.setText(amount+"");
	}
}
