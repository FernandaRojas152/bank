package heap;

public class BankHeap<P,T> implements IHeap<P, T>{
	private final static int ROOT= 1;
	private HeapItem<P, T>[] heap;
	private int size;
	private int maxSize;
	
	public BankHeap(int maxSize){
		this.maxSize= maxSize;
		size=0;
		heap= new HeapItem[maxSize+1];
	}
	
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
		while(hasLeftChild(index)) {
			int l= leftChild(index);
			if(hasRightChild(index) && heap[leftChild(index)].getPriority()< heap[rightChild(index)].getPriority()) {
				l= rightChild(index);			
			}
			if(heap[index].getPriority()< heap[l].getPriority()) {
				swap(l, l);
				minHeapify(l);
			}else break;
		}
	}

	@Override
	public void maxHeapify(int index) {
		
	}

	@Override
	public void maxHeap() {
		
	}

	@Override
	public void minHeap() {
		int index= maxSize;
		while(parent(index)!=0 && heap[parent(index)].getPriority()> heap[index].getPriority()) {
			swap(index, parent(index));
			index= parent(index);
		}
	}

	@Override
	public void insertMax(P priority, T element) {
		
	}

	@Override
	public void insertMin(P priority, T element) {
		HeapItem<P, T> insert= new HeapItem<>(priority, element);
		setMaxSize(maxSize+1);
		heap[maxSize]= insert;
		minHeap();
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
		return size;
	}
	
	@Override
	public boolean isLeaf(int p) {
		if (p>= (size/2) && p<= size) {
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
	
	private boolean hasLeftChild(int i) {
		return leftChild(i) <= maxSize;
	}
	
	private boolean hasRightChild(int i) {
		return rightChild(i) <= maxSize;
	}

	@Override
	public void swap(int p, int p2) {
		HeapItem<P, T> aux= heap[p];
		heap[p]= heap[p2];
		heap[p2]= aux;
	}
}
