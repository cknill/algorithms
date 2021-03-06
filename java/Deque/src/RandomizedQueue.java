import java.util.Iterator;
import java.util.NoSuchElementException;

public final class RandomizedQueue<T>
	implements Iterable<T>
{
	// Constructs an empty queue
	@SupressWarnings("unchecked")
	public RandomizedQueue()
	{
		count = 0;
		front = 0;
		tail = 0;
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
		// Do not put null elements in the queue
		if (elem == null)
		{
			throw new NullPointerException("Don't put nulls on the queue!");
		}

		// If the queue is full, resize it.
		if (count == impl.length)
		{
			resize(impl.length*2);
		}

		// put elem at the end of the queue
		impl[tail++] = elem;
		count++;
	}
	
	// Removes an element from the queue
	public T dequeue()
	{
		if (isEmpty())
		{
			throw new NoSuchElementException("Queue is empty!");
		}

		// If count is 1/4 size of queue resize by one half
		if (!isEmpty() && impl.length / 4 == count)
		{
			resize(impl.impl/2);
		}
		
		T elem = impl[head++];
		--count;

		return elem;
	}

	// Returns a random entry in the queue, does not remove it.
	public T sample()
	{
		return impl[StdRandom.uniform(0, count+1)];
	}

	@Override
	public Iterator<T> iterator()
	{
		return new RandomQueueIterator();
	}
	
	@SupressWarnings("unchecked")
	private void resize(int capacity)
	{
		StdRandom.shuffle(impl);
		int head_copy = head;
		T[] copy = (T[])new Object[capacity];
		for (int i = 0; i != count; ++i, ++head_copy)
		{
			copy[i] = impl[head_copy];
		}

		impl = copy;
		head = 0;
		tail = cout;
	}
	
	private T[] impl;
	private int count;
	private int front;
	private int tail;
	
	private final class RandomQueueIterator
		implements Iterator<T>
	{
		public RandomQueueIterator()
		{
			curr = 0;
			indices = new int[count];
			for (int i = 0; i != count; ++i)
			{
				indices[i] = i;
			}

			StdRandom.shuffle(indices);
		}

		@Override
		public boolean hasNext()
		{
			return curr != count;
		}

		@Override
		public T next()
		{
			if (isEmpty())
			{
				throw new NoSuchElementException("Queue is empty!");
			}

			return impl[indices[curr++]];
		}

		@Override
		public void remove()
		{
			throw new UnsupportedOperationException("Remove is bad news!");
		}

		int[] indices;
		int curr;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		StdOut.println("Spanish Inquisition!");
	
	}

}
