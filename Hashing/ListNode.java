public class ListNode<E> {
    private E value;       // data stored in this node
    private ListNode next;  // link to next node in the list

    // post: constructs a node with data 0 and null link
    public ListNode() {
        this(null, null);
    }

    // post: constructs a node with given data and null link
    public ListNode(E value) {
        this(value, null);
    }

    // post: constructs a node with given data and given link
    public ListNode(E val, ListNode next) {
        this.value = val;
        this.next = next;
    }

    public void setNext(ListNode next)
    {
        this.next = next;
    }

    public void setValue(E value)
    {
        this.value = value;
    }

    public E getValue()
    {
        return this.value;
    }

    public ListNode getNext()
    {
        return this.next;
    }
}