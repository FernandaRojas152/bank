package ui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Account;
import model.Bank;
import model.Client;

public class PrincipalWindowController {
	private Bank bank;
	
	@FXML
	private ToggleGroup options;

	@FXML
	private RadioButton queue;

	@FXML
	private RadioButton information;

	public PrincipalWindowController() {
		bank= new Bank();
	}

	public void initialize() {
		queue.setToggleGroup(options);
		information.setToggleGroup(options);
		Bank bank = new Bank();
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
	void start(ActionEvent event) throws IOException {
		if(queue.isSelected()) {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ClientQueue.fxml"));
			Scene scene= new Scene(fxmlLoader.load());
			Stage stage= new Stage();
			stage.getIcons().add(new Image(Main.class.getResourceAsStream("bank-flat.png")));
			stage.setTitle("Waiting line");
			stage.setScene(scene);
			stage.show();
		}else if(information.isSelected()) {
			TextInputDialog dialog = new TextInputDialog();
			dialog.setTitle("Search Client");
			dialog.setContentText("Please input client's id:");
			Optional<String> result = dialog.showAndWait();
			System.out.println(bank.searchClient("1098577498"));
			if(!result.get().isEmpty()) {
				try{
					bank.searchClient(result.get());
					FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ClientInformation.fxml"));
					Scene scene= new Scene(fxmlLoader.load());
					Stage stage= new Stage();
					stage.getIcons().add(new Image(Main.class.getResourceAsStream("bank-flat.png")));
					stage.setTitle("Client Information");
					stage.setScene(scene);
					stage.show();
				}catch(Exception e) {
					e.printStackTrace();
					e.getMessage();
				}
				
			}
		}
	}

}
