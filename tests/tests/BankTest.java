package tests;

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

import customException.EmptyStackException;
import model.Bank;
import model.Client;
import model.Account;

class BankTest {
	
	private Bank bank;
	
	//Stages
	
	public void setUpStage() {
		bank = new Bank();
		try {
			Account account = new Account(Double.parseDouble("4300000.0"), "0692516265");
			bank.fillClientData("Lester Sanders", "4740430425", "6011-7241-9420-8723", LocalDate.parse("2020-10-29"), 
					LocalDate.parse("2013-04-16"), account, "Normal", Double.parseDouble("1200000.0"));
			
			Account account2 = new Account(Double.parseDouble("12000.0"), "694759301080");
			bank.fillClientData("Amie Mckenzie", "4394423910", "2347-0110-3420-0815", LocalDate.parse("2020-10-01"), 
					LocalDate.parse("2010-01-08"), account2, "Baby in arms", Double.parseDouble("40000.0"));
			
			Account account3 = new Account(Double.parseDouble("7129000.0"), "0991022");
			bank.fillClientData("Gaston Vasquez", "8958683531", "2320-4450-8710-6833", LocalDate.parse("2020-10-03"), 
					LocalDate.parse("2008-07-05"), account3, "Normal", Double.parseDouble("0.0"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setUpStage2() {
		bank = new Bank();
		try {
			Account account = new Account(Double.parseDouble("4300000.0"), "0692516265");
			bank.fillCanceledClientData("Lester Sanders", "4710430425", "6011-7241-9420-8723", LocalDate.parse("2020-10-29"), 
					LocalDate.parse("2013-04-16"), account, "Normal", Double.parseDouble("1200000.0"), LocalDate.now(), "qwerty");
			
			Account account2 = new Account(Double.parseDouble("12000.0"), "694759301080");
			bank.fillCanceledClientData("Amie Mckenzie", "4394463910", "6011-7241-9420-8723", LocalDate.parse("2020-10-01"), 
					LocalDate.parse("2010-01-08"), account2, "Baby in arms", Double.parseDouble("40000.0"), LocalDate.now(), "qwerty");
			
			Account account3 = new Account(Double.parseDouble("7129000.0"), "0991022");
			bank.fillCanceledClientData("Gaston Vasquez", "8958683031", "2320-4450-8710-6833", LocalDate.parse("2020-10-03"), 
					LocalDate.parse("2008-07-05"), account3, "Normal", Double.parseDouble("0.0"), LocalDate.now(), "qwerty");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Tests
	
	@Test
	public void testFillClientData() {
		setUpStage();
		for (Client client : bank.getClientList()) {
			assertNotNull(client);
		}
	}

	@Test
	public void testFillCanceledClientData() {
		setUpStage2();
		for (Client client : bank.getClientStack()) {
			assertNotNull(client);
		}
	}
	
	@Test
	public void testDeposit() {
		setUpStage();
		Client client = bank.getClientQueue().peek().getT();
		Double deposit = 100000.0;
		Double accountAmount = client.getAccount().getAmount()+deposit;
		bank.deposit(client, deposit);
		assertEquals(accountAmount, client.getAmount());
	}
	
	@Test
	public void testWithdraw() {
		setUpStage();
		Client client = bank.getClientQueue().peek().getT();
		Double withdrawal = 100000.0;
		Double accountAmount = client.getAmount()-withdrawal;
		bank.withdraw(client, withdrawal);
		assertEquals(accountAmount, client.getAmount());
	}
	
	@Test
	public void testWithdraw2() {
		setUpStage();
		Client client = bank.getClientQueue().peek().getT();
		Double withdrawal = 10000000.0;
		try {
			bank.withdraw(client, withdrawal);
		}catch (RuntimeException e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void testPayCardAmount() {
		setUpStage();
		Client client = bank.getClientQueue().peek().getT();
		Double cash = 10000000.0;
		Double cardAmount = client.getCardAmount()-cash;
		bank.payCardAmount(client, cash);
		assertTrue(cardAmount <= client.getCardAmount());
	}
	
	@Test
	public void testPayCardAmount2() {
		setUpStage();
		Client client = bank.getClientList().get(0);
		Double accountBalance = client.getAmount();
		Double cardAmount = client.getCardAmount()-accountBalance;
		bank.payCardAmount(client);
		assertTrue(cardAmount <= client.getCardAmount());
	}
	
	@Test
	public void testPayCardAmount3() {
		setUpStage();
		Client client = bank.getClientList().get(1);
		try {
			bank.payCardAmount(client);
		}catch (RuntimeException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCancelAccount() {
		setUpStage();
		Client client = bank.getClientQueue().peek().getT();
		bank.cancelAccount(client, LocalDate.now(), "qwerty");
		try {
			assertEquals(client, bank.getClientStack().peek());
		} catch (EmptyStackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUndo() {
		setUpStage2();
		try {
			Client client = bank.getClientStack().peek();
			bank.undo();
			assertEquals(client, bank.getClientList().get(0));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void searchClient() {
		setUpStage();
		Client client = bank.getClientList().get(0);
		String ID = "4740430425";
		Client searchedClient = bank.searchClient(ID);
		assertEquals(client, searchedClient);
	}
	
	@Test
	public void sortClientsByName() {
		setUpStage();
		bank.sortClientsByName();
		for (int i = 0; i+1 < bank.getClientList().size(); i++) {
			Client client = bank.getClientList().get(i);
			Client client2 = bank.getClientList().get(i+1);
			assertTrue(client.getName().compareTo(client2.getName()) < 0);
		}
	}
	
	@Test
	public void sortClientsByAmount() {
		setUpStage();
		bank.sortClientsByAmount();
		for (int i = 0; i+1 < bank.getClientList().size(); i++) {
			Client client = bank.getClientList().get(i);
			Client client2 = bank.getClientList().get(i+1);
			assertTrue(client.getAmount() < client2.getAmount());
		}
	}
	
	@Test
	public void sortClientsByTime() {
		setUpStage();
		bank.sortClientsByTime();
		for (int i = 0; i+1 < bank.getClientList().size(); i++) {
			Client client = bank.getClientList().get(i);
			Client client2 = bank.getClientList().get(i+1);
			assertTrue(client.getMemberSinceDate().compareTo(client2.getMemberSinceDate()) < 0);
		}
	}
	
	@Test
	public void sortClientsByID() {
		setUpStage();
		bank.sortClientsByID();
		for (int i = 0; i+1 < bank.getClientList().size(); i++) {
			Client client = bank.getClientList().get(i);
			Client client2 = bank.getClientList().get(i+1);
			assertTrue(client.getId().compareTo(client2.getId()) < 0);
		}
	}
}
