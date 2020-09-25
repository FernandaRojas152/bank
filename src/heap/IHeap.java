package heap;

public interface IHeap<P,T> {
	public int parent(int p);
	public int rightChild(int p);
	public int leftChild(int p);
	public void minHeapify(int index);
	public void maxHeapify(int index);
	public void increaseMaxHeap(int index, P priority);
	public void decreaseMinHeap(int index, P key);
	public P extractMax();
	public P extractMin();
	public P max();
	public int size();
	public void buildMaxHeap();
	public void buildMinHeap();	
	public void insertMax(P priority, T element);
	public void insertMin(P priority, T element);
	public void swap(int p, int p2);
}
