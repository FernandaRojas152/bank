package stack;

import customExceptions.EmptyStackException;

/**
 * 
 * @author 
 * @version September 10th 2020
 * @param <I>
 */
public interface InStack<E> extends Iterable<E> {
	public void push(E element);
	public E peek() throws EmptyStackException;
	public E pop();
	public boolean isEmpty();
	public int Isize();
}