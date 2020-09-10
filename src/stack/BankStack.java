package stack;

public class BankStack<E> implements InStack<E>{
	private NodeStack<E> first;
	private NodeStack<E> last;
	private int size;

	public BankStack() {
		first= null;
		last= null;
		size=0;
	}

	public NodeStack<E> getFirst() {
		return first;
	}

	public void setFirst(NodeStack<E> first) {
		this.first = first;
	}

	public NodeStack<E> getLast() {
		return last;
	}

	public void setLast(NodeStack<E> last) {
		this.last = last;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	@Override
	public void Ipush(E element) {
		// TODO Auto-generated method stub
		NodeStack<E> aux= new NodeStack<>(element);
		if(first== null) {
			first= aux;
			last= aux;
			//aux.setNext(aux);
			first.setNext(last);
			//aux.setPrev(aux);
			last.setPrev(first);
			size++;
		}else {
			last.setNext(aux);
			aux.setPrev(last);
			aux.setNext(null);
			last= aux;
			size++;
		}
	}

	@Override
	public E Ipeek() {
		E aux= null;
		if(first.getNext()== null) {
			aux= first.getElement();
		}else {
			aux= last.getElement();
		}
		return aux;
	}

	@Override
	public E pop() {
		// TODO Auto-generated method stub
		E aux= null;
		if(first.getNext()==null) {
			aux= first.getElement();
			first=null;
			size--;
		}else {
			aux= last.getElement();
			last.getPrev().setNext(null);
			last= last.getPrev();
			size--;
		}
		return aux;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return first==null;
	}

	@Override
	public int Isize() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub
	}

}
