package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

public class Main {
	
	public static void main(String[] args) {
		
		Bank bank = new Bank();
		BufferedReader br;
		BufferedReader br2;
		try {
			br = new BufferedReader(new FileReader(new File("C:\\Users\\usuario\\eclipse-workspace\\bank\\resources\\database")));
			br2 = new BufferedReader(new FileReader(new File("C:\\Users\\usuario\\eclipse-workspace\\bank\\resources\\canceledAccounts")));
			
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
				bank.getClientStack().Ipush(new Client(dataArray[0], dataArray[1], dataArray[2], LocalDate.parse(dataArray[3]), 
						LocalDate.parse(dataArray[4]), a, dataArray[7], Double.parseDouble(dataArray[8])));
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
		
//		List<Client> clients = bank.getClientList();
		
//		//Sorting methods used over an arrayList
//		
//		bank.sortClientsByName();
//		bank.sortClientsByID();
//		bank.sortClientsByTime();
//		bank.sortClientsByAmount();
//		
//		for (Client client : clients) {
//			System.out.println(client.getName());
//			System.out.println(client.getiD());
//			System.out.println(client.getMemberSinceDate().toString());
//			System.out.println(client.getAccount().getAmount());
//		}
//		
//		//Search function 
//		
//		System.out.println(bank.searchClient("5918260347"));
		
		//Deposit/Withdraw
		
		Client client = bank.getClientQueue().peek().getT();
//		bank.deposit(client, 1000.0);
//		try {
//			bank.withdraw(client, 1000.0);
//		} catch (Exception e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		
		//Cancel account/Undo cancel account
		try {
//			bank.cancelAccount(client, LocalDate.now(), "qewqe");
			bank.undo();
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}