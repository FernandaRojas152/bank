package stack;

public interface InStack<I> {
	public void Ipush(I element);
	public I Ipeek();
	public I pop();
	public boolean isEmpty();
	public int Isize();
}
