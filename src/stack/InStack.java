package stack;
/**
 * 
 * @author Fernanda
 * @version September 10th 2020
 * @param <I>
 */
public interface InStack<I> {
	public void Ipush(I element);
	public I Ipeek();
	public I pop();
	public boolean isEmpty();
	public int Isize();
	public void undo();
}
