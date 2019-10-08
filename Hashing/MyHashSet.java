import java.util.*;
/**
* comment this class completely, and in accordance with the style guide.
*/

public class MyHashSet<E> implements Iterable<E>
{
	private static final int NUM_BUCKETS = 5;
	private LinkedList<E>[] buckets;
	private int size;

	public MyHashSet()
	{
		buckets = new LinkedList[NUM_BUCKETS];
		for (int i = 0; i < buckets.length; i++)
		{
			buckets[i] = new LinkedList<>();
		}
		size = 0;


	}

	//returns the index of the bucket where obj might be found
	private int toBucketIndex(Object obj)
    {
		return Math.abs(obj.hashCode()) % NUM_BUCKETS;
	}

	public int size()
	{
		return size;
	}

	public boolean contains(Object obj)
	{
        for (Object o : buckets[toBucketIndex(obj)])
        {
            if (o.equals(obj))
            {
                return true;
            }
        }

        return false;
	}

	// if obj is not present in this set, adds obj and
	// returns true; otherwise returns false
	public boolean add(E obj)
	{
        if (contains(obj)) return false;

        buckets[toBucketIndex(obj)].addFirst(obj);
        size++;
        return true;
	}

	// if obj is present in this set, removes obj and
	// returns true; otherwise returns false
	public boolean remove(Object obj)
	{
        Iterator it = buckets[toBucketIndex(obj)].iterator();
        Object next;
        while (it.hasNext())
        {
            next = it.next();
            if (obj.equals(next))
            {
                it.remove();
                return true;
            }
        }
        return false;
	}

	public String toString()
	{
		String s = "";
		for (int i = 0; i < buckets.length; i++)
			if (buckets[i].size() > 0)
				s += i + ":" + buckets[i] + " ";
		return s;
	}

	@Override
	public Iterator<E> iterator()
	{
		Iterator<E> it = new Iterator<E>()
		{
			int bucketIndex = 0;
			Iterator<E> bucketIterator;
			int count = 0;

			@Override
			public boolean hasNext()
			{
				return count < size;

			}

			@Override
			public E next()
			{
				if (!hasNext())
				{
					throw new NoSuchElementException();
				}

				if (bucketIterator == null)
				{
					bucketIterator = buckets[bucketIndex].iterator();
				}

				if (!bucketIterator.hasNext())
				{
					while (buckets[bucketIndex].isEmpty())
					{
						bucketIndex++;
					}
					bucketIterator = buckets[bucketIndex].iterator();
				}
				count++;
				return bucketIterator.next();
			}

			@Override
			public void remove()
			{
				throw new UnsupportedOperationException();
			}
		};
		return it;
	}
}