package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import binarySearchTree.BinarySearchTree;
import hashtable.HashTable;
import queue.IQueue;
import stack.IStack;

/**
 * Represents the bank and its operations
 * @author usuario
 *
 */

public class Bank {
	
	private HashTable<String, Client> clientHashTable;
	private List<Client> clientList;
	private BinarySearchTree<Double, Client> clientTree;
	private IQueue<Client> clientQueue;
	private IStack<Client> clientStack;
	
	/**
	 * Builds a bank
	 */
	
	public Bank() {
		clientHashTable = new HashTable<String, Client>();
		clientList = new ArrayList<Client>();
		clientTree = new BinarySearchTree<Double, Client>();
		clientQueue = new IQueue<Client>();
		clientStack = new IStack<Client>();
	}
	
	/**
	 * Fills the bank's data structures with new client data
	 * @param name
	 * @param iD
	 * @param cardNumber
	 * @param paymentDueDate
	 * @param memberSinceDate
	 * @throws Exception 
	 */
	
	public void fillClientData(String name, String iD, String cardNumber, LocalDate paymentDueDate,
							   LocalDate memberSinceDate, Account account, String priority, Double cardAmount) throws Exception {
		
		Client client = new Client(name, iD, cardNumber, paymentDueDate, memberSinceDate, account, priority, cardAmount);
		clientHashTable.insert(iD, client);
		clientList.add(client);
		clientTree.addNode(client.getAccount().getAmount(), client);
		if(client.getPriority().equalsIgnoreCase(client.NORMAL)) 
			clientQueue.enqueue(client);
	}
	
	/**
	 * Fills the bank's stack with canceled client data
	 * @param name
	 * @param iD
	 * @param cardNumber
	 * @param paymentDueDate
	 * @param memberSinceDate
	 * @throws Exception 
	 */
	
	public void fillCanceledClientData(String name, String iD, String cardNumber, LocalDate paymentDueDate,
							   LocalDate memberSinceDate, Account account, String priority, Double cardAmount) throws Exception {
		
		Client client = new Client(name, iD, cardNumber, paymentDueDate, memberSinceDate, account, priority, cardAmount);
		clientStack.Ipush(client);
	}
	
	/**
	 * Increases the amount of a client's savings account<br>
	 * <b>pre:</b> deposit>0 y cliente!=null<br>
	 * <b>post:</b> the amount of the client's savings account has increased by the value of amount<br>
	 * @param client
	 * @param deposit
	 */
	
	public void deposit(Client client, Double deposit) {
		
		Double amount = client.getAccount().getAmount()+deposit;
		client.getAccount().setAmount(amount);
	}
	
	/**
	 * Decreases the amount of a client's savings account<br>
	 * <b>pre:</b> deposit>0 y cliente!=null<br>
	 * <b>post:</b> the amount of the client's savings account has decreased by the value of withdrawal<br>
	 * @param client
	 * @param withdraw
	 * @throws Exception. If withdrawal is greater than the balance of the client's savings account
	 */
	
	public void withdraw(Client client, Double withdrawal) throws Exception {
		
		Double amount = client.getAccount().getAmount()-withdrawal;
		
		if(amount<0)
			throw new Exception("Invalid operation: Cannot withdraw an amount greater than the account's balance");
		else
			client.getAccount().setAmount(amount);
	}
	
	/**
	 * Cancels a client's savings account and deletes all his data from the database.
	 * Also incorporates the client data into a different database with extra information
	 * about his account<br>
	 * <b>pre:</b> client!=null<br>
	 * <b>post:</b> client is no longer contained in the bank but in a exclusive database<br>
	 * @param client
	 * @param cancelationDate
	 * @param cancelationComments
	 * @throws IOException 
	 */
	
	public void cancelAccount(Client client, LocalDate cancelationDate, String cancelationComments) throws IOException {
		
		//Cancels a client's savings account as it adds values to cancelationDate and
		//cancelationComments. Client is stored in a stack so that it can be retrieved
		client.getAccount().setCancelationDate(cancelationDate);
		client.getAccount().setCancelationComments(cancelationComments);
		clientStack.Ipush(client);
		
		//Incorporates the client data into a different database with extra information and updates
		//the main database as it overwrites it while excluding the given client
		File tempFile = new File("C:\\Users\\usuario\\eclipse-workspace\\bank\\resources\\tempFile");
		File file1 = new File("C:\\Users\\usuario\\eclipse-workspace\\bank\\resources\\canceledAccounts");
		File file2 = new File("C:\\Users\\usuario\\eclipse-workspace\\bank\\resources\\database");
		BufferedWriter tempBw = new BufferedWriter(new FileWriter(tempFile));
		BufferedWriter bw = new BufferedWriter(new FileWriter(file1, true));
		BufferedReader br = new BufferedReader(new FileReader(file2));
		updateDataBase(client, tempBw, bw, br);
		Files.move(tempFile.toPath(), file2.toPath(), StandardCopyOption.REPLACE_EXISTING);
		
		//Deletes the client from the data structures in the bank
		clientList.remove(client);
		clientTree.deleteNode(client.getAccount().getAmount());	
		
	}
	
	/**
	 * Retrieves a client's savings account and adds all his data back into the database
	 * Also deletes the client from the canceled clients database
	 * <b>pre:</b> stack!=null<br>
	 * <b>post:</b> client is now part of the bank<br>
	 * @throws IOException
	 */
	
	public void undo() throws IOException {
		
		//Retrieves a client's savings account as it adds null values to cancelationDate and
		//cancelationComments. Client is removed from the stack
		Client client = clientStack.pop();
		client.getAccount().setCancelationDate(null);
		client.getAccount().setCancelationComments(null);
		
		//Reincorporates the client data into the database and updates
		//the canceled client database as it overwrites it while excluding the client
		File tempFile = new File("C:\\Users\\usuario\\eclipse-workspace\\bank\\resources\\tempFile");
		File file1 = new File("C:\\Users\\usuario\\eclipse-workspace\\bank\\resources\\canceledAccounts");
		File file2 = new File("C:\\Users\\usuario\\eclipse-workspace\\bank\\resources\\database");
		BufferedWriter tempBw = new BufferedWriter(new FileWriter(tempFile));
		BufferedWriter bw = new BufferedWriter(new FileWriter(file2, true));
		BufferedReader br = new BufferedReader(new FileReader(file1));
		updateDataBase(client, tempBw, bw, br);
		Files.move(tempFile.toPath(), file1.toPath(), StandardCopyOption.REPLACE_EXISTING);
		
		//Adds the client back into the data structures in the bank
		clientList.add(client);
		clientTree.addNode(client.getAccount().getAmount(), client);
	}
	
	private void updateDataBase(Client client, BufferedWriter tempBw, BufferedWriter bw, BufferedReader br) throws IOException {
		
		String data = br.readLine();
		
		while(data!=null) {
			
			String[] dataArray = data.split(", ");
			
			if(dataArray[1].equals(client.getiD())) {
				data = client.getClientData();
				bw.write(data);
				bw.newLine();
			} else { 
				tempBw.write(data);
				tempBw.newLine();
			}
			data = br.readLine();
		}
		tempBw.close();
		bw.close();
		br.close();
	}
	
	/**
	 * Advances the queue<br>
	 */
	
	public void next() {
		clientQueue.dequeue();
	}

	/**
	 * Retrieves client data from the hashtable given a key<br>
	 * <b>pre:</b> client with key 'iD' exists<br>
	 * @param iD client key
	 * @return client data 
	 */
	
	public String searchClient(String iD) { 
		return clientHashTable.get(iD).toString();
	}
	
	/**
	 * Adapted from https://www.withexample.com/merge-sort-using-arraylist-java-example/<br>
	 * Sorts the client list by name using the mergesort algorithm<br>
	 * <b>pre:</b> client list is not empty<br>
	 * <b>post:</b> client list is sorted by name<br>
	 */
	
	public void sortClientsByName() {       
		divide(0, clientList.size()-1);
    }
	
	private void divide(int start,int end){
        
        //Divide till you breakdown your list to single element
        if(start<end && (end-start)>=1){
            int mid = (end + start)/2;
            divide(start, mid);
            divide(mid+1, end);        
            
            //merging Sorted array produce above into one sorted array
            merge(start,mid,end);            
        }       
    } 
	
	private void merge(int start,int mid,int end){
        
        //Below is the mergedarray that will be sorted array Array[i-mid] , Array[(mid+1)-end]
		List<Client> mergedSortedArray = new ArrayList<Client>();
        
        int left = start;
        int right = mid+1;
        
        while(left<=mid && right<=end){
            if(clientList.get(left).getName().compareTo(clientList.get(right).getName())<0){
                mergedSortedArray.add(clientList.get(left));
                left++;
            }else{
                mergedSortedArray.add(clientList.get(right));
                right++;
            }
        }       
        
        //Either of below while loop will execute
        while(left<=mid){
            mergedSortedArray.add(clientList.get(left));
            left++;
        }
        
        while(right<=end){
            mergedSortedArray.add(clientList.get(right));
            right++;
        }
        
        int i = 0;
        int j = start;
        //Setting sorted array to original one
        while(i<mergedSortedArray.size()){
            clientList.set(j, mergedSortedArray.get(i++));
            j++;
        }
    }
	
	/**
	 * Adapted from https://codereview.stackexchange.com/questions/181391/sorting-an-arraylist-of-vehicles-using-quick-sort<br>
	 * Sorts the client list by ID using the quicksort algorithm<br>
	 * <b>pre:</b> client list is not empty<br>
	 * <b>post:</b> client list is sorted by ID<br>
	 */
	
	public void sortClientsByID() {
		quickSort(0, clientList.size()-1);
	}

	protected void quickSort(int a, int b) {
		
		if(a<b) { 
			
			Client pivot = clientList.get(b);
	
		    int left = a;
		    int right = b;
	
		    while (left < right) {
		    	while(clientList.get(left).getiD().compareTo(pivot.getiD()) < 0)
		    		left++;
	
		        while(clientList.get(right).getiD().compareTo(pivot.getiD()) > 0)
		            right--;
	
		        if (right > left) {
		            Collections.swap(clientList, left, right);
		        }
		    }
		    quickSort(a, right-1);
		    quickSort(right+1, b);
		}
	}
	
	/**
	 * Sorts the client list by the oldest using the insertion sort algorithm<br>
	 * <b>pre:</b> client list is not empty<br>
	 * <b>post:</b> client list is sorted by ID<br>
	 */
	
	public void sortClientsByTime() {
		
		for (int i = 1; i < clientList.size(); i++)  {
			
		    Client key = clientList.get(i);
		    int j = i-1;
		    
		    while (j >= 0 && key.getMemberSinceDate().compareTo(clientList.get(j).getMemberSinceDate()) < 0) {
		        clientList.set(j+1, clientList.get(j)); 
		        j--;
		    }
		    clientList.set(j+1, key);
		}
	}
	
	/**
	 * Sorts the client list by amount using the inorder traversal algorithm<br>
	 * <b>pre:</b> client list is not empty<br>
	 * <b>post:</b> client list is sorted by ID<br>
	 */
	
	public void sortClientsByAmount() {
		clientList.clear();
		clientTree.inOrder(clientList);
	}

	public List<Client> getClientList() {
		return clientList;
	}

	public IQueue<Client> getClientQueue() {
		return clientQueue;
	}

	public IStack<Client> getClientStack() {
		return clientStack;
	}
}
