package queue;


/**
 * Queue node
 * @author usuario
 *
 * @param <T>
 */

public class QueueNode<T> {

	T data;
	QueueNode<T> next;
    
	QueueNode(T x) {
    	data = x;
        next = null;
    }

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public QueueNode<T> getNext() {
		return next;
	}

	public void setNext(QueueNode<T> next) {
		this.next = next;
	} 
}
