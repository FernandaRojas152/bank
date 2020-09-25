package heap;

import java.util.Comparator;

/**
 * @version September 22th 2020
 * @author 
 * Class BankHeap
 * @param <P>
 * @param <T>
 * Code based on: https://java2blog.com/heap-sort-in-java/
 * and https://www.geeksforgeeks.org
 * and https://www.codesdope.com/blog/article/priority-queue-using-heap/
 */
public class BankHeap<P extends Comparable<P>,T> implements IHeap<P,T> {
	//Attributes
	private final static int ROOT= 1;
	private P[] heap;
	private int size;
	private int heap_Size;

	//Methods
	@SuppressWarnings("unchecked")
	public BankHeap(int heap_Size){
		this.heap_Size= heap_Size;
		size=0;
		heap= (P[]) new Object[heap_Size];
	}

	@Override
	public void buildMaxHeap() {
		for (int i = heap_Size/2; i>= 1; i--) {
			maxHeapify(i);
		}
	}

	@Override
	public void buildMinHeap() {
		for (int i = heap_Size/2; i>= 1; i--) {
			minHeapify(i);
		}
	}

	public int getSize() {
		return size;
	}

	public int getheap_Size() {
		return heap_Size;
	}

	public void setheap_Size(int heap_Size) {
		this.heap_Size = heap_Size;
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
		int left= leftChild(index);
		int right= rightChild(index);
		int smallest;

		if(left<=heap_Size && heap[left].compareTo(heap[index])<0) {
			smallest= left;
		}else smallest= index;
		if(right<= heap_Size && heap[left].compareTo(heap[index])<0) {
			smallest=right;
		}
		if(smallest!=index) {
			P aux= heap[index];
			heap[index]= heap[smallest];
			heap[smallest]= aux;
			minHeapify(smallest);
		}
	}

	@Override
	public void maxHeapify(int index) {
		int left= leftChild(index);
		int right= rightChild(index);
		int largest;

		if(left<=heap_Size && heap[left].compareTo(heap[index])<0) {
			largest= left;
		}else largest= index;
		if(right<= heap_Size && heap[left].compareTo(heap[index])<0) {
			largest=right;
		}
		if(largest!=index) {
			P aux= heap[index];
			heap[index]= heap[largest];
			heap[largest]= aux;
			maxHeapify(largest);
		}
	}

	@Override
	public void increaseMaxHeap(int index, P key) {
		heap[index]= key;
		while(index>1 && heap[parent(index)].compareTo(heap[index])<0) {
			P aux= heap[index];
			heap[index]= heap[parent(index)];
			heap[parent(index)]= aux;
			index= parent(index);
		}
	}

	@Override
	public void decreaseMinHeap(int index, P key) {
		heap[index]= key;
		while(parent(index)!=0 && heap[parent(index)].compareTo(heap[index])>0) {
			P aux= heap[index];
			heap[index]= heap[parent(index)];
			heap[parent(index)]= aux;
			index= parent(index);
		}
	}

	@Override
	public void insertMax(P priority, T element) {
		//HeapItem<P> insert= new HeapItem<>(priority, element);
		setheap_Size(heap_Size+1);
		//heap[heap_Size]= insert;
		increaseMaxHeap(heap_Size, priority);
	}

	@Override
	public void insertMin(P priority, T element) {
		//HeapItem<P> insert= new HeapItem<>(priority, element);
		setheap_Size(heap_Size+1);
		heap[heap_Size]= priority;
		decreaseMinHeap(heap_Size, priority);
	}

	@Override
	public P extractMax() {
		//se debe de tomar el primer valor, intercambiarlo con el ultimo.
		//y luego ya borrarlo y llamar al metodo minHeap o maxHeap para volver a tener
		//un heap balanceado.
		P aux= heap[ROOT];
		heap[ROOT]= heap[heap_Size];
		heap[heap_Size]= null;
		setheap_Size(heap_Size-1);
		maxHeapify(ROOT);
		return aux;
	}

	@Override
	public P extractMin() {
		P aux= heap[ROOT];
		heap[ROOT]= heap[heap_Size];
		heap[heap_Size]= null;
		setheap_Size(heap_Size-1);
		minHeapify(ROOT);
		return aux;
	}

	@Override
	public P max() {
		return heap[ROOT];
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public void swap(int p, int p2) {
		// TODO Auto-generated method stub
		
	}

	/**public static void main(String[] args) {
		BankHeap<Integer> prueba= new BankHeap<Integer>(10);
		//prueba.buildMaxHeap();
		prueba.buildMinHeap();
		prueba.insertMin(1);
		System.out.println(prueba);
	}*/
}
