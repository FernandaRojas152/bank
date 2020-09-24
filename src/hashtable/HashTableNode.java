package hashtable;

public class HashTableNode<K, V> {
	
	private K k;
	private V v;
	HashTableNode<K, V> next;
	
	public HashTableNode(K k, V v) {
		super();
		this.k = k;
		this.v = v;
	}

	public K getK() {
		return k;
	}

	public void setK(K k) {
		this.k = k;
	}

	public V getV() {
		return v;
	}

	public void setV(V v) {
		this.v = v;
	}

	public HashTableNode<K, V> getNext() {
		return next;
	}

	public void setNext(HashTableNode<K, V> next) {
		this.next = next;
	} 
}
