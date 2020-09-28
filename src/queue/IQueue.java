package queue;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Queue data structure
 * @author usuario
 *
 * @param <T>
 */

public class IQueue<T> implements InQueue<T> {
	
	private QueueNode<T> front, rear;
	private int size;
	
	/**
	 * Builds an empty queue.
	 */
	
	public IQueue() {
		front = rear = null;
		size = 0;
	}
	
	/**
	 * Adds a new element to the queue.
	 * <b>post:</b> A new element has been added. Queue!=null.<br>
	 * @param t. Element data
	 */

	@Override
	public void enqueue(T t) {
		// TODO Auto-generated method stub
		
		QueueNode<T> temp = new QueueNode<>(t);
		 
	    if(isEmpty()) {
	    	front = rear = temp;
	    }else {
	    	rear.next = temp;
	        rear = temp;   
	    }
	    size++;
	}
	
	/**
	 * Retrieves the head of the queue.
	 * <b>pre:</b> Queue = (a1,a2,a3,a4..., an) V queue!=null.<br> 
	 * <b>post:</b> a1
	 * @return element
	 * @throws NoSuchElementException if queue==null
	 */

	@Override
	public QueueNode<T> peek() throws NoSuchElementException {
		// TODO Auto-generated method stub
		
		if(isEmpty())
			throw new NoSuchElementException();
		return front;
	}
	
	/**
	 * Retrieves and deletes the head of the queue.
	 * <b>pre:</b> Queue = (a1,a2,a3,a4..., an) V queue!=null.<br> 
	 * <b>post:</b> a1
	 * @return element
	 * @throws NoSuchElementException if queue==null
	 */

	@Override
	public QueueNode<T> dequeue() throws NoSuchElementException {
		// TODO Auto-generated method stub
		
		if(isEmpty())
			throw new NoSuchElementException();
		 
		QueueNode<T> temp = front;
	    front = front.next;
	    size--;
	    
	    return temp;
	}
	
	/**
	 * Determines whether a queue is empty or not.
	 * <b>post:</b> a1
	 * @return true if queue==null V false if queue!=null
	 */

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return front == null;
	}
	
	/**
	 * Returns the current size of the queue.
	 * <b>pre:</b> queue!=null.<br> 
	 * @return queue's size
	 */

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	public QueueNode<T> getFront() {
		return front;
	}

	public void setFront(QueueNode<T> front) {
		this.front = front;
	}

	public QueueNode<T> getRear() {
		return rear;
	}

	public void setRear(QueueNode<T> rear) {
		this.rear = rear;
	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return new QueueIterator<T>(front);
	}
}    
