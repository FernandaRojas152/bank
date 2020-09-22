package heap;

public class BankHeap<P,T> implements IHeap<P, T>{
	private HeapItem<P, T>[] heap;
	private int size;
	private int maxSize;
	
	@Override
	public int parent(int p) {
		return (p/2);
	}

	@Override
	public int rightChild(int p) {
		return (p*2)+1;
	}
	
	@Override
	public int leftChild(int p) {
		return (p*2);
	}
	
	@Override
	public void minHeapify(int index) {
		
	}

	@Override
	public void maxHeapify(int index) {
		
	}

	@Override
	public void maxHeap() {
		
	}

	@Override
	public void minHeap() {
		
	}

	@Override
	public void insertMax(P priority, T element) {
		
	}

	@Override
	public void insertMin(P priority, T element) {
		
	}

	@Override
	public T removeMax() {
		return null;
	}

	@Override
	public T max() {
		return null;
	}

	@Override
	public int size() {
		return maxSize;
	}
	
	@Override
	public boolean isLeaf(int p) {
		if (p>= (maxSize/2) && p<= maxSize) {
			return true;
		}
		return false;
	}

	public HeapItem<P, T>[] getHeap() {
		return heap;
	}

	public void setHeap(HeapItem<P, T>[] heap) {
		this.heap = heap;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getMaxSize() {
		return maxSize;
	}

	public void setMaxSize(int maxSize) {
		this.maxSize = maxSize;
	}
}
