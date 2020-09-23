package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import binarySearchTree.BinarySearchTree;
import hashtable.HashTable;
import heap.BankHeap;
import queue.BankQueue;

/**
 * Represents the bank and its operations
 * @author usuario
 *
 */

public class Bank {
	
	private HashTable<String, Client> clientsHashTable;
	private List<Client> clientsList;
	private BinarySearchTree<Double, Client> clientsTree;
	private BankQueue<Client> clientsQueue;
	
	
	/**
	 * Builds a bank
	 */
	
	public Bank() {
		clientsHashTable = new HashTable<String, Client>();
		clientsList = new ArrayList<Client>();
		clientsTree = new BinarySearchTree<Double, Client>();
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
														LocalDate memberSinceDate, Account account ) throws Exception {
		
		Client client = new Client(name, iD, cardNumber, paymentDueDate, memberSinceDate, account);
		clientsHashTable.insert(iD, client);
		clientsList.add(client);
		clientsTree.addNode(client.getAccount().getAmount(), client);
	}
	
	/**
	 * Retrieves client data from the hashtable given a key<br>
	 * <b>pre:</b> client with key 'iD' exists<br>
	 * @param iD client key
	 * @return client data 
	 */
	
	public String searchClient(String iD) { 
		return clientsHashTable.get(iD).toString();
	}
	
	/**
	 * Adapted from https://www.withexample.com/merge-sort-using-arraylist-java-example/<br>
	 * Sorts the client list by name using the mergesort algorithm<br>
	 * <b>pre:</b> clients list is not empty<br>
	 * <b>post:</b> clients list is sorted by name<br>
	 */
	
	public void sortClientsByName(){       
		divide(0, clientsList.size()-1);
    }
	
	private void divide(int startIndex,int endIndex){
        
        //Divide till you breakdown your list to single element
        if(startIndex<endIndex && (endIndex-startIndex)>=1){
            int mid = (endIndex + startIndex)/2;
            divide(startIndex, mid);
            divide(mid+1, endIndex);        
            
            //merging Sorted array produce above into one sorted array
            merge(startIndex,mid,endIndex);            
        }       
    } 
	
	private void merge(int start,int mid,int endIndex){
        
        //Below is the mergedarray that will be sorted array Array[i-midIndex] , Array[(midIndex+1)-endIndex]
		List<Client> mergedSortedArray = new ArrayList<Client>();
        
        int left = start;
        int right = mid+1;
        
        while(left<=mid && right<=endIndex){
            if(clientsList.get(left).getName().compareTo(clientsList.get(right).getName())<0){
                mergedSortedArray.add(clientsList.get(left));
                left++;
            }else{
                mergedSortedArray.add(clientsList.get(right));
                right++;
            }
        }       
        
        //Either of below while loop will execute
        while(left<=mid){
            mergedSortedArray.add(clientsList.get(left));
            left++;
        }
        
        while(right<=endIndex){
            mergedSortedArray.add(clientsList.get(right));
            right++;
        }
        
        int i = 0;
        int j = start;
        //Setting sorted array to original one
        while(i<mergedSortedArray.size()){
            clientsList.set(j, mergedSortedArray.get(i++));
            j++;
        }
    }
	
	/**
	 * Adapted from https://codereview.stackexchange.com/questions/181391/sorting-an-arraylist-of-vehicles-using-quick-sort<br>
	 * Sorts the client list by ID using the quicksort algorithm<br>
	 * <b>pre:</b> clients list is not empty<br>
	 * <b>post:</b> clients list is sorted by ID<br>
	 */
	
	public void sortClientsByID() {
		quickSort(0, clientsList.size()-1);
	}

	protected void quickSort(int a, int b) {
		
		if(a<b) { 
			
			Client pivot = clientsList.get(b);
	
		    int left = a;
		    int right = b;
	
		    while (left < right) {
		    	while(clientsList.get(left).getiD().compareTo(pivot.getiD()) < 0)
		    		left++;
	
		        while(clientsList.get(right).getiD().compareTo(pivot.getiD()) > 0)
		            right--;
	
		        if (right > left) {
		            Collections.swap(clientsList, left, right);
		        }
		    }
		    quickSort(a, right-1);
		    quickSort(right+1, b);
		}
	}
	
	/**
	 * Sorts the client list by the oldest using the insertion sort algorithm<br>
	 * <b>pre:</b> clients list is not empty<br>
	 * <b>post:</b> clients list is sorted by ID<br>
	 */
	
	public void sortClientsByTime() {
		
		for (int i = 1; i < clientsList.size(); i++)  {
			
		    Client key = clientsList.get(i);
		    int j = i-1;
		    
		    while (j >= 0 && key.getMemberSinceDate().compareTo(clientsList.get(j).getMemberSinceDate()) < 0) {
		        clientsList.set(j+1, clientsList.get(j)); 
		        j--;
		    }
		    clientsList.set(j+1, key);
		}
	}
	
	/**
	 * Sorts the client list by amount using the treesort algorithm<br>
	 * <b>pre:</b> clients list is not empty<br>
	 * <b>post:</b> clients list is sorted by ID<br>
	 */
	
	public void sortClientsByAmount() {
		clientsList.clear();
		clientsTree.inOrder(clientsList);
//		Collections.reverse(clientsList);
	}

	public List<Client> getClientsList() {
		return clientsList;
	}
}
