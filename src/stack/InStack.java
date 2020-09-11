package stack;
/**
 * 
 * @author Fernanda
 * @version September 10th 2020
 * @param <I>
 */
public interface InStack<E> {
	public void Ipush(E element);
	public E Ipeek();
	public E pop();
	public boolean isEmpty();
	public int Isize();
}
