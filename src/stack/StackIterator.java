package stack;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class StackIterator<E> implements Iterator<E> {

	private StackNode<E> stackNode;
	
	public StackIterator(StackNode<E> first) {
		// TODO Auto-generated constructor stub
		stackNode = first;
	}
	
	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return stackNode!=null;
	}

	@Override	
	public E next() {
		// TODO Auto-generated method stub
		if (stackNode == null)
			throw new NoSuchElementException();
		else {
			E e = stackNode.getElement();
			stackNode = stackNode.getNext();
			return e;
		}
	}
}
