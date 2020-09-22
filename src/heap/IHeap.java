package heap;

public interface IHeap<P,T> {
	public int parent(int p);
	public int rightChild(int p);
	public void minHeapify(int index);
	public void maxHeapify(int index);
	public void maxHeap();
	public void minHeap();
	public void insertMax(P priority, T element);
	public void insertMin(P priority, T element);
	public T removeMax();
	public T max();
	public int size();
	public boolean isEmpty();
	public boolean isLeaf(int p);
	
}
