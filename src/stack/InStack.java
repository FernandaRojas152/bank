package stack;

import customException.EmptyStackException;

/**
 * 
 * @author 
 * @version September 10th 2020
 * @param <I>
 */
public interface InStack<E> extends Iterable<E> {
	public void push(E element);
	public E peek();
	public E pop() throws EmptyStackException;
	public boolean isEmpty();
	public int Isize();
}