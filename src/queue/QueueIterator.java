package queue;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class QueueIterator<T> implements Iterator<T> {

	private QueueNode<T> queueNode;

	public QueueIterator(QueueNode<T> front) {
		// TODO Auto-generated constructor stub
		queueNode = front;
	}
	
	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return queueNode!=null;
	}

	@Override	
	public T next() {
		// TODO Auto-generated method stub
		if (queueNode == null)
			throw new NoSuchElementException();
		else {
			T t = queueNode.getT();
			queueNode = queueNode.getNext();
			return t;
		}
	}
}
