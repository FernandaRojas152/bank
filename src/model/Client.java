package model;

import java.time.LocalDate;

/**
 * Represents a client at the bank
 * @author usuario
 *
 */

public class Client {
	
	private String name;
	private String iD;
	private String cardNumber;
	private LocalDate paymentDueDate;
	private LocalDate memberSinceDate;
	private Account account;

	public Client(String name, String iD, String cardNumber, LocalDate paymentDueDate, LocalDate memberSinceDate,
			Account account) {
		super();
		this.name = name;
		this.iD = iD;
		this.cardNumber = cardNumber;
		this.paymentDueDate = paymentDueDate;
		this.memberSinceDate = memberSinceDate;
		this.account = account;
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
	
	@Override
	public String toString() {
		return name+" "+iD+" "+account.getAccountNumber()+" "+cardNumber+" "+paymentDueDate.toString()+" "+memberSinceDate.toString();
	}
}
