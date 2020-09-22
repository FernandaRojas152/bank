package heap;

/**
 * @version September 22th 2020
 * @author 
 * Class BankHeap
 * @param <P>
 * @param <T>
 * Code based on: https://java2blog.com/heap-sort-in-java/
 * and https://www.geeksforgeeks.org
 */
public class BankHeap<P,T> implements IHeap<P, T>{
	//Attributes
	private final static int ROOT= 1;
	private HeapItem<P, T>[] heap;
	private int size;
	private int maxSize;
	
	//Methods
	public BankHeap(int maxSize){
		this.maxSize= maxSize;
		size=0;
		heap= new HeapItem[maxSize+1];
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
				swap(index, l);
				minHeapify(l);
			}else break;
		}
	}

	@Override
	public void maxHeapify(int index) {
		while(hasLeftChild(index)) {
			int s= leftChild(index);
			if(hasRightChild(index) && heap[leftChild(index)].getPriority() > heap[rightChild(index)].getPriority()) {
				s= rightChild(index);			
			}
			if(heap[index].getPriority()> heap[s].getPriority()) {
				swap(index, s);
				maxHeapify(s);
			}else break;
		}
	}

	@Override
	public void maxHeap() {
		int index= maxSize;
		while(parent(index)!=0 && heap[parent(index)].getPriority()< heap[index].getPriority()) {
			swap(index, parent(index));
			index= parent(index);
		}
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
		HeapItem<P, T> insert= new HeapItem<>(priority, element);
		setMaxSize(maxSize+1);
		heap[maxSize]= insert;
		maxHeap();
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
		//se debe de tomar el primer valor, intercambiarlo con el ultimo.
		//y luego ya borrarlo y llamar al metodo minHeap o maxHeap para volver a tener
		//un heap balanceado.
		HeapItem<P, T> aux= heap[ROOT];
		heap[ROOT]= heap[maxSize];
		heap[maxSize]= null;
		setMaxSize(maxSize-1);
		maxHeapify(ROOT);
		return aux.getElement();
	}
	
	@Override
	public T removeMin() {
		HeapItem<P, T> aux= heap[ROOT];
		heap[ROOT]= heap[maxSize];
		heap[maxSize]= null;
		setMaxSize(maxSize-1);
		minHeapify(ROOT);
		return aux.getElement();
	}

	@Override
	public T max() {
		return heap[ROOT].getElement();
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
