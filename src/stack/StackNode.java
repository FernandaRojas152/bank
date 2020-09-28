package stack;

/**
 * 
 * @author Fernanda
 *
 * @param <E>
 */
public class StackNode<E> {
	//Attributes
	private E element;
	private StackNode<E> next;
	private StackNode<E> prev;
	
	//Methods
	/**
	 * 
	 * @param element
	 */
	public StackNode(E element) {
		this.element = element;
	}

	public E getElement() {
		return element;
	}

	public void setElement(E element) {
		this.element = element;
	}

	public StackNode<E> getNext() {
		return next;
	}

	public void setNext(StackNode<E> next) {
		this.next = next;
	}

	public StackNode<E> getPrev() {
		return prev;
	}

	public void setPrev(StackNode<E> prev) {
		this.prev = prev;
	}
}
