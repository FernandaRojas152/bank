package heap;

public interface IHeap<P,T> {
	public int parent(int p);
	public int rightChild(int p);
	public int leftChild(int p);
	public void minHeapify(int index);
	public void maxHeapify(int index);
	public void maxHeap();
	public void minHeap();
	public void insertMax(P priority, T element);
	public void insertMin(P priority, T element);
	public void swap(int p, int p2);
	public T removeMax();
	public T removeMin();
	public T max();
	public int size();
	public boolean isLeaf(int p);
	
}
