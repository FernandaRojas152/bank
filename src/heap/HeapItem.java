package heap;

/**
 * @version September 22th 2020
 * @author 
 *
 * @param <P>
 * @param <T>
 */
public class HeapItem<P,T> {
	//Attributes
	private P key;
	private T element;
	private HeapItem<P, T> next;
	private HeapItem<P, T> prev;
	private int priority;
	
	//Methods
	/**
	 * 
	 * @param key
	 * @param element
	 */
	public HeapItem(P key, T element) {
		this.key= key;
		this.element= element;
		next= null;
		prev= null;	
	}

	public P getKey() {
		return key;
	}

	public void setKey(P key) {
		this.key = key;
	}

	public T getElement() {
		return element;
	}

	public void setElement(T element) {
		this.element = element;
	}

	public HeapItem<P, T> getNext() {
		return next;
	}

	public void setNext(HeapItem<P, T> next) {
		this.next = next;
	}

	public HeapItem<P, T> getPrev() {
		return prev;
	}

	public void setPrev(HeapItem<P, T> prev) {
		this.prev = prev;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}
}
