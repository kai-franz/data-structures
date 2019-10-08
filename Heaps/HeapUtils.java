/**
 * HeapUtils class provides methods for heaps like
 * heapify, buildheap, heapsort, remove, and insert.
 * @author Evan Cheng and Kai Franz
 * @version 1/23/17
 */
public class HeapUtils
{

    /**
     * The heapify method modifies the
     * array so that the subtree rooted at index
     * has heap properties when displayed as a tree
     * This process runs in lg(n) time. It compares
     * the larger of the left or right children and
     * swaps it with the parent node if the parent is
     * smaller. This continues until completion.
     * @param heap the array that contains the heap data
     * @param index the root of the tree being heapified
     * @param heapSize the size of the heap
     */
    public static void heapify(Comparable[] heap, int index, int heapSize)
    {
        int left=index*2;
        int right=(index*2)+1;
        int max=index;
        if (left<=heapSize && right<=heapSize)
        {
            if (heap[left].compareTo(heap[right])>0)
                max=left;
            else
                max=right;
        }
        else if (left<=heapSize)
            max=left;
        if (heap[index].compareTo(heap[max])<0)
        {
            swap(heap, index, max);
            heapify(heap, max, heapSize);
        }
    }
    /**
     * The heapsort method sorts the values in the array so that
     * the resulting values are in ascending order. This is
     * done by moving swapping the first and last values and
     * then heapifying until everything is sorted. This runs
     * in nlogn time.
     * @postcondition heap is a sorted array in ascending order
     * @param heap the array representation of the heap
     * @param heapSize the size of the heap
     */
    public static void heapsort(Comparable[] heap, int heapSize)
    {
        buildHeap(heap,heapSize);
        for (int i=heapSize; i>0; i--)
            remove(heap, i);
    }


    /**
     * The swap method swaps the elements of an array
     * @postcondition the elements at index1 and index2 are swapped
     * @param heap the array to swap the elements
     * @param index1 the index of the first element
     * @param index2 the index of the second element
     */
    private static void swap(Comparable[] heap, int index1, int index2)
    {
        Comparable temp = heap[index1];
        heap[index1] = heap[index2];
        heap[index2] = temp;
    }


    /**
     * The buildHeap method sorts an array in order for it
     * to satisfy the conditions of a max-heap.
     * This is done starting at the last non-leaf node
     * and heapifying until the root node. This is done
     * in nlogn time.
     * @param heap the array to be heapified
     * @param heapSize the size of the heap
     */
    public static void buildHeap(Comparable[] heap, int heapSize)
    {
        for (int i = (heapSize/2); i > 0; i--)
        {
            heapify(heap, i, heapSize);
        }
    }


    /**
     * The remove method removes the root value of a heap,
     * making it one smaller while retaining heap properties.
     * This runs in logn time
     * @postcondition the root is removed
     * @param heap the heap to remove the value from
     * @param heapSize the size of the heap
     * @return the removed value
     */
    public static Comparable remove(Comparable[] heap, int heapSize)
    {
        Comparable temp=heap[1];
        swap(heap, 1, heapSize);
        heapify(heap, 1, heapSize-1);
        return temp;
    }

    /**
     * The insert method inserts a  value to the heap
     * by adding it as the last leaf node then using
     * buildHeap to satisfy heap conditions. This runs
     * in nlogn time.
     * @postcondition the item will be added
     * @param heap the heap to add the value to
     * @param item the value to be inserted
     * @param heapSize the size of the heap
     * @return the new heap
     */
    public static Comparable[] insert(Comparable[] heap, Comparable item, int heapSize)
    {
        Comparable[] newHeap=new Comparable[heap.length+1];
        for (int i=1; i<heap.length; i++)
            newHeap[i]=heap[i];
        newHeap[newHeap.length-1]=item;
        buildHeap(newHeap, heapSize+1);
        return newHeap;
    }

}
