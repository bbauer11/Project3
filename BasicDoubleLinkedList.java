import java.util.ArrayList;
import java.util.Comparator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * 
 * @author Brian Bauer
 * 
 *
 * @param <T>
 */
public class BasicDoubleLinkedList<T> implements Iterable<T> {
	
	Node head;
	Node tail;
	int size;

	public BasicDoubleLinkedList() {
		head = null;
		tail = null;
		size = 0;
	}
	
	public BasicDoubleLinkedList<T> addToFront(T data) {
		if (size == 0) {
			head = new Node(data);
			tail = head;
		} else {
			Node temp = new Node(data);
			head.prev = temp;
			temp.next = head;
			head = temp;
		}
		size++;
		return this;
	}
	
	public BasicDoubleLinkedList<T> addToEnd(T data) {
		if (size == 0) {
			head = new Node(data);
			tail = head;
		} else {
			Node temp = new Node(data);
			temp.prev = tail;
			tail.next = temp;
			tail = temp;
		}
		size++;
		return this;
	}
	
	public T getFirst() {
		return head.data;
	}
	
	public T getLast() {
		return tail.data;
	}
	
	public ListIterator<T> iterator() {
		return new MyIterator<T>();
	}

	public ArrayList<T> toArrayList() {
		ArrayList<T> arrayList = new ArrayList<>();
		Node curr = head;
		while (curr != tail) {
			arrayList.add(curr.data);
			curr = curr.next;
		}
		if (size > 0) 
			arrayList.add(curr.data);
		return arrayList;
	}
	
	public int getSize() {
		return size;
	}
	
	public T retrieveLastElement() {
		if (size == 0)
			return null;
		else if (size == 1) {
			T data = tail.data;
			size--;
			tail = null;
			head = null;
			return data;
		} else if (size == 2) {
			T data = tail.data;
			size--;
			head.next = null;
			tail = head;
			return data;
		} else {
			T data = tail.data;
			tail.prev.next = null;
			tail = tail.prev;			
			size--;
			return data;
		}	
	}
	
	public T retrieveFirstElement() {
		if (size == 0)
			return null;
		else if (size == 1) {
			T data = head.data;
			head = null;
			tail = null;
			size--;
			return data;
		} else if (size == 2) {
			T data = head.data;
			size--;
			tail.prev = null;
			head = tail;
			return data;
		} else {
			T data = head.data;
			head.next.prev = null;
			head = head.next;
			size--;
			return data;
		}
	}
	
	public BasicDoubleLinkedList<T> remove(T data, Comparator<T> comparator) {
		Node temp = new Node(data);
		Node curr = head;
		while (comparator.compare(curr.data, temp.data) != 0 && curr.next != null) {
			curr = curr.next;
		}
		if (curr.next == null && comparator.compare(curr.data, temp.data) != 0) 
			return null;
		else { 	
			if (size > 1) {
				if (curr == head) {
					curr.next.prev = null;
					head = curr.next;
					size--;
					return this;
				} else if (curr == tail) {
					curr.prev.next = null;
					tail = curr.prev;
					size--;
					return this;
				} else {
					curr.next.prev = curr.prev;
					curr.prev.next = curr.next;
					size--;
					return this;
				}
			} else {
				head = null;
				tail = null;
				size--;
				return this;
			}
		}
	}
	
	class MyIterator<T> implements ListIterator<T> {

		private Node curr;
		private Node nextNode;
		private Node prevNode;
			
		private MyIterator() {
			curr = null;
			nextNode = head;
			prevNode = null;
		}
		
		@Override
		public boolean hasNext() {
			return nextNode == null ?  false : true;
		}

		@SuppressWarnings("unchecked")
		@Override
		public T next() {
			if (hasNext()) {
				prevNode = curr;
				curr = nextNode;
				nextNode = nextNode.next;
				return (T) curr.data;
			} else 
				throw new NoSuchElementException("Illegal call to next(); iterator is after end of list.");
		}

		@Override
		public boolean hasPrevious() {
			return curr != null ? true : false;
		}

		@SuppressWarnings("unchecked")
		@Override
		public T previous() {
			if (hasPrevious()) {
				nextNode = curr;
				curr = prevNode;
				if (prevNode == null) 
					return (T) nextNode.data;
				prevNode = prevNode.prev;
				return (T) nextNode.data;
			}
			throw new NoSuchElementException();
		}

		@Override
		public int nextIndex() {
			throw new UnsupportedOperationException("Method not implemented.");
		}

		@Override
		public int previousIndex() {
			throw new UnsupportedOperationException("Method not implemented.");
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException("Method not implemented.");
		}

		@Override
		public void set(T e) {
			throw new UnsupportedOperationException("Method not implemented.");
		}

		@Override
		public void add(T e) {
			throw new UnsupportedOperationException("Method not implemented.");
		}
		
	}
	
	class Node {
		T data;
		Node next;
		Node prev;
		
		public Node(T data) {
			this.data = data;
			next = null;
			prev = null;
		}	
	}


}
