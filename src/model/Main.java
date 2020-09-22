package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Main {
	
	public static void main(String[] args) throws Exception {
		
		//LocalDate.parse(LocalDate.now().format(f)
		DateTimeFormatter f = DateTimeFormatter.ofPattern("dd-MM-uuuu");
		Bank bank = new Bank();
		
		Account a3 = new Account(2970000, "182358141820");
		bank.fillClientData("Colton Chandler", "5918260347", "4539-4545-6823-2574", LocalDate.parse("27-10-2020", f), 
				LocalDate.parse("09-01-2015", f), a3);
		
		Account a1 = new Account(670000, "15741805511");
		bank.fillClientData("Lynette Rhodes", "1098577498", "2410-2623-2263-3724", LocalDate.parse("10-10-2020", f), 
				LocalDate.parse("28-08-2017", f), a1);

		Account a2 = new Account(4300000, "0692516265");
		bank.fillClientData("Lester Sanders", "4710430425", "6011-7241-9420-8723", LocalDate.parse("29-10-2020", f), 
				LocalDate.parse("16-04-2013", f), a2);
	
		Account a5 = new Account(7200000, "0991022");
		bank.fillClientData("Gaston Vasquez", "8958683031", "2320-4450-8710-6833", LocalDate.parse("03-10-2020", f), 
				LocalDate.parse("05-07-2008", f), a5);

		Account a4 = new Account(5120000, "462512437397");
		bank.fillClientData("Chuck Li", "7003765335", "4539-3558-8813-1745", LocalDate.parse("16-10-2020", f), 
				LocalDate.parse("17-03-2012", f), a4);
		
		List<Client> clients = bank.getClientsList();
		
		//Sorting methods used over an arrayList
		
//		bank.sortClientsByName();
//		bank.sortClientsByID();
//		bank.sortClientsByTime();
		bank.sortClientsByAmount();
		
		for (Client client : clients) {
//			System.out.println(client.getName());
//			System.out.println(client.getiD());
//			System.out.println(client.getMemberSinceDate().toString());
			System.out.println(client.getAccount().getAmount());
		}
		
		//Search function 
		
//		System.out.println(bank.searchClient("4710430425"));
	}
}
