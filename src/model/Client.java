package model;

import java.time.LocalDate;

/**
 * Represents a client at the bank
 * @author usuario
 *
 */

public class Client implements Comparable<Client> {
	
	public final String BLIND = "Blind";
	public final String DISABLED = "Disabled";
	public final String PREGNANT = "Pregnant";
	public final String ELDER = "Elder";
	public final String BABYINARMS = "Baby in arms";
	public final String NORMAL = "Normal";

	private String name;
	private String iD;
	private String cardNumber;
	private LocalDate paymentDueDate;
	private LocalDate memberSinceDate;
	private Account account;
	private String priority;
	private Double cardAmount;

	public Client(String name, String iD, String cardNumber, LocalDate paymentDueDate, LocalDate memberSinceDate,
			Account account, String priority, Double cardAmount) {
		super();
		this.name = name;
		this.iD = iD;
		this.cardNumber = cardNumber;
		this.paymentDueDate = paymentDueDate;
		this.memberSinceDate = memberSinceDate;
		this.account = account;
		this.priority = priority;
		this.cardAmount = cardAmount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getiD() {
		return iD;
	}

	public void setiD(String iD) {
		this.iD = iD;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public LocalDate getPaymentDueDate() {
		return paymentDueDate;
	}

	public void setPaymentDueDate(LocalDate paymentDueDate) {
		this.paymentDueDate = paymentDueDate;
	}

	public LocalDate getMemberSinceDate() {
		return memberSinceDate;
	}

	public void setMemberSinceDate(LocalDate memberSinceDate) {
		this.memberSinceDate = memberSinceDate;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
	
	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public Double getCardAmount() {
		return cardAmount;
	}

	public void setCardAmount(Double cardAmount) {
		this.cardAmount = cardAmount;
	}
	
	public String getClientData() {
		return name+", "+iD+", "+cardNumber+", "+paymentDueDate.toString()+", "+memberSinceDate.toString()
		+", "+account.getAccountNumber()+", "+account.getAmount()+", "+priority+", "+cardAmount+", "+account.getCancelationDate()
		+", "+account.getCancelationComments();
	}
	
	@Override
	public String toString() {
		return name+" "+iD+" "+account.getAccountNumber()+" "+cardNumber+" "+paymentDueDate.toString()+" "+memberSinceDate.toString();
	}
	
	@Override
	public int compareTo(Client client) {
		// TODO Auto-generated method stub
		return this.getPriorityValue()-client.getPriorityValue();
	}

	public int getPriorityValue() {
		
		int priorityValue;

		if(priority.equalsIgnoreCase(BLIND))
			priorityValue = 6;
		else if(priority.equalsIgnoreCase(DISABLED))
			priorityValue = 5;
		else if(priority.equalsIgnoreCase(PREGNANT))
			priorityValue = 4;
		else if(priority.equalsIgnoreCase(ELDER))
			priorityValue = 3;
		else if(priority.equalsIgnoreCase(BABYINARMS))
			priorityValue = 2;
		else 
			priorityValue = 1;
		
		return priorityValue;
	}
}