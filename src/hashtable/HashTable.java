package hashtable;

public class HashTable<K, V> implements HashTableOperations<K, V> {
	
	public final int SIZE = 10000;
	private GenericArray<HashTableNode<K, V>> genericArray; 
    private int size; 
  
    public HashTable() { 
    	genericArray = new GenericArray<>(SIZE); 
    	size = 0; 
    }
 
	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public GenericArray<HashTableNode<K, V>> getGenericArray() {
		// TODO Auto-generated method stub
		return genericArray;
	}

	@Override
	public int hashFunction(K k) {
		// TODO Auto-generated method stub 
		int hashCode = Math.abs(k.hashCode()); 
        int index = hashCode%SIZE; 
        return index; 
	}

	@Override
	public void insert(K k, V v) {
		// TODO Auto-generated method stub  
		int index = hashFunction(k); 
        HashTableNode<K, V> head = genericArray.getE(index); 

        while (head!=null) { 
        	
            if (head.getK().equals(k)) 
                head.setV(v); 
            head = head.next; 
        } 
  
        head = genericArray.getE(index); 
        HashTableNode<K, V> hashTableNode = new HashTableNode<>(k, v); 
        hashTableNode.setNext(head); 
        genericArray.setE(index, hashTableNode); 
        size++; 
	}

	@Override
	public V get(K k) {
		// TODO Auto-generated method stub  
		int index = hashFunction(k); 
        HashTableNode<K, V> head = genericArray.getE(index); 
  
        while (head != null) { 
        	
            if (head.getK().equals(k)) 
                return head.getV(); 
            head = head.next; 
        } 
        return null; 
	} 
}
