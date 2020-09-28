package stack;

/**
 * 
 * @author Fernanda
 *
 * @param <E>
 */
public class NodeStack<E> {
	//Attributes
	private E element;
	private NodeStack<E> next;
	private NodeStack<E> prev;
	
	//Methods
	/**
	 * 
	 * @param element
	 */
	public NodeStack(E element) {
		this.element = element;
	}

	public E getElement() {
		return element;
	}

	public void setElement(E element) {
		this.element = element;
	}

	public NodeStack<E> getNext() {
		return next;
	}

	public void setNext(NodeStack<E> next) {
		this.next = next;
	}

	public NodeStack<E> getPrev() {
		return prev;
	}

	public void setPrev(NodeStack<E> prev) {
		this.prev = prev;
	}
}
