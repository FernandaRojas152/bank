package heap;
import java.util.Arrays;

/**
 * @version September 22th 2020
 * @author 
 * Class BankHeap
 * @param <P>
 * @param <T>
 * Code based on: https://java2blog.com/heap-sort-in-java/
 * and https://www.codesdope.com/blog/article/priority-queue-using-heap/
 */
public class BankHeap<P extends Comparable<P>> implements IHeap<P> {
	//Attributes
	private final static int ROOT= 1;
	public static int CAPACITY= 100;
	private P[] heap;
	private int size;
	//private int heap_Size;

	//Methods
	@SuppressWarnings("unchecked")
	public BankHeap(int heap_Size){
		size=0;
		heap= (P[]) new Comparable[heap_Size];		
	}
	
	public BankHeap(P[] a) {
		heap= a;
		size= a.length-1;
		buildMaxHeap();
	}
	
	@Override
	public void buildMaxHeap() {
		for (int i = size/2; i>= 1; i--) {
			maxHeapify(i);
		}
	}

	public int getSize() {
		return size;
	}
	
	@Override
	public int parent(int p) {
		return (p/2);
	}

	@Override
	public int rightChild(int p) {
		int right= (int) (Math.floor(p*2)+1)-1;
		return right;
	}

	@Override
	public int leftChild(int p) {
		int left= (int) Math.floor(p*2)-1;
		
		return left;
	}

	@Override
	public void maxHeapify(int index) {
		int left= leftChild(index);
		int right= rightChild(index);
		index--;
		int largest;
		if(left<= size && heap[left].compareTo(heap[index])>0) {
			largest= left;
		}else largest= index;
		if(right<= size && heap[left].compareTo(heap[index])>0) {
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
	public void increaseMaxHeap(int index, P element) {
		heap[index]= element;
		while(hasParent(index) && heap[parent(index)].compareTo(heap[index])<0) {
			swap(index, parent(index));
			index= parent(index);
		}
	}
	
	@Override
	public void insertMax(P element)  {
		size++;
		heap[size-1]= element;
		
		increaseMaxHeap(size, element);
		
		/**heap_Size++;
		heap[heap_Size-1]= element;
		
		increaseMaxHeap(heap_Size, element);
		*/
	}

	@Override
	public P extractMax() {
		//se debe de tomar el primer valor, intercambiarlo con el ultimo.
		//y luego ya borrarlo y llamar al metodo minHeap o maxHeap para volver a tener
		//un heap balanceado.
		P aux= heap[ROOT];
		heap[ROOT]= heap[size];
		//heap[heap_Size]= null;
		size--;
		maxHeapify(ROOT);
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
	
	private boolean hasParent(int i){
		return i > 1;
	}
	
	private boolean hasLeft(int i){
		return leftChild(i) < size;
	}
	
	private boolean hasRight(int i){
		return rightChild(i) < size;
	}
	
	private void swap(int index1, int index2)
	{
		P temp = heap[index1];
		heap[index1] = heap[index2];
		heap[index2] = temp;
	}

	public P[] resize() {
		return Arrays.copyOf(heap, heap.length + CAPACITY);
	}

	public static void main(String[] args) {
		BankHeap<Integer> prueba=new BankHeap<>(CAPACITY);
		prueba.insertMax(11);
		prueba.insertMax(4);
		prueba.insertMax(18);
		prueba.insertMax(10);
		prueba.insertMax(5);
		//prueba.insertMax(13);
		//prueba.insertMax(50);
		System.out.println(prueba.max());
		System.out.println(prueba.extractMax());
		//prueba.extractMax();
		//System.out.println(prueba.extractMax());
		System.out.println(prueba.max());
	}
	
	/**@Override
	public void buildMinHeap() {
		for (int i = heap_Size/2; i>= 1; i--) {
			minHeapify(i);
		}
	}*/
	
	/**@Override
	public P extractMin() {
		P aux= heap[ROOT];
		heap[ROOT]= heap[heap_Size];
		heap[heap_Size]= null;
		heap_Size--;
		minHeapify(ROOT);
		return aux;
	}
	*/
	
	/**@Override
	public void decreaseMinHeap() {
		int index= heap_Size;
		while(parent(index)!=0 && heap[parent(index)].compareTo(heap[index])>0) {
			P aux= heap[index];
			heap[index]= heap[parent(index)];
			heap[parent(index)]= aux;
			index= parent(index);
		}
	}
	*/

	/**@Override
	public void insertMin(P element) {
		resize();
		heap_Size++;
		heap[heap_Size]= element;
		
		for(int i= heap_Size-1; i>=1; i= parent(i)) {
			minHeapify(parent(i));
		}
		
		minHeapify(parent(heap_Size));
	}
	*/
	
	/**@Override
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
	*/
}