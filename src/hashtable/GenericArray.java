package hashtable;

import java.util.Arrays;

public class GenericArray<E> {

	private final Object[] array;  
	public final int length;
	   
	public GenericArray(int length) {
		array = new Object[length];
		this.length = length;
	}
	
	public E getE(int i) {
		@SuppressWarnings("unchecked")
	    final E e = (E)array[i];
	    return e;
	}    
	   
	public void setE(int i, E v) {
		array[i] = v;
	}
	 
	@Override
	public String toString() {
		return Arrays.toString(array);
	}
}
