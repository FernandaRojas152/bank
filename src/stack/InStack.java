package stack;
/**
 * 
 * @author Fernanda
 * @version September 10th 2020
 * @param <I>
 */
public interface InStack<E> {
	public void push(E element);
	public E peek();
	public E pop();
	public boolean isEmpty();
	public int Isize();
}
