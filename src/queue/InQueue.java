package queue;


import java.util.NoSuchElementException;

/**
 * Basic operations and algorithms used by a queue.
 * @author usuario
 *
 * @param <T>
 */

public interface QueueOperations<T> {
	
	/**
	 * Adds a new element to the queue.
	 * <b>post:</b> A new element has been added. Queue!=null.<br>
	 * @param t. Element data
	 */
	
	public void enqueue(T t);
	
	/**
	 * Retrieves the head of the queue.
	 * <b>pre:</b> Queue = (a1,a2,a3,a4..., an) V queue!=null.<br> 
	 * <b>post:</b> a1
	 * @return element
	 * @throws NoSuchElementException if queue==null
	 */

	public QueueNode<T> peek() throws NoSuchElementException;
	
	/**
	 * Retrieves and deletes the head of the queue.
	 * <b>pre:</b> Queue = (a1,a2,a3,a4..., an) V queue!=null.<br> 
	 * <b>post:</b> a1
	 * @return element
	 * @throws NoSuchElementException if queue==null
	 */
	
	public QueueNode<T> dequeue() throws NoSuchElementException;
	
	/**
	 * Determines whether a queue is empty or not.
	 * <b>post:</b> a1
	 * @return true if queue==null V false if queue!=null
	 */
	
	public boolean isEmpty();
	
	/**
	 * Returns the current size of the queue.
	 * <b>pre:</b> queue!=null.<br> 
	 * @return queue's size
	 */
	
	public int size();
}
