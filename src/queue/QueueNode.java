package queue;


/**
 * Queue node
 * @author usuario
 *
 * @param <T>
 */

public class QueueNode<T> {

	T t;
	QueueNode<T> next;
    
	QueueNode(T x) {
    	t = x;
        next = null;
    }

	public T getT() {
		return t;
	}

	public void setT(T t) {
		this.t = t;
	}

	public QueueNode<T> getNext() {
		return next;
	}

	public void setNext(QueueNode<T> next) {
		this.next = next;
	} 
}
