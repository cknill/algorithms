import java.util.Iterator;
import java.util.NoSuchElementException;


public final class Deque<T> implements Iterable<T>
{
	// Constructs an empty deque
	public Deque()
	{
		firstPtr = null;
		lastPtr = null;
		count = 0;
	}
	
	// Returns true if the deque is empty
	public boolean isEmpty()
	{
		return firstPtr == null;
	}
	
	// Returns the number of elements in the deque
	public int size()
	{
		return count;
	}
	
	// Adds an element to the front of the deque
	public void addFirst(T elem)
	{
		// Don't add null items. Throw NullPointerException
		if (elem == null)
		{
			throw new NullPointerException();
		}
		
		Node item = new Node(elem);
		++count;
		if (count == 1)
		{
			firstPtr = item;
			lastPtr = item;
			return;
		}
		
		item.next = firstPtr;
		firstPtr.prev = item;
		firstPtr = item;
	}
	
	// Adds an element to the end of the deque
	public void addLast(T elem)
	{
		// Don't add null items. Throw NullPointerException
		if (elem == null)
		{
			throw new NullPointerException();
		}

		Node item = new Node(elem);
		++count;
		if (count == 1)
		{
			firstPtr = item;
			lastPtr = item;
			return;
		}
		
		item.prev = lastPtr;
		lastPtr.next = item;
		lastPtr = item;
	}
	
	// Removes an element from the front of the deque
	public T removeFirst()
	{
		// Don't remove from an empty deque. Throw NoSuchElementException
		if (isEmpty())
		{
			throw new NoSuchElementException();
		}
		
		T elem = firstPtr.val;
		if (count == 1)
		{
			firstPtr = null;
			lastPtr = null;
			--count;

			return elem;
		}

		Node temp = firstPtr.next;
		temp.prev = null;
		firstPtr = temp;
		--count;

		return elem;
	}
	
	// Removes an element from the end of the deque
	public T removeLast()
	{
		// Don't remove from an empty deque. Throw NoSuchElementException
		if (isEmpty())
		{
			throw new NoSuchElementException();
		}

		T elem = lastPtr.val;
		if (count == 1)
		{
			firstPtr = null;
			lastPtr = null;
			--count;
			
			return elem;
		}
		
		Node temp = lastPtr.prev;
		temp.next = null;
		lastPtr = temp;
		
		return elem;
	}

	private final class Node
	{
		public Node(T elem)
		{
			val = elem;
			next = null;
			prev = null;
		}

		Node next;
		Node prev;
		T val;
	}
	
	// produces an object that iterates over the entire collection
	@Override
	public Iterator<T> iterator()
	{
		return new DequeIterator();
	}
	
	private Node firstPtr;
	private Node lastPtr;
	private int count;
		
	private final class DequeIterator implements Iterator<T>
	{
		public DequeIterator()
		{
			firstPtrIter = firstPtr;
		}
		@Override
		public boolean hasNext()
		{
			return firstPtrIter != lastPtrIter;
		}

		@Override
		public T next()
		{
			if (isEmpty())
			{
				throw new NoSuchElementException();
			}

			T elem = firstPtrIter.val;
			firstPtrIter = firstPtrIter.next;
			return elem;
		}

		@Override
		public void remove()
		{
			throw new UnsupportedOperationException();
		}
		
		Node firstPtrIter;
		Node lastPtrIter;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		Deque<Integer> d = new Deque<Integer>();
		d.addFirst(1);
		d.addLast(2);
		for (int i : d)
		{
			System.out.println(i);
			for (int j : d)
			{
				System.out.println("\t" + j);
			}
		}
		
		System.out.println(d.removeFirst());
		System.out.println(d.removeLast());
		System.out.println(d.size());
	}

}
