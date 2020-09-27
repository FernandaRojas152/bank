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
public class IHeap<P extends Comparable<P>> implements InHeap<P> {
	//Attributes
	private final static int ROOT= 1;
	public static int CAPACITY= 100;
	private P[] heap;
	private int heap_Size;
	private boolean type; //false it's minHeap, true it's maxHeap
	//Methods
	@SuppressWarnings("unchecked")
	public IHeap(int size, boolean type){
		type= false;
		heap_Size=0;
		heap= (P[]) new Comparable[size];
		if(type) { buildMinHeap();
		}else buildMaxHeap();
	}

	public IHeap(P[] a) {
		heap= a;
		heap_Size= a.length-1;
	}

	@Override
	public void buildMaxHeap() {
		for (int i = heap_Size/2; i>= 1; i--) {
			heapify(i);
		}
	}

	@Override
	public void buildMinHeap() {
		for (int i = heap_Size/2; i>= 1; i--) {
			heapify(i);
		}
	}

	public int getHeap_Size() {
		return heap_Size;
	}

	@Override
	public int parent(int p) {
		return (p/2);
	}

	@Override
	public int rightChild(int p) {
		return 2*p;
	}

	@Override
	public int leftChild(int p) {
		return 2*p+1;
	}

	@Override
	public void heapify(int index) {
		int left= leftChild(index);
		int right= rightChild(index);
		if(type) {	
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
				heapify(smallest);
			}
		}else {
			int largest;
			if(left<= heap_Size && heap[left].compareTo(heap[index])>0) {
				largest= left;
			}else largest= index;
			if(right<= heap_Size && heap[right].compareTo(heap[largest])>0) {
				largest=right;
			}
			if(largest!=index) {
				P aux= heap[index];
				heap[index]= heap[largest];
				heap[largest]= aux;
				heapify(largest);
			}
		}
	}

	@Override
	public void increaseMaxHeap(int index, P element) {
		heap[index]= element;
		if(type) {
			while(index>1 && heap[parent(index)].compareTo(heap[index])>0) {
				swap(index, parent(index));
				index= parent(index);
			}
		}else {
			while(index>1 && heap[parent(index)].compareTo(heap[index])<0) {
				swap(index, parent(index));
				index= parent(index);
			}
		}
	}

	@Override
	public void insert(P element)  {
		heap_Size++;
		heap[heap_Size]= element;

		increaseMaxHeap(heap_Size, element);
	}

	@Override
	public P extract() {
		if(type) {
			P aux= heap[ROOT];
			heap[ROOT]= heap[heap_Size];
			heap_Size--;
			heapify(ROOT);
			return aux;
		}else {
			P aux= heap[ROOT];
			heap[ROOT]= heap[heap_Size];
			heap[heap_Size]= null;
			heap_Size--;
			heapify(ROOT);
			return aux;
		}

	}

	@Override
	public P max() {
		return heap[ROOT];
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

	/**public static void main(String[] args) {
		IHeap<Integer> prueba=new IHeap<>(CAPACITY,true);
		prueba.insert(15);
		prueba.insert(7);
		prueba.insert(18);
		prueba.insert(10);
		prueba.insert(5);
		//prueba.insertMax(13);
		//prueba.insertMax(50);
		System.out.println(prueba.max());
		System.out.println(prueba.extract());
		//prueba.extractMax();
		//System.out.println(prueba.extractMax());
		System.out.println(prueba.max());
	}*/
}