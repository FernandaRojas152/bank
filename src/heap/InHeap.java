package heap;

public interface InHeap<P> {
	public int parent(int p);
	public int rightChild(int p);
	public int leftChild(int p);
	//public void minHeapify(int index);
	public void maxHeapify(int index);
	public void increaseMaxHeap(int index, P element);
	//public void decreaseMinHeap();
	public P extractMax();
	//public P extractMin();
	public P max();
	public int size();
	public void buildMaxHeap();
	//public void buildMinHeap();	
	public void insertMax(P priority);
	//public void insertMin(P priority);
}