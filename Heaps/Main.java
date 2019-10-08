/**
 * Main tests the HeapUtil methods
 * @author Evan Cheng and Kai Franz
 * @version 1/23/18
 */
public class Main
{

    /**
     * Tests the methods of HeapUtil
     * @param args the argument
     */
    public static void main(String[] args)
    {

        HeapUtils util=new HeapUtils();
        Comparable [] heap = new Integer[12];
        for (int i = 1; i <heap.length; i++)
        {
            heap[i] = (int)(Math.random() * 99 + 1);
        }

        System.out.println("Initial:");
        for (int i=1; i<heap.length; i++) //Tests heapsort
            System.out.print(heap[i]+" ");
        System.out.println();

        util.buildHeap(heap, 11);
        System.out.println("BuildHeap:");
        for (int i=1; i<heap.length; i++) //Tests heapsort
            System.out.print(heap[i]+" ");
        System.out.println();

        util.heapsort(heap, 11);
        System.out.println("heapsort:");
        for (int i=1; i<heap.length; i++) //Tests heapsort
            System.out.print(heap[i]+" ");
        System.out.println();

        heap=util.insert(heap, 55, 11);
        System.out.println("insert:");
        for (int i=1; i<heap.length; i++) //Tests heapsort
            System.out.print(heap[i]+" ");
        System.out.println();

        util.heapsort(heap, 11);
        System.out.println("sorted new heap:");
        for (int i=1; i<heap.length; i++) //Tests heapsort
            System.out.print(heap[i]+" ");
        System.out.println();
    }
}
