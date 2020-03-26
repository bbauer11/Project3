import java.util.Comparator;
import java.util.ListIterator;

/**
 * 
 * @author Brian Bauer
 *
 *
 *
 * @param <T>
 */
public class SortedDoubleLinkedList<T> extends BasicDoubleLinkedList<T> {

	private Comparator<T> comparator;
	
	public SortedDoubleLinkedList(Comparator<T> comparator2) {
		head = null;
		tail = null;
		size = 0;
		this.comparator = comparator2;
	}

	@Override
	public BasicDoubleLinkedList<T> addToFront(T data) {
		throw new UnsupportedOperationException("Invalid operation for sorted list");
	}
	
	@Override
	public BasicDoubleLinkedList<T> addToEnd(T data) {
		throw new UnsupportedOperationException("Invalid operation for sorted list");
	}

	public SortedDoubleLinkedList<T> add(T data) {
		if (size == 0) {
			head = new Node(data);
			tail = head;
			size++;
		} else {
			Node temp = new Node(data);
			Node curr = head;
			while (comparator.compare(curr.data, temp.data) < 0 && curr.next != null) {
				curr = curr.next;
			}
			if (curr.next == null) {
				tail.next = temp;
				temp.prev = tail;
				tail = temp;
				return this;
			} else if (comparator.compare(curr.data, temp.data) == 0) {
				if (curr == head) {
					temp.next = curr.next;
					temp.prev = curr;
					temp.next.prev = temp;
					curr.next = temp;
					return this;
				} else {
					temp.next = curr;
					curr.prev.next = temp;
					temp.prev = curr.prev;
					curr.prev = temp;
					return this;
				}
			} else {
				temp.next = curr.next;
				temp.prev = curr;
				curr.next.prev = temp;
				curr.next = temp;
				return this;
			}
		} 
		return this;
	}
	
	public ListIterator<T> iterator() {
		return super.iterator();
	}
	
	public SortedDoubleLinkedList<T> remove(T data, Comparator<T> comparator) {
		super.remove(data, comparator);
		return this;
	}
}
