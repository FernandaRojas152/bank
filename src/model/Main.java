package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.NoSuchElementException;

public class Main {
	
	public static void main(String[] args) {
		
		DateTimeFormatter f = DateTimeFormatter.ofPattern("dd-MM-uuuu");
		Bank bank = new Bank();
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(new File("C:\\Users\\usuario\\eclipse-workspace\\bank\\resources\\database")));
			
			String data = br.readLine();
			
			while(data!=null) {
				
				String[] dataArray = data.split(", ");
				Account a = new Account((double)Integer.parseInt(dataArray[6]), dataArray[5]);
				bank.fillClientData(dataArray[0], dataArray[1], dataArray[2], LocalDate.parse(dataArray[3], f), 
						LocalDate.parse(dataArray[4], f), a, dataArray[7], (double)Integer.parseInt(dataArray[8]));
				data = br.readLine();
			}
			br.close();
			
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
		bank.deposit(client, 1000.0);
		try {
			bank.withdraw(client, 2000.0);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println(client.getAccount().getAmount());
		
		//Cancel account
		try {
			bank.cancelAccount(client, LocalDate.parse(LocalDate.now().format(f), f), "qweqweq");
			
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
