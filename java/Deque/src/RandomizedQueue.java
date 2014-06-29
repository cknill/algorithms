import java.util.Iterator;
import java.util.NoSuchElementException;


public final class RandomizedQueue<T> implements Iterable<T>
{
	// Constructs an empty queue
	public RandomizedQueue()
	{
		count = 0;
		front = 0;
		impl = (T[]) new Object[1];
	}
	
	// Tests whether the queue is empty or not.
	public boolean isEmpty()
	{
		return count == 0;
	}
	
	// Tells how many elements are on the queue
	public int size()
	{
		return count;
	}
	
	// puts an element on the queue
	public void enqueue(T elem)
	{
		if (elem == null)
		{
			throw new NullPointerException();
		}

		;
	}
	
	// Removes an element from the queue
	public T dequeue()
	{
		if (isEmpty())
		{
			throw new NoSuchElementException();
		}

		return null;
	}

	// Returns a random entry in the queue, does not remove it.
	public T sample()
	{
		return null;
	}

	@Override
	public Iterator<T> iterator()
	{
		return new RandomQueueIterator();
	}
	
	private T[] impl;
	private int count;
	private int front;
	private int end;
	
	private final class RandomQueueIterator implements Iterator<T>
	{
		public RandomQueueIterator()
		{
			;
		}

		@Override
		public boolean hasNext()
		{
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public T next()
		{
			if (isEmpty())
			{
				throw new NoSuchElementException();
			}
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void remove()
		{
			throw new UnsupportedOperationException();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		StdOut.println("Spanish Inquisition!");
	
	}

}
