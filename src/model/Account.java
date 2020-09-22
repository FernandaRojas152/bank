package model;

import java.time.LocalDate;

/**
 * Represents an account owned by a client
 * @author usuario
 *
 */

public class Account {
	
	private double amount;
	private String accountNumber;
	private LocalDate cancelationDate;
	private String cancelationComments;
	
	public Account(double amount, String accountNumber) {
		super();
		this.amount = amount;
		this.accountNumber = accountNumber;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public LocalDate getCancelationDate() {
		return cancelationDate;
	}

	public void setCancelationDate(LocalDate cancelationDate) {
		this.cancelationDate = cancelationDate;
	}

	public String getCancelationComments() {
		return cancelationComments;
	}

	public void setCancelationComments(String cancelationComments) {
		this.cancelationComments = cancelationComments;
	}
}
