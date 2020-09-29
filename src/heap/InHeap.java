package heap;

public interface InHeap<P> {
	public int parent(int p);
	public int rightChild(int p);
	public int leftChild(int p);
	public void heapify(int index);
	public void increaseMaxHeap(int index, P element);
	public P extract();
	public P max();
	public void buildMaxHeap();
	public void buildMinHeap();	
	public void insert(P priority);
	public boolean isEmpty();
}