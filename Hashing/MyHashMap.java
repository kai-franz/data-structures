import java.util.*;
/**
 * MyHashMap acts like a map.
 * 
 * @author  
 * @version 
 * @param <K>   the type of key
 * @param <V>   the type of value
 */
public class MyHashMap<K, V> implements Map<K, V>
{
    private static final int NUM_BUCKETS = 5;
    private ListNode<MapEntry<K, V>>[] buckets;
    private int size;

    /**
     * a constructor
     */
    public MyHashMap()
    {
        buckets = (ListNode<MapEntry<K, V>>[]) new ListNode[NUM_BUCKETS];
        size = 0;
    }
    
    /**
     * @param obj
     *            the object to find the bucket index for
     * @return the correct bucket index for that object
     */
    private int toBucketIndex(Object obj)
    {
        return Math.abs(obj.hashCode()) % NUM_BUCKETS;
    }


    public int size()
    {
        return size;
    }


    public boolean isEmpty()
    {
        return size == 0;
    }

    @Override
    public boolean containsKey(Object key)
    {
        ListNode<MapEntry<K,V>> node = buckets[toBucketIndex(key)];
        if (node == null) return false;
        while (node != null)
        {
            if (key.equals(node.getValue().getKey()))
            {
                return true;
            }
            node = node.getNext();
        }
        return false;
    }


    public boolean containsValue(Object value)
    {
        ListNode<MapEntry<K,V>> node = buckets[toBucketIndex(value)];
        if (node == null) return false;
        while (node != null)
        {
            if (value.equals(node.getValue().getValue()))
            {
                return true;
            }
            node = node.getNext();
        }
        return false;
    }


    public V get(Object key)
    {
        ListNode<MapEntry<K,V>> node = buckets[toBucketIndex(key)];
        while (node != null)
        {
            if (key.equals(node.getValue().getKey()))
            {
                return node.getValue().getValue();
            }
            node = node.getNext();
        }
        return null;
    }


    public V put(K key, V value)
    {
        size++;
        V prev = null;
        MapEntry<K,V> entry = new MapEntry<K,V>(key, value);
        ListNode<MapEntry<K,V>> node = buckets[toBucketIndex(key)];
        if (node == null)
        {
            node = new ListNode<MapEntry<K,V>>();
            node.setValue(entry);
            buckets[toBucketIndex(key)] = node;
        }
        else if (key.equals(node.getValue().getKey()))
        {
            prev = node.getValue().getValue();
            node.setValue(entry);
        }
        while (node.getNext() != null)
        {
            if (key.equals(node.getValue().getKey()))
            {
                prev = node.getValue().getValue();
                node.setValue(entry);
            }
        }
        return null;
    }


    public V remove(Object key)
    {
        ListNode<MapEntry<K,V>> list = buckets[toBucketIndex(key)];
        if (list == null) return null;
        if (list.getValue().getKey().equals(key))
        {
            buckets[toBucketIndex(key)] = list.getNext();
            size--;
            return list.getValue().getValue();
        }
        while (list.getNext() != null)
        {
            if (key.equals(((ListNode<MapEntry<K,V>>) list.getNext()).getValue().getKey()))
            {
                V prev = ((ListNode<MapEntry<K,V>>) list.getNext()).getValue().getValue();
                list.setNext(list.getNext().getNext());
                size--;
                return prev;
            }
        }
        return null;
    }


    public void putAll(Map<? extends K, ? extends V> m)
    {
        for (K key : m.keySet())
        {
            put(key, m.get(key));
        }
    }


    public void clear()
    {
        for (int i = 0; i < NUM_BUCKETS; i++)
        {
            buckets[i] = null;
        }
    }


    public Set<K> keySet()
    {
        Set<K> s = new HashSet<>();
        for (ListNode<MapEntry<K,V>> list : buckets)
        {
            ListNode<MapEntry<K,V>> entry = list;
            while (entry != null)
            {
                s.add(entry.getValue().getKey());
                entry = entry.getNext();
            }
        }
        return s;
    }


    public Collection<V> values()
    {
        Collection<V> c = new HashSet<>();
        for (ListNode<MapEntry<K,V>> list : buckets)
        {
            ListNode<MapEntry<K,V>> entry = list;
            while (entry != null)
            {
                c.add(entry.getValue().getValue());
                entry = entry.getNext();
            }
        }
        return c;
    }

    @Override
    public Set<Entry<K, V>> entrySet()
    {
        Set<Entry<K,V>> s = new HashSet<>();
        for (ListNode<MapEntry<K,V>> list : buckets)
        {
            ListNode<MapEntry<K,V>> entry = list;
            while (entry != null)
            {
                s.add(entry.getValue());
                entry = entry.getNext();
            }
        }
        return s;
    }

    @Override
    public String toString()
    {
        String str = "{";
        for (int i = 0; i < buckets.length; i++)
        {
            if (buckets[i] != null)
            {
                if (buckets[i].getValue() != null)
                {
                    ListNode<MapEntry<K, V>> node = buckets[i];
                    while (node != null)
                    {
                        str += node.getValue().getKey() + "=" + node.getValue().getValue().toString();
                    }
                }
            }
        }
        return str + "}";
    }
}
