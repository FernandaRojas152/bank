package heap;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @version September 22th 2020
 * @author 
 * Class BankHeap
 * @param <P>
 * @param <T>
 * Code based on: https://java2blog.com/heap-sort-in-java/
 * and https://www.codesdope.com/blog/article/priority-queue-using-heap/
 */
public class IHeap<P extends Comparable<P>> implements InHeap<P> {
	//Attributes
	private final static int ROOT= 1;
	public static int CAPACITY= 10;
	private P[] heap;
	private int size;
	private int heap_Size;

	//Methods
	@SuppressWarnings("unchecked")
	public IHeap(P[] a){
		size=10;
		heap= (P[]) new Comparable[CAPACITY];
		heap_Size=0;
		
	}
	
	public IHeap(P[] a, int heap_Size) {
		heap= a;
		size= a.length;
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
			P aux= heap[smallest];
			heap[smallest]= heap[index];
			heap[index]= aux;
			minHeapify(smallest);
		}
	}

	@Override
	public void maxHeapify(int index) {
		int left= leftChild(index);
		int right= rightChild(index);
		int largest;

		if(left< heap_Size && heap[left].compareTo(heap[index])>0) {
			largest= left;
		}else largest= index;
		if(right< heap_Size && heap[left].compareTo(heap[index])>0) {
			largest=right;
		}
		if(largest!=index) {
			P aux= heap[largest];
			heap[largest]= heap[index];
			heap[index]= aux;
			maxHeapify(largest);
		}
	}

	@Override
	public void increaseMaxHeap(int index, P key) {
		heap[index]= key;
		while(index>1 && heap[parent(index)].compareTo(heap[index])<0) {
			P aux= heap[parent(index)];
			heap[parent(index)]= heap[index];
			heap[index]= aux;
			index= parent(index);
		}
	}
	
	@Override
	public void insertMax(P element)  {
		/**resize(size*2);
		heap_Size++;
		heap[heap_Size-1]= element;
		
		for(int i= heap_Size-1; i>=1; i= parent(i)) {
			maxHeapify(parent(i));
		}
		
		maxHeapify(parent(heap_Size));
		*/
		
		heap_Size++;
		heap[heap_Size-1]= element;
		
		increaseMaxHeap(heap_Size, element);
	}

	@Override
	public void decreaseMinHeap(int index, P key) {
		if(key.compareTo(heap[index])<0) {
			throw new IndexOutOfBoundsException();
		}
		heap[index]= key;
		while(parent(index)!=0 && heap[parent(index)].compareTo(heap[index])>0) {
			P aux= heap[index];
			heap[index]= heap[parent(index)];
			heap[parent(index)]= aux;
			index= parent(index);
		}
	}

	@Override
	public void insertMin(P element) {
		resize(size*2);
		heap_Size++;
		heap[heap_Size-1]= element;
		
		for(int i= heap_Size-1; i>=1; i= parent(i)) {
			minHeapify(parent(i));
		}
		
		minHeapify(parent(heap_Size));
	}

	@Override
	public P extractMax() {
		//se debe de tomar el primer valor, intercambiarlo con el ultimo.
		//y luego ya borrarlo y llamar al metodo minHeap o maxHeap para volver a tener
		//un heap balanceado.
		P aux= heap[ROOT];
		heap[ROOT]= heap[heap_Size];
		heap[heap_Size]= null;
		heap_Size--;
		maxHeapify(ROOT);
		return aux;
	}

	@Override
	public P extractMin() {
		P aux= heap[ROOT];
		heap[ROOT]= heap[heap_Size];
		heap[heap_Size]= null;
		heap_Size--;
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
	
	public P[] resize(int n) {
		return Arrays.copyOf(heap, n);
	}

	/**public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int element;
		Integer[]a= new Integer[8];
		BankHeap<Integer> prueba= new BankHeap<>(a,3);
		
		prueba.insertMax(2);
		prueba.insertMax(9);
		prueba.insertMax(12);
		//prueba.extractMax();
		System.out.println(prueba.getheap_Size());
		System.out.println(prueba.max());
	}*/
}