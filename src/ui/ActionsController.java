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
    private TextField tfComments;
	
	private QueueController q;
	private PrincipalWindowController principal;
	private Client client;
	
	@FXML
	public void initialize() {
		consignment.setToggleGroup(actions);
		withdraw.setToggleGroup(actions);
		cardPayment.setToggleGroup(actions);
		cancellation.setToggleGroup(actions);
	}

	@FXML
	void makeAction(ActionEvent event) throws IOException {
		
		if(consignment.isSelected()) {
			consignment();
		}else if(withdraw.isSelected()) {
			withdraw();
		}else if(cancellation.isSelected()) {
			cancelation(client, LocalDate.now(), tfComments.getText());
		}else if (cardPayment.isSelected()) {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CardPayment.fxml"));
			Scene scene= new Scene(fxmlLoader.load());
			Stage stage= new Stage();
			stage.getIcons().add(new Image(Main.class.getResourceAsStream("bank-flat.png")));
			stage.setTitle("Actions");
			stage.setScene(scene);
			stage.show();
		}
	}
	
	@FXML
	public void back(ActionEvent event) {
		Stage stage = (Stage) btnBack.getScene().getWindow();
		stage.close();
	}

	public void consignment() {
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Please input the consignment amount");
		Optional<String> result = dialog.showAndWait();
		Double consignment= Double.valueOf(result.get());
		principal.getBank().deposit(q.getCurrentClient(), consignment);
	}

	public void withdraw() {
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Please input the withdraw amount");
		dialog.setHeaderText("You cannot input an amount bigger than your account amount");
		Optional<String> result = dialog.showAndWait();
		Double withdraw= Double.valueOf(result.get());
		principal.getBank().withdraw(q.getCurrentClient(), withdraw);
	}
	
	public void cancelation(Client client, LocalDate cancelationDate, String cancelationComments) throws IOException {
		
		if(!tfComments.getText().equals("")) {
			
			principal.getBank().cancelAccount(client, cancelationDate, cancelationComments);
			if(client.getPriority().equals(client.NORMAL))
				principal.getBank().next();
			else
				principal.getBank().priorityNext();
			
			Stage stage = (Stage) btnBack.getScene().getWindow();
			stage.close();
			q.updateQueues();
			
		}else 
			JOptionPane.showMessageDialog(null, "Please! State the reason why you are cancelling your account.");
	}

	public void setQ(QueueController q) {
		this.q = q;
	}
	
	public void setPrincipal(PrincipalWindowController principal) {
		this.principal = principal;
	}
	
	public void setClient(Client client) {
		this.client = client;
		clientName.setText(client.getName());
	}
}
