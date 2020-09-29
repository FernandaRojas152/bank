package ui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.Account;
import model.Bank;
import model.Client;

public class PrincipalWindowController {
	
	private Bank bank;
	private ClientController clientController;
	private CurrentClientsController currentClientsController;
	private CanceledClientsController canceledClientsController;
	private QueueController queueController;

	@FXML
	private ToggleGroup options;

	@FXML
	private RadioButton queue;

	@FXML
	private RadioButton information;
	
	@FXML
	private Button btDatabase;
	
	@FXML
	private Button btCanceledClients;
	
	@FXML
	private Button btStart;
	
	@FXML
	public void initialize() {
		bank = new Bank();
		loadData();
		queue.setToggleGroup(options);
		information.setToggleGroup(options);
	}

	@FXML
	void start(ActionEvent event) throws IOException {
		if(queue.isSelected()) {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ClientQueue.fxml"));
			Pane root= fxmlLoader.load();
			queueController = fxmlLoader.getController();
			queueController.setPrincipal(this);
			Scene scene= new Scene(root);
			Stage stage= new Stage();
			stage.getIcons().add(new Image(Main.class.getResourceAsStream("bank-flat.png")));
			stage.setTitle("Waiting line");
			stage.setResizable(false);
			stage.setScene(scene);
			stage.show();
		}else if(information.isSelected()) {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ClientInformation.fxml"));
			Pane root= fxmlLoader.load();
			clientController = fxmlLoader.getController();
			clientController.setPrincipal(this);
			Scene scene= new Scene(root);
			Stage stage= new Stage();
			stage.getIcons().add(new Image(Main.class.getResourceAsStream("bank-flat.png")));
			stage.setTitle("Client Information");
			stage.setResizable(false);
			stage.setScene(scene);
			stage.show();
		}
		Stage stage = (Stage) information.getScene().getWindow();
		stage.hide();
	}
	
    @FXML
    void canceledClients(ActionEvent event) throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CancelledClients.fxml"));
    	Pane root = fxmlLoader.load();
    	canceledClientsController = fxmlLoader.getController();
    	canceledClientsController.setPrincipal(this);
		Scene scene= new Scene(root);
		Stage stage= new Stage();
		stage.getIcons().add(new Image(Main.class.getResourceAsStream("bank-flat.png")));
		stage.setTitle("Client Information");
		stage.setResizable(false);
		stage.setScene(scene);
		stage.show();
		btDatabase.setDisable(true);
		btCanceledClients.setDisable(true);
		btStart.setDisable(true);
		stage.setOnHidden(new EventHandler<WindowEvent>() {
			
			@Override
			public void handle(WindowEvent arg0) {
				// TODO Auto-generated method stub
				btDatabase.setDisable(false);
				btCanceledClients.setDisable(false);
				btStart.setDisable(false);
			}
		});
    }

	@FXML
    void clientDatabase(ActionEvent event) throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CurrentClients.fxml"));
    	Pane root= fxmlLoader.load();
		currentClientsController= fxmlLoader.getController();
		currentClientsController.setPrincipal(this);
		Scene scene= new Scene(root);
		Stage stage= new Stage();
		stage.getIcons().add(new Image(Main.class.getResourceAsStream("bank-flat.png")));
		stage.setTitle("Client Information");
		stage.setResizable(false);
		stage.setScene(scene);
		stage.show();
		btDatabase.setDisable(true);
		btCanceledClients.setDisable(true);
		btStart.setDisable(true);
		stage.setOnHidden(new EventHandler<WindowEvent>() {
			
			@Override
			public void handle(WindowEvent arg0) {
				// TODO Auto-generated method stub
				btDatabase.setDisable(false);
				btCanceledClients.setDisable(false);
				btStart.setDisable(false);
			}
		});
    }
    
    public Client searchClient(String id) {
    	return bank.searchClient(id);
    }
    
    public List<Client> getClients(){
    	return bank.getClientList();
    }

    public Bank getBank() {
		return bank;
	}
    
    public void loadData() {
    	
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
    
    public Stage getStage() {
    	return (Stage) information.getScene().getWindow();
    }
}